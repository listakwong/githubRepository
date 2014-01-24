package com.list.or.action.maint;

import java.sql.Timestamp;

import net.tak.TConstant;
import net.tak.action.BaseAction;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.list.or.Constant;
import com.list.or.bean.common.LoginBean;
import com.list.or.bean.maint.BankAccount;

public class MaintBankAccountAction extends BaseAction {
	private static final long serialVersionUID = 6625844693803393067L;
	private static final Logger logger = Logger.getLogger(MaintBankAccountAction.class);
	private BankAccount bankAccount;
	private String account_no;

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	protected Class<?> getFormClass() {
		return MaintBankAccountAction.class;
	}

	@Override
	protected String init() {
		logger.info("Entering init in MaintBankAccountAction");
		bankAccount = new BankAccount();
		if(StringUtils.isNotEmpty(account_no)){
			bankAccount = maintOctopusService.getBankAccount(account_no);
			bankAccount.setMode(Constant.MODE_EDIT);
		}
		putRequsetBean(bankAccount);
		return INPUT;
	}
	
	public String save() {
		logger.info("Entering save in MaintBankAccountAction");
		LoginBean loginBean = (LoginBean)session.get(TConstant.SESSION_USER_KEY);
		Timestamp current_time = retrieveCurrentTime();
		bankAccount.setLast_upd_by(loginBean.getUser_id());
		bankAccount.setLast_upd_date(current_time);
		if(Constant.MODE_NEW.equals(bankAccount.getMode())){
			bankAccount.setCreate_by(loginBean.getUser_id());
			bankAccount.setCreate_date(current_time);
		}
		
		maintOctopusService.saveBankAccount(bankAccount);
		
		bankAccount.setMode(Constant.MODE_EDIT);
		putRequsetBean(bankAccount);
		addActionMessage(MessageUtil.getText("msg.saveSuccess"));
		return SUCCESS;
	}

}
