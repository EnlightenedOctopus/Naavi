package fr.uha.ensisa.gl.turbocheese.mantest.dao;
import java.util.Collection;
import fr.uha.ensisa.gl.turbocheese.mantest.TestList;

public interface TestListDao {
	public void persist(TestList testList);
	public void remove(TestList testList);
	public TestList find(long id);
	public Collection<TestList> findAll();
	public long count();
}
