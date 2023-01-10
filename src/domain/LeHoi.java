package domain;

public class LeHoi {
	Integer id;
	String ngay;
	String viTri;
	String ten;
	String toChucLanDau;	
	String ghiChu;
	
	public LeHoi(Integer id,String ngay,String viTri,String ten, String toChucLanDau,String ghiChu) {
		this.id = id;
		this.ngay = ngay;
		this.viTri = viTri;
		this.ten = ten;
		this.toChucLanDau = toChucLanDau;
		this.ghiChu = ghiChu;
	}
	public Integer getId() {
		return id;
	}
	public String getTen() {
		return ten;
	}
	public String getNgay() {
		return ngay;
	}
	public String getToChucLanDau() {
		return toChucLanDau;
	}
	public String getViTri() {
		return viTri;
	}
	public String getGhiChu() {
		return ghiChu;
	}
}
