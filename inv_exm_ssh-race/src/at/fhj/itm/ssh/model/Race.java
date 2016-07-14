package at.fhj.itm.ssh.model;

public class Race {
	//FIELD
	public int id, lapCount, track;
	public String startTime, duration;	//TODO: Find a time format with HH:MM
	public java.sql.Date date;
	
	//CONSTRUCTOR
	public Race(int id, int lapCount, int track, String startTime, String duration, java.sql.Date date) {
		super();
		this.id = id;
		this.lapCount = lapCount;
		this.track = track;
		this.startTime = startTime;
		this.duration = duration;
		this.date = date;
	}
		
	public Race() {
	}

	//METHODS
	@Override
	public String toString() {
		return "Race [id=" + id + ", lapCount=" + lapCount + ", track=" + track + ", startTime=" + startTime
				+ ", duration=" + duration + ", date=" + date + "]";
	}	
	
}
