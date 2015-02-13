<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/jquery-ui.css" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/jquery.dataTables_themeroller.css" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/demo_table_jui.css" />
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.quick.pagination.min.js"></script>

<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.dataTables.js"></script>

<script type="text/javascript">
$(document).ready(function() {
				$('#usersList').dataTable({
					"bJQueryUI" : true,
					"sPaginationType" : "full_numbers",
					"bAutoWidth": false
				});
				
			});

$(document).ready(function() {
	$("input[type=submit]").button().click(function(event) {
		
	});
});

$(document).ready(function () {
    $('#selectall').click(function () {
        $('.selected').prop('checked', isChecked('selectall'));
    });
});
function isChecked(checkboxId) {
    var id = '#' + checkboxId;
    return $(id).is(":checked");
}
function resetSelectAll() {
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    if ($(".selected").length == $(".selected:checked").length) {
        $("#selectall").attr("checked", "checked");
    } else {
        $("#selectall").removeAttr("checked");
    }

    if ($(".selected:checked").length > 0) {
        $('#edit').attr("disabled", false);
    } else {
        $('#edit').attr("disabled", true);
    }
	}

</script>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
	<h3>
			Pending Requests
		</h3><hr/>

<center>
<c:if test="${not empty listOfUsers}">
		
		<form  method="post">
		<input type="hidden"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"/>
		<input type= "submit" onClick="this.form.action='${pageContext.servletContext.contextPath}/sysadmin/approveusers'" value='Approve Users' />
		<input type= "submit" onClick="this.form.action='${pageContext.servletContext.contextPath}/sysadmin/rejectusers'" value='Reject Users' />
		
		<hr/>
		<table 	class="display dataTable"  id="usersList">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectall"></input></th>
					<th>User Name</th>
					
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="username" items="${listOfUsers}">
					<tr class="gradeX">
						<td> <input type="checkbox"  class="selected" name = "selected" value='<c:out value="${username}"></c:out>' /></td>
						<td  align="justify"><c:out
									value="${username}"></c:out></td>
						
						
								
					</tr>
				</c:forEach>
				
			</tbody>
			
		</table>
		
				</form>
				
	</c:if>
</center>
