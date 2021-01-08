package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.uha.ensisa.gl.turbocheese.mantest.Report;

class TestReportDaoMem {
	private Report r;
	private ReportDaoMem mem;

	@BeforeEach
	void create() {
		r = new Report();
		mem = new ReportDaoMem();
	}
	
	@Test
	@DisplayName("We can add a report in the dao")
	void testReportDaoMemAdd() {
		mem.addReport(r);
		assertEquals(r, mem.getReport(r.getDate().getTime()));
		assertEquals(mem.findAll().size(), mem.count());
	}
	
	@Test
	@DisplayName("We can remove a report in the dao")
	void testReportDaoMemRemove() {
		mem.remove(r);
		assertEquals(0, mem.count());
	}

}
