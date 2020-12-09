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

}
