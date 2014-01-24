package com.list.or.action;

import net.tak.TConstant;
import net.tak.action.BaseAction;

import org.apache.log4j.Logger;

import com.list.or.bean.common.LoginBean;

public class LogoutAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(LogoutAction.class);
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
		return LogoutAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in LogoutAction");
		
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		if(loginBean != null){
			sysUserService.updateSysUserStatus(loginBean);
		}
		loginBean = new LoginBean();
		removeSession();
		return SUCCESS;
	}

}
