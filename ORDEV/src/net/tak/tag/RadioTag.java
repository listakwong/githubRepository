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


public class RadioTag extends BaseTag {

	private String id;
	private String label;
	private String cssStyleText;
	private String cssStyleLabel;
	private Boolean mandatory = false;
	private String editableWhen;
	private String visibleWhen;
	private String onChange;
	private String method;
	private String param;

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

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
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
		Object[] fields = getValue(bean, id);
		String value = fields[0] != null ? fields[0].toString() : TConstant.EMPTY;
		StringBuffer buffer = new StringBuffer();
		
		if(visible_ind) {
			String styleId = id.replace(".", "_");
			buffer.append("<div class=\"fieldLabel\"");
			buffer.append(">");
			if(mandatory) {
				buffer.append("* ");
			}
			buffer.append(label);
			buffer.append(":</div>\n");
			buffer.append("<div class=\"fieldText\"");
			if(StringUtils.isNotEmpty(cssStyleLabel)) {
				if(!";".equals(Character.toString(cssStyleLabel.charAt(cssStyleLabel.length() -1)))){
					cssStyleLabel += ";";
				}
				buffer.append(" style=\"").append( cssStyleLabel).append( "\"" );
			}
			buffer.append(">");
			if(StringUtils.isNotEmpty(onChange)){
				
			}
			
			List<DropDown> list = DropDownUtil.getDropDownList(method, setMethodValues(method, param));
			for ( DropDown dropDown : list) {
				
				buffer.append("<label><input type=\"radio\"");
				
				buffer.append(" id=\"");
				if(StringUtils.isNotEmpty(dropDown.getKey())) {
					buffer.append(styleId).append("_").append(dropDown.getKey());
				}
				buffer.append("\" value=\"");
				if(StringUtils.isNotEmpty(dropDown.getKey())) {
					buffer.append(dropDown.getKey());
				}
				buffer.append("\" name=\"").append(styleId).append("\"");
				if(value.equals(dropDown.getKey())) {
					buffer.append(" checked=\"checked\"");
				}
				
				if(!enable_ind) {
					buffer.append(" disabled=\"disabled\"");
				}
				if(StringUtils.isNotEmpty(cssStyleText)) {
					if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
						cssStyleText += ";";
					}
					buffer.append(" style=\"").append( cssStyleText).append( "\"" );
				}
				
				buffer.append("/>");
				if(StringUtils.isNotEmpty(dropDown.getValue())) {
					buffer.append(" <span class=\"label\">").append(dropDown.getValue()).append("</span> ");
				}
				buffer.append("</label>");
			}
			
			buffer.append("</div>\n");
		}

		pageContext.getOut().print(buffer.toString());
	}

}
