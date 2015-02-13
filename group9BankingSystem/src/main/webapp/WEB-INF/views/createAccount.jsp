<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body><center>
CREATE ACCOUNT PAGE
<form:form commandName="account" method="POST" action="${pageContext.servletContext.contextPath}/sales/addAccount">
  
   <table>
    <tr>
        <td><form:label path="type">Account Type</form:label></td>
         <td><form:select path="type" >
        <form:option value="PERSONAL_ACCOUNT">Personal Account</form:option>
        <form:option value="MERCHANT_ACCOUNT">Merchant Account</form:option>
        </form:select></td>
        <td style="font: bold;color: red;"><form:errors path="type" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="balance">Balance</form:label></td>
        <td><form:input path="balance" /></td>
        <td style="font: bold;color: red;"><form:errors path="balance" cssClass="error" /></td>
    </tr>
    <tr>
    <td><form:label path="userId">UserName</form:label></td>
        <td><form:input path="userId" /></td>
        <td style="font: bold;color: red;"><form:errors path="userId" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add Account"/>
        </td>
    </tr>
</table>  
</form:form>
</center>
</body>
</html>