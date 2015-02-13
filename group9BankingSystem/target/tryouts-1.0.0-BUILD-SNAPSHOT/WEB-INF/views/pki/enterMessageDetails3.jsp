<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	
<jsp:include page="../header.jsp"></jsp:include>
</head>


<body oncontextmenu="disableRightClick()">
	<div class="container">
		
		<hr></hr>
		<div class="row">
			 
			<div class="span9">
				<form:form method="GET" commandName="certificateFormBean"
					action="${pageContext.servletContext.contextPath}/ext/pki/verifyCertificate">
					
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

					<div align = "left"><h4>Re-confirm The Sender Details To Get His/Her Certificate Verified </h4></div>


					<table>
						<tr>
							<td><form:label path="">Sender's User-Name:  </form:label></td>
							<td><form:input path="senderUserName"></form:input></td>
							</tr>
						
					</table>
					<div class="control-group" align="left">
						<input type="submit" value="Verify Certificate" name="Send Certificate" />
					</div>
				</form:form>
			</div>
		</div>
	
	</div>		
</body>
</html>