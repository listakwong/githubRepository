package com.list.or.action.maint;

import java.util.HashMap;
import java.util.Map;

import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.maint.OctopusServiceType;

public class EnquireOctopusServiceAction extends BaseAction {

	private static final long serialVersionUID = -8941952062407988246L;

	private static final Logger logger = Logger.getLogger(EnquireOctopusServiceAction.class);
	
	private OctopusServiceType octopusServiceType;

	public OctopusServiceType getOctopusServiceType() {
		return octopusServiceType;
	}

	public void setOctopusServiceType(OctopusServiceType octopusServiceType) {
		this.octopusServiceType = octopusServiceType;
	}

	@Override
	protected Class<?> getFormClass() {
		return EnquireOctopusServiceAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in EnquireOctopusServiceAction");
		octopusServiceType = new OctopusServiceType();
		putRequsetBean(octopusServiceType);
		return INPUT;
	}
	
	public String search() {
		logger.info("Entering search in EnquireOctopusServiceAction");
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("service_type_name", octopusServiceType.getService_type_name());
		param1.put("tx_fee_user_code", octopusServiceType.getTx_fee_user_code());
		param1.put("contra_user_code", octopusServiceType.getContra_user_code());
		param1.put("deposit_account_user_code", octopusServiceType.getDeposit_account_user_code());
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("active_ind", octopusServiceType.getActive_ind());
		param2.put("service_type_code", octopusServiceType.getService_type_code());
		param2.put("venueType.venue_type_code", octopusServiceType.getVenueType().getVenue_type_code());
		
		Map<String, Object> param3 = new HashMap<String, Object>();
		param3.put("eff_start_date", octopusServiceType.getEff_start_date());
		
		Map<String, Object> param4 = new HashMap<String, Object>();
		param4.put("eff_end_date", octopusServiceType.getEff_end_date());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		parameters.put("ge", param3);
		parameters.put("le", param4);
		
		setPagingBean(parameters,null);
		putRequsetBean(octopusServiceType);
		return SUCCESS;
	}
	
	public String delete() {
		logger.info("Entering delete in EnquireOctopusServiceAction");
		String[] account_nos = tableTagCheckbox.split(", ");
		try {
			maintOctopusService.deleteBankAccountByIDs(account_nos);
			addActionMessage(MessageUtil.getText("msg.deleteSuccess"));
		} catch (Exception e) {
			addActionError(MessageUtil.getText("errors.deleteFail"));
		}
		octopusServiceType = new OctopusServiceType();
		putRequsetBean(octopusServiceType);
		return SUCCESS;
	}

}
