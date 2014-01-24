package com.list.or.bean.admin;

import net.tak.bean.BaseBean;

public class SysParam extends BaseBean {

	private String system_code;
	private String dept_code;
	private String journal_source;
	private String journal_cat;
	private String d_c_dept_fixed_part;
	private String cut_off_date_mon_rev;
	private String chg_pw_days;
	private String max_login_attempt;
	private String archive_data_in_year;
	private String purge_data_in_year;

	public String getSystem_code() {
		return system_code;
	}

	public void setSystem_code(String system_code) {
		this.system_code = system_code;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getJournal_source() {
		return journal_source;
	}

	public void setJournal_source(String journal_source) {
		this.journal_source = journal_source;
	}

	public String getJournal_cat() {
		return journal_cat;
	}

	public void setJournal_cat(String journal_cat) {
		this.journal_cat = journal_cat;
	}

	public String getD_c_dept_fixed_part() {
		return d_c_dept_fixed_part;
	}

	public void setD_c_dept_fixed_part(String d_c_dept_fixed_part) {
		this.d_c_dept_fixed_part = d_c_dept_fixed_part;
	}

	public String getCut_off_date_mon_rev() {
		return cut_off_date_mon_rev;
	}

	public void setCut_off_date_mon_rev(String cut_off_date_mon_rev) {
		this.cut_off_date_mon_rev = cut_off_date_mon_rev;
	}

	public String getArchive_data_in_year() {
		return archive_data_in_year;
	}

	public void setArchive_data_in_year(String archive_data_in_year) {
		this.archive_data_in_year = archive_data_in_year;
	}

	public String getPurge_data_in_year() {
		return purge_data_in_year;
	}

	public void setPurge_data_in_year(String purge_data_in_year) {
		this.purge_data_in_year = purge_data_in_year;
	}

	public String getChg_pw_days() {
		return chg_pw_days;
	}

	public void setChg_pw_days(String chg_pw_days) {
		this.chg_pw_days = chg_pw_days;
	}

	public String getMax_login_attempt() {
		return max_login_attempt;
	}

	public void setMax_login_attempt(String max_login_attempt) {
		this.max_login_attempt = max_login_attempt;
	}

}
