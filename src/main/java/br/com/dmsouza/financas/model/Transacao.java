//representa uma operação de crédito realizada em determinada Fonte de Recurso.
package br.com.dmsouza.financas.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.dmsouza.financas.model.enums.TipoTransacao;

@Entity
@Table(name = "transacoes")
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identificação no bando de dados 
	private LocalDate dataOcorrencia = LocalDate.now(); //data em que o débito ocorreu
	private String descricao; //descrição do débito para efeitos de reconhecimentos futuros
	private double valor; //valor do débito
	
	@ManyToOne
	private FonteDeRecurso fonteDeRecurso; //centro de custo escolhido para custear o débito
	@ManyToOne
	private Categoria categoria; //categoria, configurada pelo usuário, para indicar a que grupo pertence o débito 
	
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	public Transacao() {}
	
	//construtor, getters e setters padrões
	public Transacao(LocalDate dataOcorrencia, String descricao, double valor, FonteDeRecurso fonteDeRecurso, Categoria categoria, TipoTransacao tipo) {
		this.dataOcorrencia = dataOcorrencia;
		this.descricao = descricao;
		this.valor = valor;
		this.fonteDeRecurso = fonteDeRecurso;
		this.categoria = categoria;
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
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
}
