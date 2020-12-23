package fr.uha.ensisa.gl.turbocheese.mantest.dao;

public interface DaoFactory {
	public TestDao getTestDao();
	public TestListDao getTestListDao();
}
