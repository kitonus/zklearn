package com.jatis.test.zk.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="mst_car")
public class CarEntity {
	
	@GeneratedValue
	@Id
    private UUID id;
	
	@NotEmpty(message="Model cannot be null")
    private String model;
	@NotEmpty(message="Make cannot be null")
    private String make;
    private String preview;
    private String description;
    private Integer price;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public CarEntity(UUID id, String model, String make, String preview, String description, Integer price) {
		super();
		this.id = id;
		this.model = model;
		this.make = make;
		this.preview = preview;
		this.description = description;
		this.price = price;
	}
    
    public CarEntity() {}
}
