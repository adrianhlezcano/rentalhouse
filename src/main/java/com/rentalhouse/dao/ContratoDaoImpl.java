package com.rentalhouse.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.Contrato;

@Repository("contratoDao")
public class ContratoDaoImpl extends GenericDaoImpl<Contrato> implements ContratoDao {

	public Contrato getById(Integer idContrato) {
		return (Contrato) getSessionFactory().getCurrentSession()
				.createCriteria(Contrato.class)
				.setFetchMode("inquilino", FetchMode.JOIN)
				.setFetchMode("propiedad", FetchMode.JOIN)
				.add(Restrictions.eq("idContrato", idContrato))
				.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Contrato> getByFieldValue(String fieldName, Integer fieldValue, int firstRow, int maxRows) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Contrato.class);
		Criteria criteria2 = criteria.createCriteria("inquilino");
		criteria2.add(Restrictions.eq(fieldName, fieldValue));
		criteria.setFetchMode("propiedad", FetchMode.JOIN);
		return (List<Contrato>) criteria.addOrder(Order.asc("fechaCreacion")).list();
	}
	@SuppressWarnings("unchecked")
	public List<Contrato> getByFieldValue(String fieldName, String fieldValue, int firstRow, int maxRows) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Contrato.class);
		Criteria criteria2 = criteria.createCriteria("inquilino");
		criteria2.add(Restrictions.ilike(fieldName, fieldValue, MatchMode.ANYWHERE));
		criteria.setFetchMode("propiedad", FetchMode.JOIN);
		return (List<Contrato>) criteria.addOrder(Order.asc("fechaCreacion")).list();
	}
	@SuppressWarnings("unchecked")
	public List<Contrato> getContratos(int firstRow, int maxRows) {
		return (List<Contrato>) getSessionFactory().getCurrentSession().createCriteria(Contrato.class)
		.setFetchMode("propiedad", FetchMode.JOIN)
		.setFetchMode("inquilino", FetchMode.JOIN)
		.addOrder(Order.desc("fechaCreacion"))
		.setFirstResult(firstRow)
		.setMaxResults(maxRows)
		.list();
	}	
}
