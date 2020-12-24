package fr.uha.ensisa.gl.turbocheese.mantest.dao.mem;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import fr.uha.ensisa.gl.turbocheese.mantest.TestList;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.TestListDao;

public class TestListDaoMem implements TestListDao {
	
	private final Map<Long, TestList> store = Collections.synchronizedMap(new TreeMap<Long, TestList>());

	public void persist(TestList testList) {
		// TODO Auto-generated method stub
		store.put(testList.getId(), testList);
	}

	public void remove(TestList testList) {
		// TODO Auto-generated method stub
		store.remove(testList.getId());
	}

	public TestList find(long id) {
		// TODO Auto-generated method stub
		return store.get(id);
	}

	public Collection<TestList> findAll() {
		// TODO Auto-generated method stub
		return store.values();
	}

	public long count() {
		// TODO Auto-generated method stub
		return store.size();
	}

}