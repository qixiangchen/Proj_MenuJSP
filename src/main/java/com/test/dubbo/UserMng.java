package com.test.dubbo;

import org.springframework.stereotype.Service;

@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IUserMng.class)
@Service
public class UserMng implements IUserMng{

	public UserInfo getUserName(String id) {
		if("admin".equals(id))
			return new UserInfo("admin","管理员");
		if("user".equals(id))
			return new UserInfo("user","普通用户");
		return new UserInfo("none","无用户名");
	}

}
