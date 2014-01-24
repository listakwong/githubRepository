package com.list.or.action.common;

import net.tak.action.BaseAction;

import org.apache.log4j.Logger;

import com.list.or.bean.common.LoginBean;

public class SiteMapAction extends BaseAction {
	private static final long serialVersionUID = 1527955007410845863L;
	private static final Logger logger = Logger.getLogger(SiteMapAction.class);
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	@Override
	protected Class<?> getFormClass() {
		return SiteMapAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in SiteMapAction");
		loginBean = new LoginBean();
		putSessionBean(loginBean);
		return INPUT;
	}

}
