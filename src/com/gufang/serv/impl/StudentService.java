package com.gufang.serv.impl;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gufang.bean.Student;
import com.gufang.bean.Student2HobbyInfo;
import com.gufang.mapper.StudMapper;
import com.gufang.serv.IStudentService;
import com.test.springrabbitmq.IMsgProcess;


@Service("msgProcess")
public class StudentService implements IStudentService,IMsgProcess{
	@Autowired
	private StudMapper mapper;


	public List<Student> findAll(Integer page,Integer rows) {
		int page2 = page - 1;
		int starts = page2 * rows;
		return mapper.findAll(starts,rows);
	}
	
	public List findHobby() {
		return mapper.findHobby();
	}

	public Integer findCount() {
		return mapper.findCount();
	}

	public boolean saveStudent(Student si) {
		String id = si.getId();
		if(id == null || "".equals(id))
		{
			//Insert
			String sid = com.gufang.tld.Util.getId();
			si.setId(sid);
			mapper.saveStudent(si);
			String chkid = si.getChkid();
			String[] dim = chkid.split(",");
			for(String s:dim)
			{
				Student2HobbyInfo s2h = new Student2HobbyInfo();
				String id2 = com.gufang.tld.Util.getId();
				s2h.setId(id2);
				s2h.setSid(sid);
				s2h.setHid(s);
				mapper.saveStudentHobby(s2h);
			}			
		}
		else
		{
			//update 
			mapper.updateStudent(si);
			//delete s2h;
			
			String chkid = si.getChkid();
			String[] dim = chkid.split(",");
			for(String s:dim)
			{
				Student2HobbyInfo s2h = new Student2HobbyInfo();
				String id2 = com.gufang.tld.Util.getId();
				s2h.setId(id2);
				s2h.setSid(si.getId());
				s2h.setHid(s);
				mapper.saveStudentHobby(s2h);
			}
		}
		return true;
	}


	public boolean delStudent(String id) {
		return mapper.delStudent(id);
	}


	public boolean updateStudent(Student si) {
		return mapper.updateStudent(si);
	}


	public List<Student> query(String no) {
		return mapper.query(no);
	}
	
	public boolean saveStudentHobby(Student2HobbyInfo s2h)
	{
		return mapper.saveStudentHobby(s2h);
	}


	@Override
	public Object processMsg(Object obj) {
		try
		{
			Student stu = (Student)obj;
			return saveStudent(stu);
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
