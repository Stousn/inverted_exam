package at.fhj.itm.ssh.model;

public class Country {
	//FIELD
	int id;
	String code, name;
	
	//CONSTRUCTOR
	public Country(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
		
	//METHODS
	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + ", name=" + name + "]";
	}	
	
}
