<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="login">
<fieldset>
  <legend><spring:message code="welcome" text="Bienvenido"/></legend>	
	<div>
	  <label for="usuario_username"><spring:message code="username" text="Username"/></label><br/>
	  <span id="usuario_username" class="userRegistered"><em>${user.username }</em></span>
	</div>
	<br/>
	<div>
	   <label for="usuario_dni"><spring:message code="dni" text="DNI"/></label><br/>
	   <span id="usuario_dni" class="userRegistered"><em>${user.dni }</em></span>
	</div>	
</fieldset>	
</div>