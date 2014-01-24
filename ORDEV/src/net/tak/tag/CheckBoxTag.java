package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class CheckBoxTag extends BaseTag {

	private String id;
	private String label;
	private Boolean mandatory = false;
	private String cssStyleText;
	private String cssStyleLabel;
	private String editableWhen;
	private String visibleWhen;
	private String onChange;

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

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getCssStyleText() {
		return cssStyleText;
	}

	public void setCssStyleText(String cssStyleText) {
		this.cssStyleText = cssStyleText;
	}

	public String getCssStyleLabel() {
		return cssStyleLabel;
	}

	public void setCssStyleLabel(String cssStyleLabel) {
		this.cssStyleLabel = cssStyleLabel;
	}

	public String getEditableWhen() {
		return editableWhen;
	}

	public void setEditableWhen(String editableWhen) {
		this.editableWhen = editableWhen;
	}

	public String getVisibleWhen() {
		return visibleWhen;
	}

	public void setVisibleWhen(String visibleWhen) {
		this.visibleWhen = visibleWhen;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	@Override
	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) getJspContext();
		Object bean = getRequestBean(pageContext);
		Boolean visible_ind = false;
		Boolean enable_ind = false;
		
		String mode = (String)getObjectValue(bean, TConstant.MODE);
		
		if(StringUtils.isNotEmpty(mode) && StringUtils.isNotEmpty(visibleWhen) && StringUtils.isNotEmpty(editableWhen)) {
			String[] visibles = visibleWhen.split(",");
			String[] editables = editableWhen.split(",");
			
			for(String visible : visibles) {
				if(mode.equals(visible)) {
					visible_ind = true;
					break;
				}
			}
			
			for(String editable : editables) {
				if(mode.equals(editable)) {
					enable_ind = true;
					break;
				}
			}
		}
		
		StringBuffer buffer = new StringBuffer();
		
		String styleId = id.replace(".", "_");
		Object[] fields = getValue(bean, id);
		String value = fields[0] != null ? fields[0].toString() : TConstant.EMPTY;
		if(visible_ind) {
			buffer.append("<div class=\"fieldLabel\"");
			if(StringUtils.isNotEmpty(cssStyleLabel)) {
				if(!";".equals(Character.toString(cssStyleLabel.charAt(cssStyleLabel.length() -1)))){
					cssStyleLabel += ";";
				}
				buffer.append(" style=\"").append( cssStyleLabel).append( "\"" );
			}
			buffer.append(">");
			if(mandatory) {
				buffer.append("* ");
			}
			buffer.append(label);
			buffer.append(":</div>\n");
			buffer.append("<div class=\"\"");
			buffer.append(">");
			buffer.append("<input type=\"checkbox\" id=\"").append(styleId ).append("\" name=\"").append( id).append( "\"");
			buffer.append(" value=\"").append( value ).append("\" ");
			if(StringUtils.isNotEmpty(cssStyleText)) {
				if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
					cssStyleText += ";";
				}
				buffer.append(" style=\"").append( cssStyleText ).append( "\"" );
			}
			if(!enable_ind) {
				buffer.append(" disabled = \"disabled\"");
			}
			if(StringUtils.isNotEmpty(value) && "Y".equals(value)) {
				buffer.append(" checked=\"checked\"");
			}
			buffer.append("/>\n");
			buffer.append("</div>\n");
		}
		pageContext.getOut().print(buffer.toString());
	}

}
