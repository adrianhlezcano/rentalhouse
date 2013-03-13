package com.rentalhouse.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.rentalhouse.domain.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements
		UsuarioDao {

	public Usuario getUsuarioByUsername(String username)
			throws DataAccessException {
		return (Usuario) getSessionFactory().getCurrentSession()
				.createCriteria(Usuario.class)
				.add(Restrictions.like("username", username)).uniqueResult();
	}

	public Usuario getUsuarioForLogin(String username, String password)
			throws DataAccessException {
		return (Usuario) getSessionFactory().getCurrentSession()
				.createCriteria(Usuario.class)
				.add(Restrictions.like("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	public boolean isValidarNewUsuario(Usuario usuario)
			throws DataAccessException {
		boolean returnValue = false;
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		Criterion criterion1 = Restrictions.eq("dni", usuario.getDni());
		Criterion criterion2 = Restrictions.ilike("username",
				usuario.getUsername());
		criteria.add(Restrictions.disjunction().add(criterion1).add(criterion2));
		if (criteria.list().isEmpty()) {
			return true;
		}
		return returnValue;
	}
}
