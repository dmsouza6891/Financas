package br.com.dmsouza.financas.model.dao;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.CentroDeCusto;

public class CentroDeCustoDao {
	
	public EntityManager em;
	
	public CentroDeCustoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(CentroDeCusto centrodecusto) {
		this.em.persist(centrodecusto);
	}

}
