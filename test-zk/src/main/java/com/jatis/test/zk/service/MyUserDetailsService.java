package com.jatis.test.zk.service;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = new User(username, encoder.encode("1234"), 
				Arrays.asList(new GrantedAuthority() {
					private static final long serialVersionUID = 1L;

					@Override
					public String getAuthority() {
						return "admin".equalsIgnoreCase(username) ? "ROLE_ADMIN" : "ROLE_USER";
					}
					
				}));
		return user;
	}

}
