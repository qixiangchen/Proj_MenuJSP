package com.gufang.mvc;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gufang.bean.Student;
import com.gufang.serv.impl.OrgTreeData;
import com.gufang.serv.impl.StudentService;
import com.gufang.tld.TreeData;
import com.test.springrabbitmq.SendMsg;


@Controller
@RequestMapping("/stu")
public class StudentCtrl {
	@Autowired
	StudentService stuSrv;
	@Autowired
	SendMsg sendMsg;
	@Autowired
	OrgTreeData orgData;
	
	@RequestMapping("/login")
	public String login(){
		return "grid";
	}
	
    @ResponseBody
    @RequestMapping("/findall")
    public Map login(HttpServletRequest req,String page,String rows){
    	int iPage = 1;
    	if(page != null)
    		iPage = Integer.parseInt(page);
    	int iRows = 10;
    	if(rows != null)
    		iRows = Integer.parseInt(rows);
    	Map reMap = new HashMap();
    	System.out.println("iPage="+iPage+",iRows="+iRows);
    	List<Student> lst = stuSrv.findAll(iPage,iRows);
        reMap.put("rows", lst);
        reMap.put("total", stuSrv.findCount());
    	return reMap;
    }
    
    @ResponseBody
    @RequestMapping("/query")
    public Map query(HttpServletRequest req,String page,String rows,String no){
    	int iPage = 1;
    	if(page != null)
    		iPage = Integer.parseInt(page);
    	int iRows = 10;
    	if(rows != null)
    		iRows = Integer.parseInt(rows);
    	Map reMap = new HashMap();
    	System.out.println("iPage="+iPage+",iRows="+iRows+",no="+no);
    	List<Student> lst = stuSrv.query(no);
        reMap.put("rows", lst);
        reMap.put("total", 10);
    	return reMap;
    }
    
	@ResponseBody
	@RequestMapping("/findhobby")
	public List findhobby(){
		List rtn = stuSrv.findHobby();
		return rtn;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public boolean save(String id,String no,String name,Date dt,String chkid){
		System.out.println("id="+id+",no="+no+",name="+name+",dt="+dt+",chkid="+chkid);
		Student stu = new Student();
		stu.setId(id);
		stu.setNo(Integer.parseInt(no));
		stu.setName(name);
		stu.setDt(dt);
		stu.setChkid(chkid);
		//stuSrv.saveStudent(stu);
		String exhName = "springrabbit_exg";
		String routingKey = "direct";
		sendMsg.sendObject(exhName, routingKey, stu);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public boolean delete(String ids){
		if(ids != null)
		{
			String[] dim = ids.split(",");
			for(String id:dim)
				stuSrv.delStudent(id);
		}
    	return true;
	}
	
	@ResponseBody
	@RequestMapping("/findorg")
	public String findorg(){
		TreeData tdata = new TreeData(orgData);
		String str = tdata.getTree();
		return str;
	}
}
