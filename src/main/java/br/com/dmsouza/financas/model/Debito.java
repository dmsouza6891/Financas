//representa uma opera��o de d�bito realizada em determinado centro de custo.
package br.com.dmsouza.financas.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "debitos")
public class Debito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identifica��o no bando de dados 
	private LocalDate dataOcorrencia; //data em que o d�bito ocorreu
	private String descricao; //descri��o do d�bito para efeitos de reconhecimentos futuros
	private double valor; //valor do d�bito
	
	@ManyToOne
	CentroDeCusto centrodecusto; //centro de custo escolhido para custear o d�bito
	@ManyToOne
	Categoria categoria; //categoria, configurada pelo usu�rio, para indicar a que grupo pertence o d�bito 
	
	//construtor, getters e setters padr�es
	public Debito(LocalDate dataOcorrencia, String descricao, double valor, CentroDeCusto centrodecusto, Categoria categoria) {
		this.dataOcorrencia = dataOcorrencia;
		this.descricao = descricao;
		this.valor = valor;
		this.centrodecusto = centrodecusto;
		this.categoria = categoria;
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
	
}
