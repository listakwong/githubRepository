<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>

<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
		<script type="text/javascript">
			$(function(){
				var mode = $("#octopusServiceType_mode").val();
				if(mode == "N"){
					$("#revenueItemCon").css("display","none");
					$("#mailingListCon").css("display","none");
				} else {
					$("#revenueItemCon").css("display","block");
					$("#mailingListCon").css("display","block");
				}
			});
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.maintOctopusService" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="maint!*" method="post">
			<takhtml:hidden id="octopusServiceType.mode"/>
			<takhtml:hidden id="octopusServiceType.last_upd_date"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.maintOctopusService"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.service_type_code" label="label.maint.serviceTypeCode" cssStyleLabel="width:250px;" editableWhen="false" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.service_type_name" label="label.maint.serviceTypeName" cssStyleLabel="width:250px;" size="100" maxlength="100" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.tx_fee_user_code" label="label.maint.txFeeUserCode" cssStyleLabel="width:250px;" size="20" maxlength="9" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
					<takhtml:label label="label.maint.tx_fee_user_code_eg_number"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.contra_user_code" label="label.maint.contraUserCode" cssStyleLabel="width:250px;" size="20" maxlength="15" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
					<takhtml:label label="label.maint.contra_user_code_eg_number"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:textField id="octopusServiceType.deposit_account_user_code" label="label.maint.depositAccountUserCode" cssStyleLabel="width:250px;" size="20" maxlength="13" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
					<takhtml:label label="label.maint.deposit_account_user_code_eg_number"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="octopusServiceType.active_ind" label="label.activeInd" param="SYS_YN" method="syscode" cssStyleLabel="width:250px;" all="Y" mandatory="true" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:datepicker id="octopusServiceType.eff_start_date" label="label.maint.effStartDate" mandatory="true" cssStyleLabel="width:250px;" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:datepicker id="octopusServiceType.eff_end_date" label="label.maint.effEndDate" cssStyleLabel="width:250px;" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:checkbox id="octopusServiceType.included_ind" label="label.maint.MonthlyRevenueInterfaceFile" cssStyleLabel="width:250px;" editableWhen="N,E" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="octopusServiceType.venueType.venue_type_code" param="" method="venue" label="label.maint.venueType" cssStyleLabel="width:250px;" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:select id="octopusServiceType.bankAccount.account_no" param="" method="account" label="label.maint.accountNumber" cssStyleLabel="width:250px;" mandatory="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
				</div>
			</div>
			<div id="revenueItemCon">
				<div id="labelContainer">
					<div class="content">
						<takhtml:label label="label.maint.revenueItem"/>
						<takhtml:button label="button.create" onClick="maint" id="button" visibleWhen="N,E,R"/>
						<takhtml:button label="button.delete" onClick="maint!deleteRevenue" id="button" message="msg.confirmDelete" actionType="delete" deleteType="checkbox" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<takhtml:table id="tableRevenue" linkUri="maint" classBean="revenueItem" paging="false" hiddenTotalCount="true" requestName="request.page.bean.1" selectall="true">
						<takhtml:column label="label.maint.revenueItemCode" id="revenue_item_code" type="checkbox"/>
						<takhtml:column label="label.maint.revenueItemCode" id="revenue_item_code" linkId="revenue_item_code" link="maint" sortable="false" cssStyle="width:120px;"/>
						<takhtml:column label="label.maint.revenueItemName" id="revenue_item_name" sortable="false" cssStyle="width:150px;"/>
						<takhtml:column label="label.maint.revenueUserCode" id="revenue_user_code" sortable="false" cssStyle="width:150px;"/>
						<takhtml:column label="label.maint.gfmisDesc" id="gfmis_desc" sortable="false" cssStyle="width:150px;"/>
						<takhtml:column label="label.active" id="active_ind" sortable="false" cssStyle="width:80px;" param="SYS_YN"/>
					</takhtml:table>
				</div>
			</div>
			<div id="mailingListCon">
				<div id="labelContainer">
					<div class="content">
						<takhtml:label label="label.maint.mailingList"/>
						<takhtml:button label="button.create" onClick="maint" id="button" visibleWhen="N,E,R"/>
						<takhtml:button label="button.delete" onClick="maint!deleteRevenue" id="button" message="msg.confirmDelete" actionType="delete" deleteType="checkbox" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<takhtml:table id="tableEmail" linkUri="maint" classBean="mailingList" paging="false" hiddenTotalCount="true" requestName="request.page.bean.2" selectall="true"  cssStyle="width:30%;">
						<takhtml:column label="label.maint.email" id="mail_id" type="checkbox"/>
						<takhtml:column label="label.maint.email" id="email" linkId="mail_id" sortable="false" link="maint" cssStyle="width:100px;"/>
					</takhtml:table>
				</div>
			</div>
			<div id="labelContainer">
				<div class="content">
					<takhtml:button label="button.save" onClick="maint!save" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.viewOctopusDevice" onClick="enquire" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.viewCollectionCentre" onClick="maint!save" id="button" visibleWhen="N,E,R"/>
					<takhtml:button label="button.back" onClick="enquire" id="button" visibleWhen="N,E,R"/>
				</div>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>