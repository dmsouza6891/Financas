package br.com.dmsouza.financas.model.dao;

import jakarta.persistence.*;

import br.com.dmsouza.financas.util.JPAUtil;

public class DaoFactory {
	
	private static final EntityManager em = JPAUtil.getEntityManager();
	
	public static FonteDeRecursoDao getFonteDeRecursoDao() {
		return new FonteDeRecursoDao(em);
	}

}
