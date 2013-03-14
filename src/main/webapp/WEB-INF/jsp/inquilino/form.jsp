<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$("#searchGarante").bind("click", function() {		
		get_garante($("#inquilino_garante").val());		
	});		
});
</script>
<div id="formulario">  
 	<form:form method="POST" modelAttribute="inquilinoForm">
	<form:hidden path="idPersona"/>
	<form:hidden path="action"/>		
	<fieldset>		
	  <c:choose> 
	    <c:when test="${inquilinoForm.action == 'INSERT' }">		  	
	  		<legend>Nuevo Inquilino</legend>
	  	</c:when>	
	  	<c:otherwise>
	  		<legend>Actualizar Inquilino</legend>
	 	 </c:otherwise>		  
	  </c:choose>
	  <ul>
	    <form:errors element="li" path="apellido" cssClass="error"/>	
	    <form:errors element="li" path="nombre" cssClass="error" />
	    <form:errors element="li" path="dni" cssClass="error"/>
	    <form:errors element="li" path="cuit" cssClass="error"/>
	    <form:errors element="li" path="email" cssClass="error"/>
	    <form:errors element="li" path="telefono" cssClass="error"/>
	    <form:errors element="li" path="ocupacion" cssClass="error"/>
	    <form:errors element="li" path="lugarTrabajo" cssClass="error"/>
		<form:errors element="li" path="ingreso" cssClass="error"/>
	  </ul>		  	
	  <p>
        <!-- row 0 -->
	    <label for="inquilino_garante"><spring:message code="garanteId" text="DNI Garante"/></label>
	  	<input type="text" id="inquilino_garante" size="8" placeholder="dni">
	  	<input type="button" name="search" id="searchGarante" value="Checkear"/>
	  	<form:hidden path="idGarante"/>
	  	&nbsp;&nbsp;
	  	<span id="inquilino_name" style="font-weight: bold;">
	  	  <c:if test="${not empty inquilinoForm.nombreGarante}"> 		  
	  	    ${inquilinoForm.nombreGarante }	
	  	  </c:if>
	  	</span>	  	
	  
	    <br/> <!-- row 1 -->
	    <label for="inquilino_apellido"><spring:message code="apellido" text="Apellido"/></label>
	  	<form:input path="apellido" maxlength="25" id="inquilino_apellido"/>		  	 
	  	
        <br/> <!-- row 2 -->
	  	<label for="inquilino_nombre"><spring:message code="nombre" text="Nombre"/></label>
	    <form:input path="nombre" maxlength="50" id="inquilino_nombre"/>		  			
	 
	    <br/> <!-- row 3 -->
	    <label for="inquilino_tipoDni"><spring:message code="tipoDeDni" text="Tipo DNI"/></label>
	    <form:select path="tipoDni" id="inquilino_tipoDni">
	      <c:forEach items="${inquilinoForm.tiposDni }" var="tiposDni">
	      	<form:option value="${tiposDni.value }">${tiposDni.value }</form:option>
	      </c:forEach>
        </form:select>
        &nbsp;&nbsp;
        <label for="inquilino_dni"><spring:message code="dni" text="DNI"/></label>
	    <form:input path="dni" maxlength="10" id="inquilino_dni"/> 
	      
	    <br/> <!-- row 4 -->		  
	    <label for="inquilino_cuit"><spring:message code="cuit" text="CUIT"/></label>
	    <form:input path="cuit" maxlength="15" id="inquilino_cuit"/>
	    &nbsp;&nbsp;
	    <label for="inquilino_email"><spring:message code="email" text="mail"/></label>
        <form:input path="email" maxlength="50" id="inquilino_email" placeholder="example@mail.com"/>                          
                       
        <br/> <!-- row 5 -->
        <label for="dia_nacimiento"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></label>
	    <spring:message code="dia" text="DD"/>
        <form:select path="diaNacimiento" id="dia_nacimiento" >
  		  <form:options items="${inquilinoForm.dias }"/> 
	    </form:select>
	    <spring:message code="mes" text="MM"/>
	    <form:select path="mesNacimiento" id="mes_nacimiento" >
	 	  <form:options items="${inquilinoForm.meses }"/> 
	    </form:select>
	    <spring:message code="anio" text="YYYY"/>
	    <form:select path="anioNacimiento" id="anio_nacimiento" >
		  <form:options items="${inquilinoForm.anios }"/> 
	    </form:select>
   		    
 		<br/> <!-- row 6 -->
 		<label for="inquilino_tipoTelefono"><spring:message code="tipoTelefono" text="Tipo Telefono"/></label>
	    <form:select path="tipoTelefono" id="inquilino_tipoTelefono">
	      <form:options items="${inquilinoForm.tiposTelefono }" /> 
	    </form:select>    
	    &nbsp;&nbsp;
	    <label for="inquilino_telefono"><spring:message code="telefono" text="Telefono"/></label>
	    <form:input path="telefono" maxlength="15" id="inquilino_telefono"/>

		<br/><!-- row 7 -->
	    <label for="inquilino_ocupacion"><spring:message code="ocupacion" text="Ocupacion"/></label>
		<form:input path="ocupacion" maxlength="30" id="inquilino_ocupacion"/>		
	    &nbsp;&nbsp;
	    <label for="inquilino_lugarTrabajo"><spring:message code="lugarTrabajo" text="Empresa"/></label>
		<form:input path="lugarTrabajo" maxlength="25" id="inquilino_lugarTrabajo"/>
 		      		    
	    <br/><!-- row 8 -->
	    <label for="inquilino_ingreso"><spring:message code="ingreso" text="Salario"/></label>
		<form:input path="ingreso" maxlength="10" size="8" id="inquilino_ingreso"/>	    

	    <br/> <!-- row 9 -->
	    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
	  </p>	  
	</fieldset>
  </form:form>
</div>