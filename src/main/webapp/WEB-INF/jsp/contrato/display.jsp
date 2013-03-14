<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	<div id="personaTable">
		<fieldset>
			<table>
			  <tbody>	
			    <tr>
					<td><spring:message code="fechaCreacion" text="Fecha de Creacion"/></td>
					<td><fmt:formatDate value="${contrato.fechaCreacion }" pattern="dd/MM/yyyy" var="fecha"/>
						<c:out value="${fecha }"/></td>
				</tr>		    
				<tr>
			      <td><spring:message code="Inquilino" text="Inquilino"/></td>
				  <td>${contrato.inquilino.apellido}, ${contrato.inquilino.nombre } - ${contrato.inquilino.dni }</td>
			    </tr>				
				<tr>
					<td><spring:message code="Propiedad" text="Propiedad"/></td>
					<td>
					  ${contrato.propiedad.operacionPropiedad.value }: ${contrato.propiedad.tipoPropiedad.value } - 
		    		  ${contrato.propiedad.tipoMoneda.value } - ${contrato.propiedad.precioAlquiler } 
		    		  (
		    		  ${contrato.propiedad.domicilio.calle } ${contrato.propiedad.domicilio.numero }
		    		  <c:if test="${not empty contrato.propiedad.domicilio.piso}">${contrato.propiedad.domicilio.piso}</c:if>
		    		  <c:if test="${not empty contrato.propiedad.domicilio.depto}">${contrato.propiedad.domicilio.depto}</c:if>
		    		  )
					</td>					
				</tr>
				<tr>
					<td><spring:message code="depositoGarantia" text="Deposito"/></td>
					<td>${contrato.depositoGarantia }</td>
				</tr>
				<tr>
					<td><spring:message code="honorarios" text="Honorarios"/> </td>
					<td>${contrato.honorarios}</td>
				</tr>
				<tr>
					<td><spring:message code="comision" text="Comision"/></td>
					<td>${contrato.comision }</td>
				</tr>
				
				<tr>
				    <td><spring:message code="cuotas" text="Meses"/></td>
					<td>${contrato.cuotas.size }</td>
				</tr>
				<c:if test="${not empty contrato.observacion}">
				<tr>
				    <td><spring:message code="observacion" text="Observacion"/></td>
					<td>${contrato.observacion }</td>
				</tr>
				</c:if>
				<tr>
				    <td><spring:message code="estadoContrato" text="Estado del Contrato"/></td>
					<td>${contrato.estadoContrato.value}</td>
				</tr>				
		      </tbody>			
			</table>		
		</fieldset>
	</div>
<a href="<spring:url value='edit/${contrato.idContrato}'/>">editar</a>