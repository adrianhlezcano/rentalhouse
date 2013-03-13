package com.rentalhouse.domain;

public enum EstadoContrato {
	VIGENTE("Vigente"), CANCELADO("Cancelado"), FINALIZADO("Finalizado"), RENOVADO(
			"Renovado");
	
	private String value;
	private EstadoContrato(String value){
		this.value=value;
	}	
	public String getValue(){
		return this.value;
	}		
}
