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
		ExecutedTest et = new ExecutedTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"),State.SUCCESS, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		daoFactory.getTestListDao().find(0l).addTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"));
		sut.execute(0l);
		sut.next("success", et.getComment());
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest().getName(),sut2.getExecutedTest(0).getTest().getName());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	@Test
	@DisplayName("Failed Test hasn't been saved in new Report")
	public void failedReportTest() throws IOException {
		ExecutedTest et = new ExecutedTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"),State.FAILED, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		daoFactory.getTestListDao().find(0l).addTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"));
		sut.execute(0l);
		sut.next("fail", et.getComment());
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest().getName(),sut2.getExecutedTest(0).getTest().getName());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	@Test
	@DisplayName("Skiped Test hasn't been saved in new Report")
	public void skipedReportTest() throws IOException {
		ExecutedTest et = new ExecutedTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"),State.SKIPED, "Ceci est un super commentaire");
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		daoFactory.getTestListDao().find(0l).addTest(new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment"));
		sut.execute(0l);
		sut.next("skiped", et.getComment());
		assertEquals(1l,daoFactory.getReportDao().count());
		assertFalse(daoFactory.getReportDao().findAll().isEmpty());
		Iterator<Report> i = daoFactory.getReportDao().findAll().iterator();
		Report sut2 = i.next();
		assertEquals(et.getTest().getName(),sut2.getExecutedTest(0).getTest().getName());
		assertEquals(et.getState(),sut2.getExecutedTest(0).getState());
		assertEquals(et.getComment(),sut2.getExecutedTest(0).getComment());
	}
	
	@Test
	@DisplayName("New Test is stored")
	public void TestAddTest() throws IOException {
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		sut.addtest("testName", "testDescription", 0l);
		assertEquals(daoFactory.getTestListDao().find(0l).find(0l).getName(), "testName");
		assertEquals(daoFactory.getTestListDao().find(0l).find(0l).getDescription(), "testDescription");
	}
	
	@Test
	@DisplayName("New TestList is stored in daoTestList")
	public void TestAddTestList() throws IOException {
		sut.creatList("testList");
		assertEquals(daoFactory.getTestListDao().find(0l).getName(), "testList");
	}
	
	@Test
	@DisplayName("Test is correctly deleted")
	public void deleteTest() throws IOException {
		daoFactory.getTestListDao().persist(new TestList("", 0l));
		fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test(0l,"testname","testcomment");
		daoFactory.getTestListDao().find(0l).addTest(t);
		assertEquals(daoFactory.getTestListDao().find(0l).size(),1l);
		sut.delete(0l, 0l);
		assertEquals(daoFactory.getTestListDao().find(0l).size(),0l);
	}
	
	
	
	
}
