package application;

import java.io.FileNotFoundException;

import domain.DiTich;
import domain.LeHoi;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.DiTichService;
import service.LeHoiService;
import service.Interface.IDataService;

public class LeHoiDetailSceneController{
	
    @FXML
    private TextField nameField;

    @FXML
    private TextField ngayamlich;

    @FXML
    private TextField vitri;

    @FXML
    private TextField landautochuc;
    
    @FXML
    private TextField ghichu;
    
	private IDataService<LeHoi> dataService = new LeHoiService();

	public void setDefault() {
		nameField.setFocusTraversable(false);
		ngayamlich.setFocusTraversable(false);
		vitri.setFocusTraversable(false);
		ghichu.setFocusTraversable(false);
		landautochuc.setFocusTraversable(false);
		
		ghichu.setEditable(false);
		nameField.setEditable(false);
		ngayamlich.setEditable(false);
		vitri.setEditable(false);
		landautochuc.setEditable(false);
	}
	public void setData(int id) throws FileNotFoundException {
		setDefault();
		LeHoi data = dataService.getByID(id);
		nameField.setText(data.getTen());
		ngayamlich.setText(data.getNgay());
		vitri.setText(data.getViTri());
		ghichu.setText(data.getGhiChu());
		landautochuc.setText(data.getToChucLanDau());

	}

}
