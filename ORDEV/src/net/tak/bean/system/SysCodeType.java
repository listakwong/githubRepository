package net.tak.bean.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sys_code_type")
public class SysCodeType implements Serializable {
	private static final long serialVersionUID = -2847424732438615875L;
	private String code_type_id;
	private String code_type_desc;
	private Set<SysCode> syscodes = new HashSet<SysCode>();

	@Id
	public String getCode_type_id() {
		return code_type_id;
	}

	public void setCode_type_id(String code_type_id) {
		this.code_type_id = code_type_id;
	}

	public String getCode_type_desc() {
		return code_type_desc;
	}

	public void setCode_type_desc(String code_type_desc) {
		this.code_type_desc = code_type_desc;
	}

	@OneToMany(targetEntity=SysCode.class,cascade=CascadeType.ALL)
	@JoinColumn(name="code_type_id")
	public Set<SysCode> getSyscodes() {
		return syscodes;
	}

	public void setSyscodes(Set<SysCode> syscodes) {
		this.syscodes = syscodes;
	}
}
