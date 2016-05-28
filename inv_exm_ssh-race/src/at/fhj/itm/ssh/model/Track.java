package at.fhj.itm.ssh.model;

public class Track {
	//FIELD
	int id, seatCount;
	double lenghtInM;
	String name, city;
	String fastestLap; //TODO: Find a Time format containing minutes, seconds, ... thousands.
	
	//CONSTRUCTOR
	public Track(int id, int seatCount, double lenghtInM, String name, String city, String fastestLap) {
		super();
		this.id = id;
		this.seatCount = seatCount;
		this.lenghtInM = lenghtInM;
		this.name = name;
		this.city = city;
		this.fastestLap = fastestLap;
	}
		
	//METHODS
		
	
}
