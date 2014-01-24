package net.tak.util;

import java.util.List;

import net.tak.bean.common.PageBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.list.or.service.common.IPagingService;

public class PagingUtil {
	
	private static ApplicationContext ac = null;
	private static IPagingService pagingService;
	private static Object class_bean;
	static  {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		pagingService = (IPagingService) ac.getBean("pagingService");
	}
	
	public static List<Object> pagingList(PageBean bean){
		class_bean = (Object) ac.getBean(bean.getClass_name());
		bean.setClass_bean(class_bean.getClass());
		return pagingService.retrievePagingList(bean);
	}

}
