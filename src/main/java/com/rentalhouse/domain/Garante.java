package com.rentalhouse.domain;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name="GARANTE")
@PrimaryKeyJoinColumn(name="ID_PERSONA")
public class Garante extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;	
	private TipoGarantia tipoGarantia;
	private String detalleGarantia;
	private Integer valorGarantia;	
	private Domicilio domicilio;	
	private Collection<Inquilino> inquilinos;
	
	public Garante(){
		super();
	}
	
	@Enumerated(EnumType.STRING) @Column(name="TIPO_GARANTIA", length=10, nullable=false)
	public TipoGarantia getTipoGarantia(){ return tipoGarantia ; }
	public void setTipoGarantia(TipoGarantia tipoGarantia){ this.tipoGarantia = tipoGarantia; }
	
	@Column(name="DETALLE_GARANTIA", length=50)
	public String getDetalleGarantia(){ return detalleGarantia; }
	public void setDetalleGarantia(String detalleGarantia){ this.detalleGarantia = detalleGarantia; }
	
	@Column(name="VALOR_GARANTIA", nullable=false)
	public Integer getValorGarantia() { return valorGarantia; }
	public void setValorGarantia(Integer valorGarantia) { this.valorGarantia = valorGarantia; }
		
	@Embedded 
	public Domicilio getDomicilio() { return domicilio; }
	public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio; }
	
	@ManyToMany(mappedBy="garantes", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)	 
	public Collection<Inquilino> getInquilino(){ return inquilinos; }
	public void setInquilino(Collection<Inquilino> inquilinos) { this.inquilinos = inquilinos; }
	
	
	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();
		return sb.append(super.toString())
		.append(" tipoGarantia: "+getTipoGarantia())
		.append(" detalleGarantia: "+getDetalleGarantia())
		.append(" valorGarantia: "+String.valueOf(getValorGarantia()))
		.append(" "+getDomicilio()).toString();		
	}
}
