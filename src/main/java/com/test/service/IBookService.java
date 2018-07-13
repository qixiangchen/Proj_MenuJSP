package com.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bean.BookInfo;
import com.test.bean.BookUser;

public interface IBookService {
	public List<BookInfo> findAdminBook(String name,String typeId);
	public List<BookInfo> findUserBook(String loginId);
	public void saveAdminBook(BookInfo bi);
	public void updateAdminBook(BookInfo bi);
	public void deleteBook(Integer id);
	public void saveUserBook(BookUser bu);
	public void deleteUserBook(Integer id);
}
