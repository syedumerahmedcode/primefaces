package com.umer.sitemonitoring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.repository.CheckRepository;

@Service
public class CheckService {
	
	@Autowired
	private CheckRepository checkRepository;
	
	public List<Check> findAll(){
		return checkRepository.findAll();
	}

	public void save(Check check) {
		checkRepository.save(check);		
	}

}
