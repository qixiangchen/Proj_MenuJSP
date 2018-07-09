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

public class ButtonTag extends BaseTag{
	private String click = null;
	private String name = null;

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		try
		{
			JspWriter out = this.pageContext.getOut();
			
			ButtonInfo bi = new ButtonInfo(click,name);
			Object obj = pageContext.getAttribute("currentCtrl");
			if(obj instanceof WindowInfo)
			{
				WindowInfo win = (WindowInfo)obj;
				win.addBtns(bi);
			}
			if(obj instanceof GridInfo)
			{
				GridInfo grid = (GridInfo)obj;
				ToolbarInfo tbi = grid.getTb();
				LineInfo li = tbi.getCurrentLine();
				if(li != null)
				{
					li.addBtns(bi);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
