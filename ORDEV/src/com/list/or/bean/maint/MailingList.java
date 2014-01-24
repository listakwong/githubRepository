package com.list.or.bean.maint;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "interface_file_mailing_list")
@SequenceGenerator(sequenceName = "mail_seq", name = "mail_seq")
public class MailingList extends BaseBean implements Serializable {

	private static final long serialVersionUID = 4465245319337879968L;

	private Integer mail_id;
	private String email;
	private String create_by;
	private String last_upd_by;
	
	private Timestamp create_date;
	private Timestamp last_upd_date;
	private OctopusServiceType octopusServiceType = new OctopusServiceType();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_seq")
	public Integer getMail_id() {
		return mail_id;
	}

	public void setMail_id(Integer mail_id) {
		this.mail_id = mail_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
