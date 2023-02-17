package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.model.CentroDeCusto;
import br.com.dmsouza.financas.model.dao.CentroDeCustoDao;
import br.com.dmsouza.financas.util.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class CadastroCentroDeCustoController implements Initializable {
	
	@FXML private Button btPrimeiro;
	@FXML private Button btAnterior;
	@FXML private Button btProximo;
	@FXML private Button btUltimo;
	@FXML private Button btNovo;
	@FXML private Button btEditar;
	@FXML private Button btExcluir;
	
	@FXML private TextField txtCodigo;
	@FXML private TextField txtNome;
	@FXML private TextField txtSaldo;
	
	private CentroDeCustoDao dao = new CentroDeCustoDao(JPAUtil.getEntityManager()); 
	private ArrayList<CentroDeCusto> registros = new ArrayList<CentroDeCusto>(dao.buscarTodos());
	private int posicaoAtual=0;
	
	public void initialize(URL location, ResourceBundle resources) {
		txtCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtSaldo.setDisable(true);
		updateView();
	}
	
	public void updateView() {
		if(!registros.isEmpty()) {
			CentroDeCusto registro = registros.get(posicaoAtual); //retorna o primeiro elemento dos registros
			txtCodigo.setText(String.format("%d",registro.getId()));      //popula os campos de texto com os dados do registro
			txtNome.setText(registro.getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
		
	}
	
	@FXML
	public void onButtonProximoAction(ActionEvent event) {
		if(posicaoAtual+1 < registros.size()) {
			posicaoAtual+=1 ; //incrementa a posição atual
			CentroDeCusto registro = registros.get(posicaoAtual);    //retorna o elemento da que corresponde a próxima posição nos registros
			txtCodigo.setText(String.format("%d",registro.getId())); //popula os campos de texto com os dados do registro
			txtNome.setText(registro.getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
	}
	
	@FXML
	public void onButtonAnteriorAction(ActionEvent event) {
		if(posicaoAtual-1 >= 0) {
			posicaoAtual-=1; //decrementa a posição atual
			CentroDeCusto registro = registros.get(posicaoAtual);    //retorna o elemento da que corresponde a posição anterior nos registros
			txtCodigo.setText(String.format("%d",registro.getId())); //popula os campos de texto com os dados do registro
			txtNome.setText(registro.getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
	}
	
	@FXML
	public void onButtonPrimeiroAction(ActionEvent event) {
		if(!registros.isEmpty()) {
			posicaoAtual = 0;
			CentroDeCusto registro = registros.get(posicaoAtual);    //retorna o primeiro elemento dos registros
			txtCodigo.setText(String.format("%d",registro.getId())); //popula os campos de texto com os dados do registro
			txtNome.setText(registro.getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
	}
	
	@FXML
	public void onButtonUltimoAction(ActionEvent event) {
		if(!registros.isEmpty()) {
			posicaoAtual = registros.size()-1;
			CentroDeCusto registro = registros.get(posicaoAtual);    //retorna o primeiro elemento dos registros
			txtCodigo.setText(String.format("%d",registro.getId())); //popula os campos de texto com os dados do registro
			txtNome.setText(registro.getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
	}
	
	@FXML
	public void onButtonNovoAction(ActionEvent event) {
		System.out.println("Clicou em Novo");
	}
	
	@FXML
	public void onButtonEditarAction(ActionEvent event) {
		System.out.println("Clicou em Editar");
	}
	
	@FXML
	public void onButtonExcluirAction(ActionEvent event) {
		System.out.println("Clicou em Excluir");
	}
	
	
	
}
