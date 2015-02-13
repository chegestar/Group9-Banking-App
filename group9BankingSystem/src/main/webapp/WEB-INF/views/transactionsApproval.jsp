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
<jsp:include page="header.jsp"></jsp:include>
<body>
	<table class="display dataTable" id="list">
		<thead>
			<tr>

				<th>Id</th>
				<th>Amount</th>
				<th>Destination</th>
				<th>Origin</th>
				<th>Type</th>
				<th>Approval/Rejection</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="transaction" items="${listOfTrans}">
				<tr class="gradeX">
					<td align="justify"><c:out value="${transaction.transId}"></c:out></td>
					<td align="justify"><c:out value="${transaction.amount}"></c:out></td>

					<td align="justify"><c:out value="${transaction.destAccount}"></c:out></td>
					<td align="justify"><c:out value="${transaction.originAccount}"></c:out></td>
					<td align="justify"><c:out value="${transaction.type}"></c:out></td>
					<td align="justify"><a href="${pageContext.servletContext.contextPath}/transaction/approve/${transaction.transId}">approve</a>  || <a href="${pageContext.servletContext.contextPath}/transaction/reject/${transaction.transId}">reject</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</body>
</html>