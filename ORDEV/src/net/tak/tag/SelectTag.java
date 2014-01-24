package net.tak.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.bean.common.DropDown;
import net.tak.util.DropDownUtil;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;


public class SelectTag extends BaseTag {
	private String id;
	private String label;
	private String cssStyleText;
	private String cssStyleLabel;
	private String headerKey;
	private String headerValue;
	private Boolean mandatory = false;
	private String editableWhen;
	private String visibleWhen;
	private String onChange;
	private String method;
	private String param;
	private Boolean all = false;

	public Boolean getAll() {
		return all;
	}

	public void setAll(Boolean all) {
		this.all = all;
	}

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

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method.toLowerCase();
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	/*private String getName(){
		String[] name = id.split("\\.");
		return name[name.length -1];
	}*/
	
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
		
		Object[] fields = getValue(bean, id);
		String value = fields[0] != null ? fields[0].toString() : TConstant.EMPTY;
		StringBuffer buffer = new StringBuffer();
		if(visible_ind) {
			String styleId = id.replace(".", "_");
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
			buffer.append("<div class=\"fieldText\"");
			if(StringUtils.isNotEmpty(cssStyleText)) {
				if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
					cssStyleText += ";";
				}
				buffer.append(" style=\"").append( cssStyleText).append( "\"" );
			}
			buffer.append(">\n");
			if(enable_ind) {
				if(StringUtils.isNotEmpty(onChange)){
					buffer.append("<script type='text/javascript'>\n");
					buffer.append("customComboboxOnChange['")
					.append(styleId)
					.append("'] = \"")
					.append(getOnChange())
					.append("\";\n");
					buffer.append("</script>\n");
				}
				
				buffer.append("<select id=\"").append(styleId);
				buffer.append("\" name=\"").append(id).append("\">\n");
				buffer.append("<option value=\"");
				if(StringUtils.isNotEmpty(headerKey)) {
					buffer.append(headerKey);
				}
				buffer.append("\">");
				if(StringUtils.isNotEmpty(headerValue)) {
					buffer.append(headerValue);
				}
				buffer.append("</option>\n");
				
				if(all) {
					buffer.append("<option value=\"all\">");
					buffer.append(MessageUtil.getText("dropdown.allRecords"));
					buffer.append("</option>\n");
				}
				List<DropDown> list = null;
				if(StringUtils.isNotEmpty(param)) {
					list = DropDownUtil.getDropDownList(method, setMethodValues(method, param));
				} else {
					list = DropDownUtil.getDropDownList(method);
				}
				for ( DropDown dropDown : list) {
					buffer.append("<option value=\"");
					if(StringUtils.isNotEmpty(dropDown.getKey())) {
						buffer.append(dropDown.getKey());
					}
					
					buffer.append("\"");
					if(value.equals(dropDown.getKey())) {
						buffer.append(" selected=\"selected\"");
					}
					
					buffer.append(">");
					if(StringUtils.isNotEmpty(dropDown.getValue())) {
						buffer.append(dropDown.getValue());
					}
					buffer.append("</option>\n");
				}
				
				buffer.append("</select>\n");
				buffer.append("<script language=\"javascript\" type=\"text/javascript\">\n");
				buffer.append("$(function() {$(\"#").append(styleId).append("\" ).combobox();});\n");
				buffer.append("</script>\n");
			} else {
				if(StringUtils.isNotEmpty(value)){
					List<DropDown> list = DropDownUtil.getDropDownList(method, setMethodValues(method, param, value));
					for ( DropDown dropDown : list) {
						if(StringUtils.isNotEmpty(dropDown.getValue())) {
							buffer.append(dropDown.getValue());
							break;
						}
					}
				}
				
			}
			
			buffer.append("</div>\n");
		}
		
		pageContext.getOut().print(buffer.toString());
	}
	

}
