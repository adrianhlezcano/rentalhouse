<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="content">
	<table>
		<thead>
			<tr>
			    <th><spring:message code="inquilino" text="Inquilino"/></th>
				<th><spring:message code="propiedad" text="Propiedad"/></th>								
				<th><spring:message code="estado" text="Estado"/></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contratoList }" var="contratoItem">
				<tr>
				    <td>${contratoItem.inquilino.nombreCompleto}</td>
					<td>${contratoItem.propiedad}</td>					
					<td>${contratoItem.estadoContrato}</td>
					<td>
					  <spring:url value="/admin/contrato/cuotas/${contratoItem.idContrato }" var="payUrl">
					  	<spring:param name="page">0</spring:param>
					  </spring:url>
						<a href="${payUrl }">
							<spring:message code="pagar" text="Pagar"/>
						</a>
					</td>	
					<td>
						<a href="<spring:url value='/admin/contrato/${contratoItem.idContrato}'/>">Ver</a>							
					</td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>