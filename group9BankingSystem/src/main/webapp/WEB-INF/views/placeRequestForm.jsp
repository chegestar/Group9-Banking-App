<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place Requests</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<center>
	<c:if test="${not empty accounts}">
	<form action="${pageContext.servletContext.contextPath}/transaction/placeRequest" method="POST">
		<table>
			<tr>
				<td>Place request For:</td>
				<td><select name="account">
						<c:forEach var="account" items="${accounts}">
							<option value="${account}">${account}</option>
						</c:forEach>
				</select></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit"
					value="Request Access" /></td>
			</tr>
		</table>
		 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	</form>
	</c:if>
	</center>
</body>
</html>