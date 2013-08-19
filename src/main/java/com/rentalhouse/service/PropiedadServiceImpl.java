package com.rentalhouse.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentalhouse.dao.PropiedadDao;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.form.PropiedadForm;

@Service("propiedadService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PropiedadServiceImpl implements PropiedadService{
	@Autowired
	private PropiedadDao propiedadDao;	
	
	public List<Propiedad> getPropidadByField(String fieldName,
			Object fieldValue) {
		return propiedadDao.getPropidadByField(fieldName, fieldValue);
	}
	public List<Propiedad> getPropidades(int firstRow, int maxRow, boolean all) {
		return propiedadDao.getPropidades(firstRow, maxRow, all);
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(Propiedad propiedad) {		
		propiedadDao.save(propiedad);		
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(Propiedad propiedad) {		
		propiedadDao.update(propiedad);				
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void remove(Propiedad propiedad) {		
		propiedadDao.delete(propiedad);		
	}
	public Propiedad getPropiedadById(Integer idPropiedad){
		return propiedadDao.getPropiedadById(idPropiedad);
	}
	public Propiedad get(Integer idPropiedad) {
		return propiedadDao.get(idPropiedad);		
	}
	public List<Map<String, Object>> searchPropidades(String tipoOperacion, String tipoPropiedad, 
			Integer precioMinimo, Integer precioMaximo, Integer dormitorios) {	
		List<Propiedad> propiedadList = propiedadDao.searchPropidades(tipoOperacion, 
				tipoPropiedad, precioMinimo, precioMaximo, dormitorios); 
		Map<String, Object> propiedadJson = Collections.emptyMap();
		List<Map<String, Object>> returnList = 
			new ArrayList<Map<String, Object>>(propiedadList.size());
		for (Propiedad p : propiedadList) {
			propiedadJson = new HashMap<String, Object>();
			propiedadJson.put("idPropiedad", p.getIdPropiedad().toString());
			propiedadJson.put("tipoPropiedad", p.getTipoPropiedad().getValue());
			propiedadJson.put("operacionPropiedad", p.getOperacionPropiedad().getValue());
			propiedadJson.put("precioAlquiler", p.getPrecioAlquiler());
			propiedadJson.put("precioVenta", p.getPrecioVenta());
			propiedadJson.put("dormitorios", p.getDormitorios());
			returnList.add(propiedadJson);			
		}			
		return returnList;			
	}
	public List<Propiedad> searchPropidadesByParams(String tipoOperacion, String tipoPropiedad, 
			Integer precioMinimo, Integer precioMaximo, Integer dormitorios) {	
		return propiedadDao.searchPropidades(tipoOperacion, 
				tipoPropiedad, precioMinimo, precioMaximo, dormitorios); 				
	}

}
