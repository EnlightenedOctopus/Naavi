package fr.uha.ensisa.gl.turbocheese.mantest;

public class Test {
	long id;
	String name;
	
	public Test(long id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public Test() {
		this(0, null);
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
}
