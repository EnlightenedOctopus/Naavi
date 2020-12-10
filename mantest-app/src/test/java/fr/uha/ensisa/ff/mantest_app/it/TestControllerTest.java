package fr.uha.ensisa.ff.mantest_app.it;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.ff.mantest_app.controller.TestController;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;

class TestControllerTest {
	
	@Mock public DaoFactory daoFactory;
	@Mock public TestDao daoTask;
	public TestController sut;
	
	@BeforeEach
	public void prepareDao(){
		MockitoAnnotations.openMocks(this); // crée les @Mock
		Mockito.when(daoFactory.getTestDao()).thenReturn(this.daoTask);
		sut = new TestController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}
	
	@Test
	public void emptyList() throws IOException{
		ModelAndView ret = sut.list();
		Collection<fr.uha.ensisa.gl.turbocheese.mantest.Test> tests = 
				(Collection<fr.uha.ensisa.gl.turbocheese.mantest.Test>)ret.getModelMap().get("tests");
		assertNotNull(tests);
		assertTrue(tests.isEmpty());
	}
	
	@Test
	public void createTest() throws IOException{
		sut.create("", "");
		//Il faut qu'un persist ait été appelé sur daoTask
		Mockito.verify(daoTask).persist(Mockito.any(fr.uha.ensisa.gl.turbocheese.mantest.Test.class));
	}
	
	/*@Test
	public void delete() throws IOException {
		long removedId = 5l;
		sut.delete(removedId);
		ArgumentCaptor<fr.uha.ensisa.gl.turbocheese.mantest.Test> removed =
				ArgumentCaptor.forClass(fr.uha.ensisa.gl.turbocheese.mantest.Test.class);
		Mockito.verify(daoTask).remove(removed.capture());
		assertEquals(Long.valueOf(removedId), removed.getValue().getId());
	}*/

}
