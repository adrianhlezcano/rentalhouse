package com.rentalhouse.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.DisjunctionFragment;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.OperacionPropiedad;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.domain.TipoPropiedad;

@Repository("propiedadDao")
public class PropiedadDaoImpl extends GenericDaoImpl<Propiedad> implements PropiedadDao{
	private final Log LOG = LogFactoryImpl.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	public List<Propiedad> getPropidadByField(String fieldName,
			Object fieldValue) {
		return (List<Propiedad>) getSessionFactory().getCurrentSession()
				.createCriteria(Propiedad.class)
				.add(Restrictions.eq(fieldName, fieldValue))
				.add(Restrictions.eq("publicar", Boolean.TRUE))
				.addOrder(Order.asc("precioVenta"))
				.addOrder(Order.asc("precioAlquiler")).list();
	}
	@SuppressWarnings("unchecked")
	public List<Propiedad> getPropidades(int firstRow, int maxRow, boolean all) {
		return (List<Propiedad>) getSessionFactory().getCurrentSession()
		.createCriteria(Propiedad.class).list();
	}
	
	public Propiedad getPropiedadById(Integer idPropiedad){		
		return (Propiedad) getSessionFactory().getCurrentSession()
				.createCriteria(Propiedad.class)
				.add(Restrictions.eq("idPropiedad", idPropiedad))
				.addOrder(Order.asc("precioVenta"))
				.addOrder(Order.asc("precioAlquiler")).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Propiedad> searchPropidades(String tipoOperacion,
			String tipoPropiedad, Integer precioMinimo, Integer precioMaximo,
			Integer dormitorios) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Propiedad.class);
		criteria.add(Restrictions.eq("operacionPropiedad", OperacionPropiedad.valueOf(tipoOperacion)));
		criteria.add(Restrictions.eq("tipoPropiedad", TipoPropiedad.valueOf(tipoPropiedad)));
		String fieldName = OperacionPropiedad.valueOf(tipoOperacion).getValue().startsWith("A") ?
				"precioAlquiler" : "precioVenta";
		// check dormitorios field
		if (dormitorios > 0){
			criteria.add(Restrictions.eq("dormitorios", dormitorios.toString()));
		}		
		// precioMinimo or precioMaximo
		if (precioMinimo > 0 && precioMaximo > 0){
			criteria.add(Restrictions.between("precioAlquiler", precioMinimo, precioMaximo));
		} else if (precioMinimo > 0){
			criteria.add(Restrictions.ge(fieldName, precioMinimo));
		} else if (precioMaximo > 0){
			criteria.add(Restrictions.ge(fieldName, precioMaximo));
		}	
		criteria.addOrder(Order.asc("precioAlquiler"));
		criteria.addOrder(Order.asc("precioVenta"));
        return criteria.list();
	}
}
