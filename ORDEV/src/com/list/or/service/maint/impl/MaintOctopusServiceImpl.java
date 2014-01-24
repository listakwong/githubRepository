package com.list.or.service.maint.impl;

import org.apache.log4j.Logger;

import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.MailingList;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.RevenueItem;
import com.list.or.bean.maint.VenueType;
import com.list.or.dao.maint.IMaintOctopusDao;
import com.list.or.service.maint.IMaintOctopusService;

public class MaintOctopusServiceImpl implements IMaintOctopusService {
	private static final Logger logger = Logger.getLogger(MaintOctopusServiceImpl.class);
	
	private IMaintOctopusDao maintOctopusDao;

	public IMaintOctopusDao getMaintOctopusDao() {
		return maintOctopusDao;
	}

	public void setMaintOctopusDao(IMaintOctopusDao maintOctopusDao) {
		this.maintOctopusDao = maintOctopusDao;
	}

	@Override
	public void deleteBankAccountByIDs(String[] account_nos) throws Exception {
		logger.info("Entering deleteBankAccountByIDs in MaintOctopusServiceImpl");
		for(String account_no : account_nos) {
			maintOctopusDao.deleteBankAccountByID(new BankAccount(account_no.trim()));
		}
	}

	@Override
	public BankAccount getBankAccount(String account_no) {
		logger.info("Entering getBankAccount in MaintOctopusServiceImpl");
		return maintOctopusDao.getBankAccount(account_no);
	}

	@Override
	public void deleteVenueTypeByIDs(String[] venue_type_codes)
			throws Exception {
		logger.info("Entering deleteVenueTypeByIDs in MaintOctopusServiceImpl");
		for(String venue_type_code : venue_type_codes) {
			maintOctopusDao.deleteVenueTypeByID(new VenueType(venue_type_code.trim()));
		}
		
	}

	@Override
	public VenueType getVenueType(String venue_type_code) {
		logger.info("Entering getVenueType in MaintOctopusServiceImpl");
		return maintOctopusDao.getVenueType(venue_type_code);
	}

	@Override
	public void saveBankAccount(BankAccount bankAccount) {
		logger.info("Entering saveBankAccount in MaintOctopusServiceImpl");
		maintOctopusDao.saveBankAccount(bankAccount);
		
	}

	@Override
	public void saveVenueType(VenueType venueType) {
		logger.info("Entering saveVenueType in MaintOctopusServiceImpl");
		maintOctopusDao.saveVenueType(venueType);
		
	}

	@Override
	public OctopusServiceType getOctopusServiceType(Integer service_type_code) {
		logger.info("Entering getOctopusServiceType in MaintOctopusServiceImpl");
		return maintOctopusDao.getOctopusServiceType(service_type_code);
	}

	@Override
	public RevenueItem getRevenueItem(RevenueItem revenueItem) {
		logger.info("Entering getRevenueItem in MaintOctopusServiceImpl");
		return maintOctopusDao.getRevenueItem(revenueItem);
	}

	@Override
	public MailingList getMailingList(MailingList mailingList) {
		logger.info("Entering getMailingList in MaintOctopusServiceImpl");
		return maintOctopusDao.getMailingList(mailingList);
	}

}
