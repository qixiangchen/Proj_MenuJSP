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

public class FieldTag extends BaseTag{
	private String type = "text";
	private String id = null;
	private String label = null;
	private String url = null;
	private String idField = "id";
	private String nameField = "name";
	private String width = "100";
	private String onclick = "";
	private String multiline = "false";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getMultiline() {
		return multiline;
	}

	public void setMultiline(String multiline) {
		this.multiline = multiline;
	}

	@Override
	public int doStartTag() throws JspException {
		try
		{
			FieldInfo fi = new FieldInfo(type,id,label,url);
			fi.setIdField(idField);
			fi.setNameField(nameField);
			fi.setWidth(width);
			fi.setOnclick(onclick);
			fi.setMultiline(multiline);
			this.pageContext.setAttribute("field", fi);
			
			Object obj = pageContext.getAttribute("currentCtrl");
			if(obj instanceof WindowInfo)
			{
				WindowInfo win = (WindowInfo)obj;
				win.addFlds(fi);
			}
			if(obj instanceof GridInfo)
			{
				GridInfo grid = (GridInfo)obj;
				ToolbarInfo tbi = grid.getTb();
				LineInfo li = tbi.getCurrentLine();
				if(li != null)
				{
					li.addFlds(fi);
				}
			}
			
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

}
