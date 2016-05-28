package at.fhj.itm.ssh.model;

import java.util.Date;

public class Driver {
	//FIELD
	int id, weightInKg;
	//int age; // ?
	Date dob;
	String fName, lName, team;
	//Sting brand; // ?
		
	//CONSTRUCTOR
	public Driver(int id, int weightInKg, Date dob, String fName, String lName, String team) {
		super();
		this.id = id;
		this.weightInKg = weightInKg;
		this.dob = dob;
		this.fName = fName;
		this.lName = lName;
		this.team = team;
	}
		
	//METHODS
		
	
}
