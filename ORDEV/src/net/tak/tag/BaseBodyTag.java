package net.tak.tag;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.tak.TConstant;

import org.apache.commons.lang3.StringUtils;

public class BaseBodyTag extends BodyTagSupport {

	private static final long serialVersionUID = 7970657755606546334L;
	
	protected Object getRequestBean(PageContext pageContext){
		return pageContext.getRequest().getAttribute(TConstant.REQUEST_BEAN_KEY);
	}
	
	protected Object getRequestPageBean(PageContext pageContext){
		return pageContext.getRequest().getAttribute(TConstant.REQUEST_PAGE_BEAN_KEY);
	}
	
	protected Object getRequestPageBean(PageContext pageContext,String requestName2) {
		return pageContext.getRequest().getAttribute(requestName2);
	}
	
	protected Map<String, String> setMethodValues(String method, String param,String value) {
		Map<String, String> map = new HashMap<String,String>();
		
		if("syscode".equals(method.toLowerCase())) {
			map.put("code_type_id", param.toUpperCase());
			if(StringUtils.isNotEmpty(value)){
				map.put("code_id", value);
			}
		}
		
		return map;
	}
	
	protected Map<String, String> setMethodValues(String method, String param) {
		return setMethodValues(method, param, "");
	}
	
	protected Object[] getValuess(Object bean,String name) {
		Object[] fields = new Object[2];
		String[] names = name.split("\\.");
		String methodName = "";
		Object field_object = null;
		String returnType_name = "";
		try {
			if(names.length > 1) {
				methodName = "get" + Character.toUpperCase( names[0].charAt(0)) + names[0].substring(1, names[0].length());
				Method m = bean.getClass().getMethod(methodName);
				field_object = m.invoke(bean);
				
				for(int i = 1 ;i < names.length ; i ++) {
					methodName = "get" + Character.toUpperCase( names[i].charAt(0)) + names[i].substring(1, names[i].length());
					m = field_object.getClass().getMethod(methodName);
					field_object = m.invoke(field_object);
				}
				
				returnType_name = m.getReturnType().getName();
			} else {
				methodName = "get" + Character.toUpperCase( name.charAt(0)) + name.substring(1, name.length() );
				Method m  = bean.getClass().getMethod(methodName);
				field_object = m.invoke(bean);
				returnType_name = m.getReturnType().getName();
			}
		} catch (Exception e) {
			return null;
		}
		
		fields[0] = field_object;
		fields[1] = returnType_name;
		return fields;
	}

}
