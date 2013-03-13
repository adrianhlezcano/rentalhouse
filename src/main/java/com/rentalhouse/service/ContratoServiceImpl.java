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

import com.rentalhouse.dao.ContratoDao;
import com.rentalhouse.dao.CuotaDao;
import com.rentalhouse.domain.Contrato;
import com.rentalhouse.domain.Cuota;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.utils.StringValidation;

@Service("contratoService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ContratoServiceImpl implements ContratoService{
	@Autowired
	private ContratoDao contratoDao;
	@Autowired
	private CuotaDao cuotaDao;
	private Log log = LogFactoryImpl.getLog(getClass());

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void saveContrato(Contrato contrato) {
		contratoDao.save(contrato);		
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateContrato(Contrato contrato) {
		contratoDao.update(contrato);		
	}
	public Contrato getContratoById(Integer idContrato) {
		return contratoDao.getById(idContrato);
	}
	public List<Contrato> getContratos(int firstRow, int maxRows){
		log.info("get contrato: firstRow: "+firstRow+", maxRows: "+maxRows);
		return contratoDao.getContratos(firstRow, maxRows);
	}	
	public List getContratosByFieldValue(String fieldName, String fieldValue, int firstRow, int maxRows) {
		List<Contrato> contratoList = Collections.emptyList();
		if (StringValidation.isValidString(fieldValue, 0, fieldValue.length(), "^[\\d]+$")){
			contratoList = contratoDao.getByFieldValue(fieldName, Integer.valueOf(fieldValue), firstRow, maxRows);
		} else if (StringValidation.isValidString(fieldValue, 0, fieldValue.length(), "^[\\w]+$")){
			contratoList = contratoDao.getByFieldValue(fieldName, fieldValue, firstRow, maxRows);
		}
		Map<String, String> contratoJson = Collections.emptyMap();
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>(contratoList.size());		
		for(Contrato c : contratoList){
			contratoJson = new HashMap<String, String>();
			contratoJson.put("nombreInquilino", c.getInquilino().getNombreCompleto());
			contratoJson.put("propiedad", c.getPropiedad().toString());
			contratoJson.put("estadoContrato", c.getEstadoContrato().getValue());
			contratoJson.put("idContrato", String.valueOf(c.getIdContrato()));			
			returnList.add(contratoJson);
		}	
		return returnList;
	}	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateCuota(Cuota cuota) {
		cuotaDao.update(cuota);
	}
	public Cuota getCuota(Integer idCuota) {
		return cuotaDao.get(idCuota);		
	}
	public List<Cuota> getCuotaByIdContrato(Integer idContrato, int firstRow, int maxRows) {
		return cuotaDao.getByIdContrato(idContrato, firstRow, maxRows);
	}
	public Integer numberOfCuotasByContrato(Integer idContrato) {
		return cuotaDao.numberOfCuotasByContrato(idContrato);
	}
	public List<Cuota> getCuotaByFieldName(String fieldName, Object fieldValue, int firstRow, int maxRows) {
		return cuotaDao.getByFieldName(fieldName, fieldValue, firstRow, maxRows);
	}	
}
