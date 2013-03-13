package com.rentalhouse.form;

import java.util.HashSet;
import java.util.Set;

import com.rentalhouse.domain.Garante;
import com.rentalhouse.domain.Inquilino;

public class InquilinoForm extends PersonaForm {
	private String ingreso;
	private String ocupacion;
	private String lugarTrabajo;
	private Set<Garante> garantes;
	private Integer idPropiedadGarantia;
	private Integer idGarante;
	private String nombreGarante;
	
	public InquilinoForm(){
		super();
		setIngreso("0.00");		
	}
	public InquilinoForm(Inquilino inquilino){
		super(inquilino);
		setIngreso(String.valueOf(inquilino.getIngreso()));
		setOcupacion(inquilino.getOcupacion());
		setLugarTrabajo(inquilino.getLugarTrabajo());
		Set<Garante> garantes = inquilino.getGarantes();
		Garante g =  (Garante) garantes.toArray()[0];
		setIdGarante(g.getIdPersona());
		setNombreGarante(g.getNombreCompleto());
	}	
	public Inquilino toInquilino(Inquilino inquilino, Garante garante){
		inquilino = (Inquilino) super.toPersona(inquilino);
		inquilino.setIngreso(Float.parseFloat(ingreso));
		inquilino.setOcupacion(ocupacion);
		inquilino.setLugarTrabajo(lugarTrabajo);
		inquilino.setGarantes(new HashSet<Garante>());
		inquilino.getGarantes().add(garante);		
		return inquilino;
	}
	public String getIngreso() {
		return ingreso;
	}
	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getLugarTrabajo() {
		return lugarTrabajo;
	}
	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}
	public Set<Garante> getGarantes() {
		return garantes;
	}
	public void setGarantes(Set<Garante> garantes) {
		this.garantes = garantes;
	}
	public Integer getIdPropiedadGarantia() {
		return idPropiedadGarantia;
	}
	public void setIdPropiedadGarantia(Integer idPropiedadGarantia) {
		this.idPropiedadGarantia = idPropiedadGarantia;
	}	
	public Integer getIdGarante() {
		return idGarante;
	}
	public void setIdGarante(Integer idGarante) {
		this.idGarante = idGarante;
	}
	public String getNombreGarante() {
		return nombreGarante;
	}
	public void setNombreGarante(String nombreGarante) {
		this.nombreGarante = nombreGarante;
	}
}
