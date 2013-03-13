package com.rentalhouse.domain;

public enum TipoDni{
	DNI("DNI"), LE("LE"), LC("LC"), PASS("PASAPORTE");
	
	private TipoDni(String value){
		this.value = value;
	}
	private String value;
	public String getValue(){
		return value;
	}
}	
