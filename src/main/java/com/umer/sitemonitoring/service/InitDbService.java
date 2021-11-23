package com.umer.sitemonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umer.sitemonitoring.repository.CheckRepository;


@Service
public class InitDbService {
	
	@Autowired
	private CheckRepository checkRepository;

}
