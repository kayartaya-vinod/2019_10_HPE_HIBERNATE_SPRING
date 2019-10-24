package com.hpe.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.hpe.cfg.AppConfig;

public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// sprint container
		AnnotationConfigWebApplicationContext ctx;
		ctx = new AnnotationConfigWebApplicationContext();
		
		// load beans via AppConfig
		ctx.register(AppConfig.class);
		
		// register DispatcherServlet with TomCat (or any web container)
		Dynamic ds = servletContext.addServlet("s1", new DispatcherServlet(ctx));
		ds.setLoadOnStartup(1);
		
		// instruct Tomcat to pass all requests to DispatcherServlet (front controller)u
		ds.addMapping("/");
		
		
	}

}
