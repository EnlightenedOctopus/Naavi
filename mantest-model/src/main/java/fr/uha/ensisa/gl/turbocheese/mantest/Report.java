package fr.uha.ensisa.gl.turbocheese.mantest;

import java.util.ArrayList;
import java.util.Date;

public class Report {
	private ArrayList<ExecutedTest> tests;
	private Date date;
	
	public Report() {
		tests= new ArrayList<ExecutedTest>();
		date = new Date(System.currentTimeMillis());
	}
	
	public int getCountTest() {
		return tests.size();
	}
	public ExecutedTest getExecutedTest(int index) {
		return tests.get(index);
	}
	public void addExecutedTest(ExecutedTest t) {
		tests.add(t);
	}	
	public Date getDate() {
		return date;
	}
}
