package com.umer.sitemonitoring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

// TODO: Please change the class name to something more professional.
public class MyWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		
		// Create the 'root' spring application context
		// This is done via registering SpringCOnfiguration class which
		// contains the annotations: @Configuration and @ComponentScan
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);

		// Manage the life cycle of the root application context
		// This is achieved via ContextLoaderListener
		container.addListener(new ContextLoaderListener(rootContext));
	}

}
