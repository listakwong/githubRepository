package com.list.or.action.maint;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.common.LoginBean;
import com.list.or.bean.maint.OctopusServiceType;

public class MaintOctopusServiceAction extends BaseAction {
	
	private static final long serialVersionUID = 2388652016228529967L;
	private static final Logger logger = Logger.getLogger(MaintOctopusServiceAction.class);
	private OctopusServiceType octopusServiceType;
	private Integer service_type_code;

	public OctopusServiceType getOctopusServiceType() {
		return octopusServiceType;
	}

	public void setOctopusServiceType(OctopusServiceType octopusServiceType) {
		this.octopusServiceType = octopusServiceType;
	}

	public Integer getService_type_code() {
		return service_type_code;
	}

	public void setService_type_code(Integer service_type_code) {
		this.service_type_code = service_type_code;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintOctopusServiceAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintVenueTypeAction");
		octopusServiceType = new OctopusServiceType();
		if(service_type_code != null){
			octopusServiceType = maintOctopusService.getOctopusServiceType(service_type_code);
			octopusServiceType.setMode(Constant.MODE_EDIT);
		
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("octopusServiceType.service_type_code", octopusServiceType.getService_type_code());
			
			Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
			parameters.put("eq", param);
			
			setPagingBean2(parameters, null,Constant.REQUEST_PAGE_BEAN_KEY_1);
			
			setPagingBean2(parameters, null,Constant.REQUEST_PAGE_BEAN_KEY_2);
		}
		//setPagingBean(parameters,null);
		putRequsetBean(octopusServiceType);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintVenueTypeAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		octopusServiceType.setLast_upd_by(loginBean.getUser_id());
		octopusServiceType.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(octopusServiceType.getMode())){
			octopusServiceType.setCreate_by(loginBean.getUser_id());
			octopusServiceType.setCreate_date(current_time);
		}
		
//		maintOctopusService.saveVenueType(venueType);
		
		octopusServiceType.setMode(Constant.MODE_EDIT);
		putRequsetBean(octopusServiceType);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
