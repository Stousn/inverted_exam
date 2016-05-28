package at.fhj.itm.ssh.model;

import java.util.Date;

public class Race {
	//FIELD
	int id, lapCount;
	String track;
	String startTime, duration;	//TODO: Find a time format with HH:MM
	Date date;
	
	//CONSTRUCTOR
	public Race(int id, int lapCount, String track, String startTime, String duration, Date date) {
		super();
		this.id = id;
		this.lapCount = lapCount;
		this.track = track;
		this.startTime = startTime;
		this.duration = duration;
		this.date = date;
	}
		
	//METHODS
		
	
}
