package com.list.or.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tak.TConstant;
import net.tak.bean.common.PageBean;
import net.tak.dao.BaseDao;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.list.or.dao.common.IPagingDao;

public class PagingDaoImpl extends BaseDao implements IPagingDao {
	private static final Logger logger = Logger.getLogger(PagingDaoImpl.class);

	/*@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> retrievePaginguser(PageBean pageBean) {
		logger.info("Entering retrieveSysuser in PagingDaoImpl");

		Criteria criteria = getSession().createCriteria(pageBean.getClass_bean());

		Map<String, Map<String, Object>> parameters = pageBean.getParameters();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> ordering = pageBean.getOrdering();
		
		for(String restriction : parameters.keySet()) {
			param = parameters.get(restriction);
			if("like".equals(restriction)) {
				for(String key : param.keySet()){
					if (param.get(key) != null
							&& StringUtils.isNotEmpty(param.get(key).toString())) {
						criteria.add(Restrictions.like(key, "%" + param.get(key) + "%"));
					}
				}
			} else if("eq".equals(restriction)) {
				for(String key : param.keySet()){
					if (param.get(key) != null
							&& StringUtils.isNotEmpty(param.get(key).toString())) {
						criteria.add(Restrictions.eq(key, param.get(key)));
					}
				}
			} else if("ge".equals(restriction)) {
				for(String key : param.keySet()){
					if (param.get(key) != null
							&& StringUtils.isNotEmpty(param.get(key).toString())) {
						criteria.add(Restrictions.ge(key, param.get(key)));
					}
				}
			} else if("le".equals(restriction)) {
				for(String key : param.keySet()){
					if (param.get(key) != null
							&& StringUtils.isNotEmpty(param.get(key).toString())) {
						criteria.add(Restrictions.le(key, param.get(key)));
					}
				}
			}
		}

		for (String key : ordering.keySet()) {
			if(TConstant.SORTING_ASC.equals(ordering.get(key))) {
				criteria.addOrder(Order.asc(key));
			} else if(TConstant.SORTING_DESC.equals(ordering.get(key))) {
				criteria.addOrder(Order.desc(key));
			}
		}

		pageBean.setTotal_record(criteria.list().size());

		criteria.setFirstResult(pageBean.getFrom_record());
		criteria.setMaxResults(pageBean.getRecord_per_page());
		
		return criteria.list();
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> retrievePagingList(PageBean pageBean) {
		logger.info("Entering retrievePagingList in PagingDaoImpl");

		Criteria criteria = getSession().createCriteria(pageBean.getClass_bean());

		Map<String, Map<String, Object>> parameters = pageBean.getParameters();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> ordering = pageBean.getOrdering();
		
		if(parameters != null){
			for(String restriction : parameters.keySet()) {
				param = parameters.get(restriction);
				if("like".equals(restriction)) {
					for(String key : param.keySet()){
						if (param.get(key) != null
								&& StringUtils.isNotEmpty(param.get(key).toString())) {
							criteria.add(Restrictions.like(key, "%" + param.get(key) + "%"));
						}
					}
				} else if("eq".equals(restriction)) {
					for(String key : param.keySet()){
						if (param.get(key) != null
								&& StringUtils.isNotEmpty(param.get(key).toString())) {
							if(!"all".equals(param.get(key).toString())){
								criteria.add(Restrictions.eq(key, param.get(key)));
							}
						}
					}
				} else if("ge".equals(restriction)) {
					for(String key : param.keySet()){
						if (param.get(key) != null
								&& StringUtils.isNotEmpty(param.get(key).toString())) {
							criteria.add(Restrictions.ge(key, param.get(key)));
						}
					}
				} else if("le".equals(restriction)) {
					for(String key : param.keySet()){
						if (param.get(key) != null
								&& StringUtils.isNotEmpty(param.get(key).toString())) {
							criteria.add(Restrictions.le(key, param.get(key)));
						}
					}
				}
			}
		}
		
		if(ordering != null){
			for (String key : ordering.keySet()) {
				if(TConstant.SORTING_ASC.equals(ordering.get(key))) {
					criteria.addOrder(Order.asc(key));
				} else if(TConstant.SORTING_DESC.equals(ordering.get(key))) {
					criteria.addOrder(Order.desc(key));
				}
			}
		}

		pageBean.setTotal_record(criteria.list().size());

		criteria.setFirstResult(pageBean.getFrom_record());
		criteria.setMaxResults(pageBean.getRecord_per_page());
		
		return criteria.list();
	}
	
}
