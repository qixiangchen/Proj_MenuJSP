package com.gufang.tld;

public class ColumnInfo {
	private String id = null;
	private String sortable = "false";
	private String width = "20";
	private String name = null;
	private String hidden = "false";
	private String formatter = null;
	private String type = "text";
	public ColumnInfo(String id,String name,String width,String sortable,
			String hidden,String formatter,String type)
	{
		this.id = id;
		this.name = name;
		this.width = width;
		this.sortable = sortable;
		this.hidden = hidden;
		this.formatter = formatter;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSortable() {
		if(Util.isNull(sortable))
			return "false";
		return sortable;
	}
	public void setSortable(String sortable) {
		this.sortable = sortable;
	}
	public String getHidden() {
		if(Util.isNull(hidden))
			return "false";
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getWidth() {
		if(Util.isNull(width))
			return "100";
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getName() {
		if(Util.isNull(name))
			return "NoName";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
