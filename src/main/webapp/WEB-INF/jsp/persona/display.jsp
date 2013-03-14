<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	<div id="personaTable">
		<fieldset>
			<table>
				<tr>
					<td>${persona.apellido } ${persona.nombre }</td>					
				</tr>
				<tr>
					<td>${persona.tipoDni } ${persona.dni }</td>
				</tr>
				<tr>
					<td>${persona.cuit }</td>
				</tr>
				<tr>
					<td><fmt:formatDate value="${persona.fechaNacimiento }" pattern="dd/MM/yyyy" var="personaFechaNacimiento"/>
						<c:out value="${personaFechaNacimiento }"/></td>
				</tr>
				<tr>
					<td>${persona.tipoTelefono } ${persona.telefono }</td>
				</tr>
				<tr>
					<td>${persona.email }</td>
				</tr>			
			</table>		
		</fieldset>
	</div>
<a href="../persona">Lista de Personas</a>
<a href="<spring:url value='edit/${persona.dni}'/>">editar</a>
<spring:url value="remove" var="removeUrl">
	<spring:param name="idPersona">${persona.idPersona }</spring:param>
</spring:url>
<a href="${removeUrl}">remover</a>