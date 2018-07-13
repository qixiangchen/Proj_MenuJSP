package com.gufang.oa.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gufang.oa.bean.A2RInfo;
import com.gufang.oa.bean.AnimalInfo;
import com.gufang.oa.bean.RegionInfo;

public interface IAnimalService {
	public List<AnimalInfo> findAnimal(String query);
	public List<RegionInfo> findRegion();
	public void saveAnimal(AnimalInfo ai);
	public void updateAnimal(AnimalInfo ai);
	public void deleteAnimal(Integer id);
	public void saveA2R(A2RInfo a2r);
	public void deleteA2R(Integer id);
	public String findRegionByAid(Integer id);
}
