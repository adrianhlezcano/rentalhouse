package com.rentalhouse.dao;

import java.util.List;

import com.rentalhouse.domain.Contrato;

public interface ContratoDao extends GenericDao<Contrato> {
	public Contrato getById(Integer idContrato);
	public List<Contrato> getContratos(int firstRow, int maxRows);
	public List<Contrato> getByFieldValue(String fieldName, Integer fieldValue, int firstRow, int maxRows);
	public List<Contrato> getByFieldValue(String fieldName, String fieldValue, int firstRow, int maxRows);
	
}
