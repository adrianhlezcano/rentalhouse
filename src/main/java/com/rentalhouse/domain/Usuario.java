package com.rentalhouse.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

@Entity @Table(name="USUARIO") @PrimaryKeyJoinColumn(name="ID_PERSONA")
public class Usuario extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String preguntaSeguridad;
	private String respuestaSeguridad;
	private Date fechaDeUltimoIngreso;
	private Date fechaDeCreacion;
	
	public Usuario(){
		super();
	}
	
	@Column(name="USERNAME", nullable=false, length=25, unique=true)
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username.toLowerCase(); }
	
	@Column(name="PASSWORD", nullable=false, length=25)
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password;}
	
	@Column(name="PREGUNTA_SEGURIDAD", nullable=false, length=35)
	public String getPreguntaSeguridad() { return preguntaSeguridad;}
	public void setPreguntaSeguridad(String preguntaSeguridad) { this.preguntaSeguridad = preguntaSeguridad;}
	
	@Column(name="RESPUESTA_SEGURIDAD", nullable=false, length=15)
	public String getRespuestaSeguridad() {	return respuestaSeguridad; }
	public void setRespuestaSeguridad(String respuestaSeguridad) { this.respuestaSeguridad = respuestaSeguridad;}
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="FECHA_ULTIMO_ACCESO")
	public Date getFechaUltimoAcceso() { return fechaDeUltimoIngreso; }
	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) { this.fechaDeUltimoIngreso = fechaUltimoAcceso;}
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="FECHA_DE_CREACION", updatable=false)
	public Date getFechaDeCreacion() { return fechaDeCreacion; }
	public void setFechaDeCreacion(Date fechaDeCreacion) { this.fechaDeCreacion = fechaDeCreacion; }
	
	@Override public boolean equals(Object o){
		if(o!=null && o instanceof Usuario){
			Usuario u = (Usuario) o;
			return u.getUsername().equalsIgnoreCase(getUsername());
		}
		return false;
	}	
	@Override public int hashCode(){ return 17 * 31 + getUsername().hashCode(); }
	@Override public String toString(){ 
		StringBuilder sb = new StringBuilder();
		return sb
		.append(" username: "+getUsername())
		.append(" password: "+getPassword())
		.append(" preguntaSeguridad: "+getPreguntaSeguridad())
		.append(" respuestaSeguridad: "+getRespuestaSeguridad())
		.toString();
	}
}

