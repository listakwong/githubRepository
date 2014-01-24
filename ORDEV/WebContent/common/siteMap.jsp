<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tlds/takhtml.tld" prefix="takhtml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include flush="true" page="/WEB-INF/jsp/html.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9; IE=8; IE=7; IE=EDGE"></meta>
		<title><s:text name="title.lcsd_ocrs"/> - <s:text name="title.siteMap"/></title>
	</head>
	<body leftmargin="0" topmargin="5px" marginwidth="0" marginheight="0" class="body">
		<div id="pagewrap">
			<form id="fr" action="siteMap!*" method="post">
				<takhtml:banner/>
				<takhtml:menu/>
				<div id="labelContainer">
					<div class="content">
						<takhtml:title label="title.siteMap"/>
					</div>
				</div>
				<div id="labelContainer">
					<takhtml:siteMap type="site"/>
				</div>
			</form>
		</div>
	</body>
</html>