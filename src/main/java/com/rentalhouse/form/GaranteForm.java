package com.rentalhouse.form;

import java.util.Arrays;
import java.util.List;

import com.rentalhouse.domain.Domicilio;
import com.rentalhouse.domain.Garante;
import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Provincia;
import com.rentalhouse.domain.TipoGarantia;

public class GaranteForm extends PersonaForm{
	private String detalleGarantia;
	private String valorGarantia;
	private String tipoGarantia;
	private List<TipoGarantia> tipoGarantiaList = Arrays.asList(TipoGarantia.values());
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
	
	public GaranteForm(){
		super();
	}
	public GaranteForm(Garante garante){
		super(garante);
		setDetalleGarantia(garante.getDetalleGarantia());
		setTipoGarantia(garante.getTipoGarantia().getValue());
		setValorGarantia(String.valueOf(garante.getValorGarantia()));
		setBarrio(garante.getDomicilio().getBarrio());
		setCalle(garante.getDomicilio().getCalle());
		setNumero(garante.getDomicilio().getNumero());
		setPiso(garante.getDomicilio().getPiso());
		setDepto(garante.getDomicilio().getDepto());
		setCodigoPostal(garante.getDomicilio().getCodigoPostal());	
		setIdLocalidad(garante.getDomicilio().getLocalidad().getIdLocalidad());
		setIdProvincia(garante.getDomicilio().getLocalidad().getProvincia().getIdProvincia());				
	}
	public Garante toGarante(Garante garante, Localidad localidad){
		garante = (Garante) super.toPersona(garante);
		Domicilio domicilio = new Domicilio();
		domicilio.setCalle(getCalle());
		domicilio.setNumero(getNumero());
		domicilio.setPiso(getPiso());
		domicilio.setDepto(getDepto());
		domicilio.setCodigoPostal(getCodigoPostal());
		domicilio.setBarrio(getBarrio());
		domicilio.setLocalidad(localidad);
		garante.setDomicilio(domicilio);
		garante.setValorGarantia(Integer.parseInt(getValorGarantia()));
		garante.setDetalleGarantia(getDetalleGarantia());
		garante.setTipoGarantia(TipoGarantia.valueOf(tipoGarantia.toUpperCase()));
		return garante;
	}
	public String getDetalleGarantia() {
		return detalleGarantia;
	}
	public void setDetalleGarantia(String detalleGarantia) {
		this.detalleGarantia = detalleGarantia;
	}
	public String getValorGarantia() {
		return valorGarantia;
	}
	public void setValorGarantia(String valorGarantia) {
		this.valorGarantia = valorGarantia;
	}
	public String getTipoGarantia() {
		return tipoGarantia;
	}
	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}
	public List<TipoGarantia> getTipoGarantiaList() {
		return tipoGarantiaList;
	}
	public void setTipoGarantiaList(List<TipoGarantia> tipoGarantiaList) {
		this.tipoGarantiaList = tipoGarantiaList;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
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
	public List<Localidad> getLocalidadList() {
		return localidadList;
	}
	public void setLocalidadList(List<Localidad> localidadList) {
		this.localidadList = localidadList;
	}
	
	
}
