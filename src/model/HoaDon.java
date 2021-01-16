package model;

public class HoaDon {
	private String MaHD;
	private int loaiDien;
	private int LuongDien;
	private float total;
	public HoaDon() {
		
	}
	public HoaDon(String maHD, int loaiDien, int luongDien, float total) {
		super();
		this.MaHD = maHD;
		this.loaiDien = loaiDien;
		this.LuongDien = luongDien;
		this.total = total;
	}
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public int getLoaiDien() {
		return loaiDien;
	}
	public void setLoaiDien(int loaiDien) {
		this.loaiDien = loaiDien;
	}
	public int getLuongDien() {
		return LuongDien;
	}
	public void setLuongDien(int luongDien) {
		LuongDien = luongDien;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
}
