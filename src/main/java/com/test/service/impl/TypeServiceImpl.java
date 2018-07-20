package com.test.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.bean.BlogInfo;
import com.test.bean.TypeInfo;
import com.test.mapper.BlogMapper;
import com.test.mapper.TypeMapper;
import com.test.service.IBlogService;
import com.test.service.ITypeService;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper,TypeInfo> 
	implements ITypeService<TypeInfo>{

}
