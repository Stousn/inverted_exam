package at.fhj.itm.ssh.model;

import java.time.LocalDateTime;

public class Track {
	//FIELD
	public int id;
	public int seatCount;
	public int city;
	public int lenghtInM;
	public String name;
	public LocalDateTime fastestLap;

	//CONSTRUCTOR
	public Track(int id, String name, int lenghtInM, LocalDateTime fastestLap, int seatCount, int cityId) {
		super();
		this.id = id;
		this.seatCount = seatCount;
		this.lenghtInM = lenghtInM;
		this.name = name;
		this.city = cityId;
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
