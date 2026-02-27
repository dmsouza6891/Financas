/*representa um repositório financeiro que contém uma quantia determinada dinheiro. 
Podendo ser uma conta bancária, uma carteira, cofre. Ou seja, configurado conforme necessidade do usuário*/
package br.com.dmsouza.financas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fontesderecursos")
public class FonteDeRecurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;       //para uso e identificação no banco de dados
	private String nome;  //nome da fonte de recurso
	private double saldo; //saldo atual da fonte de recurso
	
	public FonteDeRecurso(){}
	
	//contrutores, gets e setters padrões
	public FonteDeRecurso(String nome, double saldo) {
		this.nome = nome;
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
