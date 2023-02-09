package br.com.dmsouza.financas.model.dao;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.Credito;

public class CreditoDao {
	
	public EntityManager em;
	
	public CreditoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Credito credito) {
		this.em.persist(credito);
	}

}
