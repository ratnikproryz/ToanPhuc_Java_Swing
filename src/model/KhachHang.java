package model;
import controller.*;

import java.beans.VetoableChangeSupport;
import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class KhachHang {
	private String MaKH;
	private String TenKH;
	private String CMND;
	private String diaChi;
	private String sdt;
	private String ngaySinh;
	public KhachHang() {
		
	}
	public KhachHang(String maKH, String tenKH, String cMND, String diaChi, String sdt, String ngaySinh) {
		super();
		this.MaKH = maKH;
		this.TenKH = tenKH;
		this.CMND = cMND;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getTenKH() {
		return TenKH;
	}
	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
//	create a method query all information of table KhachHang
	
	
}
