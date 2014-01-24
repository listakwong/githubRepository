package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.util.MessageUtil;

public class TitleTag extends SimpleTagSupport {
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = MessageUtil.getText(label);;
	}

	@Override
	public void doTag() throws JspException, IOException {
		
		PageContext pageContext = (PageContext) getJspContext();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class=\"pageTitle\">").append(label).append("</div>");
		pageContext.getOut().print(buffer.toString());
		
	}

}
