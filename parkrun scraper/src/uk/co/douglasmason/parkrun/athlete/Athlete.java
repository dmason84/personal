package uk.co.douglasmason.parkrun.athlete;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;

import uk.co.douglasmason.parkrun.Parkrun;

public class Athlete {
	// Member variables
	private String id;
	private String name;
	private LinkedList<Result> results = new LinkedList<Result>();
	
	public int getRunsDoneByDate(Calendar calendar) throws ParseException {
		int count = 0;
		for (Result result : results) {
			Calendar runDate = Calendar.getInstance();
			runDate.setTime(Parkrun.dateFormatParkrun.parse(result.getRunDate()));
			if (runDate.compareTo(calendar) <= 0) {
				count++;
			}
		}
		return count;
	}
	
	public Athlete(String id) {
		super();
		this.id = id;
	}

	public void addResult(Result result) {
		results.add(result);
	}
	
	public LinkedList<Result> getResults() {
		return results;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
