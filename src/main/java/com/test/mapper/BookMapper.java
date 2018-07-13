package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bean.BookInfo;
import com.test.bean.BookUser;

public interface BookMapper {
	public List<BookInfo> findAdminBook(@Param("name") String name,
			@Param("typeId") String typeId);
	public List<BookInfo> findUserBook(@Param("loginId") String loginId);
	public void saveAdminBook(BookInfo bi);
	public void updateAdminBook(BookInfo bi);
	public void deleteBook(@Param("id") Integer id);
	public void deleteUserBook(@Param("id") Integer id);
	public void saveUserBook(BookUser bu);
}
