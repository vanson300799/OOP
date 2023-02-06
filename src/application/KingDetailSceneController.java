package application;

import java.io.FileNotFoundException;

import domain.King;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.KingService;
import service.Interface.IDataService;

public class KingDetailSceneController{
	
    @FXML
    private TextField mieuHieu;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nienHieu;

    @FXML
    private TextField tenHuy;

    @FXML
    private TextField theThu;

    @FXML
    private TextField thuyHieu;

    @FXML
    private TextField triVi;
    
	private IDataService<King> dataService = new KingService();

	public void setDefault() {
		nameField.setFocusTraversable(false);
		mieuHieu.setFocusTraversable(false);
		nienHieu.setFocusTraversable(false);
		tenHuy.setFocusTraversable(false);
		theThu.setFocusTraversable(false);
		thuyHieu.setFocusTraversable(false);
		triVi.setFocusTraversable(false);
		
		nameField.setEditable(false);
		mieuHieu.setEditable(false);
		nienHieu.setEditable(false);
		tenHuy.setEditable(false);
		theThu.setEditable(false);
		thuyHieu.setEditable(false);
		triVi.setEditable(false);
	}
	public void setData(int id) throws FileNotFoundException {
		setDefault();
		King data = dataService.getByID(id);
		nameField.setText(data.getTen());
		mieuHieu.setText(data.getMieuHieu());
		nienHieu.setText(data.getNienHieu());
		tenHuy.setText(data.getTenHuy());
		theThu.setText(data.getTheThu());
		thuyHieu.setText(data.getThuyHieu());
		triVi.setText(data.getTriVi());

	}

}
