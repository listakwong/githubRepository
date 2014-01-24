package com.list.or.dao.admin;

import java.util.List;

import com.list.or.bean.admin.SysParameter;

public interface ISysParameterDao {
	
	public List<SysParameter> retrieveSysParameter();
	
	public SysParameter getSysParameterByID(String parameter_id);
	
	public void saveSysParameter(SysParameter sysParameter);
	
	public SysParameter getSysParameter(String parameter_id);

}
