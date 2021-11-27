package com.umer.sitemonitoring.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umer.sitemonitoring.entity.Check;
import com.umer.sitemonitoring.repository.CheckRepository;

@Service
public class InitDbService {
	
	private static final String INIT_DATABASE_FINISH = "*** INIT DATABASE FINISH ***";
	private static final String INIT_DATABASE_START = "*** INIT DATABASE START ***";
	
	private static final String EXAMPLE = "example";
	private static final String EXAMPLE_URL = "http://www.example.com";
	
	private static final String SITE_MONITORING = "sitemonitoring";
	private static final String SITE_MONITORING_URL = "http://www.sitemonitoring.com";
	
	private static final String SOME_OTHER_LINK = "someotherlink";
	private static final String SOME_OTHER_LINK_URL = "http://www.someotherlink.com";
	
	
	@Autowired
	private CheckRepository checkRepository;

	@PostConstruct
	public void init() {
		System.out.println(INIT_DATABASE_START);
		{
			Check check = new Check();
			check.setName(EXAMPLE);
			check.setUrl(EXAMPLE_URL);
			saveCheck(check);
		}
		{
			Check check = new Check();
			check.setName(SITE_MONITORING);
			check.setUrl(SITE_MONITORING_URL);
			saveCheck(check);
		}

		{
			Check check = new Check();
			check.setName(SOME_OTHER_LINK);
			check.setUrl(SOME_OTHER_LINK_URL);
			saveCheck(check);
		}
		System.out.println(INIT_DATABASE_FINISH);
	}

	private void saveCheck(Check check) {
		checkRepository.save(check);
	}

}
