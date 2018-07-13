package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BodyTag extends BaseTag{
	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			JspWriter out = this.pageContext.getOut();
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			this.pageContext.setAttribute("webctx", getCtx());
			fmutil.process("body.ftl",map,out);
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doEndTag() throws JspException {
		super.doEndTag();
		try {
			JspWriter out = this.pageContext.getOut();
			out.println("</div></body><br></html>");
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}


}
