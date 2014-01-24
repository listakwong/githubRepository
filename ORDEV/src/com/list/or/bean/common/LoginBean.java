package com.list.or.bean.common;

import java.sql.Timestamp;

import net.tak.bean.BaseBean;

public class LoginBean extends BaseBean {
	private String user_id;
	private String user_name;
	private String password;
	private String old_password;
	private String repassword;
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
	private Timestamp last_login_date;
	private Timestamp Last_logout_datetime;
	
	public Timestamp getLast_logout_datetime() {
		return Last_logout_datetime;
	}
	public void setLast_logout_datetime(Timestamp last_logout_datetime) {
		Last_logout_datetime = last_logout_datetime;
	}
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
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getLast_upd_by() {
		return last_upd_by;
	}
	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}
	public Timestamp getLast_upd_date() {
		return last_upd_date;
	}
	public void setLast_upd_date(Timestamp last_upd_date) {
		this.last_upd_date = last_upd_date;
	}
	public Timestamp getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(Timestamp last_login_date) {
		this.last_login_date = last_login_date;
	}
}
