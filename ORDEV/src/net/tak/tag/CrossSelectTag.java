package net.tak.tag;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.tak.TConstant;
import net.tak.bean.common.DropDown;
import net.tak.util.DropDownUtil;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;


public class CrossSelectTag extends BaseTag {
	
	private String id;
	private String label;
	private Boolean mandatory = false;
	private String cssStyle;
	private String editableWhen;
	private String visibleWhen;
	private String method;
	private String listWidth = "250";
	private String horizontal ="scroll";
	private String vertical = "scroll";
	private String rows = "10";

	protected static final String SELECT_TXT = ">";
	protected static final String REMOVE_TXT = "<";
	protected static final String SELECTALL_TXT = ">>";
	protected static final String REMOVEALL_TXT = "<<";
	protected static final String FONT_SIZE = "14";

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
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
		this.method = method;
	}

	public String getListWidth() {
		return listWidth;
	}

	public void setListWidth(String listWidth) {
		this.listWidth = listWidth;
	}

	public String getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(String horizontal) {
		this.horizontal = horizontal;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	
	private String getName(){
		String[] name = id.split("\\.");
		return name[name.length -1];
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
		if(visible_ind) {
			String styleId = id.replace(".", "_");
			buffer.append("<div class=\"content\">\n");
			buffer.append("<div class=\"crossSelect-label\"");
			buffer.append(">");
			if( mandatory ) {
				buffer.append("* ");
			}
			buffer.append( label );
			buffer.append(":</div></div><br/>\n");
			buffer.append("<div class=\"center\">\n");
			buffer.append("<div class='crossSelect-content'>\n");
			buffer.append("<script>\n");
			buffer.append("$(document).ready(function(){ \n");
			buffer.append("$('form input[type=\"\"]').click(function() { $(this).select().unbind('click');});\n");
			buffer.append("$(\"#").append( styleId ).append("\").crossSelect({\n");
			
			buffer.append("select_txt: \"").append(SELECT_TXT);
			buffer.append("\"\n,remove_txt: \"").append(REMOVE_TXT);
			buffer.append("\"\n,selectAll_txt: \"").append(SELECTALL_TXT);
			buffer.append("\"\n,removeAll_txt: \"").append(REMOVEALL_TXT);
			buffer.append("\"\n,vertical: \"").append(vertical);
			buffer.append("\"\n,horizontal: \"").append(horizontal);
			buffer.append("\"\n,listWidth: \"").append(listWidth);
			buffer.append("\"\n,font: \"").append(FONT_SIZE);
			buffer.append("\"\n,rows: ").append(rows);
			
			if( enable_ind ){
				buffer.append("\n,dlbclick: true");
				buffer.append("\n,clickSelects: false");
				buffer.append("\n,clicksAccumulate: true});");
			} else {
				buffer.append("\n,disabled: true");
				buffer.append("\n,dlbclick: false");
				buffer.append("\n,clickSelects: false");
				buffer.append("\n,clicksAccumulate: false});");
	
				buffer.append("\n$('.jqxs_selectButton').removeClass('jqxs_active').attr('disabled', 'disabled');");
				buffer.append("\n$('.jqxs_removeButton').removeClass('jqxs_active').attr('disabled', 'disabled');");
				buffer.append("\n$('.jqxs_selectAllButton').removeClass('jqxs_active').attr('disabled', 'disabled');");
				buffer.append("\n$('.jqxs_removeAllButton').removeClass('jqxs_active').attr('disabled', 'disabled');");
				// Unbind click and dblclick event
				buffer.append("\n$('li').unbind('click');");
				buffer.append("\n$('li').unbind('dblclick');\n");
			}
				
			buffer.append("});\n");
			buffer.append("</script>\n");
			buffer.append("<select name=\"").append( id).append("\" id=\"").append(styleId).append("\" multiple=\"multiple\">\n");
			
			try {
				Set<String> selected = getValues(bean,getName());
				
				List<DropDown> list = DropDownUtil.getDropDownList(method.toLowerCase());
				for ( DropDown dropDown : list) {
					buffer.append("<option value=\"");
					if(StringUtils.isNotEmpty(dropDown.getKey())) {
						buffer.append(dropDown.getKey());
					}
					buffer.append("\"");
					if (this.matchPreviousValues(selected,dropDown.getKey().trim())) {
						buffer.append(" selected=\"selected\"");
					}
					
					buffer.append(">");
					if(StringUtils.isNotEmpty(dropDown.getValue())) {
						buffer.append(dropDown.getValue());
					}
					buffer.append("</option>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			buffer.append("</select>\n");
			buffer.append("</div>");
			buffer.append("</div>");
		}
		pageContext.getOut().print(buffer.toString());
	}
	
	private boolean matchPreviousValues(Set<String> previous, String value) {
		
		if(previous != null && StringUtils.isNotEmpty(value)){
			if(previous.contains(value)) {
				return true;
			}
 		}
		return false;

	}

}
