<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="search">
  <fieldset>
	<legend>Buscar Inmueble</legend>		  
	    <div>
	      <label for="tipoPropiedad"><spring:message code="tipoPropiedad" text="Inmueble"/></label>
	      <select name="tipoPropiedad" id="tipoPropiedad">
		    <c:forEach items="${propiedadForm.tiposPropiedad }" var="tipoPropiedad">
		      <option value="${tipoPropiedad }">${tipoPropiedad.value }</option>
		    </c:forEach>
          </select>          
	    </div>
	    <div>
	      <label for="operacionPropiedad">
	        <spring:message code="operacionPropiedad" text="Operacion"/>
	      </label>
	      <select name="operacionPropiedad" id="operacionPropiedad">
		    <c:forEach items="${propiedadForm.operaciones}" var="operacion">
		      <option value="${operacion }">${operacion.value }</option>
		    </c:forEach>
          </select>	     
	    </div>
	    <div>
	      <spring:message code="propiedad_precio" text="Precio:"/>	       
	      <label for="precioMinimo"><spring:message code="desde" text="desde"/></label>
	      <input  name="precioMinimo" maxlength="10" id="precioMinimo" size="7" placeholder="1000" />
	      &nbsp;&nbsp;
	      <label for="precioMaximo"><spring:message code="hasta" text="hasta"/></label>
	      <input  name="precioMaximo" maxlength="10" id="precioMaximo" size="7" placeholder="2000"/>
	      &nbsp;&nbsp;&nbsp;&nbsp;
	      <label for="dormitorio"><spring:message code="dormitorio" text="Dormitorios"/></label>
	      <select name="dormitorios" id="dormitorio">
		    <c:forEach items="${propiedadForm.dormitorioList }" var="dormitorio">
		      <option value="${dormitorio }">${dormitorio }</option>
		    </c:forEach>
		  </select>
	    </div>	    
	    <div>	      
		  <input type="submit" id="searchButton" value="<spring:message code="buscar" text="Buscar"/>" />  	 
	    </div>	    
  </fieldset>
</div>
<script type="text/javascript">
	$("#searchButton").click(function(){
		get_propiedades();
	});
</script>