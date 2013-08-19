<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">    

	var getNextImage = function(imageName) {
		var imageList = "${imgList}";
		var context = getContext();
		
		if (imageList && imageList.length > 0) {
			var i = 0;
			imageList = imageList.split(";");
			for (i = 0; i < imageList.length; i++) {
				if (imageName === "") {
					return context + "/resources" + imageList[0];
				}
				var imgName = context + "/resources" + imageList[i];
				if (imgName === imageName) {
					if (i === (imageList.length - 1)) {
						return context + "/resources" + imageList[0];
					} else {
						return context + "/resources" + imageList[i + 1];
					}
				}
			}
		} else {
			return context + "/resources/images/house.jpeg";
		}		
	};
	
	var rotateImages = function() {
		var src = $("#rotateImage").attr("src");
		var nextImg = getNextImage(src);
		$("#rotateImage").attr("src", nextImg);
		$("#rotateImageUrl").attr("src", nextImg);		
	};

	$(document).ready(function() {
		$("#rotateImage").attr("src", getNextImage(""));		
		window.setInterval(function(){rotateImages()}, 5000);
	});
</script>
<div id="detalle">
	<fieldset>
	<legend>
	  <span class="title">
        ${propiedad.operacionPropiedad.value } - ${propiedad.tipoPropiedad.value } 
        &lt; ${propiedad.domicilio.calle } ${propiedad.domicilio.numero } &gt;
      </span>
	</legend>
	  <div>
      <div id="displayImage" style="width: 220px; height: 220px; float: right; margin: 0px 10px 10px 0px;" >
	  	<fieldset>
	  	<legend>Imagenes</legend>
	  		<div>
	  			<a href="" id="rotateImageUrl">
	  			  <img id="rotateImage" alt="Imagen" src="" height="200px" width="200px"/>
	  			</a>
	  		</div>  			  	
	  	</fieldset>
	  </div>	 
	  <div id="detalle">
	  <p style="line-height: 1.5em;">	    
	      <span class="title"><spring:message code="tipoPropiedad" text="Inmueble"/></span> 
	      <span class="titleValue">${propiedad.tipoPropiedad.value }</span><br>
	    
	      <span class="title"><spring:message code="operacionPropiedad" text="Operacion"/></span>
	      <span class="titleValue">${propiedad.operacionPropiedad.value }</span><br>
	    
	      <span class="title"><spring:message code="precioAlquiler" text="Precio de Alquiler"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.precioAlquiler }">		   
			${propiedad.tipoMoneda.value}${propiedad.precioAlquiler }		
		  </c:if></span><br>
	    
	      <span class="title"><spring:message code="precioVenta" text="Precio de Venta"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.precioVenta }">	
	      ${propiedad.tipoMoneda.value} ${propiedad.precioVenta }</c:if></span><br>
	    
	      <span class="title"><spring:message code="dormitorio" text="Dorm."/> </span>
		  <span class="titleValue">${propiedad.dormitorios }</span><br>
	    
	      <span class="title"><spring:message code="detalle" text="Detalle"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.detalle}">${propiedad.detalle }
	      </c:if></span><br>	    
	    
	      <span class="title"><spring:message code="superficie" text="Sup. m2"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.superficie}">${propiedad.superficie }
	      </c:if></span><br>
	    
	      <span class="title"><spring:message code="estrenar" text="A estrenar?"/></span>
	      <span class="titleValue"><c:choose>
	        <c:when test="${propiedad.estrenar}">Si</c:when>
		    <c:otherwise>No</c:otherwise>
		  </c:choose></span><br>
	    
	      <span class="title"><spring:message code="aptoCredito" text="Apto Credito?"/></span>
		  <span class="titleValue"><c:choose>
			<c:when test="${propiedad.aptoCredito}">Si</c:when>
			<c:otherwise>No</c:otherwise>
		  </c:choose></span><br>
		
	      <span class="title"><spring:message code="aptoEscritura" text="Apto Escritura?"/></span>
	      <span class="titleValue"><c:choose>
		    <c:when test="${propiedad.aptoEscritura}">Si</c:when>
		    <c:otherwise>No</c:otherwise>
		  </c:choose></span><br>
	    
	      <span class="title"><spring:message code="calle" text="Calle"/></span>
	      <span class="titleValue">${propiedad.domicilio.calle } ${propiedad.domicilio.numero }</span><br>
	   
	      <span class="title"><spring:message code="piso" text="Piso"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.domicilio.piso}">
	        ${propiedad.domicilio.piso } "${propiedad.domicilio.depto }"
	      </c:if></span><br>
	    
	      <span class="title"><spring:message code="barrio" text="Barrio"/></span>
	      <span class="titleValue"><c:if test="${not empty propiedad.domicilio.barrio}">
	        ${propiedad.domicilio.barrio}
	      </c:if></span><br>
	    
	      <span class="title"><spring:message code="codigoPostal" text="Codigo Postal"/></span>
	      <span class="titleValue">${propiedad.domicilio.codigoPostal }</span><br>
	    
	      <span class="title"><spring:message code="localidad" text="Localidad"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.nombre}</span><br>
	   
	      <span class="title"><spring:message code="provincia" text="Provincia"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.provincia.nombre }</span><br>
	   
	      <span class="title"><spring:message code="propietario" text="Propietario"/></span>
	      <span class="titleValue">
	        ${propiedad.propietario.nombreCompleto} - ${propiedad.propietario.dni}
	      </span><br> 
	  </div>
	</div>
	</fieldset>				
	</div>
    <a href="<spring:url value='edit/${propiedad.idPropiedad}'/>">Editar</a>
    <spring:url value="remove" var="removeUrl">
	  <spring:param name="idPropiedad">${propiedad.idPropiedad}</spring:param>
    </spring:url>
    <a href="${removeUrl}">Remover</a>