package com.list.or.service.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.tak.bean.common.DropDown;
import net.tak.bean.system.SysCode;
import net.tak.bean.system.SysFunctionRight;

import org.apache.log4j.Logger;

import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.VenueType;
import com.list.or.dao.common.IDropDownDao;
import com.list.or.service.common.IDropDownService;

public class DropDownServiceImpl implements IDropDownService {
	private static final Logger logger = Logger	.getLogger(DropDownServiceImpl.class);
	
	private IDropDownDao dropDownDao;
	public IDropDownDao getDropDownDao() {
		return dropDownDao;
	}
	public void setDropDownDao(IDropDownDao dropDownDao) {
		this.dropDownDao = dropDownDao;
	}
	
	@Override
	public List<DropDown> retrieveGroups() {
		logger.info("Entering retrieveGroups in DropDownServiceImpl");
		List<Object[]> list = dropDownDao.retrieveSysusergroup();
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		for(Object[] o : list) {
			DropDown dropDown = new DropDown();
			dropDown.setKey(o[0].toString());
			dropDown.setValue(o[1].toString());
			dropDownList.add(dropDown);
		}
		return dropDownList;
	}
	
	@Override
	public List<DropDown> retrieveSyscode(Map<String, String> map) {
		logger.info("Entering retrieveSyscode in DropDownServiceImpl");
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		List<SysCode> list = dropDownDao.retrieveSyscode(map);
		for(SysCode code : list) {
			
			DropDown dropDown = new DropDown();
			dropDown.setKey(code.getCode_id());
			dropDown.setValue(code.getCode_desc());
			dropDown.setType(code.getSysCodeType().getCode_type_id());
			dropDownList.add(dropDown);
		}
		return dropDownList;
	}
	@Override
	public List<DropDown> retrieveOctopuses() {
		logger.info("Entering retrieveOctopuses in DropDownServiceImpl");
		List<OctopusServiceType> list = dropDownDao.retrieveOctopusServiceType();
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		
		for(OctopusServiceType octopus : list) {
			DropDown dropDown = new DropDown();
			dropDown.setKey(octopus.getService_type_code().toString());
			dropDown.setValue(octopus.getService_type_name());
			
			dropDownList.add(dropDown);
		}
		
		return dropDownList;
	}
	@Override
	public List<DropDown> retrieveFunctions() {
		logger.info("Entering retrieveFunction in DropDownServiceImpl");
		List<SysFunctionRight> list = dropDownDao.retrieveSysFunctionRight();
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		for(SysFunctionRight function : list) {
			DropDown dropDown = new DropDown();
			dropDown.setKey(function.getFunc_right_id());
			dropDown.setValue(function.getFunc_right_desc());
			dropDownList.add(dropDown);
		}
		
		return dropDownList;
	}
	@Override
	public List<DropDown> retrieveVenue() {
		logger.info("Entering retrieveVenue in DropDownServiceImpl");
		List<VenueType> list = dropDownDao.retrieveVenueType();
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		for(VenueType venue : list) {
			DropDown dropDown = new DropDown();
			dropDown.setKey(venue.getVenue_type_code());
			dropDown.setValue(venue.getVenue_type_name());
			dropDownList.add(dropDown);
		}
		
		return dropDownList;
	}
	@Override
	public List<DropDown> retrieveAccount() {
		logger.info("Entering retrieveAccount in DropDownServiceImpl");
		List<BankAccount> list = dropDownDao.retrieveBankAccount();
		List<DropDown> dropDownList = new ArrayList<DropDown>();
		for(BankAccount account : list) {
			DropDown dropDown = new DropDown();
			dropDown.setKey(account.getAccount_no());
			dropDown.setValue(account.getAccount_name());
			dropDownList.add(dropDown);
		}
		
		return dropDownList;
	}

}
