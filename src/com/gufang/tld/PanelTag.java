package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PanelTag extends BaseTag{
	private String id = "pp";
	private String url = "login.do";
	private String title = "用户名";
	private String width = "400";
	private String height = "280";
	private String region = "center";
	List<FieldInfo> flds = new ArrayList<FieldInfo>();
	List<ButtonInfo> btns = new ArrayList<ButtonInfo>();

	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			JspWriter out = this.pageContext.getOut();
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			map.put("url",getUrl());
			map.put("title",getTitle());
			map.put("id",getId());
			map.put("width",getWidth());
			map.put("height",getHeight());
			map.put("flds",getFlds());
			map.put("btns",getBtns());
			map.put("region",getRegion());

			fmutil.process("panel.ftl",map,out);
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public List<FieldInfo> getFlds() {
		if(flds == null)
		{
			flds = new ArrayList<FieldInfo>();
		}
		return flds;
	}

	public void setFlds(List<FieldInfo> flds) {
		this.flds = flds;
	}

	public List<ButtonInfo> getBtns() {
		if(btns == null)
		{
			btns = new ArrayList<ButtonInfo>();
		}
		return btns;
	}

	public void setBtns(List<ButtonInfo> btns) {
		this.btns = btns;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

}
