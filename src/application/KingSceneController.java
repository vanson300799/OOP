package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.King;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.KingService;

public class KingSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private KingService getDataService; 
	@FXML
	private AnchorPane searchField;
	@FXML
	private TableView<King> kingTable;
	@FXML
	private TableColumn<King, Integer> idColumn;
	@FXML
	private TableColumn<King, String> tenColumn;
	@FXML
	private TableColumn<King, String> mieuhieuColumn;
	@FXML
	private TableColumn<King, String> thuyhieuColumn;
	@FXML
	private TableColumn<King, String> nienhieuColumn;
	@FXML
	private TableColumn<King, String> tenhuyColumn;
	@FXML
	private TableColumn<King, String> thethuColumn;
	@FXML
	private TableColumn<King, String> triviColumn;
	
	ObservableList<King> kingObservableList = FXCollections.observableArrayList();
	
	public KingSceneController() throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		sceneRoot = new Scene(root);
		getDataService = new KingService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<King> kings;
		try {
			kings = getDataService.getDataKings();
			kingObservableList.addAll(kings);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
		mieuhieuColumn.setCellValueFactory(new PropertyValueFactory<>("mieuHieu"));
		thuyhieuColumn.setCellValueFactory(new PropertyValueFactory<>("thuyHieu"));
		nienhieuColumn.setCellValueFactory(new PropertyValueFactory<>("nienHieu"));
		tenhuyColumn.setCellValueFactory(new PropertyValueFactory<>("tenHuy"));
		thethuColumn.setCellValueFactory(new PropertyValueFactory<>("theThu"));
		triviColumn.setCellValueFactory(new PropertyValueFactory<>("triVi"));
		
		kingTable.setItems(kingObservableList);
	}
	@FXML
	public void searchData(ActionEvent event) {
	}
	@FXML
	public void backToHome(ActionEvent event) throws IOException {
		stage = (Stage) searchField.getScene().getWindow();
		stage.setScene(sceneRoot);
		stage.setTitle("Trang chủ");
	}
}
