package entities;

public class Rol {
	private int id;
	private String desc;
	
	public Rol() {
		
	}
	
	public Rol(int id, String d) {
		this.id = id;
		this.desc = d;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
	    return desc;
	}
}



