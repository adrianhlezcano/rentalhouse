package com.rentalhouse.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity()
@Table(name="INQUILINO")
@PrimaryKeyJoinColumn(name="ID_PERSONA") 
public class Inquilino extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ocupacion;	
	private float ingreso;
	private String lugarTrabajo;	
	private Set<Garante> garantes;
	
	public Inquilino() {
		super();		
	}

	@Column(name="OCUPACION", length=30, nullable=false)
	public String getOcupacion() { return ocupacion; }
	public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }
	
	@Column(name="INGRESO", precision=2, scale=10)
	public float getIngreso() { return ingreso; }
	public void setIngreso(float ingreso) { this.ingreso = ingreso; }
	
	@Column(name="LUGAR_TRABAJO", length=25)
	public String getLugarTrabajo() { return lugarTrabajo; }
	public void setLugarTrabajo(String lugarTrabajo) { this.lugarTrabajo = lugarTrabajo; }
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="GARANTES_POR_INQUILINO", 
		joinColumns=@JoinColumn(name="ID_INQUILINO"), 
		inverseJoinColumns=@JoinColumn(name="ID_GARANTE"))
	public Set<Garante> getGarantes() { return garantes; }
	public void setGarantes(Set<Garante> garantes) { this.garantes = garantes; }

}
