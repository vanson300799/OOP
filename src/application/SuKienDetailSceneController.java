package application;

import java.awt.TextArea;
import java.io.FileNotFoundException;

import domain.DiTich;
import domain.LeHoi;
import domain.SuKien;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.DiTichService;
import service.LeHoiService;
import service.SuKienService;
import service.Interface.IDataService;

public class SuKienDetailSceneController{
	
    @FXML
    private TextField thoiGian;
    
    @FXML
    private javafx.scene.control.TextArea suKien;
    
	private IDataService<SuKien> dataService = new SuKienService();

	public void setDefault() {
		suKien.setFocusTraversable(false);
		thoiGian.setFocusTraversable(false);
		suKien.setWrapText(true);
		
		thoiGian.setEditable(false);
		suKien.setEditable(false);
	}
	public void setData(int id) throws FileNotFoundException {
		setDefault();
		SuKien data = dataService.getByID(id);
		thoiGian.setText(data.getThoiGian());
		suKien.setText(data.getSuKien());

	}

}
