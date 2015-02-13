<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/jquery-ui.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/jquery.dataTables_themeroller.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/demo_table_jui.css" />
<script
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/jquery.quick.pagination.min.js"></script>

<script
	src="${pageContext.servletContext.contextPath}/resources/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#list').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bAutoWidth" : false
		});

	});
</script>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<table class="display dataTable" id="list">
		<thead>
			<tr>
				<th>User Name</th>
				<th>Last Name</th>
				<th>First Name</th>

				<th>Salary</th>
				<th>Role</th>
				<th>Contact Number</th>
				<th>E-Mail Address</th>
				<th> Remove Link</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="user" items="${listOfUsers}">
				<tr class="gradeX">
					<td align="justify"><a
						href="${pageContext.servletContext.contextPath}/manager/modifyUser/${user.userName}">
							<c:out value="${user.lastName}"></c:out>
					</a></td>
					<td align="justify"><c:out value="${user.firstName}"></c:out></td>
					<td align="justify"><c:out value="${user.lastName}"></c:out></td>
					<td align="justify"><c:out value="${user.salary}"></c:out></td>
					<td align="justify"><c:out value="${user.roleId}"></c:out></td>
					<td align="justify"><c:out value="${user.contactNumber}"></c:out></td>
					<td align="justify"><c:out value="${user.emailId}"></c:out></td>
					<td align="justify"><a 	href="${pageContext.servletContext.contextPath}/manager/removeEmployee/${user.userName}">Remove Employee</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</body>
</html>