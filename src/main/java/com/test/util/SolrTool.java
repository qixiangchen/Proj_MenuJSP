package com.test.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SolrTool {
	@Autowired
	private SolrClient client;
	
	public void delete(String id)
	{
		try
		{
			client.deleteById(id.toString());
			UpdateResponse up = client.commit();
			System.out.println(up);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteAll()
	{
		try
		{
			SolrQuery q = new SolrQuery();
			q.set("q", "*:*");
			q.set("fl", "id");
			QueryResponse resp = client.query(q);
			SolrDocumentList lst = resp.getResults();
			for(SolrDocument doc:lst)
			{
				String id = (String)doc.getFieldValue("id");
				client.deleteById(id);
				
			}
			client.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void update(Map map)
	{
		try
		{
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", map.get("id"));
			for(Object o:map.keySet())
			{
				String key = (String)o;
				if(!"id".equals(key))
				{
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("set", map.get(key));
					doc.addField(key, m);
				}
			}
			UpdateResponse resp = client.add(doc);
			client.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addMap(Map map)
	{
		try
		{
			SolrInputDocument doc = new SolrInputDocument();
			Set<String> keys = map.keySet();
			for(String k:keys)
			{
				doc.addField(k, map.get(k));
			}
	
			client.add(doc);
			UpdateResponse up = client.commit();
			System.out.println(up);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public <T> ResultInfo<T> queryList(Class<T> clz,String query,
			Integer starts,Integer rows,String flCol)
	{
		try
		{
			SolrQuery q = new SolrQuery();
			q.set("q",query);//定义查询条件，使用属性名：属性值的形式，查询全部使用*
			q.set("fl", "*");//定义返回属性列表，返回全部以*代替
			// 分页显示功能
			q.setStart(starts);
			q.setRows(rows);
			q.setHighlight(true);
			q.setHighlightSimplePre("<font color='red'>"); 
			q.setHighlightSimplePost("</font>"); 
			q.setParam("hl.fl", flCol); 
			QueryResponse response = client.query(q);
			String hl = response.getHighlighting().toString();
			Map<String,Map<String,List<String>>> hlMap = response.getHighlighting();
			SolrDocumentList list = response.getResults();
			System.out.println("total = "+list.getNumFound());
			List<T> lst = getBeans(clz, list,flCol,hlMap);
			
			ResultInfo<T> result = new ResultInfo<T>();
			result.setList(lst);
			result.setTotal(list.getNumFound());
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public final <T> List<T> getBeans(Class<T> clazz, 
			SolrDocumentList solrDocumentList,String flCol,
			Map<String,Map<String,List<String>>> hlMap) throws Exception{
		List<T> list = new ArrayList<T>();
		T t = null;
		for (SolrDocument solrDocument : solrDocumentList)
		{
			//反射出实例
			t = clazz.newInstance();
			//获取所有属性
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields)
			{
				Object val = solrDocument.get(field.getName());
				if(val != null)
				{
					String val2 = val.toString();
					if(val2.startsWith("[") && val2.endsWith("]"))
						val2 = val2.substring(1,val2.length()-1);
					if(field.getType() == java.sql.Date.class)
					{
						java.util.Date dt = new java.util.Date(val2);
						val2 = new java.sql.Date(dt.getTime()).toString();
					}
					if(field.getType() == java.sql.Timestamp.class)
					{
						java.util.Date dt = new java.util.Date(val2);
						val2 = new java.sql.Timestamp(dt.getTime()).toString();
					}
					if(flCol.indexOf(field.getName())>=0)
					{
		                List<String> lstHigh= hlMap.get(solrDocument.get("id")).get(field.getName());
		                if(lstHigh!=null && lstHigh.size()>0)
		                	val2=lstHigh.get(0);
					}
					BeanUtils.setProperty(t, field.getName(), val2);
				}
			}
			list.add(t);
		}
		return list;

	}

	public final <T> T getBean(Class<T> clazz, 
			SolrDocument solrDocument) throws Exception {
		//反射出实例
		T t = clazz.newInstance();
		//获取所有属性
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 如果注解为默认的 采用此属性的name来从solr中获取值
			BeanUtils.setProperty(t, field.getName(), 
					solrDocument.get(field.getName()));
	
		}
		return t;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
