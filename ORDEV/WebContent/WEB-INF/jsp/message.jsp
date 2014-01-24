<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="center">
	<s:if test="actionMessages != null">
		<s:actionmessage/>
	</s:if>
	<s:if test="actionErrors != null">
		<s:actionerror/>
	</s:if>
</div>