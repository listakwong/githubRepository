package com.list.or.action.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageDigestUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.bean.admin.LoginUser;
import com.list.or.bean.common.LoginBean;
import com.opensymphony.xwork2.ActionContext;

public class ChangePasswordAction extends BaseAction {
	private static final long serialVersionUID = 1597029706388132071L;
	private static final Logger logger = Logger.getLogger(ChangePasswordAction.class);
	
	private LoginBean loginBean;
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	@Override
	protected Class<?> getFormClass() {
		return ChangePasswordAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in ChangePasswordAction");
		loginBean = new LoginBean();
		putSessionBean(loginBean);
		return INPUT;
	}
	
	public String change() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		logger.info("Entering change in ChangePasswordAction");
		LoginBean login = (LoginBean) ActionContext.getContext().getSession().get(TConstant.SESSION_USER_KEY);
		String password = loginBean.getPassword();
		String repassword = loginBean.getRepassword();
		String old_password = loginBean.getOld_password();
		Boolean flag_ind = true;
		
		if(StringUtils.isEmpty(old_password)) {
			addActionError(getText("errors.required",new String[]{getText("label.common.oldPassword")}));
			flag_ind = false;
		}else if(!MessageDigestUtil.encrypt(old_password).equals(login.getPassword())) {
			addActionError(getText("errors.common.changePassword.matchExistingPassword"));
			flag_ind = false;
		}

		if(StringUtils.isEmpty(password)) {
			addActionError(getText("errors.required",new String[]{getText("label.common.newPassword")}));
			flag_ind = false;
		}else if(old_password.equals(password)) {
			addActionError(getText("errors.common.changePassword.newPasswordSameAsOld"));
			flag_ind = false;
		} 
		if(StringUtils.isEmpty(repassword)) {
			addActionError(getText("errors.required",new String[]{getText("label.common.reEnterNewPassword")}));
			flag_ind = false;
		}else if(!repassword.equals(password)) {
			addActionError(getText("errors.common.changePassword.newPasswordNotMatch"));
			flag_ind = false;
		}
		if(flag_ind) {
			LoginUser loginUser = new LoginUser();
			loginUser.setUser_id(login.getUser_id());
			loginUser.setUser_name(login.getUser_name());
			loginUser.setPassword(MessageDigestUtil.encrypt(password));
			sysUserService.updateSysPassword(loginUser);
			addActionMessage(getText("msg.saveSuccess"));
		}
		return SUCCESS;
	}

}
