package com.gufang.tld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class ColumnTag extends BaseTag{
	private String id = null;
	private String sortable = "false";
	private String width = "20";
	private String name = null;
	private String hidden = "false";
	private String formatter = null;
	private String type = "text";
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
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

	@Override
	public int doStartTag() throws JspException {
		try
		{
			GridInfo grid = (GridInfo)pageContext.getAttribute("currentCtrl");
			ColumnInfo col = new ColumnInfo(id,name,width,sortable,
					hidden,formatter,type);
			grid.addCols(col);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
