package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import domain.DiTich;
import domain.King;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.DiTichService;
import service.Interface.IDataService;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class DiTichSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private IDataService<DiTich> dataService; 
	@FXML
	private TextField searchField;
	@FXML
	private TableView<DiTich> diTichTable;
	@FXML
	private TableColumn<DiTich, Integer> idColumn;
	@FXML
	private TableColumn<DiTich, String> tenColumn;
//	@FXML
//	private TableColumn<DiTich, ImageView> anhColumn;
	@FXML
	private TableColumn<DiTich, String> diaDiemColumn;
	@FXML
	private TableColumn<DiTich, String> hangMucColumn;
	@FXML
	private TableColumn<DiTich, String> ghiChuColumn;
	ObservableList<DiTich> diTichObservableList = FXCollections.observableArrayList();
	
	public DiTichSceneController() throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		sceneRoot = new Scene(root);
		dataService = new DiTichService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<DiTich> diTichs;
		try {
			diTichs = dataService.getData();
			diTichObservableList.addAll(diTichs);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Add value into table
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
//		anhColumn.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		diaDiemColumn.setCellValueFactory(new PropertyValueFactory<>("diaDiem"));
		hangMucColumn.setCellValueFactory(new PropertyValueFactory<>("hangMuc"));
		ghiChuColumn.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
		
		diTichTable.setItems(diTichObservableList);
		
		//Add search into table
		FilteredList<DiTich> filterData = new FilteredList<>(diTichObservableList, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filterData.setPredicate(diTich -> {
				if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(diTich.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(diTich.getDiaDiem().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(diTich.getGhiChu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(diTich.getHangMuc().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		SortedList<DiTich> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(diTichTable.comparatorProperty());
		diTichTable.setItems(sortedData);
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
		
		Integer index = diTichTable.getSelectionModel().getSelectedIndex();
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
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DiTichDetailScene.fxml"));
	    root = (Parent)fxmlLoader.load();
	
	    DiTichDetailSceneController diTichDetailSceneController = fxmlLoader.getController();
	    diTichDetailSceneController.setData(id);
	    
	    Stage stage = new Stage();
	    stage.setTitle(name);
	    stage.setScene(new Scene(root));  
	    stage.show();
	}
}
