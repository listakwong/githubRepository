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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.maintBankAccount" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="maint!*" method="post">
			<takhtml:hidden id="bankAccount.mode"/>
			<takhtml:hidden id="bankAccount.last_upd_date"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.maintBankAccount"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="bankAccount.account_no" label="label.maint.accountNumber" size="20" maxlength="20" mandatory="true" editableWhen="N" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="bankAccount.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="Y" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="bankAccount.account_name" label="label.maint.accountNumber" size="100" maxlength="100" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="bankAccount.bank_name" label="label.maint.accountNumber" size="100" maxlength="100" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
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