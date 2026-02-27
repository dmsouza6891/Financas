package br.com.dmsouza.financas.model.dao;

import jakarta.persistence.*;

import br.com.dmsouza.financas.model.Transacao;

public class CreditoDao {
	
	public EntityManager em;
	
	public CreditoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Transacao credito) {
		this.em.persist(credito);
	}

}
