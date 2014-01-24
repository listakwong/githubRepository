package com.list.or.action.maint;

import java.sql.Timestamp;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.common.LoginBean;
import com.list.or.bean.maint.VenueType;

public class MaintVenueTypeAction extends BaseAction {
	private static final long serialVersionUID = -6215158526395650737L;
	private static final Logger logger = Logger.getLogger(MaintVenueTypeAction.class);
	private VenueType venueType;
	private String venue_type_code;

	public String getVenue_type_code() {
		return venue_type_code;
	}

	public void setVenue_type_code(String venue_type_code) {
		this.venue_type_code = venue_type_code;
	}

	public VenueType getVenueType() {
		return venueType;
	}

	public void setVenueType(VenueType venueType) {
		this.venueType = venueType;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintVenueTypeAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintVenueTypeAction");
		venueType = new VenueType();
		if(StringUtils.isNotEmpty(venue_type_code)){
			venueType = maintOctopusService.getVenueType(venue_type_code);
			venueType.setMode(Constant.MODE_EDIT);
		}
		putRequsetBean(venueType);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintVenueTypeAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		venueType.setLast_upd_by(loginBean.getUser_id());
		venueType.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(venueType.getMode())){
			venueType.setCreate_by(loginBean.getUser_id());
			venueType.setCreate_date(current_time);
		}
		
		maintOctopusService.saveVenueType(venueType);
		
		venueType.setMode(Constant.MODE_EDIT);
		putRequsetBean(venueType);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
