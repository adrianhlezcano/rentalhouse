<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="search">
  <fieldset>
	<legend>Buscar Inquilino</legend>
	  <input type="radio" name="fieldName" value="dni"  id="fieldName1"/>
	  <label for="fieldName1"><spring:message code="dni" text="DNI"/></label>
	  &nbsp;&nbsp;
	  <input type="radio" name="fieldName" value="apellido"  id="fieldName2"/>
	  <label for="fieldName2"><spring:message code="apellido" text="Apellido"/></label>	
	  &nbsp;
	  <input id="fieldValue" type="text" name="fieldValue"/>	 
	  <input id="searchButton" type="button" value="Buscar" />	  
  </fieldset>
</div>
<script type="text/javascript">
$("#searchButton").click(function(){
	var fieldName = $("input:radio[name=fieldName]:checked").val();
	var fieldValue = $("#fieldValue").val();	
	if (fieldName && fieldValue){
		get_contratos(fieldName, fieldValue, 0);	
	} else {
		alert("The search text field is empty.")
	}			
});	
</script>