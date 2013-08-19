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
  	});
 
</script>  
</head>
<body>
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
  </nav>   

  <div id="tableContainer">
    <div id="tableRow">
      <section id="main">
          <tiles:insertAttribute name="content"/>        	      
      </section>
      <aside>
         <tiles:insertAttribute name="login"/>	      
      </aside>     
    </div>     
  </div>
  <footer>
    &copy; 2013, <author>Adrian Lezcano</author><br/>
    All trademarks and registered trademarks on this site are property of the respective owner.
  </footer>  
  </div> 
</body>
</html>