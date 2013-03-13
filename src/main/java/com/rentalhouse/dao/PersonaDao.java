package com.rentalhouse.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.rentalhouse.domain.Persona;

public interface PersonaDao extends GenericDao<Persona>{
	public List<Persona> getPersonaByApellido(String apellido, Class<? extends Persona> type) throws DataAccessException;
	public Persona getPersonaByDni(int dni, Class<? extends Persona> type) throws DataAccessException;
	public List<Persona> getPersonas(int firstRow, int maxRows, Class<? extends Persona> type) throws DataAccessException;
	public List<Persona> find(String fieldName, String fieldValue, Class<? extends Persona> type) throws DataAccessException;
	public List<Persona> findPersonaByFieldValue(String fieldName, Integer fieldValue, Class<? extends Persona> type) throws DataAccessException;
	public List<Persona> findPersonaByFieldValue(String fieldName, String fieldValue, Class<? extends Persona> type) throws DataAccessException;
	public List<Persona> getAll(Class<? extends Persona> type) throws DataAccessException;

}