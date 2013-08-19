<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<c:choose>
<c:when test="${not empty sessionScope.user }">
	<jsp:include page="/WEB-INF/jsp/usuario/registered.jsp" />
</c:when>
<c:otherwise>  
	<jsp:include page="/WEB-INF/jsp/usuario/signIn.jsp" />  
</c:otherwise>	
</c:choose>