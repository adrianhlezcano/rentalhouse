<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Rental House</title>
  <!-- Styles -->
  <link rel="stylesheet" href="<spring:url value="/resources/styles/main.css" htmlEscape="true" />" type="text/css"/>  
  <link rel="stylesheet" href="<spring:url value="/resources/styles/jquery-ui-1.8.23.custom.css" htmlEscape="true" />" type="text/css"/>  
  <!-- Javascripts -->
  <script type="text/javascript" src="<spring:url value="/resources/scripts/jquery-1.7.2.min.js" htmlEscape="true"/>" >
  </script>
  <script type="text/javascript" src="<spring:url value="/resources/scripts/jquery-ui-1.8.23.custom.min.js" htmlEscape="true"/>" >
  </script>
  <script type="text/javascript" src="<spring:url value="/resources/scripts/main.js" htmlEscape="true"/>" >
  </script>
  <script type="text/javascript">
    var getContext = function(){
    	var context = "${pageContext.request.contextPath}";
    	return context;
    };

    $(document).ready(function(){
		$("li.menuItem").click(function(){
		  var item = $(this).text();
	      var offset = $(this).offset();
	      displayMenu(item.toLowerCase(), offset);
	      $("#navigationMenu").show();        	    
		});
		setCheckedMenu();
		$("#provincia").change(function(){
			var provinciaId = $("#provincia").attr("value");
			get_localidades(provinciaId);
		});
		$("#createReport").click(function(){
			getReports();
		});
  	});
 
</script>  
</head>
<body>
<c:choose>
  <c:when test="${empty sessionScope.user }">
	<c:redirect url="/admin"></c:redirect>
  </c:when>
  <c:otherwise>  
  <div id="allContent">
  <header>    
    <div id="headerSlogan" class="headerSlogan">      
      <img id="slogan" src="<spring:url value='/resources/images/house10.jpeg' />" alt="rental house slogan" height="100px" width="150px"/>
    </div>
    <div id="headerTitle" class="headerTitle">
      Rental House
    </div>
  </header>
  
  <nav>  
    <ul>
      <li class="menuItem">Inquilino</li>
      <li class="menuItem">Propietario</li>
      <li class="menuItem">Garante</li>
      <li class="menuItem">Contrato</li>
      <li class="menuItem">Propiedad</li>
      <li class="menuItem">Reportes</li>
    </ul>    
  </nav>   

  <div id="tableContainer">
    <div id="tableRow">
      <section id="main">        
          <tiles:insertAttribute name="search"/>
          <tiles:insertAttribute name="content"/>	             
      </section>
      <aside>
      	<div style="text-align: right;">
      	  <a href="<spring:url value='/'/>">publico</a>
      	</div>
      	<br/>
        <tiles:insertAttribute name="login"/>	      
      </aside>     
    </div>     
  </div>
  <footer>
    Copyright &copy; 2013, <author>Adrian Lezcano</author><br/>
    This program is free software: you can redistribute it and/or modify <br/>
    it under the terms of the GNU General Public License version 3.
  </footer>  
  </div>   
  </c:otherwise>	
</c:choose>
</body>
</html>