package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

import domain.King;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.KingService;
import service.Interface.IDataService;

public class KingSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private IDataService<King> dataService; 
	@FXML
	private TextField searchField;
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
		dataService = new KingService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<King> kings;
		try {
			kings = dataService.getData();
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
	
		FilteredList<King> filterData = new FilteredList<>(kingObservableList, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filterData.setPredicate(king -> {
				if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(king.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getNienHieu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getThuyHieu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getMieuHieu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getTenHuy().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getTheThu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(king.getTriVi().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		SortedList<King> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(kingTable.comparatorProperty());
		kingTable.setItems(sortedData);
	}

	@FXML
	public void backToHome(ActionEvent event) throws IOException {
		stage = (Stage) searchField.getScene().getWindow();
		stage.setScene(sceneRoot);
		stage.setTitle("Trang chủ");
	}
	

    @FXML
    void getDetail(MouseEvent event) throws IOException {
    	
    	Integer index = kingTable.getSelectionModel().getSelectedIndex();
    	if(index <= -1) {
    		return;
    	}
    	else {
    		Integer id = idColumn.getCellData(index);
    		String name = tenColumn.getCellData(index);
    		openScene(id,name);
    	}

    }
    
    void openScene(int id, String name) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KingDetailScene.fxml"));
	    root = (Parent)fxmlLoader.load();

	    KingDetailSceneController kingDetailSceneController = fxmlLoader.getController();
	    kingDetailSceneController.setData(id);
	    
	    Stage stage = new Stage();
	    stage.setTitle(name);
	    stage.setScene(new Scene(root));  
	    stage.show();
    }
}

