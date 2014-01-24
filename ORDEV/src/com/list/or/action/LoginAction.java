package com.list.or.action;

import net.tak.action.BaseAction;

import org.apache.log4j.Logger;

import com.list.or.bean.admin.SysUser;
import com.list.or.bean.common.LoginBean;

public class LoginAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	private static final long serialVersionUID = 8805436501204343447L;
	
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	@Override
	protected Class<?> getFormClass() {
		return LoginAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in LoginAction");
		loginBean = new LoginBean();
		putRequsetBean(loginBean);
		return INPUT;
	}
	
	public String login() {
		logger.info("Entering login in LoginAction");
		try {
			SysUser sysUser = sysUserService.validateUser(loginBean);
			putRequsetBean(sysUser);
			putSessionUser(sysUser);
			putSessionMenu(loginBean);
		} catch (Exception e) {
			addActionError(e.getMessage());
			putRequsetBean(loginBean);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String logout(){
		logger.info("Entering logout in LoginAction");
		removeSession();
		return "logout";
	}

}
