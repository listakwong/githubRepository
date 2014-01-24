package com.list.or.service.admin.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.security.auth.login.LoginException;

import net.tak.bean.system.SysFunctionRight;
import net.tak.dao.ISystemDao;
import net.tak.util.CalendarUtil;
import net.tak.util.MessageDigestUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.admin.LoginUser;
import com.list.or.bean.admin.SysParameter;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.admin.SysUserGroup;
import com.list.or.bean.common.LoginBean;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.dao.admin.ISysParameterDao;
import com.list.or.dao.admin.ISysUserDao;
import com.list.or.service.admin.ISysUserService;

public class SysUserServiceImpl implements ISysUserService {
	private static final Logger logger = Logger.getLogger(SysUserServiceImpl.class);
	private ISysUserDao sysUserDao;
	private ISystemDao systemDao;
	private ISysParameterDao sysParameterDao;
	
	public ISysParameterDao getSysParameterDao() {
		return sysParameterDao;
	}
	public void setSysParameterDao(ISysParameterDao sysParameterDao) {
		this.sysParameterDao = sysParameterDao;
	}

	public ISysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(ISysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public ISystemDao getSystemDao() {
		return systemDao;
	}

	public void setSystemDao(ISystemDao systemDao) {
		this.systemDao = systemDao;
	}

	@Override
	public SysUser validateUser(LoginBean pLoginBean) throws Exception{
		logger.info("Entering ValidateUser in SysUserBoImpl");
		boolean dbSuccess = dbValidateUser(pLoginBean);
		
		if(dbSuccess) {
			return adAuthenticate(pLoginBean);
		}else {
			logger.info("No such user");
			throw new LoginException("No such user");
		}
	}
	
	private boolean dbValidateUser(LoginBean loginUser){
		logger.info("Entering dbValidateUser in SysUserBoImpl");
		LoginUser lvLoginUser = sysUserDao.getSysUserForLogin(loginUser);
		return lvLoginUser != null;
	}
	
	private SysUser adAuthenticate(LoginBean loginUser) throws LoginException, NoSuchAlgorithmException, UnsupportedEncodingException{
		logger.info("Entering adAuthenticate in SysUserBoImpl");
		SysUser lvSysUser = this.getSysUser(loginUser.getUser_id());
		if (! Constant.YES.equals(lvSysUser.getActive_ind())){
			logger.info("Account expired");
			throw new LoginException("Account expired");
		}
		if(Constant.YES.equals(lvSysUser.getLocked_ind())) {
			logger.info("Account locked");
			throw new LoginException("Account locked");
		}
		
		if(StringUtils.isEmpty(lvSysUser.getPassword())) {
			logger.info("Empty password is not allowed");
			throw new LoginException("Empty password is not allowed");
		}
		
		String lvEncryPassword = MessageDigestUtil.encrypt(loginUser.getPassword());
		if(!lvEncryPassword.equals(lvSysUser.getPassword())) {
			logger.info("Wrong Password");
			throw new LoginException("Wrong Password");
		}
		
		lvSysUser.setStatus_code("LI");
		lvSysUser.setLast_upd_by("system");
		lvSysUser.setLast_upd_date(systemDao.retrieveSysTimestamp());
		return lvSysUser;
	}

	@Override
	public void updateSysPassword(LoginUser loginUser) {
		logger.info("Entering updateSysPassword in SysUserBoImpl");
		sysUserDao.updateSysPassword(loginUser);
	}

	@Override
	public void updateSysUserStatus(LoginBean loginBean) {
		logger.info("Entering updateSysUserStatus in SysUserBoImpl");
		sysUserDao.updateSysUserStatus(loginBean);
	}

	@Override
	public void deleteSysUserByIDs(String[] user_ids) throws Exception {
		logger.info("Entering deleteSysUserByIDs in SysUserBoImpl");
		for(String user_id : user_ids) {
			sysUserDao.deleteSysUserByID(new SysUser(user_id.trim()));
		}
	}

	@Override
	public SysUser getSysUser(String user_id) {
		logger.info("Entering getSysUser in SysUserBoImpl");
		SysUser sysuser = sysUserDao.getSysUser(user_id);
		SysParameter sysParameter = sysParameterDao.getSysParameterByID("CHG_PW_DAYS");
		if(sysuser != null) {
			Timestamp pwd_expiry_date = sysuser.getLast_chg_pwd_date();
			int parameter_value = Integer.valueOf(StringUtils.isNotEmpty(sysParameter.getParameter_value()) ? sysParameter.getParameter_value() : "0" );
			sysuser.setPwd_expiry_date(CalendarUtil.minusTimestamp(pwd_expiry_date, Calendar.DATE, parameter_value));
		}
		
		for(SysUserGroup group : sysuser.getSysUserGroups()) {
			sysuser.getGroups().add(group.getUser_group_id());
		}
		
		return sysuser;
	}
	@Override
	public SysUserGroup getSysUserGroup(String user_group_id) {
		logger.info("Entering getSysUserGroup in SysUserBoImpl");
		SysUserGroup sysUserGroup = sysUserDao.getSysUserGroup(user_group_id);
		
		for(OctopusServiceType octopus : sysUserGroup.getOctopusServiceTypes()) {
			sysUserGroup.getOctopuses().add(octopus.getService_type_code().toString());
		}
		
		for(SysFunctionRight functions : sysUserGroup.getSysFunctionRights()) {
			sysUserGroup.getFunctions().add(functions.getFunc_right_id());
		}
		
		return sysUserDao.getSysUserGroup(user_group_id);
	}
	@Override
	public void deleteSysUserGroupByIDs(String[] user_group_ids)
			throws Exception {
		logger.info("Entering deleteSysUserGroupByIDs in SysUserBoImpl");
		for(String user_group_id : user_group_ids) {
			sysUserDao.deleteSysUserGroupByID(new SysUserGroup(user_group_id.trim()));
		}
	}
	@Override
	public void saveSysUser(SysUser sysUser) {
		logger.info("Entering saveSysUser in SysUserBoImpl");
		sysUserDao.saveSysUser(sysUser);
	}
	
	@Override
	public void saveSysUserGroup(SysUserGroup sysUserGroup) {
		logger.info("Entering saveSysUserGroup in SysUserBoImpl");
		sysUserDao.saveSysUserGroup(sysUserGroup);
	}

}
