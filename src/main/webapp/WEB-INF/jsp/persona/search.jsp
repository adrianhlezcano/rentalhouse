<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="search">
  <fieldset>
	<legend>Buscar</legend>		
	 <input type="radio" name="fieldName" value="dni"  id="fieldName" checked="checked"/><spring:message code="dni" text="DNI"/>
	  &nbsp;&nbsp;
	  <input type="radio" name="fieldName" value="apellido"  id="fieldName"/><spring:message code="apellido" text="Apellido"/>		
	  <input id="fieldValue" type="text" name="fieldValue"/>	  
	  <input id="searchButton" type="button" value="Buscar"/>		  
  </fieldset>
</div>
<script type="text/javascript">
setSearchLegend();
$("#radioset").buttonset();		
$("#searchButton").click(function(){
	var fieldName = $("input:radio[name=fieldName]:checked").val();
	var fieldValue = $("#fieldValue").val();
	if (!(fieldName && fieldValue )){
		alert("The search text field is empty.");
	} else if (fieldValue.length <  4){
		alert("Pleaser, insert a valid "+fieldName+" to search");
	} else {
		get_persona(fieldName, fieldValue, 0);
	}
});	
</script>