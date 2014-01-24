package net.tak.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import net.tak.TConstant;
import net.tak.bean.common.PageBean;
import net.tak.service.ISystemService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.list.or.Constant;
import com.list.or.bean.admin.SysUser;
import com.list.or.bean.common.LoginBean;
import com.list.or.service.admin.ISysParameterService;
import com.list.or.service.admin.ISysUserService;
import com.list.or.service.maint.IMaintOctopusService;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements SessionAware ,RequestAware{

	private static final long serialVersionUID = -4995925857746196472L;

	protected ISystemService systemService;
	protected ISysUserService sysUserService;
	protected ISysParameterService sysParameterService;
	protected IMaintOctopusService maintOctopusService;
	public Map<String, Object> session;
	public Map<String, Object> request;
	
	protected String page;
	protected String tableTagCheckbox;
	
	private PageBean pagebean;

	public PageBean getPagebean() {
		return pagebean;
	}

	public void setPagebean(PageBean pagebean) {
		this.pagebean = pagebean;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTableTagCheckbox() {
		return tableTagCheckbox;
	}

	public void setTableTagCheckbox(String tableTagCheckbox) {
		this.tableTagCheckbox = tableTagCheckbox;
	}

	public ISysParameterService getSysParameterService() {
		return sysParameterService;
	}

	public void setSysParameterService(ISysParameterService sysParameterService) {
		this.sysParameterService = sysParameterService;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public IMaintOctopusService getMaintOctopusService() {
		return maintOctopusService;
	}

	public void setMaintOctopusService(IMaintOctopusService maintOctopusService) {
		this.maintOctopusService = maintOctopusService;
	}

	@Override
	public String execute() throws Exception {
		return init();
	}

	protected abstract Class<?> getFormClass();

	protected abstract String init();

	protected void putSessionUser(SysUser pSysUser) {
		LoginBean loginBean = new LoginBean();
		loginBean.setUser_id(pSysUser.getUser_id());
		loginBean.setUser_name(pSysUser.getUser_name());
		loginBean.setPassword(pSysUser.getPassword());
		loginBean.setLast_login_date(pSysUser.getLast_upd_date());
		session.remove(TConstant.SESSION_USER_KEY);
		session.put(TConstant.SESSION_USER_KEY, loginBean);
	}
	
	protected Timestamp retrieveCurrentTime(){
		return systemService.retrieveSysTimestamp();
	}
	
	protected Date retrieveCurrentDate(){
		return systemService.retrieveSysdate();
	}

	protected void putSessionMenu(LoginBean loginBean) {
		session.remove(TConstant.SESSION_MENU_KEY);
		session.put(TConstant.SESSION_MENU_KEY, systemService.retrieveSysMenu(loginBean));
	}
	
	protected void putSessionBean(Object object) {
		session.remove(TConstant.SESSION_BEAN_KEY);
		session.put(TConstant.SESSION_BEAN_KEY, object);
	}
	
	protected void putRequsetBean(Object object) {
		request.remove(TConstant.REQUEST_BEAN_KEY);
		request.put(TConstant.REQUEST_BEAN_KEY, object);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	protected void removeSession() {
		session.remove(TConstant.SESSION_USER_KEY);
		session.remove(TConstant.SESSION_MENU_KEY);
		session.remove(TConstant.SESSION_BEAN_KEY);
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}

	public Integer getCurrentPage() {
		Integer current_page = 1;
		if(StringUtils.isNotEmpty(page)) {
			String[] pages = page.split(",");
			if(StringUtils.isNotEmpty(pages[0].trim())) {
				current_page = Integer.valueOf(pages[0].trim());
			}
		}
		
		return current_page;
	}
	
	
	public void setPagingBean(Map<String, Map<String, Object>> parameters, Map<String,String> ordering) {
		if(pagebean == null)
			pagebean = new PageBean();
		
		if(parameters != null){
			if(parameters.size() > 0) {
				pagebean.setParameters(parameters);
			}
		}
		
		Map<String, String> orderings = new HashMap<String,String> ();
		
		if(StringUtils.isNotEmpty(pagebean.getSortBy())) {
			orderings.clear();
			orderings.put(pagebean.getSortBy(), pagebean.getSort());
		} else {
			if(ordering != null && ordering.size() > 0) {
				pagebean.setSort(Constant.EMPTY);
				pagebean.setSortBy(Constant.EMPTY);
				orderings.putAll(ordering);
			}
		}
		
		pagebean.setOrdering(orderings);
		pagebean.setSearch_ind(true);
		pagebean.setCurrent_page(getCurrentPage());
		
		request.put(TConstant.REQUEST_PAGE_BEAN_KEY, pagebean);
	}
	
	public void setPagingBean2(Map<String, Map<String, Object>> parameters, Map<String,String> ordering,String pageBeanKey) {
		
		pagebean = new PageBean();
		
		if(parameters != null){
			if(parameters.size() > 0) {
				pagebean.setParameters(parameters);
			}
		}
		
		pagebean.setOrdering(ordering);
		pagebean.setSearch_ind(true);
		pagebean.setCurrent_page(getCurrentPage());
		pagebean.setRecord_per_page(9999);
		
		request.put(pageBeanKey, pagebean);
	}

}
