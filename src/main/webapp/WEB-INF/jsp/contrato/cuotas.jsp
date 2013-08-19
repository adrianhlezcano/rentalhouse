<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="detalle"> 
    <span class="title"><spring:message code="inquilinio" text="Inquilinio"/>:</span> 
    <span class="titleValue">${contrato.inquilino.nombreCompleto }</span></br>

    <span class="title"><spring:message code="Propiedad" text="Propiedad"/>:</span> 
    <span class="titleValue">
    ${contrato.propiedad.tipoPropiedad.value }
    &lt; ${contrato.propiedad.domicilio.calle } ${contrato.propiedad.domicilio.numero } &gt;    
    </span></br>
</div>
<div id="content">
<table>
	<thead>
		<tr>
	    <th><spring:message code="cuotas" text="Meses"/></th>
		<th><spring:message code="fechaVencimiento" text="Vencimiento"/></th>
		<th><spring:message code="adeudado" text="Adeudado"/></th>
		<th><spring:message code="fechaPago" text="Fecha de Pago"/></th>				
		<th><spring:message code="pagado" text="Pagado"/></th>					
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cuotaList }" var="cuota">
			<tr class="row">
			<td>
			${cuota.numeroCuota} 
			<c:if test="${not cuota.pagado }">
			  <input id="${cuota.numeroCuota}" name="cuota" type='radio' align="right" onclick="enableRow(this);"/> 
			</c:if>
			</td>		
			<fmt:formatDate value="${cuota.fechaVencimiento}" pattern="dd-MM-yyyy" var="fechaVencimiento"/>                    
			<td>${fechaVencimiento }</td>					
			<td>${cuota.importeAdeudado}</td>					
			<c:choose>
			<c:when test="${cuota.pagado }">
				<td>${cuota.fechaPago}</td>
		        <td>${cuota.importePagado}</td>
			</c:when>
			<c:otherwise>						  
				<td></td>
				<form:form method="POST">
				<td>							
				  <input type="text" name="importePagado" value="${cuota.importeAdeudado }" 
				     size="6" maxlength="10" disabled="disabled" onkeyup="checkDigit(this);" />
				  <input type="hidden" name="idCuota" value="${cuota.idCuota }" />
				  <input type="submit" name="pay" value="PAGAR" disabled="disabled"/>
				  <span id="error"></span>							  
				</td>	
		        </form:form>
			</c:otherwise>
			</c:choose>				
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	  <tr>
	    <spring:url value="/admin/contrato/cuotas/${cuotaList[0].contrato.idContrato }" var="back">
		  	<spring:param name="page">${previous}</spring:param>
		</spring:url>
		<spring:url value="/admin/contrato/cuotas/${cuotaList[0].contrato.idContrato }" var="forward">
		  	<spring:param name="page">${next}</spring:param>
		</spring:url>			
		<td align="left">
		  <c:if test="${not empty previous and previous gt -1 }">
		    <a href="${back }">Atras</a>
		  </c:if>		
		</td>
		<td colspan="3"></td>
		<td align="right">
		  <c:if test="${not empty next and next gt 0 }">
		    <a href="${forward }">Siguiente</a>
		  </c:if>			
		</td>
	  </tr>			
	</tfoot>		
</table>
</div>