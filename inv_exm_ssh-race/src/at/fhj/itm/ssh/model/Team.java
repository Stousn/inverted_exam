package at.fhj.itm.ssh.model;

public class Team {
	//FIELD
	public int id, brand;
	public String code, name;
	
	//CONSTRUCTOR
	public Team(int id, String code, String name, int brand) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.brand = brand;
	}
	public Team() {
	}
		
	//METHODS
	@Override
	public String toString() {
		return "Team [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + "]";
	}	
	
}
