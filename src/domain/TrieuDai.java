package domain;

public class TrieuDai {
	Integer id;
	String thoiGian;
	String ten;
	
	public TrieuDai(Integer id, String thoiGian, String ten) {
		this.id = id;
		this.thoiGian = thoiGian;
		this.ten = ten;
	}

	public Integer getId() {
		return id;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public String getTen() {
		return ten;
	}
}
