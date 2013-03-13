package com.rentalhouse.service;

import java.util.List;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Provincia;

public interface LocalidadService {
	public List<Localidad> getLocalidadByIdProvincia(Integer idProvincia);
	public Localidad getLocalidadById(Integer idLocalidad);
	public List<Provincia> getProvincias();
	public Provincia getProvinciaById(Integer idProvincia);
}
