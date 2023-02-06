package application;

import java.io.FileNotFoundException;

import domain.DiTich;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.DiTichService;
import service.Interface.IDataService;

public class DiTichDetailSceneController{
	
    @FXML
    private TextField diadiem;

    @FXML
    private TextField ditich;

    @FXML
    private TextField hangmuc;

    @FXML
    private TextField ghichu;
    
	private IDataService<DiTich> dataService = new DiTichService();

	public void setDefault() {
		diadiem.setFocusTraversable(false);
		ditich.setFocusTraversable(false);
		hangmuc.setFocusTraversable(false);
		ghichu.setFocusTraversable(false);
		
		ghichu.setEditable(false);
		hangmuc.setEditable(false);
		ditich.setEditable(false);
		diadiem.setEditable(false);
	}
	public void setData(int id) throws FileNotFoundException {
		setDefault();
		DiTich data = dataService.getByID(id);
		diadiem.setText(data.getDiaDiem());
		ditich.setText(data.getTen());
		hangmuc.setText(data.getHangMuc());
		ghichu.setText(data.getGhiChu());

	}

}
