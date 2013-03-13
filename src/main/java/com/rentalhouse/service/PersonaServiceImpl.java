package com.rentalhouse.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rentalhouse.dao.PersonaDao;
import com.rentalhouse.dao.UsuarioDao;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Usuario;
import com.rentalhouse.utils.StringValidation;

@Service("personaService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PersonaServiceImpl implements PersonaService{	
	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private UsuarioDao usuarioDao;

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(Persona persona) {			
		personaDao.save(persona);
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(Persona persona) {		
		personaDao.update(persona);		
	}
	public Persona get(Integer id) {		
		return personaDao.get(id);		
	}	
	public Persona getByDni(Integer dni, Class<? extends Persona> type) {		
		return personaDao.getPersonaByDni(dni, type);
	}
	public List<Persona> getByApellido(String apellido, Class<? extends Persona> type) {		
		return (List<Persona>) personaDao.getPersonaByApellido(apellido, type);		
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(Persona persona) {		
		personaDao.delete(persona);		
	}
	public List<Persona> getAll(int firstRow, int maxRows, boolean all, Class<? extends Persona> type) {
		List<Persona> returnList = Collections.emptyList(); 
		if(all){			
			returnList = (List<Persona>) personaDao.getAll(type);			
		}
		returnList = personaDao.getPersonas(firstRow, maxRows, type);		
		return returnList;
	}
	public List<Persona> find(String fieldName, String fieldValue, Class<? extends Persona> type) {		
		return (List<Persona>) personaDao.find(fieldName, fieldValue, type);		
	}
	public Usuario getUsuarioByUsername(String username) {		
		return usuarioDao.getUsuarioByUsername(username);		
	}
	public Usuario getUsuarioForLogin(String username, String password) {		
		return usuarioDao.getUsuarioForLogin(username, password);		
	}	
	public List findPersonaByFieldValue(String fieldName, String fieldValue, Class<? extends Persona> type) {
		List<Persona> personList = Collections.emptyList();
		if (StringValidation.isValidString(fieldValue, 0, fieldValue.length(), "^(\\d+)$")){
			personList = personaDao.findPersonaByFieldValue(fieldName, Integer.valueOf(fieldValue), type);			
		} else {			
			personList = personaDao.findPersonaByFieldValue(fieldName, fieldValue, type);
		}
		Map<String, String> personJson = Collections.emptyMap();
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>(personList.size());		
		for(Persona p : personList){
			personJson = new HashMap<String, String>();
			personJson.put("nombreCompleto", p.getNombreCompleto());
			personJson.put("dni", String.valueOf(p.getDni()));
			personJson.put("tipoDni", p.getTipoDni().getValue());
			personJson.put("telefono", p.getTelefono());
			personJson.put("tipoTelefono", p.getTipoTelefono().getValue());
			returnList.add(personJson);
		}	
		return returnList;
	}
	
}
