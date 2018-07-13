package com.test.bean;

import java.io.Serializable;

public class TypeInfo implements Serializable{
	private Integer id = null;
	private String name = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
