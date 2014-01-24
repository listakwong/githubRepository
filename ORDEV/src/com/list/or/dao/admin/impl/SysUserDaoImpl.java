package com.list.or.dao.admin.impl;

import net.tak.dao.BaseDao;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.list.or.bean.admin.LoginUser;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.admin.SysUserGroup;
import com.list.or.bean.common.LoginBean;
import com.list.or.dao.admin.ISysUserDao;

public class SysUserDaoImpl extends BaseDao implements ISysUserDao {
	private static final Logger logger = Logger.getLogger(SysUserDaoImpl.class);
	@Override
	public LoginUser getSysUserForLogin(LoginBean pLoginBean) {
		logger.info("Entering getSysUserForLogin in SysUserDaoImpl");
		return (LoginUser) getSession().get(LoginUser.class, pLoginBean.getUser_id());
	}

	@Override
	public SysUser getSysUser(String user_id) {
		logger.info("Entering getSysUser in SysUserDaoImpl");
		return (SysUser) getSession().get(SysUser.class, user_id);
	}

	@Override
	public void updateSysPassword(LoginUser pLoginUser) {
		logger.info("Entering updateSysPassword in SysUserDaoImpl");
		
		getSession().saveOrUpdate(pLoginUser);
	}

	@Override
	public void updateSysUserStatus(LoginBean loginBean) {
		logger.info("Entering updateSysUserStatus in SysUserDaoImpl");
		
		StringBuffer hql = new StringBuffer();
		hql.append("update SysUser set status_code = 'LO', last_upd_by = 'system', last_upd_date = getSystemDate()");
		hql.append(" where user_id = :user_id");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("user_id", loginBean.getUser_id());
		query.executeUpdate();
	}

	@Override
	public void deleteSysUserByID(SysUser sysUser) throws Exception{
		logger.info("Entering deleteSysUserByID in SysUserDaoImpl");
		getSession().delete(sysUser);
	}

	@Override
	public SysUserGroup getSysUserGroup(String user_group_id) {
		logger.info("Entering getSysUserGroup in SysUserDaoImpl");
		return (SysUserGroup) getSession().get(SysUserGroup.class, user_group_id);
	}

	@Override
	public void deleteSysUserGroupByID(SysUserGroup sysUserGroup)
			throws Exception {
		logger.info("Entering deleteSysUserGroupByID in SysUserDaoImpl");
		getSession().delete(sysUserGroup);
	}

	@Override
	public void saveSysUser(SysUser sysUser) {
		logger.info("Entering saveSysUser in SysUserDaoImpl");
		getSession().saveOrUpdate(sysUser);
	}

	@Override
	public void saveSysUserGroup(SysUserGroup sysUserGroup) {
		logger.info("Entering saveSysUserGroup in SysUserDaoImpl");
		getSession().saveOrUpdate(sysUserGroup);
	}

}
