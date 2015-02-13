<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="../header.jsp"></jsp:include>

<body><center>
Show Access Page
<form method="POST" action="${pageContext.servletContext.contextPath}/ext/showRequests">
  
   <table>
    
    <tr>
    
        <td> Account</td>
        <td><select name="account" >
        <c:forEach var="account" items="${accounts}">
        <option value="${account.accountId }">${account.accountId }</option>
        </c:forEach>
        </select>
        
        </td>
        
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Show who has access"/>
        </td>
    </tr>
</table> 
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> 
</form>
</center>
</body>
</html>