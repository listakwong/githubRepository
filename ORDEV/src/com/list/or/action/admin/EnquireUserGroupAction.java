package com.list.or.action.admin;

import java.util.HashMap;
import java.util.Map;

import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.admin.SysUserGroup;

public class EnquireUserGroupAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(EnquireUserGroupAction.class);
	private static final long serialVersionUID = 3798407699838912172L;
	
	private SysUserGroup sysUserGroup;

	public SysUserGroup getSysUserGroup() {
		return sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	@Override
	protected Class<?> getFormClass() {
		return EnquireUserGroupAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in EnquireUserGroupAction");
		sysUserGroup = new SysUserGroup();
		putRequsetBean(sysUserGroup);
		return INPUT;
	}
	
	public String search() {
		logger.info("Entering search in EnquireUserGroupAction");
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("user_group_name", sysUserGroup.getUser_group_name());
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("user_group_id", sysUserGroup.getUser_group_id());
		param2.put("active_ind", sysUserGroup.getActive_ind());
		param2.put("admin_group_ind", sysUserGroup.getAdmin_group_ind());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		
		setPagingBean(parameters,null);
		putRequsetBean(sysUserGroup);
		return SUCCESS;
	}
	
	public String delete() {
		logger.info("Entering delete in EnquireUserGroupAction");
		String[] user_group_ids = tableTagCheckbox.split(", ");
		try {
			sysUserService.deleteSysUserGroupByIDs(user_group_ids);
			addActionMessage(MessageUtil.getText("msg.deleteSuccess"));
		} catch (Exception e) {
			addActionError(MessageUtil.getText("errors.deleteFail"));
		}
		
		sysUserGroup = new SysUserGroup();
		putRequsetBean(sysUserGroup);
		return SUCCESS;
	}

}
