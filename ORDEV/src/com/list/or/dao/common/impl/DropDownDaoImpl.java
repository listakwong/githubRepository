package com.list.or.dao.common.impl;

import java.util.List;
import java.util.Map;

import net.tak.bean.system.SysCode;
import net.tak.bean.system.SysFunctionRight;
import net.tak.dao.BaseDao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.list.or.Constant;
import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.VenueType;
import com.list.or.dao.common.IDropDownDao;


public class DropDownDaoImpl extends BaseDao implements IDropDownDao {
	private static final Logger logger = Logger	.getLogger(DropDownDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> retrieveSysusergroup() {
		logger.info("Entering retrieveSysusergroup in DropDownDaoImpl");
		StringBuffer hql = new StringBuffer();
		hql.append("select _group.user_group_id as key, _group.user_group_id || ' - ' || _group.user_group_name as value");
		hql.append(" from SysUserGroup as _group ");
		hql.append(" where _group.active_ind = 'Y' ");
		hql.append(" order by _group.user_group_id asc ");
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysCode> retrieveSyscode(Map<String, String> map) {
		logger.info("Entering retrieveSyscodes in DropDownDaoImpl");
		Criteria criteria = getSession().createCriteria(SysCode.class).createAlias("sysCodeType", "codeType");
		if(map != null){
			for(String key : map.keySet()) {
				if("code_type_id".equals(key)){
					criteria.add(Restrictions.eq("codeType."+key, map.get(key)));
				}else {
					criteria.add(Restrictions.eq(key, map.get(key)));
				}
			}
		}
		
		criteria.addOrder(Order.asc("display_seq_no"));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OctopusServiceType> retrieveOctopusServiceType() {
		logger.info("Entering retrieveOctopusServiceType in DropDownDaoImpl");
		Criteria criteria = getSession().createCriteria(OctopusServiceType.class);
		criteria.add(Restrictions.eq("active_ind", Constant.YES));
		criteria.addOrder(Order.asc("service_type_code"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysFunctionRight> retrieveSysFunctionRight() {
		logger.info("Entering retrieveSysFunctionRight in DropDownDaoImpl");
		Criteria criteria = getSession().createCriteria(SysFunctionRight.class);
		criteria.addOrder(Order.asc("func_right_id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenueType> retrieveVenueType() {
		logger.info("Entering retrieveVenueType in DropDownDaoImpl");
		Criteria criteria = getSession().createCriteria(VenueType.class);
		criteria.add(Restrictions.eq("active_ind", "Y"));
		criteria.addOrder(Order.asc("venue_type_code"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> retrieveBankAccount() {
		logger.info("Entering retrieveBankAccount in DropDownDaoImpl");
		Criteria criteria = getSession().createCriteria(BankAccount.class);
		criteria.add(Restrictions.eq("active_ind", "Y"));
		criteria.addOrder(Order.asc("account_no"));
		return criteria.list();
	}

}
