package br.com.dmsouza.financas.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
			ScrollPane ScrollPane = loader.load();
		
			mainScene = new Scene(ScrollPane);
			System.out.println("***************************************Início***************************************");
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Controle Financeiro");
			primaryStage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

}
