package domain;

public class DiTich {
	Integer id;
	String ten;
	String hinhAnh;
	String diaDiem;
	String hangMuc;	
	String ghiChu;
	
	public DiTich(Integer id,String ten,String hinhAnh,String diaDiem, String hangMuc,String ghiChu) {
		this.id = id;
		this.ten = ten;
		this.hinhAnh = hinhAnh;
		this.diaDiem = diaDiem;
		this.hangMuc = hangMuc;
		this.ghiChu = ghiChu;
	}
	public Integer getId() {
		return id;
	}
	public String getTen() {
		return ten;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public String getHangMuc() {
		return hangMuc;
	}
	public String getGhiChu() {
		return ghiChu;
	}
}
