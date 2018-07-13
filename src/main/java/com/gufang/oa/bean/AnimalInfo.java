package com.gufang.oa.bean;

import java.io.Serializable;

public class AnimalInfo implements Serializable{
	private Integer aid = null;
	private String name = null;
	private Integer weight = null;
	
	private String regionName = null;//Name以逗号分隔
	private String regionId = null;//Id以逗号分隔
	private Integer sex = 1;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	
}
