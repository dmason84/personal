package uk.co.douglasmason.parkrun;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.douglasmason.parkrun.athlete.Athlete;
import uk.co.douglasmason.parkrun.athlete.Result;

public class Parkrun {

	public static DateFormat dateFormatParkrun = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat dateFormatCsv = new SimpleDateFormat("MM/dd/yyyy");
	private static LinkedList<Athlete> athletes = new LinkedList<Athlete>();
	private static Date firstRun = new Date();
	
	public static void main(String[] args) throws IOException, ParseException {
		for (String s : args) {
			Athlete athlete = new Athlete(s);
			getAthleteData(athlete);
			athletes.add(athlete);
			Date athleteFirstRun = dateFormatParkrun.parse(athlete.getResults().getLast().getRunDate());
			if (athleteFirstRun.compareTo(firstRun) < 0) {
				firstRun = athleteFirstRun;
			}			
		}
		System.out.println();
		System.out.println("First run: " + dateFormatParkrun.format(firstRun));
		System.out.println();
		outputCsv();
	}

	private static void getAthleteData(Athlete athlete) throws IOException {
		System.out.println("Getting data for athlete: " + athlete.getId());
		// Construct the athlete URL for all results
		String url = "http://www.parkrun.org.uk/results/athleteeventresultshistory/?athleteNumber="
				+ athlete.getId() + "&eventNumber=0";

		// Get the Jsoup Document. Need the user agent otherwise we get a 403
		// error
		Document doc = Jsoup
				.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36")
				.get();
		
		String html = doc.toString();
		int index1 = html.indexOf("h2");
		int index2 = html.indexOf(" - ", index1);
		String name = html.substring(index1 + 3, index2);
		athlete.setName(name);
		
		Elements tableElements = doc.select("table[id=results]");

		/*
		 * Elements tableHeaderEles = tableElements.select("thead tr th");
		 * System.out.println("headers"); for (int i = 0; i <
		 * tableHeaderEles.size(); i++) {
		 * System.out.println(tableHeaderEles.get(i).text()); }
		 * System.out.println();
		 */

		Elements tableRowElements = tableElements.select(":not(thead) tr");

		for (int i = 0; i < tableRowElements.size(); i++) {
			Element row = tableRowElements.get(i);
			Elements rowItems = row.select("td");
			if (rowItems.size() == 7) {
				Result result = new Result();
				result.setEvent(rowItems.get(0).text());
				result.setRunDate(rowItems.get(1).text());
				result.setEventNumber(rowItems.get(2).text());
				result.setPosition(rowItems.get(3).text());
				result.setTime(rowItems.get(4).text());
				result.setAgeGrade(rowItems.get(5).text());
				result.setPb(rowItems.get(6).text());

				athlete.addResult(result);
			}
		}
		System.out.println("Successfully retrieved " + athlete.getName() + " (" + athlete.getResults().size() + " runs since " + athlete.getResults().getLast().getRunDate() + ")");
	}
	
	private static void outputCsv() throws ParseException {
		String athleteHeader = "Date";
		String dataTypeHeader = "date";
		for (Athlete athlete : athletes) {
			athleteHeader += "," + athlete.getName();
			dataTypeHeader += ",number"; 
		}
		System.out.println(athleteHeader);
		System.out.println(dataTypeHeader);
		Calendar runDate = Calendar.getInstance();
		// Loop through every Saturday since the first parkrun
		runDate.setTime(firstRun);
		Calendar currentDate = Calendar.getInstance();
		while (runDate.compareTo(currentDate) < 0) {
			String row = dateFormatCsv.format(runDate.getTime());
			// Loop through athletes and find out how many runs they've done by this date
			for (Athlete athlete : athletes) {
				row += "," + athlete.getRunsDoneByDate(runDate);
			}
			System.out.println(row);
			runDate.add(Calendar.DATE, 7);
		}
	}
}
