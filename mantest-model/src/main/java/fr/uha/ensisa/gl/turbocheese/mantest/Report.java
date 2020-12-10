package fr.uha.ensisa.gl.turbocheese.mantest;

import java.util.ArrayList;

public class Report {
	private ArrayList<ExecutedTest> tests;
	
	public int getCountTest() {
		return tests.size();
	}
	public ExecutedTest getExecutedTest(int index) {
		return tests.get(index);
	}
	public void addExecutedTest(ExecutedTest t) {
		tests.add(t);
	}
	public void removeTest(int index) {
		tests.remove(index);
	}
	
	
}
