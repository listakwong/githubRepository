package com.list.or.service.common.impl;

import java.util.List;

import net.tak.bean.common.PageBean;

import org.apache.log4j.Logger;

import com.list.or.dao.common.IPagingDao;
import com.list.or.service.common.IPagingService;

public class PagingServiceImpl implements IPagingService {
	private static final Logger logger = Logger.getLogger(PagingServiceImpl.class);

	private IPagingDao pagingDao;

	public IPagingDao getPagingDao() {
		return pagingDao;
	}

	public void setPagingDao(IPagingDao pagingDao) {
		this.pagingDao = pagingDao;
	}

	/*@Override
	public List<SysUser> retrievePaginguser(PageBean pageBean) {
		logger.info("Entering retrievePaginguser in PagingServiceImpl");
		return pagingDao.retrievePaginguser(pageBean);
	}
*/
	@Override
	public List<Object> retrievePagingList(PageBean pageBean) {
		logger.info("Entering retrievePagingList in PagingServiceImpl");
		return pagingDao.retrievePagingList(pageBean);
	}

}
