package com.rentalhouse.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity @Table(name="PERSONA") @Inheritance(strategy=InheritanceType.JOINED)
public class  Persona implements Serializable {	
	private static final long serialVersionUID = 1L;
	private Integer idPersona;
	private String nombre;
	private String apellido;
	private TipoDni tipoDni;
	private int dni;
	private String cuit;
	private Date fechaNacimiento;	
	private String telefono;
	private TipoTelefono tipoTelefono;
	private String email;
	private Integer version;
		
	public Persona() {}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID_PERSONA")
	public final Integer getIdPersona() { return idPersona; }
	public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
	
	@Column(name="NOMBRE", length=50, nullable=false)
	public String getNombre() {	return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre;}
	
	@Column(name="APELLIDO", length=30, nullable=false)
	public String getApellido() { return apellido;}
	public void setApellido(String apellido) { this.apellido = apellido; }
	
	@Temporal(TemporalType.DATE) @Column(name="FECHA_NACIMIENTO")
	public Date getFechaNacimiento() {return fechaNacimiento; }
	public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
	
	@Enumerated(EnumType.STRING) @Column(name="TIPO_DNI", length=10, nullable=false)
	public TipoDni getTipoDni() { return tipoDni; }
	public void setTipoDni(TipoDni tipoDni) { this.tipoDni = tipoDni; }
	
	@Column(name="DNI", nullable=false, unique=true) 	
	public int getDni() { return dni; }
	public void setDni(int dni) { this.dni = dni; }
	
	@Column(name="CUIT", length=15, unique=true) 
	public String getCuit() { return cuit; }
	public void setCuit(String cuit) { this.cuit = cuit; }
	
	@Column(name="TELEFONO", length=15)
	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	
	@Enumerated(EnumType.STRING) @Column(name="TIPO_TELEFONO", length=15)
	public TipoTelefono getTipoTelefono() { return tipoTelefono; }
	public void setTipoTelefono(TipoTelefono tipoTelefono) { this.tipoTelefono = tipoTelefono; }
	
	@Column(name="EMAIL", length=60)
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	@Version 
	public Integer getVersion() { return version; }
	public void setVersion(Integer version) { this.version = version; }
	
	@Transient
	public final String getNombreCompleto(){
		return apellido.trim() + ", " + nombre.trim();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Persona){
			Persona p=(Persona) obj;
			return (this.getIdPersona()==p.getIdPersona() 
					&& this.getDni()==p.getDni() 
					&& this.getTipoDni() == p.getTipoDni());
		}
		return false;		
	}	
	@Override
	public int hashCode() {
		int value=17;
		value = 31 * value + getDni();
		value = 31 * value + getIdPersona();
		value = 31 * value + getTipoDni().hashCode();		
		return value;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.append("ID: "+getIdPersona())
		.append(" Nombre Completo: ".concat(getApellido()).concat("; ").concat(getNombre()))
		.append(" ".concat(getTipoDni().toString()).concat(": ").concat(String.valueOf(getDni())))
		.append(" CUIT: "+getCuit())
		.append(" mail: "+getEmail())		
		.append(" Telefono ".concat(getTipoTelefono().toString()).concat(": ").concat(getTelefono())).
		toString();		
	}
}
