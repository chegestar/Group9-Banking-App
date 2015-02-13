<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Employee</title>
</head>
<body>
<center>
<form:form commandName="user" method="POST" action="${pageContext.servletContext.contextPath}/manager/updateEmployee">

   <table>
    <tr>
        <td><form:label path="userName">User Name</form:label></td>
        <td><form:input path="userName" readonly="true"/></td>
        <td style="font: bold;color: red;"><form:errors path="userName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="department">Department</form:label></td>
        <td><form:input path="department" readonly="true"/></td>
        <td style="font: bold;color: red;"><form:errors path="userName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="contactNumber">contactNumber</form:label></td>
        <td><form:input path="contactNumber" readonly="true"/></td>
        <td style="font: bold;color: red;"><form:errors path="contactNumber" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="emailId">emailId</form:label></td>
        <td><form:input path="emailId" readonly="true"/></td>
        <td style="font: bold;color: red;"><form:errors path="emailId" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="roleId">roleId</form:label></td>
        <td><form:select path="roleId" >
        <form:option value="ROLE_ADMIN">ADMIN</form:option>
        <form:option value="ROLE_REGULAREMPLOYEE">RegularEmployee</form:option>
        <form:option value="ROLE_MANAGER">Manager</form:option>
        <form:option value="ROLE_OFFICIAL">Official</form:option>
        </form:select></td>
        <td style="font: bold;color: red;"><form:errors path="roleId" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Update User"/>
        </td>
    </tr>
    </table>
</form:form>
</center>
</body>
</html>