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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.enquireBankAccount" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="enquire!*" method="post">
			<takhtml:hidden id="bankAccount.mode"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.enquireBankAccount"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="bankAccount.account_no" label="label.maint.accountNumber" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:select id="bankAccount.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="bankAccount.account_name" label="label.maint.accountName" size="30" maxlength="100" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:textField id="bankAccount.bank_name" label="label.maint.bankName" size="30" maxlength="100" editableWhen="N,E,R" visibleWhen="N,E,R"/>
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
				<takhtml:table id="table" linkUri="enquire!search" classBean="bankAccount" selectall="true">
					<takhtml:column label="label.maint.accountNumber" id="account_no" type="checkbox"/>
					<takhtml:column label="label.maint.accountNumber" id="account_no" linkId="account_no" sortable="false" link="maint" cssStyle="width:80px;"/>
					<takhtml:column label="label.active" id="active_ind" sortable="false" cssStyle="width:50px;" param="SYS_YN"/>
					<takhtml:column label="label.maint.accountName" id="account_name" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.bankName" id="bank_name" sortable="false" cssStyle="width:150px;"/>
				</takhtml:table>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>