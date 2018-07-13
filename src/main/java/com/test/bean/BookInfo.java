package com.test.bean;

import java.io.Serializable;

public class BookInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String name = null;
	private Integer typeId = null;
	private String typeName = null;
	private String author = null;
	private Integer count = null;
	private String isSelected = null;
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getIsSelected() {
		if(isSelected == null)
			return "1";
		else
			return "2";
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

}
