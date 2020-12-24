package fr.uha.ensisa.ff.mantest_app.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.uha.ensisa.ff.mantest_app.controller.TestController;
import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest;
import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest.State;
import fr.uha.ensisa.gl.turbocheese.mantest.Report;
import fr.uha.ensisa.gl.turbocheese.mantest.TestList;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.mem.DaoFactoryMem;

public class TestControllerWithoutMockTest {
	
	public TestController sut;
	public DaoFactory daoFactory;

	@BeforeEach
	public void initialise() {
		sut = new TestController();
		daoFactory = new DaoFactoryMem();
		sut.daoFactory = daoFactory;
	}
	
	@Test
	@DisplayName("Success Test hasn't been saved in new Report")
	public void successReportTest() throws IOException {
		long id = 19l;
		ExecutedTest et = new ExecutedTest(null,State.SUCCESS, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		sut.initialiseExecute(0l);
		sut.next("end", "success", et.getComment(), id);
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest(),sut2.getExecutedTest(0).getTest());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	@Test
	@DisplayName("Failed Test hasn't been saved in new Report")
	public void failedReportTest() throws IOException {
		long id = 19l;
		ExecutedTest et = new ExecutedTest(null,State.FAILED, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		sut.initialiseExecute(0l);
		sut.next("end", "fail", et.getComment(), id);
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest(),sut2.getExecutedTest(0).getTest());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	@Test
	@DisplayName("Skiped Test hasn't been saved in new Report")
	public void skipedReportTest() throws IOException {
		long id = 19l;
		ExecutedTest et = new ExecutedTest(null,State.SKIPED, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		sut.initialiseExecute(0l);
		sut.next("end", "skiped", et.getComment(), id);
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest(),sut2.getExecutedTest(0).getTest());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	
}
