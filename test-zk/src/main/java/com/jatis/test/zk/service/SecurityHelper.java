package com.jatis.test.zk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityHelper")
public class SecurityHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityHelper.class);

	public String getCurrentUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				return auth.getName();
			}
			return "";
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.warn("Failed getting username: ", e);
			} else {
				logger.warn("Failed getting username: "+e.getMessage());
			}
			return "";
		}
	}
}
