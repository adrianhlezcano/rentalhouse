var valid_search = function(){
	var fieldValue = $("#fieldValue").val();
	if (!(fieldValue)){
		return false;		
	}
	return true;
};
/**
 * create the menu items with the corresponding urls
 */
var menuNav = {
    'inquilino' : {'Nuevo' : '/admin/inquilino?new', 'Listar' : '/admin/inquilino' },
    'propietario' : {'Nuevo' : '/admin/propietario?new', 'Listar' : '/admin/propietario' },
    'garante' : {'Nuevo' : '/admin/garante?new', 'Listar' : '/admin/garante' },
    'contrato' : {'Nuevo' : '/admin/contrato?new', 'Listar' : '/admin/contrato' },
    'propiedad' : {'Nuevo' : '/admin/propiedad?new', 'Listar' : '/admin/propiedad' }		  
};
/**
 * Show menu items when the user clicks in an specific menu (E.g: Propiedad)
 * 
 * @param item
 * @param offset
 */
var displayMenu = function(item, offset) {
	var urlPath = getContext();	
	var top = parseInt(offset.top + 20) || offset.top;
	var left = parseInt(offset.left) || offset.left;
	var divContent = "<div id='navigationMenu' style='position: fixed; top:"+top+"px; left: "+left+"px;'>";
    divContent += "<ul>";
	for(var i in menuNav[item]){
		divContent += "<li><a href='"+ urlPath + menuNav[item][i] + "'>"+i+"</a></li>";
	}
	divContent += "</ul>";	
	$("body").append(divContent);
	$("#navigationMenu").mouseenter(
      function () {
		$("#navigationMenu").show();		
	}).mouseleave(
	  function () {
		$("#navigationMenu").remove();
	    });
};
/**
 * Determine the actual position to display menu items
 * 
 * @param item
 * @param offset
 */
var checkMenu = function(item, offset){
	var top = parseInt(offset.top + 20) || offset.top;
	var left = parseInt(offset.left) || offset.left;
	var navigatioMenuOffset = $("#navigationMenu").offset();
	var topMenu = parseInt(navigatioMenuOffset.top + 20);
	var leftMenu = parseInt(navigatioMenuOffset.left);
	if (top == topMenu && left == leftMenu){
		$("#navigationMenu").show();
	}else{
		$("#navigationMenu").remove();
	}
};
/**
 * Change the background of the menu item selected
 * 
 */
var setCheckedMenu = function(){
	var url = document.URL;
    $(".menuItem").each(function(index){
		var item = $(this).text();		
		if (url.indexOf(item.toLowerCase()) >= 0){
			$(this).css('background-color', '#c8b99c');
		}else{
			$(this).css('background-color', '#efe5d0');
		}
	});
};
/**
 * modulo: CUOTAS
 * disable all cuotas, except the cuota selected 
 * 
 * @param value
 */
var enableRow = function (value) {
	var id = $(value).attr("id");
	$(".row").each(function(index){
		var rowId = $(this).find("input:radio[name=cuota]").attr("id");
	    if(rowId && rowId == id){
	    	$(this).find("input:text[name=importePagado]").removeAttr("disabled");
	    	$(this).find("input:submit[name=pay]").removeAttr("disabled");
	    }else if(rowId) {
	    	$(this).find("input:text[name=importePagado]").attr("disabled", "disabled");
	    	$(this).find("input:submit[name=pay]").attr("disabled", "disabled");
	    }
	});
};
/**
 * modulo: CUOTAS
 * check if the user enter digits in importe a pagar
 * 
 * @param value
 * @returns
 */
var checkDigit = function (value) {
	var text = $(value).val();
	var patt=/^([\d\.]{1,7})$/g;
	if (text && patt.exec(text)) {
		$(value).attr("value", text);	 	
	}else if (text) {
		var val = new String(text);		
		$(value).attr("value",val.substring(0, val.length - 1));
	}
};
/**
 * Get Inquilino By dni
 * 
 * @param dni
 */
var get_inquilino = function (inquilinoDni){
	var url = getContext() + "/admin/inquilino/get?dni="+inquilinoDni;	
	$("#contrato_inquilino").empty();
	$("#inquilino_name").empty();
	$("#idInquilino").empty();
	$("#inquilino_name").show();
	$.getJSON(url, function (persona){		
		if (persona && persona.idPersona){
			$("#inquilino_name").append(persona.nombreCompleto);		
			$("#idInquilino").attr("value", persona.idPersona);		
		}else{
			$("#inquilino_name").append("<span class='error'>No se han encontrado resultos.</span>");
			$("#idInquilino").attr("value", "");
		}
	});
};
/**
 * Get Contratos By Inquilino
 * 
 * @param fn FieldName
 * @param fv FieldValue
 * @param page Page Number
 */
var get_contratos = function (fn, fv, page){	
	var url = getContext() + "/admin/contrato/search?fieldName="+fn+"&fieldValue="+fv+"&page="+page;
	$("table tbody").empty();
	$.getJSON(url, function (result){
	  for (var i = 0; i < result.contratoList.length; i++){
		var row = "<tr><td>"+result.contratoList[i].nombreInquilino+"</td>";
		row += "<td>"+result.contratoList[i].propiedad+"</td>";
		row += "<td>"+result.contratoList[i].estadoContrato.toUpperCase() +"</td>";		
		var pagarPath = getContext() + "/admin/contrato/cuotas/"+result.contratoList[i].idContrato;
		row += "<td><a href='"+pagarPath+"'>Pagar</a></td></tr>"
		$("table tbody").append(row);
	  }		
	});
};
/**
 * Get localidad by provincia seleted
 * 
 * @param provinciaId int
 */
var get_localidades = function (provinciaId){
	var url = getContext() + "/admin/localidad/"+provinciaId;
	$("#localidad").empty();			
	$.getJSON(url, function (localidades){		
		for(var i = 0; i < localidades.length; i++){
			$('#localidad').append($("<option/>", {
		        value: localidades[i].idLocalidad,
		        text: localidades[i].nombre
		    }));
		} 
	});	
};
/**
 * Get garante full name by DNI
 * 
 * @param garanteDni
 */
var get_garante = function(garanteDni){
	var url = getContext() + "/admin/garante/get?dni="+garanteDni;	
	$("#inquilino_garante").empty();
	$("#inquilino_name").empty();
	$("#idGarante").empty();
	$("#inquilino_name").show();
	$.getJSON(url, function (persona){		
		if (persona && persona.idPersona){
			$("#inquilino_name").append(persona.nombreCompleto);		
			$("#idGarante").attr("value", persona.idPersona);		
		}else{
			$("#inquilino_name").append("<span class='error'>No se han econtrado resultados.</span>");
			$("#idGarante").attr("value", "");
		}
	});
};
/**
 * Get propietario full name by propietario dni
 * 
 * @param propietarioDni
 */
var get_propietario = function (propietarioDni){
	var url = getContext() + "/admin/propietario/get?dni="+propietarioDni;	
	$("#propiedad_propietario").empty();
	$("#propietario_name").empty();
	$("#idPropietario").empty();
	$("#propietario_name").show();
	$.getJSON(url, function (persona){		
		if (persona && persona.idPersona){
			$("#propietario_name").append(persona.nombreCompleto);		
			$("#idPropietario").attr("value", persona.idPersona);		
		}else{
			$("#propietario_name").append("<span class='error'>No se han econtrado resultados.</span>");
			$("#idPropietario").attr("value", "");
		}
	});
};
/**
 * Get list of propiedades
 * 
 * 
 * @param tipo TipodeInmueble
 * @param operacion TipodeOperacion
 * @param precioMinimo 
 * @param precioMaximo
 * @param dorms dormitorios
 */
//var get_propiedades = function(tipo, operacion, precioMinimo, precioMaximo, dorms){	
var get_propiedades = function(){
	var tipo=$("#tipoPropiedad").val();
	var operacion=$("#operacionPropiedad").val();
	var precioMinimo=parseInt($("#precioMinimo").val()) || 0;
	var precioMaximo=parseInt($("#precioMaximo").val()) || 0;
	var dormitorios=parseInt($("#dormitorio").val()) || 0;
	
	var url = getContext() + "/admin/propiedad/search?";
	url += "tipoPropiedad="+tipo+"&operacionPropiedad="+operacion;
	url += "&precioMinimo="+precioMinimo+"&precioMaximo="+precioMaximo+"&dormitorios="+dormitorios;
	
	$("table tbody").empty();		
	$.getJSON(url, function (result) {
	  if(result && result.propiedadList.length > 0){
		for (var i = 0; i < result.propiedadList.length; i++){
	    	tipo = result.propiedadList[i].tipoPropiedad;
	    	operacion = result.propiedadList[i].operacionPropiedad;
	    	precioMinimo = parseInt(result.propiedadList[i].precioAlquiler) || "";
	    	precioMaximo = parseInt(result.propiedadList[i].precioVenta) || "";
	    	dormitorios = result.propiedadList[i].dormitorios || "";
			var row = "<tr><td>"+ tipo +"</td>";
			row += "<td>"+ operacion + "</td>";
			row += "<td>"+ precioMinimo + "</td>";
			row += "<td>"+ precioMaximo + "</td>";
			row += "<td>"+ dormitorios + "</td>";
			var urlPath = getContext() + "/admin/propiedad/"+result.propiedadList[i].idPropiedad;
			row += "<td><a href='"+urlPath+"'>Ver</a></td></tr>";
			$("table tbody").append(row);
		 }		
	  }else{
		  alert("No se han econtrado resultados.!");
	  }
      
	});			
};

/**
* Get list of propiedades
* 
* 
* @param tipo TipodeInmueble
* @param operacion TipodeOperacion
* @param precioMinimo 
* @param precioMaximo
* @param dorms dormitorios
*/
var get_propiedades_in_home = function(){
	var tipo=$("#tipoPropiedad").val();
	var operacion=$("#operacionPropiedad").val();
	var precioMinimo=parseInt($("#precioMinimo").val()) || 0;
	var precioMaximo=parseInt($("#precioMaximo").val()) || 0;
	var dormitorios=parseInt($("#dormitorio").val()) || 0;
	
	var url = getContext() + "/search?";
	url += "tipoPropiedad="+tipo+"&operacionPropiedad="+operacion;
	url += "&precioMinimo="+precioMinimo+"&precioMaximo="+precioMaximo+"&dormitorios="+dormitorios;
	
	$("table tbody").empty();		
	$.getJSON(url, function (result) {
	  if(result && result.propiedadList.length > 0){
		for (var i = 0; i < result.propiedadList.length; i++){
			var idPropiedad = result.propiedadList[i].idPropiedad
	    	var tipo = result.propiedadList[i].tipoPropiedad;
	    	var operacion = result.propiedadList[i].operacionPropiedad;
	    	var precio = parseInt(result.propiedadList[i].precioAlquiler) || parseInt(result.propiedadList[i].precioVenta);
	    	var dormitorios = result.propiedadList[i].dormitorios || "";
	    	var domicilio = result.propiedadList[i].domicilio || "";			
			var urlPath = getContext() + "/propiedad/"+result.propiedadList[i].idPropiedad;
			var row = "<tr><td><a href='"+urlPath+"'>"+ tipo + "</a></td>";			
			row += "<td>"+ operacion + "</td>";
			row += "<td>"+ precio + "</td>";
			row += "<td>"+ dormitorios + "</td>";
			row += "<td>"+ domicilio + "</td>";			
			row += "<td><img alt='Image de Propiedad' src='' id='propiedad"+ idPropiedad +"' height='80px' width='100px'></td></tr>";
			$("table tbody").append(row);
		 }
		 setImages(result.imageMap);
	  }else{
		  alert("No se han econtrado resultados.!");
	  }     
	});			
};

/**
 * Get list of persona (Propietarios, Inquilino, Garantes)
 * 
 * @param fn field Name
 * @param fv field Value
 * @param page page Number
 */
var get_persona = function (fn, fv, page){	
	var url = document.URL + "/search?fieldName="+fn+"&fieldValue="+fv+"&page="+page;
	$("table tbody").empty();	
	$.getJSON(url, function (result){	  
	  for (var i = 0; i < result.personList.length; i++){
		var row = "<tr><td>"+result.personList[i].nombreCompleto +"</td>";
		row += "<td>"+result.personList[i].tipoDni + " " +result.personList[i].dni+"</td>";
		row += "<td>"+result.personList[i].tipoTelefono+" "+result.personList[i].telefono+"</td>";
        var viewUrl = document.URL + "/" +result.personList[i].dni;		
		row += "<td><a href='"+ viewUrl +"'>Ver</a></td>";				
		$("table tbody").append(row);
	  }		
	});
};
/**
 * Set legend in div element by menu item selected (Propiedad, Inquilino, etc..)
 * 
 */
var setSearchLegend = function(){
	var url = document.URL;
	url = url.substring(url.lastIndexOf('/')+1, url.length);
	url = url.substring(0, 1).toUpperCase() + url.substring(1, url.length);
	$("#search fieldset legend").text("Buscar "+url);
};