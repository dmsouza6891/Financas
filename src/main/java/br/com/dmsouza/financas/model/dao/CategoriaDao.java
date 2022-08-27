package br.com.dmsouza.financas.model.dao;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.Categoria;

public class CategoriaDao {
	
	public EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

}
