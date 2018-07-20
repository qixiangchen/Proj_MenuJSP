package com.test.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.bean.BlogInfo;
import com.test.bean.TypeInfo;
import com.test.service.IBlogService;
import com.test.service.ITypeService;
import com.test.util.FreeMarkerUtil;
import com.test.util.RedisTool;
import com.test.util.ResultInfo;
import com.test.util.SolrTool;

@Controller
@CacheConfig(cacheNames = "BlogCtrl")
public class BlogCtrl {
	@Autowired
	private IBlogService blogServ;
	@Autowired
	private ITypeService typeServ;
	@Autowired
	private SolrTool solr;
	@Autowired
	private RedisTool redis;
	
	@RequestMapping("/init")
	public String init()
	{
		List lst = new ArrayList();
		lst.add("Hello");
		//redis.saveList("java", lst);
		System.out.println(redis.getList("java"));
		
		return "blog";
	}
	
	@ResponseBody
	@RequestMapping("/findblog")
	public Map findblog(Integer page,Integer rows)
	{
		Map m = new HashMap();
		//定义查询封装对象，对应生成SQL的where子句
		Wrapper<BlogInfo> wrap = new EntityWrapper<BlogInfo>();
		//wrap.like("name", "%Huawei%");
		Page<BlogInfo> p = new Page<BlogInfo>(page,rows);
		List<BlogInfo> lst = blogServ.selectPage(p,wrap).getRecords();
		for(BlogInfo bi:lst)
		{
			Long typeId = bi.getTypeId();
			TypeInfo ti = (TypeInfo)typeServ.selectById(typeId);
			if(ti != null)
				bi.setTypeName(ti.getName());
		}
		
		m.put("rows", lst);
		m.put("total", blogServ.selectCount(wrap));
		return m;
	}
	
	@Cacheable(keyGenerator = "keyGenerator")
	@ResponseBody
	@RequestMapping("/findsolrblog")
	public Map findsolrblog(Integer page,Integer rows,String name)
	{
		System.out.println("name="+name);
		Map m = new HashMap();
		String query = "*:*";//"name:"+name;
		if(name != null && !"".equals(name))
			query = "name:"+name;
		Integer starts = (page-1)*rows;
		ResultInfo<BlogInfo> result = solr.queryList(BlogInfo.class, query, starts, rows, "name,content");
				
		m.put("rows", result.getList());
		m.put("total", result.getTotal());
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/findtype")
	public List findtype()
	{
		Wrapper<TypeInfo> wrap = new EntityWrapper<TypeInfo>();
		return typeServ.selectList(wrap);

		
	}
	
	@CacheEvict(allEntries=true)
	@ResponseBody
	@RequestMapping("/saveblog")
	public boolean saveblog(HttpServletRequest req,BlogInfo bi)
	{
		blogServ.insertOrUpdate(bi);
		
		Map map = new HashMap();
		map.put("id",bi.getId());
		map.put("name",bi.getName());
		map.put("dt",bi.getDt());
		map.put("status",bi.getStatus());
		
		TypeInfo ti = (TypeInfo)typeServ.selectById(bi.getTypeId());
		map.put("typeName", ti.getName());
		map.put("typeId", bi.getTypeId());
		map.put("content", bi.getContent());
				
		FreeMarkerUtil.generateHtml(req.getServletContext(), "html", "blog.html", map);
		
		solr.addMap(map);
		
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/publishblog")
	public boolean publishblog(HttpServletRequest req,String id)
	{
		BlogInfo blog = (BlogInfo)blogServ.selectById(id);
		blog.setStatus(1);
		blogServ.insertOrUpdate(blog);
		
		Map map = new HashMap();
		map.put("id",blog.getId());
		map.put("name",blog.getName());
		map.put("dt",blog.getDt());
		map.put("status",blog.getStatus());
		TypeInfo ti = (TypeInfo)typeServ.selectById(blog.getTypeId());
		map.put("typeName", ti.getName());
		map.put("typeId", blog.getTypeId());
		map.put("content", blog.getContent());
				
		FreeMarkerUtil.generateHtml(req.getServletContext(), "html", "blog.html", map);
		
		solr.update(map);
		
		return true;
	}
	
	@CacheEvict(allEntries=true)
	@ResponseBody
	@RequestMapping("/deleteblog")
	public boolean deleteblog(HttpServletRequest req,String id)
	{
		if(id != null)
		{
			String[] dim = id.split(",");
			for(String sid:dim)
			{
				blogServ.deleteById(sid);
				solr.delete(sid);
			}
		}

		return true;
	}
}
