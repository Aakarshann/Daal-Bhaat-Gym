
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
// Get the session and request objects
HttpSession userSession = request.getSession(false);
String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
String contextPath = request.getContextPath();
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/home.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>
</head>
<body>
	<!-- Nav bar with logo and links -->
	<div class="headingall" style="background-color: black;">
		<table>
			<tr class="headingflex">
				<td>
					<!-- Logo that links to home page --> <a href="Home.html"><img
						src="<%=contextPath%>/resources/images/others/logo.jpeg" alt="logo"></a>
				</td>
				<td>
					<!-- Heading that links to home page -->
					<h1 class="heading" style="background-color: black;">DAALBHAAT</h1>
				</td>
				<td>
					<!-- The main navigation menu of the home page to lead to other pages -->
					<div id="Sidenav" class="topnav">
						<a href="<%=contextPath + StringUtils.PAGE_URL_PLAN%>">Plans</a> <a
							href="<%=contextPath + StringUtils.PAGE_URL_HOME%>">Home</a>
							<a
							href="<%=contextPath + StringUtils.PAGE_URL_ADMIN%>">Dashbord</a>

						<%
						// Conditionally set the action URL based on user session
						if (currentUser != null) {
						%>
						<form action="<%=request.getContextPath()%>/LogoutServlet"
							method="post">
							<input type="submit" value="Logout">
						</form>
						<%
						} else {
						out.print(contextPath + StringUtils.PAGE_URL_LOGIN);
						}
						%>
					</div>
					</td>
			</tr>
		</table>
	</div>

	<!-- Main content area -->
	<div class="main-content">
		<!-- Include your JSP logic here -->
	</div>

	<!-- Footer section -->
	<footer>
		<!-- Footer content here -->
	</footer>
</body>
</html>