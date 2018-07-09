package com.gufang.tld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class GridTag extends BaseTag{
	private String id = "dg";
	private String url = "login.do";
	private String title = "用户名";
	private String pageSize = "4";
	private String pageDim = "4,8";
	private String selectOne = "true";
	private String remoteSort = "false";
	private String pagination = "true";
	private String rownumbers = "false";
	private List<ColumnInfo> cols = new ArrayList<ColumnInfo>();
	private List<ButtonInfo> btns = new ArrayList<ButtonInfo>();
	private ToolbarInfo tb = null;

	@Override
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			GridInfo grid = new GridInfo();
			grid.setId(getId());
			this.pageContext.setAttribute("currentCtrl", grid);

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
				out.println("Url is null");
				return SKIP_BODY;
			}
			FreeMarkerUtil  fmutil = new FreeMarkerUtil();
			Map map = new HashMap();
			map.put("ctx",getCtx());
			map.put("id",getId());
			map.put("url",getUrl());
			map.put("title",getTitle());
			map.put("pageSize",getPageSize());
			map.put("pageDim",getPageDim());
			map.put("selectOne",getSelectOne());
			map.put("remoteSort",getRemoteSort());

			GridInfo grid = (GridInfo)pageContext.getAttribute("currentCtrl");
			List<ColumnInfo> cols = grid.getCols();
			map.put("cols",cols);
			List<ButtonInfo> btns = grid.getBtns();
			map.put("btns",btns);
			ToolbarInfo tb = grid.getTb();
			if(tb != null)
				map.put("tb",tb);
			
			map.put("pagination",getPagination());
			map.put("rownumbers",getRownumbers());

			fmutil.process("grid.ftl",map,out);
		} catch(Exception e) {
			e.printStackTrace();
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

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageDim() {
		return pageDim;
	}

	public void setPageDim(String pageDim) {
		this.pageDim = pageDim;
	}

	public String getSelectOne() {
		return selectOne;
	}

	public void setSelectOne(String selectOne) {
		this.selectOne = selectOne;
	}

	public List<ColumnInfo> getCols() {
		if(cols == null)
		{
			cols = new ArrayList<ColumnInfo>();
		}
		return cols;
	}

	public void setCols(List<ColumnInfo> cols) {
		this.cols = cols;
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
	public String getRemoteSort() {
		return remoteSort;
	}
	public void setRemoteSort(String remoteSort) {
		this.remoteSort = remoteSort;
	}
	public ToolbarInfo getTb() {
		return tb;
	}
	public void setTb(ToolbarInfo tb) {
		this.tb = tb;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public String getRownumbers() {
		return rownumbers;
	}
	public void setRownumbers(String rownumbers) {
		this.rownumbers = rownumbers;
	}

}
