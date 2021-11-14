package com.umer.sitemonitoring.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloController {

	public String showHello() {
		return "Hello from the Managed bean";
	}
}
