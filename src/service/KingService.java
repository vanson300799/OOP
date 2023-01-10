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

import domain.King;

public class KingService {
	public void UploadData() throws IOException{
		String link = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		Document doc = Jsoup.connect(link).timeout(15000).get();
		List<Element> listElement = doc.select("tr");
		List<King> kings = new ArrayList<King>();
		int j = 1;
		for (Element element : listElement) {
			Element ele = element.select(" td:nth-child(2)").first();
			Element ele1 = element.select(" td:nth-child(3)").first();
			Element ele2 = element.select(" td:nth-child(4)").first();
			Element ele3 = element.select(" td:nth-child(5)").first();
			Element ele4 = element.select(" td:nth-child(6)").first();
			Element ele5 = element.select(" td:nth-child(7)").first();
			Element ele6 = element.select(" td:nth-child(8)").first();

			if(ele != null && ele1 != null && ele2 != null && ele3 != null && ele4 != null && ele5 != null && ele6 != null) {
				if(ele.text().trim() != "") {
					Integer id = j;
					String ten = ele.text().replaceAll("\\[[^]]*]", "");
					String mieuHieu = ele1.text().replaceAll("\\[[^]]*]", "");
					String thuyHieu = ele2.text().replaceAll("\\[[^]]*]", "");
					String nienHieu = ele3.text().replaceAll("\\[[^]]*]", "");
					String tenHuy = ele4.text().replaceAll("\\[[^]]*]", "");
					String theThu = ele5.text().replaceAll("\\[[^]]*]", "");
					String triVi = ele6.text().replaceAll("\\[[^]]*]", "");
					King king = new King(id,ten,nienHieu,mieuHieu,thuyHieu,tenHuy,theThu,triVi);
					kings.add(king);
					j ++;
				}
			}
		}
		String data = new Gson().toJson(kings);
		try(FileWriter file = new FileWriter("king.json")) {
			file.write(data);
			file.flush();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<King> getDataKings() throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("king.json");) {
			Type type = new TypeToken<ArrayList<King>>(){}.getType();
			List<King> kings = gson.fromJson(data, type);
            return kings;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
