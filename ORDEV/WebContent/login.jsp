<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>
<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"> 
		<title><s:text name="title.lcsd_ocrs"/> - <s:text name="title.loginPage"/></title>
	</head>
	<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
		<div id="pagewrap">
			<form id="fr" action="login!*" method="post">
				<takhtml:hidden id="loginBean.mode"/>
				<div class="center">
					<!-- <img src="../images/login_page.jpg" border="0"> -->
				</div>
				<div class="center">
					<div style="float: left;width: 333px;height: 250px;">
						<!-- <img src="../images/octopus.jpg" border="0"> -->
					</div>
					<div style="float: right;width: 500px;">
						<div style="height: 100px"></div>
						<div style="height: 50px;vertical-align: bottom;">
							<jsp:include page="/WEB-INF/jsp/message.jsp"></jsp:include>
						</div>
						<div id="labelContainer">
							<div class="loginLabel">
								<takhtml:title label="title.loginPage"/>
							</div>
						</div>
						<div id="labelContainer">
							<div class="loginLabel">
								<takhtml:textField label="label.common.userId" id="loginBean.user_id" cssStyleText="width:150px;text-transform:lowercase;" mandatory="true" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
							</div>
						</div>
						<div id="labelContainer">
							<div class="loginLabel">
								<takhtml:password label="label.common.password" id="loginBean.password" cssStyleText="width:150px;" mandatory="true" size="20" maxlength="20" editableWhen="N,E,R" visibleWhen="N,E,R"/>
							</div>
						</div>
						<div id="labelContainer">
							<div class="loginLabel">	
								<takhtml:button label="button.login" onClick="login!login" id="login" enterEvent="true" visibleWhen="N,E,R"/>
							</div>
						</div>
						<div id="labelContainer">
							<div class="loginLabel">
								<takhtml:link label="label.link.forgotPassword" onClick="forgetPassword" id="forgetPassword"/>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>