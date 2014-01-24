package com.list.or.action.maint;

import java.util.HashMap;
import java.util.Map;

import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.log4j.Logger;

import com.list.or.bean.maint.BankAccount;

public class EnquireBankAccountAction extends BaseAction {
	private static final long serialVersionUID = -5278669307058579101L;

	private static final Logger logger = Logger.getLogger(EnquireBankAccountAction.class);
	
	private BankAccount bankAccount;

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	protected Class<?> getFormClass() {
		return EnquireBankAccountAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in EnquireBankAccountAction");
		bankAccount = new BankAccount();
		putRequsetBean(bankAccount);
		return INPUT;
	}
	
	public String search() {
		logger.info("Entering search in EnquireBankAccountAction");
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("account_no", bankAccount.getAccount_no());
		param1.put("account_name", bankAccount.getAccount_name());
		param1.put("bank_name", bankAccount.getBank_name());
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("active_ind", bankAccount.getActive_ind());
		
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		parameters.put("like", param1);
		parameters.put("eq", param2);
		
		setPagingBean(parameters,null);
		putRequsetBean(bankAccount);
		return SUCCESS;
	}
	
	public String delete() {
		logger.info("Entering delete in EnquireBankAccountAction");
		String[] account_nos = tableTagCheckbox.split(", ");
		try {
			maintOctopusService.deleteBankAccountByIDs(account_nos);
			addActionMessage(MessageUtil.getText("msg.deleteSuccess"));
		} catch (Exception e) {
			addActionError(MessageUtil.getText("errors.deleteFail"));
		}
		
		bankAccount = new BankAccount();
		putRequsetBean(bankAccount);
		return SUCCESS;
	}

}
