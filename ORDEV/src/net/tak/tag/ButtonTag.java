package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class ButtonTag extends BaseTag {
	
	private String id ;
	private String label;
	private String message;
	private String onClick;
	private String cssStyle;
	private String visibleWhen;
	private String actionType;
	private String deleteType;
	private Boolean enterEvent = false;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = MessageUtil.getText(message);
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

	public String getVisibleWhen() {
		return visibleWhen;
	}

	public void setVisibleWhen(String visibleWhen) {
		this.visibleWhen = visibleWhen;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	public Boolean getEnterEvent() {
		return enterEvent;
	}

	public void setEnterEvent(Boolean enterEvent) {
		this.enterEvent = enterEvent;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		Object bean = getRequestBean(pageContext);
		Boolean visible_ind = false;
		
		String mode = (String)getObjectValue(bean, TConstant.MODE);
		
		if(StringUtils.isNotEmpty(mode) && StringUtils.isNotEmpty(visibleWhen) ) {
			String[] visibles = visibleWhen.split(",");
			
			for(String visible : visibles) {
				if(mode.equals(visible)) {
					visible_ind = true;
					break;
				}
			}
			
		}
		StringBuffer buffer = new StringBuffer();
		if(visible_ind) {
			String styleId = id.replace(".", "_");
			
			buffer.append("<div class=\"button\"");
			buffer.append(" id=\"").append( styleId ).append("\">");
			buffer.append("<a href=\"javascript:void(0);\"");
			
			if(StringUtils.isNotEmpty(cssStyle)) {
				if(!";".equals(Character.toString(cssStyle.charAt(cssStyle.length() -1)))){
					cssStyle += ";";
				}
				buffer.append(" style=\"").append( cssStyle).append( "\"" );
			}
			
			if(StringUtils.isNotEmpty(actionType)) {
				String actiontype = actionType.toLowerCase();
				String deletetype = deleteType.toLowerCase();
				if("delete".equals(actiontype)) {
					buffer.append(" onclick = \"doDelete('").append(message).append("','").append(onClick).append("','").append(actiontype).append("','").append(deletetype).append("')\"");
				} else {
					buffer.append(" onclick = \"showConfirmDialog('").append(message).append("','").append(onClick).append("','").append(actiontype).append("')\"");
				}
				
			} else {
				buffer.append(" onclick=\"doAction('").append(onClick).append("')\"");
			}
			
			buffer.append(">");
			buffer.append( label );
			buffer.append("</a>");
			buffer.append("</div>");
			if(enterEvent) {
				buffer.append("<script>\n");
				buffer.append("$(document).keydown(function(event){ ");
				buffer.append(" if(event.which == 13) { ");
				buffer.append(" doAction('").append(onClick).append("'); ");
				buffer.append(" return false; ");
				buffer.append(" } });\n");
				buffer.append("</script>\n");
				
			}
			
		}
		pageContext.getOut().print(buffer.toString());
	}

}
