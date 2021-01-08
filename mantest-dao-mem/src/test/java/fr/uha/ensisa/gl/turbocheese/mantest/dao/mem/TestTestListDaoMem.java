package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.uha.ensisa.gl.turbocheese.mantest.TestList;

class TestTestListDaoMem {
	private TestList tl;
	private TestListDaoMem listMem;
	
	@BeforeEach
	void create() {
		tl = new TestList("list", 0l);
		listMem = new TestListDaoMem();
	}
	
	
	@Test
	@DisplayName("We can add a list in the dao")
	void testListDaoMemPersist() {
		listMem.persist(tl);
		assertEquals(tl, listMem.find(tl.getId()));
		assertEquals(listMem.findAll().size(), listMem.count());
	}
	
	@Test
	@DisplayName("We can remove a list in the dao")
	void testListDaoMemRemove() {
		listMem.remove(tl);
		assertEquals(0, listMem.count());
	}
}
