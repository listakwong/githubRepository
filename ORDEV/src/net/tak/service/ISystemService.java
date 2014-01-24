package net.tak.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import net.tak.bean.system.SysMenu;

import com.list.or.bean.common.LoginBean;

public interface ISystemService {
	public Date retrieveSysdate();

	public Timestamp retrieveSysTimestamp();

	public Date retrieveMonthLastDay(Integer pMonthOffset);

	public Date retrieveMonthFirstDay(Integer pMonthOffset);
	
	public Map<Integer,List<SysMenu>> retrieveSysMenu(LoginBean loginBean);
	
}
