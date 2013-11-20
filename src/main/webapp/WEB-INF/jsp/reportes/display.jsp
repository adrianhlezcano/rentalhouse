<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<div id="search">
  <fieldset>
	<legend>Seleccione un reporte</legend>			    
    	<ul style="list-style: none;">
    	<c:forEach items="${reportList }" var="reportListItem" >
		  <li><input type="radio" name="reportName" value="${reportListItem }"/> 
		  		<span class="reportTitles">${reportListItem.nameValue }</span>
		  </li>
		</c:forEach>		
		</ul>	
		<p align="right">
			<input type="button" value="Crear Reporte" id="createReport"/>
		</p>		  	      	
  </fieldset>
</div>
<div hidden="true" id="reportsDiv" style="padding: 5px; margin: 3px;" >
	<fieldset>
	<legend>Reportes Resultantes</legend>
	<ul id="reportUl" style="list-style-type: none;" >
		
	</ul>
	</fieldset>	
</div>