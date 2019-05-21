package com.jatis.test.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.jatis.test.zk.dto.RegistrationDTO;
import com.jatis.test.zk.dto.ResponseDTO;

@Component("regService")
public class RegistrationService implements InitializingBean{
	
	private final Logger logger = LoggerFactory.getLogger(RegistrationService.class); 

	@Secured("ROLE_ADMIN")
	public ResponseDTO register(RegistrationDTO dto) {
		ResponseDTO response = new ResponseDTO();
		response.setMessage(dto.toString());
		response.setSuccess(true);
		
		logger.info("Register: "+dto);
		return response;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("-->Bean initialized");
	}
}
