package com.list.or.service.admin.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import com.list.or.bean.admin.SysParameter;
import com.list.or.bean.admin.SysParam;
import com.list.or.bean.common.LoginBean;
import com.list.or.dao.admin.ISysParameterDao;
import com.list.or.service.admin.ISysParameterService;

public class SysParameterServiceImpl implements ISysParameterService {
	private static final Logger logger = Logger.getLogger(SysParameterServiceImpl.class);
	
	private ISysParameterDao sysParameterDao;
	public ISysParameterDao getSysParameterDao() {
		return sysParameterDao;
	}
	public void setSysParameterDao(ISysParameterDao sysParameterDao) {
		this.sysParameterDao = sysParameterDao;
	}
	@Override
	public SysParam getSysParameterValue() {
		logger.info("Entering getSysParameterValue in SysParameterServiceImpl");
		SysParam sysParam = new SysParam();
		List<SysParameter> list = sysParameterDao.retrieveSysParameter();
		
		for(SysParameter sysParameter : list){
			String parameter_id = sysParameter.getParameter_id();
			String parameter_value = sysParameter.getParameter_value();
			String method = "set" + Character.toUpperCase( parameter_id.charAt(0)) + parameter_id.substring(1, parameter_id.length() ).toLowerCase();
			Method m;
			try {
				m = sysParam.getClass().getMethod(method,String.class);
				m.invoke(sysParam, parameter_value);
			} catch (Exception e) {
				continue;
			}
		}
		
		return sysParam;
	}
	@Override
	public void saveSysParameter(SysParam sysParam,LoginBean loginBean) {
		logger.info("Entering saveSysParameter in SysParameterServiceImpl");
		Field[] fields = sysParam.getClass().getDeclaredFields();
		
		for(Field field : fields) {
			try {
				SysParameter sysParameter = new SysParameter();
				String field_name = field.getName();
				String methodName = "get" + Character.toUpperCase( field_name.charAt(0)) + field_name.substring(1, field_name.length() );
				sysParameter = sysParameterDao.getSysParameter(field_name.toUpperCase());
				
				Method m = sysParam.getClass().getDeclaredMethod(methodName);
				
				String parameter_value = (String) m.invoke(sysParam);
				
				sysParameter.setParameter_value(parameter_value);
				sysParameter.setLast_upd_by(loginBean.getUser_id());
				sysParameter.setLast_upd_date(loginBean.getLast_upd_date());
			} catch (Exception e) {
				continue;
			}
		}
	}

}
