package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.ReportDao;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;

public class DaoFactoryMem implements DaoFactory {
	
	public final TestDao testDao = new TestDaoMem();
	public final ReportDao reportDao = new ReportDaoMem();
	
	public TestDao getTestDao() {
		// TODO Auto-generated method stub
		return this.testDao;
	}
	
	public ReportDao getReportDao() {
		return this.reportDao;
	}

}
