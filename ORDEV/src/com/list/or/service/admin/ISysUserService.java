package com.list.or.service.admin;

import com.list.or.bean.admin.LoginUser;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.admin.SysUserGroup;
import com.list.or.bean.common.LoginBean;

public interface ISysUserService {
	public SysUser validateUser(LoginBean loginBean) throws Exception;

	public void updateSysPassword(LoginUser pLoginUser);
	
	public void updateSysUserStatus(LoginBean loginBean);
	
	public void deleteSysUserByIDs(String[] user_ids) throws Exception;
	
	public SysUser getSysUser(String user_id);
	
	public void saveSysUser(SysUser sysUser);
	
	public SysUserGroup getSysUserGroup(String user_group_id);
	
	public void deleteSysUserGroupByIDs(String[] user_group_ids) throws Exception;
	
	public void saveSysUserGroup(SysUserGroup sysUserGroup);
}
