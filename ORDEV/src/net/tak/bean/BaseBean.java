package net.tak.bean;

import com.list.or.Constant;

public class BaseBean{
	
	private String mode = Constant.MODE_NEW;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
