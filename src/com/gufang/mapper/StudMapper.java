package com.gufang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gufang.bean.Student;
import com.gufang.bean.Student2HobbyInfo;
import com.gufang.tld.IDbMapper;

public interface StudMapper{
	public List<Student> findAll(@Param("starts") Integer starts,@Param("rows") Integer rows);
	public List<Student> query(@Param("no") String no);
	public List findHobby();
	public Integer findCount();
	public boolean saveStudent(Student si);
	public boolean delStudent(String id);
	public boolean updateStudent(Student si);
	public boolean saveStudentHobby(Student2HobbyInfo s2h);
	public List findOrg(@Param("id") String id);
}
