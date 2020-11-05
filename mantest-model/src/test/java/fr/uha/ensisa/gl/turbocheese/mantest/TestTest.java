package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class TestTest {
		fr.uha.ensisa.gl.turbocheese.mantest.Test sut; // System Under Test

		
		@BeforeEach
		void createTest() {
			sut = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		}
		
		@Test
		@DisplayName("A test should have a name")
		void setName() {
			assertNull(sut.getName());
			String name = "A sample test name";
			sut.setName(name);
			assertEquals(name, sut.getName());
		}
}