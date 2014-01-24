package com.list.or.dao.admin.impl;

import java.util.List;

import org.apache.log4j.Logger;

import net.tak.dao.BaseDao;

import com.list.or.bean.admin.SysParameter;
import com.list.or.dao.admin.ISysParameterDao;

public class SysParameterDaoImpl extends BaseDao implements ISysParameterDao {
	private static final Logger logger = Logger.getLogger(SysParameterDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysParameter> retrieveSysParameter() {
		logger.info("Entering retrieveSysParameter in SysParameterDaoImpl");
		return (List<SysParameter>) getSession().createCriteria(SysParameter.class).list();
	}

	@Override
	public SysParameter getSysParameterByID(String parameter_id) {
		logger.info("Entering getSysParameterByID in SysParameterDaoImpl");
		return (SysParameter) getSession().get(SysParameter.class, parameter_id);
	}

	@Override
	public void saveSysParameter(SysParameter sysParameter) {
		logger.info("Entering saveSysParameter in SysParameterDaoImpl");
		getSession().save(sysParameter);
	}

	@Override
	public SysParameter getSysParameter(String parameter_id) {
		logger.info("Entering getSysParameter in SysParameterDaoImpl");
		return (SysParameter) getSession().load(SysParameter.class, parameter_id);
	}

}
