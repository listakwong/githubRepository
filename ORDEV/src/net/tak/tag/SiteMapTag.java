package net.tak.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.tak.TConstant;
import net.tak.bean.system.SysMenu;
import net.tak.util.MessageUtil;

import org.apache.commons.lang3.StringUtils;

public class SiteMapTag extends SimpleTagSupport {
	
	private static final String SITE = "site";
	private static final String MAP = "map";
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		StringBuffer buffer = new StringBuffer();
		if(SITE.equals(type)){
			Map<Integer,List<SysMenu>> map = (Map<Integer, List<SysMenu>>) pageContext.getSession().getAttribute(TConstant.SESSION_MENU_KEY);
			List<SysMenu> menu_level1 = map.get(0);
			List<SysMenu> menu_level2 = map.get(1);
			List<SysMenu> menu_level3 = map.get(2);
			
			buffer.append("<div id=\"labelContainer\">\n");
			buffer.append("<div class=\"siteMap\">\n");
			buffer.append("<ul>\n");
			int l2_index = 0;
			int l3_index = 0;
			for(SysMenu menu1 : menu_level1){
				String menu_path = menu1.getMenu_path();
				buffer.append("<li>");
				if(StringUtils.isNotEmpty(menu_path)){
					buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('"+menu1.getMenu_path()+"')\">");
					buffer.append(menu1.getMenu_desc());
					buffer.append("</a>\n");
				} else {
					buffer.append(menu1.getMenu_desc());
					buffer.append("<ul>\n");
					for(; l2_index < menu_level2.size();l2_index ++){
						if(menu_level2.get(l2_index).getParent_menu_id().equals( menu1.getMenu_id())){
							String l2menu_path = menu_level2.get(l2_index).getMenu_path();
							buffer.append("<li>");
							if(StringUtils.isNotEmpty(l2menu_path)){
								buffer.append("- <a href=\"javascript:void(0);\" onclick=\"doAction('"+menu_level2.get(l2_index).getMenu_path()+"')\">");
								buffer.append(menu_level2.get(l2_index).getMenu_desc());
								buffer.append("</a>\n");
							} else {
								buffer.append(menu_level2.get(l2_index).getMenu_desc());
								buffer.append("<ul>\n");
								for(; l3_index < menu_level3.size();l3_index ++){
									if(menu_level3.get(l3_index).getParent_menu_id().equals( menu_level2.get(l2_index).getMenu_id())){
										String l3menu_path = menu_level3.get(l3_index).getMenu_path();
										buffer.append("<li>");
										
										if(StringUtils.isNotEmpty(l3menu_path)){
											buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('"+menu_level3.get(l3_index).getMenu_path()+"')\">");
											buffer.append(menu_level3.get(l3_index).getMenu_desc());
											buffer.append("</a>\n");
										} else {
											buffer.append(menu_level3.get(l3_index).getMenu_desc());
										}
										
										buffer.append("</li>\n");
									}else {
										break;
									}
								}
								buffer.append("</ul>\n");
							}
							buffer.append("</li>\n");
						}else {
							break;
						}
					}
					buffer.append("</ul>\n");
					
				}
				buffer.append("</li>\n");
			}
			buffer.append("</ul>\n");
			buffer.append("</div>");
			buffer.append("</div>");
		} else if(MAP.equals(type)){
			buffer.append("<div id=\"siteContainer\">\n");
			buffer.append("<div class=\"content\">\n");
			buffer.append("<div class=\"bottomSite\">\n");
			buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('../common/siteMap')\">").append(MessageUtil.getText("label.siteMap")).append("</a>\n");
			buffer.append("</div>\n");
			buffer.append("</div>\n");
			buffer.append("</div>\n");
		}
		
		pageContext.getOut().print(buffer.toString());
	}

}
