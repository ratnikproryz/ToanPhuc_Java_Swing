package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.DAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
	
public class HoaDonView extends JFrame implements ActionListener{
	JLabel lbMaKH,lbMaKH2, lbmonth, lbTenKH, lbMaHD, lbFirstIndex, lbLastIndex, lbThue, lbTotal,lbNum; 
	JTextField tfMaKH,tfMaKH2, tfMonth, tfTenKH, tfMaHD, tfFirstIndex, tfLastIndex, tfThue, tfTotal,tfNum, tfDonGia, tfTempCost;
	JButton btSearch, btInHD;
	DAO dbDao = new DAO();
	Connection connection =  dbDao.DAOC();
	
	public HoaDonView() {
		
		setLayout(new BorderLayout());// split the containenr into 2 part
		setSize(700,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JPanel pnHD_1 = new JPanel();// include jlable, jbutton and textfield
		
		Border Border_1 = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_1 = BorderFactory.createTitledBorder(Border_1, "Nhập thông tin");
		pnHD_1.setBorder(titledBorder_1);
		pnHD_1.setLayout(new GridLayout(2, 1));
		
		JPanel pnHd_1_1 = new JPanel();
		pnHd_1_1.setLayout(new GridLayout(3, 2));
		
		lbMaKH= new JLabel("Mã khách hàng");
		pnHd_1_1.add(lbMaKH);
		tfMaKH= new JTextField(10);
		pnHd_1_1.add(tfMaKH);
		
		lbmonth= new JLabel("Ngày tháng");
		pnHd_1_1.add(lbmonth);
		tfMonth= new JTextField(10);
		pnHd_1_1.add(tfMonth);
		
		JPanel pnHD_1_2 = new JPanel();
		pnHD_1_2.setLayout(new FlowLayout());
		
		btSearch= new JButton("Search");
		pnHD_1_2.add(btSearch);
		pnHD_1.add(pnHd_1_1);
		pnHD_1.add(pnHD_1_2);
		add(pnHD_1,BorderLayout.WEST);
		
		JLabel pnHD_2= new JLabel();
		Border Border_2 = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_2 = BorderFactory.createTitledBorder(Border_2, "Hoá đơn chi tiết");
		pnHD_2.setBorder(titledBorder_2);
		pnHD_2.setLayout(new GridLayout(2, 1));
		
		JLabel pnHD_2_1= new JLabel();
		pnHD_2_1.setLayout(new GridLayout(10, 2));
		
		lbMaHD= new JLabel("Mã hoá đơn");
		pnHD_2_1.add(lbMaHD);
		tfMaHD= new JTextField(10);
		pnHD_2_1.add(tfMaHD);
		
		lbMaKH2= new JLabel("Mã Khách hàng");
		pnHD_2_1.add(lbMaKH2);
		tfMaKH2= new JTextField(10);
		pnHD_2_1.add(tfMaKH2);
		lbTenKH= new JLabel("Tên Khách hàng");
		pnHD_2_1.add(lbTenKH);
		tfTenKH= new JTextField(10);
		pnHD_2_1.add(tfTenKH);
		
		lbFirstIndex= new JLabel("Chỉ số đầu");
		pnHD_2_1.add(lbFirstIndex);
		tfFirstIndex= new JTextField(10);
		pnHD_2_1.add(tfFirstIndex);
		lbLastIndex= new JLabel("Chỉ số cuối");
		pnHD_2_1.add(lbLastIndex);
		tfLastIndex= new JTextField(10);
		pnHD_2_1.add(tfLastIndex);
//		
		lbNum= new JLabel("Lượng điện tiêu thụ");
		pnHD_2_1.add(lbNum);
		tfNum= new JTextField(10);
		pnHD_2_1.add(tfNum);
		
		JLabel lbDonGia= new JLabel("Đơn giá");
		pnHD_2_1.add(lbDonGia);
		tfDonGia= new JTextField(10);
		pnHD_2_1.add(tfDonGia);
		
		JLabel lbTempCost= new JLabel("Thành tiền");
		pnHD_2_1.add(lbTempCost);
		tfTempCost= new JTextField(10);

		pnHD_2_1.add(tfTempCost);
		
		lbThue= new JLabel("Thuế GTGT");
		pnHD_2_1.add(lbThue);
		tfThue= new JTextField("10%");

		pnHD_2_1.add(tfThue);
		
		lbTotal= new JLabel("Tổng cộng");
		pnHD_2_1.add(lbTotal);
		tfTotal= new JTextField(10);

		pnHD_2_1.add(tfTotal);
		JPanel pnHD_2_2 = new JPanel();
		pnHD_2_2.setLayout(new FlowLayout());
		btInHD= new JButton("Get bill");
		pnHD_2_2.add(btInHD);
		
		pnHD_2.add(pnHD_2_1);
		pnHD_2.add(pnHD_2_2);
		add(pnHD_2, BorderLayout.CENTER);
		
		btSearch.addActionListener(this);
		btInHD.addActionListener(this);

	}
	public void Search() {
		//nhap vao ma khach hang, ngaythang de truy xuat hoa don
		String maKH, month, tenKH, maHD=null;
		maKH= tfMaKH.getText();
		month= tfMonth.getText();
		int firstNum=0, lastNum=0, finalNum=0;
		float donGia=0,tempcost, thue=0, total=0;
		try {
			String sql="{call proc_hoadon (?,?)}";
			CallableStatement cs= connection.prepareCall(sql);
			cs.setString(1, maKH);
			cs.setString(2, month);
			
			ResultSet resultSet= cs.executeQuery();
			int i=1;
			
			while(resultSet.next()) {
				tfMaHD.setText(resultSet.getString(i));
				maHD=tfMaHD.getText();
				i++;
				tfMaKH2.setText(resultSet.getString(i));
				i++;
				tfTenKH.setText(resultSet.getString(i));
				i++;
				tfFirstIndex.setText(resultSet.getString(i));
				i++;
				tfLastIndex.setText(resultSet.getString(i));
				i++;
				// lay loai dien (gia dinh:0 ; kinh doanh:1)
				
				if(resultSet.getString(i).equals("0")) {
					donGia= 3000.0f;
					tfDonGia.setText(donGia+" VND (Gia đình)");
				}
				else {
					donGia= 3500.0f;//
					tfDonGia.setText(donGia+" VND (Kinh doanh)");
				}
				
			}
			
			firstNum=Integer.parseInt(tfFirstIndex.getText());
			lastNum= Integer.parseInt(tfLastIndex.getText());
			finalNum=lastNum-firstNum;
			tfNum.setText(String.valueOf(finalNum));// luong dien tieu thu
			// tinh tien dien
			tempcost= donGia*finalNum;
			tfTempCost.setText(String.valueOf(tempcost));
			thue= tempcost*0.1f; //thue GTGT 10%
			tfThue.setText(String.valueOf(thue+" (10%)"));
			total= tempcost+thue;
			tfTotal.setText(total+" VND");
			
			//insert luong dien , tng tien vao csdl
			Statement statement= connection.createStatement();
			sql="update hoadon set luongdien= "+finalNum+",tongtien= "+ total+" where mahd='"+maHD+"' and makh='"+maKH+"'";
			statement.executeUpdate(sql);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
//	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Search")) {
			Search();
		}
	}

	
		
	
	
}
