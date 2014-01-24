package com.list.or.bean.maint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name="bank_account")
public class BankAccount extends BaseBean implements Serializable {

	private static final long serialVersionUID = 2919459636650654385L;

	private String account_no;
	private String account_name;
	private String active_ind;
	private String bank_name;
	private String create_by;
	private String last_upd_by;
	
	private Timestamp create_date;
	private Timestamp last_upd_date;

	private Set<OctopusServiceType> octopusServices = new HashSet<OctopusServiceType>();
	
	public BankAccount() {
		super();
	}

	public BankAccount(String account_no) {
		super();
		this.account_no = account_no;
	}
	
	@Id
	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	@Column(nullable=true, updatable=false) 
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	
	@Column(nullable=true, updatable=false) 
	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	@Column(nullable=true) 
	public String getLast_upd_by() {
		return last_upd_by;
	}

	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}

	@Column(nullable=true) 
	public Timestamp getLast_upd_date() {
		return last_upd_date;
	}

	public void setLast_upd_date(Timestamp last_upd_date) {
		this.last_upd_date = last_upd_date;
	}

	@OneToMany(targetEntity=OctopusServiceType.class,cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="account_no",insertable=false, updatable=false)
	public Set<OctopusServiceType> getOctopusServices() {
		return octopusServices;
	}
	public void setOctopusServices(Set<OctopusServiceType> octopusServices) {
		this.octopusServices = octopusServices;
	}

}
