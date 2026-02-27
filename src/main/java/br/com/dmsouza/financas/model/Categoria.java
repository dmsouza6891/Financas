//representa uma categoria de um d�bito ou cr�dito, para ser poss�vel agrup�-los
package br.com.dmsouza.financas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identifica��o no banco de dados
	private String nome; //nome da categoria que representar� o grupo de d�bitos ou cr�ditos
	
	public Categoria() {}
	
	//contrutor, getters e setters padr�es
	public Categoria(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
