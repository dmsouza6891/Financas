package br.com.dmsouza.financas.model.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.FonteDeRecurso;

public class FonteDeRecursoDao {
	
	public EntityManager em;
	
	public FonteDeRecursoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(FonteDeRecurso centrodecusto) {
		this.em.persist(centrodecusto);
	}
	
	public void editar(FonteDeRecurso centrodecusto) {
		this.em.merge(centrodecusto);
	}
	
	public void remover(FonteDeRecurso centrodecusto) {
		centrodecusto = em.merge(centrodecusto);
		this.em.remove(centrodecusto);
	}
	
	public FonteDeRecurso buscarPorId(int id) {
		return this.em.find(FonteDeRecurso.class, id);
	}
	
	public boolean buscaNome(String nome) {  //verifica de existe a ocorrência de um nome nos cadastros
		List<FonteDeRecurso> cadastros = buscarTodos();
		Iterator<FonteDeRecurso> iterator = cadastros.iterator();
		FonteDeRecurso registro;
		while(iterator.hasNext()) {
			registro = iterator.next();
			if(registro.getNome().equals(nome)) 
				return true;
		}
		return false;
	}
	
	public List<FonteDeRecurso> buscarTodos(){
		String jpql = "SELECT p FROM FonteDeRecurso AS p ";
		return this.em.createQuery(jpql, FonteDeRecurso.class).getResultList();
	}

}
