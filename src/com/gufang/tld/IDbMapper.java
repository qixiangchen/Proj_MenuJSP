package com.gufang.tld;

import java.util.List;

public interface IDbMapper {
	//设置树形节点的ID字段名称
	public void setIdField(String id);
	//树形节点的ID字段名称
	public String getIdField();
	//设置树形节点的Text字段名称
	public void setNameField(String name);
	//树形节点的Text字段名称
	public String getNameField();
	//取得根对象
	public Object getRootObject();
	//根据ID取得子节点列表
	public List getChildren(String id);
}
