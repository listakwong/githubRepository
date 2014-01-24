package com.list.or.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.TConstant;
import net.tak.util.FormatUtil;
import net.tak.util.MessageUtil;

import com.list.or.bean.common.LoginBean;

public class BannerTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		LoginBean loginBean = (LoginBean) pageContext.getSession().getAttribute(TConstant.SESSION_USER_KEY);
		String login_date = FormatUtil.timestampToString(loginBean.getLast_login_date(), MessageUtil.getText(TConstant.TAK_PROPERTY, TConstant.TAK_PROPERTY_KEY_DEFAULT_DATETIME));
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class='banner'>\n");
		buffer.append("<br/>");
		buffer.append("<div class='box-right-right'>");
		buffer.append(MessageUtil.getText("banner.welcome"));
		buffer.append("</div>\n");
		buffer.append("<br/>");
		buffer.append("<div class='box-right-right'>");
		buffer.append(loginBean.getUser_name());
		buffer.append("</div>\n");
		buffer.append("<br/>");
		buffer.append("<div class='box-right-right'>");
		buffer.append(MessageUtil.getText("banner.loginAt")  + " : " + login_date);
		buffer.append("</div>\n");
		buffer.append("</div>\n");
		pageContext.getOut().print(buffer.toString());
	}

}
