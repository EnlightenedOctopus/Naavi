package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class ExecutedTestTest {
		ExecutedTest sut; // System Under Test

		
		@Test
		@DisplayName("Constructor Executed Test Failed")
		void CorrectExecutedTestCreated() {
			ExecutedTest.State s =  ExecutedTest.State.SUCCESS;
			String testcomment = "Ce test a malheureusement raté";
			fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
			ExecutedTest sut = new ExecutedTest(t,s,testcomment);
			assertEquals(testcomment,sut.getComment());
			assertNull(sut.getTest().getName());
			assertEquals(0,sut.getTest().getId());
			sut.getTest().setId(12);
			assertEquals(12,sut.getTest().getId());
			assertEquals(s,sut.getState());
		}
		
		@Test
		@DisplayName("Fast Constructor Success Test Failed")
		void ExecutedTestSuccessCreated() {
			fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
			ExecutedTest sut = new ExecutedTest(t);
			assertEquals(t,sut.getTest());
			assertEquals(ExecutedTest.State.SUCCESS,sut.getState());
			assertNull(sut.getComment());
		}
}