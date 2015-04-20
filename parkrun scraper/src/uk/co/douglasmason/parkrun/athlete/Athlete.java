package uk.co.douglasmason.parkrun.athlete;

import java.util.LinkedList;

public class Athlete {
	// Member variables
	private String id;
	private String name;
	private LinkedList<Result> results = new LinkedList<Result>();
	
	
	
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
