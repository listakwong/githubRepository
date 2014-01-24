package net.tak.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import net.tak.bean.common.DropDown;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.list.or.service.common.IDropDownService;

public class DropDownUtil {
	
	private static ApplicationContext ac = null;
	private static IDropDownService dropDownService;
	static  {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		dropDownService = (IDropDownService) ac.getBean("dropDownService");
	}
	
	@SuppressWarnings("unchecked")
	public static List<DropDown> getDropDownList(String method ,Map<String,String> map) {
		method = "retrieve" + Character.toUpperCase( method.charAt(0)) + method.substring(1, method.length() );
		
		try {
			Method m = dropDownService.getClass().getMethod(method, Map.class);
			return (List<DropDown>)m.invoke(dropDownService, map);
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<DropDown> getDropDownList(String method) {
		method = "retrieve" + Character.toUpperCase( method.charAt(0)) + method.substring(1, method.length() );
		try {
			Method m = dropDownService.getClass().getMethod(method);
			return (List<DropDown>)m.invoke(dropDownService);
		} catch (Exception e) {
			return null;
		}
	}
	
}
