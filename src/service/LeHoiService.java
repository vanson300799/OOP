package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.DiTich;
import domain.King;
import domain.LeHoi;
import service.Interface.IDataService;

public class LeHoiService implements IDataService<LeHoi>{
	public void UploadData() throws IOException{
		String link = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
		Document doc = Jsoup.connect(link).timeout(15000).get();
		List<Element> listElement = doc.select("tr");
		List<LeHoi> leHois = new ArrayList<LeHoi>();
		int j = 1;
		for (Element element : listElement) {
			Element ele = element.select(" td:nth-child(1)").first();
			Element ele1 = element.select(" td:nth-child(2)").first();
			Element ele2 = element.select(" td:nth-child(3)").first();
			Element ele3 = element.select(" td:nth-child(4)").first();
			Element ele4 = element.select(" td:nth-child(5)").first();

			if(ele != null && ele1 != null && ele2 != null && ele3 != null && ele4 != null) {
					Integer id = j;
					String ngay = ele.text().replaceAll("\\[[^]]*]", "");
					String viTri = ele1.text().replaceAll("\\[[^]]*]", "");
					String ten = ele2.text().replaceAll("\\[[^]]*]", "");
					String lanDau = ele3.text().replaceAll("\\[[^]]*]", "");
					String ghiChu = ele4.text().replaceAll("\\[[^]]*]", "");
					LeHoi leHoi = new LeHoi(id,ngay,viTri,ten,lanDau,ghiChu);
					leHois.add(leHoi);
					j ++;
			}
		}
		
		String data = new Gson().toJson(leHois);
		try(FileWriter file = new FileWriter("lehoi.json")) {
			file.write(data);
			file.flush();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<LeHoi> getData() throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("lehoi.json");) {
			Type type = new TypeToken<ArrayList<LeHoi>>(){}.getType();
			List<LeHoi> leHois = gson.fromJson(data, type);
            return leHois;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public LeHoi getByID(int id) throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("lehoi.json");) {
			Type type = new TypeToken<ArrayList<LeHoi>>(){}.getType();
			List<LeHoi> leHois = gson.fromJson(data, type);
			LeHoi result = leHois.stream().filter(e->e.getId() == id).findFirst().get();
            return result;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
