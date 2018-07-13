package com.gufang.bean;

import java.sql.Date;

public class Student implements java.io.Serializable{
	private String id;
	private int no;
	private String name = null;
	private Date dt = null;
	private String hobby = null;
	private String chkid = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getChkid() {
		return chkid;
	}
	public void setChkid(String chkid) {
		this.chkid = chkid;
	}
}
