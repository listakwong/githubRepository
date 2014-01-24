package com.list.or.dao.maint.impl;

import java.util.List;

import net.tak.dao.BaseDao;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.MailingList;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.RevenueItem;
import com.list.or.bean.maint.VenueType;
import com.list.or.dao.maint.IMaintOctopusDao;

public class MaintOctopusDaoImpl extends BaseDao implements IMaintOctopusDao {
	private static final Logger logger = Logger.getLogger(MaintOctopusDaoImpl.class);

	@Override
	public void deleteBankAccountByID(BankAccount bankAccount) throws Exception {
		logger.info("Entering deleteBankAccountByID in MaintOctopusDaoImpl");
		getSession().delete(bankAccount);
	}

	@Override
	public BankAccount getBankAccount(String account_no) {
		logger.info("Entering getBankAccount in MaintOctopusDaoImpl");
		return (BankAccount) getSession().get(BankAccount.class, account_no);
	}

	@Override
	public void deleteVenueTypeByID(VenueType venueType) throws Exception {
		logger.info("Entering deleteVenueTypeByID in MaintOctopusDaoImpl");
		getSession().delete(venueType);
		
	}

	@Override
	public VenueType getVenueType(String venue_type_code) {
		logger.info("Entering getVenueType in MaintOctopusDaoImpl");
		return (VenueType) getSession().get(VenueType.class, venue_type_code);
	}

	@Override
	public void saveBankAccount(BankAccount bankAccount) {
		logger.info("Entering saveBankAccount in MaintOctopusDaoImpl");
		getSession().saveOrUpdate(bankAccount);
	}

	@Override
	public void saveVenueType(VenueType venueType) {
		logger.info("Entering saveVenueType in MaintOctopusDaoImpl");
		getSession().saveOrUpdate(venueType);
	}

	@Override
	public OctopusServiceType getOctopusServiceType(Integer service_type_code) {
		logger.info("Entering getOctopusServiceType in MaintOctopusDaoImpl");
		return (OctopusServiceType) getSession().get(OctopusServiceType.class, service_type_code);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RevenueItem getRevenueItem(RevenueItem revenueItem) {
		logger.info("Entering getRevenueItem in MaintOctopusDaoImpl");
		StringBuffer buffer = new StringBuffer();
		buffer.append(" from RevenueItem ri ");
		buffer.append(" where ri.revenue_item_code= :revenue_item_code ");
		buffer.append(" and ri.octopusServiceType.service_type_code = :service_type_code ");
		Query query = getSession().createQuery(buffer.toString());
		query.setParameter("revenue_item_code", revenueItem.getRevenue_item_code());
		query.setParameter("service_type_code", revenueItem.getOctopusServiceType().getService_type_code());
		
		List<RevenueItem> list = query.list();
		if(list != null) 
			return list.get(0);
		return new RevenueItem();
	}

	@SuppressWarnings("unchecked")
	@Override
	public MailingList getMailingList(MailingList mailingList) {
		logger.info("Entering getMailingList in MaintOctopusDaoImpl");
		StringBuffer buffer = new StringBuffer();
		buffer.append(" from MailingList ml ");
		buffer.append(" where ml.mail_id = :mail_id ");
		buffer.append(" and ml.octopusServiceType.service_type_code = :service_type_code ");
		Query query = getSession().createQuery(buffer.toString());
		query.setParameter("mail_id", mailingList.getMail_id());
		query.setParameter("service_type_code", mailingList.getOctopusServiceType().getService_type_code());
		
		List<MailingList> list = query.list();
		if(list != null) 
			return list.get(0);
		return new MailingList();
	}

}
