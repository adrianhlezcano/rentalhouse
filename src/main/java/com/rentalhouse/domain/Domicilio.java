package com.rentalhouse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Domicilio implements Serializable{
	private static final long serialVersionUID = 1L;	
	private String calle;
	private String numero;
	private String piso;
	private String depto;
	private String codigoPostal;
	private String barrio;
	private Localidad localidad;
		
	public Domicilio() {}
	public Domicilio(String calle, String numero, String piso, String depto,
			String codigoPostal, String barrio, Localidad localidad) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.depto = depto;
		this.codigoPostal = codigoPostal;
		this.barrio = barrio;
		this.localidad = localidad;
	}
	
	@Column(name="CALLE", length=30, nullable=false)
	public String getCalle() { return calle; }	
	public void setCalle(String calle) { this.calle = calle; }
	
	@Column(name="NUMERO", length=5, nullable=false) 
	public String getNumero() { return numero; }	
	public void setNumero(String numero) { this.numero = numero; }
	
	@Column(name="PISO", length=2, nullable=true)
	public String getPiso() { return piso; }
	public void setPiso(String piso) { this.piso = piso.toUpperCase(); }
	
	@Column(name="DEPTO", length=2, nullable=true)
	public String getDepto() { return depto; }
	public void setDepto(String depto) { this.depto = depto; }
	
	@Column(name="BARRIO", length=25, nullable=true)
	public String getBarrio() { return barrio; }
	public void setBarrio(String barrio) { this.barrio = barrio; }
	
	@Column(name="CODIGO_POSTAL", length=8, nullable=false)
	public String getCodigoPostal() { return codigoPostal; }	
	public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
	
	@ManyToOne(targetEntity=Localidad.class, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="ID_LOCALIDAD")
	public Localidad getLocalidad() { return localidad; }
	public void setLocalidad(Localidad localidad) { this.localidad = localidad; }
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb = sb.append(getCalle()).append(" "+getNumero());
		if (getPiso() != null && getPiso().length() > 0) sb.append(" "+getPiso());
		if (getDepto() != null && getDepto().length() > 0) sb.append(" "+getDepto());
		return sb.toString().trim();				
	}
	
}
