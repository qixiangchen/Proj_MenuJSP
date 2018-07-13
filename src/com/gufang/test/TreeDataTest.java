package com.gufang.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gufang.mapper.StudMapper;
import com.gufang.serv.impl.OrgTreeData;
import com.gufang.tld.TreeData;

public class TreeDataTest {

	public static void main(String[] args) {
		try
		{
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			OrgTreeData orgTreeData = (OrgTreeData)ctx.getBean("orgTreeData");
			Object obj = orgTreeData.getRootObject();
			TreeData tdata = new TreeData(orgTreeData);
			String str = tdata.getTree();
			System.out.println(str);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
