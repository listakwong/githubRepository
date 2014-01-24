package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class DatePickerTag extends BaseTag {

	private String id;
	private String label;
	private String cssStyleText;
	private String cssStyleLabel;
	private Boolean mandatory = false;
	private String editableWhen;
	private String visibleWhen;
	private Boolean dateRange= false;
	private String dateFormat ="yy-mm-dd";
	private String toId;

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

	public Boolean getDateRange() {
		return dateRange;
	}

	public void setDateRange(Boolean dateRange) {
		this.dateRange = dateRange;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
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
		String styleId = id.replace(".", "_");
		String toValue = "";
		String styleToId = "";
		if(dateRange) {
			Object[] toFields = getValue(bean, id);
			toValue = toFields[0] != null ? toFields[0].toString() : TConstant.EMPTY;
			if(toId != null){
				styleToId = toId.replace(".", "_");
			}
		}
		
		StringBuffer buffer = new StringBuffer();
		if(visible_ind) {
			buffer.append("<div class=\"fieldLabel\"");
			if(StringUtils.isNotEmpty(cssStyleLabel)) {
				if(!";".equals(Character.toString(cssStyleLabel.charAt(cssStyleLabel.length() -1)))){
					cssStyleLabel += ";";
				}
				buffer.append(" style=\"").append( cssStyleLabel).append( "\"" );
			}
			buffer.append(">");
			if (mandatory) {
				buffer.append("* ");
			}
			buffer.append(getLabel());
			buffer.append(":</div>");
			buffer.append("<div class=\"fieldText\"");
			if(StringUtils.isNotEmpty(cssStyleText)) {
				if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
					cssStyleText += ";";
				}
				buffer.append(" style=\"").append( cssStyleText).append( "\"" );
			}
			buffer.append(">\n");
//			if(enable) {
				buffer.append("<input type=\"text\" id=\"").append( styleId ).append("\" name=\"").append( id ).append("\"");
				buffer.append(" value=\"").append( value ).append("\"/>");
				
				if(dateRange) {
					buffer.append(" <span class=\"label\">to</span> ");
					buffer.append("<input type=\"text\" id=\"").append( styleToId ).append("\" name=\"").append( toId ).append("\"");
					buffer.append(" value=\"").append( toValue ).append("\"/>\n");
				}
				
				buffer.append("\n<script>\n");
				buffer.append("$(function() { ");
				buffer.append("$( \"#").append( styleId ).append("\" ).datepicker({\n");
				buffer.append(" showOn: \"button\",\n");
				buffer.append(" buttonImage: \"../css/images/calendar.gif\",\n");
				if(!enable_ind) {
					buffer.append(" disabled: true,\n");
				}
				buffer.append(" buttonImageOnly: true,\n");
				buffer.append(" defaultDate: \"+1w\",\n");
				buffer.append(" dateFormat: \"").append( dateFormat ).append("\",\n");
				buffer.append(" onClose: function( selectedDate ) {\n");
				if(dateRange) {
					buffer.append("	$( \"#").append( styleToId ).append("\" ).datepicker(\"option\", \"minDate\", selectedDate );\n");
				}
				buffer.append("}});\n");
			
				if(dateRange) {
					buffer.append("$( \"#").append( styleToId ).append("\" ).datepicker({\n");
					buffer.append(" showOn: \"button\",\n");
					buffer.append(" buttonImage: \"../css/images/calendar.gif\",\n");
					if(!enable_ind) {
						buffer.append(" disabled: true,\n");
					}
					buffer.append(" buttonImageOnly: true,\n");
					buffer.append(" defaultDate: \"+1w\",\n");
					buffer.append(" dateFormat: \"").append( dateFormat ).append("\",\n");
					buffer.append(" onClose: function( selectedDate ) {\n");
					buffer.append("	$( \"#").append( styleId ).append("\" ).datepicker(\"option\", \"maxDate\", selectedDate );\n");
					buffer.append("}});\n");
				}
				buffer.append("});\n");
				buffer.append("</script>\n");
			/*} else {
				buffer.append( value );
				if(dateRange) {
					buffer.append(" <span class=\"label\">to</span> ");
					buffer.append( toValue );
				}
			}*/
			buffer.append("</div>");
		}
		pageContext.getOut().print(buffer.toString());
	}

}
