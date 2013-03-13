package com.rentalhouse.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	private Class<T> type;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	protected SessionFactory getSessionFactory() throws DataAccessException{
		return this.sessionFactory;
	}
	protected GenericDaoImpl(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	public GenericDaoImpl(Class<T> type) {
		super();
		this.type = type;
	}
	@SuppressWarnings("unchecked")
	public T get(Integer id) throws DataAccessException{
		if (id == null){
			return null;
		} else {
			return (T) getSessionFactory().getCurrentSession().get(type, id);
		}		
	}	
	public void save(T object) throws DataAccessException{
		getSessionFactory().getCurrentSession().persist(object);		
	}
	public void update(T object) throws DataAccessException{
		getSessionFactory().getCurrentSession().merge(object);
	}
	public void delete(T object) throws DataAccessException{
		getSessionFactory().getCurrentSession().delete(object);		
	}
	protected void setType(Class<T> type) {
		this.type = type;
	}
}
