package com.test.bean;

import java.io.Serializable;

public class BookUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bookId = null;
	private String loginId = null;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
