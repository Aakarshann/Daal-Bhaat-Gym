<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../pages/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link rel="index" href="header.html">
</head>
<body>
<%
	String userType = (String) session.getAttribute("userType");
	if (userType != null && userType.equals("admin")) {
		// Include admin header
	%><h1>admin role session</h1>
	<%
	} else if (userType != null && userType.equals("user")) {
	// Include regular header
	%><h1>Ola brother user session</h1>
	<%
	} else {
	// No session, handle accordingly
	%><h1>Session kunei pani chaina but filter chalena huhut</h1>
	<%
	}
	%>
	<h1>WRAHDBAHWIDBIAWHDBAIHWDIAHW</h1>
</body>
</html>