package com.rentalhouse.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.Inquilino;
import com.rentalhouse.domain.Persona;

@Repository("personaDao")
public class PersonaDaoImpl extends GenericDaoImpl<Persona> implements PersonaDao {
	
	public PersonaDaoImpl(){
		super(Persona.class);
	}	
	public PersonaDaoImpl(Class<Persona> type){
		super(type);		
	}

	@SuppressWarnings("unchecked")
	public List<Persona> getPersonaByApellido(String apellido,
			Class<? extends Persona> type) throws DataAccessException {
		return getSessionFactory().getCurrentSession().createCriteria(type)
				.add(Restrictions.ilike("apellido", apellido))
				.addOrder(Order.asc("apellido")).list();
	}

	public Persona getPersonaByDni(int dni, Class<? extends Persona> type)
			throws DataAccessException {
		return (Persona) getSessionFactory().getCurrentSession()
				.createCriteria(type).add(Restrictions.eq("dni", dni))
				.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas(int firstRow, int maxRows,
			Class<? extends Persona> type) throws DataAccessException {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(type);
		criteria.setMaxResults(maxRows);
		criteria.setFirstResult(firstRow);
		criteria.addOrder(Order.asc("apellido"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> find(String fieldName, String fieldValue,
			Class<? extends Persona> type) throws DataAccessException {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(type);
		criteria.add(Restrictions.ilike(fieldName, fieldValue,
				MatchMode.ANYWHERE));
		return criteria.addOrder(Order.asc("apellido")).list();
	}	
	@SuppressWarnings("unchecked")
	public List<Persona> getAll(Class<? extends Persona> type) throws DataAccessException{
		return Collections.unmodifiableList(getSessionFactory().getCurrentSession().createCriteria(Persona.class).list());
	}
	@SuppressWarnings("unchecked")
	public List<Persona> findPersonaByFieldValue(String fieldName, Integer fieldValue, 
			Class<? extends Persona> type) throws DataAccessException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.eq(fieldName, fieldValue));		
		return Collections.unmodifiableList(criteria.addOrder(Order.asc("apellido")).list());
	}
	@SuppressWarnings("unchecked")
	public List<Persona> findPersonaByFieldValue(String fieldName, String fieldValue,
			Class<? extends Persona> type)
			throws DataAccessException {
		return Collections.unmodifiableList(getSessionFactory()
				.getCurrentSession().createCriteria(type)
				.add(Restrictions.ilike(fieldName, fieldValue, MatchMode.ANYWHERE))
				.addOrder(Order.asc("apellido")).list());
	}
	@SuppressWarnings("unchecked")
	public Inquilino findInquilinoWithGarantes(Integer dni)
			throws DataAccessException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Inquilino.class);
		criteria.setFetchMode("garantes", FetchMode.SELECT);
		criteria.add(Restrictions.eq("dni", dni));
		return (Inquilino) criteria.uniqueResult();		
	}	
}
