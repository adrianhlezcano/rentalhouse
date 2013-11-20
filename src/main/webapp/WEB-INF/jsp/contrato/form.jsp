<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="formulario">  
  <form:form method="POST" modelAttribute="contratoForm">
  <form:hidden path="idContrato"/>
  <form:hidden path="action"/>		
  <fieldset>
  <legend>Nuevo Contrato</legend>	 
  <ul>
	<form:errors element="li" path="depositoGarantia" cssClass="error"/>	
    <form:errors element="li" path="comision" cssClass="error"/>
    <form:errors element="li" path="honorarios" cssClass="error"/>	
	<form:errors element="li" path="cuotas" cssClass="error"/>
	<form:errors element="li" path="importeCuota" cssClass="error"/>
	<form:errors element="li" path="observacion" cssClass="error"/>
  </ul>
    
  <div id="searchRenter">       
    <fieldset>    
	<legend>Buscar Inquilino</legend>
	<label for="contrato_inquilino"><spring:message code="dni" text="DNI"/></label>		
	<input type="text" id="contrato_inquilino" size="10">
	<input type="button" name="search" id="searchInquilino" value="Checkear"/>
	<form:hidden path="idInquilino"/>
	&nbsp;&nbsp;&nbsp;&nbsp;
    <span id="inquilino_name" style="font-weight: bold;" >	  	  
	  <c:if test="${not empty contratoForm.nombreInquilino }"> 		  
	    ${contratoForm.nombreInquilino }"	
	  </c:if>
    </span>	  
    </fieldset>
  </div>  
    
  <!-- row 1 -->  
  <div id="searchProperty">
    <fieldset>
    <legend>Seleccionar Inmueble</legend>   	
   	<form:select path="idPropiedad" id="contrato_propiedad" size="5" style="width: 450px;">
   	  <form:option value="0" selected="selected">-- Lista de Inmuebles --</form:option>
   	  
	  <c:forEach items="${contratoForm.propiedadList }" var="propiedad">	          	        
		<form:option value="${propiedad.idPropiedad}">
		  ${propiedad.operacionPropiedad.value }: ${propiedad.tipoPropiedad.value } -
   		  ${propiedad.tipoMoneda.value } ${propiedad.precioAlquiler } 
   		  (
   			${propiedad.domicilio.calle } ${propiedad.domicilio.numero }
   			<c:if test='${not empty propiedad.domicilio.piso}'>${propiedad.domicilio.piso}</c:if>
   			<c:if test='${not empty propiedad.domicilio.depto}'>${propiedad.domicilio.depto}</c:if>
   		  )
		</form:option>
	  </c:forEach>	
    </form:select>   	
 	</fieldset>
  </div>
    
    <br/><!-- row 2 -->
    <label for="propiedad_administrado"><spring:message code="administrado" text="Administrado?"/></label>
	<form:checkbox path="administrado" id="propiedad_administrado" />
    &nbsp;&nbsp;&nbsp;
    <label for="contrato_cuotas"><spring:message code="cuotas" text="Meses"/></label>
	<form:input path="cuotas" maxlength="2" size="2" id="contrato_cuotas"/>	
	&nbsp;&nbsp;&nbsp;
    <label for="contrato_importeCuota"><spring:message code="importeCuota" text="Precio por Mes"/></label>
    <form:input path="importeCuota" maxlength="5" size="4" id="contrato_importeCuota" readonly="true"/>
        
    <br/><!-- row 4 -->
    <label for="contrato_depositoGarantia"><spring:message code="depositoGarantia" text="Deposito"/></label>
    <form:input path="depositoGarantia" maxlength="10" size="5" id="contrato_depositoGarantia"/>
    &nbsp;&nbsp;&nbsp;
    <label for="contrato_comision"><spring:message code="comision" text="Comision"/></label>
    <form:input path="comision" maxlength="10" size="5" id="contrato_comision"/>			  
	&nbsp;&nbsp;&nbsp;
    <label for="contrato_honorarios"><spring:message code="honorarios" text="Honorarios"/></label>
    <form:input path="honorarios" maxlength="10"  size="5" id="contrato_honorarios"/>		
	   
    <br/><!-- row 6 -->
    <label for="contrato_observacion"><spring:message code="observacion" text="Observacion"/></label>
    <br/>
	<form:textarea path="observacion" rows="2" cols="60" />		
	
    <br/><!-- row 7 -->	 
    <input type="submit" value="<spring:message code="enviar" text="Enviar"/>" />
    
    </fieldset>  
  </form:form>
</div> 
<script type="text/javascript">	
$("#searchInquilino").bind("click", function() {		
	get_inquilino($("#contrato_inquilino").val());		
});
$("#contrato_propiedad").change(function(){
	var aux = $("select option:selected").text();
	var aux = aux.substring(aux.indexOf("$") + 1, aux.indexOf("("));
	$("#contrato_importeCuota").attr("value", aux.trim());
});
</script>