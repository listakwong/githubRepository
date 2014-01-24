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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.admin.enquireUserAccount" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="enquire!*" method="post">
			<takhtml:hidden id="sysUser.mode"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.admin.enquireUserAccount"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="sysUser.user_id" label="label.admin.userId" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:textField id="sysUser.user_name" label="label.admin.userName" size="50" maxlength="50" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:select id="sysUser.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:select id="sysUser.locked_ind" param="SYS_YN" method="syscode" label="label.admin.lockedInd" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:button label="button.search" onClick="enquire!search" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.reset" onClick="enquire" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.create" onClick="maint" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.delete" onClick="enquire!delete" id="button" message="msg.confirmDelete" actionType="delete" deleteType="checkbox" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="resultContainer">
				<takhtml:table id="table" linkUri="enquire!search" classBean="sysUser">
					<takhtml:column label="label.admin.userId" id="user_id" type="checkbox"/>
					<takhtml:column label="label.admin.userId" id="user_id" linkId="user_id" sortable="false" link="maint" cssStyle="width:100px;"/>
					<takhtml:column label="label.admin.userName" id="user_name" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.active" id="active_ind" sortable="false" cssStyle="width:80px;" param="SYS_YN"/>
					<takhtml:column label="label.admin.status" id="status_code" sortable="false" cssStyle="width:80px;" param="ACC_STATUS"/>
					<takhtml:column label="label.admin.unsuccessfulAttempt" sortable="false" id="unsuccessful_attempt" cssStyle="width:50px;"/>
				</takhtml:table>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>