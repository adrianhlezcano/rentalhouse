<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="formulario"> 
 	<form:form method="POST" modelAttribute="usuarioForm">
	<fieldset>		
	  <legend>Iniciar Sesion con Credenciales</legend>	 
	  <ul>
	    <form:errors element="li" path="username" cssClass="error"/>
		<form:errors element="li" path="preguntaSeguridad" cssClass="error"/>
		<form:errors element="li" path="respuestaSeguridad" cssClass="error"/> 
		<form:errors element="li" path="email" cssClass="error"/>   
	  </ul>		  	
	  <p>	  
	    <label for="usuario_username"><spring:message code="username" text="Username"/></label>
		<form:input path="username" maxlength="25" id="usuario_username"/><br/>
	    	     
		<label for="usuario_preguntaSeguridad"><spring:message code="preguntaSeguridad" text="Pregunta Seguridad"/></label>
		<form:select path="preguntaSeguridad" id="usuario_preguntaSeguridad">
		  <form:options items="${usuarioForm.preguntas }"/>
	    </form:select><br/>
		
		<label for="usuario_respuestaSeguridad"><spring:message code="respuestaSeguridad" text="Respuesta Seguridad"/></label>
		<form:password path="respuestaSeguridad" maxlength="15" id="usuario_respuestaSeguridad"/><br/>
		
		<label for="usuario_email"><spring:message code="email" text="mail"/></label>
        <form:input path="email" maxlength="50" id="usuario_email" placeholder="example@mail.com"/><br/>
		    
	    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
	  </p>	  
	</fieldset>
  </form:form>
</div>