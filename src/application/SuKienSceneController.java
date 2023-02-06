package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.SuKienService;
import service.Interface.IDataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.LeHoi;
import domain.SuKien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class SuKienSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private IDataService<SuKien> dataService; 
	@FXML
	private TextField searchField;
	@FXML
	private TableView<SuKien> suKienTable;
	@FXML
	private TableColumn<SuKien, Integer> idColumn;
	@FXML
	private TableColumn<SuKien, String> suKienColumn;
	@FXML
	private TableColumn<SuKien, String> thoiGianColumn;
	
	ObservableList<SuKien> suKienObservableList = FXCollections.observableArrayList();
	
	public SuKienSceneController() throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		sceneRoot = new Scene(root);
		dataService = new SuKienService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<SuKien> suKiens;
		try {
			suKiens = dataService.getData();
			suKienObservableList.addAll(suKiens);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Add value into table
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		suKienColumn.setCellValueFactory(new PropertyValueFactory<>("suKien"));
		thoiGianColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));		
		
		suKienTable.setItems(suKienObservableList);
		
		//Add search into table
		FilteredList<SuKien> filterData = new FilteredList<>(suKienObservableList, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filterData.setPredicate(suKien -> {
				if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(suKien.getSuKien().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(suKien.getThoiGian().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		SortedList<SuKien> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(suKienTable.comparatorProperty());
		suKienTable.setItems(sortedData);
	}
	// Event Listener on Button.onAction
	@FXML
	public void backToHome(ActionEvent event) {
		stage = (Stage) searchField.getScene().getWindow();
		stage.setScene(sceneRoot);
		stage.setTitle("Trang chủ");
	}
	
	@FXML
    void getDetail(MouseEvent event) throws IOException {
    	
    	Integer index = suKienTable.getSelectionModel().getSelectedIndex();
    	if(index <= -1) {
    		return;
    	}
    	else {
    		Integer id = idColumn.getCellData(index);
    		String name = suKienColumn.getCellData(index);
    		openScene(id,name);
    	}

    }
    
    void openScene(int id, String name) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SuKienDetailScene.fxml"));
	    root = (Parent)fxmlLoader.load();

	    SuKienDetailSceneController suKienDetailSceneController = fxmlLoader.getController();
	    suKienDetailSceneController.setData(id);
	    
	    Stage stage = new Stage();
	    stage.setTitle(name);
	    stage.setScene(new Scene(root));  
	    stage.show();
    }
	
}
