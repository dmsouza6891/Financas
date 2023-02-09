package br.com.dmsouza.financas.model.dao;

import br.com.dmsouza.financas.util.JPAUtil;

public class DaoFactory {
	
	public static CentroDeCustoDao criarCentroDeCustoDao() {
		return new CentroDeCustoDao(JPAUtil.getEntityManager());
	}

}
