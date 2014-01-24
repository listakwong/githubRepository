package net.tak.bean.system;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_code")
public class SysCode implements Serializable {

	private static final long serialVersionUID = -5093695136731484141L;
	private String code_id;
	private String code_desc;
	private String active_ind;
	private Integer display_seq_no;
	private SysCodeType sysCodeType;

	@Id
	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}

	public String getCode_desc() {
		return code_desc;
	}

	public void setCode_desc(String code_desc) {
		this.code_desc = code_desc;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}

	public Integer getDisplay_seq_no() {
		return display_seq_no;
	}

	public void setDisplay_seq_no(Integer display_seq_no) {
		this.display_seq_no = display_seq_no;
	}

	@ManyToOne(targetEntity = SysCodeType.class,cascade=CascadeType.ALL)
	@JoinColumn(name="code_type_id",insertable=false, updatable=false)
	public SysCodeType getSysCodeType() {
		return sysCodeType;
	}

	public void setSysCodeType(SysCodeType sysCodeType) {
		this.sysCodeType = sysCodeType;
	}

}
