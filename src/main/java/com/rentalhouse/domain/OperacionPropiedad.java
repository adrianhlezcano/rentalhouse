package com.rentalhouse.domain;

public enum OperacionPropiedad{	
	ALQUILA("Alquila"), VENDE("Vende"), ALQUILA_VENDE("Alquila y/o Vende"), TEMPORAL(
			"Temporal");
	
	private String value;
	private OperacionPropiedad(String value){
		this.value=value;
	}	
	public String getValue(){
		return this.value;
	}		
}	
