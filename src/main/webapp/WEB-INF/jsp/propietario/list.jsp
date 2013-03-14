<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<article>
	<table>
		<thead>
			<tr>			
				<th><spring:message code="nombre_completo" text="Nombre Completo"/></th>
				<th><spring:message code="dni" text="DNI"/></th>
				<th><spring:message code="telefono" text="Telefono"/></th>							
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${propietarioList }" var="propietarioItem">
				<tr>				
				    <td>${propietarioItem.nombreCompleto}</td>
					<td>${propietarioItem.tipoDni} ${propietarioItem.dni}</td>
					<td>${propietarioItem.tipoTelefono}: ${propietarioItem.telefono}</td>					
					<td>						
						<a href="<spring:url value='/propietario/${propietarioItem.dni}'/>">
							<spring:message code="ver" text="Ver"/>
						</a>						
					</td>			
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</article>