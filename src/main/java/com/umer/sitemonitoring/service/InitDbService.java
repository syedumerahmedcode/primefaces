package com.umer.sitemonitoring.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.repository.CheckRepository;


@Service
public class InitDbService {
	
	@Autowired
	private CheckRepository checkRepository;
	
	@PostConstruct
	public void init() {
		Check check=new Check();
		check.setName("example");
		check.setUrl("http:www.example.com");
		checkRepository.save(check);		
	}

}
