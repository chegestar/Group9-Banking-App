<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
					action="${pageContext.servletContext.contextPath}/ext/pki/verifySignature">
					<table>

						<tr>
							<td><form:label path="">Sender's User Name :  </form:label></td>
							<td><form:input path="senderUserName"></form:input></td>
						</tr>
						<tr>
							<td><form:label path="">Enter the exact message received in e-mail:  </form:label></td>
							<td><form:input path="message"></form:input></td>
						</tr>
						<tr>
							<td><form:label path="">Enter the exact signature of sender received in e-mail:  </form:label></td>
							<td><form:input path="signature"></form:input></td>
						</tr>

					</table>
					<div class="control-group" align="center">
						<input type="submit"
							value="Decrypt Message Using Sender's Public Key"
							name="DecryptMessage" class="btn btn-success btn-sm" />
					</div>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>