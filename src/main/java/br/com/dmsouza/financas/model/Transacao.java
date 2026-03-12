//representa uma transação realizada em determinada Fonte de Recurso.
package br.com.dmsouza.financas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.dmsouza.financas.model.enums.TipoTransacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identificação no bando de dados 
	private LocalDate dataOcorrencia = LocalDate.now(); //data em que transação ocorreu
	private String descricao; //descrição da transação para efeitos de reconhecimentos futuros
	private BigDecimal valor; //valor do débito
	
	@ManyToOne
	private FonteDeRecurso fonteDeRecurso; //Fonte de Recurso onde será realizada a transação 
	@ManyToOne
	private Categoria categoria; //categoria, configurada pelo usuário, para indicar a que grupo prédefinido pertence a transação 
	@ManyToOne
	private Competencia competencia; //competencia(mes/ano) em que a transação ocorreu 
	
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	public Transacao() {}
	
	//construtor, getters e setters padr�es
	public Transacao(LocalDate dataOcorrencia, String descricao, BigDecimal valor, FonteDeRecurso fonteDeRecurso, Categoria categoria, Competencia competencia, TipoTransacao tipo) {
		this.dataOcorrencia = dataOcorrencia;
		this.descricao = descricao;
		this.valor = valor;
		this.fonteDeRecurso = fonteDeRecurso;
		this.categoria = categoria;
		this.competencia = competencia;
		this.tipo = tipo;
	}
	public LocalDate getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(LocalDate dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}

	public FonteDeRecurso getFonteDeRecurso() {
		return fonteDeRecurso;
	}

	public void setFonteDeRecurso(FonteDeRecurso fonteDeRecurso) {
		this.fonteDeRecurso = fonteDeRecurso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	
}
