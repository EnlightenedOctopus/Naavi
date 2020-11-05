package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import fr.uha.ensisa.gl.turbocheese.mantest.Test;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestDao;

public class TestDaoMem implements TestDao {
	
	private final Map<Long, Test> store = Collections.synchronizedMap(new TreeMap<Long, Test>());

	public void persist(Test test) {
		// TODO Auto-generated method stub
		store.put(test.getId(), test);
	}

	public void remove(Test test) {
		// TODO Auto-generated method stub
		store.remove(test.getId());
	}

	public Test find(long id) {
		// TODO Auto-generated method stub
		return store.get(id);
	}

	public Collection<Test> findAll() {
		// TODO Auto-generated method stub
		return store.values();
	}

	public long count() {
		// TODO Auto-generated method stub
		return store.size();
	}

}
