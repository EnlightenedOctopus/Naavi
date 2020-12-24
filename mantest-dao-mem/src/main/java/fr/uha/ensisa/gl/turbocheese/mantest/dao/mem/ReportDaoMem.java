package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import fr.uha.ensisa.gl.turbocheese.mantest.Report;
import fr.uha.ensisa.gl.turbocheese.mantest.Test;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.ReportDao;

public class ReportDaoMem implements ReportDao {

	private final Map<Date, Report> store = Collections.synchronizedMap(new TreeMap<Date, Report>());
	
	@Override
	public void addReport(Report r) {
		// TODO Auto-generated method stub
		store.put(r.getDate(), r);
	}

	@Override
	public void remove(Report r) {
		store.remove(r.getDate());
	}

	@Override
	public Report getReport(Date d) {
		// TODO Auto-generated method stub
		return store.get(d);
	}

	@Override
	public Collection<Report> findAll() {
		// TODO Auto-generated method stub
		return store.values();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return store.size();
	}

}
