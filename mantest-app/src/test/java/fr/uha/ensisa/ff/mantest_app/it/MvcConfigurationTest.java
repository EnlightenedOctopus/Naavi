package fr.uha.ensisa.ff.mantest_app.it;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import fr.uha.ensisa.ff.mantest_app.config.MvcConfiguration;
import fr.uha.ensisa.gl.turbocheese.mantest.dao.mem.DaoFactoryMem;

class MvcConfigurationTest {
	private MvcConfiguration mvc;

	@BeforeEach
	void create() {
		mvc = new MvcConfiguration();
	}
	
	@Test
	void templateResolver() {
		 assertEquals(".html", mvc.templateResolver().getSuffix());
		 assertEquals(TemplateMode.HTML.toString(), mvc.templateResolver().getTemplateMode().toString());
		 assertEquals("/WEB-INF/views/", mvc.templateResolver().getPrefix());
	}
	
	/*@Test
	void springTemplateEngine() {
		 assertEquals(true, mvc.springTemplateEngine().getEnableSpringELCompiler());
	}*/
	
	@Test
	void viewResolver() {
		ThymeleafViewResolver tlvr = (ThymeleafViewResolver)mvc.viewResolver();
		SpringTemplateEngine ste = (SpringTemplateEngine)tlvr.getTemplateEngine();
		assertEquals(true, ste.getEnableSpringELCompiler());
	}
	
	@Test
	void getDaoFactory() {
		assertEquals(mvc.getDaoFactory().getClass().toString(),new DaoFactoryMem().getClass().toString());
	}
	
	@Test
	void multipartResolver() {
		assertEquals(mvc.multipartResolver().getClass().toString(),new CommonsMultipartResolver().getClass().toString());
	}

}
