package com.umer.sitemonitoring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * It bootstraps Spring in our web application
 * 
 * @author umer
 *
 */
public class SiteMonitoringWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = createAndRegisterRootContext();
		addRootContextAsContextLoaderListerner(container, rootContext);
	}

	/**
	 * Manage the life cycle of the root application context. This is achieved via
	 * ContextLoaderListener
	 * 
	 * @param container
	 * @param rootContext
	 */
	private void addRootContextAsContextLoaderListerner(ServletContext container,
			AnnotationConfigWebApplicationContext rootContext) {
		container.addListener(new ContextLoaderListener(rootContext));
	}

	/**
	 * Create the 'root' spring application context. This is done via registering
	 * SpringConfiguration class which contains the annotations: @Configuration
	 * and @ComponentScan
	 * 
	 * @return
	 */
	private AnnotationConfigWebApplicationContext createAndRegisterRootContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);
		return rootContext;
	}

}
