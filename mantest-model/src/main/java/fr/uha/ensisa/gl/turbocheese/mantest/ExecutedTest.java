package fr.uha.ensisa.gl.turbocheese.mantest;

public class ExecutedTest {
	private Test test;
	private State state;
	private String comment;
	
	public enum State { 
		SUCCESS, FAILED, SKIPED
	}
	
	public ExecutedTest(Test t,State s,String c) {
		test=t;
		state=s;
		comment=c;
	}
	
	public ExecutedTest(Test success_test) {
		//When a test is success, comment is not useful
		test=success_test;
		state=State.SUCCESS;
		comment=null;
	}
	
	public Test getTest() {
		return test;
	}
	public State getState() {
		return state;
	}
	public String getComment() {
		return comment;
	}
	
}
