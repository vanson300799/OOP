package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.DiTichService;
import service.KingService;
import service.LeHoiService;
import service.TrieuDaiService;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainSceneController {
	private DiTichService diTichService;
	private KingService kingService;
	private LeHoiService leHoiService;
	private TrieuDaiService trieuDaiService;
	public MainSceneController() {
		diTichService = new DiTichService();
		kingService   = new KingService(); 
		leHoiService  = new LeHoiService();
		trieuDaiService  = new TrieuDaiService();
	}
	// Event Listener on Button.onAction
	@FXML
	Button btnKing;
	@FXML
	public void submit(ActionEvent event) throws IOException {
		diTichService.UploadData();
		kingService.UploadData();
		leHoiService.UploadData();
		trieuDaiService.UploadData();
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToKingView(ActionEvent event) throws IOException {
		Parent kingScene = FXMLLoader.load(getClass().getResource("KingScene.fxml"));
		Scene scene = new Scene(kingScene);
		Stage stage = (Stage) btnKing.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Vua Việt Nam");
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToDiTichView(ActionEvent event) throws IOException {
		Parent diTichScene = FXMLLoader.load(getClass().getResource("DiTichScene.fxml"));
		Scene scene = new Scene(diTichScene);
		Stage stage = (Stage) btnKing.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Di tích lịch sử Viêt Nam");
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToLeHoiView(ActionEvent event) throws IOException {
		Parent leHoiScene = FXMLLoader.load(getClass().getResource("LeHoiScene.fxml"));
		Scene scene = new Scene(leHoiScene);
		Stage stage = (Stage) btnKing.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Lễ hội Viêt Nam");
	}
	@FXML
	public void goToTrieuDaiView(ActionEvent event) throws IOException {
		Parent trieuDaiScene = FXMLLoader.load(getClass().getResource("TrieuDaiScene.fxml"));
		Scene scene = new Scene(trieuDaiScene);
		Stage stage = (Stage) btnKing.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Triều đại Việt Nam");
	}
}
