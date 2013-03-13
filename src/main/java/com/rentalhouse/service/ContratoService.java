package com.rentalhouse.service;

import java.util.List;

import com.rentalhouse.domain.Contrato;
import com.rentalhouse.domain.Cuota;

public interface ContratoService {
	// Contrato Dao
	public void saveContrato(Contrato contrato);
	public void updateContrato(Contrato contrato);	
	public Contrato getContratoById(Integer idContrato);
	public List<Contrato> getContratos(int firstRow, int maxRows);
	public List getContratosByFieldValue(String fieldName, String fieldValue, int firstRow, int maxRows);

	// Cuota Dao
	public void updateCuota(Cuota cuota);
	public Cuota getCuota(Integer idCuota);
	public List<Cuota> getCuotaByIdContrato(Integer idContrato, int firstRow, int maxRows);
	public Integer numberOfCuotasByContrato(Integer idContrato);
	public List<Cuota> getCuotaByFieldName(String fieldName, Object fieldValue, int firstRow, int maxRows);
}
