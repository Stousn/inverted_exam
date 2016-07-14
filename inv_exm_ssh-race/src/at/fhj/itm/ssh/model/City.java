package at.fhj.itm.ssh.model;

public class City {
	//FIELD
	int id;
	String code, name, country;
	
	//CONSTRUCTOR
	public City(int id, String code, String name, String country) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.country = country;
	}
		
	//METHODS
	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", name=" + name + ", country=" + country + "]";
	}	
		
}
