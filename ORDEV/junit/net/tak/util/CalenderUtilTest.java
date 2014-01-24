package net.tak.util;

import java.sql.Timestamp;
import java.util.Calendar;

import net.tak.service.ISystemService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalenderUtilTest {
	private static ApplicationContext ac = null;
	private static ISystemService systemService;
	
	static {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		systemService = (ISystemService) ac.getBean("systemService");
	}
	@Test
	public void testPlusTimestamp() {
		Timestamp time = systemService.retrieveSysTimestamp();
		
		System.out.println(CalendarUtil.plusTimestamp(time, Calendar.DATE, 16));
		
		System.out.println(CalendarUtil.minusTimestamp(time, Calendar.DATE, 14));
		
	}
}
