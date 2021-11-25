package com.umer.sitemonitoring.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.service.CheckService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
public class CheckListController {
	
	@ManagedProperty("#{checkService}")
	private CheckService checkService;
	
	private List<Check> checks;
	
	private Check check=new Check();
	
	@PostConstruct
	public void loadChecks() {
		checks=checkService.findAll();
	}
	
	public void save() {
		checkService.save(check);
	}

}
