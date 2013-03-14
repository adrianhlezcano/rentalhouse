<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	<div id="formulario">
		<h2>Create a new persona</h2>
		<form:form method="POST" modelAttribute="personaForm">
			<form:hidden path="idPersona"/>
			<form:hidden path="action"/>
			<fieldset>
			<table>				
				<tr>
					<th><spring:message code="apellido" text="Surnames"/></th>
					<td><form:input path="apellido" size="25" id="persona_apellido"/>
						<form:errors path="apellido" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="nombre" text="Names"/></th>
					<td><form:input path="nombre" size="50" id="persona_nombre"/>
						<form:errors path="nombre" cssClass="error"/>
					</td>
				<tr>
				<tr>
					<th><spring:message code="tipoDeDni" text="ID Type"/></th>
					<td><form:select path="tipoDni" id="tipo_dni">
							<form:options items="${personaForm.tiposDni }"/>
					    </form:select></td> 				
				<tr>
				<tr>
					<th><spring:message code="dni" text="ID"/></th>
					<td><form:input path="dni" size="10" id="persona_dni"/>
						<form:errors path="dni" cssClass="error"/>	
					</td>
				<tr>
				<tr>
					<th><spring:message code="cuit" text="CUIT"/></th>
					<td><form:input path="cuit" size="15" id="persona_cuit"/>
						<form:errors path="cuit" cssClass="error"/>
					</td>
				<tr>
				<tr>
					<th><spring:message code="fechaDeNacimiento" text="Date of Born"/></th>
					<td>
						<spring:message code="dia" text="Date"/>
						<form:select path="diaNacimiento" id="dia_nacimiento" >
							<form:options items="${personaForm.dias }"/> 
						</form:select>
						<spring:message code="mes" text="Month"/>
						<form:select path="mesNacimiento" id="mes_nacimiento" >
							<form:options items="${personaForm.meses }"/> 
						</form:select>
						<spring:message code="anio" text="Year"/>
						<form:select path="anioNacimiento" id="anio_nacimiento" >
							<form:options items="${personaForm.anios }"/> 
						</form:select>
					</td>
				<tr>
				<tr>
					<th><spring:message code="tipoTelefono" text="Phone Type"/></th>
					<td><form:select path="tipoTelefono" id="tipo_telefono">
							<form:options items="${personaForm.tiposTelefono }"/>							 
						</form:select></td>
				<tr>
				<tr>
					<th><spring:message code="telefono" text="Phone"/></th>
					<td><form:input path="telefono" size="15" id="persona_telefono"/>
						<form:errors path="telefono" cssClass="error"/>
					</td>
				<tr>
				<tr>
					<th><spring:message code="email" text="mail"/></th>
					<td><form:input path="email" size="50" id="persona_email"/>
						<form:errors path="email" cssClass="error"/>
					</td>
				<tr>	
				<tr>
					<th></th>
					<td><input type="submit" value="<spring:message code="enviar" text="Send"/>"/></td>
				</tr>		   
			</table>
			</fieldset>
		</form:form>
	</div>