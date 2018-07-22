package com.test.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.bean.M2TInfo;
import com.test.bean.MenuInfo;
import com.test.bean.TypeInfo;
import com.test.service.IM2TService;
import com.test.service.IMenuService;
import com.test.service.ITypeService;
import com.test.util.FreeMarkerUtil;
import com.test.util.PageUtil;
import com.test.util.RedisTool;
import com.test.util.ResultInfo;
import com.test.util.SolrTool;

@Controller
public class MenuCtrl {
	@Autowired
	private IMenuService menuServ;
	@Autowired
	private ITypeService typeServ;
	@Autowired
	private IM2TService m2tServ;
	@Autowired
	private SolrTool solr;
	@Autowired
	private RedisTool redis;
	
	private ModelAndView initListData(Integer page,Integer rows,String name)
	{
		if(page == null)
			page = 1;
		if(rows == null)
			rows = 4;
		if(name == null)
			name = "";
		System.out.println("page="+page+",rows="+rows+",name="+name);
		ModelAndView mv = initListDataSolr(page,rows,name);
		mv.addObject("query", name);
		return mv;
	}
	
	private ModelAndView initListDataDb(Integer page,Integer rows,String name)
	{
		ModelAndView mv = new ModelAndView();
		Wrapper<MenuInfo> wrapper = new EntityWrapper<MenuInfo>();
		wrapper.like("name", "%"+name+"%");
		Page<MenuInfo> p = new Page<MenuInfo>(page,rows);
		Page pageObj = menuServ.selectPage(p,wrapper);
		List<MenuInfo> menuList = pageObj.getRecords();
		for(MenuInfo mi:menuList)
		{
			Wrapper<M2TInfo> wrapper2 = new EntityWrapper<M2TInfo>();
			wrapper2.eq("mid", mi.getId());
			List<M2TInfo> m2tLst = m2tServ.selectList(wrapper2);
			String tname = "";
			for(M2TInfo m2t:m2tLst)
			{
				TypeInfo ti = typeServ.selectById(m2t.getTid());
				tname = tname + ti.getName() + ",";
			}
			mi.setTname(tname);
		}
		mv.addObject("menuList", menuList);
		mv.setViewName("menu");
		
		String url = "/init?name="+name;
		Integer total = menuServ.selectCount(wrapper);
		PageUtil pu = new PageUtil(url,page,rows,total);
		mv.addObject("pagediv", pu.toHtml());
		
		return mv;
	}
	
	private ModelAndView initListDataSolr(Integer page,Integer rows,String name)
	{
		String query = "*:*";//"name:"+name;
		if(name != null && !"".equals(name))
			query = "name:"+name;
		ModelAndView mv = new ModelAndView();
		Integer starts = (page-1)*rows;
		ResultInfo<MenuInfo> result = solr.queryList(MenuInfo.class, query, starts, rows, "name");		
		mv.addObject("menuList", result.getList());
		mv.setViewName("menu");
		
		String url = "/init?name="+name;
		Integer total = result.getTotal().intValue();
		PageUtil pu = new PageUtil(url,page,rows,total);
		mv.addObject("pagediv", pu.toHtml());
		
		return mv;
	}
	
	@RequestMapping("/init")
	public ModelAndView init(Integer page,Integer rows,String name)
	{
		return initListData(page,rows,name);
	}
	
	@RequestMapping("/addmenu")
	public ModelAndView addmenu(Integer mid)
	{
		MenuInfo mi = new MenuInfo();
		if(mid != null)
		{
			mi = menuServ.selectById(mid);
		}
		ModelAndView mv = new ModelAndView();
		Wrapper<TypeInfo> wrapper = new EntityWrapper<TypeInfo>();
		List<TypeInfo> typeList = typeServ.selectList(wrapper);
		if(mid != null)
		{
			Wrapper<M2TInfo> wrapper2 = new EntityWrapper<M2TInfo>();
			wrapper2.eq("mid", mid);
			List<M2TInfo> m2tLst = m2tServ.selectList(wrapper2);
			for(TypeInfo ti:typeList)
			{
				for(M2TInfo m2t:m2tLst)
				{
					if(ti.getId() == m2t.getTid())
						ti.setChecked("checked");
				}
			}
		}
		mv.addObject("menu", mi);
		mv.addObject("typeList", typeList);
		mv.setViewName("addmenu");
		return mv;
	}
	
	@RequestMapping("/savemenu")
	public ModelAndView savemenu(HttpServletRequest req,MenuInfo mi)
	{
		String tid = mi.getTid();
		menuServ.insertOrUpdate(mi);
		Map delMap = new HashMap();
		delMap.put("mid", mi.getId());
		m2tServ.deleteByMap(delMap);
		
		String tname = "";
		if(tid != null)
		{
			String[] dim = tid.split(",");
			for(String tid2:dim)
			{
				M2TInfo m2t = new M2TInfo();
				m2t.setMid(mi.getId());
				m2t.setTid(Integer.parseInt(tid2));
				m2tServ.insert(m2t);
				
				TypeInfo ti = typeServ.selectById(tid2);
				tname = tname + ti.getName() + ",";
			}
		}
		
		Map m = new HashMap();
		m.put("id",mi.getId());
		m.put("name",mi.getName());
		m.put("dt",mi.getDt());
		m.put("price",mi.getPrice());
		m.put("tname",tname);
		
		FreeMarkerUtil.generateHtml(req.getServletContext(), "html", "menu.html", m);
		solr.addMap(m);
		
		return initListData(1,4,null);
	}
	
	@ResponseBody
	@RequestMapping("/deletemenu")
	public boolean deletemenu(Integer[] ids)
	{
		if(ids != null)
		{
			for(Integer id:ids)
			{
				menuServ.deleteById(id);
				Map delMap = new HashMap();
				delMap.put("mid", id);
				m2tServ.deleteByMap(delMap);
				
				solr.delete(id+"");
			}
		}
		return true;
	}
}
