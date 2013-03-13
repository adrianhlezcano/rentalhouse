package com.rentalhouse.dao;

import org.springframework.dao.DataAccessException;

public interface GenericDao <T>{
	public T get(Integer id)throws DataAccessException;
	public void save(T object)throws DataAccessException;
	public void update(T object) throws DataAccessException;
	public void delete(T object)throws DataAccessException;
}
