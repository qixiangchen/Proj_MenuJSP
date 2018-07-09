package com.gufang.tld;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LoginTag extends BaseTag{
	private String url = "login.do";
	private String nameLabel = "用户名";
	private String nameId = "loginId";
	private String pwdLabel = "密码";
	private String pwdId = "pwd";
	private String loginLabel = "登录";
	
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
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			map.put("url",getUrl());
			map.put("nameLabel",getNameLabel());
			map.put("nameId",getNameId());
			map.put("pwdLabel",getPwdLabel());
			map.put("pwdId",getPwdId());
			map.put("loginLabel",getLoginLabel());
			fmutil.process("login.ftl",map,out);
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
	public String getNameLabel() {
		return nameLabel;
	}
	public void setNameLabel(String nameLabel) {
		this.nameLabel = nameLabel;
	}
	public String getNameId() {
		return nameId;
	}
	public void setNameId(String nameId) {
		this.nameId = nameId;
	}
	public String getPwdLabel() {
		return pwdLabel;
	}
	public void setPwdLabel(String pwdLabel) {
		this.pwdLabel = pwdLabel;
	}
	public String getPwdId() {
		return pwdId;
	}
	public void setPwdId(String pwdId) {
		this.pwdId = pwdId;
	}
	public String getLoginLabel() {
		return loginLabel;
	}
	public void setLoginLabel(String loginLabel) {
		this.loginLabel = loginLabel;
	}	
}
