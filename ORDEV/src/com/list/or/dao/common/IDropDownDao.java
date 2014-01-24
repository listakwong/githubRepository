package com.list.or.dao.common;

import java.util.List;
import java.util.Map;

import net.tak.bean.system.SysCode;
import net.tak.bean.system.SysFunctionRight;

import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.VenueType;

public interface IDropDownDao {
	public List<SysCode> retrieveSyscode(Map<String,String> map);
	
	public List<Object[]> retrieveSysusergroup();
	
	public List<OctopusServiceType> retrieveOctopusServiceType();
	
	public List<SysFunctionRight> retrieveSysFunctionRight();
	
	public List<VenueType> retrieveVenueType();
	
	public List<BankAccount> retrieveBankAccount();
	
}
