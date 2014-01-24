package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;

public class HiddenTag extends BaseTag {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		StringBuffer buffer = new StringBuffer();
		String styleId = id.replace(".", "_");
		Object bean = getRequestBean(pageContext);
		Object[] fields = getValue(bean, id);
		String value = fields[0] != null ? fields[0].toString() : TConstant.EMPTY;
		
		buffer.append("<input type=\"hidden\" id=\"").append(styleId)
				.append("\" name=\"").append(id).append("\" value=\"")
				.append(value).append("\"/>");
		pageContext.getOut().print(buffer.toString());
	}

}
