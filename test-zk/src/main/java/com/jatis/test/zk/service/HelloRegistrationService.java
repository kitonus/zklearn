package com.jatis.test.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jatis.test.zk.dto.RegistrationDTO;

@Component("helloRegistrationService")
public class HelloRegistrationService {
	private final Logger log = LoggerFactory.getLogger(HelloRegistrationService.class);


	public void save(RegistrationDTO registration) {
		log.info("Save name="+registration.getName()+", birth date="+registration.getDateOfBirth());
	}
}
