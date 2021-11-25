package com.umer.sitemonitoring.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.service.CheckService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class CheckListController implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
