package net.tak.bean.system;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 2582275684497152964L;
	private Integer menu_id;
	private Integer parent_menu_id;
	private String menu_desc;
	private String menu_path;
	private Integer menu_level;
	private Integer seq_no;

	private SysFunctionRight sysFunctionRight = new SysFunctionRight();

	@Id
	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public Integer getParent_menu_id() {
		return parent_menu_id;
	}

	public void setParent_menu_id(Integer parent_menu_id) {
		this.parent_menu_id = parent_menu_id;
	}

	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	public String getMenu_path() {
		return menu_path;
	}

	public void setMenu_path(String menu_path) {
		this.menu_path = menu_path;
	}

	public Integer getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(Integer menu_level) {
		this.menu_level = menu_level;
	}

	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "func_right_id")
	public SysFunctionRight getSysFunctionRight() {
		return sysFunctionRight;
	}

	public void setSysFunctionRight(SysFunctionRight sysFunctionRight) {
		this.sysFunctionRight = sysFunctionRight;
	}

	public Integer getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(Integer seq_no) {
		this.seq_no = seq_no;
	}

}
