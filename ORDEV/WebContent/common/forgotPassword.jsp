<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>

<html>
	<script language="javascript" type="text/javascript">
	
	</script>
	<head>
	<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
	<title><s:text name="title.lcsd_ocrs" /> - <s:text name="title.forgotPassword" /></title>
	</head>
	<body leftmargin="0" topmargin="15px" marginwidth="0" marginheight="15px" class="body" >
		<div id="pagewrap">
			<form id="fr" action="forgetPassword!*" method="post">
				<takhtml:hidden id="loginBean.mode"/>
				<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
				<div id="labelContainer">
					<div class="content">
						<takhtml:title label="title.forgotPassword"/>
					</div>
				</div>
				<!-- Start the code here -->
				<br>
				<div id="labelContainer">
					<div class="content">
						<takhtml:textField label="label.common.enterUserId" id="loginBean.user_id" maxlength="20" size="20" mandatory="true" cssStyleText="text-transform:lowercase" editableWhen="N,E,R" visibleWhen="N,E,R"/>
					</div>
				</div>
				<div id="labelContainer">
					<div class="content">
						<takhtml:button label="button.submit" onClick="forgetPassword!reGenPassword" id="submit" visibleWhen="N,E,R"/>
						<takhtml:button label="button.back" onClick="login" id="back" visibleWhen="N,E,R"/>
					</div>
				</div>
				<!-- End the code here -->
			</form>
		</div>
	</body>
</html>