package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestTestDaoMem {
	private fr.uha.ensisa.gl.turbocheese.mantest.Test t;
	private TestDaoMem mem;
	
	@BeforeEach
	void create() {
		t = new fr.uha.ensisa.gl.turbocheese.mantest.Test(0, "test", "description");
		mem = new TestDaoMem();
	}
	
	@Test
	@DisplayName("We can add a test in the dao")
	void testDaoMemPersist() {
		mem.persist(t);
		assertEquals(t, mem.find(t.getId()));
		assertEquals(mem.findAll().size(), mem.count());
	}
	
	@Test
	@DisplayName("We can remove a test in the dao")
	void testDaoMemRemove() {
		mem.remove(t);
		assertEquals(0, mem.count());
	}

}
