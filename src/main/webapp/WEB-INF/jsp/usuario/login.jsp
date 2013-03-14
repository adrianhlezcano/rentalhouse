<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="login">
<fieldset>
  <legend><spring:message code="iniciarSession" text="Iniciar Sesion"/></legend>
	<form method="POST" action="usuario/login">
	<div>
	  <label for="user"><spring:message code="username" text="Username"/></label><br/>
	  <input type="text" name="username" id="user" placeHolder="juan123"/>
	</div>
	<div>
	  <label for="passw"><spring:message code="password" text="Password"/></label><br/>
	  <input type="password" name="password" id="passw" placeHolder="******"/>			
	</div>
	<div>
	  <span id="error"></span><br/>
	  <input type="submit" value="<spring:message code='loguearse' text='Ingresar'/>"/>
	</div>
	</form>
	<div>
	  <p>
	    <a href="<spring:url value='/usuario/registrar'/> ">
	      <spring:message code="registrarme" text="Registrarse" />								
		</a><br/>
		<a href="<spring:url value="/usuario/registrar"/>">
		  <spring:message code="forgotPassword" text="Olvido su Password?"/>
		</a>
	  </p>
	</div>			
</fieldset>		
</div>