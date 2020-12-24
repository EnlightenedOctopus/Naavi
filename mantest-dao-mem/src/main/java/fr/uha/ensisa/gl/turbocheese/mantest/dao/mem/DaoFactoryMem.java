package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.ReportDao;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestListDao;

public class DaoFactoryMem implements DaoFactory {
	
	public final TestDao testDao = new TestDaoMem();
	public final ReportDao reportDao = new ReportDaoMem();
	public final TestListDao testListDao = new TestListDaoMem();
	
	public TestDao getTestDao() {
		return this.testDao;
	}
	
	public ReportDao getReportDao() {
		return this.reportDao;
	}
	
	public TestListDao getTestListDao() {
		return this.testListDao;
	}
	
}