package com.gufang.serv;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gufang.bean.Student;
import com.gufang.bean.Student2HobbyInfo;

public interface IStudentService {
	public List<Student> findAll(Integer page,Integer rows);
	public List<Student> query(String no);
	public List findHobby();
	public Integer findCount();
	public boolean saveStudent(Student si);
	public boolean delStudent(String id);
	public boolean updateStudent(Student si);
	public boolean saveStudentHobby(Student2HobbyInfo s2h);
}
