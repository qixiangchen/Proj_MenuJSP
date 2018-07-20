package com.test.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.bean.BlogInfo;
import com.test.mapper.BlogMapper;
import com.test.service.IBlogService;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,BlogInfo> 
	implements IBlogService<BlogInfo>{

}
