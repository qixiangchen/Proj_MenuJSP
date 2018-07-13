package com.test.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.bean.BookInfo;
import com.test.bean.BookUser;
import com.test.service.IBookService;

@Controller
public class BookCtrl {
	@Autowired
	private IBookService serv;
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req)
	{
		req.getSession().setAttribute("loginId", null);
		return "login";
	}
	
	@RequestMapping("/likebook")
	public String likebook(HttpServletRequest req)
	{
		return "userlikes";
	}
	
	@RequestMapping("/logindone")
	public String logindone(HttpServletRequest req,String loginId,String pwd)
	{
		req.setAttribute("userName", loginId);
		req.getSession().setAttribute("loginId", loginId);
		if("admin".equals(loginId) && "1".equals(pwd))
		{
			return "bookadmin";
		}
		else if(("user1".equals(loginId) || 
				"user2".equals(loginId) ||
				"user3".equals(loginId) ||
				"user4".equals(loginId) ||
				"user5".equals(loginId)) && "1".equals(pwd))
		{
			return "bookuser";
		}
		else
			return "login";
			
	}
	
	@ResponseBody
	@RequestMapping("/findadminbook")
	public List findadminbook(HttpServletRequest req,String name,String typeId)
	{
		if(name == null)
			name = "";
		if(typeId == null)
			typeId = "";
		String loginId = (String)req.getSession().getAttribute("loginId");
		System.out.println("findadminbook loginId==="+loginId);
		return serv.findAdminBook(name,typeId);
	}
	
	@ResponseBody
	@RequestMapping("/finduserbook")
	public List finduserbook(HttpServletRequest req)
	{
		String loginId = (String)req.getSession().getAttribute("loginId");
		System.out.println("finduserbook loginId==="+loginId);
		return serv.findUserBook(loginId);
	}
	
	@ResponseBody
	@RequestMapping("/booksave")
	public boolean booksave(BookInfo bi)
	{
		Integer id = bi.getId();
		if(id != null)
			serv.updateAdminBook(bi);
		else
			serv.saveAdminBook(bi);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/userlike")
	public boolean userlike(HttpServletRequest req,int[] ids)
	{
		String loginId = (String)req.getSession().getAttribute("loginId");
		System.out.println("userlike loginId==="+loginId);
		for(int i=0;i<ids.length;i++)
		{
			BookUser bu = new BookUser();
			bu.setBookId(ids[i]);
			bu.setLoginId(loginId);
			serv.saveUserBook(bu);
		}
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/delbook")
	public boolean delbook(Integer id)
	{
		serv.deleteBook(id);
		serv.deleteUserBook(id);
		return true;
	}
}
