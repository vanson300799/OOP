package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.LeHoiService;
import service.Interface.IDataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.DiTich;
import domain.LeHoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class LeHoiSceneController implements Initializable{
	private Parent root;
	private Scene sceneRoot;
	private Stage stage;
	private IDataService<LeHoi> dataService; 
	@FXML
	private TextField searchField;
	@FXML
	private TableView<LeHoi> leHoiTable;
	@FXML
	private TableColumn<LeHoi, Integer> idColumn;
	@FXML
	private TableColumn<LeHoi, String> ngayColumn;
	@FXML
	private TableColumn<LeHoi, String> viTriColumn;
	@FXML
	private TableColumn<LeHoi, String> tenColumn;
	@FXML
	private TableColumn<LeHoi, String> toChucLanDauColumn;
	@FXML
	private TableColumn<LeHoi, String> ghiChuColumn;
	ObservableList<LeHoi> leHoiObservableList = FXCollections.observableArrayList();
	
	public LeHoiSceneController() throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		sceneRoot = new Scene(root);
		dataService = new LeHoiService();
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		List<LeHoi> leHois;
		try {
			leHois = dataService.getData();
			leHoiObservableList.addAll(leHois);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Add value into table
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
		viTriColumn.setCellValueFactory(new PropertyValueFactory<>("viTri"));
		tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
		toChucLanDauColumn.setCellValueFactory(new PropertyValueFactory<>("toChucLanDau"));
		ghiChuColumn.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
		
		leHoiTable.setItems(leHoiObservableList);
		
		//Add search into table
		FilteredList<LeHoi> filterData = new FilteredList<>(leHoiObservableList, b -> true);
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filterData.setPredicate(leHoi -> {
				if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(leHoi.getViTri().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(leHoi.getNgay().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(leHoi.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(leHoi.getToChucLanDau().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else if(leHoi.getGhiChu().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		SortedList<LeHoi> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(leHoiTable.comparatorProperty());
		leHoiTable.setItems(sortedData);
	}
	// Event Listener on Button.onAction
	@FXML
	public void backToHome(ActionEvent event) {
		stage = (Stage) searchField.getScene().getWindow();
		stage.setScene(sceneRoot);
		stage.setTitle("Trang chủ");
	}
}
