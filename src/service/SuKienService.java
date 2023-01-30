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

import domain.LeHoi;
import domain.SuKien;
import service.Interface.IDataService;

public class SuKienService implements IDataService<SuKien>{
	public void UploadData() throws IOException{
		String link = "https://quynhonland.vn/tom-tat-cac-moc-su-kien-lich-su-viet-nam/";
		Document doc = Jsoup.connect(link).timeout(15000).get();
		List<Element> listElement = doc.select("p");
		List<SuKien> sukiens = new ArrayList<SuKien>();

		int j = 1;
		String suKien, thoiGian;
		for(int i = 2; i <= 116 ; i++) {
			Element element = listElement.get(i);
			String parts[] = element.text().split("\\•", 2);
			if(parts.length > 1) {
				suKien = parts[1];
			} else suKien = parts[0];
			parts = suKien.split("\\,", 2);
			int count = parts[0].replaceAll("[0-9]", "").length();
			if(count < 25 && parts.length > 1) {
				thoiGian = parts[0];
				suKien = parts[1];
			} else {
				parts = suKien.split("\\.", 2);
				count = parts[0].replaceAll("[0-9]", "").length();
				if(count < 15) {
					thoiGian = parts[0];
					suKien = parts[1];
				} else {
					thoiGian = "???";
				}
			}
			suKien = suKien.trim();
			suKien = suKien.substring(0, 1).toUpperCase() + suKien.substring(1);
			SuKien sukien = new SuKien(j, thoiGian, suKien);
			sukiens.add(sukien);
			j++;
		}
		
		String data = new Gson().toJson(sukiens);
		try(FileWriter file = new FileWriter("sukien.json")) {
			file.write(data);
			file.flush();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<SuKien> getData() throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("sukien.json");) {
			Type type = new TypeToken<ArrayList<SuKien>>(){}.getType();
			List<SuKien> sukiens = gson.fromJson(data, type);
            return sukiens;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
