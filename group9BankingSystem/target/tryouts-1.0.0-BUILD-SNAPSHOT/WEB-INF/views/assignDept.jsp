<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Department</title>
</head>
<body>
	<center>
	<c:if test="${not empty listOfUsers}">
	<form action="${pageContext.servletContext.contextPath}/hrdept/updateDepartment" method="POST">
		<table>
			<tr>
				<td>Assign Department For:</td>
				<td><select name="username">
						<c:forEach var="user" items="${listOfUsers}">
							<option value="${user.userName}">${ user.userName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><select name="department">
						<option value="DEPT_HR">Human Resources</option>
						<option value="DEPT_SYSADMIN">System Administration</option>
						<option value="DEPT_TRANS">Transaction Monitoring</option>
						<option value="DEPT_SALES">Sales</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="Assign Deptartment" /></td>
			</tr>
		</table>
		 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	</form>
	</c:if>
	</center>
</body>
</html>