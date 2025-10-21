package br.com.dmsouza.financas.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.application.Main;
import br.com.dmsouza.financas.gui.util.ViewConstants;
import br.com.dmsouza.financas.model.FonteDeRecurso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable{
	
	@FXML private MenuItem menuItemFonteDeRecurso;
	@FXML private MenuItem menuItemExtratoMensal;
	@FXML private MenuItem menuItemVersao;
	@FXML private VBox containerTableView;	
	
	private FonteDeRecursoListController controllerFonteDeRecursoList;
	
	public void initialize(URL location, ResourceBundle resources) {
		loadFonteDeRecursoListView();
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
	public void onMenuItemFonteDeRecursoAction(ActionEvent event) {
		Stage parentStage = (Stage) Main.getMainScene().getWindow();
		FonteDeRecurso obj = new FonteDeRecurso();
		createDialogFormFonteDeRecurso(obj, ViewConstants.CADASTRO_FONTE_DE_RECURSO, parentStage);	
		loadFonteDeRecursoListView();
	}
	
	private void loadFonteDeRecursoListView() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewConstants.TABELA_DE_FONTE_DE_RECURSO));
	        VBox fonteDeRecursoListView = loader.load();

	        controllerFonteDeRecursoList = loader.getController();
	        containerTableView.getChildren().clear(); //limpa a VBox antes
	        containerTableView.getChildren().add(fonteDeRecursoListView);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void createDialogFormFonteDeRecurso(FonteDeRecurso obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastro de Fonte de Recurso");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			if(controllerFonteDeRecursoList != null) {
				controllerFonteDeRecursoList.updateTableView();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
