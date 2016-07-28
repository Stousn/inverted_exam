package at.fhj.itm.ssh.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Race {
	//FIELD
	public int id;
	public int lapCount;
	public int trackId;
	public LocalTime startTime;
	public LocalTime duration;
	public LocalDate date;
	
	//CONSTRUCTOR
	public Race(int id, int lapCount, LocalTime startTime, LocalTime duration, LocalDate date, int trackId) {
		super();
		this.id = id;
		this.lapCount = lapCount;
		this.trackId = trackId;
		this.startTime = startTime;
		this.duration = duration;
		this.date = date;
	}
		
	public Race() {
	}

	//METHODS
	@Override
	public String toString() {
		return "Race [id=" + id + ", lapCount=" + lapCount + ", track=" + trackId + ", startTime=" + startTime
				+ ", duration=" + duration + ", date=" + date + "]";
	}	
	
}
