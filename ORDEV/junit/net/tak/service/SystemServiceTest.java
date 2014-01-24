package net.tak.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SystemServiceTest {
	private static ApplicationContext ac = null;
	private static ISystemService systemService;
	
	static {
		String path = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(path);
		systemService = (ISystemService) ac.getBean("systemService");
	}
	
	/*@Test
	public void testRetrieveSysMenus(){
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUser_id("user01");
		List<SysMenus> sysMenusList = systemService.retrieveSysMenus(loginBean);
		
		for(SysMenus sysMenus : sysMenusList){
			System.out.println(sysMenus.getMenu_id() + " | " + sysMenus.getMenu_desc() + " | " + sysMenus.getMenu_level());
			
			forLoog(sysMenus);
		}
		
		
	}
	private void forLoog(SysMenus sysMenus) {
        for(SysMenus child: sysMenus.getChildren()){  
        	forLoog(child);  
        }
	}*/
	
}
