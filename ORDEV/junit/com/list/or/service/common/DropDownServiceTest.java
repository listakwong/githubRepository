package com.list.or.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tak.bean.common.DropDown;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DropDownServiceTest {
	
	private static ApplicationContext ac = null;
	private static IDropDownService dropDownService;
	static  {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		dropDownService = (IDropDownService) ac.getBean("dropDownService");
	}
	
	@Test
	public void testTetrieveSyscode() {
		Map<String,String> map = new HashMap<String,String>();
		
		//map.put("code_id", "N");
		//map.put("active_ind", "Y");
		List<DropDown> list = dropDownService.retrieveSyscode(map);
		System.out.println("--------------");
		for(DropDown down : list) {
			
			System.out.println(down.getKey() + " | " + down.getValue() + " | " + down.getType());
		}
	}
	
	@Test
	public void testRetrieveSysusergroup() {
		Map<String,String> map = new HashMap<String,String>();
		
		//map.put("code_id", "N");
		map.put("active_ind", "Y");
		List<DropDown> list = dropDownService.retrieveGroups();
		System.out.println("--------------");
		for(DropDown down : list) {
			
			System.out.println(down.getKey() + " | " + down.getValue() + " | " + down.getType());
		}
	}

}
