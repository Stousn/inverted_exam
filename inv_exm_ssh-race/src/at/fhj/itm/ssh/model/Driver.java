package at.fhj.itm.ssh.model;

import java.time.LocalDate;

public class Driver {
	//FIELD
	public int id;
	public int weightInKg;
	public LocalDate dob;
	public String fName;
	public String lName; 
	public int country, team;
	//Sting brand; // ?
		
	//CONSTRUCTOR
	public Driver(int id, int weightInKg, LocalDate dob, String fName, String lName, int country, int team) {
		super();
		this.id = id;
		this.weightInKg = weightInKg;
		this.dob = dob;
		this.fName = fName;
		this.lName = lName;
		this.country = country;
		this.team = team;
	}
	
	public Driver() {
	}
		
	//METHODS
	@Override
	public String toString() {
		return "Driver [id=" + id + ", weightInKg=" + weightInKg + ", dob=" + dob + ", fName=" + fName + ", lName="
				+ lName + ", team=" + team + "]";
	}
	
}
