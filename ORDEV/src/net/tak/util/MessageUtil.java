package net.tak.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.DefaultTextProvider;

public class MessageUtil{
	
	private static final DefaultTextProvider textProvider = new DefaultTextProvider();
	
	public MessageUtil() {
		super();
	}

	public static String getText(String applicationName,String key) {
		return PropertiesManager.getInstance(applicationName).getProperty(key);
	}
	
	public static String getText(String key) {
		if(StringUtils.isNotEmpty(key)){
			return textProvider.getText(key, key);
		}
		return "";
	}
	
	public static String getText(String key, String[] field) {
		return textProvider.getText(key, field);
	}
	
	public static void setMessagesToSession(List<String> msg, ActionContext actionContext) {
		actionContext.getSession().put("message", msg);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getMessagesToSession(ActionContext actionContext) {
		return (List<String>) actionContext.getSession().get("message");
	}
	
	public static void clearMessagesToSession(ActionContext actionContext) {
		actionContext.getSession().remove("message");
	}

}
