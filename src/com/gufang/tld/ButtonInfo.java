package com.gufang.tld;

public class ButtonInfo {
	private String click = null;
	private String name = null;
	
	public ButtonInfo(String click,String name)
	{
		this.click = click;
		this.name = name;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
