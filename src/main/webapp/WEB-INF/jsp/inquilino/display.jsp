<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="detalle">
<fieldset>
<legend><spring:message code="detalle" text="Details"/></legend>
  <ul>
    <li>	
      <span class="title"><spring:message code="nombre_completo" text="Nombre Completo"/></span> 
      <span class="titleValue">${inquilino.nombreCompleto }</span>
    </li>
    <li>
      <span class="title">${inquilino.tipoDni }</span>
      <span class="titleValue">${inquilino.dni }</span>
    </li>
    <li>
      <span class="title"><spring:message code="cuit" text="CUIT"/></span>
      <span class="titleValue">${inquilino.cuit }</span>
    </li>
    <li>
      <span class="title"><spring:message code="ocupacion" text="Ocupacion"/></span>
      <span class="titleValue">${inquilino.ocupacion }</span>
    </li>
    <li>
      <span class="title"><spring:message code="lugarTrabajo" text="Empresa"/></span>
      <span class="titleValue">${inquilino.lugarTrabajo }</span>
    </li>
    <li>
      <span class="title"><spring:message code="ingreso" text="Salario"/></span>
	  <span class="titleValue">${inquilino.ingreso }</span>
    </li>
    <li>
      <span class="title"><spring:message code="fechaDeNacimiento" text="Fecha de Nac."/></span>
      <fmt:formatDate value="${inquilino.fechaNacimiento }" pattern="dd/MM/yyyy" var="personaFechaNacimiento"/>
      <span class="titleValue">${personaFechaNacimiento}</span>
    </li>
    <li>
      <span class="title"><spring:message code="telefono" text="Telefono"/></span>
      <span class="titleValue">${inquilino.tipoTelefono}-${inquilino.telefono}</span>	    
    </li>    	       	     
    <li>
      <span class="title"><spring:message code="email" text="email"/></span>
      <span class="titleValue">${inquilino.email }</span>
    </li>
  </ul>
  </fieldset>				
</div>
<a href="<spring:url value='edit/${inquilino.dni}'/>">editar</a>
<spring:url value="remove" var="removeUrl">
<spring:param name="idPersona">${inquilino.idPersona}</spring:param>
</spring:url>
<a href="${removeUrl }">remover</a>