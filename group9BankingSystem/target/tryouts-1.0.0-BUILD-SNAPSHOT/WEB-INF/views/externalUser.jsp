<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>

<center>
<h1>Register External User </h1>
<form:form commandName="user" method="POST" action="${pageContext.servletContext.contextPath}/sales/addExternalUser">
  
   <table>
    <tr>
        <td><form:label path="exLastName">Last Name</form:label></td>
        <td><form:input path="exLastName" /></td>
        <td style="font: bold;color: red;"><form:errors path="exLastName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exFirstName">First Name</form:label></td>
        <td><form:input path="exFirstName" /></td>
        <td style="font: bold;color: red;"><form:errors path="exFirstName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exSsn">SSN</form:label></td>
        <td><form:input path="exSsn" /></td>
        <td style="font: bold;color: red;"><form:errors path="exSsn" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exEmailId">emailId</form:label></td>
        <td><form:input path="exEmailId" /></td>
        <td style="font: bold;color: red;"><form:errors path="exEmailId" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exAddress">Address</form:label></td>
        <td><form:textarea path="exAddress" /></td>
        <td style="font: bold;color: red;"><form:errors path="exAddress" cssClass="error" /></td>
    </tr>
    
    <tr>
        <td><form:label path="exGender">gender</form:label></td>
        <td><form:input path="exGender" /></td>
        <td style="font: bold;color: red;"><form:errors path="exGender" cssClass="error" /></td>
        </tr>
        
      <tr>
        <td><form:label path="exZipCode">zipCode</form:label></td>
        <td><form:input path="exZipCode" /></td>
        <td style="font: bold;color: red;"><form:errors path="exZipCode" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exContactNumber">contactNumber</form:label></td>
        <td><form:input path="exContactNumber" /></td>
        <td style="font: bold;color: red;"><form:errors path="exContactNumber" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="exUserName">userName</form:label></td>
        <td><form:input path="exUserName" /></td>
        <td style="font: bold;color: red;"><form:errors path="exUserName" cssClass="error" /></td>
    </tr>
    
    
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Register External User"/>
        </td>
    </tr>
</table>  
</form:form>
</center>
</body>
</html>