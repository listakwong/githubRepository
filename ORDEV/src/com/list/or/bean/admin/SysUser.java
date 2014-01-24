package com.list.or.bean.admin;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseBean implements Serializable {

	private static final long serialVersionUID = 5037515728832429416L;

	private String user_id;
	private String user_name;
	private String password;
	private String email;
	private String active_ind;
	private String locked_ind;
	private String status_code;
	private Integer unsuccessful_attempt;
	private Timestamp last_chg_pwd_date;
	private String reset_pwd_ind;
	private String create_by;
	private Timestamp create_date;
	private String last_upd_by;
	private Timestamp last_upd_date;
	private Timestamp pwd_expiry_date;
	
	private Set<String> groups = new HashSet<String>();

	private Set<SysUserGroup> sysUserGroups = new HashSet<SysUserGroup>();

	public SysUser() {
		super();
	}

	public SysUser(String user_id) {
		this.user_id = user_id;
	}

	@Id
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public String getLocked_ind() {
		return locked_ind;
	}

	public void setLocked_ind(String locked_ind) {
		this.locked_ind = locked_ind;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public Integer getUnsuccessful_attempt() {
		return unsuccessful_attempt;
	}

	public void setUnsuccessful_attempt(Integer unsuccessful_attempt) {
		this.unsuccessful_attempt = unsuccessful_attempt;
	}

	public Timestamp getLast_chg_pwd_date() {
		return last_chg_pwd_date;
	}

	public void setLast_chg_pwd_date(Timestamp last_chg_pwd_date) {
		this.last_chg_pwd_date = last_chg_pwd_date;
	}

	public String getReset_pwd_ind() {
		return reset_pwd_ind;
	}

	public void setReset_pwd_ind(String reset_pwd_ind) {
		this.reset_pwd_ind = reset_pwd_ind;
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
	
	@Transient
	public Timestamp getPwd_expiry_date() {
		return pwd_expiry_date;
	}

	public void setPwd_expiry_date(Timestamp pwd_expiry_date) {
		this.pwd_expiry_date = pwd_expiry_date;
	}

	@ManyToMany(targetEntity = SysUserGroup.class, cascade = {
			CascadeType.ALL },fetch=FetchType.LAZY)
	@JoinTable(name = "sys_user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "user_group_id")
	)
	public Set<SysUserGroup> getSysUserGroups() {
		return sysUserGroups;
	}

	public void setSysUserGroups(Set<SysUserGroup> sysUserGroups) {
		this.sysUserGroups = sysUserGroups;
	}

	@Transient
	public Set<String> getGroups() {
		return groups;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}

}
