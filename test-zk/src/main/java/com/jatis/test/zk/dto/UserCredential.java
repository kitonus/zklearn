package com.jatis.test.zk.dto;

import java.io.Serializable;

public class UserCredential implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
