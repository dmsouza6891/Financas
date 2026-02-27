package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.gui.util.Alerts;
import br.com.dmsouza.financas.model.FonteDeRecurso;
import br.com.dmsouza.financas.model.dao.FonteDeRecursoDao;
import br.com.dmsouza.financas.model.dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CadastroFonteDeRecursoController implements Initializable {
	
	//botões de controle da janela
	@FXML private Button btPrimeiro;
	@FXML private Button btAnterior;
	@FXML private Button btProximo;
	@FXML private Button btUltimo;
	@FXML private Button btNovo;
	@FXML private Button btEditar;
	@FXML private Button btCancela;
	@FXML private Button btExcluir;
	
	@FXML private TextField txtCodigo; //exibe o código de cadastro do registro
	@FXML private TextField txtNome;   //exibe o nome de cadastro do registro
	@FXML private TextField txtSaldo;  //exite o saldo do centro de custo do registro
	                        
	private static Locale linguagemPadrao = new Locale("pt", "BR"); //usado para tratar o padrão monetário do saldo exibido  

	FonteDeRecursoDao dao = DaoFactory.getFonteDeRecursoDao(); //objeto utilizado para acessar a base de dados
	private ArrayList<FonteDeRecurso> registros; //utilizado para manusear os registros retornados da base de dados
	
	public void initialize(URL location, ResourceBundle resources) {
		updateView();
	}
	
	public void stop() {
		dao.em.close();
	}
	
	public void updateView() { //utilizado para deixar a janela de cadastro com as configurações padrão
		registros = new ArrayList<FonteDeRecurso>(dao.buscarTodos()); 
		configuraModoExibicaoInicial(); 
		if(!registros.isEmpty()) { //se houver itens cadastrados configura a janela para exibi-los e insere botões de fluxo e controle
			habilitaModoFluxo();
			FonteDeRecurso registro = registros.get(0);  //retorna o primeiro elemento dos registros
			fillTextFields(registro);  //utiliza método para popular os campos de texto com os dados do registro
		}	     
	}
	
	public void configuraModoExibicaoInicial() { //configura a view com os parâmetros padrões de exibição
		esvaziaTextFields();
		btNovo.setText("Novo");
		btEditar.setText("Editar");
		btNovo.setDisable(false);
		btEditar.setDisable(true);
		btExcluir.setDisable(true);
		btCancela.setVisible(false);
		desabilitaModoFluxo();
		desabilitaTextFields(); 
	}
	
	public void configuraModoInsercao() { //configura elementos da view para a inserção dos dados
		txtNome.setDisable(false);
		txtSaldo.setDisable(false);
		desabilitaModoFluxo();
		esvaziaTextFields();
		btNovo.setText("Gravar");
		btCancela.setVisible(true);
		btCancela.setDisable(false);
		btEditar.setDisable(true);
		btExcluir.setDisable(true);
	}
	
	public void configuraModoEdicao() { //configura elementos da view para a edicao dos dados
		txtNome.setDisable(false);
		desabilitaModoFluxo();
		btEditar.setText("Gravar");
		btCancela.setVisible(true);
		btCancela.setDisable(false);
		btNovo.setDisable(true);
		btExcluir.setDisable(true);
	}
	
	public void desabilitaTextFields() { 
		txtCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtSaldo.setDisable(true);
	}
	
	public void esvaziaTextFields() { 
		txtCodigo.setText("");
		txtNome.setText("");
		txtSaldo.setText("");
	}
	
	public void habilitaModoFluxo() {
		btPrimeiro.setDisable(false);
		btProximo.setDisable(false);
		btAnterior.setDisable(false);
		btUltimo.setDisable(false);
		btEditar.setDisable(false);
		btExcluir.setDisable(false);
	}
	
	public void desabilitaModoFluxo() { 
		btPrimeiro.setDisable(true);
		btProximo.setDisable(true);
		btAnterior.setDisable(true);
		btUltimo.setDisable(true);
	}
	
	public void fillTextFields(FonteDeRecurso registro) {  //popula os campos da view com os dados do registro a ser exibido
		txtCodigo.setText(String.format("%d",registro.getId())); 
		txtNome.setText(registro.getNome());
		txtSaldo.setText(NumberFormat.getCurrencyInstance(linguagemPadrao).format(registro.getSaldo()));
	}
	
	private int getPosicaoAtual(){  //retorna o id CentrodeCusto correspondente ao Id atual do form
	    int idAtual = Integer.parseInt(txtCodigo.getText());
	    return registros.indexOf(dao.buscarPorId(idAtual));
	}
	
	@FXML //atualiza a variável de posição e carrega a View com o registro posterior
	public void onButtonProximoAction(ActionEvent event) {
		if(getPosicaoAtual() < registros.size()-1)
			fillTextFields(registros.get(getPosicaoAtual()+1));
	}
	
	@FXML //atualiza a variável de posição e carrega a View com o registro anterior
	public void onButtonAnteriorAction(ActionEvent event) {
		if(getPosicaoAtual() > 0)
			fillTextFields(registros.get(getPosicaoAtual()-1));
	}
	
	@FXML //atualiza a variável de posição e carrega a View com o primeiro registro
	public void onButtonPrimeiroAction(ActionEvent event) { 
		if(!registros.isEmpty()) {
			fillTextFields(registros.get(0));
		}
	}
	
	@FXML //atualiza a variável de posição e carrega a View com o último registro
	public void onButtonUltimoAction(ActionEvent event) { 
		if(!registros.isEmpty()) {
			fillTextFields(registros.get(registros.size()-1)); 
		}
	}
	
	@FXML
	public void onButtonNovoAction(ActionEvent event) { //configura as rotinas a serem realizadas quando o botão Novo é clicado
		
		switch(btNovo.getText()) {
			case "Novo":
				configuraModoInsercao();
			break;
			
			case "Gravar":
				FonteDeRecurso registro = new FonteDeRecurso();
				registro.setNome(txtNome.getText());
				
				try {
					NumberFormat numeroFormato = NumberFormat.getNumberInstance(linguagemPadrao);
					Number saldo = numeroFormato.parse(txtSaldo.getText());
					registro.setSaldo(saldo.doubleValue());
				}
				catch(NumberFormatException e) {
					Alerts.showAlert("Erro de Entrada-NumberFormat","Saldo inválido", "Você precisa digitar um número válido", AlertType.WARNING);
				}
				catch(ParseException e) {
					Alerts.showAlert("Erro de Entrada-ParseException","Saldo inválido", "Você precisa digitar um número válido", AlertType.WARNING);
				}
				
				if(txtNome.getText().isEmpty() || txtSaldo.getText().isEmpty()) { //utilizado para garantir que os campos obrigatórios estão preenchidos
				    Alerts.showAlert("Validação", "Campos obrigatórios", "Preencha todos os campos", AlertType.WARNING);
				    return;
				}
		
				if(dao.buscaNome(registro.getNome())) { //caso de inserção de registro com nome existente
					Alerts.showAlert("Erro de Entrada","Banco de Dados", "Já existe uma Fonte de Recurso com esse nome, insira outro nome", AlertType.WARNING);
				}
			    else {
			    	dao.em.getTransaction().begin();
			    	dao.cadastrar(registro);
			    	dao.em.getTransaction().commit();
			    	updateView();
			    }
			break;
		}
	}
	
	@FXML
	public void onButtonEditarAction(ActionEvent event) { //configura as rotinas a serem realizadas quando o botão Editar é clicado
		
		switch(btEditar.getText()){ //controla os estágios do botão 
			case "Editar": //habilita os campos para a alteração dos dados 
				if(!txtCodigo.getText().isEmpty()){
					configuraModoEdicao();
				}
			break;
			case "Gravar": //operacionaliza a persistência dos dados editados
				FonteDeRecurso atual = dao.em.find(FonteDeRecurso.class, Integer.parseInt(txtCodigo.getText())); //altera para estado Gerenciado
				
				if (txtNome.getText().isEmpty() || txtSaldo.getText().isEmpty()) {
				    Alerts.showAlert("Validação", "Campos obrigatórios", "Preencha todos os campos", AlertType.WARNING);
				    return;
				}
				
				if(dao.buscaNome(txtNome.getText())) { //caso de inserção de registro com nome existente
					Alerts.showAlert("Aviso","Banco de Dados", "Já existe um Fonte de Recurso com esse nome, insira outro nome", AlertType.WARNING);
				}
			    else {
			    	atual.setNome(txtNome.getText());
			    	dao.em.getTransaction().begin();
			    	dao.editar(atual);
			    	dao.em.getTransaction().commit();
			    	updateView();
			    }
			break;
		}
	}
	
	@FXML
	public void onButtonExcluirAction(ActionEvent event) {
		
		if(!registros.isEmpty()){
			Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Deseja realmente excluir esta fonte de recurso?");
			if (result.isPresent() && result.get() == ButtonType.OK) {
				dao.em.getTransaction().begin();
				dao.remover(dao.em.find(FonteDeRecurso.class, Integer.parseInt(txtCodigo.getText())));
				dao.em.getTransaction().commit();
			}
		}
		updateView();
	}
	
	@FXML
	public void onButtonCancelaAction(ActionEvent event) {
		updateView();
	}
	
}
