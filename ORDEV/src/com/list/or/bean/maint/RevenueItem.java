package com.list.or.bean.maint;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "revenue_item")
public class RevenueItem extends BaseBean implements Serializable {

	private static final long serialVersionUID = -5120001625146485232L;

	private String revenue_item_code;
	private String revenue_item_name;
	private String revenue_user_code;
	private String active_ind;
	private String gfmis_desc;
	private String create_by;
	private String last_upd_by;

	private Timestamp create_date;
	private Timestamp last_upd_date;

	private OctopusServiceType octopusServiceType = new OctopusServiceType();

	@Id
	public String getRevenue_item_code() {
		return revenue_item_code;
	}

	public void setRevenue_item_code(String revenue_item_code) {
		this.revenue_item_code = revenue_item_code;
	}

	public String getRevenue_item_name() {
		return revenue_item_name;
	}

	public void setRevenue_item_name(String revenue_item_name) {
		this.revenue_item_name = revenue_item_name;
	}

	public String getRevenue_user_code() {
		return revenue_user_code;
	}

	public void setRevenue_user_code(String revenue_user_code) {
		this.revenue_user_code = revenue_user_code;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public String getGfmis_desc() {
		return gfmis_desc;
	}

	public void setGfmis_desc(String gfmis_desc) {
		this.gfmis_desc = gfmis_desc;
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

	@ManyToOne(targetEntity = OctopusServiceType.class, cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "service_type_code", insertable = true, updatable = true)
	public OctopusServiceType getOctopusServiceType() {
		return octopusServiceType;
	}

	public void setOctopusServiceType(OctopusServiceType octopusServiceType) {
		this.octopusServiceType = octopusServiceType;
	}

}
