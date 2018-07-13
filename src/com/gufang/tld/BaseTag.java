package com.gufang.tld;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BaseTag extends TagSupport{
	private String ctx = null;

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}
	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		ctx = Util.getContext();
		if(ctx == null)
		{
			ctx = this.pageContext.getServletContext().getContextPath();
		}
		else
		{
			if("/".equals(ctx))
				ctx = "";
		}
		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
