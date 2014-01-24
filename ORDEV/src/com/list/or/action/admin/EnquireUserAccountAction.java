package com.list.or.action.admin;

import java.util.HashMap;
import java.util.Map;

import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.admin.SysUser;

public class EnquireUserAccountAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(EnquireUserAccountAction.class);
	private static final long serialVersionUID = 3798407699838912172L;
	
	private SysUser sysUser;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	protected Class<?> getFormClass() {
		return EnquireUserAccountAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in EnquireUserAccountAction");
		sysUser = new SysUser();
		putRequsetBean(sysUser);
		return INPUT;
	}
	
	public String search() {
		logger.info("Entering search in EnquireUserAccountAction");
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("user_name", sysUser.getUser_name());
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("user_id", sysUser.getUser_id());
		param2.put("active_ind", sysUser.getActive_ind());
		param2.put("locked_ind", sysUser.getLocked_ind());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		
		setPagingBean(parameters,null);
		putRequsetBean(sysUser);
		return SUCCESS;
	}
	
	public String delete() {
		logger.info("Entering delete in EnquireUserAccountAction");
		String[] user_ids = tableTagCheckbox.split(", ");
		try {
			sysUserService.deleteSysUserByIDs(user_ids);
			addActionMessage(MessageUtil.getText("msg.deleteSuccess"));
		} catch (Exception e) {
			addActionError(MessageUtil.getText("errors.deleteFail"));
		}
		sysUser = new SysUser();
		putRequsetBean(sysUser);
		return SUCCESS;
	}

}
