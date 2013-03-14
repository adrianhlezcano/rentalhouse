<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="formulario">  
  <form:form method="POST" modelAttribute="garanteForm">
  <form:hidden path="idPersona"/>
  <form:hidden path="action"/>		
  <fieldset>		
  <c:choose> 
    <c:when test="${garanteForm.action == 'INSERT' }">		  	
  		<legend>Nuevo Garante</legend>
  	</c:when>	
  	<c:otherwise>
  		<legend>Actualizar Garante</legend>
 	 </c:otherwise>		  
  </c:choose>
  <ul>
    <form:errors element="li" path="apellido" cssClass="error"/>	
    <form:errors element="li" path="nombre" cssClass="error" />
    <form:errors element="li" path="dni" cssClass="error"/>
    <form:errors element="li" path="cuit" cssClass="error"/>
    <form:errors element="li" path="email" cssClass="error"/>
    <form:errors element="li" path="telefono" cssClass="error"/>
    <form:errors element="li" path="valorGarantia" cssClass="error"/>
    <form:errors element="li" path="detalleGarantia" cssClass="error"/>
    <form:errors element="li" path="numero" cssClass="error"/>
    <form:errors element="li" path="piso" cssClass="error"/>
    <form:errors element="li" path="depto" cssClass="error"/>	
    <form:errors element="li" path="barrio" cssClass="error"/>
    <form:errors element="li" path="codigoPostal" cssClass="error"/>	    		    
  </ul>		  	
  <p>		 
    <!-- row 0 -->
    <label for="garante_apellido"><spring:message code="apellido" text="Apellido"/></label>
  	<form:input path="apellido" maxlength="25" id="garante_apellido"/>		  	 
  	
    <br/> <!-- row 1 -->
  	<label for="garante_nombre"><spring:message code="nombre" text="Nombre"/></label>
    <form:input path="nombre" maxlength="50" id="garante_nombre"/>		  			
  		  
    <br/> <!-- row 2 -->
    <label for="garante_tipoDni"><spring:message code="tipoDeDni" text="Tipo DNI"/></label>
    <form:select path="tipoDni" id="garante_tipoDni">
      <c:forEach items="${garanteForm.tiposDni }" var="tiposDni">
      	<form:option value="${tiposDni.value }">${tiposDni.value }</form:option>
      </c:forEach>
    </form:select>
    &nbsp;&nbsp;
    <label for="garante_dni"><spring:message code="dni" text="DNI"/></label>
    <form:input path="dni" maxlength="10" id="garante_dni"/>
      
    <br/> <!-- row 3 -->		  
    <label for="garante_cuit"><spring:message code="cuit" text="CUIT"/></label>
    <form:input path="cuit" maxlength="15" id="garante_cuit"/>
    &nbsp;&nbsp;
    <label for="garante_email"><spring:message code="email" text="mail"/></label>
    <form:input path="email" maxlength="50" id="garante_email" placeholder="example@mail.com"/>                          
                      
    <br/> <!-- row 4 -->
    <label for="dia_nacimiento"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></label>
    <spring:message code="dia" text="DD"/>
       <form:select path="diaNacimiento" id="dia_nacimiento" >
 		  <form:options items="${garanteForm.dias }"/> 
    </form:select>
    <spring:message code="mes" text="MM"/>
    <form:select path="mesNacimiento" id="mes_nacimiento" >
 	  <form:options items="${garanteForm.meses }"/> 
    </form:select>
    <spring:message code="anio" text="YYYY"/>
    <form:select path="anioNacimiento" id="anio_nacimiento" >
	  <form:options items="${garanteForm.anios }"/> 
    </form:select>
  		    
	<br/> <!-- row 5 -->
    <label for="garante_tipoTelefono"><spring:message code="tipoTelefono" text="Tipo Telefono"/></label>
    <form:select path="tipoTelefono" id="garante_tipoTelefono">
      <form:options items="${garanteForm.tiposTelefono }" /> 
    </form:select>    
    &nbsp;&nbsp;
    <label for="garante_telefono"><spring:message code="telefono" text="Telefono"/></label>
    <form:input path="telefono" maxlength="15" id="garante_telefono"/>
    
    <br/><!-- row 6 -->
    <label for="garante_tipoGarantia"><spring:message code="tipoGarantia" text="Tipo Garantia"/></label>
    <form:select path="tipoGarantia" id="garante_tipoGarantia">
      <c:forEach items="${garanteForm.tipoGarantiaList }" var="tipoGarantia">
      	<form:option value="${tipoGarantia.value }">${tipoGarantia.value }</form:option>
      </c:forEach>
    </form:select>
    &nbsp;&nbsp;
    <label for="garante_valorGarantia"><spring:message code="valorGarantia" text="Valor Garantia"/></label>
    <form:input path="valorGarantia" maxlength="11" size="7" id="garante_valorGarantia"/>
		      		    
    <br/><!-- row 7 -->
    <label for="garante_detalleGarantia"><spring:message code="detalleGarantia" text="Detalle Garantia"/></label>    
    <br/><!-- row 8 -->
    <form:textarea path="detalleGarantia" id="garante_detalleGarantia" rows="3" cols="55" htmlEscape="true"/>
    	    
    <br/> <!-- row 9 -->
    <label for="garante_calle"><spring:message code="calle" text="Calle"/></label>
    <form:input path="calle" maxlength="25" id="garante_calle" placeholder="San Jose"/>	     
  	&nbsp;&nbsp;&nbsp;
  	<label for="garante_numero"><spring:message code="numero" text="N."/></label>
    <form:input path="numero" size="3" maxlength="5" id="garante_numero" placeholder="123"/>	
    
    <br/> <!-- row 10 -->
    <label for="garante_piso"><spring:message code="piso" text="Piso"/></label>
    <form:input path="piso" size="2" maxlength="2" id="garante_piso" placeholder="3" />	
    &nbsp;&nbsp;&nbsp;
    <label for="garante_depto"><spring:message code="depto" text="Depto"/></label>
    <form:input path="depto" size="2" maxlength="2" id="garante_depto" placeholder="B"/>

    <br/> <!-- row 11 -->
    <label for="garante_barrio"><spring:message code="barrio" text="Barrio"/></label>
    <form:input path="barrio" maxlength="25" id="garante_barrio"/>	
    &nbsp;&nbsp;&nbsp;	      
    <label for="garante_codigoPostal"><spring:message code="codigoPostal" text="Cod. Postal"/></label>
    <form:input path="codigoPostal" maxlength="10" id="garante_codigoPostal" size="7" placeholder="11" />	    
    		    
    <br/> <!-- row 12 -->
    <label for="provincia"><spring:message code="provincia" text="Provincia"/></label>
    <form:select path="idProvincia" id="provincia">
      <c:forEach items="${garanteForm.provinciaList }" var="provincia">
        <form:option value="${provincia.idProvincia }">${provincia.nombre }</form:option>
      </c:forEach>		       
    </form:select>		   
        
    <br/> <!-- row 13 -->
    <label for="localidad"><spring:message code="localidad" text="Localidad"/></label>
    <form:select path="idLocalidad" id="localidad">
      <c:forEach items="${garanteForm.localidadList }" var="localidad">	          	        
        <form:option value="${localidad.idLocalidad}">${localidad.nombre }</form:option>
      </c:forEach>	
    </form:select>
    
    <br/> <!-- row 14 -->
    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
  </p>	  
 </fieldset>
 </form:form>
</div>
<script type="text/javascript">
$("#provincia").bind("change", function() {		
	get_localidades($("#provincia").val());		
});	
</script>