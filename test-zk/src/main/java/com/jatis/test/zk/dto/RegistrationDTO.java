package com.jatis.test.zk.dto;

import java.util.Date;

public class RegistrationDTO {
	private String name;
	private boolean male;
	private Date dateOfBirth;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "RegistrationDTO [name=" + name + ", male=" + male + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
