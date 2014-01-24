package com.list.or.service.common;

import java.util.List;
import java.util.Map;

import net.tak.bean.common.DropDown;

public interface IDropDownService {
	public List<DropDown> retrieveSyscode(Map<String,String> map);
	
	public List<DropDown> retrieveGroups();
	
	public List<DropDown> retrieveOctopuses();
	
	public List<DropDown> retrieveFunctions();
	
	public List<DropDown> retrieveVenue();
	
	public List<DropDown> retrieveAccount();
	
}
