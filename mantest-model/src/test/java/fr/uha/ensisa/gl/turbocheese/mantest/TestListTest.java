package fr.uha.ensisa.gl.turbocheese.mantest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestListTest {
	fr.uha.ensisa.gl.turbocheese.mantest.TestList sut; // System Under Test
	
	@BeforeEach
	void createTestList() {
		sut = new fr.uha.ensisa.gl.turbocheese.mantest.TestList("test", (long) 1);
	}
	
	@Test
	@DisplayName("A testlist should have a name")
	void setName() {
		assertEquals("test",sut.getName());
		String name = "A sample testlist name";
		sut.setName(name);
		assertEquals(name, sut.getName());
	}
	
	@Test
	@DisplayName("A testlist should have an ID")
	void setId() {
		assertEquals(1, sut.getId());
		Long id = (long) 12;
		sut.setId(id);
		assertEquals((long)12, sut.getId());
	}
	
	@Test
	@DisplayName("We can add a test to the list")
	void addTest() {
		fr.uha.ensisa.gl.turbocheese.mantest.Test test = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		sut.addTest(test);
		assertEquals(1, sut.size());
		assertEquals(1, sut.getTests().size());
	}
	@Test
	@DisplayName("We can find a test in the list")
	void find() {
		fr.uha.ensisa.gl.turbocheese.mantest.Test test = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		sut.addTest(test);
		assertEquals(sut.getTests().toArray()[0], sut.find(test.getId()));
	}
	
	@Test
	@DisplayName("We can delete a test from the list")
	void delete() {
		fr.uha.ensisa.gl.turbocheese.mantest.Test test = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		sut.addTest(test);
		sut.deleteTest(test.getId());
		assertEquals(0, sut.getTests().size());
	}
	
	
}
