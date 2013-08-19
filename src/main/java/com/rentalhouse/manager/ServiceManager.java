package com.rentalhouse.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Usuario;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PersonaService;

@Component
public class ServiceManager{
	@Autowired
	private PersonaService personaService;
	@Autowired
	private LocalidadService localidadService;
	
	
	public void save(Persona persona){
		personaService.save(persona);
	}
	public void update(Persona persona){
		personaService.update(persona);
	}
	public Persona get(Integer id){
		return personaService.get(id);
	}
	public Persona getByDni(Integer dni, Class<? extends Persona> type){
		return personaService.getByDni(dni, type);
	}
	public List<Persona> getByApellido(String apellido, Class<? extends Persona> type){
		return personaService.getByApellido(apellido, type);
	}
	public void delete(Persona persona){
		personaService.delete(persona);
	}
	public List<Persona> getAll(int firstRow, int maxRows, boolean all, Class<? extends Persona> type){
		return personaService.getAll(firstRow, maxRows, all, type);
	}
	public List<Persona> find(String fieldName, String fieldValue, Class<? extends Persona> type){
		return personaService.find(fieldName, fieldValue, type);
	}
	public Usuario getUsuarioByUsername(String username){
		return personaService.getUsuarioByUsername(username);
	}
	public Usuario getUsuarioForLogin(String username, String password){
		return personaService.getUsuarioForLogin(username, password);
	}
	public List<Localidad> getLocalidadByIdProvincia(Integer idProvincia){
		return localidadService.getLocalidadByIdProvincia(idProvincia);
	}
	public Localidad getLocalidadById(Integer idLocalidad){
		return localidadService.getLocalidadById(idLocalidad);
	}
}
