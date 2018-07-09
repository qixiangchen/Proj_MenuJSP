package com.gufang.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gufang.mapper.StudMapper;
import com.gufang.tld.IDbMapper;

@Service
public class OrgTreeData implements IDbMapper{
	@Autowired
	private StudMapper mapper;

	@Override
	public void setIdField(String id) {
		
	}

	@Override
	public String getIdField() {
		return "id";
	}

	@Override
	public void setNameField(String name) {

	}

	@Override
	public String getNameField() {
		return "name";
	}

	@Override
	public Object getRootObject() {
		List lst = mapper.findOrg(null);
		if(lst.size()>0)
			return lst.get(0);
		return null;
	}

	@Override
	public List getChildren(String id) {
		return mapper.findOrg(id);
	}

}
