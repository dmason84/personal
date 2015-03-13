package uk.co.douglasmason.parkrun.athlete;

import java.util.LinkedList;

public class Athlete {
	// Member variables
	private String id;
	private String name;
	private LinkedList<Result> results = new LinkedList<Result>();
	
	public void addResult(Result result) {
		results.add(result);
	}
	
	public LinkedList<Result> getResults() {
		return results;
	}
}
