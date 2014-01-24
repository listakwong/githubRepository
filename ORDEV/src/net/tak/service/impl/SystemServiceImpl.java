package net.tak.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tak.TConstant;
import net.tak.bean.system.SysMenu;
import net.tak.dao.ISystemDao;
import net.tak.service.ISystemService;

import org.apache.log4j.Logger;

import com.list.or.bean.common.LoginBean;

public class SystemServiceImpl implements ISystemService {
	private static final Logger logger = Logger.getLogger(SystemServiceImpl.class);
	private ISystemDao systemDao;
	public ISystemDao getSystemDao() {
		return systemDao;
	}

	public void setSystemDao(ISystemDao systemDao) {
		this.systemDao = systemDao;
	}

	@Override
	public Date retrieveSysdate() {
		logger.info("Entering retrieveSysdate in SystemServiceImpl");
		return systemDao.retrieveSysdate();
	}

	@Override
	public Timestamp retrieveSysTimestamp() {
		logger.info("Entering retrieveSysTimestamp in SystemServiceImpl");
		return systemDao.retrieveSysTimestamp();
	}

	@Override
	public Date retrieveMonthLastDay(Integer pMonthOffset) {
		logger.info("Entering retrieveMonthLastDay in SystemServiceImpl");
		return systemDao.retrieveMonthLastDay(pMonthOffset);
	}

	@Override
	public Date retrieveMonthFirstDay(Integer pMonthOffset) {
		logger.info("Entering retrieveMonthFirstDay in SystemServiceImpl");
		return systemDao.retrieveMonthFirstDay(pMonthOffset);
	}

	@Override
	public Map<Integer,List<SysMenu>> retrieveSysMenu(LoginBean loginBean) {
		logger.info("Entering retrieveSysMenu in SystemServiceImpl");
		
		List<Object[]> list = systemDao.retrieveSysMenu(loginBean);
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		SysMenu sysMenu = null;
		Map<Integer,List<SysMenu>> map = new HashMap<Integer,List<SysMenu>>();
		Integer level = 0;
		for (Object[] o : list) {
			sysMenu = new SysMenu();
			sysMenu.setMenu_id(Integer.valueOf(o[0].toString()));
			sysMenu.setParent_menu_id(Integer.valueOf(o[1].toString()));
			sysMenu.setMenu_desc(o[2].toString());
			sysMenu.setMenu_path(o[3]!=null ? o[3].toString(): TConstant.EMPTY);
			sysMenu.setMenu_level(Integer.valueOf(o[4].toString()));
			sysMenu.setSeq_no(Integer.valueOf(o[5].toString()));
			
			if(sysMenu.getMenu_level() == level) {
				menuList.add(sysMenu);
			} else {
				map.put(level, menuList);
				menuList = new ArrayList<SysMenu>();
				menuList.add(sysMenu);
				level ++;
			}
		}
		map.put(level, menuList);
		return map;
	}

}
