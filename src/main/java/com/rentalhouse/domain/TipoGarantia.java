package com.rentalhouse.domain;

public enum TipoGarantia {
    PROPIEDAD("Propiedad"), SUELDO("Sueldo"), OTROS("Otros");
	
	private TipoGarantia(String value){
		this.value = value;
	}
	private String value;
	public String getValue(){
		return value;
	}
}
