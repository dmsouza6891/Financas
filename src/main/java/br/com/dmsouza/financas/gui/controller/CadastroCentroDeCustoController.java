package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.model.CentroDeCusto;
import br.com.dmsouza.financas.model.dao.CentroDeCustoDao;
import br.com.dmsouza.financas.util.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	private List<CentroDeCusto> registros;
	
	public void initialize(URL location, ResourceBundle resources) {
		updateView();
	}
	
	public void updateView() {
		registros = dao.buscarTodos();
		Iterator<CentroDeCusto> iterator = registros.iterator();
		if(iterator.hasNext()) {
			CentroDeCusto  registro = iterator.next();
			txtCodigo.setText(String.format("%d",registro.getId()));
			txtNome.setText(iterator.next().getNome());
			txtSaldo.setText(String.format("%.2f", registro.getSaldo()));
		}
		
	}
	
	@FXML
	public void onButtonProximoAction(ActionEvent event) {
		System.out.println("Clicou em Proximo");
	}
	
	@FXML
	public void onButtonAnteriorAction(ActionEvent event) {
		System.out.println("Clicou em Anterior");
	}
	
	@FXML
	public void onButtonPrimeiroAction(ActionEvent event) {
		System.out.println("Clicou em Primeiro");
	}
	
	@FXML
	public void onButtonUltimoAction(ActionEvent event) {
		System.out.println("Clicou em Ultimo");
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
