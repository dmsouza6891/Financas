//representa uma opera��o de cr�dito realizada em determinada Fonte de Recurso.
package br.com.dmsouza.financas.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import br.com.dmsouza.financas.model.enums.TipoTransacao;

@Entity
@Table(name = "transacoes")
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identifica��o no bando de dados 
	private LocalDate dataOcorrencia = LocalDate.now(); //data em que o d�bito ocorreu
	private String descricao; //descri��o do d�bito para efeitos de reconhecimentos futuros
	private double valor; //valor do d�bito
	
	@ManyToOne
	private FonteDeRecurso fonteDeRecurso; //centro de custo escolhido para custear o d�bito
	@ManyToOne
	private Categoria categoria; //categoria, configurada pelo usu�rio, para indicar a que grupo pertence o d�bito 
	
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	public Transacao() {}
	
	//construtor, getters e setters padr�es
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
