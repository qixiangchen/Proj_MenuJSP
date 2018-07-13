package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.BookInfo;
import com.test.bean.BookUser;
import com.test.mapper.BookMapper;
import com.test.service.IBookService;

@Service
public class BookServiceImpl implements IBookService{
	@Autowired
	private BookMapper mapper;

	@Override
	public List<BookInfo> findAdminBook(String name,String typeId) {
		return mapper.findAdminBook(name,typeId);
	}

	@Override
	public void saveAdminBook(BookInfo bi) {
		mapper.saveAdminBook(bi);
		
	}

	@Override
	public void updateAdminBook(BookInfo bi) {
		mapper.updateAdminBook(bi);
		
	}

	@Override
	public void deleteBook(Integer id) {
		mapper.deleteBook(id);
		
	}

	@Override
	public List<BookInfo> findUserBook(String loginId) {
		return mapper.findUserBook(loginId);
	}

	@Override
	public void saveUserBook(BookUser bu) {
		mapper.saveUserBook(bu);
	}

	@Override
	public void deleteUserBook(Integer id) {
		mapper.deleteUserBook(id);
		
	}

}
