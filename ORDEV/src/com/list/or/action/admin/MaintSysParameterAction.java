package com.list.or.action.admin;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.admin.SysParam;
import com.list.or.bean.common.LoginBean;
import com.opensymphony.xwork2.ActionContext;

public class MaintSysParameterAction extends BaseAction {
	private static final long serialVersionUID = 6625844693803393067L;
	private static final Logger logger = Logger.getLogger(MaintSysParameterAction.class);
	private SysParam sysParam;

	public SysParam getSysParam() {
		return sysParam;
	}

	public void setSysParam(SysParam sysParam) {
		this.sysParam = sysParam;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintSysParameterAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintSysParameterAction");
		sysParam = sysParameterService.getSysParameterValue();
		putRequsetBean(sysParam);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintSysParameterAction");
		
		LoginBean loginBean = (LoginBean) ActionContext.getContext().getSession().get(TConstant.SESSION_USER_KEY);
		loginBean.setLast_upd_date(systemService.retrieveSysTimestamp());
		sysParameterService.saveSysParameter(sysParam,loginBean);
		
		putRequsetBean(sysParam);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
