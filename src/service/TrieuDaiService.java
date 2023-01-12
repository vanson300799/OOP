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

import domain.TrieuDai;

public class TrieuDaiService {
	public void UploadData() throws IOException{
		String link = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		Document doc = Jsoup.connect(link).timeout(15000).get();
		List<Element> listElement = doc.select("ul.sidebar-toc-list");
		List<TrieuDai> trieudais = new ArrayList<TrieuDai>();
		
		int j = 1;
		String thoiGian;
		String ten;
		for (Element element : listElement) {
			if(element.text() != "") {
				List<Element> thoiKy = element.select("li");
				for(Element ele : thoiKy) {
					Element span = ele.select("span").first();
					if(span != null) span.remove();
					String text = ele.text();
					if((text.indexOf("(") > -1)) {
						String parts[] = text.split("\\(", 2);
						thoiGian = parts[1].replace(")", "");
						thoiGian = thoiGian.replace("(", "");
						ten = parts[0];
					} else {
						thoiGian = "???";
						ten = text;
					}
					TrieuDai trieudai = new TrieuDai(j, thoiGian, ten);
					trieudais.add(trieudai);
					j++;
				}
			}
		}
		
		String data = new Gson().toJson(trieudais);
		try(FileWriter file = new FileWriter("trieudai.json")) {
			file.write(data);
			file.flush();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<TrieuDai> getData() throws FileNotFoundException{
		Gson gson = new Gson();
		try(FileReader data = new FileReader("trieudai.json");) {
			Type type = new TypeToken<ArrayList<TrieuDai>>(){}.getType();
			List<TrieuDai> trieudais = gson.fromJson(data, type);
            return trieudais;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
