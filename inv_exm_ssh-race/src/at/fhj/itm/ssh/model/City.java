package at.fhj.itm.ssh.model;

public class City {
	//FIELD
	public int id;
	public String code;
	public String name;
	public int country;
	
	//CONSTRUCTOR
	public City(int id, String code, String name, int country) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.country = country;
	}
		
	public City() {
	}

	//METHODS
	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", name=" + name + ", country=" + country + "]";
	}	
		
}
