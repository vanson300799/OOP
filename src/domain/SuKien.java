package domain;

public class SuKien {
	Integer id;
	String thoiGian;
	String suKien;
	
	public SuKien(Integer id, String thoiGian, String suKien) {
		this.id = id;
		this.thoiGian = thoiGian;
		this.suKien = suKien;
	}

	public Integer getId() {
		return id;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public String getSuKien() {
		return suKien;
	}
}
