package com.rentalhouse.domain;

public enum TipoTelefono {
	MOVIL("Movil"), CASA("Casa"), TRABAJO("Trabajo");
	
	private TipoTelefono(String value){
		this.value = value;
	}
	private String value;
	public String getValue(){
		return value;
	}
}
