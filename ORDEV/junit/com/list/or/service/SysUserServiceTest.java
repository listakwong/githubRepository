package com.list.or.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.list.or.bean.admin.SysUser;
import com.list.or.service.admin.ISysUserService;

public class SysUserServiceTest {

	private static ApplicationContext ac = null;
	private static ISysUserService sysUserService;
	
	static {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		sysUserService = (ISysUserService) ac.getBean("sysUserService");
	}
	
	@Test
	public void testGetSysUser() {
		SysUser user = new SysUser();
		user = sysUserService.getSysUser("user01");
		System.out.println(user.getUser_name());
		
		
		for(String u : user.getGroups()) {
			System.out.println(u);
		}
	}


}
