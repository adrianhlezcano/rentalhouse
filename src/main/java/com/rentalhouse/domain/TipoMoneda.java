package com.rentalhouse.domain;

public enum TipoMoneda {
	DOLARES("U$S"), PESOS("$");
	
	private String value;
	private TipoMoneda(String value){
		this.value=value;
	}
	public String getValue(){
		return value;
	}
}
