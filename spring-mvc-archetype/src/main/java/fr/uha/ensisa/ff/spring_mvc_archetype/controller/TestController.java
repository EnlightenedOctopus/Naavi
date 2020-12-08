package fr.uha.ensisa.ff.spring_mvc_archetype.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.turbocheese.mantest.Test;
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
		return ret;
	}
	
	@RequestMapping(value="/create")
	public String create(@RequestParam(required=true) String testName) throws IOException{
		Test newTest = new Test();
		long id = daoFactory.getTestDao().count()+1;
		while(daoFactory.getTestDao().find(id) != null) id++;
		newTest.setId(id);
		newTest.setName(testName);
		daoFactory.getTestDao().persist(newTest);
		return "redirect:/list";
	}

}
