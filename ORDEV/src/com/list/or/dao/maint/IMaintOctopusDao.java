package com.list.or.dao.maint;

import com.list.or.bean.maint.BankAccount;
import com.list.or.bean.maint.MailingList;
import com.list.or.bean.maint.OctopusServiceType;
import com.list.or.bean.maint.RevenueItem;
import com.list.or.bean.maint.VenueType;

public interface IMaintOctopusDao {
	
	public void deleteBankAccountByID(BankAccount bankAccount) throws Exception;
	
	public BankAccount getBankAccount(String account_no);
	
	public void saveBankAccount(BankAccount bankAccount);
	
	public void deleteVenueTypeByID(VenueType venueType) throws Exception;
	
	public VenueType getVenueType(String venue_type_code);
	
	public void saveVenueType(VenueType venueType);
	
	public OctopusServiceType getOctopusServiceType(Integer service_type_code);
	
	public RevenueItem getRevenueItem(RevenueItem revenueItem);
	
	public MailingList getMailingList(MailingList mailingList);
	
	
}
