package com.list.or.bean.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class SysParamTest {

	@Test
	public void testSysParam() throws Exception {
		SysParam lSysParam = new SysParam();
		Field[] fields = lSysParam.getClass().getDeclaredFields();
		for(Field field : fields) {
			System.out.println(field.getName());
			String methodName = "get" + Character.toUpperCase( field.getName().charAt(0)) +field.getName().substring(1, field.getName().length() );
			Method m = lSysParam.getClass().getDeclaredMethod(methodName);
			System.out.println(m.getName());
		}
		
	}
}
