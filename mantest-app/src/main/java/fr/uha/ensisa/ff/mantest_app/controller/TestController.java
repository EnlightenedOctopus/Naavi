package fr.uha.ensisa.ff.mantest_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.turbocheese.mantest.Test;
import fr.uha.ensisa.gl.turbocheese.mantest.TestList;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;

@Controller
public class TestController {
	
	@Autowired
	public DaoFactory daoFactory;
	
	@RequestMapping(value="/")
	public String hello() throws IOException{
		return "redirect:/hello";
	}

	@RequestMapping(value="/list")
	public ModelAndView list() throws IOException{
		ModelAndView ret = new ModelAndView("list");
		ret.addObject("tests", daoFactory.getTestDao().findAll());
		ret.addObject("testlists", daoFactory.getTestListDao().findAll());
		return ret;
	}
	
	@RequestMapping(value="/formadd")
	public ModelAndView formAdd() throws IOException{
		ModelAndView ret = new ModelAndView("formadd");
		return ret;
	}
	
	@RequestMapping(value="/create")
	public String create(@RequestParam(required=true) String testName, @RequestParam(required=true) String testDescription, @RequestParam(required=true) Long listId) throws IOException{
		Test newTest = new Test();
		long id = daoFactory.getTestDao().count()+1;
		while(daoFactory.getTestDao().find(id) != null) id++;
		newTest.setId(id);
		newTest.setName(testName);
		newTest.setDescription(testDescription);
		daoFactory.getTestDao().persist(newTest);
		if(daoFactory.getTestListDao().find(listId) != null) {
			daoFactory.getTestListDao().find(listId).addTest(newTest);
		}
		else {
			if(daoFactory.getTestListDao().count()==(long)0) {
				daoFactory.getTestListDao().persist(new TestList("default",(long)0));
			}
			daoFactory.getTestListDao().find((long)0).addTest(newTest);
		}
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/createlist")
	public String creatList(@RequestParam(required=true) String listName) {
		long id = daoFactory.getTestListDao().count()+1;
		while(daoFactory.getTestListDao().find(id) != null) id++;
		System.out.println(new TestList(listName,id));
		daoFactory.getTestListDao().persist(new TestList(listName,id));
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(required=true) long id) throws IOException{
		Test deleteTest = daoFactory.getTestDao().find(id);
		daoFactory.getTestDao().remove(deleteTest);
		return "redirect:/list";
	}

}
