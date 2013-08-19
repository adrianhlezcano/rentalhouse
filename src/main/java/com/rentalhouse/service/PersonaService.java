package com.rentalhouse.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Usuario;

public interface PersonaService {
	public void save(Persona persona);
	public void update(Persona persona);
	public Persona get(Integer id);
	public Persona getByDni(Integer dni, Class<? extends Persona> type);
	public List<Persona> getByApellido(String apellido, Class<? extends Persona> type);
	void delete(Persona persona);
	public List<Persona> getAll(int firstRow, int maxRows, boolean all, Class<? extends Persona> type);
	public List<Persona> find(String fieldName, String fieldValue, Class<? extends Persona> type);
	public List<Persona> findPersonaByFieldValue(String fieldName, String fieldValue, Class<? extends Persona> type);
	public Usuario getUsuarioByUsername(String username);
	public Usuario getUsuarioForLogin(String username, String password);	
	public boolean isNewUsuario(Usuario usuario);
	public Usuario getUsuarioWithCredentials(String username,
			String respuestaSeguridad, String email);
}
