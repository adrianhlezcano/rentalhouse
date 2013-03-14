<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	<div id="detalle">
	<fieldset>
	<legend><spring:message code="detalle" text="Details"/></legend>
	  <ul>
	    <li>
	      <span class="title"><spring:message code="tipoPropiedad" text="Inmueble"/></span> 
	      <span class="titleValue">${propiedad.tipoPropiedad.value }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="operacionPropiedad" text="Operacion"/></span>
	      <span class="titleValue">${propiedad.operacionPropiedad.value }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="precioAlquiler" text="Precio de Alquiler"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.precioAlquiler }">		   
			${propiedad.tipoMoneda.value}${propiedad.precioAlquiler }		
		  </c:if></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="precioVenta" text="Precio de Venta"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.precioVenta }">	
	      ${propiedad.tipoMoneda.value} ${propiedad.precioVenta }</c:if></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="dormitorio" text="Dorm."/> </span>
		  <span class="titleValue">${propiedad.dormitorios }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="detalle" text="Detalle"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.detalle}">${propiedad.detalle }</c:if></span>	    
	    </li>
	    <li>
	      <span class="title"><spring:message code="superficie" text="Sup. m2"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.superficie}">${propiedad.superficie }</c:if></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="estrenar" text="A estrenar?"/></span>
	      <span class="titleValue"><c:choose>
	        <c:when test="${propiedad.estrenar}">YES</c:when>
		    <c:otherwise>NO</c:otherwise>
		  </c:choose></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="aptoCredito" text="Apto Credito?"/></span>
		  <span class="titleValue"><c:choose>
			<c:when test="${propiedad.aptoCredito}">YES</c:when>
			<c:otherwise>NO</c:otherwise>
		  </c:choose></span>
		</li>
	    <li>
	      <span class="title"><spring:message code="aptoEscritura" text="Apto Escritura?"/></span>
	      <span class="titleValue"><c:choose>
		    <c:when test="${propiedad.aptoEscritura}">YES</c:when>
		    <c:otherwise>NO</c:otherwise>
		  </c:choose></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="calle" text="Calle"/></span>
	      <span class="titleValue">${propiedad.domicilio.calle } ${propiedad.domicilio.numero }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="piso" text="Piso"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.domicilio.piso}">
	        ${propiedad.domicilio.piso } "${propiedad.domicilio.depto }"
	      </c:if></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="barrio" text="Barrio"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.domicilio.barrio}">
	        ${propiedad.domicilio.barrio}
	      </c:if></span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="codigoPostal" text="Codigo Postal"/></span>
	      <span class="titleValue">${propiedad.domicilio.codigoPostal }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="localidad" text="Localidad"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.nombre}</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="provincia" text="Provincia"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.provincia.nombre }</span>
	    </li>
	    <li>
	      <span class="title"><spring:message code="propietario" text="Propietario"/></span>
	      <span class="titleValue">
	        ${propiedad.propietario.nombreCompleto} - ${propiedad.propietario.dni}
	      </span>
	    </li>
	  </ul>
	  </fieldset>				
	</div>
    <a href="<spring:url value='edit/${propiedad.idPropiedad}'/>">Editar</a>
    <spring:url value="remove" var="removeUrl">
	  <spring:param name="idPropiedad">${propiedad.idPropiedad}</spring:param>
    </spring:url>
    <a href="${removeUrl}">Remover</a>