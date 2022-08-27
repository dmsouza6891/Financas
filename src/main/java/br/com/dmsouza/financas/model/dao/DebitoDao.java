package br.com.dmsouza.financas.model.dao;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.Debito;

public class DebitoDao {
	
	public EntityManager em;
	
	public DebitoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Debito debito) {
		this.em.persist(debito);
	}

}
