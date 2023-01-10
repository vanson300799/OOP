package domain;

public class King {
	Integer id;
	String ten;
	String nienHieu;
	String mieuHieu;
	String thuyHieu;	
	String tenHuy;
	String theThu;
	String triVi;
	public King(Integer id,String ten,String nienHieu,String mieuHieu, String thuyHieu,String tenHuy,String theThu,String triVi) {
		this.id = id;
		this.ten = ten;
		this.nienHieu = nienHieu;
		this.mieuHieu = mieuHieu;
		this.thuyHieu = thuyHieu;
		this.tenHuy = tenHuy;
		this.theThu = theThu;
		this.triVi = triVi;
	}
	public Integer getId() {
		return id;
	}
	public String getTen() {
		return ten;
	}
	public String getNienHieu() {
		return nienHieu;
	}
	public String getThuyHieu() {
		return thuyHieu;
	}
	public String getMieuHieu() {
		return mieuHieu;
	}
	public String getTenHuy() {
		return tenHuy;
	}
	public String getTheThu() {
		return theThu;
	}
	public String getTriVi() {
		return triVi;
	}
}
