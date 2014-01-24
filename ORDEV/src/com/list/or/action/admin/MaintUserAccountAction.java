package com.list.or.action.admin;

import java.sql.Timestamp;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageDigestUtil;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.common.LoginBean;

public class MaintUserAccountAction extends BaseAction {
	private static final long serialVersionUID = 6625844693803393067L;
	private static final Logger logger = Logger.getLogger(MaintUserAccountAction.class);
	private SysUser sysUser;
	private String user_id;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintUserAccountAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintUserAccountAction");
		sysUser = new SysUser();
		if(StringUtils.isNotEmpty(user_id)){
			sysUser = sysUserService.getSysUser(user_id);
			sysUser.setMode(Constant.MODE_EDIT);
		}
		putRequsetBean(sysUser);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintUserAccountAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		sysUser.setLast_upd_by(loginBean.getUser_id());
		sysUser.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(sysUser.getMode())){
			sysUser.setStatus_code(Constant.USER_STATUS_NEWADD);
			sysUser.setUnsuccessful_attempt(0);
			String password = MessageDigestUtil.genPassword();
			try {
				sysUser.setPassword(MessageDigestUtil.encrypt(password));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			sysUser.setCreate_by(loginBean.getUser_id());
			sysUser.setCreate_date(current_time);
			addActionMessage("your new password is: " + password);
		}
		
		sysUserService.saveSysUser(sysUser);
		
		sysUser.setMode(Constant.MODE_EDIT);
		putRequsetBean(sysUser);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		
		return SUCCESS;
	}

}
