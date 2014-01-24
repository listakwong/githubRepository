package com.list.or.action;

import java.util.Date;

import org.apache.log4j.Logger;

import net.tak.action.BaseAction;
import net.tak.bean.common.TemplateBean;

public class TemplateUIAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(TemplateUIAction.class);
	private static final long serialVersionUID = -3287574080365780804L;
	private TemplateBean bean;
	
	public TemplateBean getBean() {
		return bean;
	}

	public void setBean(TemplateBean bean) {
		this.bean = bean;
	}

	@Override
	protected Class<?> getFormClass() {
		return TemplateUIAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in TemplateUIAction");
		bean = new TemplateBean();
		bean.setDatepicker_c(new Date());
		bean.setSelect_a("Y");
		bean.setSelect_b("N");
		bean.setField_a("aaaaa");
		bean.setCheckbox_a("Y");
		bean.setDatepicker_a("2014-01-01");
		bean.setDatepicker_b("2014-01-20");
		bean.setPassword_a("11111");
		bean.setRadio_a("Y");
		bean.setSysUserGroups("ADMIN,CNT,USER");
//		putSessionBean(bean);
		
		return INPUT;
	}
}
