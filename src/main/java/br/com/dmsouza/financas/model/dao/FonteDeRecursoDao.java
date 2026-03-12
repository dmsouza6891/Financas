package br.com.dmsouza.financas.model.dao;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.*;

import br.com.dmsouza.financas.model.FonteDeRecurso;

public class FonteDeRecursoDao {
	
	public EntityManager em;
	
	public FonteDeRecursoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(FonteDeRecurso fontederecurso) {
		this.em.persist(fontederecurso);
	}
	
	public void editar(FonteDeRecurso fontederecurso) {
		this.em.merge(fontederecurso);
	}
	
	public void remover(FonteDeRecurso fontederecurso) {
		fontederecurso = em.merge(fontederecurso);
		this.em.remove(fontederecurso);
	}
	
	public FonteDeRecurso buscarPorId(int id) {
		return this.em.find(FonteDeRecurso.class, id);
	}
	
	public boolean buscaNome(String nome) {  //verifica de existe a ocorr�ncia de um nome nos cadastros
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
