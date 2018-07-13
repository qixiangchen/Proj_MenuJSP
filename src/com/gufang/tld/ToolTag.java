package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ToolTag extends BaseTag{
	@Override
	public int doStartTag() throws JspException {
		try
		{
			Object obj = pageContext.getAttribute("currentCtrl");
			if(obj instanceof GridInfo)
			{
				GridInfo grid = (GridInfo)obj;
				ToolbarInfo tbi = new ToolbarInfo(grid.getId()+"toolbar");
				grid.setTb(tbi);
			}
			
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}



}
