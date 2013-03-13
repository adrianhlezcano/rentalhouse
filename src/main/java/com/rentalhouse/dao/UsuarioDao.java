package com.rentalhouse.dao;

import org.springframework.dao.DataAccessException;

import com.rentalhouse.domain.Usuario;

public interface UsuarioDao extends GenericDao<Usuario> {
	public Usuario getUsuarioByUsername(String username)
			throws DataAccessException;

	public Usuario getUsuarioForLogin(String username, String password)
			throws DataAccessException;

	public boolean isValidarNewUsuario(Usuario usuario)
			throws DataAccessException;
}
