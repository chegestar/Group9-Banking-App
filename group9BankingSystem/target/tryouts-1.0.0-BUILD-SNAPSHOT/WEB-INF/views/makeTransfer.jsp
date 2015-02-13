<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp"></jsp:include>
<body><center>
MAKE TRANSFER
<form:form commandName="transaction" method="POST" action="${pageContext.servletContext.contextPath}/ext/makeTransfer">
  <form:hidden path="type" value="TRANSFER"/>
   <table>
    <tr>
        <td><form:label path="amount">Amount</form:label></td>
        <td><form:input path="amount" /></td>
        <td style="font: bold;color: red;"><form:errors path="amount" cssClass="error" /></td>
    </tr>
    <tr>
    
        <td><form:label path="destAccount">Destination Account</form:label></td>
        <td><form:input path="destAccount" /></td>
        <td style="font: bold;color: red;"><form:errors path="destAccount" cssClass="error" /></td>
    </tr>
    
     <tr>
    
        <td><form:label path="originAccount">Origin Account</form:label></td>
        <td><form:select path="originAccount" >
        <c:forEach var="account" items="${listOfAccounts}">
        <form:option value="${account.accountId}">${account.accountId}</form:option>
        </c:forEach>
        </form:select>
       	</td>
        <td style="font: bold;color: red;"><form:errors path="originAccount" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Transfer"/>
        </td>
    </tr>
</table>  
</form:form>
</center>
</body>
</html>