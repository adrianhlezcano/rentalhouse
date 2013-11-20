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
// 					return context + "/resources" + imageList[0];
					return context + imageList[0];
				}
// 				var imgName = context + "/resources" + imageList[i];
                var imgName = context + imageList[i];
				if (imgName === imageName) {
					if (i === (imageList.length - 1)) {
// 						return context + "/resources" + imageList[0];
						return context + imageList[0];
					} else {
// 						return context + "/resources" + imageList[i + 1];
						return context + imageList[i + 1];
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
	  <span class="title" style="color: #954b4b;">
        ${propiedad.operacionPropiedad.value } - ${propiedad.tipoPropiedad.value } - Precio
        <c:choose>
	      <c:when test="${not empty propiedad.precioAlquiler }">		   
			${propiedad.tipoMoneda.value}${propiedad.precioAlquiler }		
		  </c:when>
		  <c:otherwise>
		     ${propiedad.tipoMoneda.value} ${propiedad.precioVenta }
		  </c:otherwise>
		</c:choose>        
      </span>
	</legend>
	  <div>
      <div id="displayImage" style="width: 350px; height: 240px; float: right; margin: 0px 10px 10px 10px;" >
	  	<fieldset>
	  	<legend class="title">Imagenes</legend>
	  		<div>
	  			<a href="" id="rotateImageUrl">
	  			  <img id="rotateImage" alt="Imagen" src="" height="235px" width="320px"/>
	  			</a>
	  		</div>  			  	
	  	</fieldset>
	  </div>	 
	  <div id="detalle">
	  <p style="line-height: 1.5em;">   
	      <span class="title"><spring:message code="precio" text="Precio"/></span>
	      <span class="titleValue">
	      <c:choose>
	      <c:when test="${not empty propiedad.precioAlquiler }">		   
			${propiedad.tipoMoneda.value}${propiedad.precioAlquiler }		
		  </c:when>
		  <c:otherwise>
		     ${propiedad.tipoMoneda.value} ${propiedad.precioVenta }
		  </c:otherwise>
		  </c:choose>		  
		  </span><br>
		  
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
	    
	      <span class="title"><spring:message code="localidad" text="Localidad"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.nombre}</span><br>
	   
	      <span class="title"><spring:message code="provincia" text="Provincia"/></span>
	      <span class="titleValue">${propiedad.domicilio.localidad.provincia.nombre }</span><br><br><br>
	      <span class="title"><a href="<spring:url value='/'/>">Volver</a></span>   
	      
	  </div>	  
	</div>
  </fieldset>				
</div>
<br>
<div>
  <fieldset>
    <legend class="title" style="color: #954b4b;">Contactanos</legend>
    <div style="margin-right: 10px; float: left;">   
    <label for="email" class="title"><spring:message code="email" text="Mail"/></label><br/>
    <input name="email" id="email" tabindex="1"/><br/>
    
    <label for="asunto" class="title"><spring:message code="asunto" text="Asunto"/></label><br/>
    <input name="asunto" id="asunto" tabindex="2"/> <br/>   
        <input type="submit" id="consultar" value="<spring:message code="consultar" text="Consultar"/>" tabindex="4"/>
    </div> 
    
    <label for="consulta" class="title"><spring:message code="consulta" text="Consulta"/></label><br/>
    <textarea name="consulta" id="consulta" cols="50" rows="4" tabindex="3"></textarea>  
<!--     <div style="text-align: right; margin-right: 5px;"> -->
<%--       <input type="submit" id="consultar" value="<spring:message code="consultar" text="Consultar"/>" tabindex="4"/> --%>
<!--     </div> -->
  </fieldset>
</div>    