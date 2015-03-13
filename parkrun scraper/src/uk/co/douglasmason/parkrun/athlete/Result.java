package uk.co.douglasmason.parkrun.athlete;

public class Result {
	private String event;
	private String runDate;
	private String eventNumber;
	private String position;
	private String time;
	private String ageGrade;
	private String pb;
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public String getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAgeGrade() {
		return ageGrade;
	}
	public void setAgeGrade(String ageGrade) {
		this.ageGrade = ageGrade;
	}
	public String getPb() {
		return pb;
	}
	public void setPb(String pb) {
		this.pb = pb;
	}
	public String toString() {
		return "Event: " + event + " | "
				+ "Run Date: " + runDate + " | "
				+ "Event Number: " + eventNumber + " | "
				+ "Position: " + position + " | "
				+ "Time: " + time + " | "
				+ "Age Grade: " + ageGrade + " | "
				+ "PB?: " + pb;
	}
}
