package com.rentalhouse.form;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import com.rentalhouse.domain.Domicilio;
import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.OperacionPropiedad;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.domain.Provincia;
import com.rentalhouse.domain.TipoMoneda;
import com.rentalhouse.domain.TipoPropiedad;
import com.rentalhouse.utils.AppConstant;

public class PropiedadForm {
	private Integer idPropiedad;
	private String action;
	private String referencia;
	private String barrio;
	private String calle;
	private String numero;
	private String piso;
	private String depto;
	private String codigoPostal; 	
	private String detalle;
	private String dormitorios;
	private String operacionPropiedad;
	private String precioVenta;
	private String precioAlquiler;
	private String tipoMoneda;	
	private String tipoPropiedad;
	public Boolean publicar;
	private Boolean estrenar;
	private Boolean aptoEscritura;
	private Boolean aptoCredito;
	private Integer idLocalidad;
	private Integer idProvincia;
	private Integer idPropietario;
	private String nombrePropietario;
	private String superficie;
	private List<OperacionPropiedad> operaciones = Arrays.asList(OperacionPropiedad.values());	
	private List<TipoMoneda> tiposMonedas = Arrays.asList(TipoMoneda.values());
	private List<TipoPropiedad> tiposPropiedad=Arrays.asList(TipoPropiedad.values());
	private List<Localidad> localidadList;
	private List<Provincia> provinciaList;
	private List<String> dormitorioList=Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","+ 10");
	
	public PropiedadForm() {
		setIdPropietario(0);
		setIdProvincia(4); // 4 = CORRIENTES (by default)
		setPrecioAlquiler("0");
		setPrecioVenta("0");
		setSuperficie("0");
		setTipoMoneda(TipoMoneda.PESOS.toString());
		setPublicar(Boolean.TRUE);
		setEstrenar(Boolean.TRUE);
		setAction(AppConstant.INSERT);
	}
	public PropiedadForm(Propiedad propiedad){	
		setIdPropiedad(propiedad.getIdPropiedad());
		setReferencia(propiedad.getReferencia());
		setBarrio(propiedad.getDomicilio().getBarrio());
		setCalle(propiedad.getDomicilio().getCalle());
		setNumero(propiedad.getDomicilio().getNumero());
		setPiso(propiedad.getDomicilio().getPiso());
		setDepto(propiedad.getDomicilio().getDepto());
		setCodigoPostal(propiedad.getDomicilio().getCodigoPostal());
		setDetalle(propiedad.getDetalle());
		setDormitorios(propiedad.getDormitorios());
		setOperacionPropiedad(propiedad.getOperacionPropiedad().getValue());
		setPrecioVenta(propiedad.getPrecioVenta()==null?"":propiedad.getPrecioVenta().toString());
		setPrecioAlquiler(propiedad.getPrecioAlquiler()==null?"":propiedad.getPrecioAlquiler().toString());
		setTipoMoneda(propiedad.getTipoMoneda().toString());
		setTipoPropiedad(propiedad.getTipoPropiedad().toString());
		setEstrenar(propiedad.isEstrenar());
		setAptoEscritura(propiedad.isAptoEscritura());
		setAptoCredito(propiedad.isAptoCredito());		
		setIdLocalidad(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		setIdProvincia(propiedad.getDomicilio().getLocalidad().getProvincia().getIdProvincia());
		setIdPropietario(propiedad.getPropietario().getIdPersona());
		setNombrePropietario(propiedad.getPropietario().getNombreCompleto());
		setSuperficie(String.valueOf(propiedad.getSuperficie()));
		setPublicar(propiedad.isPublicar());
		setAction(AppConstant.UPDATE);
	}	
	public Propiedad toPropiedad(Propiedad p, Localidad localidad, Propietario propietario){	
		p.setReferencia(referencia);
		p.setIdPropiedad(idPropiedad);
		Domicilio domicilio = new Domicilio(getCalle(), getNumero(), getPiso(),
				getDepto(), getCodigoPostal(), getBarrio(), localidad);		
		p.setDomicilio(domicilio);
		p.setDetalle(getDetalle());
		p.setDormitorios(getDormitorios());
		p.setOperacionPropiedad(OperacionPropiedad.valueOf(getOperacionPropiedad()));
		p.setPrecioVenta(getPrecioVenta().isEmpty()?null:Integer.valueOf(getPrecioVenta()));
		p.setPrecioAlquiler(getPrecioAlquiler().isEmpty()?null:Integer.valueOf(getPrecioAlquiler()));
		p.setTipoMoneda(TipoMoneda.valueOf(getTipoMoneda()));
		p.setTipoPropiedad(TipoPropiedad.valueOf(getTipoPropiedad()));
		p.setEstrenar(getEstrenar());
		p.setAptoEscritura(getAptoEscritura());
		p.setAptoCredito(getAptoCredito());		
		p.setSuperficie(Integer.valueOf(getSuperficie()));
		p.setPropietario(propietario);
		p.setPublicar(getPublicar());
		return p;			
	}
	public Integer getIdPropiedad() {
		return idPropiedad;
	}
	public void setIdPropiedad(Integer idPropiedad) {
		this.idPropiedad = idPropiedad;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepto() {
		return depto;
	}
	public void setDepto(String depto) {
		this.depto = depto;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getDormitorios() {
		return dormitorios;
	}
	public void setDormitorios(String dormitorios) {
		this.dormitorios = dormitorios;
	}
	public String getOperacionPropiedad() {
		return operacionPropiedad;
	}
	public void setOperacionPropiedad(String operacionPropiedad) {
		this.operacionPropiedad = operacionPropiedad;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getPrecioAlquiler() {
		return precioAlquiler;
	}
	public void setPrecioAlquiler(String precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	public String getTipoPropiedad() {
		return tipoPropiedad;
	}
	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}
	public Boolean getPublicar() {
		return publicar;
	}
	public void setPublicar(Boolean publicar) {
		this.publicar = publicar;
	}
	public Boolean getEstrenar() {
		return estrenar;
	}
	public void setEstrenar(Boolean estrenar) {
		this.estrenar = estrenar;
	}
	public Boolean getAptoEscritura() {
		return aptoEscritura;
	}
	public void setAptoEscritura(Boolean aptoEscritura) {
		this.aptoEscritura = aptoEscritura;
	}
	public Boolean getAptoCredito() {
		return aptoCredito;
	}
	public void setAptoCredito(Boolean aptoCredito) {
		this.aptoCredito = aptoCredito;
	}
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public Integer getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}
	public Integer getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(Integer idPropietario) {
		this.idPropietario = idPropietario;
	}
	public List<OperacionPropiedad> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<OperacionPropiedad> operaciones) {
		this.operaciones = operaciones;
	}
	public List<TipoMoneda> getTiposMonedas() {
		return tiposMonedas;
	}
	public void setTiposMonedas(List<TipoMoneda> tiposMonedas) {
		this.tiposMonedas = tiposMonedas;
	}
	public List<TipoPropiedad> getTiposPropiedad() {
		return tiposPropiedad;
	}
	public void setTiposPropiedad(List<TipoPropiedad> tiposPropiedad) {
		this.tiposPropiedad = tiposPropiedad;
	}
	public List<Localidad> getLocalidadList() {
		return localidadList;
	}
	public void setLocalidadList(List<Localidad> localidadList) {
		this.localidadList = localidadList;
	}
	public List<Provincia> getProvinciaList() {
		return provinciaList;
	}
	public void setProvinciaList(List<Provincia> provinciaList) {
		this.provinciaList = provinciaList;
	}
	public List<String> getDormitorioList() {
		return dormitorioList;
	}
	public void setDormitorioList(List<String> dormitorioList) {
		this.dormitorioList = dormitorioList;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
}
