package com.gufang.tld;

import java.util.ArrayList;
import java.util.List;

public class WindowInfo {
	private String id = null;
	private String title = null;
	private String fromId = null;
	private String url = null;
	private String width = "400";
	private String height = "280";
	private String datagrid = "";
	List<FieldInfo> flds = new ArrayList<FieldInfo>();
	List<ButtonInfo> btns = new ArrayList<ButtonInfo>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
		return flds;
	}
	public void setFlds(List<FieldInfo> flds) {
		this.flds = flds;
	}
	public void addFlds(FieldInfo fld) {
		if(flds == null)
			flds = new ArrayList();
		flds.add(fld);
	}
	public List<ButtonInfo> getBtns() {
		return btns;
	}
	public void setBtns(List<ButtonInfo> btns) {
		this.btns = btns;
	}
	public void addBtns(ButtonInfo btn) {
		if(btns == null)
			btns = new ArrayList();
		btns.add(btn);
	}
	public String getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
	}	
}
