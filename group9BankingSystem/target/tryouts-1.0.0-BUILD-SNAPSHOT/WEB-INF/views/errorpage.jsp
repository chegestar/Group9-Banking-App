<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<header>
	<h2>Ups... sorry, something went wrong.</h2>
	<span class="byline">Error</span>
</header>

<section>
	<c:choose>
		<c:when test="${not empty ex_message}">
		<c:out value="${ex_message}"></c:out></c:when>
		<c:otherwise>
	Sorry, We will update the admin and he will  get back to you.
	</c:otherwise>
	</c:choose>
</section>