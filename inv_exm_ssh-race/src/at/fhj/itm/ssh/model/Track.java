package at.fhj.itm.ssh.model;

public class Track {
	//FIELD
	public int id, seatCount, city;
	public String lenghtInM;
	public String name;
	public String fastestLap; //TODO: Find a Time format containing minutes, seconds, ... thousands.

	//CONSTRUCTOR
	public Track(int id, int seatCount, String lenghtInM, String name, int city, String fastestLap) {
		super();
		this.id = id;
		this.seatCount = seatCount;
		this.lenghtInM = lenghtInM;
		this.name = name;
		this.city = city;
		this.fastestLap = fastestLap;
	}
	
	public Track(){
	}

	//METHODS
	@Override
	public String toString() {
		return "Track [id=" + id + ", seatCount=" + seatCount + ", lenghtInM=" + lenghtInM + ", name=" + name
				+ ", city=" + city + ", fastestLap=" + fastestLap + "]";
	}	

}
