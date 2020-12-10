package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class ExecutedTestTest {
		fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest sut; // System Under Test

		
		@Test
		@DisplayName("Constructor Executed Test Failed")
		void CorrectExecutedTestCreated() {
			fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest.State s =  fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest.State.SUCESS;
			String testcomment = "Ce test a malheureusement raté";
			fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
			fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest sut = new fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest(t,s,testcomment);
			assertEquals(testcomment,sut.getComment());
			assertNull(sut.getTest().getName());
			assertEquals(0,sut.getTest().getId());
			sut.getTest().setId(12);
			assertEquals(12,sut.getTest().getId());
			assertEquals(s,sut.getState());
		}
}