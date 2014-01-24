package net.tak.tag;

import java.io.Serializable;

public class TableColumnBean implements Serializable {

	private static final long serialVersionUID = -85497641645835806L;

	private String id;
	private String label;
	private String type;
	private String format;
	private String cssStyle;
	private Boolean sortable = false;
	private String link;
	private Boolean editable = false;
	private String param;
	private String linkId;
	

	public TableColumnBean() {

	}

	public TableColumnBean(String id, String label, String type, String format,
			String cssStyle, Boolean sortable, String link, Boolean editable,
			String param, String linkId) {
		super();
		this.id = id;
		this.label = label;
		this.type = type;
		this.format = format;
		this.cssStyle = cssStyle;
		this.sortable = sortable;
		this.link = link;
		this.editable = editable;
		this.param = param;
		this.linkId = linkId;
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
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

}
