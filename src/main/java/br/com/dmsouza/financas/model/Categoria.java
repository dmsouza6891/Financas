//representa uma categoria de um débito ou crédito, para ser possível agrupá-los
package br.com.dmsouza.financas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //para uso e identificação no banco de dados
	private String nome; //nome da categoria que representará o grupo de débitos ou créditos
	
	public Categoria() {}
	
	//contrutor, getters e setters padrões
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
