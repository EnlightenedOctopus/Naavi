package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDaoFactoryMem {
	private DaoFactoryMem dfm;
	
	@BeforeEach
	void create() {
		dfm = new DaoFactoryMem();
	}

	@Test
	void getTestDao() {
		assertEquals(dfm.testDao, dfm.getTestDao());
	}
	
	@Test
	void getTestListDao() {
		assertEquals(dfm.testListDao, dfm.getTestListDao());
	}
	
	@Test
	void getReportDao() {
		assertEquals(dfm.reportDao, dfm.getReportDao());
	}

}
