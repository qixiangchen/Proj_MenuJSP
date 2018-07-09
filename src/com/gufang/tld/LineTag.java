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

public class LineTag extends BaseTag{
	@Override
	public int doStartTag() throws JspException {
		try
		{
			Object obj = pageContext.getAttribute("currentCtrl");
			if(obj instanceof GridInfo)
			{
				GridInfo grid = (GridInfo)obj;
				ToolbarInfo tbi = grid.getTb();
				LineInfo li = new LineInfo();
				tbi.addLines(li);
			}
			
		} catch(Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

}
