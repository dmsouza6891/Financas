package br.com.dmsouza.financas.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.application.Main;
import br.com.dmsouza.financas.gui.util.Util;
import br.com.dmsouza.financas.model.CentroDeCusto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable{
	
	@FXML private MenuItem menuItemCentroDeCusto;
	@FXML private MenuItem menuItemExtratoMensal;
	@FXML private MenuItem menuItemVersao;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void onMenuItemExtratoMensalAction() {
		System.out.println("Clicou em Extrato Mensal");
	}
	
	@FXML
	public void onMenuItemVersaoAction() {
		System.out.println("Clicou em Versão");
	}

	@FXML
	public void onMenuItemCentroDeCustoAction(ActionEvent event) {
		Stage parentStage = (Stage) Main.getMainScene().getWindow();
		CentroDeCusto obj = new CentroDeCusto();
		createDialogFormCentroDeCusto(obj, "/views/CadastroCentroDeCusto.fxml", parentStage);	
		/*
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CentroDeCustoView.fxml"));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //referência o primeiro elemento da Scene que no caso em questão é um VBox
			
			Node mainMenu = mainVBox.getChildren().get(0); //guarda uma referência para os filhos do VBox que no caso em questão é um Menu
			mainVBox.getChildren().clear(); //limpa os elementos do VBox. Senão for feito a cada clique vai ser gerada uma nova view
			mainVBox.getChildren().add(mainMenu); //adiciona ao VBox o Menu
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			CentroDeCustoController controller = loader.getController(); //pega o controlador do arquivo fxml passado no parâmetro
			controller.updateTableView(); //atualiza a TableView
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	private void createDialogFormCentroDeCusto(CentroDeCusto obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			CadastroCentroDeCustoController controller = loader.getController();
			controller.updateView();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastro de Centro de Custo");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
