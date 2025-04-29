package br.com.dmsouza.financas.gui.controller;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import br.com.dmsouza.financas.model.CentroDeCusto;
import br.com.dmsouza.financas.model.dao.CentroDeCustoDao;
import br.com.dmsouza.financas.util.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CentroDeCustoController implements Initializable{
	
	private CentroDeCustoDao dao = new CentroDeCustoDao(JPAUtil.getEntityManager());
	private ObservableList<CentroDeCusto> obsList;
	
	@FXML private TableView<CentroDeCusto> tableViewCentroDeCusto;
	@FXML private TableColumn<CentroDeCusto, Integer> tableColumnCodigo;
	@FXML private TableColumn<CentroDeCusto, String> tableColumnNome;
	@FXML private TableColumn<CentroDeCusto, Double> tableColumnSaldo;
	@FXML private Button buttonNovo;
	
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		//trecho usado para indicar com quais dados serão populados as células da tabela
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<CentroDeCusto,Integer>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<CentroDeCusto,String>("nome"));
		tableColumnSaldo.setCellValueFactory(new PropertyValueFactory<CentroDeCusto,Double>("saldo"));
	}

	public void updateTableView() {
		List<CentroDeCusto> list = dao.buscarTodos();
		Iterator<CentroDeCusto> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getNome());
		}
		obsList = FXCollections.observableArrayList(list);
		tableViewCentroDeCusto.setItems(obsList);
	}
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {

	}
	

}
