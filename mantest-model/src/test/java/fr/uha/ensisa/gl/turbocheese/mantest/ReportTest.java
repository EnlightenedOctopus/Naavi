package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportTest {

	Report sut;
	
	@BeforeEach
	void createTest() {
		sut = new Report();
	}
	
	@Test
	@DisplayName("Error in adding or deleting ExecutedTest in a Report")
	public void addAndDeleteExecutedTestTest(){
		ExecutedTest t1 = new ExecutedTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test());
		ExecutedTest t2 = new ExecutedTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(),ExecutedTest.State.FAILED,"No comment");
		sut.addExecutedTest(t1);
		sut.addExecutedTest(t2);
		assertEquals(t1, sut.getExecutedTest(0));
		assertEquals(t2, sut.getExecutedTest(1));
		assertEquals(2,sut.getCountTest());
		try {
			sut.getExecutedTest(2);
			fail("Out of bounds Exception must be throw");
		} catch ( Exception e ) {
		}
		try {
			sut.getExecutedTest(-1);
			fail("Exception must be throw");
		} catch ( Exception e ) {
		}
	}
	
	@Test
	@DisplayName("Error in a empty Report")
	public void emptyReport() {
		assertEquals(0, sut.getCountTest());
		try {
			sut.getExecutedTest(0);
			fail("He can find a test in an empty Report");
		} catch ( Exception e ) {
		}
	}
	
	@Test
	@DisplayName("Error in Report date")
	public void dateReport() {
		long justafter = System.currentTimeMillis();
		assertEquals(sut.getDate().getTime(),justafter);
	}
}
