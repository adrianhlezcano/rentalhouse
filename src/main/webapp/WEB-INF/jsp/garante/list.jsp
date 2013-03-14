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
			<c:forEach items="${garanteList }" var="garanteItem">
				<tr>				
				    <td>${garanteItem.nombreCompleto}</td>
					<td>${garanteItem.tipoDni} ${garanteItem.dni}</td>
					<td>${garanteItem.tipoTelefono}: ${garanteItem.telefono}</td>					
					<td>						
						<a href="<spring:url value='/garante/${garanteItem.dni}'/>">
							<spring:message code="ver" text="Ver"/>
						</a>						
					</td>			
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</article>