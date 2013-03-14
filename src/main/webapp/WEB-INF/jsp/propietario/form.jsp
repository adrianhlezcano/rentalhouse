<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="formulario">  
 	<form:form method="POST" modelAttribute="propietarioForm">
	<form:hidden path="idPersona"/>
	<form:hidden path="action"/>		
	<fieldset>		
	  <c:choose> 
	    <c:when test="${propietarioForm.action == 'INSERT' }">		  	
	  		<legend>Nuevo Propietario</legend>
	  	</c:when>	
	  	<c:otherwise>
	  		<legend>Actualizar Propietario</legend>
	 	 </c:otherwise>		  
	  </c:choose>
	  <ul>
	    <form:errors element="li" path="apellido" cssClass="error"/>	
	    <form:errors element="li" path="nombre" cssClass="error" />
	    <form:errors element="li" path="dni" cssClass="error"/>
	    <form:errors element="li" path="cuit" cssClass="error"/>
	    <form:errors element="li" path="email" cssClass="error"/>
	    <form:errors element="li" path="telefono" cssClass="error"/>
	    <form:errors element="li" path="numero" cssClass="error"/>
	    <form:errors element="li" path="piso" cssClass="error"/>
	    <form:errors element="li" path="depto" cssClass="error"/>	
	    <form:errors element="li" path="barrio" cssClass="error"/>
	    <form:errors element="li" path="codigoPostal" cssClass="error"/>	    		    
	  </ul>		  	
	  <p>
	    <!-- row 0 -->
	    <label for="propietario_apellido"><spring:message code="apellido" text="Apellido"/></label>
	  	<form:input path="apellido" maxlength="25" id="propietario_apellido"/>		  	 
	  	
        <br/> <!-- row 1 -->
	  	<label for="propietario_nombre"><spring:message code="nombre" text="Nombre"/></label>
	    <form:input path="nombre" maxlength="50" id="propietario_nombre"/>		  			
	 
	    <br/> <!-- row 2 -->
	    <label for="propietario_tipoDni"><spring:message code="tipoDeDni" text="Tipo DNI"/></label>
	    <form:select path="tipoDni" id="propietario_tipoDni">
	      <c:forEach items="${propietarioForm.tiposDni }" var="tiposDni">
	      	<form:option value="${tiposDni.value }">${tiposDni.value }</form:option>
	      </c:forEach>
        </form:select>
        &nbsp;&nbsp;
        <label for="propietario_dni"><spring:message code="dni" text="DNI"/></label>
	    <form:input path="dni" maxlength="10" id="propietario_dni"/> 
	
	    <br/> <!-- row 3 -->		  
	    <label for="propietario_cuit"><spring:message code="cuit" text="CUIT"/></label>
	    <form:input path="cuit" maxlength="15" id="propietario_cuit"/>
	    &nbsp;&nbsp;
	    <label for="propietario_email"><spring:message code="email" text="mail"/></label>
        <form:input path="email" maxlength="50" id="propietario_email" placeholder="example@mail.com"/>                          
                       
        <br/> <!-- row 4 -->
        <label for="dia_nacimiento"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></label>
	    <spring:message code="dia" text="DD"/>
        <form:select path="diaNacimiento" id="dia_nacimiento" >
  		  <form:options items="${propietarioForm.dias }"/> 
	    </form:select>
	    <spring:message code="mes" text="MM"/>
	    <form:select path="mesNacimiento" id="mes_nacimiento" >
	 	  <form:options items="${propietarioForm.meses }"/> 
	    </form:select>
	    <spring:message code="anio" text="YYYY"/>
	    <form:select path="anioNacimiento" id="anio_nacimiento" >
		  <form:options items="${propietarioForm.anios }"/> 
	    </form:select>
   		    
 		<br/> <!-- row 5 -->
 		<label for="propietario_tipoTelefono"><spring:message code="tipoTelefono" text="Tipo Telefono"/></label>
	    <form:select path="tipoTelefono" id="propietario_tipoTelefono">
	      <form:options items="${propietarioForm.tiposTelefono }" /> 
	    </form:select>    
	    &nbsp;&nbsp;
	    <label for="propietario_telefono"><spring:message code="telefono" text="Telefono"/></label>
	    <form:input path="telefono" maxlength="15" id="propietario_telefono"/>
	
	    <br/> <!-- row 6 -->
 		    <label for="propietario_calle"><spring:message code="calleNombre" text="Calle"/></label>
	    <form:input path="calle" maxlength="25" id="propietario_calle" placeholder="San Jose"/>	     
	  	&nbsp;&nbsp;&nbsp;
	  	<label for="propietario_numero"><spring:message code="calleNumero" text="N."/></label>
	    <form:input path="numero" size="3" maxlength="5" id="propietario_numero" placeholder="123"/>	
	    
	    <br/> <!-- row 7 -->
	    <label for="propietario_piso"><spring:message code="piso" text="Piso"/></label>
	    <form:input path="piso" size="2" maxlength="2" id="propietario_piso" placeholder="3" />	
	    &nbsp;&nbsp;&nbsp;
	    <label for="propietario_depto"><spring:message code="depto" text="Depto"/></label>
	    <form:input path="depto" size="2" maxlength="2" id="propietario_depto" placeholder="B"/>

	    <br/> <!-- row 8 -->
	    <label for="propietario_barrio"><spring:message code="barrio" text="Barrio"/></label>
	    <form:input path="barrio" maxlength="25" id="propietario_barrio"/>	
	    &nbsp;&nbsp;&nbsp;	      
	    <label for="propietario_codigoPostal"><spring:message code="codigoPostal" text="Codigo Postal"/></label>
	    <form:input path="codigoPostal" maxlength="10" id="propietario_codigoPostal" size="7" placeholder="11" />	    
	    		    
	    <br/> <!-- row 9 -->
	    <label for="provincia"><spring:message code="provincia" text="Provincia"/></label>
	    <form:select path="idProvincia" id="provincia">
	      <c:forEach items="${propietarioForm.provinciaList }" var="provincia">
	        <form:option value="${provincia.idProvincia }">${provincia.nombre }</form:option>
	      </c:forEach>		       
	    </form:select>		   
	        
	    <br/> <!-- row 10 -->
	    <label for="localidad"><spring:message code="localidad" text="Localidad"/></label>
	    <form:select path="idLocalidad" id="localidad">
	      <c:forEach items="${propietarioForm.localidadList }" var="localidad">	          	        
	        <form:option value="${localidad.idLocalidad}">${localidad.nombre }</form:option>
	      </c:forEach>	
	    </form:select>
	    
	    <br/> <!-- row 11 -->
	    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
	  </p>	  
	</fieldset>
  </form:form>
</div>
<script type="text/javascript">	
$("#propietario_provincia").bind("change", function() {		
	get_localidades($("#propietario_provincia").val());		
});	
</script>