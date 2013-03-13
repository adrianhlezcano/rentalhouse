package com.rentalhouse.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.Cuota;

@Repository("cuotaDao")
public class CuotaDaoImpl extends GenericDaoImpl<Cuota> implements CuotaDao{
	
	@SuppressWarnings("unchecked")
	public List<Cuota> getByIdContrato(Integer idContrato, int firstRow, int maxRows) {
//		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Cuota.class);
//		Criteria criteria2 = criteria.createCriteria("contrato").add(Restrictions.eq("idContrato", idContrato));
//		return (List<Cuota>) criteria.addOrder(Order.asc("numeroCuota")).list();
		return (List<Cuota>) getSessionFactory().getCurrentSession().createCriteria(Cuota.class)
		  .addOrder(Order.asc("numeroCuota"))
		  .createCriteria("contrato").add(Restrictions.eq("idContrato", idContrato))
		  .setFirstResult(firstRow)
		  .setMaxResults(maxRows)
		  .list();
	}
	@SuppressWarnings("unchecked")
	public List<Cuota> getByFieldName(String fieldName, Object fieldValue, int firstRow, int maxRows) {
//		return (List<Cuota>) getSessionFactory().getCurrentSession().createCriteria(Cuota.class)
//		.add(Restrictions.eq(fieldName, fieldValue))
//		.addOrder(Order.asc("numero")).list();
		return (List<Cuota>) getSessionFactory().getCurrentSession().createCriteria(Cuota.class)
		.add(Restrictions.eq(fieldName, fieldValue))
		.addOrder(Order.asc("numero"))
		.setFirstResult(firstRow)
		.setMaxResults(maxRows)
		.list();
	}
	public Integer numberOfCuotasByContrato(Integer idContrato) {
		return (Integer) getSessionFactory().getCurrentSession().createCriteria(Cuota.class)
		.setProjection(Projections.max("numeroCuota"))
		.createCriteria("contrato").add(Restrictions.eq("idContrato", idContrato))
		.uniqueResult();		
	}

}
