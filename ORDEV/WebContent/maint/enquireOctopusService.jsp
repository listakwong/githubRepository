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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.enquireOctopusService" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="enquire!*" method="post">
			<takhtml:hidden id="octopusServiceType.mode"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.enquireOctopusService"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.service_type_code" label="label.maint.serviceTypeCode" cssStyleLabel="width:230px;" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:textField id="octopusServiceType.service_type_name" label="label.maint.serviceTypeName" cssStyleLabel="width:230px;" size="30" maxlength="100" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.tx_fee_user_code" label="label.maint.txFeeUserCode" cssStyleLabel="width:230px;" size="30" maxlength="100" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:select id="octopusServiceType.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" cssStyleLabel="width:230px;" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.deposit_account_user_code" label="label.maint.depositAccountUserCode" cssStyleLabel="width:230px;" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:textField id="octopusServiceType.contra_user_code" label="label.maint.contraUserCode" cssStyleLabel="width:230px;" size="30" maxlength="100" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:datepicker id="octopusServiceType.eff_start_date" dateRange="true" toId="octopusServiceType.eff_end_date" cssStyleLabel="width:230px;" cssStyleText="width:350px;" label="label.maint.effDate" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					<takhtml:select id="octopusServiceType.venueType.venue_type_code" param="" method="venue" label="label.maint.venueType" cssStyleLabel="width:100px;" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:button label="button.search" onClick="enquire!search" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.reset" onClick="enquire" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.create" onClick="maint" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.delete" onClick="enquire!delete" id="button" message="msg.confirmDelete" actionType="delete" deleteType="checkbox" visibleWhen="N,E,R"/>
					<takhtml:button label="button.export" onClick="enquire!export" id="button" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="resultContainer">
				<takhtml:table id="table" linkUri="enquire!search" classBean="octopusServiceType" selectall="true" cssStyle="width:100%">
					<takhtml:column label="label.maint.serviceTypeCode" id="service_type_code" type="checkbox"/>
					<takhtml:column label="label.maint.serviceTypeCode" id="service_type_code" linkId="service_type_code" sortable="false" link="maint" cssStyle="width:200px;"/>
					<takhtml:column label="label.maint.serviceTypeName" id="service_type_name" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.txFeeUserCode" id="tx_fee_user_code" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.contraUserCode" id="contra_user_code" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.depositAccountUserCode" id="deposit_account_user_code" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.active" id="active_ind" sortable="false" cssStyle="width:80px;" param="SYS_YN"/>
					<takhtml:column label="label.maint.effStartDate" id="eff_start_date" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.effEndDate" id="eff_end_date" sortable="false" cssStyle="width:150px;"/>
					<takhtml:column label="label.maint.venueType" id="venueType.venue_type_name" sortable="false" cssStyle="width:150px;"/>
				</takhtml:table>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>