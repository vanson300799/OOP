package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.TrieuDaiService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.TrieuDai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class TrieuDaiSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private TrieuDaiService trieuDaiService; 
	@FXML
	private TextField searchField;
	@FXML
	private TableView<TrieuDai> trieuDaiTable;
	@FXML
	private TableColumn<TrieuDai, Integer> idColumn;
	@FXML
	private TableColumn<TrieuDai, String> tenColumn;
	@FXML
	private TableColumn<TrieuDai, String> thoiGianColumn;
	
	ObservableList<TrieuDai> trieuDaiObservableList = FXCollections.observableArrayList();
	
	public TrieuDaiSceneController() throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		sceneRoot = new Scene(root);
		trieuDaiService = new TrieuDaiService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<TrieuDai> trieuDais;
		try {
			trieuDais = trieuDaiService.getData();
			trieuDaiObservableList.addAll(trieuDais);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Add value into table
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
		thoiGianColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));		
		
		trieuDaiTable.setItems(trieuDaiObservableList);
		
		//Add search into table
		FilteredList<TrieuDai> filterData = new FilteredList<>(trieuDaiObservableList, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filterData.setPredicate(trieuDai -> {
				if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(trieuDai.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(trieuDai.getThoiGian().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		SortedList<TrieuDai> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(trieuDaiTable.comparatorProperty());
		trieuDaiTable.setItems(sortedData);
	}
	// Event Listener on Button.onAction
	@FXML
	public void backToHome(ActionEvent event) {
		stage = (Stage) searchField.getScene().getWindow();
		stage.setScene(sceneRoot);
		stage.setTitle("Trang chủ");
	}
}
