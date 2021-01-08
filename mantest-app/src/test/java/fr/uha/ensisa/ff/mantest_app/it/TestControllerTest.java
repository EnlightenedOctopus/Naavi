package fr.uha.ensisa.ff.mantest_app.it;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.ff.mantest_app.controller.TestController;
import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest;
import fr.uha.ensisa.gl.turbocheese.mantest.Report;
import fr.uha.ensisa.gl.turbocheese.mantest.TestList;
import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest.State;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.ReportDao;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestListDao;

class TestControllerTest {
	
	@Mock public DaoFactory daoFactory;
	@Mock public TestDao daoTask;
	@Mock public ReportDao daoReport;
	@Mock public TestListDao daoList;
	public TestController sut;
	
	@BeforeEach
	public void prepareDao(){
		MockitoAnnotations.openMocks(this); // crée les @Mock
		Mockito.when(daoFactory.getTestDao()).thenReturn(this.daoTask);
		Mockito.when(daoFactory.getReportDao()).thenReturn(this.daoReport);
		Mockito.when(daoFactory.getTestListDao()).thenReturn(this.daoList);
		sut = new TestController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}
	//Test
//	@Test
//	public void emptyList() throws IOException{
//		ModelAndView ret = sut.list();
//		Collection<fr.uha.ensisa.gl.turbocheese.mantest.Test> tests = 
//				(Collection<fr.uha.ensisa.gl.turbocheese.mantest.Test>)ret.getModelMap().get("tests");
//		assertNotNull(tests);
//		assertTrue(tests.isEmpty());
//	}
	
//	@Test
//	public void createTest() throws IOException{
//		sut.create("", "");
//		//Il faut qu'un persist ait été appelé sur daoTask
//		Mockito.verify(daoTask).persist(Mockito.any(fr.uha.ensisa.gl.turbocheese.mantest.Test.class));
//	}
	
	/*@Test
	public void delete() throws IOException {
		long removedId = 5l;
		sut.delete(removedId);
		ArgumentCaptor<fr.uha.ensisa.gl.turbocheese.mantest.Test> removed =
				ArgumentCaptor.forClass(fr.uha.ensisa.gl.turbocheese.mantest.Test.class);
		Mockito.verify(daoTask).remove(removed.capture());
		assertEquals(Long.valueOf(removedId), removed.getValue().getId());
	}*/


	@Test
	@DisplayName("Execute page doesn't add a test")
	public void addexecutedTest() throws IOException {
		long id = 19l;
		fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		t.setId(id);
		ExecutedTest et = new ExecutedTest(t,State.FAILED, "Ceci est un super commentaire");
		//sut.next("fail",et.getComment(),id);
		//pas fini
	}
	
	@Test
	@DisplayName("End button doesn't create a Report")
	public void createReport() throws IOException {
		long id = 19l;
		fr.uha.ensisa.gl.turbocheese.mantest.Test t = new fr.uha.ensisa.gl.turbocheese.mantest.Test();
		t.setId(id);
		TestList tList = new TestList("TestList",0l);
		tList.addTest(t);
		ExecutedTest et = new ExecutedTest(t,State.FAILED, "Ceci est un super commentaire");
		Mockito.when(daoList.find(0l)).thenReturn(tList);
		sut.initialiseExecute(0l);
		sut.next("end",et.getComment());
		Mockito.verify(daoReport).addReport(Mockito.any(Report.class));
	}

	
	@Test
	public void hello() throws IOException {
		assertEquals("redirect:/list", sut.hello());
	}
	
	@Test
	public void createList() throws IOException {
		assertEquals("redirect:/list", sut.creatList("list"));
	}
	
	@Test
	public void deletelist() throws IOException {
		sut.creatList("list");
		assertEquals("redirect:/list", sut.deletelist(0l));
	}
	
	@Test
	public void addtest() throws IOException {
		sut.creatList("list");
		assertEquals(sut.addtest("Test1", "Description", 0l), "redirect:/list");
	}
	
	@Test
	public void setup() throws IOException {
		assertEquals("redirect:/list", sut.setup());
	}
	
	@Test
	public void list() throws IOException {
		assertEquals("list", sut.list().getViewName());
	}
	
	@Test
	public void formadd() throws IOException {
		assertEquals("formadd", sut.formAdd().getViewName());
	}
	
	@Test
	public void testRedirectDelete() throws IOException{
		assertEquals("redirect:/list", sut.delete(0,690));
	}
	/*@Test
	public void execute() throws IOException {
		sut.creatList("list");
		Mockito.when(daoList.find(0l)).thenReturn(new TestList("",0l));
		assertEquals("execute", sut.execute(0l).getViewName());
	}*/
	
}
