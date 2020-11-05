package fr.uha.ensisa.gl.turbocheese.mantest.dao;
import java.util.Collection;
import fr.uha.ensisa.gl.turbocheese.mantest.Test;

public interface TestDao {
	public void persist(Test test);
	public void remove(Test test);
	public Test find(long id);
	public Collection<Test> findAll();
	public long count();
}
