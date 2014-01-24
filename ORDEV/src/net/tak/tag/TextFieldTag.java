package net.tak.tag;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.util.FormatUtil;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class TextFieldTag extends BaseTag {
	
	private String label;
	private String id;
	private String cssStyleText;
	private String cssStyleLabel;
	private String editableWhen;
	private String visibleWhen;
	private String maxlength;
	private String size;
	private Boolean mandatory = false;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = MessageUtil.getText(label);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
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
		Object[] fields = getValue(bean, id);
		Object value = fields[0] ;
		String return_type_name = fields[1] != null ? fields[1].toString() : TConstant.EMPTY;
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
			buffer.append(">");
			if(enable_ind) {
				value = fields[0] != null ? fields[0].toString() : TConstant.EMPTY;
				buffer.append("<input type=\"text\" id=\"").append(styleId).append("\" name=\"").append(id).append( "\"");
				
				if(StringUtils.isNotEmpty(maxlength)) {
					buffer.append(" maxlength=\"" ).append( maxlength ).append( "\"");
				}
				
				if(StringUtils.isNotEmpty(cssStyleText)) {
					if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
						cssStyleText += ";";
					}
					buffer.append(" style=\"").append( cssStyleText).append( "\"" );
				}
				
				buffer.append(" value=\"").append( value ).append("\" ");
				
				if(StringUtils.isNotEmpty(size)) {
					buffer.append(" size=\"").append( size ).append( "\"" );
				}
				
				buffer.append("/>\n");
				
			} else {
				/*Object value = getObjectValue(bean, id);
				String method = "get" + Character.toUpperCase(getName().charAt(0)) + getName().substring(1, getName().length());
				String returnType = "";
				try {
					Method m = bean.getClass().getMethod(method);
					returnType = m.getReturnType().getName();
				} catch (Exception e) {
					returnType = "";
				}*/
				
				if(value != null){
					if("java.lang.Integer".equals(return_type_name) || "int".equals(return_type_name)) {
						value = value.toString();
					} else if("java.math.BigDecimal".equals(return_type_name)) {
						value = FormatUtil.bigDecimaltoString((BigDecimal)value, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DOLLAR_FORMAT));
					} else if("java.sql.Timestamp".equals(return_type_name)) {
						value = FormatUtil.timestampToString((Timestamp)value, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATETIME));
					} else if("java.sql.Date".equals(return_type_name)) {
						value = FormatUtil.sqlDateToString((java.sql.Date)value, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATE));
					} else if("java.util.Date".equals(return_type_name)) {
						value = FormatUtil.utildateToString((java.util.Date)value, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATE));
					} else {
						value = value.toString();
					}
				} else {
					value = "";
				}
				
				buffer.append(value);
				
				buffer.append("<input type=\"hidden\" id=\"").append(styleId).append("\" name=\"").append(id).append("\" value=\"").append(value).append("\"/>");
			}
			buffer.append("</div>\n");
		}
		pageContext.getOut().print(buffer.toString());
	}

}
