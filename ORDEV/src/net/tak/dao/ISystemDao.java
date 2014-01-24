package net.tak.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.list.or.bean.common.LoginBean;

public interface ISystemDao {
	public Date retrieveSysdate();

	public Timestamp retrieveSysTimestamp();

	public Date retrieveMonthLastDay(Integer pMonthOffset);

	public Date retrieveMonthFirstDay(Integer pMonthOffset);
	
	public List<Object[]> retrieveSysMenu(LoginBean loginBean);
	
}
