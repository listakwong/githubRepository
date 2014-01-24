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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.admin.maintUserAccount" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="maint!*" method="post">
			<takhtml:hidden id="sysUser.mode"/>
			<takhtml:hidden id="sysUser.last_upd_date"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.admin.maintUserAccount"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="sysUser.user_id" label="label.admin.userId" size="20" maxlength="20" mandatory="true" editableWhen="N" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="sysUser.user_name" label="label.admin.userName" size="50" maxlength="50" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="sysUser.email" label="label.admin.email" size="50" maxlength="100" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="sysUser.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="Y" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:select id="sysUser.status_code" param="ACC_STATUS" method="syscode" label="label.admin.status" cssStyleLabel="width: 25%;" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="sysUser.locked_ind" param="SYS_YN" method="syscode" label="label.admin.lockedInd" all="Y" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:textField id="sysUser.unsuccessful_attempt" label="label.admin.unsuccessfulAttempt" cssStyleLabel="width: 25%;" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="sysUser.pwd_expiry_date" label="label.admin.passwordExpiryDate" editableWhen="false" visibleWhen="N,E,R"/>
					<takhtml:textField id="sysUser.last_chg_pwd_date" label="label.admin.lastChangedPasswordDate" cssStyleLabel="width: 25%;" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<takhtml:crossSelect method="groups" id="sysUser.groups" label="label.admin.userGroup" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:button label="button.save" onClick="maint!save" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.back" onClick="enquire" id="button" visibleWhen="N,E,R"/>
				</div>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>