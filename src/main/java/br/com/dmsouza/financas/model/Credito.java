//representa uma operação de crédito realizada em determinado centro de custo.
package br.com.dmsouza.financas.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "creditos")
public class Credito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identificação no bando de dados 
	private LocalDate dataOcorrencia; //data em que o débito ocorreu
	private String descricao; //descrição do débito para efeitos de reconhecimentos futuros
	private double valor; //valor do débito
	
	@ManyToOne
	CentroDeCusto centrodecusto; //centro de custo escolhido para custear o débito
	@ManyToOne
	Categoria categoria; //categoria, configurada pelo usuário, para indicar a que grupo pertence o débito 
	
	public Credito() {}
	
	//construtor, getters e setters padrões
	public Credito(LocalDate dataOcorrencia, String descricao, double valor, CentroDeCusto centrodecusto, Categoria categoria) {
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
