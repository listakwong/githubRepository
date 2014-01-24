package com.list.or.action.maint;

import java.util.HashMap;
import java.util.Map;

import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.maint.VenueType;

public class EnquireVenueTypeAction extends BaseAction {

	private static final long serialVersionUID = -7329017336085965815L;

	private static final Logger logger = Logger.getLogger(EnquireVenueTypeAction.class);
	
	private VenueType venueType;

	public VenueType getVenueType() {
		return venueType;
	}

	public void setVenueType(VenueType venueType) {
		this.venueType = venueType;
	}

	@Override
	protected Class<?> getFormClass() {
		return EnquireVenueTypeAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in EnquireVenueTypeAction");
		venueType = new VenueType();
		putRequsetBean(venueType);
		return INPUT;
	}
	
	public String search() {
		logger.info("Entering search in EnquireVenueTypeAction");
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("venue_type_code", venueType.getVenue_type_code());
		param1.put("venue_type_name", venueType.getVenue_type_name());
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("active_ind", venueType.getActive_ind());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		
		setPagingBean(parameters,null);
		putRequsetBean(venueType);
		return SUCCESS;
	}
	
	public String delete() {
		logger.info("Entering delete in EnquireVenueTypeAction");
		String[] venue_type_codes = tableTagCheckbox.split(", ");
		try {
			maintOctopusService.deleteVenueTypeByIDs(venue_type_codes);
			addActionMessage(MessageUtil.getText("msg.deleteSuccess"));
		} catch (Exception e) {
			addActionError(MessageUtil.getText("errors.deleteFail"));
		}
		
		venueType = new VenueType();
		putRequsetBean(venueType);
		return SUCCESS;
	}

}
