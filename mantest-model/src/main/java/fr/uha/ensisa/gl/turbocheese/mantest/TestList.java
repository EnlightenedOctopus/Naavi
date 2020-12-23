package fr.uha.ensisa.gl.turbocheese.mantest;

import java.util.Collection;
import java.util.TreeMap;

public class TestList {
	private TreeMap<Long, Test> tList = new TreeMap<Long, Test>();
	private String name;
	private long id;
	
	public TestList(String name, Long id) {
		this.name=name;
		this.id=id;
	}
	
	public void addTest(Test test) {
		this.tList.put(test.getId(), test);
	}
	
	public void deleteTest(Long id) {
		this.tList.remove(id);

	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public int size() {
		return this.tList.size();
	}
	
	public Collection<Test> getTests(){
		return this.tList.values();
	}
	
	public Test find(long id) {
		return tList.get(id);	
	}

}
