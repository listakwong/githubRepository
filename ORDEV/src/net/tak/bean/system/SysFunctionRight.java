package net.tak.bean.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.list.or.bean.admin.SysUserGroup;

@Entity
@Table(name = "sys_function_right")
public class SysFunctionRight implements Serializable {
	private static final long serialVersionUID = -1110486365242668063L;
	private String func_right_id;
	private String func_right_desc;
	private SysMenu sysMenu;
	private Set<SysUserGroup> sysUserGroups = new HashSet<SysUserGroup>();

	@Id
	public String getFunc_right_id() {
		return func_right_id;
	}

	public void setFunc_right_id(String func_right_id) {
		this.func_right_id = func_right_id;
	}

	public String getFunc_right_desc() {
		return func_right_desc;
	}

	public void setFunc_right_desc(String func_right_desc) {
		this.func_right_desc = func_right_desc;
	}

	@OneToOne(mappedBy = "sysFunctionRight")
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	@ManyToMany(targetEntity = SysUserGroup.class, cascade = {
			CascadeType.ALL}, mappedBy = "sysFunctionRights")
	public Set<SysUserGroup> getSysUserGroups() {
		return sysUserGroups;
	}

	public void setSysUserGroups(Set<SysUserGroup> sysUserGroups) {
		this.sysUserGroups = sysUserGroups;
	}

}
