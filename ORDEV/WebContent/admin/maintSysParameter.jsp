<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>

<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.admin.maintSysParameter" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="maint!*" method="post">
			<takhtml:hidden id="sysParam.mode"/>
			<takhtml:hidden id="sysParam.last_upd_date"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.admin.maintSysParameter"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.system_code" label="label.admin.sysParameter.systemCode" cssStyleLabel="width: 35%;" size="10" maxlength="4" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.dept_code" label="label.admin.sysParameter.deptCode" cssStyleLabel="width: 35%;" size="4" maxlength="2" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.journal_source" label="label.admin.sysParameter.journalSource" cssStyleLabel="width: 35%;" size="25" maxlength="25" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.journal_cat" label="label.admin.sysParameter.journalCat" cssStyleLabel="width: 35%;" size="25" maxlength="25" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.d_c_dept_fixed_part" label="label.admin.sysParameter.dCDeptFixedPart" cssStyleLabel="width: 35%;" size="4" maxlength="1" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.cut_off_date_mon_rev" label="label.admin.sysParameter.cutOffDateMonRev" cssStyleLabel="width: 35%;" size="4" maxlength="2" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.chg_pw_days" label="label.admin.sysParameter.chgPwDays" cssStyleLabel="width: 35%;" size="4" maxlength="3" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.max_login_attempt" label="label.admin.sysParameter.maxLoginAttempt" cssStyleLabel="width: 35%;" size="4" maxlength="2" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.archive_data_in_year" label="label.admin.sysParameter.archiveDateInYear" cssStyleLabel="width: 35%;" size="4" maxlength="2" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysParam.purge_data_in_year" label="label.admin.sysParameter.purgeDateInYear" cssStyleLabel="width: 35%;" size="4" maxlength="2" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:button label="button.save" onClick="parameter!save" id="button" visibleWhen="N,E,R"/>
				</div>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>