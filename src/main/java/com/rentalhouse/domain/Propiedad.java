package com.rentalhouse.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity 
@Table(name="PROPIEDAD")
public class Propiedad implements Serializable {	
	private static final long serialVersionUID = 1L;
	private Integer idPropiedad;
	private String referencia;
	private String dormitorios;
	private String detalle;
	private Integer precioVenta;
	private Integer precioAlquiler;
	private TipoMoneda tipoMoneda;
	private Domicilio domicilio;
	private TipoPropiedad tipoPropiedad;
	private OperacionPropiedad operacionPropiedad;
	private boolean estrenar;
	private boolean aptoCredito;
	private boolean aptoEscritura;	
	private boolean publicar;
	private Persona propietario;
	private Integer version;
	private Integer superficie;
	
	public Propiedad(){}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID_PROPIEDAD")
	public final Integer getIdPropiedad() { return idPropiedad; }
	public void setIdPropiedad(Integer idPropiedad) { this.idPropiedad = idPropiedad; }
	
	@Column(name="DORMITORIOS", length=5, nullable=true) 
	public String getDormitorios() { return dormitorios; }
	public void setDormitorios(String dormitorios) { this.dormitorios = dormitorios; }
		
	@Column(name="DETALLE", length=150, nullable=true)
	public String getDetalle() { return detalle; }
	public void setDetalle(String detalle) { this.detalle = detalle; }
	
	@Enumerated(EnumType.STRING) @Column(name="TIPO_MONEDA", length=7)
	public TipoMoneda getTipoMoneda() { return tipoMoneda; }
	public void setTipoMoneda(TipoMoneda tipoMoneda) { this.tipoMoneda = tipoMoneda; }
	
	@Column(name="PRECIO_VENTA", scale=10, nullable=true) 
	public Integer getPrecioVenta() { return precioVenta; }
	public void setPrecioVenta(Integer precioVenta) { this.precioVenta = precioVenta; }
	
	@Column(name="PRECIO_ALQUILER", scale=10, nullable=true)
	public Integer getPrecioAlquiler() { return precioAlquiler; }
	public void setPrecioAlquiler(Integer precioAlquiler) { this.precioAlquiler = precioAlquiler; }
	
	@Embedded
	public Domicilio getDomicilio() { return domicilio; }
	public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio; }
	
	@Enumerated(value=EnumType.STRING) @Column(name="TIPO_PROPIEDAD", length=25)
	public TipoPropiedad getTipoPropiedad() { return tipoPropiedad; }
	public void setTipoPropiedad(TipoPropiedad tipoPropiedad) { this.tipoPropiedad = tipoPropiedad; }
	
	@Enumerated(value=EnumType.STRING) @Column(name="OPERACION_PROPIEDAD")
	public OperacionPropiedad getOperacionPropiedad() { return operacionPropiedad; }
	public void setOperacionPropiedad(OperacionPropiedad operacionPropiedad) {
		this.operacionPropiedad = operacionPropiedad;
	}
	
	@ManyToOne(targetEntity=Persona.class, fetch=FetchType.EAGER, optional=false) 
	@JoinColumn(name="ID_PROPIETARIO")
	public Persona getPropietario() { return propietario; }
	public void setPropietario(Persona propietario) { this.propietario = propietario; }
	
	@Column(name="REFERENCIA", length=10)
	public String getReferencia() {  return referencia; }	
	public void setReferencia(String referencia) { this.referencia = referencia; }
	
	@Version @Column(name="VERSION")
	public Integer getVersion() { return version; }
	public void setVersion(Integer version) { this.version = version; }	
	
	@Column(name="ESTRENAR")
	public boolean isEstrenar() { return estrenar; }
	public void setEstrenar(boolean estrenar) { this.estrenar = estrenar; }
	
	@Column(name="APT0_CREDITO")
	public boolean isAptoCredito() { return aptoCredito; }
	public void setAptoCredito(boolean aptoCredito) { this.aptoCredito = aptoCredito; }
	
	@Column(name="APTO_ESCRITURA")
	public boolean isAptoEscritura() { return aptoEscritura; }
	public void setAptoEscritura(boolean aptoEscritura) { this.aptoEscritura = aptoEscritura; }
	
	@Column(name="PUBLICAR")
	public boolean isPublicar() { return publicar; }
	public void setPublicar(boolean publicar) { this.publicar = publicar; }
	
	@Column(name="SUPERFICIE", nullable=true)
	public Integer getSuperficie() { return superficie; }
	public void setSuperficie(Integer superficie) { this.superficie = superficie; }	
		
	@Override
	public boolean equals(Object obj) {		
		if(obj!=null && obj instanceof Propiedad){
			Propiedad p=(Propiedad) obj;
			return p.getIdPropiedad() == getIdPropiedad()
				&& p.getPropietario().getIdPersona() == getPropietario().getIdPersona();		      
		}
		return false;
	}	
	@Override
	public int hashCode() {
		return 31 * 17 + Long.valueOf(getIdPropiedad()).intValue();		
	}	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb = sb.append(getTipoPropiedad().getValue()).append(": "+getTipoMoneda().getValue());
		if (getPrecioAlquiler()!=null && getPrecioAlquiler() > 0) sb.append(" "+getPrecioAlquiler());
		if (getPrecioVenta()!=null && getPrecioVenta() > 0) sb.append(" "+getPrecioVenta());
		sb = sb.append(" ("+getDomicilio()+")");
		return sb.toString().trim();				
	}
}
