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
		return "redirect:/list";
	}

	@RequestMapping(value="/list")
	public ModelAndView list() throws IOException{
		ModelAndView ret = new ModelAndView("list");
//		ret.addObject("tests", daoFactory.getTestDao().findAll());
		ret.addObject("testlists", daoFactory.getTestListDao().findAll());
		return ret;
	}
	
	@RequestMapping(value="/setup")
	public String setup() throws IOException{
		TestList testlist = new TestList("List 1",(long)1);
		testlist.addTest(new Test((long)1, "List 1 Test 1", ""));
		testlist.addTest(new Test((long)2, "List 1 Test 2", ""));
		testlist.addTest(new Test((long)3, "List 1 Test 3", ""));
		daoFactory.getTestListDao().persist(testlist);
		TestList testlist2 = new TestList("List 2",(long)2);
		testlist2.addTest(new Test((long)1, "List 2 Test 1", ""));
		testlist2.addTest(new Test((long)2, "List 2 Test 2", ""));
		testlist2.addTest(new Test((long)3, "List 2 Test 3", ""));
		testlist2.addTest(new Test((long)4, "List 2 Test 3", ""));
		daoFactory.getTestListDao().persist(testlist2);
		TestList testlist3 = new TestList("List 3",(long)3);
		testlist3.addTest(new Test((long)1, "List 3 Test 1", ""));
		testlist3.addTest(new Test((long)2, "List 3 Test 2", ""));
		daoFactory.getTestListDao().persist(testlist3);
		return "redirect:/list";
		//ALORS EN FAIT FAUT VIRER L'ID des CONSTRUCTEURS ET LE METTRE DANS DAOFACTORY
	}
	
	@RequestMapping(value="/formadd")
	public ModelAndView formAdd() throws IOException{
		ModelAndView ret = new ModelAndView("formadd");
		return ret;
	}
	
//	@RequestMapping(value="/create")
//	public String create(@RequestParam(required=true) String testName, @RequestParam(required=true) String testDescription, @RequestParam(required=true) Long listId) throws IOException{
//		Test newTest = new Test();
//		long id = daoFactory.getTestDao().count()+1;
//		while(daoFactory.getTestDao().find(id) != null) id++;
//		newTest.setId(id);
//		newTest.setName(testName);
//		newTest.setDescription(testDescription);
//		daoFactory.getTestDao().persist(newTest);
//		if(daoFactory.getTestListDao().find(listId) != null) {
//			daoFactory.getTestListDao().find(listId).addTest(newTest);
//		}
//		else {
//			if(daoFactory.getTestListDao().count()==(long)0) {
//				daoFactory.getTestListDao().persist(new TestList("default",(long)0));
//			}
//			daoFactory.getTestListDao().find((long)0).addTest(newTest);
//		}
//		
//		return "redirect:/list";
//	}
	
	@RequestMapping(value="/createlist")
	public String creatList(@RequestParam(required=true) String listName) {
		long id = daoFactory.getTestListDao().count()+1;
		while(daoFactory.getTestListDao().find(id) != null) id++;
		System.out.println(new TestList(listName,id));
		daoFactory.getTestListDao().persist(new TestList(listName,id));
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(required=true) long id, @RequestParam(required=true) long listId) throws IOException{
		if(daoFactory.getTestListDao().find(listId) == null) {
		}
		if(daoFactory.getTestListDao().find(listId).find(id) != null) {
			daoFactory.getTestListDao().find(listId).deleteTest(id);
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value="/deletelist")
	public String deletelist(@RequestParam(required=true) long id) throws IOException{
		daoFactory.getTestListDao().remove(daoFactory.getTestListDao().find(id));
		return "redirect:/list";
	}
	
	@RequestMapping(value="/addtest")
	public String addtest(@RequestParam(required=true) String testName, @RequestParam(required=true) String testDescription, @RequestParam(required=true) Long listId) throws IOException{
		if(daoFactory.getTestListDao().find(listId) == null) {
			return "redirect:/list";
		}
		Test newTest = new Test();
		long id = daoFactory.getTestListDao().find(listId).size()+1;
		while(daoFactory.getTestListDao().find(listId).find(id) != null) id++;
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

}
