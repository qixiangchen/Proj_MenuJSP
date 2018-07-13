package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class WindowTag extends BaseTag{
	private String id = "win";
	private String url = "login.do";
	private String title = "用户名";
	private String fromId = "";
	private String width = "400";
	private String height = "280";
	private String datagrid = "";
	List<FieldInfo> flds = new ArrayList<FieldInfo>();
	List<ButtonInfo> btns = new ArrayList<ButtonInfo>();
	
	public String helpMsg()
	{
		return "<c1510b:login url=\"login.do\" nameLabel=\"登录名\" 	nameId=\"loginId\" pwdLabel=\"密码\" pwdId=\"pwd\" loginLabel=\"登录\"/>"; 
	}
	
	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			JspWriter out = this.pageContext.getOut();
			if(url == null) {
				out.println(helpMsg());
				return SKIP_BODY;
			}
			
			
			WindowInfo win = new WindowInfo();
			win.setId(getId());
			win.setTitle(getTitle());
			this.pageContext.setAttribute("currentCtrl", win);
			
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			if(url == null) {
				out.println(helpMsg());
				return SKIP_BODY;
			}
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			map.put("id",getId());
			map.put("url",getUrl());
			map.put("title",getTitle());
			map.put("fromId",getFromId());
			map.put("width",getWidth());
			map.put("height",getHeight());
			map.put("datagrid",getDatagrid());
			WindowInfo win = (WindowInfo)this.pageContext.getAttribute("currentCtrl");
			map.put("flds",win.getFlds());
			map.put("btns",win.getBtns());
			
			fmutil.process("window.ftl",map,out);
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromId() {
		return id+"Form";
		//return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
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

	public String getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
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

}
