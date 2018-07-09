package com.gufang.tld;

import java.util.ArrayList;
import java.util.List;

public class LineInfo {
	List<FieldInfo> flds = new ArrayList<FieldInfo>();
	List<ButtonInfo> btns = new ArrayList<ButtonInfo>();
	public List<FieldInfo> getFlds() {
		return flds;
	}
	public void setFlds(List<FieldInfo> flds) {
		this.flds = flds;
	}
	public void addFlds(FieldInfo fi)
	{
		if(flds == null)
			flds = new ArrayList<FieldInfo>();
		flds.add(fi);
	}
	public List<ButtonInfo> getBtns() {
		return btns;
	}
	public void setBtns(List<ButtonInfo> btns) {
		this.btns = btns;
	}
	public void addBtns(ButtonInfo bi)
	{
		if(btns == null)
			btns = new ArrayList<ButtonInfo>();
		btns.add(bi);
	}
}
