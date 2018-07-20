package com.test.util;

import java.util.List;

public class ResultInfo<T> {
	private List<T> list = null;
	private Long total = null;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}	
}
