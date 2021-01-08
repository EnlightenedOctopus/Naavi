package fr.uha.ensisa.ff.mantest_app.controller;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest;
import fr.uha.ensisa.gl.turbocheese.mantest.ExecutedTest.State;
import fr.uha.ensisa.gl.turbocheese.mantest.Report;
import fr.uha.ensisa.gl.turbocheese.mantest.Test;
import fr.uha.ensisa.gl.turbocheese.mantest.TestList;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;

@Controller
public class TestController {
	
	@Autowired
	public DaoFactory daoFactory;
	
	private Report report;
	private Iterator<Test> iterator;
	private Test currentTest;
	private boolean inexec;
	
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
		TestList testlist = new TestList("List 1",0l);
		testlist.addTest(new Test((long)1, "List 1 Test 1", ""));
		testlist.addTest(new Test((long)2, "List 1 Test 2", ""));
		testlist.addTest(new Test((long)3, "List 1 Test 3", ""));
		daoFactory.getTestListDao().persist(testlist);
		TestList testlist2 = new TestList("List 2",1l);
		testlist2.addTest(new Test((long)1, "List 2 Test 1", ""));
		testlist2.addTest(new Test((long)2, "List 2 Test 2", ""));
		testlist2.addTest(new Test((long)3, "List 2 Test 3", ""));
		testlist2.addTest(new Test((long)4, "List 2 Test 3", ""));
		daoFactory.getTestListDao().persist(testlist2);
		TestList testlist3 = new TestList("List 3",2l);
		testlist3.addTest(new Test((long)1, "List 3 Test 1", ""));
		testlist3.addTest(new Test((long)2, "List 3 Test 2", ""));
		daoFactory.getTestListDao().persist(testlist3);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/formadd")
	public ModelAndView formAdd() throws IOException{
		ModelAndView ret = new ModelAndView("formadd");
		return ret;
	}
	
	
	@RequestMapping(value="/createlist")
	public String creatList(@RequestParam(required=true) String listName) {
		long id = daoFactory.getTestListDao().count();
		while(daoFactory.getTestListDao().find(id) != null) id++;
		System.out.println(id);
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
		long id = daoFactory.getTestListDao().find(listId).size();
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
				daoFactory.getTestListDao().persist(new TestList("Default",(long)0));
			}
			daoFactory.getTestListDao().find((long)0).addTest(newTest);
		}
		
		return "redirect:/list";
	}
	@RequestMapping(value="/execute")
	public ModelAndView execute(@RequestParam(required=false) Long id) throws IOException {
		if(!inexec &&( id == null || daoFactory.getTestListDao().find(id)==null)) {
			return new ModelAndView("redirect:/list");		
		}
		if (id!=null) {
			initialiseExecute(id);
		}
		ModelAndView ret = new ModelAndView("execute");
		ret.addObject("testtoexecute",currentTest);
		ret.addObject("hasnext", iterator.hasNext());
		return ret;
	}
	
	public void initialiseExecute(long idlist) {
		report = new Report();
		inexec = true;
		long id = daoFactory.getReportDao().count();
		while(daoFactory.getReportDao().getReport(id) != null) id++;
		report.setId(id);
		iterator = daoFactory.getTestListDao().find(idlist).getTests().iterator();
		currentTest = iterator.next();
	}
	
	@RequestMapping(value="/next")
	public String next(@RequestParam(required=false) String state, @RequestParam(required=true) String comment) throws IOException {
		ExecutedTest.State s;
		switch (state) {
			case "success" : s=State.SUCCESS; break;
			case "fail" : s=State.FAILED; break;
			default : s=State.SKIPED;
		}
		ExecutedTest et = new ExecutedTest(currentTest, s , comment);
		report.addExecutedTest(et);
		if (!(iterator.hasNext())) {
			daoFactory.getReportDao().addReport(report);
			inexec=false;
			return "redirect:/list";
		}
		currentTest = iterator.next();
		return "redirect:/execute";
	}

	@RequestMapping(value="/reportlist")
	public ModelAndView reportlist() throws IOException{
		ModelAndView ret = new ModelAndView("reportlist");
		ret.addObject("reports", daoFactory.getReportDao().findAll());
		return ret;
	}
	
	@RequestMapping(value="/report")
	public ModelAndView report(@RequestParam(required=true) long id) throws IOException{
		ModelAndView ret = new ModelAndView("report");
		ret.addObject("report",  daoFactory.getReportDao().getReport(id));
		return ret;
	}
	
	@RequestMapping(value="/deletereport")
	public String deletereport(@RequestParam(required=true) long id) throws IOException{
		daoFactory.getReportDao().remove(daoFactory.getReportDao().getReport(id));
		return "redirect:/reportlist";
	}
}
