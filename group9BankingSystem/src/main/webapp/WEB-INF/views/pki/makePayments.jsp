<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make Payments</title>
</head>
<body>
	<form:form method="POST" commandName="certificateFormBean"
		action="${pageContext.servletContext.contextPath}/ext/pki/sendPayment">
		<c:if test="${ errorMessage != null}">
			<div style="font-size: 15px; color: red;">
				<div id="status_message">${errorMessage}</div>
			</div>

		</c:if>
		<c:if test="${ certificateStatusMessage != null}">
			<div style="font-size: 15px; color: green;">
				<div id="status_message">${certificateStatusMessage}</div>
			</div>

		</c:if>

		<div align="left">
			<h5>Enter The Payment Details</h5>
		</div>


		<table>
			<tr>
				<td><form:label path="">Destination Merchant:  </form:label></td>
				<td><form:input path="destinationUserName"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="">Amount:  </form:label></td>
				<td><form:input path="message"></form:input></td>
			</tr>

			<tr>
				<td><form:label path="">Source Account Id:  </form:label></td>
				<td><form:input path="destinationEmailId"></form:input></td>
			</tr>
		</table>
		
			<input type="submit" value="Make Payment" name="Make Payment"/>
		
	</form:form>
</body>
</html>