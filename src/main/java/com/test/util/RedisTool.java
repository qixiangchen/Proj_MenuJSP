package com.test.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisTool {
	@Autowired
	//@Qualifier("redisTemplate") 
	private RedisTemplate redis;
	
	public void saveObject(String key,Object obj)
	{
		redis.opsForValue().set(key,obj);
	}
	
	public Object getObject(String key)
	{
		return redis.opsForValue().get(key);
	}
	
	public void saveMap(String key,Map map)
	{
		redis.opsForHash().putAll(key,map);
	}
	
	public Map getMap(String key)
	{
		return redis.opsForHash().entries(key);
	}
	
	public void saveList(String key,List list)
	{
		redis.opsForList().leftPushAll(key, list);
	}
	
	public List getList(String key)
	{
		return redis.opsForList().range(key, 0, -1);
	}
}
