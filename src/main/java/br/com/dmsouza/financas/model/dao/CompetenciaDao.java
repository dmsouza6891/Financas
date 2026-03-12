package br.com.dmsouza.financas.model.dao;

import java.time.YearMonth;
import java.util.Iterator;
import java.util.List;

import br.com.dmsouza.financas.model.Competencia;
import jakarta.persistence.EntityManager;

public class CompetenciaDao {
	
	public EntityManager em;
	
	public CompetenciaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Competencia competencia) {
		this.em.persist(competencia);
	}
	
	public void editar(Competencia competencia) {
		this.em.merge(competencia);
	}
	
	public void remover(Competencia competencia) {
		competencia = em.merge(competencia);
		this.em.remove(competencia);
	}
	
	public Competencia buscarPorId(int id) {
		return this.em.find(Competencia.class, id);
	}
	
	public boolean buscaCompetencia(YearMonth referencia) {  //verifica de existe a ocorrência de um nome nos cadastros
		List<Competencia> cadastros = buscarTodos();
		Iterator<Competencia> iterator = cadastros.iterator();
		Competencia registro;
		while(iterator.hasNext()) {
			registro = iterator.next();
			if(registro.getReferencia().equals(referencia)) 
				return true;
		}
		return false;
	}
	
	public List<Competencia> buscarTodos(){
		String jpql = "SELECT p FROM Competencia AS p ";
		return this.em.createQuery(jpql, Competencia.class).getResultList();
	}

}
