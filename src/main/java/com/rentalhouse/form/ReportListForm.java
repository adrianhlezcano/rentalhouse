package com.rentalhouse.form;

public enum ReportListForm {
	LISTADO_PROPIEDADES("Listado de propiedades disponibles."),
	LISTADO_CONTRATOS("Listado de contratos vigentes."),
	PAGOS_REALIZADOS("Grafico de Pagos Realidos a la fecha."),
	PAGOS_FALTANTES("Grafico de Pagos Pendientes a la fecha.");
	
	private String nameValue;
	
	private ReportListForm(String nameValue) {
		this.nameValue = nameValue;
	}
	
	public String getNameValue() {
		return nameValue;
	}
}
