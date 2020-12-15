package fr.uha.ensisa.gl.turbocheese.mantest;

import java.util.ArrayList;
import java.util.Iterator;

public class TestList {
	private ArrayList<Test> tList = new ArrayList<Test>();
	private String name;
	private int id;
	
	public TestList(String name, int id) {
		this.name=name;
		this.id=id;
	}
	
	public Iterator<Test> getListIterator() {
		return this.tList.iterator();
	}
	
	public void addTest(Test test) {
		this.tList.add(test);
	}
	
	public void deleteTest(Test test) {
		this.tList.remove(test);
		//On détruit le test aussi de la mémoire ?
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	

}
