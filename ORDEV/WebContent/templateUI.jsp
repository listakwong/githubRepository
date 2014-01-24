<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
		<title><s:text name="title.lcsd_ocrs"/> - <s:text name="title.sampleUI"/></title>
	</head>
	<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
		<div id="pagewrap">
			<form id="fr" action="template!*" method="post">
				<takhtml:banner/>
				<takhtml:menu/>
				<div id="labelContainer">
					<div class="content">
						<takhtml:title label="title.sampleUI"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:button label="label.sample.ui.button" onClick="forgetPassword" id="button" message="msg.confirmSave" visibleWhen="N,E,R"/>
						<takhtml:button label="label.sample.ui.button" onClick="forgetPassword" id="button2" message="msg.confirmExit" visibleWhen="N,E,R"/>
						<takhtml:button label="label.sample.ui.button" onClick="forgetPassword" id="button3" message="msg.confirmPurgeAudit" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:datepicker id="bean.datepicker_a" label="label.sample.ui.datePicker"  mandatory="true" dateFormat="yy-mm-dd" dateRange="true" toId="bean.datepicker_b" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:datepicker id="bean.datepicker_c" label="label.sample.ui.datePicker"  mandatory="true" dateFormat="yy-mm-dd" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:select id="bean.select_a" param="SYS_YN" method="syscode"  mandatory="true" label="label.sample.ui.comboBox1" all="true"  editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:select id="bean.select_b" param="SYS_YN" method="syscode"  mandatory="true" label="label.sample.ui.comboBox2" all="true"  editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:password label="label.common.password" id="bean.password_a" mandatory="true" size="20" maxlength="20" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:textField id="bean.field_a" label="label.sample.ui.textField" mandatory="true" size="20" maxlength="20" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:checkbox id="bean.checkbox_a" label="label.sample.ui.checkBox" mandatory="true" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:radio id="bean.radio_a" label="label.sample.ui.radioButton" param="SYS_YN" method="syscode" mandatory="true" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<takhtml:crossSelect method="sysusergroup" id="bean.sysUserGroups" label="label.sample.ui.crossSelect" mandatory="true" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:textArea label="label.sample.ui.textArea" id="bean.text_area_a" mandatory="true" editableWhen="false" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<takhtml:table id="table" linkUri="template" classBean="sysUser">
						<takhtml:column label="label.admin.userId" id="user_id" type="checkbox"/>
						<takhtml:column label="label.admin.userId" id="user_id" link="../admin/maintUser"/>
						<takhtml:column label="label.admin.userName" id="user_name"/>
						<takhtml:column label="label.admin.active" id="active_ind"/>
						<takhtml:column label="label.admin.locked" id="locked_ind"/>
						<takhtml:column label="label.admin.unsuccessfulAttempt" id="unsuccessful_attempt"/>
						<takhtml:column label="label.admin.lastChangedPasswordDate" id="last_chg_pwd_date"/>
						<takhtml:column label="label.create_date" id="create_date"/>
						<takhtml:column label="label.last_upd_by" id="last_upd_by"/>
						<takhtml:column label="label.create_by" id="create_by"/>
						<takhtml:column label="label.last_upd_date" id="last_upd_date"/>
					</takhtml:table>
				</div>
			</form>
		</div>
		<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
	</body>
</html>