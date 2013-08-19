<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="detalle">
<fieldset>
<legend><spring:message code="detalle" text="Details"/></legend>
  <ul>  
    <li>	
      <span class="title"><spring:message code="fechaCreacion" text="Fecha de Creacion"/></span> 
      <fmt:formatDate value="${contrato.fechaCreacion }" pattern="dd/MM/yyyy" var="fecha"/>
      <span class="titleValue"><c:out value="${fecha }"/></span>
    </li>    
    <li>
      <span class="title"><spring:message code="Inquilino" text="Inquilino"/></span>
      <span class="titleValue">${contrato.inquilino.nombreCompleto} - ${contrato.inquilino.dni }</span>
    </li>   
    <li>
      <span class="title"><spring:message code="Propiedad" text="Propiedad"/></span>
      <span class="titleValue">
          ${contrato.propiedad.tipoPropiedad.value } - 
		  ${contrato.propiedad.tipoMoneda.value } ${contrato.propiedad.precioAlquiler } 
		  ( ${contrato.propiedad.domicilio.calle } ${contrato.propiedad.domicilio.numero }
		    <c:if test="${not empty contrato.propiedad.domicilio.piso}">piso ${contrato.propiedad.domicilio.piso}</c:if>
		    <c:if test="${not empty contrato.propiedad.domicilio.depto}">dpto ${contrato.propiedad.domicilio.depto}</c:if>
		  )
      </span>
    </li>    
    <li>
      <span class="title"><spring:message code="depositoGarantia" text="Deposito"/></span>
      <span class="titleValue">${contrato.depositoGarantia }</span>
    </li>
    <li>
      <span class="title"><spring:message code="honorarios" text="Honorarios"/></span>
      <span class="titleValue">${contrato.honorarios}</span>
    </li>
    <li>
      <span class="title"><spring:message code="comision" text="Comision"/></span>
	  <span class="titleValue">${contrato.comision }</span>
    </li>
    <li>
      <span class="title"><spring:message code="observacion" text="Observacion"/></span>      
      <span class="titleValue">${contrato.observacion }</span>
    </li>
    <li>
      <span class="title"><spring:message code="estadoContrato" text="Estado del Contrato"/></span>
      <span class="titleValue">${contrato.estadoContrato.value}</span>	    
    </li>
    <br/><br/>
    <li>
      <span class="title">Desea Cancelar este contrato ?</span>
      <span class="titleValue">
        <a href="<spring:url value='/admin/contrato/cancel/${contrato.idContrato}'/>">S&iacute;, deseo Cancelar.</a>      
      </span>	    
    </li>
    </ul>
  </fieldset>				
</div>