<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	
</head>


<body oncontextmenu="disableRightClick()">
	
	
		<hr></hr>
		
			 
			<div class="span9">
				<form:form method="GET" commandName="certificateFormBean"
					action="${pageContext.servletContext.contextPath}/ext/pki/enterMessageDetails">
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

					<div align = "left"><h4>User Certificate</h4></div>
					<table>
						
						<tr>
							<td><form:label path="issuerName">Certificate Issuer Name:  </form:label></td>
							<td><form:input disabled="true"  path="issuerName"></form:input></td>
							<%-- <td><label>${ certificateFormBean.issuerName}</label></td>
						 --%></tr>
						<tr>
							<td><form:label path="validFrom">Certificate Valid From:  </form:label></td>
							<td><form:input disabled="true"  path="validFrom"></form:input></td>
							<%-- <td><label>${ certificateFormBean.validFrom}</label></td>
						 --%></tr>
						<tr>
							<td><form:label path="validTo">Certificate Valid To:  </form:label></td>
							<td><form:input disabled="true"  path="validTo"></form:input></td>
							<%-- <td><label>${ certificateFormBean.validTo}</label></td>
							 --%></tr>
						<tr>
							<td><form:label path="userPublicKey">Your Public Key (Scroll Down):  </form:label></td>
							<td><form:textarea disabled="true"  path="userPublicKey"></form:textarea></td>
							<%-- <td><label>${ certificateFormBean.userPublicKey}</label></td>
						 --%></tr>
						<tr>
							<td><form:label path="version">X509 Certificate Version:  </form:label></td>
							<td><form:input disabled="true"  path="version"></form:input></td>
							<%-- <td><label>${ certificateFormBean.version}</label></td>
						 --%></tr>
						<tr>
							<td><form:label path="caSignature">Bank CA Signature:  </form:label></td>
							<td><form:input disabled="true"  path="caSignature"></form:input></td>
							<%-- <td><label>${ certificateFormBeancaSignature}</label></td>
						 --%></tr>
						 
					</table>
					<div class="control-group" align="left">
						<input type="submit" value="Send To User" name="Send Certificate" class="btn btn-success btn-sm" />
					</div>
				</form:form>
			
	
	</div>		
</body>
</html>