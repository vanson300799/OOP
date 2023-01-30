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
import service.Interface.IDataService;

public class DiTichService implements IDataService<DiTich>{
	public void UploadData() throws IOException{
		String link = "https://vi.wikipedia.org/wiki/Di_t%C3%ADch_qu%E1%BB%91c_gia_%C4%91%E1%BA%B7c_bi%E1%BB%87t_(Vi%E1%BB%87t_Nam)";
		Document doc = Jsoup.connect(link).timeout(15000).get();
		List<Element> listElement = doc.select("tr");
		List<DiTich> diTichs = new ArrayList<DiTich>();
		int j = 1;
		for (Element element : listElement) {
			Element ele = element.select(" td:nth-child(1)").first();
			Element ele1 = element.select(" td:nth-child(2) img").first();
			Element ele2 = element.select(" td:nth-child(3)").first();
			Element ele3 = element.select(" td:nth-child(4)").first();
			Element ele4 = element.select(" td:nth-child(5)").first();

			if(ele != null && ele1 != null && ele2 != null && ele3 != null && ele4 != null) {
				if(ele.text().trim() != "") {
					Integer id = j;
					String ten = ele.text().replaceAll("\\[[^]]*]", "");
					String hinhAnh = "https:" + ele1.attr("src").replaceAll("\\[[^]]*]", "");
					String diaDiem = ele2.text().replaceAll("\\[[^]]*]", "");
					String hangMuc = ele3.text().replaceAll("\\[[^]]*]", "");
					String ghiChu = ele4.text().replaceAll("\\[[^]]*]", "");
					DiTich diTich = new DiTich(id,ten,hinhAnh,diaDiem,hangMuc,ghiChu);
					diTichs.add(diTich);
					System.out.println(ten);
					j ++;
				}
			}
		}
		
		String data = new Gson().toJson(diTichs);
		try(FileWriter file = new FileWriter("ditich.json")) {
			file.write(data);
			file.flush();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<DiTich> getData() throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("ditich.json");) {
			Type type = new TypeToken<ArrayList<DiTich>>(){}.getType();
			List<DiTich> diTichs = gson.fromJson(data, type);
            return diTichs;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
