package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.DAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChiSoDienView extends JFrame implements ActionListener{
	DAO dbDao = new DAO();
	Connection connection =  dbDao.DAOC();
	
	JPanel pnCS, pnCS_1, pnCS_2;
	JTextField tfMaKH, tfMaHD, tfMaCS, tfMonth,tfFirstIndex, tfLastIndex, tfType;
	JLabel lbType;
	
	public ChiSoDienView() {
		Container conCS= getContentPane();
		setSize(500,300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setVisible(true);
		
		pnCS= new JPanel();
		Border Border = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(Border, "Cập nhật chỉ số điện");
		pnCS.setBorder(titledBorder);
		pnCS.setLayout(new BorderLayout());
		
		
		pnCS_1 = new JPanel();
		pnCS_1.setLayout(new GridLayout(8, 2));

		JLabel lbMaHD= new JLabel("Mã Hoá đơn");
		pnCS_1.add(lbMaHD);
		tfMaHD= new JTextField(10);
		pnCS_1.add(tfMaHD);
		
		JLabel lbMaKH= new JLabel("Mã Khách hàng");
		pnCS_1.add(lbMaKH);
		tfMaKH= new JTextField(10);
		pnCS_1.add(tfMaKH);
		
		lbType= new JLabel("Loại điện");
		pnCS_1.add(lbType);
		tfType= new JTextField(10);
		pnCS_1.add(tfType);
		
		JLabel lbMaCS= new JLabel("Mã Chỉ số");
		pnCS_1.add(lbMaCS);
		tfMaCS= new JTextField(10);
		pnCS_1.add(tfMaCS);
		
		JLabel lbMonth= new JLabel("Ngày tháng");
		pnCS_1.add(lbMonth);
		tfMonth= new JTextField(10);
		pnCS_1.add(tfMonth);
		
		JLabel lbFirstIndex= new JLabel("Chỉ số đầu");
		pnCS_1.add(lbFirstIndex);
		 tfFirstIndex= new JTextField(10);
		pnCS_1.add(tfFirstIndex);
		JLabel lbLastIndex= new JLabel("Chỉ số cuối");
		pnCS_1.add(lbLastIndex);
		tfLastIndex= new JTextField(10);
		pnCS_1.add(tfLastIndex);
		
		pnCS_2= new JPanel();
		JButton btInsert= new JButton("Insert");
		pnCS_2.add(btInsert);
		JButton btUpdate= new JButton("Update");
		pnCS_2.add(btUpdate);
		
		btInsert.addActionListener(this);
		btUpdate.addActionListener(this);
		
		pnCS.add(pnCS_1,BorderLayout.CENTER);
		pnCS.add(pnCS_2, BorderLayout.SOUTH);
		add(pnCS);
	}
	public void Insert() {
		String maKH, maHD, maCS, month;
		int firstIndex, lastIndex,type;
		try {
			maKH= tfMaKH.getText();
			maHD= tfMaHD.getText();
			maCS= tfMaCS.getText();
			month= tfMonth.getText();
			type= Integer.parseInt(tfType.getText());
			firstIndex= Integer.parseInt(tfFirstIndex.getText());
			lastIndex= Integer.parseInt(tfLastIndex.getText());
			
			
			Statement statement= connection.createStatement();
			
			String sql_1= "insert into chiso values('"+maCS+"','"+month+"',"+firstIndex+","+lastIndex+
														",'"+maKH+"')";
			int check=0;
			if(statement.executeUpdate(sql_1)== 0) {
				JOptionPane.showMessageDialog(null, "Insert into ChiSo failed");
				check=1;
			}
			String sql_2="insert into hoadon(mahd, makh, loaidien) values('"+maHD+"','"+maKH+"',"+type+")";
	
			if(statement.executeUpdate(sql_2)==0) {
				JOptionPane.showMessageDialog(null, "Insert into Hoadon failed");
				check=1;
			}
			if(check==0) {
				JOptionPane.showMessageDialog(null, "Insert thanh cong");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void update() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Insert")) {
			Insert();
		}
	}
}
