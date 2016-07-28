package at.fhj.itm.ssh.model;

public class Brand {
	//FIELD
	public int id;
	public String code;
	public String name;
		
	//CONSTRUCTOR
	public Brand(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Brand() {
	}

	//METHODS
	@Override
	public String toString() {
		return "Brand [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	
}
