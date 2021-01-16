package model;

public class ChiSoDien {
	private String maCSD;
	private String ngayThangString;
	private int firstNum;
	private int lastNum;
	public ChiSoDien() {
		
	}
	public ChiSoDien(String maCSD, String ngayThangString, int firstNum, int lastNum) {
		super();
		this.maCSD = maCSD;
		this.ngayThangString = ngayThangString;
		this.firstNum = firstNum;
		this.lastNum = lastNum;
	}
	public String getMaCSD() {
		return maCSD;
	}
	public void setMaCSD(String maCSD) {
		this.maCSD = maCSD;
	}
	public String getNgayThangString() {
		return ngayThangString;
	}
	public void setNgayThangString(String ngayThangString) {
		this.ngayThangString = ngayThangString;
	}
	public int getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
	
}
