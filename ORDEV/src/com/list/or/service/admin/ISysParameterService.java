package com.list.or.service.admin;

import com.list.or.bean.admin.SysParam;
import com.list.or.bean.common.LoginBean;

public interface ISysParameterService {
	
	public SysParam getSysParameterValue();
	
	public void saveSysParameter(SysParam sysParam,LoginBean loginBean);
}
