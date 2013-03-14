<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="detalle">
<fieldset>
<legend><spring:message code="detalle" text="Details"/></legend>
  <ul>  
<!--     <li>	 -->
<%--       <span class="title"><spring:message code="legajo" text="Folder Id"/></span>  --%>
<%--       <span class="titleValue"><c:if test="${not empty propietario.legajo}">${propietario.legajo}</c:if></span> --%>
<!--     </li> -->
    <li>	
      <span class="title"><spring:message code="nombre_completo" text="Nombre Completo"/></span> 
      <span class="titleValue">${propietario.nombreCompleto }</span>
    </li>
    <li>
      <span class="title">${propietario.tipoDni}</span>
      <span class="titleValue">${propietario.dni }</span>
    </li>
    <li>
      <span class="title"><spring:message code="cuit" text="CUIT"/></span>
      <span class="titleValue">${propietario.cuit }</span>
    </li>
    <li>
      <span class="title"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></span>
      <fmt:formatDate value="${propietario.fechaNacimiento }" pattern="dd/MM/yyyy" var="personaFechaNacimiento"/>
      <span class="titleValue">${personaFechaNacimiento}</span>
    </li>
    <li>
      <span class="title"><spring:message code="telefono" text="Telefono"/></span>
      <span class="titleValue">${propietario.tipoTelefono}-${propietario.telefono}</span>	    
    </li>	     
    <li>
      <span class="title"><spring:message code="email" text="email"/></span>
      <span class="titleValue">${propietario.email }</span>
    </li>
    <li>
      <span class="title"><spring:message code="calle" text="Calle"/></span>
      <span class="titleValue">${propietario.domicilio.calle } ${propietario.domicilio.numero }</span>
    </li>	    
    <li>
      <span class="title"><spring:message code="piso" text="Piso"/></span>
	  <span class="titleValue"><c:if test="${not empty propietario.domicilio.piso}">
		${propietario.domicilio.piso } &quot;${propietario.domicilio.depto }&quot;
	  </c:if></span>
	</li>
    <li>
      <span class="title"><spring:message code="barrio" text="Barrio"/></span>
      <span class="titleValue"><c:if test="${not empty propietario.domicilio.barrio}">${propietario.domicilio.barrio}	</c:if></span>
    </li>
    <li>
      <span class="title"><spring:message code="codigoPostal" text="Codigo Postal"/></span>
      <span class="titleValue">${propietario.domicilio.codigoPostal }</span>
    </li>
    <li>
      <span class="title"><spring:message code="localidad" text="Localidad"/></span>
      <span class="titleValue">${propietario.domicilio.localidad.nombre }</span>
    </li>
    <li>
      <span class="title"><spring:message code="provincia" text="Provincia"/></span>
      <span class="titleValue">${propietario.domicilio.localidad.provincia.nombre }</span>
    </li>
  </ul>
  </fieldset>				
</div>
<a href="<spring:url value='edit/${propietario.dni}'/>">editar</a>
<spring:url value="remove" var="removeUrl">
<spring:param name="idPersona">${propietario.idPersona}</spring:param>
</spring:url>
<a href="${removeUrl }">remover</a>