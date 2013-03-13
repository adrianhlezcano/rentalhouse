package com.rentalhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentalhouse.dao.LocalidadDao;
import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Provincia;

@Service("localidadService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class LocalidadServiceImpl implements LocalidadService{
	@Autowired
	private LocalidadDao localidadDao;

	public List<Localidad> getLocalidadByIdProvincia(Integer idProvincia) {
		return localidadDao.getLocalidadByIdProvincia(idProvincia);
	}
	public Localidad getLocalidadById(Integer idLocalidad) {
		Localidad localidad = localidadDao.getLocalidadById(idLocalidad);		
		return localidad;
	}
	public List<Provincia> getProvincias(){
		return localidadDao.getProvincias();
	}
	public Provincia getProvinciaById(Integer idProvincia){
		Provincia provincia = localidadDao.getProvinciaById(idProvincia);
		return provincia;
	}

}
