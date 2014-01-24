package net.tak.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tak.TConstant;
import net.tak.bean.common.PageBean;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.list.or.bean.admin.SysUser;

public class PagingUtilTest {
	private static ApplicationContext ac = null;
	String path = "applicationContext.xml";
	@Before
	public void before(){
		ac = new ClassPathXmlApplicationContext(path);
	}
	
	@Test
	public void testClassName() {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		System.out.println("-------------");
		Object class_name =  ac.getBean("sysUser");
		class_name.getClass();
		System.out.println(class_name.getClass());
	}
	
	@Test
	public void testPagingList () throws Exception {
		
		Object class_name =  ac.getBean("sysUser");
		String[] fields = {"user_id","user_name","active_ind","user_id","last_upd_date"};
		
		SysUser sysUser = new SysUser();
		PageBean bean = new PageBean();
		String page = "1";
		
		bean.setClass_bean(class_name.getClass());
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("user_name", sysUser.getUser_name());
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("user_id", sysUser.getUser_id());
		param2.put("active_ind", sysUser.getActive_ind());
		param2.put("locked_ind", sysUser.getLocked_ind());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		
		bean.setParameters(parameters);
		bean.setSearch_ind(true);
		
		if(StringUtils.isNotEmpty(page)) {
			String[] pages = page.split(",");
			if(StringUtils.isNotEmpty(pages[0].trim())) {
				bean.setCurrent_page(Integer.valueOf(pages[0].trim()));
			}
		}
		
		Map<String, String> ordering = new HashMap<String, String>();
		ordering.put("user_name", TConstant.SORTING_ASC);
		ordering.put("last_upd_date", TConstant.SORTING_DESC);
		bean.setOrdering(ordering);
		List<Object> list = PagingUtil.pagingList(bean);
			
		for(Object o : list) {
			for(String name : fields) {
				String methodName = "get" + Character.toUpperCase( name.charAt(0)) +name.substring(1, name.length() );
				Method m  = o.getClass().getMethod(methodName);
				Object field_object = m.invoke(o);
				System.out.print(" methodName | " + field_object.toString());
			}
			
			System.out.println("--------------  ");
		}
		
		
	}

}
