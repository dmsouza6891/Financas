package br.com.dmsouza.financas.gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Util {
	
	//retorna o palco atual de uma determinada gui
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

}
