<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<jsp:include page="../header.jsp"></jsp:include>
</head>
<body>
<center>
<p style="font: bold;color: red;"><c:out value="${error}"></c:out></p>
	<form:form commandName="change" method="POST"
		action="${pageContext.servletContext.contextPath}/changePass">
		<table>

			<tr>
				<td><form:label path="oldPass">Current Password</form:label></td>
				<td><form:password path="oldPass" /></td>
				<td style="font: bold;color: red;"><form:errors path="oldPass" cssClass="error" /></td>
			</tr>
			<tr>

				<td><form:label path="newPass">New Password</form:label></td>
				<td><form:password path="newPass" /></td>
<td style="font: bold;color: red;"><form:errors path="newPass" cssClass="error" /></td>
			</tr>
			<tr>

				<td><form:label path="confirmPass">Confirm New Password</form:label></td>
				<td><form:password path="confirmPass" /></td>
<td style="font: bold;color: red;"><form:errors path="confirmPass" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="change password" />
				</td>
			</tr>
		</table>

	</form:form>
	</center>
</body>
</html>