package com.umer.sitemonitoring.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.umer.sitemonitoring.service.HelloSpringService;

//@ManagedBean
@Scope("request")
@Component
public class HelloController {

	// Using expression language
	// Here, we are using Spring Bean name. By default, it is the same as the class
	// name.
//	@ManagedProperty("#{helloSpringService}")
	@Autowired
	private HelloSpringService helloSpringService;

	public String showHello() {
		// return "Hello from the Managed bean";
		return helloSpringService.sayHello();
	}

	/**
	 * This is needed so that JSF runtime can inject the instance in the variable.
	 * Otherwise, it will throw an exception on running the project.
	 * 
	 * @param helloSpringService
	 */
	public void setHelloSpringService(HelloSpringService helloSpringService) {
		this.helloSpringService = helloSpringService;
	}
}
