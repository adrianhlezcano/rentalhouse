<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<aside>
<div id="search">
  <fieldset>
	<legend class="title" style="color: #954b4b; margin: 10px 0px 10px 0px;">Buscar Inmueble</legend>		  
	    <div  style="margin: 0px 0px 10px 0px;">
	      <label for="tipoPropiedad" class="title"><spring:message code="tipoPropiedad" text="Inmueble"/></label><br>
	      <select name="tipoPropiedad" id="tipoPropiedad">
		    <c:forEach items="${propiedadForm.tiposPropiedad }" var="tipoPropiedad">
		      <option value="${tipoPropiedad }">${tipoPropiedad.value }</option>
		    </c:forEach>
          </select>          
	    </div>
	    <div style="margin: 10px 0px 10px 0px;">
	      <label for="operacionPropiedad" class="title">
	        <spring:message code="operacionPropiedad" text="Operacion"/>
	      </label><br>
	      <select name="operacionPropiedad" id="operacionPropiedad">
		    <c:forEach items="${propiedadForm.operaciones}" var="operacion">
		      <option value="${operacion }">${operacion.value }</option>
		    </c:forEach>
          </select>	     
	    </div>
	    <div style="margin: 10px 0px 10px 0px;">
	      <label for="dormitorio" class="title"><spring:message code="dormitorio" text="Dormitorios"/></label><br>
	      <select name="dormitorios" id="dormitorio">
		    <c:forEach items="${propiedadForm.dormitorioList }" var="dormitorio">
		      <option value="${dormitorio }">${dormitorio }</option>
		    </c:forEach>
		  </select>
	    </div>
	    <div style="margin: 10px 0px 10px 0px;">
	      <fieldset>
	      <legend class="title"><spring:message code="propiedad_precio" text="Precio"/></legend>
	             
	      <label for="precioMinimo"><spring:message code="desde" text="Desde"/></label>
	      <input  name="precioMinimo" maxlength="10" id="precioMinimo" size="7" placeholder="1000" /><br>
	      
	      <label for="precioMaximo"><spring:message code="hasta" text="Hasta"/></label>
	      <input  name="precioMaximo" maxlength="10" id="precioMaximo" size="7" placeholder="2000"/><br>      
		  </fieldset>
	    </div>	    
	    <div style="text-align: center;">	      
		  <input type="submit" id="searchButton" value="<spring:message code="buscar" text="Buscar"/>" />  	 
	    </div>	    
  </fieldset>
</div>
<br/><br/>
</aside>

<script type="text/javascript">
	$("#searchButton").click(function(){
		get_propiedades_in_home();
	});
</script>