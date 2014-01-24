package com.list.or.action.maint;

import java.sql.Timestamp;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.common.LoginBean;
import com.list.or.bean.maint.RevenueItem;
import com.list.or.bean.maint.VenueType;

public class MaintRevenueItemAction extends BaseAction {
	private static final long serialVersionUID = -5919183852669942365L;
	
	private static final Logger logger = Logger.getLogger(MaintRevenueItemAction.class);
	private RevenueItem revenueItem;
	
	private String revenue_item_code;
	private Integer service_type_code;
	
	public String getRevenue_item_code() {
		return revenue_item_code;
	}

	public void setRevenue_item_code(String revenue_item_code) {
		this.revenue_item_code = revenue_item_code;
	}

	public Integer getService_type_code() {
		return service_type_code;
	}

	public void setService_type_code(Integer service_type_code) {
		this.service_type_code = service_type_code;
	}

	public RevenueItem getRevenueItem() {
		return revenueItem;
	}

	public void setRevenueItem(RevenueItem revenueItem) {
		this.revenueItem = revenueItem;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintRevenueItemAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintRevenueItemAction");
		revenueItem = new RevenueItem();
		revenueItem.getOctopusServiceType().setService_type_code(service_type_code);
		if(StringUtils.isNotEmpty(revenue_item_code)){
			revenueItem.setRevenue_item_code(revenue_item_code);
			revenueItem = maintOctopusService.getRevenueItem(revenueItem);
			revenueItem.setMode(Constant.MODE_EDIT);
		}
		putRequsetBean(revenueItem);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintRevenueItemAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		revenueItem.setLast_upd_by(loginBean.getUser_id());
		revenueItem.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(revenueItem.getMode())){
			revenueItem.setCreate_by(loginBean.getUser_id());
			revenueItem.setCreate_date(current_time);
		}
		
//		maintOctopusService.saveVenueType(revenueItem);
		
		revenueItem.setMode(Constant.MODE_EDIT);
		putRequsetBean(revenueItem);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
