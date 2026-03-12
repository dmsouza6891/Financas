package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.gui.util.Alerts;
import br.com.dmsouza.financas.model.Competencia;
import br.com.dmsouza.financas.model.dao.CompetenciaDao;
import br.com.dmsouza.financas.model.dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroCompetenciaController implements Initializable {
	
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
	@FXML private TextField txtMes;   //exibe o mês da competência
	@FXML private TextField txtAno;  //exibe o ano da competência
	@FXML private ComboBox<String> comboBoxSituacao; //exibe a situação da competência
	                      
	CompetenciaDao dao = DaoFactory.getCompetenciaDao(); //objeto utilizado para acessar a base de dados
	private ArrayList<Competencia> registros; //utilizado para manusear os registros retornados da base de dados
	
	public void initialize(URL location, ResourceBundle resources) {
		updateView();
	}
	
	public void stop() {
		dao.em.close();
	}
	
	public void updateView() { //utilizado para deixar a janela de cadastro com as configurações padrão
		registros = new ArrayList<Competencia>(dao.buscarTodos()); 
		configuraModoExibicaoInicial(); 
		if(!registros.isEmpty()) { //se houver itens cadastrados configura a janela para exibi-los e insere bot�es de fluxo e controle
			habilitaModoFluxo();
			Competencia registro = registros.get(0);  //retorna o primeiro elemento dos registros
			fillTextFields(registro);  //utiliza método para popular os campos de texto com os dados do registro
		}	     
	}
	
	public void configuraModoExibicaoInicial() { //configura a view com os par�metros padr�es de exibi��o
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
		txtMes.setDisable(false);
		txtAno.setDisable(false);
		desabilitaModoFluxo();
		esvaziaTextFields();
		btNovo.setText("Gravar");
		btCancela.setVisible(true);
		btCancela.setDisable(false);
		btEditar.setDisable(true);
		btExcluir.setDisable(true);
	}
	
	public void configuraModoEdicao() { //configura elementos da view para a edicao dos dados
		txtMes.setDisable(false);
		txtAno.setDisable(false);
		desabilitaModoFluxo();
		btEditar.setText("Gravar");
		btCancela.setVisible(true);
		btCancela.setDisable(false);
		btNovo.setDisable(true);
		btExcluir.setDisable(true);
	}
	
	public void desabilitaTextFields() { 
		txtCodigo.setDisable(true);
		txtMes.setDisable(true);
		txtAno.setDisable(true);
		comboBoxSituacao.setDisable(true);
	}
	
	public void esvaziaTextFields() { 
		txtCodigo.setText("");
		txtMes.setText("");
		txtAno.setText("");
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
	
	public void fillTextFields(Competencia registro) {  //popula os campos da view com os dados do registro a ser exibido
		txtCodigo.setText(String.format("%d",registro.getId())); 
		txtMes.setText(String.format("%d",registro.getReferencia().getMonthValue()));
		txtAno.setText(String.format("%d",registro.getReferencia().getYear()));
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
	
	@FXML //atualiza a variável de posição e carrega a View com o �ltimo registro
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
				Competencia registro = new Competencia();
				
				try {
					int mes = Integer.parseInt(txtMes.getText());
					int ano = Integer.parseInt(txtAno.getText());
					YearMonth referencia = YearMonth.of(mes, ano);
					registro.setReferencia(referencia);
				}
				catch(NumberFormatException e) {
					Alerts.showAlert("Erro de Entrada-ParseException","Dados Inválidos", "Você precisa digitar um número válido", AlertType.WARNING);
				}
				
				if(txtMes.getText().isEmpty() || txtAno.getText().isEmpty()) { //utilizado para garantir que os campos obrigat�rios est�o preenchidos
				    Alerts.showAlert("Validação", "Campos obrigatórios", "Preencha todos os campos", AlertType.WARNING);
				    return;
				}
		
				if(dao.buscaCompetencia(registro.getReferencia())){ //caso de inserçãp de registro com referencia existente
					Alerts.showAlert("Erro de Entrada","Banco de Dados", "Já existe uma Comepetencia com essa referencia, insira outro período", AlertType.WARNING);
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
	public void onButtonEditarAction(ActionEvent event) { //configura as rotinas a serem realizadas quando o bot�o Editar � clicado
		
		switch(btEditar.getText()){ //controla os est�gios do bot�o 
			case "Editar": //habilita os campos para a altera��o dos dados 
				if(!txtCodigo.getText().isEmpty()){
					configuraModoEdicao();
				}
			break;
			case "Gravar": //operacionaliza a persist�ncia dos dados editados
				Competencia atual = dao.em.find(Competencia.class, Integer.parseInt(txtCodigo.getText())); //altera para estado Gerenciado
				
				if(txtMes.getText().isEmpty() || txtAno.getText().isEmpty()) { //utilizado para garantir que os campos obrigat�rios est�o preenchidos
				    Alerts.showAlert("Validação", "Campos obrigatórios", "Preencha todos os campos", AlertType.WARNING);
				    return;
				}
				
				try {
					int mes = Integer.parseInt(txtMes.getText());
					int ano = Integer.parseInt(txtAno.getText());
					YearMonth referencia = YearMonth.of(mes, ano);
					
					if(dao.buscaCompetencia(referencia)) { //caso de inserção de registro com nome existente
						Alerts.showAlert("Aviso","Banco de Dados", "Já existe um Fonte de Recurso com esse nome, insira outro nome", AlertType.WARNING);
					}
				    else {
				    	atual.setReferencia(referencia);
				    	dao.em.getTransaction().begin();
				    	dao.editar(atual);
				    	dao.em.getTransaction().commit();
				    	updateView();
				    }
				}
				catch(NumberFormatException e) {
					Alerts.showAlert("Erro de Entrada-ParseException","Dados Inválidos", "Você precisa digitar um número válido", AlertType.WARNING);
				}
			break;
		}
	}
	
	@FXML
	public void onButtonExcluirAction(ActionEvent event) {
		
		if(!registros.isEmpty()){
			Optional<ButtonType> result = Alerts.showConfirmation("Confirma��o", "Deseja realmente excluir esta fonte de recurso?");
			if (result.isPresent() && result.get() == ButtonType.OK) {
				dao.em.getTransaction().begin();
				dao.remover(dao.em.find(Competencia.class, Integer.parseInt(txtCodigo.getText())));
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
