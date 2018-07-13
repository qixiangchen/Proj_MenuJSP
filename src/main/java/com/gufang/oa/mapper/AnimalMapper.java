package com.gufang.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gufang.oa.bean.A2RInfo;
import com.gufang.oa.bean.AnimalInfo;
import com.gufang.oa.bean.RegionInfo;

public interface AnimalMapper {
	public List<AnimalInfo> findAnimal(@Param("query") String query);
	public List<RegionInfo> findRegion();
	public void saveAnimal(AnimalInfo ai);
	public void updateAnimal(AnimalInfo ai);
	public void deleteAnimal(@Param("aid") Integer id);
	public void saveA2R(A2RInfo a2r);
	public void deleteA2R(@Param("aid") Integer id);
	public String findRegionByAid(@Param("aid") Integer id);
}
