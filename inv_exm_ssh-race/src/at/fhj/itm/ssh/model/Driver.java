package at.fhj.itm.ssh.model;


public class Driver {
	//FIELD
	public int id, weightInKg;
	public java.sql.Date dob;
	public String fName, lName; 
	public int country, team;
	//Sting brand; // ?
		
	//CONSTRUCTOR
	public Driver(int id, int weightInKg, java.sql.Date dob, String fName, String lName, int country, int team) {
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
