package uk.co.douglasmason.parkrun;
import java.net.*;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.douglasmason.parkrun.athlete.Athlete;
import uk.co.douglasmason.parkrun.athlete.Result;

public class Parkrun {

	public static void main (String[] args) throws IOException {
		getAthleteHtml("587461");
	}
	
	public static void getAthleteHtml(String athleteId) throws IOException {
		// Construct the athlete URL for all results
		String url = "http://www.parkrun.org.uk/results/athleteeventresultshistory/?athleteNumber=" + athleteId + "&eventNumber=0";
		
		// Get the Jsoup Document. Need the user agent otherwise we get a 403 error
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36").get();
        Elements tableElements = doc.select("table[id=results]");

        Elements tableHeaderEles = tableElements.select("thead tr th");
        System.out.println("headers");
        for (int i = 0; i < tableHeaderEles.size(); i++) {
           System.out.println(tableHeaderEles.get(i).text());
        }
        System.out.println();

        Elements tableRowElements = tableElements.select(":not(thead) tr");

        Athlete athlete = new Athlete();
        
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
        	   
        	   System.out.println(result);
           }
        }
        
        System.out.println(athlete.getResults().size());
	}
}
