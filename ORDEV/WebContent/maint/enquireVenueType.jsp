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
		<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.maint.enquireVenueType" /></title>
	</head>
<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
	<div id="pagewrap">
		<form id="fr" action="enquire!*" method="post">
			<takhtml:hidden id="venueType.mode"/>
			<takhtml:banner/>
			<takhtml:menu/>
			<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
			<div id="labelContainer">
				<div class="content">
					<takhtml:title label="title.maint.enquireVenueType"/>
				</div>
			</div>
			<div id="criteriaContainer">
				<div class="content">
					<takhtml:textField id="venueType.venue_type_code" label="label.maint.venueTypeCode" size="20" maxlength="20" editableWhen="N,R,E" visibleWhen="N,E,R"/>
					<takhtml:textField id="venueType.venue_type_name" label="label.maint.venueTypeName" size="30" maxlength="100" editableWhen="N,R,E" visibleWhen="N,E,R"/>
				</div>
				<div class="content">
					<takhtml:select id="venueType.active_ind" param="SYS_YN" method="syscode" label="label.activeInd" all="true" editableWhen="N,E,R" visibleWhen="N,E,R"/>
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
				<takhtml:table id="table" linkUri="enquire!search" classBean="venueType" selectall="true">
					<takhtml:column label="label.maint.venueTypeCode" id="venue_type_code" type="checkbox"/>
					<takhtml:column label="label.maint.venueTypeCode" id="venue_type_code" linkId="venue_type_code" sortable="false" link="maint" cssStyle="width:80px;"/>
					<takhtml:column label="label.maint.venueTypeName" id="venue_type_name" sortable="false" cssStyle="width:200px;"/>
					<takhtml:column label="label.active" id="active_ind" sortable="false" cssStyle="width:50px;" param="SYS_YN"/>
				</takhtml:table>
			</div>
			<takhtml:siteMap type="map"/>
		</form>
	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/dialog.jsp"/>
</body>
</html>