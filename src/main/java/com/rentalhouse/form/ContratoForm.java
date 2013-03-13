package com.rentalhouse.form;

import java.util.Date;
import java.util.List;

import com.rentalhouse.domain.Contrato;
import com.rentalhouse.domain.EstadoContrato;
import com.rentalhouse.domain.Inquilino;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.utils.DateUtils;

public class ContratoForm {
	private Integer idContrato;	
	private String action;
	private String estadoContrato;
	private String fechaCreacion;
	private String comision;
	private String honorarios;
	private String depositoGarantia;
	private Integer idPropiedad;
	private Integer idInquilino;
	private String cuotas;
	private String importeCuota;	
	private Boolean administrado;
	private String observacion;
	private String nombreInquilino;
	private String propiedad;
	private List<Propiedad> propiedadList;
	
	public ContratoForm(){			
		setAdministrado(Boolean.TRUE);
		setCuotas("24");		
		setAction(AppConstant.INSERT);
	}
	public ContratoForm(Contrato contrato){	
		setIdContrato(contrato.getIdContrato());
		setEstadoContrato(contrato.getEstadoContrato().getValue());
		setFechaCreacion(DateUtils.fromDateToString(contrato.getFechaCreacion(), "dd MMM yyyy"));
		setComision(String.valueOf(contrato.getComision()));
		setHonorarios(String.valueOf(contrato.getHonorarios()));
		setDepositoGarantia(String.valueOf(contrato.getDepositoGarantia()));
		setIdPropiedad(contrato.getPropiedad().getIdPropiedad());
		setIdInquilino(contrato.getInquilino().getIdPersona());
		setAdministrado(contrato.isAdministrado());		
		setObservacion(contrato.getObservacion());
		setNombreInquilino(contrato.getInquilino().getApellido()+ " "+contrato.getInquilino().getNombre());
		setPropiedad(contrato.getPropiedad().toString());
	}	
	public Contrato toContrato(Contrato contrato, Propiedad propiedad, Inquilino inquilino){
		contrato.setIdContrato(getIdContrato());
		contrato.setEstadoContrato(EstadoContrato.VIGENTE);
		contrato.setFechaCreacion(new Date());
		contrato.setComision(Float.valueOf(getComision()));
		contrato.setHonorarios(Float.valueOf(getHonorarios()));
		contrato.setDepositoGarantia(Float.valueOf(getDepositoGarantia()));
		contrato.setPropiedad(propiedad);
		contrato.setInquilino(inquilino);
		contrato.setAdministrado(getAdministrado());
		contrato.setObservacion(getObservacion());
		int c = Integer.valueOf(getCuotas()).intValue();
		contrato.createCuotas(c, Integer.valueOf(getImporteCuota()));
		return contrato;
	}

	public Integer getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getHonorarios() {
		return honorarios;
	}
	public void setHonorarios(String honorarios) {
		this.honorarios = honorarios;
	}
	public String getDepositoGarantia() {
		return depositoGarantia;
	}
	public void setDepositoGarantia(String depositoGarantia) {
		this.depositoGarantia = depositoGarantia;
	}
	public Integer getIdPropiedad() {
		return idPropiedad;
	}
	public void setIdPropiedad(Integer idPropiedad) {
		this.idPropiedad = idPropiedad;
	}
	public Integer getIdInquilino() {
		return idInquilino;
	}
	public void setIdInquilino(Integer idInquilino) {
		this.idInquilino = idInquilino;
	}
	public String getCuotas() {
		return cuotas;
	}
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}
	public String getImporteCuota() {
		return importeCuota;
	}
	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}
	public Boolean getAdministrado() {
		return administrado;
	}
	public void setAdministrado(Boolean administrado) {
		this.administrado = administrado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public List<Propiedad> getPropiedadList() {
		return propiedadList;
	}
	public void setPropiedadList(List<Propiedad> propiedadList) {
		this.propiedadList = propiedadList;
	}
	public String getNombreInquilino() {
		return nombreInquilino;
	}
	public void setNombreInquilino(String nombreInquilino) {
		this.nombreInquilino = nombreInquilino;
	}	
	public String getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}
}
