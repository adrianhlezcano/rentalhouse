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
			<c:forEach items="${inquilinoList }" var="inquilinoItem">
				<tr>				
				    <td>${inquilinoItem.nombreCompleto}</td>
					<td>${inquilinoItem.tipoDni} ${inquilinoItem.dni}</td>
					<td>${inquilinoItem.tipoTelefono}: ${inquilinoItem.telefono}</td>					
					<td>						
						<a href="<spring:url value='/inquilino/${inquilinoItem.dni}'/>">
							<spring:message code="ver" text="Ver"/>
						</a>						
					</td>			
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</article>