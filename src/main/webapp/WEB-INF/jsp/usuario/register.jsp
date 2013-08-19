<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="formulario"> 
 	<form:form method="POST" modelAttribute="usuarioForm">
	<form:hidden path="idPersona"/>
	<form:hidden path="action"/>		
	<fieldset>		
	  <legend>Registrarse</legend>	 
	  <ul>
	    <form:errors element="li" path="apellido" cssClass="error"/>	
	    <form:errors element="li" path="nombre" cssClass="error" />
	    <form:errors element="li" path="username" cssClass="error"/>
	    <form:errors element="li" path="password" cssClass="error"/>
		<form:errors element="li" path="password2" cssClass="error"/>
		<form:errors element="li" path="preguntaSeguridad" cssClass="error"/>
		<form:errors element="li" path="respuestaSeguridad" cssClass="error"/>
	    <form:errors element="li" path="dni" cssClass="error"/>
	    <form:errors element="li" path="cuit" cssClass="error"/>
	    <form:errors element="li" path="email" cssClass="error"/>
	    <form:errors element="li" path="telefono" cssClass="error"/>	    
	  </ul>		  	
	  <p>	  
	    <!-- row 0 -->
	    <label for="usuario_apellido"><spring:message code="apellido" text="Apellido"/></label>
	  	<form:input path="apellido" maxlength="25" id="usuario_apellido"  />		  	 
	  	
        <br/> <!-- row 1 -->
	  	<label for="usuario_nombre"><spring:message code="nombre" text="Nombre"/></label>
	    <form:input path="nombre" maxlength="50" id="usuario_nombre"/>		  			
	 
	    <br/><!-- row 2 -->
	    <label for="usuario_username"><spring:message code="username" text="Username"/></label>
		<form:input path="username" maxlength="25" id="usuario_username"/>
		
		<br/><!-- row 3 -->
		<label for="usuario_password"><spring:message code="password" text="Password"/></label>
		<form:password path="password" maxlength="25" id="usuario_password"/>
		<br/>
		<label for="usuario_password2"><spring:message code="passwordConfirmar" text="Confirmar"/></label>
		<form:password path="password2" maxlength="25" id="usuario_password2"/>
			 
		<br/><!-- row 4 -->
		<label for="usuario_preguntaSeguridad"><spring:message code="preguntaSeguridad" text="Pregunta Seguridad"/></label>
		<form:select path="preguntaSeguridad" id="usuario_preguntaSeguridad">
		  <form:options items="${usuarioForm.preguntas }"/>
	    </form:select>
		<br/><!-- row 4 -->
		<label for="usuario_respuestaSeguridad"><spring:message code="respuestaSeguridad" text="Respuesta Seguridad"/></label>
		<form:input path="respuestaSeguridad" maxlength="15" id="usuario_respuestaSeguridad"/>
	
	    <br/> <!-- row 5 -->
	    <label for="usuario_tipoDni"><spring:message code="tipoDeDni" text="Tipo DNI"/></label>
	    <form:select path="tipoDni" id="usuario_tipoDni">
	      <c:forEach items="${usuarioForm.tiposDni }" var="tiposDni">
	      	<form:option value="${tiposDni.value }">${tiposDni.value }</form:option>
	      </c:forEach>
        </form:select>
        &nbsp;&nbsp;
        <label for="usuario_dni"><spring:message code="dni" text="DNI"/></label>
	    <form:input path="dni" maxlength="10" id="usuario_dni"/> 
	
	    <br/> <!-- row 6 -->	  
	    <label for="usuario_email"><spring:message code="email" text="mail"/></label>
        <form:input path="email" maxlength="50" id="usuario_email" placeholder="example@mail.com"/>                          
                       
        <br/> <!-- row 7 -->
        <label for="dia_nacimiento"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></label>
	    <spring:message code="dia" text="DD"/>
        <form:select path="diaNacimiento" id="dia_nacimiento" >
  		  <form:options items="${usuarioForm.dias }"/> 
	    </form:select>
	    <spring:message code="mes" text="MM"/>
	    <form:select path="mesNacimiento" id="mes_nacimiento" >
	 	  <form:options items="${usuarioForm.meses }"/> 
	    </form:select>
	    <spring:message code="anio" text="YYYY"/>
	    <form:select path="anioNacimiento" id="anio_nacimiento" >
		  <form:options items="${usuarioForm.anios }"/> 
	    </form:select>
   		    
 		<br/> <!-- row 8 -->
 		<label for="usuario_tipoTelefono"><spring:message code="tipoTelefono" text="Tipo Telefono"/></label>
	    <form:select path="tipoTelefono" id="usuario_tipoTelefono">
	      <form:options items="${usuarioForm.tiposTelefono }" /> 
	    </form:select>    
	    &nbsp;&nbsp;
	    <label for="usuario_telefono"><spring:message code="telefono" text="Telefono"/></label>
	    <form:input path="telefono" maxlength="15" id="usuario_telefono"/>
	    	    
	    <br/> <!-- row 9 -->
	    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
	  </p>	  
	</fieldset>
  </form:form>
</div>