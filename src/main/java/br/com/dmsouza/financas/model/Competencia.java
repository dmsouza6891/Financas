//agrupa as transações e fontes de recursos por mês, oferecendo controles de abertura e fechamento
package br.com.dmsouza.financas.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import br.com.dmsouza.financas.model.enums.SituacaoCompetencia;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "competencias")
public class Competencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;       //para uso e identificação no banco de dados
	private YearMonth referencia;  //mês e ano da competência
	
	@Enumerated(EnumType.STRING)
	private SituacaoCompetencia	situacao = SituacaoCompetencia.ABERTA;
	
	@OneToMany(mappedBy = "competencia")
	private List<Transacao> transacoes = new ArrayList<>();
	 
	@OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL)
	private List<CompetenciaFonteRecurso> fontes = new ArrayList<>();
	
	protected Competencia() {
	}
	
	public Competencia(int id, YearMonth referencia, SituacaoCompetencia situacao, List<Transacao> transacoes, List<CompetenciaFonteRecurso> fontes) {
		this.id = id;
		this.referencia = referencia;
		this.situacao = situacao;
		this.transacoes = transacoes;
		this.fontes = fontes;
	}

	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public YearMonth getReferencia() {
		return referencia;
	}

	public void setReferencia(YearMonth referencia) {
		this.referencia = referencia;
	}

	public SituacaoCompetencia getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCompetencia situacao) {
		this.situacao = situacao;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public List<CompetenciaFonteRecurso> getFontes() {
		return fontes;
	}

	public void setFontes(List<CompetenciaFonteRecurso> fontes) {
		this.fontes = fontes;
	}
	
}
