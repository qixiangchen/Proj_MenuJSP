package com.gufang.tld;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class Util {
	public static String toStr(Object obj)
	{
		if(obj == null)
			return "";
		return obj.toString().trim();
	}
	
	public static boolean isNull(Object obj)
	{
		if(obj == null)
			return true;
		if("".equals(obj.toString().trim()))
			return true;
		return false;
	}
	
	public static String getId()
	{
		return UUID.randomUUID().toString();
	}
	
	public static String getContext()
	{
		try
		{
			ClassLoader cl = Util.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("webctx.properties");
			if(is != null)
			{
				Properties prop = new Properties();
				prop.load(is);
				String key = "webctx";
				String value = prop.getProperty(key);
				return value;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static List getField(Class clz)
	{
		List lst = new ArrayList();
		Field[] fdim = clz.getDeclaredFields();
		for(Field f:fdim)
			lst.add(f.getName());
		return lst;
	}
	
	public static Method getMethod(Class clz,String name)
	{
		Method[] mdim = clz.getMethods();
		String mname = "get"+name;
		for(Method m:mdim)
		{
			if(mname.equalsIgnoreCase(m.getName()))
				return m;
		}
		return null;
	}
	
	public static String getFieldValue(Object obj,String field)
	{
		try
		{
			Class clz = obj.getClass();
			Method m = getMethod(clz,field);
			if(m == null)
				return "";
			Object rtn = m.invoke(obj, new Object[]{});
			if(rtn == null)
				return "";
			return rtn.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public static Map easyuiMap(List lst,Integer total)
	{
		Map m = new HashMap();
		m.put("rows", lst);
		m.put("total", total);
		
		return m;
	}
}
