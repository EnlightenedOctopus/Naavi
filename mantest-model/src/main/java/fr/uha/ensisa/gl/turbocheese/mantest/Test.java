package fr.uha.ensisa.gl.turbocheese.mantest;

public class Test {
	long id;
	String name;
	String description;
	
	public Test(long id, String name, String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}
	
	public Test() {
		this(0, null, null);
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
}
