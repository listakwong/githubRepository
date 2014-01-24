package com.list.or.bean.admin;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "sys_parameter")
public class SysParameter extends BaseBean implements Serializable {

	private static final long serialVersionUID = 9040506839799173596L;
	private String parameter_id;
	private String parameter_value;
	private String create_by;
	private Timestamp create_date;
	private String last_upd_by;
	private Timestamp last_upd_date;

	@Id
	public String getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(String parameter_id) {
		this.parameter_id = parameter_id;
	}

	public String getParameter_value() {
		return parameter_value;
	}

	public void setParameter_value(String parameter_value) {
		this.parameter_value = parameter_value;
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
}
