package net.tak.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import net.tak.TConstant;
import net.tak.bean.system.SysMenu;

public class MenuTag extends SimpleTagSupport {

	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		Map<Integer,List<SysMenu>> map = (Map<Integer, List<SysMenu>>) pageContext.getSession().getAttribute(TConstant.SESSION_MENU_KEY);
		List<SysMenu> menu_level1 = map.get(0);
		List<SysMenu> menu_level2 = map.get(1);
		List<SysMenu> menu_level3 = map.get(2);
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div id=\"labelContainer\">\n");
		buffer.append("<div class=\"menu\">\n");
		buffer.append("<ul>\n");
		int l2_index = 0;
		int l3_index = 0;
		for(SysMenu menu1 : menu_level1){
			String menu_path = menu1.getMenu_path();
			buffer.append("<li>");
			if(StringUtils.isEmpty(menu_path)){
				buffer.append("<a><span>");
				buffer.append(menu1.getMenu_desc());
				buffer.append("</span></a>\n");
				buffer.append("<ul>\n");
				for(; l2_index < menu_level2.size();l2_index ++){
					if(menu_level2.get(l2_index).getParent_menu_id().equals( menu1.getMenu_id())){
						String l2menu_path = menu_level2.get(l2_index).getMenu_path();
						buffer.append("<li>");
						if(StringUtils.isEmpty(l2menu_path)){
							buffer.append("<a><span>");
							buffer.append(menu_level2.get(l2_index).getMenu_desc());
							buffer.append("</span></a>\n");
							buffer.append("<ul>\n");
							for(; l3_index < menu_level3.size();l3_index ++){
								if(menu_level3.get(l3_index).getParent_menu_id().equals( menu_level2.get(l2_index).getMenu_id())){
									buffer.append("<li>");
									buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('"+menu_level3.get(l3_index).getMenu_path()+"')\">");
									buffer.append(menu_level3.get(l3_index).getMenu_desc());
									buffer.append("</a>\n");
									buffer.append("</li>\n");
								}else {
									break;
								}
							}
							buffer.append("</ul>\n");
						} else {
							
							buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('"+menu_level2.get(l2_index).getMenu_path()+"')\">");
							buffer.append(menu_level2.get(l2_index).getMenu_desc());
							buffer.append("</a>\n");
						}
						buffer.append("</li>\n");
					}else {
						break;
					}
				}
				
				buffer.append("</ul>\n");
			} else {
				buffer.append("<a href=\"javascript:void(0);\" onclick=\"doAction('"+menu1.getMenu_path()+"')\">");
				buffer.append(menu1.getMenu_desc());
				buffer.append("</a>\n");
			}
			buffer.append("</li>\n");
		}
		buffer.append("</ul>\n");
		buffer.append("</div>");
		buffer.append("</div>");
		
		pageContext.getOut().print(buffer.toString());
	}

}
