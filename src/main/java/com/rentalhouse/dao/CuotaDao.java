package com.rentalhouse.dao;

import java.util.List;

import com.rentalhouse.domain.Cuota;

public interface CuotaDao extends GenericDao<Cuota>{
	public List<Cuota> getByIdContrato(Integer idContrato, int firstRow, int maxRows);
	public Integer numberOfCuotasByContrato(Integer idContrato);
	public List<Cuota> getByFieldName(String fieldName, Object fieldValue, int firstRow, int maxRows);
}
