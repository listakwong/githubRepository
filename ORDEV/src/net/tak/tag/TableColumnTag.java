package net.tak.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import net.tak.util.MessageUtil;

public class TableColumnTag extends BaseTag {
	private String id;
	private String label;
	private String type;
	private String format;
	private String cssStyle;
	private Boolean sortable = false;
	private String link;
	private String sortBy;
	private Boolean editable = false;
	private String param;
	private String linkId;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		TableTag tableTag = (TableTag) getParent();
		TableColumnBean bean = new TableColumnBean(id, label, type, format,
				cssStyle, sortable, link, editable,	param, linkId);
		tableTag.addColumns(bean);
	}

}
