<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GROUP9BANK</title>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster" />
	<link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" />
		<nav>
            <ul class="fancyNav">
                <sec:authorize access="isFullyAuthenticated()"><li id="home"><a href="${pageContext.servletContext.contextPath}/" class="homeIcon">Home</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')"><li id="createUser"><a href="${pageContext.servletContext.contextPath}/sysadmin/showRequests">Approve Requests</a></li></sec:authorize>
                
                <sec:authorize access="hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')"><li id="viewLogs"><a href="${pageContext.servletContext.contextPath}/sysadmin/viewlogs">View Logs</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, 'ROLE_MANAGER;*')"><li id="managerpage"><a href="${pageContext.servletContext.contextPath}/manager/userManagement">Manage Employees</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, '*;DEPT_HR')"><li id="newemployee"><a href="${pageContext.servletContext.contextPath}/hrdept/addEmployeeForm">Add New Employee</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, '*;DEPT_SALES')"><li id="newuser"><a href="${pageContext.servletContext.contextPath}/sales/addExternalUserForm">Add External User</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, '*;DEPT_SALES')"><li id="newaccount"><a href="${pageContext.servletContext.contextPath}/sales/addAccountForm">Add Account</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')"><li id="listaccounts"><a href="${pageContext.servletContext.contextPath}/ext/listAccounts">Browse Accounts</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')"><li id="deposit"><a href="${pageContext.servletContext.contextPath}/ext/makeDepositForm">Deposit</a></li></sec:authorize>
                <sec:authorize access="hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')"><li id="withdraw"><a href="${pageContext.servletContext.contextPath}/ext/makeWithdrawalForm">Withdraw</a></li></sec:authorize>
               	<sec:authorize access="hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')"><li id="withdraw"><a href="${pageContext.servletContext.contextPath}/ext/makeTransferForm">Transfer</a></li></sec:authorize>
               	<sec:authorize access="hasPermission(#user, 'ROLE_OFFICIAL;*')"><li id="assign"><a href="${pageContext.servletContext.contextPath}/official/setManagerForm">Set Manager</a></li></sec:authorize>
               	<sec:authorize access="hasPermission(#user, 'ROLE_OFFICIAL;*')"><li id="assign"><a href="${pageContext.servletContext.contextPath}/official/showManagers">Show Manager</a></li></sec:authorize>
               	<sec:authorize access="hasPermission(#user, '*;DEPT_TRANS:ROLE_OFFICIAL;*')"><li id="assign"><a href="${pageContext.servletContext.contextPath}/transaction/getRequests">Transaction Requests</a></li></sec:authorize>
               <sec:authorize access="hasPermission(#user, '*;DEPT_HR')"><li id="assign"><a href="${pageContext.servletContext.contextPath}/hrdept/getUnassigned">Assign Departments</a></li></sec:authorize>
               <sec:authorize access="hasPermission(#user, 'ROLE_ADMIN;DEPT_SYSADMIN')"><li id="admin"><a href="${pageContext.servletContext.contextPath}/sysadmin/viewUsers">view Users</a></li></sec:authorize>
               <sec:authorize access="hasPermission(#user, '*;DEPT_TRANS')"><li id="trans"><a href="${pageContext.servletContext.contextPath}/transaction/placeRequestForm">Request Transactions Access</a></li></sec:authorize>
               <sec:authorize access="hasPermission(#user, '*;DEPT_TRANS')"><li id="trans"><a href="${pageContext.servletContext.contextPath}/transaction/showRequests">Show Requests</a></li></sec:authorize>
               <sec:authorize access="hasPermission(#user, 'EXTERNAL_USER;EXTERNAL_USER')"><li id="trans"><a href="${pageContext.servletContext.contextPath}/ext/showRequestsForm">Show Requests</a></li></sec:authorize>
              <sec:authorize access="isFullyAuthenticated()"><li id="logout"><a href="${pageContext.servletContext.contextPath}/j_spring_security_logout">logout</a></li></sec:authorize>
              <sec:authorize access="isFullyAuthenticated()"><li id="logout"><a href="${pageContext.servletContext.contextPath}/changePassForm">change password</a></li></sec:authorize>
              
                
            </ul>
    </nav>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</head>
<body>
<h1>
	GROUP 9 BANKING SYSTEM 
</h1>
