package fr.uha.ensisa.gl.turbocheese.mantest.dao;

import java.util.Collection;
import java.util.Date;

import fr.uha.ensisa.gl.turbocheese.mantest.Report;

public interface ReportDao {
	public void addReport(Report r);
	public void remove(Report r);
	public Report getReport(long id);
	public Collection<Report> findAll();
	public long count();
}
