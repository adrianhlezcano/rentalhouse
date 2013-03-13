package com.rentalhouse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROVINCIA")
public class Provincia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProvincia;
	private String nombre;
	
	public Provincia(){}
	
	@Id @Column(name="ID_PROVINCIA")
	public Integer getIdProvincia(){ return idProvincia; }
	protected void setIdProvincia(Integer idProvincia){ this.idProvincia = idProvincia; }
	
	@Column(name="NOMBRE", length=25, nullable=false)
	public String getNombre(){ return nombre; }
	protected void setNombre(String nombre){ this.nombre = nombre; }	
	
	@Override public boolean equals(Object o){
		if (o!=null && o instanceof Provincia){
			Provincia l = (Provincia) o;
			return l.getIdProvincia() == getIdProvincia();			
		}
		return false;
	}
	@Override public int hashCode(){ return 31 * 17 + getIdProvincia(); }
	@Override public String toString(){
		return String.format("Provincia: %d - %s", getIdProvincia(), getNombre());
	}
}
