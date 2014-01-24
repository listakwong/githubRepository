package net.tak.tag;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;

import net.tak.TConstant;
import net.tak.bean.common.DropDown;
import net.tak.bean.common.PageBean;
import net.tak.util.DropDownUtil;
import net.tak.util.FormatUtil;
import net.tak.util.MessageUtil;
import net.tak.util.PagingUtil;

import org.apache.commons.lang3.StringUtils;

public class TableTag extends BaseBodyTag {
	private static final long serialVersionUID = 7696695198193049987L;
	private List<TableColumnBean> tempColumns = new ArrayList<TableColumnBean>();
	private String id;
	private Boolean hiddenTotalCount = false;
	private Boolean paging = true;
	private String cssStyle;
	private String result;
	private String linkUri;
	private Integer page;
	private Boolean selectall = false;
	private String classBean;
	private String requestName;

	public String getClassBean() {
		return classBean;
	}

	public void setClassBean(String classBean) {
		this.classBean = classBean;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getLinkUri() {
		return linkUri;
	}

	public void setLinkUri(String linkUri) {
		this.linkUri = linkUri;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getHiddenTotalCount() {
		return hiddenTotalCount;
	}

	public void setHiddenTotalCount(Boolean hiddenTotalCount) {
		this.hiddenTotalCount = hiddenTotalCount;
	}

	public Boolean getPaging() {
		return paging;
	}

	public void setPaging(Boolean paging) {
		this.paging = paging;
	}

	public void addColumns(TableColumnBean bean) {
		tempColumns.add(bean);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Boolean getSelectall() {
		return selectall;
	}

	public void setSelectall(Boolean selectall) {
		this.selectall = selectall;
	}

	@Override
	public int doEndTag() throws JspException {
		PageBean bean = null;
		if(StringUtils.isNotEmpty(requestName)) {
			bean = (PageBean) getRequestPageBean(pageContext,requestName);
		} else {
			bean = (PageBean) getRequestPageBean(pageContext);
		}
		
		try { 
			if(bean != null)
				if(bean.getSearch_ind()){
					bean.setClass_name(classBean);
					pageContext.getOut().print(setTable(bean).toString());
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tempColumns.clear();
		return SKIP_BODY;
	}
	
	private StringBuffer setTable(PageBean bean) {
		StringBuffer buffer = new StringBuffer();
		
		List<Object> list = PagingUtil.pagingList(bean);
		
		buffer.append("<div class=\"tableContainer\" ");
		
		buffer.append(">\n");
		
		if(list.size() > 0 ){
			if(!hiddenTotalCount) {
				buffer.append(setTotalCount(bean));
			}
			if(paging) {
				buffer.append(setPaging(bean));
			}
			buffer.append("<hr />\n");
			
			buffer.append(setColumns(list));
			
			buffer.append("<hr />\n");
			if(paging) {
				buffer.append(setPaging(bean));
			}
		} else {
			buffer.append("<p>no records!</p>\n");
		}
		buffer.append("</div>\n");
		
		return buffer;
	}
	
	
	private StringBuffer setPaging(PageBean bean) {
		int totalPages = bean.getTotalPages();
		Integer current_page = bean.getCurrent_page();
		
		int page = current_page;
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div class=\"tableContent\">\n");
		buffer.append("<input type=\"hidden\" id=\"sort\" name=\"pagebean.sort\" value=\"").append(bean.getSort()).append("\">\n");
		buffer.append("<input type=\"hidden\" id=\"sortBy\" name=\"pagebean.sortBy\" value=\"").append(bean.getSortBy()).append("\">\n");
		buffer.append("<input type=\"hidden\" name=\"page\" id=\"page\" value=\"\"/>\n");
		
		String homeLabel = MessageUtil.getText("label.home");
		String prevLabel = MessageUtil.getText("label.previous");
		String nextLabel = MessageUtil.getText("label.next");
		String endLabel = MessageUtil.getText("label.end");
		
		String herf = "<a href=\"javascript:void(0);\" onclick=\"doPage('";
		
		if(current_page > 1) {
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(1).append(")\">").append(homeLabel).append("</a>").append("</span>\n");
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(current_page -1).append(")\">").append(prevLabel).append("</a>").append("</span>\n");
		}
		
		//show previous 5
		for ( int prevPage = (page - 5 > 0 ? page - 5 : 1); prevPage < page; prevPage ++){
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(prevPage).append(")\">").append(prevPage).append("</a>").append("</span>\n");
		}
		
		buffer.append("<span>").append("<a href=\"javascript:void(0);\" class=\"selected\">").append(page).append("</a>").append("</span>\n");
		
		//show next 5
		for ( int nextPage = page + 1; nextPage < page + 5 && nextPage <= totalPages; nextPage ++){
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(nextPage).append(")\">").append(nextPage).append("</a>").append("</span>\n");
		}
		
		if( current_page < totalPages) {
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(current_page + 1).append(")\">").append(nextLabel).append("</a>").append("</span>\n");
			buffer.append("<span>").append(herf).append(linkUri).append("',").append(totalPages).append(")\">").append(endLabel).append("</a>").append("</span>\n");
		}
		
		buffer.append("\n</div>\n");
		return buffer;
	}
	
	private StringBuffer setTotalCount(PageBean bean) {
		StringBuffer buffer = new StringBuffer();
		
		String[] field = new String[3];
		field[0] = bean.getFromRecord();
		field[1] = bean.getToRecord();
		field[2] = bean.getTotalRecord();
		buffer.append("<div class=\"content\">\n<label>");
		buffer.append(MessageUtil.getText("label.records",field));
		buffer.append("</label>\n</div>\n");
		return buffer;
	}
	
	private StringBuffer setColumns(List<Object> list){
		StringBuffer buffer = new StringBuffer();
		List<TableColumnBean> columns = new ArrayList<TableColumnBean>();
		columns.addAll(tempColumns);
		
		buffer.append("<div class=\"tableContent\">\n");
		buffer.append("<table ");
		if(StringUtils.isNotEmpty(cssStyle)) {
			if(!";".equals(Character.toString(cssStyle.charAt(cssStyle.length() -1)))){
				cssStyle += ";";
			}
			buffer.append(" style=\"").append( cssStyle).append( "\"" );
		}
		buffer.append(" >\n");
		buffer.append("<tr>\n");
		for(TableColumnBean bean : columns){
			String type = bean.getType() != null ? bean.getType().trim().toLowerCase() : "";
			String id = bean.getId();
			Boolean sortable = bean.getSortable();
			buffer.append("<td class=\"td-label\">\n");
			if(StringUtils.isEmpty(type)){
				if(sortable) {
					buffer.append("<div class=\"sortTitle\">\n");
					buffer.append("<a title=\"click to sort by Status\" href=\"javascript:void(0);\" onclick=\"changeSort('").append(linkUri).append("','").append(id).append("');\">\n");
					buffer.append(bean.getLabel());
					buffer.append("</a>\n");
					buffer.append("</div>\n");
					
					buffer.append("<div class=\"sortUpAndDown\">\n");
					buffer.append("<div class=\"sortUp\"></div>\n");
					buffer.append("<div class=\"sortDown\"></div>\n");
					buffer.append("</div>\n");
					
				} else {
					buffer.append(bean.getLabel());
				}
				
			} else if("checkbox".equals(type)) {
				if(selectall) {
					buffer.append("<input type=\"hidden\" id=\"selectall_ind\" name=\"selectall_ind\" value=\"true\"/>\n");
					buffer.append("<input type=\"checkbox\" id=\"selectall\" onclick=\"checkAll()\" name=\"selectall\" value=\"").append("\"/>\n");
				} else {
					buffer.append("<input type=\"hidden\" id=\"selectall_ind\" name=\"selectall_ind\" value=\"false\"/>\n");
				}
			} else if("radio".equals(type)) {
				
			}
			buffer.append("</td>\n");
		}
		buffer.append("</tr>\n");
		buffer.append(setColumnResults(list));
		buffer.append("</table>\n");
		buffer.append("\n</div>\n");
		return buffer;
	}
	
	private StringBuffer setColumnResults(List<Object> list){
		StringBuffer buffer = new StringBuffer();
		List<TableColumnBean> columns = new ArrayList<TableColumnBean>();
		columns.addAll(tempColumns);
		
		List<DropDown> dropDownList = this.retrieveSysCode("syscode");

		Set<String> keys = new HashSet<String>();
		for(Object o : list){
			buffer.append("<tr>\n");
			for(TableColumnBean bean :columns){
				String name = bean.getId().trim();
				String linkId = bean.getLinkId();
				String type = bean.getType() != null ? bean.getType().trim().toLowerCase() : "";
				String link = bean.getLink() != null ? bean.getLink().trim() : "";
				String param = bean.getParam();
				String columnCssStyle = "";
				if(StringUtils.isNotEmpty(bean.getCssStyle())) {
					columnCssStyle = bean.getCssStyle();
					if(!";".equals(Character.toString(columnCssStyle.charAt(columnCssStyle.length() -1)))){
						columnCssStyle += ";";
					}
				}
				try {
					
					String[] fields = getPropertyValue(o,name,param,dropDownList);
					
					if("checkbox".equals(type)) {
						buffer.append("<td class=\"td-content-checkbox\">\n");
						buffer.append("<input type=\"checkbox\" id=\"tableTagCheckbox\" name=\"tableTagCheckbox\" value=\"").append(fields[0]).append("\"/>\n");
					} else if("radio".equals(type)) {
						buffer.append("<td class=\"td-content-radio\">");
						buffer.append("<input type=\"radio\" id=\"tableTagRadio\" name=\"tableTagRadio\" value=\"").append(fields[0]).append("\"/>\n");
					} else {
						if(StringUtils.isNotEmpty(link)){
							buffer.append("<td class=\"td-content-left\"").append(" style=\"").append(columnCssStyle).append("\">\n");
							String[] link_fields = getPropertyValue(o,linkId,null,null);
							keys.add(linkId);
							buffer.append("<a href=\"javascript:void(0);\" onclick=\"toAction('").append(link).append("','").append(linkId).append("','").append(link_fields[0]).append("')\">").append(fields[0]).append("</a>\n");
						} else {
							if("java.lang.Integer".equals(fields[1]) || "int".equals(fields[1]) || "java.math.BigDecimal".equals(fields[1])) {
								buffer.append("<td class=\"td-content-right\"").append(" style=\"").append(columnCssStyle).append("\">\n");
							} else {
								buffer.append("<td class=\"td-content-left\"").append(" style=\"").append(columnCssStyle).append("\">\n");
							}
							buffer.append(fields[0]);
						}
					}
					
					buffer.append("</td>\n");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			buffer.append("</tr>\n");
		}
		
		for(String key :keys){
			buffer.append("<input type=\"hidden\" id=\"").append(key).append("\" name=\"").append(key).append("\" value=\"\">\n");
		}
		return buffer;
	}
	
	
	private String[] getPropertyValue(Object o,String id,String param,List<DropDown> dropDownList) throws Exception{
		String[] fields = new String[2];
		String field_value = "";
		String returnType_name = "";
		if(StringUtils.isNotEmpty(id)){
			Object field_object = null;
			String[] ids = id.split("\\.");
			String methodName = "";
			if(ids.length > 1) {
				methodName = "get" + Character.toUpperCase( ids[0].charAt(0)) + ids[0].substring(1, ids[0].length());
				Method m = o.getClass().getMethod(methodName);
				field_object = m.invoke(o);
				
				for(int i = 1 ;i < ids.length ; i ++) {
					methodName = "get" + Character.toUpperCase( ids[i].charAt(0)) + ids[i].substring(1, ids[i].length());
					m = field_object.getClass().getMethod(methodName);
					field_object = m.invoke(field_object);
				}
				
				returnType_name = m.getReturnType().getName();
			} else {
				methodName = "get" + Character.toUpperCase( id.charAt(0)) + id.substring(1, id.length() );
				
				Method m  = o.getClass().getMethod(methodName);
				
				field_object = m.invoke(o);
				
				returnType_name = m.getReturnType().getName();
			}
			
//			Object[] fieldss = getValuess(o,id);
			
			if(StringUtils.isNotEmpty(param)){
				for(DropDown dropDown : dropDownList){
					if(field_object.equals(dropDown.getKey()) && param.equals(dropDown.getType())) {
						field_object = dropDown.getValue();
						break;
					}
				}
			}
			
			if(field_object != null){
				if("java.lang.Integer".equals(returnType_name) || "int".equals(returnType_name)) {
					field_value = field_object.toString();
				} else if("java.math.BigDecimal".equals(returnType_name)) {
					field_value = FormatUtil.bigDecimaltoString((BigDecimal)field_object, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DOLLAR_FORMAT));
				} else if("java.sql.Timestamp".equals(returnType_name)) {
					field_value = FormatUtil.timestampToString((Timestamp)field_object, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATETIME));
				} else if("java.sql.Date".equals(returnType_name)) {
					field_value = FormatUtil.sqlDateToString((java.sql.Date)field_object, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATE));
				} else if("java.util.Date".equals(returnType_name)) {
					field_value = FormatUtil.utildateToString((java.util.Date)field_object, MessageUtil.getText(TConstant.TAK_PROPERTY,TConstant.TAK_PROPERTY_KEY_DEFAULT_DATE));
				} else {
					field_value = field_object.toString();
				}
			} 
		}
		
		fields[0] = field_value;
		fields[1] = returnType_name;
		
		return fields;
	}
	
	
	private List<DropDown> retrieveSysCode(String method) {
		List<DropDown> list = DropDownUtil.getDropDownList(method,null);
		return list;
	}

}
