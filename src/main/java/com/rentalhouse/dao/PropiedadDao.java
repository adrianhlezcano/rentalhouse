package com.rentalhouse.dao;

import java.util.List;

import com.rentalhouse.domain.Propiedad;

public interface PropiedadDao extends GenericDao<Propiedad> {
	public List<Propiedad> getPropidadByField(String fieldName, Object fieldValue);
	public List<Propiedad> searchPropidades(String tipoOperacion, String tipoPropiedad,
			Integer precioMinimo, Integer precioMaximo, Integer dormitorios);
	public List<Propiedad> getPropidades(int firstRow, int maxRow, boolean all);
	public Propiedad getPropiedadById(Integer idPropiedad);
}
