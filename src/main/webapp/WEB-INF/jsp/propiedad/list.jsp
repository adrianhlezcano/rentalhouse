<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<article>
	<table>
		<thead>
			<tr>
				<th><spring:message code="tipoPropiedad" text="Inmueble"/></th>
				<th><spring:message code="operacionPropiedad" text="Operacion"/></th>
				<th><spring:message code="precioAlquiler" text="Precio de Alquiler"/></th>
				<th><spring:message code="precioVenta" text="Precio de Venta"/></th>		
				<th><spring:message code="dormitorio" text="Dorm."/></th>				
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${propiedadList }" var="propiedad">
				<tr>
					<td>${propiedad.tipoPropiedad.value}</td>
					<td>${propiedad.operacionPropiedad.value}</td>
					<td>
					  <c:if test="${not empty propiedad.precioAlquiler}">
					    ${propiedad.tipoMoneda.value} ${propiedad.precioAlquiler}
					  </c:if>
					</td>
					<td>
					  <c:if test="${not empty propiedad.precioVenta}">
					    ${propiedad.tipoMoneda.value} ${propiedad.precioVenta}
					  </c:if>	
					</td>				
					<td>${propiedad.dormitorios}</td>								
					<td>
						<a href="propiedad/${propiedad.idPropiedad }">
							<spring:message code="ver" text="Ver"/>
						</a>
					</td>					
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</article>