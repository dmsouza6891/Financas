package br.com.dmsouza.financas.model.dao;

import java.util.Iterator;
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
	
	public void editar(CentroDeCusto centrodecusto) {
		this.em.merge(centrodecusto);
	}
	
	public void remover(CentroDeCusto centrodecusto) {
		centrodecusto = em.merge(centrodecusto);
		this.em.remove(centrodecusto);
	}
	
	public CentroDeCusto buscarPorId(int id) {
		return this.em.find(CentroDeCusto.class, id);
	}
	
	public boolean buscaNome(String nome) {  //verifica de existe a ocorrência de um nome nos cadastros
		List<CentroDeCusto> cadastros = buscarTodos();
		Iterator<CentroDeCusto> iterator = cadastros.iterator();
		CentroDeCusto registro;
		while(iterator.hasNext()) {
			registro = iterator.next();
			if(registro.getNome().equals(nome)) 
				return true;
		}
		return false;
	}
	
	public List<CentroDeCusto> buscarTodos(){
		String jpql = "SELECT p FROM CentroDeCusto AS p ";
		return this.em.createQuery(jpql, CentroDeCusto.class).getResultList();
	}

}
