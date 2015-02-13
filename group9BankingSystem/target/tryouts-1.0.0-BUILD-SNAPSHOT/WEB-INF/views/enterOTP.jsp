<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp"></jsp:include>
<body><center>
<form method="POST" action="${pageContext.servletContext.contextPath}/ext/checkOTP/${transId}">
  
   <table>
    <tr>
        <td>KEY</td>
        <td><input type = "password" name="key"/></td>
        <td style="font: bold;color: red;"></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Confirm"/>
        </td>
    </tr>
    
    </table>
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form>
    </center>
</body>
</html>