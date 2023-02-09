/*representa um reposit�rio financeiro que cont�m uma quantia determinada dinheiro. 
Podendo ser uma conta banc�ria, uma carteira, cofre. Ou seja, configurado conforme necessidade do usu�rio*/

package br.com.dmsouza.financas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "centrosdecusto")
public class CentroDeCusto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;       //para uso e identifica��o no banco de dados
	private String nome;  //nome do centro de custo
	private double saldo;   //saldo atual do centro de custo
	
	public CentroDeCusto(){}
	
	//contrutores, gets e setters padr�es
	public CentroDeCusto(String nome, double saldo) {
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
