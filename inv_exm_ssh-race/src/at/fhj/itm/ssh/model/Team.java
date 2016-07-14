package at.fhj.itm.ssh.model;

public class Team {
	//FIELD
	int id;
	String code, name, brand;
	
	//CONSTRUCTOR
	public Team(int id, String code, String name, String brand) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.brand = brand;
	}
		
	//METHODS
	@Override
	public String toString() {
		return "Team [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + "]";
	}	
	
}
