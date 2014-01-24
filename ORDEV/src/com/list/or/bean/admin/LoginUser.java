package com.list.or.bean.admin;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "SYS_USER")
public class LoginUser extends BaseBean implements Serializable{

	private static final long serialVersionUID = 6471230913303029115L;

	private String user_id;
	private String user_name;
	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

}
