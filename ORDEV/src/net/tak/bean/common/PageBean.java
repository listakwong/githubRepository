package net.tak.bean.common;

import java.util.HashMap;
import java.util.Map;

import net.tak.TConstant;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class PageBean {

	private static Integer recordPerPage;
	static {
		String recordPerPages = MessageUtil.getText(TConstant.OR_PROPERTY,
				"recordPerPage");
		if (StringUtils.isNotEmpty(recordPerPages)) {
			recordPerPage = Integer.valueOf(recordPerPages);
		} else {
			recordPerPage = 1;
		}
	}
	private Integer current_page = 1;
	private String pagingMethod;
	private Map<String, String> ordering = new HashMap<String, String>();
	
	private Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();

	private Integer record_per_page = recordPerPage;

	private String sort = "";

	private String sortBy = "";

	private Integer total_record;
	
	private Boolean search_ind = false;
	
	private String class_name;
	
	private Class<?> class_bean;

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public Class<?> getClass_bean() {
		return class_bean;
	}

	public void setClass_bean(Class<?> class_bean) {
		this.class_bean = class_bean;
	}

	public Boolean getSearch_ind() {
		return search_ind;
	}

	public void setSearch_ind(Boolean search_ind) {
		this.search_ind = search_ind;
	}

	public Integer getTotalPages() {
		return (getTotal_record()/record_per_page) + ((getTotal_record() % record_per_page) == 0 ? 0 :1 ) ;
	}

	public Integer getCurrent_page() {
		return current_page;
	}

	public String getFromRecord() {
		return Integer.toString((getCurrent_page() - 1) * recordPerPage + 1);
	}

	public String getToRecord() {
		if(getTo_record() > getTotal_record()){
			return getTotalRecord();
		}
		
		return Integer.toString(getCurrent_page() * recordPerPage);
	}

	public Integer getFrom_record() {
		return (getCurrent_page() - 1) * recordPerPage;
	}

	public Map<String, String> getOrdering() {
		return ordering;
	}

	public Integer getRecord_per_page() {
		return record_per_page;
	}

	public String getSort() {
		return sort;
	}

	public Integer getTo_record() {
		return getCurrent_page() * recordPerPage;
	}

	public Integer getTotal_record() {
		return total_record;
	}
	
	public String getTotalRecord() {
		return total_record.toString();
	}

	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}

	public void setOrdering(Map<String, String> ordering) {
		this.ordering = ordering;
	}

	public void setRecord_per_page(Integer record_per_page) {
		this.record_per_page = record_per_page;
	}

	public void setSort(String sort) {
		if(StringUtils.isNotEmpty(sort)) {
			String[] sorts = sort.split(", ");
			if(sorts.length > 0){
				sort = sorts[0];
			} else {
				sort = "";
			}
		}
		
		this.sort = sort;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		if(StringUtils.isNotEmpty(sortBy)) {
			String[] sortBys = sortBy.split(", ");
			if(sortBys.length > 0){
				sortBy = sortBys[0];
			} else {
				sortBy ="";
			}
		}
		
		this.sortBy = sortBy;
	}

	public void setTotal_record(Integer total_record) {
		this.total_record = total_record;
	}

	public Map<String, Map<String, Object>> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Map<String, Object>> parameters) {
		this.parameters = parameters;
	}

	public String getPagingMethod() {
		return pagingMethod;
	}

	public void setPagingMethod(String pagingMethod) {
		this.pagingMethod = pagingMethod;
	}

}
