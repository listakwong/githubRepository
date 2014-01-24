package com.list.or.bean.maint;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

import com.list.or.bean.admin.SysUserGroup;

@Entity
@Table(name = "octopus_service_type")
@SequenceGenerator(sequenceName = "service_type_seq", name = "service_type_seq")
public class OctopusServiceType extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1496902601601081323L;
	private Integer service_type_code;
	private String service_type_name;
	private Date eff_start_date;
	private Date eff_end_date;
	private String tx_fee_user_code;
	private String contra_user_code;
	private String deposit_account_user_code;
	private String active_ind;
	private String included_ind;
	private BigDecimal tx_charge_rate;
	private String create_by;
	private String last_upd_by;

	private Timestamp create_date;
	private Timestamp last_upd_date;

	private BankAccount bankAccount = new BankAccount();
	private VenueType venueType = new VenueType();

	private Set<SysUserGroup> sysUserGroups = new HashSet<SysUserGroup>();
	
	private Set<MailingList> mailingLists = new HashSet<MailingList>();
	
	private Set<RevenueItem> revenueItems = new HashSet<RevenueItem>();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_type_seq")
	public Integer getService_type_code() {
		return service_type_code;
	}

	public void setService_type_code(Integer service_type_code) {
		this.service_type_code = service_type_code;
	}

	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	public Date getEff_start_date() {
		return eff_start_date;
	}

	public void setEff_start_date(Date eff_start_date) {
		this.eff_start_date = eff_start_date;
	}

	public Date getEff_end_date() {
		return eff_end_date;
	}

	public void setEff_end_date(Date eff_end_date) {
		this.eff_end_date = eff_end_date;
	}

	public String getTx_fee_user_code() {
		return tx_fee_user_code;
	}

	public void setTx_fee_user_code(String tx_fee_user_code) {
		this.tx_fee_user_code = tx_fee_user_code;
	}

	public String getContra_user_code() {
		return contra_user_code;
	}

	public void setContra_user_code(String contra_user_code) {
		this.contra_user_code = contra_user_code;
	}

	public String getDeposit_account_user_code() {
		return deposit_account_user_code;
	}

	public void setDeposit_account_user_code(String deposit_account_user_code) {
		this.deposit_account_user_code = deposit_account_user_code;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public String getIncluded_ind() {
		return included_ind;
	}

	public void setIncluded_ind(String included_ind) {
		this.included_ind = included_ind;
	}

	public BigDecimal getTx_charge_rate() {
		return tx_charge_rate;
	}

	public void setTx_charge_rate(BigDecimal tx_charge_rate) {
		this.tx_charge_rate = tx_charge_rate;
	}

	@Column(nullable = true, updatable = false)
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	@Column(nullable = true, updatable = false)
	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	@Column(nullable = true)
	public String getLast_upd_by() {
		return last_upd_by;
	}

	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}

	@Column(nullable = true)
	public Timestamp getLast_upd_date() {
		return last_upd_date;
	}

	public void setLast_upd_date(Timestamp last_upd_date) {
		this.last_upd_date = last_upd_date;
	}

	@ManyToMany(targetEntity = SysUserGroup.class, cascade = { CascadeType.ALL }, mappedBy = "octopusServiceTypes")
	public Set<SysUserGroup> getSysUserGroups() {
		return sysUserGroups;
	}

	public void setSysUserGroups(Set<SysUserGroup> sysUserGroups) {
		this.sysUserGroups = sysUserGroups;
	}

	@ManyToOne(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_no", insertable = false, updatable = false)
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@ManyToOne(targetEntity = VenueType.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "venue_type_code", insertable = false, updatable = false)
	public VenueType getVenueType() {
		return venueType;
	}

	public void setVenueType(VenueType venueType) {
		this.venueType = venueType;
	}

	@OneToMany(targetEntity = MailingList.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_code",insertable=false, updatable=false)
	public Set<MailingList> getMailingLists() {
		return mailingLists;
	}

	public void setMailingLists(Set<MailingList> mailingLists) {
		this.mailingLists = mailingLists;
	}

	@OneToMany(targetEntity = RevenueItem.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_code",insertable=false, updatable=false)
	public Set<RevenueItem> getRevenueItems() {
		return revenueItems;
	}

	public void setRevenueItems(Set<RevenueItem> revenueItems) {
		this.revenueItems = revenueItems;
	}

}
