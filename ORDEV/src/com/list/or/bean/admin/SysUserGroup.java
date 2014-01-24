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
import net.tak.bean.system.SysFunctionRight;

import com.list.or.bean.maint.OctopusServiceType;

@Entity
@Table(name = "sys_user_group")
public class SysUserGroup extends BaseBean implements Serializable {

	private static final long serialVersionUID = 8982640174627058719L;

	private String user_group_id;
	private String user_group_name;
	private String active_ind;
	private String admin_group_ind;
	private String create_by;
	private String last_upd_by;

	private Timestamp create_date;
	private Timestamp last_upd_date;
	private Set<SysFunctionRight> sysFunctionRights = new HashSet<SysFunctionRight>();;
	private Set<SysUser> sysUsers = new HashSet<SysUser>();

	private Set<OctopusServiceType> octopusServiceTypes = new HashSet<OctopusServiceType>();

	private Set<String> octopuses = new HashSet<String>();
	private Set<String> functions = new HashSet<String>();

	public SysUserGroup() {
		super();
	}

	public SysUserGroup(String user_group_id) {
		this.user_group_id = user_group_id;
	}

	@Id
	public String getUser_group_id() {
		return user_group_id;
	}

	public void setUser_group_id(String user_group_id) {
		this.user_group_id = user_group_id;
	}

	public String getUser_group_name() {
		return user_group_name;
	}

	public void setUser_group_name(String user_group_name) {
		this.user_group_name = user_group_name;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public String getAdmin_group_ind() {
		return admin_group_ind;
	}

	public void setAdmin_group_ind(String admin_group_ind) {
		this.admin_group_ind = admin_group_ind;
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

	@ManyToMany(targetEntity = SysFunctionRight.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_grp_func_right", joinColumns = @JoinColumn(name = "user_group_id"), inverseJoinColumns = @JoinColumn(name = "func_right_id"))
	public Set<SysFunctionRight> getSysFunctionRights() {
		return sysFunctionRights;
	}

	public void setSysFunctionRights(Set<SysFunctionRight> sysFunctionRights) {
		this.sysFunctionRights = sysFunctionRights;
	}

	@ManyToMany(mappedBy = "sysUserGroups", fetch = FetchType.EAGER)
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(targetEntity = OctopusServiceType.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_grp_octopus_service", joinColumns = @JoinColumn(name = "user_group_id"), inverseJoinColumns = @JoinColumn(name = "service_type_code"))
	public Set<OctopusServiceType> getOctopusServiceTypes() {
		return octopusServiceTypes;
	}

	public void setOctopusServiceTypes(
			Set<OctopusServiceType> octopusServiceTypes) {
		this.octopusServiceTypes = octopusServiceTypes;
	}

	@Transient
	public Set<String> getOctopuses() {
		return octopuses;
	}

	public void setOctopuses(Set<String> octopuses) {
		this.octopuses = octopuses;
	}

	@Transient
	public Set<String> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<String> functions) {
		this.functions = functions;
	}

}
