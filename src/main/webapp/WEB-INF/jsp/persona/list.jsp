<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div>
	<table>
		<thead>
			<tr>
				<th><spring:message code="nombre_completo" text="Full Name"/></th>
				<th><spring:message code="dni" text="ID"/>DNI</th>			
				<th><spring:message code="telefono" text="Phone"/>Telefono</th>
				<th><spring:message code="email" text="email"/>email</th>
				<th>Editar</th>
				<th>Eliminar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarioList }" var="personaItem">
				<tr>
					<td>${personaItem.apellido} ${personaItem.nombre}</td>
					<td>${personaItem.tipoDni} ${personaItem.dni}</td>
					<td>${personaItem.tipoTelefono}: ${personaItem.telefono}</td>
					<td>${personaItem.email}</td>
					<spring:url value="edit" htmlEscape="true" var="editUrl">
						<spring:param name="dni">${personaItem.dni }</spring:param>
					</spring:url>
					<spring:url value="remove" htmlEscape="true" var="removeUrl">
						<spring:param name="dni">${personaItem.dni }</spring:param>
					</spring:url>
					<td><a href="<spring:url value='/persona/edit/${personaItem.dni }'/>">editar</a></td>
					<td><a href="<spring:url value='/persona/${personaItem.dni }'/>">remover</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6"></td><td><a href="persona?new">Agregar</a> </td>
			</tr>
		</tfoot>
	</table>
</div>