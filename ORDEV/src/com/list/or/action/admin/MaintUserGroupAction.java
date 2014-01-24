package com.list.or.action.admin;

import java.sql.Timestamp;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.admin.SysUserGroup;
import com.list.or.bean.common.LoginBean;

public class MaintUserGroupAction extends BaseAction {
	private static final long serialVersionUID = 6625844693803393067L;
	private static final Logger logger = Logger.getLogger(MaintUserGroupAction.class);
	private SysUserGroup sysUserGroup;
	private String user_group_id;

	public String getUser_group_id() {
		return user_group_id;
	}

	public void setUser_group_id(String user_group_id) {
		this.user_group_id = user_group_id;
	}

	public SysUserGroup getSysUserGroup() {
		return sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintUserGroupAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintUserGroupAction");
		sysUserGroup = new SysUserGroup();
		if(StringUtils.isNotEmpty(user_group_id)){
			sysUserGroup = sysUserService.getSysUserGroup(user_group_id);
			sysUserGroup.setMode(Constant.MODE_EDIT);
		}
		putRequsetBean(sysUserGroup);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintUserGroupAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		sysUserGroup.setLast_upd_by(loginBean.getUser_id());
		sysUserGroup.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(sysUserGroup.getMode())){
			sysUserGroup.setCreate_by(loginBean.getUser_id());
			sysUserGroup.setCreate_date(current_time);
		}
		
		sysUserService.saveSysUserGroup(sysUserGroup);
		
		sysUserGroup.setMode(Constant.MODE_EDIT);
		putRequsetBean(sysUserGroup);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
