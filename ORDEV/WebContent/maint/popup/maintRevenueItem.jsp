<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>
<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.maintRevenueItem" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="maint!*" method="post">
			<takhtml:hidden id="revenueItem.mode"/>
			<takhtml:hidden id="revenueItem.last_upd_date"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.maintRevenueItem"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="revenueItem.octopusServiceType.service_type_code" label="label.maint.serviceTypeCode" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="revenueItem.revenue_item_code" label="label.maint.revenueItemCode" size="20" maxlength="20" mandatory="true" editableWhen="N" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="revenueItem.revenue_item_name" label="label.maint.revenueItemName" size="50" maxlength="100" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="revenueItem.revenue_user_code" label="label.maint.revenueUserCode" size="20" maxlength="13" mandatory="true" editableWhen="N" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="revenueItem.gfmis_desc" label="label.maint.gfmisDesc" size="100" maxlength="20" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="revenueItem.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="Y" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:button label="button.save" onClick="maint!save" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.close" onClick="enquire" id="button" visibleWhen="N,E,R"/>
				</div>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>