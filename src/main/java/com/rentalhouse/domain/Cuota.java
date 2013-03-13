package com.rentalhouse.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="CUOTA")
public class Cuota implements Serializable, Comparable<Cuota> {
	private static final long serialVersionUID = 1L;
	private Integer idCuota;
	private int numeroCuota;
	private Boolean pagado;
	private Date fechaVencimiento;
	private Date fechaPago;
	private Integer importeAdeudado;
	private Integer importePagado;
	private Contrato contrato;
	
	public Cuota() {}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID_CUOTA")
	public Integer getIdCuota() { return idCuota; }
	public void setIdCuota(Integer idCuota) { this.idCuota = idCuota; }
	
	@Column(name="NUMERO_CUOTA")
	public int getNumeroCuota() { return numeroCuota; }
	public void setNumeroCuota(int numeroCuota) { this.numeroCuota = numeroCuota; }
	
	@Column(name="PAGADO")
	public boolean isPagado() { return pagado; }
	public void setPagado(Boolean pagado) { this.pagado = pagado; }
	
	@Temporal(TemporalType.DATE) @Column(name="FECHA_VENCIMIENTO")
	public Date getFechaVencimiento() { return fechaVencimiento; }
	public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
	
	@Temporal(TemporalType.DATE) @Column(name="FECHA_PAGO") 
	public Date getFechaPago() { return fechaPago; }
	public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
	
	@Column(name="IMPORTE_ADEUDADO", precision=2, scale=8)
	public Integer getImporteAdeudado() { return importeAdeudado; }
	public void setImporteAdeudado(Integer importeAdeudado) { this.importeAdeudado = importeAdeudado; }
	
	@Column(name="IMPORTE_PAGADO", precision=2, scale=8)
	public Integer getImportePagado() { return importePagado; }
	public void setImportePagado(Integer importePagado) { this.importePagado = importePagado; }
	
	@ManyToOne(targetEntity=Contrato.class, fetch=FetchType.EAGER) 
	@JoinColumn(name="ID_CONTRATO")
	public Contrato getContrato() { return contrato; }
	public void setContrato(Contrato contrato) { this.contrato = contrato; }
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj!=null && obj instanceof Cuota){
			Cuota c=(Cuota) obj;
			return c.getImporteAdeudado() ==this.getImporteAdeudado() && 
					c.getNumeroCuota()==this.getNumeroCuota() &&
					c.isPagado() == this.isPagado() &&
					c.getFechaVencimiento().equals(this.getFechaVencimiento());
		}
		return false;
	}	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int value=17;
		value = 31 * value + getImporteAdeudado().intValue();
		value = 31 * value + getNumeroCuota();
		value = 31 * value + (isPagado()? 1 : -1);
		value = 31 * value + getFechaVencimiento().hashCode();
		return value;
	}	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		return sb.append("id: "+getIdCuota())
		.append("cuota N. " +getNumeroCuota())
		.append(" Venc: ")
		.append(getFechaVencimiento())
		.append(" $ ")
		.append(getImporteAdeudado())
		.append(isPagado() ? " Pagado":" No Pagado") 
		.append(" Monto Pagado: "+ pagado)
		.toString();
	}

	public int compareTo(Cuota o) {		
		if (this.getNumeroCuota() - o.getNumeroCuota() == 0) return 0;
		return (this.getNumeroCuota() > o.getNumeroCuota()? 1 : -1);
	}
	@Transient
	public void pay(Integer importePagado){
		this.fechaPago = new java.util.Date();
		this.pagado = true;
		this.importePagado = importePagado;
	}

}
