package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class LabelTag extends SimpleTagSupport {
	
	private String label;
	private Boolean visible = true;
	private String cssStyle;
	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = MessageUtil.getText(label);
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		StringBuffer buffer = new StringBuffer();
		if(visible) {
			buffer.append("<div class=\"label\" style=\"");
			
			if(StringUtils.isNotEmpty( cssStyle )) {
				if(!";".equals(Character.toString(cssStyle.charAt(cssStyle.length() -1)))){
					cssStyle += ";";
				}
				
				buffer.append(cssStyle);
			}
			
			buffer.append("\">");
			buffer.append( label );
			buffer.append("</div>\n");
		}
		pageContext.getOut().print(buffer.toString());
	}

}
