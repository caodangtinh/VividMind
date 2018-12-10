package com.test.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tour {
	@Id
	private int id;
	private String name;
	private String longDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
}
