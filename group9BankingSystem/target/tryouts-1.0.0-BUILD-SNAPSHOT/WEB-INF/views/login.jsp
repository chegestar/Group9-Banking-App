<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" />
<script type="text/javascript">
 var RecaptchaOptions = {
    theme : 'clean'
 };
 </script>

<body><center>
<c:if test="${not empty error}">
		
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	
	</c:if>
	
	 <br><br>
 <h1><center>GROUP 9 Banking</center></h1>
<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 

		<table>
			<tr>
				<td>USERNAME:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>PASSWORD:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			
				
		</table>
		
		<div class="recaptcha_area" id="recaptcha_area" height=200 width=200>
		<%
        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Ld4RekSAAAAAH1lXz8YmZOsDfoEeEs7kL8U2afN", "6Ld4RekSAAAAAGCe5sAo87jkuHE21Hfqg1WAqLpf", false);
		((ReCaptchaImpl) c).setRecaptchaServer("https://www.google.com/recaptcha/api");
       out.print(c.createRecaptchaHtml(null, null));    %>
		</div>
     
    <input type="hidden"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"/>
    <table>
    <tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
    </table>
 
	</form>
	</center>
</body>
</html>