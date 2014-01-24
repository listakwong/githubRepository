package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class TextAreaTag extends BaseTag {
	
	private String label;
	private String id;
	private String cssStyleText;
	private String cssStyleLabel;
	private String editableWhen;
	private String visibleWhen;
	private Boolean mandatory = false;
	private String rows = "10";
	private String cols = "80";

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

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
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
			buffer.append(">\n");
			if(enable_ind) {
				buffer.append("<textarea id=\"").append(styleId ).append("\" name=\"").append( id).append( "\"");
				
				if(StringUtils.isNotEmpty(cssStyleText)) {
					if(!";".equals(Character.toString(cssStyleText.charAt(cssStyleText.length() -1)))){
						cssStyleText += ";";
					}
					buffer.append(" style=\"").append( cssStyleText).append( "\"" );
				}
				
				buffer.append(" rows=\"").append( rows ).append("\" ");
				buffer.append(" cols=\"").append( cols ).append("\">\n");
				buffer.append( value );
				buffer.append("</textarea>\n");
			} else {
				buffer.append(value.replace("\n", "<br />"));
			}
			buffer.append("</div>\n");
		}
		
		
		pageContext.getOut().print(buffer.toString());
	}

}
