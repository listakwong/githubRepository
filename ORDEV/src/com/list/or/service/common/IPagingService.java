package com.list.or.service.common;

import java.util.List;

import net.tak.bean.common.PageBean;

public interface IPagingService {
//	public List<SysUser> retrievePaginguser(PageBean pageBean);
	
	public List<Object> retrievePagingList(PageBean pageBean) ;
}
