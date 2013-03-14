<%@ include file="/WEB-INF/jsp/includes.jsp" %>
  <div id="formulario">  
	  <form:form method="POST" modelAttribute="propiedadForm">
		<form:hidden path="idPropiedad"/>
		<form:hidden path="action"/>		
		<fieldset>		
		  <c:choose> 
		    <c:when test="${propiedadForm.action == 'INSERT' }">		  	
		  		<legend>Nueva Propiedad</legend>
		  	</c:when>	
		  	<c:otherwise>
		  		<legend>Actualizar Propiedad</legend>
		 	 </c:otherwise>		  
		  </c:choose>
		  <ul>
		    <form:errors element="li" path="idPropietario" cssClass="error"/>	
		    <form:errors element="li" path="precioAlquiler" cssClass="error" />
		    <form:errors element="li" path="precioVenta" cssClass="error"/>
		    <form:errors element="li" path="superficie" cssClass="error"/>
		    <form:errors element="li" path="detalle" cssClass="error"/>
		    <form:errors element="li" path="calle" cssClass="error"/>
		    <form:errors element="li" path="numero" cssClass="error"/>
		    <form:errors element="li" path="piso" cssClass="error"/>
		    <form:errors element="li" path="depto" cssClass="error"/>
		    <form:errors element="li" path="barrio" cssClass="error"/>
		    <form:errors element="li" path="codigoPostal" cssClass="error"/>		    		    
		  </ul>		  	
		  <p>
		    <!-- row 0 -->
		    <label for="propiedad_propietario"><spring:message code="propietarioId" text="DNI Propietario"/></label>
		  	<input type="text" id="propiedad_propietario" size="8" placeholder="dni">
		  	<input type="button" name="search" id="searchPropietario" value="Checkear"/>
		  	<form:hidden path="idPropietario"/>
		  	&nbsp;&nbsp;
		  	<span id="propietario_name" style="font-weight: bold;">
		  	  <c:if test="${not empty propiedadForm.nombrePropietario }"> 		  
		  	    ${propiedadForm.nombrePropietario }	
		  	  </c:if>
		  	</span>	  		
		  		  
		    <br/> <!-- row 1 -->
		    <label for="propiedad_tipoPropiedad"><spring:message code="tipoPropiedad" text="Inmueble"/></label>
		    <form:select path="tipoPropiedad" id="propiedad_tipoPropiedad">
		      <c:forEach items="${propiedadForm.tiposPropiedad }" var="tipoPropiedad">
		      	<form:option value="${tipoPropiedad }">${tipoPropiedad.value }</form:option>
		      </c:forEach>
            </form:select>
            
		    <br/> <!-- row 2 -->		  
		    <label for="propiedad_operacionPropiedad"><spring:message code="operacionPropiedad" text="Operacion"/></label>
		    <form:select path="operacionPropiedad" id="propiedad_operacionPropiedad">
		      <c:forEach items="${propiedadForm.operaciones}" var="operacion">
		      	<form:option value="${operacion }">${operacion.value }</form:option>
		      </c:forEach>
            </form:select>
                        
            <br/> <!-- row 3 -->
            <label for="propiedad_precioAlquiler"><spring:message code="precioAlquiler" text="Precio de Alquiler"/></label>
		    <form:input path="precioAlquiler" maxlength="10" id="propiedad_precioAlquiler" size="3"/>
		    &nbsp;&nbsp;
		    <label for="propiedad_precioVenta"><spring:message code="precioVenta" text="Precio de Venta"/></label>
		    <form:input  path="precioVenta" maxlength="10" id="propiedad_precioVenta" size="6"/>	
		    &nbsp;&nbsp;	      
		    <label for="propiedad_tipoMoneda"><spring:message code="tipoMoneda" text="Moneda"/></label>
    	    <c:forEach items="${propiedadForm.tiposMonedas }" var="tipoMoneda">
		      <form:radiobutton path="tipoMoneda" value="${tipoMoneda}" id="${tipoMoneda }" label="${tipoMoneda.value }" /> 
  		    </c:forEach> 
  		    
  		    <br/> <!-- row 4 -->
  		    <label for="propiedad_dormitorio"><spring:message code="dormitorio" text="Dormitorios"/></label>
		    <form:select path="dormitorios" id="propiedad_dormitorio">
		    <c:forEach items="${propiedadForm.dormitorioList }" var="dormitorio">
		      <form:option value="${dormitorio }">${dormitorio }</form:option>
		    </c:forEach>
		    </form:select>	    
		    &nbsp;&nbsp;
		    <label for="propiedad_superficie"><spring:message code="propiedad_superficie" text="Sup. (m2)"/></label>
		    <form:input path="superficie" id="propiedad_superficie" maxlength="10" size="7" placeholder="250"/>
		    &nbsp;&nbsp;
<%-- 		    <label for="propiedad_referencia"><spring:message code="referencia" text="Identifier"/></label> --%>
<%-- 		    <form:input path="referencia" id="propiedad_referencia" maxlength="10" size="7" placeholder=""/>	 --%>
		    
  		    <br/><!-- row 5 --><label for="propiedad_detalle"><spring:message code="detalle" text="Detalle"/></label>
  		    <br/><!-- row 6 --><form:textarea path="detalle" id="propiedad_detalle" rows="3" cols="55"/>
		    		       
		    <br/> <!-- row 7 -->
		    <label for="propiedad_publicar"><spring:message code="publicar" text="Publicar?"/></label>
		    <form:checkbox path="publicar" id="propiedad_publicar"/>
		    &nbsp;&nbsp;
		    <label for="propiedad_estrenar"><spring:message code="estrenar" text="A Estrenar?"/></label>
		    <form:checkbox path="estrenar" id="propiedad_estrenar"/>
		    &nbsp;&nbsp;
		    <label for="propiedad_aptoCredito"><spring:message code="aptoCredito" text="Apto Credito?"/></label>
		    <form:checkbox path="aptoCredito" id="propiedad_aptoCredito"/>
		    &nbsp;&nbsp;
		    <label for="propiedad_aptoEscritura"><spring:message code="aptoEscritura" text="Apto Escritura?"/></label>
		    <form:checkbox path="aptoEscritura" id="propiedad_aptoEscritura"/>	      
		    
		    <br/> <!-- row 8 -->
  		    <label for="propiedad_calle"><spring:message code="calleNombre" text="Calle"/></label>
		    <form:input path="calle" maxlength="25" id="propiedad_calle" placeholder="San Jose"/>	     
		  	&nbsp;&nbsp;&nbsp;
		  	<label for="propiedad_numero"><spring:message code="calleNumero" text="N."/></label>
		    <form:input path="numero" size="3" maxlength="5" id="propiedad_numero" placeholder="123"/>	
		    
		    <br/> <!-- row 9 -->
		    <label for="propiedad_piso"><spring:message code="piso" text="Piso"/></label>
		    <form:input path="piso" size="2" maxlength="2" id="propiedad_piso" placeholder="3" />	
		    &nbsp;&nbsp;&nbsp;
		    <label for="propiedad_depto"><spring:message code="depto" text="Depto"/></label>
		    <form:input path="depto" size="2" maxlength="2" id="propiedad_depto" placeholder="B"/>
		 
		    <br/> <!-- row 10 -->
		    <label for="propiedad_barrio"><spring:message code="barrio" text="Barrio"/></label>
		    <form:input path="barrio" maxlength="25" id="propiedad_barrio"/>	
		    &nbsp;&nbsp;&nbsp;	      
		    <label for="propiedad_codigoPostal"><spring:message code="codigoPostal" text="Codigo Postal"/></label>
		    <form:input path="codigoPostal" maxlength="10" id="propiedad_codigoPostal" size="7" placeholder="11" />	    
		    
		    <br/> <!-- row 11 -->
		    <label for="propiedad_provincia"><spring:message code="provincia" text="Provincia"/></label>
		    <form:select path="idProvincia" id="propiedad_provincia">
		      <c:forEach items="${propiedadForm.provinciaList }" var="provincia">
		        <form:option value="${provincia.idProvincia }">${provincia.nombre }</form:option>
		      </c:forEach>		       
		    </form:select>		   
		        
		    <br/> <!-- row 12 -->
		    <label for="propiedad_localidad"><spring:message code="localidad" text="Localidad"/></label>
		    <form:select path="idLocalidad" id="propiedad_localidad">
		      <c:forEach items="${propiedadForm.localidadList }" var="localidad">	          	        
		        <form:option value="${localidad.idLocalidad}">${localidad.nombre }</form:option>
		      </c:forEach>	
		    </form:select>
		    
		    <br/> <!-- row 13 -->
		    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
		  </p>	  
		</fieldset>
	  </form:form>
  </div> 
<script type="text/javascript">
$("#propiedad_provincia").bind("change", function() {		
	get_localidades($("#propiedad_provincia").val());		
});	
$("#searchPropietario").bind("click", function() {		
	get_propietario($("#propiedad_propietario").val());		
});		
</script>