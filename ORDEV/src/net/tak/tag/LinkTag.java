package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class LinkTag extends SimpleTagSupport {

	private String id ;
	private String label;
	private String onClick;
	private String cssStyle;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = MessageUtil.getText(label);
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();

		StringBuffer buffer = new StringBuffer();
		buffer.append("<div class=\"\">");
		buffer.append("<a href=\"javascript:void(0);\"");
		if(StringUtils.isNotEmpty(cssStyle)) {
			if(!";".equals(Character.toString(cssStyle.charAt(cssStyle.length() -1)))){
				cssStyle += ";";
			}
			buffer.append(" style=\"").append( cssStyle).append( "\"" );
		}
		buffer.append(" onclick=\"doAction('").append(onClick).append("')\">");
		buffer.append(label);
		buffer.append("</a>");
		buffer.append("</div>");
		pageContext.getOut().print(buffer.toString());
	}

}
