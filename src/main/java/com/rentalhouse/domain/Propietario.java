package com.rentalhouse.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PROPIETARIO", uniqueConstraints={@UniqueConstraint(columnNames="legajo")})
@PrimaryKeyJoinColumn(name="ID_PERSONA")
public class Propietario extends Persona implements Serializable{	
	private static final long serialVersionUID = 1L;
	private String legajo;
	private Set<Propiedad> propiedades;
	private Domicilio domicilio;	
	
	public Propietario(){
		super();
	}
	
	@Column(name="LEGAJO", length=10)
	public String getLegajo() { return legajo; }
	public void setLegajo(String legajo) { this.legajo = legajo; }
	
	@OneToMany(mappedBy="propietario", targetEntity=Propiedad.class, fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	public Set<Propiedad> getPropiedades() { return propiedades; }
	public void setPropiedades(Set<Propiedad> propiedades) { this.propiedades = propiedades; }	
	public void addPropiedad(Propiedad propiedad){		
		propiedad.setPropietario(this);
		getPropiedades().add(propiedad);
	}
	
	@Embedded
	public Domicilio getDomicilio() { return domicilio; }
	public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio; }
	
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.append(super.toString())
		.append(" legajo: "+getLegajo())
		.append(" "+getDomicilio()).toString();
	}
}
