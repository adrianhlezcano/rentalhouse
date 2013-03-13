package com.rentalhouse.service;

import java.util.List;

import com.rentalhouse.domain.Propiedad;

public interface PropiedadService {
	public void save(Propiedad propiedad);
	public void update(Propiedad propiedad);
	public void remove(Propiedad propiedad);
	public Propiedad get(Integer idPropiedad);
	public Propiedad getPropiedadById(Integer idPropiedad);
	public List<Propiedad> getPropidadByField(String fieldName, Object fieldValue);
	public List<Propiedad> getPropidades(int firstRow, int maxRow, boolean all);
	public List searchPropidades(String tipoOperacion, String tipoPropiedad,
			Integer precioMinimo, Integer precioMaximo, Integer dormitorios);

}
