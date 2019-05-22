package com.jatis.test.zk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="mst_member")
public class MemberEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private Boolean genderMale;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getGenderMale() {
		return genderMale;
	}
	public void setGenderMale(Boolean genderMale) {
		this.genderMale = genderMale;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
