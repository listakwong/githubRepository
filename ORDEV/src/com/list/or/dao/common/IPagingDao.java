package com.list.or.dao.common;

import java.util.List;

import net.tak.bean.common.PageBean;

public interface IPagingDao {
	
//	public List<SysUser> retrievePaginguser(PageBean pageBean);
	
	public List<Object> retrievePagingList(PageBean pageBean);
	
}
