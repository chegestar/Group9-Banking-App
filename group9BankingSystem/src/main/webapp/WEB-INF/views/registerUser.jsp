<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<center>
<h1>Register New Employee</h1>
<form:form commandName="user" method="POST" action="${pageContext.servletContext.contextPath}/hrdept/addEmployee">
  
   <table>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
        <td style="font: bold;color: red;"><form:errors path="lastName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" /></td>
        <td style="font: bold;color: red;"><form:errors path="firstName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="ssn">SSN</form:label></td>
        <td><form:input path="ssn" /></td>
        <td style="font: bold;color: red;"><form:errors path="ssn" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="emailId">emailId</form:label></td>
        <td><form:input path="emailId" /></td>
        <td style="font: bold;color: red;"><form:errors path="emailId" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="address">Address</form:label></td>
        <td><form:textarea path="address" /></td>
        <td style="font: bold;color: red;"><form:errors path="address" cssClass="error" /></td>
    </tr>
    
    <tr>
        <td><form:label path="gender">gender</form:label></td>
        <td><form:input path="gender" /></td>
        <td style="font: bold;color: red;"><form:errors path="gender" cssClass="error" /></td>
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
        <td><form:label path="zipCode">zipCode</form:label></td>
        <td><form:input path="zipCode" /></td>
        <td style="font: bold;color: red;"><form:errors path="zipCode" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="contactNumber">contactNumber</form:label></td>
        <td><form:input path="contactNumber" /></td>
        <td style="font: bold;color: red;"><form:errors path="contactNumber" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="userName">userName</form:label></td>
        <td><form:input path="userName" /></td>
        <td style="font: bold;color: red;"><form:errors path="userName" cssClass="error" /></td>
    </tr>
   
    <tr>
        <td><form:label path="department">Department</form:label></td>
         <td><form:select path="Department" >
        <form:option value="DEPT_HR">Human Resources</form:option>
        <form:option value="DEPT_SYSADMIN">System Administration</form:option>
        <form:option value="DEPT_TRANS">Transaction Monitoring</form:option>
        <form:option value="DEPT_SALES">Sales</form:option>
        </form:select></td>
        <td style="font: bold;color: red;"><form:errors path="department" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="salary">Salary</form:label></td>
        <td><form:input path="salary" /></td>
        <td style="font: bold;color: red;"><form:errors path="salary" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Register New Employee User"/>
        </td>
    </tr>
</table>  
</form:form>
</center>
</body>
</html>