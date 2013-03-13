package com.rentalhouse.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Provincia;

@Repository("localidadDao")
public class LocalidadDaoImpl extends GenericDaoImpl<Localidad> implements LocalidadDao{

	@SuppressWarnings("unchecked")
	public List<Localidad> getLocalidadByIdProvincia(Integer idProvincia) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Localidad.class);
		Criteria criteria2 = criteria.createCriteria("provincia").add(
				Restrictions.eq("idProvincia", idProvincia));
		return criteria.addOrder(Order.asc("nombre")).list();	
	}
	public Localidad getLocalidadById(Integer idLocalidad) {
		return super.get(idLocalidad);
	}
	@SuppressWarnings("unchecked")
	public List<Provincia> getProvincias() {
		return (List<Provincia>) getSessionFactory().getCurrentSession()
				.createCriteria(Provincia.class).addOrder(Order.asc("nombre"))
				.list();
	}
	public Provincia getProvinciaById(Integer idProvincia) {
		return (Provincia) getSessionFactory().getCurrentSession().get(
				Provincia.class, idProvincia);
	}

}
