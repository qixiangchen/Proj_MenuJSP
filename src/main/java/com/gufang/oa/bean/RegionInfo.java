package com.gufang.oa.bean;

import java.io.Serializable;

public class RegionInfo implements Serializable{
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
