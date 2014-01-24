package com.list.or.dao.admin;

import com.list.or.bean.admin.LoginUser;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.admin.SysUserGroup;
import com.list.or.bean.common.LoginBean;

public interface ISysUserDao {
	public LoginUser getSysUserForLogin(LoginBean loginBean);

	public SysUser getSysUser(String user_id);
	
	public void saveSysUser(SysUser sysUser);

	public void updateSysPassword(LoginUser loginUser);
	
	public void updateSysUserStatus(LoginBean loginBean);
	
	public void deleteSysUserByID(SysUser sysUser) throws Exception;
	
	public SysUserGroup getSysUserGroup(String user_group_id);
	
	public void saveSysUserGroup(SysUserGroup sysUserGroup);
	
	public void deleteSysUserGroupByID(SysUserGroup sysUserGroup) throws Exception;
	
}
