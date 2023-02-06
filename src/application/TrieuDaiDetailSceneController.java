package application;

import java.io.FileNotFoundException;

import domain.King;
import domain.TrieuDai;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.KingService;
import service.TrieuDaiService;
import service.Interface.IDataService;

public class TrieuDaiDetailSceneController{

    @FXML
    private TextField nameField;

    @FXML
    private TextField thoiGian;
    
	private IDataService<TrieuDai> dataService = new TrieuDaiService();

	public void setDefault() {
		nameField.setFocusTraversable(false);
		thoiGian.setFocusTraversable(false);
		
		nameField.setEditable(false);
		thoiGian.setEditable(false);
	}
	public void setData(int id) throws FileNotFoundException {
		setDefault();
		TrieuDai data = dataService.getByID(id);
		nameField.setText(data.getTen());
		thoiGian.setText(data.getThoiGian());
	}

}
