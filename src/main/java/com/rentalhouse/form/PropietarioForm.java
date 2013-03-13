package com.rentalhouse.form;

import java.util.List;

import com.rentalhouse.domain.Domicilio;
import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.domain.Provincia;

public class PropietarioForm extends PersonaForm{
	private String legajo;
	private String barrio;
	private String calle;
	private String numero;
	private String piso;
	private String depto;
	private String codigoPostal;
	private Integer idLocalidad;
	private Integer idProvincia;
	private List<Provincia> provinciaList;
	private List<Localidad> localidadList;
	
	public PropietarioForm(){
		super();
	}
	public PropietarioForm(Propietario propietario){
		super(propietario);
		setLegajo(propietario.getLegajo());
		setBarrio(propietario.getDomicilio().getBarrio());
		setCalle(propietario.getDomicilio().getCalle());
		setNumero(propietario.getDomicilio().getNumero());
		setPiso(propietario.getDomicilio().getPiso());
		setDepto(propietario.getDomicilio().getDepto());
		setCodigoPostal(propietario.getDomicilio().getCodigoPostal());	
		setIdLocalidad(propietario.getDomicilio().getLocalidad().getIdLocalidad());
		setIdProvincia(propietario.getDomicilio().getLocalidad().getProvincia().getIdProvincia());				
	}
	public Propietario toPropietario(Propietario propietario, Localidad localidad){
		propietario = (Propietario) super.toPersona(propietario);
		propietario.setLegajo(getLegajo());
		Domicilio domicilio = new Domicilio();
		domicilio.setCalle(getCalle());
		domicilio.setNumero(getNumero());
		domicilio.setPiso(getPiso());
		domicilio.setDepto(getDepto());
		domicilio.setCodigoPostal(getCodigoPostal());
		domicilio.setBarrio(getBarrio());
		domicilio.setLocalidad(localidad);
		propietario.setDomicilio(domicilio);
		return propietario;
	}
	
	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepto() {
		return depto;
	}
	public void setDepto(String depto) {
		this.depto = depto;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public Integer getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public List<Provincia> getProvinciaList() {
		return provinciaList;
	}	
	public void setProvinciaList(List<Provincia> provinciaList) {
		this.provinciaList = provinciaList;
	}
	public void setLocalidadList(List<Localidad> localidadList) {
		this.localidadList = localidadList;
	}
	public List<Localidad> getLocalidadList() {
		return localidadList;
	}
}
