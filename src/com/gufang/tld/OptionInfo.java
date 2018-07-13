package com.gufang.tld;

public class OptionInfo {
	private String id = null;
	private String name = null;
	public OptionInfo(String id,String name)
	{
		this.id = id;
		this.name =name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
