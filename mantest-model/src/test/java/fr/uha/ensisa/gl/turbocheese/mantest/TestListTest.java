package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestListTest {
	fr.uha.ensisa.gl.turbocheese.mantest.TestList sut; // System Under Test
	
	@BeforeEach
	void createTest() {
		sut = new fr.uha.ensisa.gl.turbocheese.mantest.TestList("test", (long) 1);
	}
	
	@Test
	@DisplayName("A testlist should have a name and a size")
	void setName() {
		assertEquals("test",sut.getName());
		String name = "A sample testlist name";
		sut.setName(name);
		assertEquals(name, sut.getName());
		assertEquals(1, sut.getId());
		Long id = (long) 12;
		sut.setId(id);
		assertEquals((long)12, sut.getId());
		sut.addTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test());
		assertEquals(1, sut.size());
	}
	
	
}
