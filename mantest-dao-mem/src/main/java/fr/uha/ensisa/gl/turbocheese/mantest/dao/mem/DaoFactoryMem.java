package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;

public class DaoFactoryMem implements DaoFactory {
	
	public final TestDao testDao = new TestDaoMem();
	public TestDao getTestDao() {
		// TODO Auto-generated method stub
		return this.testDao;
	}

}
