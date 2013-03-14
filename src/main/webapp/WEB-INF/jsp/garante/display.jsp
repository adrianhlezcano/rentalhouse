<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="detalle">
<fieldset>
<legend><spring:message code="detalle" text="Details"/></legend>
  <ul>
    <li>	
      <span class="title"><spring:message code="nombre_completo" text="Nombre Completo"/></span> 
      <span class="titleValue">${garante.nombreCompleto }</span>
    </li>
    <li>
      <span class="title">${garante.tipoDni }</span>
      <span class="titleValue">${garante.dni }</span>
    </li>
    <li>
      <span class="title"><spring:message code="cuit" text="CUIT"/></span>
      <span class="titleValue">${garante.cuit}</span>
    </li>
    <li>
      <span class="title"><spring:message code="tipoGarantia" text="Tipo de Garantia"/></span>
      <span class="titleValue">${garante.tipoGarantia } - 
        <fmt:formatNumber value="${garante.valorGarantia }" type="currency" currencySymbol="$"/> 		   
	 </span>
    </li>
    <li>
      <span class="title"><spring:message code="detalleGarantia" text="Detalle de Garantia"/></span>
      <span class="titleValue"><c:if test="${not empty garante.detalleGarantia}">	
      ${garante.detalleGarantia }</c:if></span>
    </li>
    <li>
      <span class="title"><spring:message code="fechaNacimiento" text="Fecha de Nac."/></span>
	  <span class="titleValue"><fmt:formatDate value="${garante.fechaNacimiento }" pattern="dd/MM/yyyy" var="personaFechaNacimiento"/>
	  <c:out value="${personaFechaNacimiento }"/></span>
    </li>
    <li>
      <span class="title"><spring:message code="telefono" text="Telefono"/></span>
      <span class="titleValue">${garante.tipoTelefono}-${garante.telefono}</span>	    
    </li>	     
    <li>
      <span class="title"><spring:message code="email" text="email"/></span>
      <span class="titleValue">${garante.email }</span>
    </li>
    <li>
      <span class="title"><spring:message code="calle" text="Calle"/></span>
      <span class="titleValue">${garante.domicilio.calle } ${garante.domicilio.numero }</span>
    </li>	    
    <li>
      <span class="title"><spring:message code="piso" text="Piso"/></span>
	  <span class="titleValue"><c:if test="${not empty garante.domicilio.piso}">
		${garante.domicilio.piso } &quot;${garante.domicilio.depto }&quot;
	  </c:if></span>
	</li>
    <li>
      <span class="title"><spring:message code="barrio" text="Barrio"/></span>
      <span class="titleValue"><c:if test="${not empty garante.domicilio.barrio}">${garante.domicilio.barrio}</c:if></span>
    </li>
    <li>
      <span class="title"><spring:message code="codigoPostal" text="Codigo Postal"/></span>
      <span class="titleValue">${garante.domicilio.codigoPostal }</span>
    </li>
    <li>
      <span class="title"><spring:message code="localidad" text="Localidad"/></span>
      <span class="titleValue">${garante.domicilio.localidad.nombre }</span>
    </li>
    <li>
      <span class="title"><spring:message code="provincia" text="Provincia"/></span>
      <span class="titleValue">${garante.domicilio.localidad.provincia.nombre }</span>
    </li>
  </ul>
  </fieldset>				
</div>
<a href="<spring:url value='edit/${garante.dni}'/>">editar</a>
<spring:url value="remove" var="removeUrl">
<spring:param name="idPersona">${garante.idPersona}</spring:param>
</spring:url>
<a href="${removeUrl }">remover</a>