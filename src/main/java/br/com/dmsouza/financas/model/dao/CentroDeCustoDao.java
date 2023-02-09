package br.com.dmsouza.financas.model.dao;

import java.util.List;

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
	
	public CentroDeCusto buscarPorId(int id) {
		return this.em.find(CentroDeCusto.class, id);
	}
	
	public List<CentroDeCusto> buscarTodos(){
		String jpql = "SELECT p FROM CentroDeCusto AS p ";
		return this.em.createQuery(jpql, CentroDeCusto.class).getResultList();
	}

}
