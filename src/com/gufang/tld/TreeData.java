package com.gufang.tld;

import java.util.List;

public class TreeData {
	private IDbMapper mapper;
	public TreeData(IDbMapper mapper)
	{
		this.mapper = mapper;
	}
	
	public String getTree()
	{
		StringBuffer sb = new StringBuffer();
		Object root = mapper.getRootObject();
		if(root != null)
		{
			sb.append("[\r\n");
			recurseTreeNode(sb,root);
			String temp = sb.toString();
			if(temp.endsWith(",\r\n"))
			{
				sb.delete(sb.length()-3, sb.length()-1);
			}
			sb.append("]\r\n");
		}
		
		return sb.toString();
	}

	public void recurseTreeNode(StringBuffer sb,Object obj)
	{
		if(obj != null)
		{
			sb.append("{");
			String idKey = mapper.getIdField();
			String idVal = Util.getFieldValue(obj,idKey);
			String nameKey = mapper.getNameField();
			String nameVal = Util.getFieldValue(obj,nameKey);
			sb.append("\"id\":");
			sb.append("\""+idVal+"\",\r\n");
			sb.append("\"text\":");
			sb.append("\""+nameVal+"\",\r\n");
			//遍历其他属性
			List flds = Util.getField(obj.getClass());
			StringBuffer sb2 = new StringBuffer();
			if(flds.size()>0)
			{
				sb.append("\"attributes\":{\r\n");
				for(Object f:flds)
				{
					String fname = (String)f;
					if(!(fname.equals(mapper.getIdField()) || fname.equals(mapper.getNameField())))
					{
						String fval = Util.getFieldValue(obj,fname);
						sb.append("\""+fname+"\":");
						sb.append("\""+fval+"\",\r\n");
					}
				}
				String temp = sb.toString();
				if(temp.endsWith(",\r\n"))
				{
					sb.delete(sb.length()-3, sb.length()-1);
				}
				sb.append("},\r\n");
			}
			List lst = mapper.getChildren(idVal);
			if(lst.size()>0)
			{
				sb.append("\"children\":[\r\n");
				for(Object obj2:lst)
				{
					recurseTreeNode(sb,obj2);
				}
				String temp = sb.toString();
				if(temp.endsWith(",\r\n"))
				{
					sb.delete(sb.length()-3, sb.length()-1);
				}
				sb.append("]\r\n");
			}
			String temp = sb.toString();
			if(temp.endsWith(",\r\n"))
			{
				sb.delete(sb.length()-3, sb.length()-1);
			}
			sb.append("},\r\n");
		}
	}
}
