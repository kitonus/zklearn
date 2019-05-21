package com.jatis.test.zk.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.test.zk.dto.RegistrationDTO;
import com.jatis.test.zk.dto.ResponseDTO;
import com.jatis.test.zk.entity.MemberEntity;
import com.jatis.test.zk.repository.MemberRepository;

@Component("regService")
public class RegistrationService implements InitializingBean{
	
	private final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private MemberRepository memberRepository;

	@Secured("ROLE_ADMIN")
	@Transactional
	public ResponseDTO register(RegistrationDTO dto) {
		ResponseDTO response = new ResponseDTO();
		response.setMessage(dto.toString());
		
		logger.info("Register: "+dto);
		
		MemberEntity member = new MemberEntity();
		member.setName(dto.getName());
		member.setGenderMale(dto.isMale());
		member.setLastUpdate(new Date());
		member.setDateOfBirth(dto.getDateOfBirth());
		memberRepository.save(member);
		response.setSuccess(true);
		return response;
	}
	
	@Transactional(readOnly=true)
	public RegistrationDTO findOne(String name) {
		MemberEntity member = memberRepository.findById(name).orElse(null);
		if (member == null) {
			return null;
		}
		RegistrationDTO dto = new RegistrationDTO();
		dto.setDateOfBirth(member.getDateOfBirth());
		dto.setMale(Boolean.TRUE.equals(member.getGenderMale()));
		dto.setName(member.getName());
		return dto;
	}
	
	public List<MemberEntity> findAll(){
		return this.memberRepository.findAll();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("-->Bean initialized");
	}
}
