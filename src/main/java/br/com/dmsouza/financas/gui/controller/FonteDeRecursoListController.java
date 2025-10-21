package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.model.FonteDeRecurso;
import br.com.dmsouza.financas.model.dao.FonteDeRecursoDao;
import br.com.dmsouza.financas.model.dao.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class FonteDeRecursoListController implements Initializable{
	
	FonteDeRecursoDao dao = DaoFactory.getFonteDeRecursoDao();
	private ObservableList<FonteDeRecurso> obsList;
	
	@FXML private TableView<FonteDeRecurso> tableViewFonteDeRecurso;
	@FXML private TableColumn<FonteDeRecurso, Integer> tableColumnCodigo;
	@FXML private TableColumn<FonteDeRecurso, String> tableColumnNome;
	@FXML private TableColumn<FonteDeRecurso, Double> tableColumnSaldo;
	
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		updateTableView();
	}

	private void initializeNodes() {
		//trecho usado para indicar com quais dados serão populados as células da tabela
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<FonteDeRecurso,Integer>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<FonteDeRecurso,String>("nome"));
		tableColumnSaldo.setCellValueFactory(new PropertyValueFactory<FonteDeRecurso,Double>("saldo"));
	
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		tableColumnSaldo.setCellFactory(new Callback<TableColumn<FonteDeRecurso, Double>, TableCell<FonteDeRecurso, Double>>() {
		    @Override
		    public TableCell<FonteDeRecurso, Double> call(TableColumn<FonteDeRecurso, Double> column) {
		        return new TableCell<FonteDeRecurso, Double>() {
		            @Override
		            protected void updateItem(Double item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty || item == null) {
		                    setText(null);
		                } else {
		                    setText(currencyFormat.format(item)); // Ex: R$ 1.234,56
		                }
		            }
		        };
		    }
		});
	}

	public void updateTableView() {
		List<FonteDeRecurso> list = dao.buscarTodos();
		obsList = FXCollections.observableArrayList(list);
		tableViewFonteDeRecurso.setItems(obsList);
	}	

}
