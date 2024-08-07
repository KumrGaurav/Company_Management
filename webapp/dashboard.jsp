<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
</head>
<body>
	<%
	String userName = request.getParameter("userName");
	session.setAttribute("userName", userName);
	%>

	<s:if test="%{#session.dashboard == 'admin'}">
		<jsp:forward page="/admin.jsp" />
	</s:if>
	<s:if test="%{#session.dashboard == 'companyUserDashboard'}">
		<jsp:forward page="/companyUserDashboard.jsp?userName=${userName}" />
	</s:if>
	<s:elseif test="%{#session.dashboard == 'employees'}">
		<jsp:forward page="employeeDashboard.jsp" />
		<%-- Print message in console for 'employees' dashboard --%>
		<%
		System.out.println("Employee Dashboard is being accessed.");
		%>
	</s:elseif>
	<h1>Redirecting...</h1>
	<div>
		Sorry...some error occured
		<s:property value="#session.dashboard" />
	</div>

</body>
</html>


