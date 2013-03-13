package com.rentalhouse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="LOCALIDAD")
public class Localidad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idLocalidad;
	private String nombre;
	private Provincia provincia;	
	
	public Localidad(){}	
	
	@Id	@Column(name="ID_LOCALIDAD")
	public Integer getIdLocalidad() { return idLocalidad; }
	protected void setIdLocalidad(Integer idLocalidad) { this.idLocalidad = idLocalidad; }
	
	@Column(name="NOMBRE", length=35, nullable=false)
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
		
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Provincia.class)
	@JoinColumn(name="ID_PROVINCIA", nullable=false)
	public Provincia getProvincia() { return provincia; }
	public void setProvincia(Provincia provincia) { this.provincia = provincia; }
	
	@Override public boolean equals(Object o){
		if (o!=null && o instanceof Localidad){
			Localidad l = (Localidad) o;
			return l.getIdLocalidad() == getIdLocalidad() && 
				l.getProvincia().getIdProvincia() == getProvincia().getIdProvincia();			
		}
		return false;
	}
	@Override public int hashCode(){ return 31 * 17 + getIdLocalidad(); }
	@Override public String toString(){
		return String.format("Localidad %d: %s - %s", getIdLocalidad(),
				getNombre(), getProvincia());
	}
}
