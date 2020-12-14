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
import fr.uha.ensisa.gl.turbocheese.mantest.dao.DaoFactory;

@Controller
public class TestController {
	
	@Autowired
	public DaoFactory daoFactory;
	
	private Report report;
	
	@RequestMapping(value="/")
	public String hello() throws IOException{
		return "redirect:/hello";
	}

	@RequestMapping(value="/list")
	public ModelAndView list() throws IOException{
		ModelAndView ret = new ModelAndView("list");
		ret.addObject("tests", daoFactory.getTestDao().findAll());
		return ret;
	}
	
	@RequestMapping(value="/formadd")
	public ModelAndView formAdd() throws IOException{
		ModelAndView ret = new ModelAndView("formadd");
		return ret;
	}
	
	@RequestMapping(value="/create")
	public String create(@RequestParam(required=true) String testName, @RequestParam(required=true) String testDescription) throws IOException{
		Test newTest = new Test();
		long id = daoFactory.getTestDao().count()+1;
		while(daoFactory.getTestDao().find(id) != null) id++;
		newTest.setId(id);
		newTest.setName(testName);
		newTest.setDescription(testDescription);
		daoFactory.getTestDao().persist(newTest);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(required=true) long id) throws IOException{
		Test deleteTest = daoFactory.getTestDao().find(id);
		daoFactory.getTestDao().remove(deleteTest);
		return "redirect:/list";
	}
	@RequestMapping(value="/execute")
	public ModelAndView execute(@RequestParam(required=true) String id) throws IOException {
		if (id=="start") {
			//DO SOMETHING WITH REPORT
		}
		Iterator<Test> i = daoFactory.getTestDao().findAll().iterator();
		ModelAndView ret = new ModelAndView("execute");
		ret.addObject("testtoexecute",i.next());
		return ret;
	}
	@RequestMapping(value="/next")
	public String next(@RequestParam(required=true) String state, @RequestParam(required=true) String comment, @RequestParam(required=true) long id) throws IOException {
		ExecutedTest.State s;
		switch (state) {
		case "success" : s=State.SUCCESS; break;
		case "fail" : s=State.FAILED; break;
		default : s=State.SKIPED;
		}
		//ExecutedTest et = new ExecutedTest(daoFactory.getTestDao().find(id), s , comment);
		//report.addExecutedTest(et);
		return "redirect:/list";
	}
	
}
