package com.gufang.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gufang.oa.bean.A2RInfo;
import com.gufang.oa.bean.AnimalInfo;
import com.gufang.oa.bean.RegionInfo;
import com.gufang.oa.mapper.AnimalMapper;
import com.gufang.oa.service.IAnimalService;

@Service
public class AnimalServiceImpl implements IAnimalService{
	@Autowired
	private AnimalMapper mapper;
	
	@Override
	public List<AnimalInfo> findAnimal(String query) {
		// TODO Auto-generated method stub
		return mapper.findAnimal(query);
	}

	@Override
	public List<RegionInfo> findRegion() {
		// TODO Auto-generated method stub
		return mapper.findRegion();
	}

	@Override
	public void saveAnimal(AnimalInfo ai) {
		// TODO Auto-generated method stub
		mapper.saveAnimal(ai);
	}

	@Override
	public void deleteAnimal(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteAnimal(id);
	}

	@Override
	public void saveA2R(A2RInfo a2r) {
		// TODO Auto-generated method stub
		mapper.saveA2R(a2r);
	}

	@Override
	public void updateAnimal(AnimalInfo ai) {
		// TODO Auto-generated method stub
		mapper.updateAnimal(ai);
	}

	@Override
	public void deleteA2R(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteA2R(id);
	}

	@Override
	public String findRegionByAid(Integer id) {
		// TODO Auto-generated method stub
		return mapper.findRegionByAid(id);
	}

}
