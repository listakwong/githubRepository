package net.tak.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import net.tak.dao.BaseDao;
import net.tak.dao.ISystemDao;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.list.or.bean.common.LoginBean;

public class SystemDaoImpl extends BaseDao implements ISystemDao {
	private static final Logger logger = Logger.getLogger(SystemDaoImpl.class);

	@Override
	public Date retrieveSysdate() {
		logger.info("Entering retrieveSysdate in SysUserDaoImpl");
		String sql = "SELECT getSystemDate() FROM DUAL";
		return (Date) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Timestamp retrieveSysTimestamp() {
		logger.info("Entering retrieveSysTimestamp in SysUserDaoImpl");
		String sql = "SELECT getSystemDate() FROM DUAL";
		return (Timestamp) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Date retrieveMonthLastDay(Integer pMonthOffset) {
		logger.info("Entering retrieveMonthLastDay in SysUserDaoImpl");
		String sql = "SELECT LAST_DAY(TRUNC(ADD_MONTHS(getSystemDate(), ?))) FROM DUAL";
		return (Date) getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Date retrieveMonthFirstDay(Integer pMonthOffset) {
		logger.info("Entering retrieveMonthFirstDay in SysUserDaoImpl");
		String sql = "SELECT TRUNC(ADD_MONTHS(LAST_DAY(getSystemDate()), ?-1)+1) FROM DUAL";
		return (Date) getSession().createSQLQuery(sql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> retrieveSysMenu(LoginBean loginBean) {
		logger.info("Entering retrieveSysMenu in SysUserDaoImpl");
		StringBuffer hql = new StringBuffer();
		hql.append(" select distinct menu_.menu_id, menu_.parent_menu_id, menu_.menu_desc, ");
		hql.append(" menu_.menu_path, menu_.menu_level, menu_.seq_no ");
		hql.append(" from SysMenu as menu_ ");
		hql.append(" inner join menu_.sysFunctionRight funRight_ ");
		hql.append(" inner join funRight_.sysUserGroups group_ ");
		hql.append(" inner join group_.sysUsers user_ ");
		hql.append(" where user_.user_id = :user_id ");
		hql.append(" order by menu_.menu_level asc, menu_.seq_no asc ");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("user_id", loginBean.getUser_id());
		
		List<Object[]> list = query.list();
		return list;
	}

}
