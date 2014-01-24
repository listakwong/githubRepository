package net.tak.tag;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.TConstant;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public class BaseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
	}

	protected Object getSessionBean(PageContext pageContext){
		return pageContext.getSession().getAttribute(TConstant.SESSION_BEAN_KEY);
	}
	
	protected Object getRequestBean(PageContext pageContext){
		return pageContext.getRequest().getAttribute(TConstant.REQUEST_BEAN_KEY);
	}
	
	protected Object getRequestPageBean(PageContext pageContext){
		return pageContext.getRequest().getAttribute(TConstant.REQUEST_PAGE_BEAN_KEY);
	}
	
	/*protected String getValue(Object bean, String name) {
		try {
			Object object = PropertyUtils.getProperty(bean, name);
			if(object!=null){
				return object.toString();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}*/
	
	protected Object[] getValue(Object bean,String name) {
		Object[] fields = new Object[2];
		String[] names = name.split("\\.");
		String methodName = "";
		Object field_object = null;
		Object returnType_name = "";
		try {
			if(names.length > 2) {
				methodName = "get" + Character.toUpperCase( names[1].charAt(0)) + names[1].substring(1, names[1].length());
				Method m = bean.getClass().getMethod(methodName);
				field_object = m.invoke(bean);
				
				for(int i = 2 ;i < names.length ; i ++) {
					methodName = "get" + Character.toUpperCase( names[i].charAt(0)) + names[i].substring(1, names[i].length());
					m = field_object.getClass().getMethod(methodName);
					field_object = m.invoke(field_object);
				}
				returnType_name = m.getReturnType().getName();
			} else {
				methodName = "get" + Character.toUpperCase( names[1].charAt(0)) + names[1].substring(1, names[1].length() );
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
	
	protected Object getObjectValue(Object bean, String name) {
		Object object = null;
		try {
			object = PropertyUtils.getProperty(bean, name);
		} catch (Exception e) {
			return null;
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	protected Set<String> getValues(Object bean, String name){
		Set<String> selectedList = new HashSet<String>();
		try {
			Set<Object> selectedKey = (Set<Object>) PropertyUtils.getProperty(bean, name);
			if(selectedKey != null){
				for(Object key : selectedKey){
					selectedList.add(key.toString().trim());
				}
			}
		} catch (Exception e) {
			return selectedList;
		}
		return selectedList;
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
		return setMethodValues(method, param, null);
	}
}
