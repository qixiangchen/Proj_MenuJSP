package com.gufang.oa.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gufang.oa.FreeMarkerUtil;
import com.gufang.oa.bean.A2RInfo;
import com.gufang.oa.bean.AnimalInfo;
import com.gufang.oa.service.IAnimalService;
import com.test.dubbo.IUserMng;

@Controller
public class AnimalCtrl {
	@Reference(check=false)
	private IUserMng userMng;
	
	@Autowired
	private IAnimalService serv;
	
	@RequestMapping("/init")
	public String init()
	{
		System.out.println(userMng.getUserName("admin"));
		return "animal";
	}
	
	@ResponseBody
	@RequestMapping("/findanimal")
	public Map findanimal(Integer page,Integer rows,String query)
	{
		Map m = new HashMap();
		PageHelper.startPage(page, rows);
		List lst = serv.findAnimal(query);
		PageInfo<List> pi = new PageInfo<List>(lst); 
		m.put("rows",pi.getList());
		m.put("total",pi.getTotal());
		
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/findregion")
	public List findregion()
	{
		return serv.findRegion();
	}
	
	@ResponseBody
	@RequestMapping("/saveanimal")
	public boolean saveanimal(HttpServletRequest req,AnimalInfo ai)
	{
		System.out.println("aid="+ai.getAid()+",regionId="+ai.getRegionId());
		String regionId = ai.getRegionId();
		Integer aid = ai.getAid();
		boolean isNew = true;
		if(aid != null)
			isNew = false;
		if(isNew)
		{
			serv.saveAnimal(ai);
		}
		else
		{
			serv.updateAnimal(ai);
			serv.deleteA2R(aid);
		}
		if(regionId != null)
		{
			String[] dim = regionId.split(",");
			//for(int i=0;i<dim.length;i++)
			for(String s:dim)
			{
				Integer rid = Integer.parseInt(s);
				A2RInfo a2r = new A2RInfo();
				a2r.setRid(rid);
				a2r.setAid(ai.getAid());
				serv.saveA2R(a2r);
			}
		}
		//生成静态页面
		Map map = new HashMap();
		map.put("id", ai.getAid());
		map.put("name",ai.getName());
		map.put("weight", ai.getWeight());
		String regionName = serv.findRegionByAid(ai.getAid());
		map.put("regionName", regionName);
		
		FreeMarkerUtil.generateHtml(req.getServletContext(), "animal.ftl", map);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/delanimal")
	public boolean delanimal(String aid)
	{
		if(aid != null)
		{
			String[] dim = aid.split(",");
			for(String s:dim)
			{
				Integer aid2 = Integer.parseInt(s);
				serv.deleteAnimal(aid2);
				serv.deleteA2R(aid2);
			}
		}
		return true;
	}
}
