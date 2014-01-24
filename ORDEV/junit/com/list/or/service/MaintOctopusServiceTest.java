package com.list.or.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.list.or.bean.maint.MailingList;
import com.list.or.bean.maint.RevenueItem;
import com.list.or.service.maint.IMaintOctopusService;

public class MaintOctopusServiceTest {
	private static ApplicationContext ac = null;
	private static IMaintOctopusService maintOctopusService;
	
	static {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		maintOctopusService = (IMaintOctopusService) ac.getBean("maintOctopusService");
	}
	
	
	@Test 
	public void testGetRevenueItem(){
		
		RevenueItem revenueItem = new RevenueItem();
		revenueItem.getOctopusServiceType().setService_type_code(4);
		revenueItem.setRevenue_item_code("MM");
		
		revenueItem = maintOctopusService.getRevenueItem(revenueItem);
		
		System.out.println(revenueItem.getOctopusServiceType().getService_type_code() + " | " + revenueItem.getRevenue_item_code() + " | " + revenueItem.getRevenue_item_name() + " | " + revenueItem.getRevenue_user_code());
	
		MailingList mailingList = new MailingList();
		mailingList.getOctopusServiceType().setService_type_code(19);
		mailingList.setMail_id(31);
		mailingList = maintOctopusService.getMailingList(mailingList);
		
		System.out.println(mailingList.getOctopusServiceType().getService_type_code() + " | " + mailingList.getMail_id() + " | " + mailingList.getEmail());
	}
}
