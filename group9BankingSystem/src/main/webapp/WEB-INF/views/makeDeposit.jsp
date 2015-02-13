<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<jsp:include page="header.jsp"></jsp:include>
<head>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.js"></script>
<script>
$().ready(function(){
	$("#depositForm").validate();
});
</script>
<script type="text/css">
#depositForm { width: 500px; }
#depositForm label { width: 250px; }
#depositForm label.error, #depositForm input.submit { margin-left: 253px; }

</script>
</head>
<body><center>
CREATE ACCOUNT PAGE
<form:form id="depositForm" commandName="transaction" method="POST" action="${pageContext.servletContext.contextPath}/ext/makeDeposit">
  <form:hidden path="type" value="DEPOSIT"/>
   <table>
    <tr>
        <td><form:label path="amount">Amount</form:label></td>
        <td><form:input path="amount" class="required"/></td>
        <td style="font: bold;color: red;"><form:errors path="amount" cssClass="error" /></td>
    </tr>
    <tr>
    
        <td><form:label path="destAccount">Destination Account</form:label></td>
        <td><form:select path="destAccount" class="required">
        <c:forEach var="account" items="${listOfAccounts}">
        <form:option value="${account.accountId }">${account.accountId }</form:option>
        </c:forEach>
        </form:select>
        
        </td>
        <td style="font: bold;color: red;"><form:errors path="destAccount" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Make Deposit"/>
        </td>
    </tr>
</table>  
</form:form>
</center>
</body>
</html>