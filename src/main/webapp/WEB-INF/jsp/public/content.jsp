<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript"> 

	var setImages = function(imageMap) {
		var imageList = imageMap;	
		var context = getContext();
		imageList = imageList.split(";");
		
		
		if (!jQuery.isEmptyObject(imageList)) {
			for (var j = 0; j < imageList.length; j++){
				var image = imageList[j];
				image = image.split(":");
				var imgId = image[0];
				var imgVal = image[1];				
				$("#propiedad"+imgId).attr("src", context.concat(imgVal));
			}
		}
	};
	$(document).ready(function() {		
		setImages("${imageMap}");
	});
</script>
<article>
	<table>
		<thead>
			<tr>
				<th><spring:message code="tipoPropiedad" text="Inmueble"/></th>
				<th><spring:message code="operacionPropiedad" text="Operacion"/></th>
				<th><spring:message code="precio" text="Precio"/></th>		
				<th><spring:message code="dormitorio" text="Dorm."/></th>								
				<th><spring:message code="domicilio" text="Domicilio"/></th>
				<th></th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${propiedadList }" var="propiedad">
				<tr>
					<td>
					  <a href="propiedad/${propiedad.idPropiedad }">${propiedad.tipoPropiedad.value}</a>
					</td>
					<td>${propiedad.operacionPropiedad.value}</td>
					<td>
					  ${propiedad.tipoMoneda.value}
					  <c:choose>
					  	<c:when test="${not empty propiedad.precioAlquiler}">
					  	  ${propiedad.precioAlquiler}
					  	</c:when>
					  	<c:otherwise>
					  	   ${propiedad.precioVenta}
					  	</c:otherwise>
					  </c:choose>					  
					</td>		
					<td>${propiedad.dormitorios}</td>	
					<td>${propiedad.domicilio.calle} ${propiedad.domicilio.numero}</td>
					<td>					  
					  <img alt="Image de Propiedad" src="" id="propiedad${propiedad.idPropiedad }" height="80px" width="100px">
					</td>
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</article>