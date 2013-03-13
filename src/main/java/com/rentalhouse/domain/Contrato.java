package com.rentalhouse.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CONTRATO")
public class Contrato implements Serializable {	
	private static final long serialVersionUID = 1L;
	private Integer idContrato;
	private EstadoContrato estadoContrato;
	private Date fechaCreacion;
	private Float comision;
	private Float honorarios;
	private Float depositoGarantia;
	private Propiedad propiedad;
	private Inquilino inquilino;
	private Set<Cuota> cuotas;	
	private Boolean administrado;
	private String observacion;	
	
	public Contrato() {	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID_CONTRATO")
	public Integer getIdContrato(){ return idContrato; }
	public void setIdContrato(Integer idContrato) { this.idContrato = idContrato; }
	
	@Enumerated(value=EnumType.STRING) @Column(name="ESTADO_CONTRATO", length=10, nullable=false)
	public EstadoContrato getEstadoContrato(){  return estadoContrato; }
	public void setEstadoContrato(EstadoContrato estadoContrato) { this.estadoContrato = estadoContrato; }
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="FECHA", nullable=false)
	public Date getFechaCreacion() { return fechaCreacion; }
	public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }	

	@Column(name="COMISION", scale=8, precision=2)
	public Float getComision() { return comision; }
	public void setComision(Float comision) { this.comision = comision; }
	
	@Column(name="HONORARIOS", scale=8, precision=2) 
	public Float getHonorarios() { return honorarios; }
	public void setHonorarios(Float honorarios) { this.honorarios = honorarios; }
	
	@Column(name="DEPOSITO_GARANTIA", scale=8, precision=2)
	public Float getDepositoGarantia() { return depositoGarantia; }
	public void setDepositoGarantia(Float depositoGarantia) { this.depositoGarantia = depositoGarantia; }
	
	@ManyToOne(targetEntity=Propiedad.class, fetch=FetchType.LAZY, optional=false) 
	@JoinColumn(name="ID_PROPIEDAD")
	public Propiedad getPropiedad() { return propiedad; }
	public void setPropiedad(Propiedad propiedad) { this.propiedad = propiedad; }
	
	@ManyToOne(targetEntity=Inquilino.class, fetch=FetchType.LAZY, optional=false) 
	@JoinColumn(name="ID_PERSONA")
	public Inquilino getInquilino() { return inquilino; }
	public void setInquilino(Inquilino inquilino) { this.inquilino = inquilino; }
	
	@OneToMany(mappedBy="contrato", targetEntity=Cuota.class, fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})	
	public Set<Cuota> getCuotas() { return cuotas; }
	public void setCuotas(Set<Cuota> cuotas) { this.cuotas = cuotas; }	
		
	@Column(name="ADMINISTRADO")
	public boolean isAdministrado() { return administrado; }
	public void setAdministrado(Boolean administrado) { this.administrado = administrado; }
	
	@Column(name="OBSERVACION", length=100)
	public String getObservacion() { return observacion; }
	public void setObservacion(String observacion) { this.observacion = observacion; }
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Contrato){
			Contrato c = (Contrato) obj;
			return c.getIdContrato() == this.getIdContrato() &&
				c.getPropiedad().getIdPropiedad() == this.getPropiedad().getIdPropiedad() &&
				c.getInquilino().getIdPersona() == this.getInquilino().getIdPersona() &&
				c.getFechaCreacion().equals(this.getFechaCreacion());
		}
		return false;
	}	
	@Override
	public int hashCode() {
		int value = 17;
		value = 31 * value + getIdContrato();
		value = 31 * value + getPropiedad().getIdPropiedad();
		value = 31 * value + getInquilino().getIdPersona();
		value = 31 * value + getFechaCreacion().hashCode();
		return (int) getIdContrato();
	}	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		return sb.append("id: "+getIdContrato())
		.append(" administrado: "+ (isAdministrado()? "yes":"no"))
		.append(" estado: "+getEstadoContrato().getValue())
		.append(" honorarios: "+getHonorarios())
		.append(" comision: "+getComision())
		.append(" depositoGarantia: "+getDepositoGarantia())
		.append(" inquilino: "+getInquilino())
		.append(" propiedad: "+getPropiedad())
		.toString();
	}	
	public void createCuotas(int numeroCuotas, int importeCuota){
		this.cuotas = new HashSet<Cuota>(numeroCuotas);
		Calendar calendar = Calendar.getInstance();
		Cuota c = null;
		for (int i = 0; i < numeroCuotas; i++) {
			c = new Cuota();
			c.setContrato(this);
			c.setNumeroCuota(i + 1);
			if (i == 0){
				c.setFechaVencimiento(calendar.getTime());
				c.setImporteAdeudado(importeCuota);
				c.setImportePagado(importeCuota);
				c.setFechaPago(calendar.getTime());
				c.setPagado(Boolean.TRUE);
			} else {
				int monthIncrement = i;
				if (calendar.get(Calendar.DATE) > 20) monthIncrement ++;
				calendar.add(Calendar.MONTH, monthIncrement);
				calendar.set(Calendar.DATE, 10);				
				c.setFechaVencimiento(calendar.getTime());
				c.setImporteAdeudado(i > 11? new Double(importeCuota * 1.2).intValue() : importeCuota);			
				c.setPagado(Boolean.FALSE);
			}
			calendar = Calendar.getInstance();
			cuotas.add(c);			
		}		
	}	
}
