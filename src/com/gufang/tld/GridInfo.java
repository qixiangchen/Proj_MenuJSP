package com.gufang.tld;

import java.util.ArrayList;
import java.util.List;

public class GridInfo {
	private String id = "dg";
	private List<ColumnInfo> cols = new ArrayList<ColumnInfo>();
	private List<ButtonInfo> btns = new ArrayList<ButtonInfo>();
	private ToolbarInfo tb = null;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public void addCols(ColumnInfo col) {
		if(cols == null)
		{
			cols = new ArrayList<ColumnInfo>();
		}
		cols.add(col);
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
	public void addBtns(ButtonInfo btn) {
		if(btns == null)
		{
			btns = new ArrayList<ButtonInfo>();
		}
		btns.add(btn);
	}
	public ToolbarInfo getTb() {
		return tb;
	}
	public void setTb(ToolbarInfo tb) {
		this.tb = tb;
	}
}
