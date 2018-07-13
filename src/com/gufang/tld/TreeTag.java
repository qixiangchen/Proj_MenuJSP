package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TreeTag extends BaseTag{
	private String id = "dg";
	private String url = "tree.do";
	private String method = "get";
	private String width = "30%";
	private String region = "west";
	private String title = "";
	private String panelWidth = "200px";
	private String checkbox = "false";

	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			JspWriter out = this.pageContext.getOut();
			if(url == null) {
				out.println("Url is null");
				return SKIP_BODY;
			}
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			map.put("id",getId());
			map.put("url",getUrl());
			map.put("method",getMethod());
			map.put("width",getWidth());
			map.put("region",getRegion());
			map.put("title",getTitle());
			map.put("panelWidth",getPanelWidth());
			map.put("checkbox",getCheckbox());
			fmutil.process("tree.ftl",map,out);
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	@Override
	public void release() {
		super.release();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPanelWidth() {
		return panelWidth;
	}
	public void setPanelWidth(String panelWidth) {
		this.panelWidth = panelWidth;
	}
	public String getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

}
