package com.rentalhouse.domain;

public enum TipoPropiedad{
	CASA("Casa"), 
	DEPARTAMENTO("Departamento"), 
	OFICINA_LOCAL("Oficina | Local"),
	TERRENO("Terreno | Lotes"), 
	CASA_QUINTA("Casa fin de semana"), 
	CAMPO("Campo"),
	COUNTRY("Country | Barrio Cerrado"), 
	DEPOSITOS("Galpones | Depositos"),
	COCHERAS("Cocheras"), 
	OTROS("Otros Inmuebles");
	
	private String value;
	private TipoPropiedad(String value){
		this.value=value;
	}
	public String getValue(){
		return this.value;
	}
	
}	
