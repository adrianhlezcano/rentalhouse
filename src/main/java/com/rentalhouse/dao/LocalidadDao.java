package com.rentalhouse.dao;

import java.util.List;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Provincia;

public interface LocalidadDao extends GenericDao<Localidad>{
	public List<Localidad> getLocalidadByIdProvincia(Integer idProvincia);
	public Localidad getLocalidadById(Integer idLocalidad);
	public List<Provincia> getProvincias();
	public Provincia getProvinciaById(Integer idProvincia);
}
