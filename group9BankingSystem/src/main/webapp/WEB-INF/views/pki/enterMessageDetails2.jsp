<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Secure Banking System</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.js"></script>
	
	

</head>


<body oncontextmenu="disableRightClick()">
	<div class="container">
		
		<hr></hr>
		<div class="row">
			 
			<div class="span9">
				<form:form method="GET" commandName="certificateFormBean"
					action="${pageContext.servletContext.contextPath}/ext/pki/obtainCertificateForVerification">
					
					
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
					
					<div align = "left"><h5>Enter The User-Name Of The User Whose Certificate You Want To Verify</h5></div>


					<table>
						<tr>
							<td><form:label path="">Sender User Name:  </form:label></td>
							<td><form:input path="senderUserName"></form:input></td>
							<%-- <td><label>${ certificateFormBean.serialNumber}</label></td>
						 --%></tr>
						
					</table>
					<div class="control-group" align="left">
						<input type="submit" value="View Certificate Of Sender" name="Send Certificate" class="btn btn-success btn-sm" />
					</div>
				</form:form>
			</div>
		</div>
		
	</div>		
</body>
</html>