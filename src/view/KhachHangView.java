package view;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

//import javax.jws.Oneway;
import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import controller.DAO;

public class KhachHangView extends JFrame implements ActionListener, MouseListener{
	DAO dbDao = new DAO();
	Connection connection =  dbDao.DAOC();

	JPanel pnKH_2;
	
	JLabel lbMaKH,lbTenkH, lbNumPhone, lbCMND, lbAddress, lbNgDK;
	JTextField tfMaKH, tfTenKH, tfNumPhone, tfCMND, tfAddress, tfNgDK;
	JButton btInsert, btUpdate, btDelete, btGetInfor,btView;
	Vector vData=  new Vector();
	Vector vTitle= new Vector();
	
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb;
	int col_num;
	int selectedRow=0;


	
	public KhachHangView() {
		Container conKH = getContentPane();
		setLayout(new BorderLayout());
		setSize(700,500);
		setLocationRelativeTo(null);
		setVisible(true);
	
		//create jpanel pnpk_1 to input information about customer
		
		JPanel pnKh_1= new JPanel();
		Border Border_1 = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_1 = BorderFactory.createTitledBorder(Border_1, "Khách hàng");
		pnKh_1.setBorder(titledBorder_1);
		pnKh_1.setLayout(new BorderLayout());
		//
		JPanel pnKH_1_1= new JPanel();
		pnKH_1_1.setLayout(new GridLayout(7,2));
		lbMaKH=new JLabel("Mã Khách hàng");
		pnKH_1_1.add(lbMaKH);
		tfMaKH= new JTextField(10);
		pnKH_1_1.add(tfMaKH);
		
		lbTenkH= new JLabel("Tên Khách hàng");
		pnKH_1_1.add(lbTenkH);
		tfTenKH= new JTextField(10);
		pnKH_1_1.add(tfTenKH);
		
		lbCMND= new JLabel("CMND");
		pnKH_1_1.add(lbCMND);
		tfCMND= new JTextField(10);
		pnKH_1_1.add(tfCMND);
		
		lbAddress= new JLabel("Địa chỉ");
		pnKH_1_1.add(lbAddress);
		tfAddress= new JTextField(10);
		pnKH_1_1.add(tfAddress);
		
		lbNumPhone= new JLabel("Số điện thoại");
		pnKH_1_1.add(lbNumPhone);
		tfNumPhone= new JTextField(10);
		pnKH_1_1.add(tfNumPhone);
		
		lbNgDK= new JLabel("Ngày sinh");
		pnKH_1_1.add(lbNgDK);
		tfNgDK= new JTextField(10);
		pnKH_1_1.add(tfNgDK);
		
		JLabel pnKH_1_2 = new JLabel();
		pnKH_1_2.setLayout(new FlowLayout());
		btInsert= new JButton("Insert");
		pnKH_1_2.add(btInsert);
		btUpdate= new JButton("Update");
		pnKH_1_2.add(btUpdate);
		btDelete= new JButton("Delete");
		pnKH_1_2.add(btDelete);
		btGetInfor= new JButton("Get Infor");
		pnKH_1_2.add(btGetInfor);
		JButton btSearch= new JButton("Search");
		pnKH_1_2.add(btSearch);

		btInsert.addActionListener(this);
		btDelete.addActionListener(this);
		btGetInfor.addActionListener(this);
		btUpdate.addActionListener(this);
		btSearch.addActionListener(this);
		
		pnKh_1.add(pnKH_1_1,BorderLayout.NORTH);
		pnKh_1.add(pnKH_1_2);
		add(pnKh_1,BorderLayout.WEST);
		
		//create jpanel pnkh_2 to show informaton about customer
		
		pnKH_2 = new JPanel();
		Border Border_2 = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder_2 = BorderFactory.createTitledBorder(Border_2, "Thông tin");
		pnKH_2.setBorder(titledBorder_2);
		pnKH_2.setLayout(new BorderLayout());
		add(pnKH_2);
		
//		
		reload();
		model= new DefaultTableModel(vData, vTitle);
		tb= new JTable(model);
		
		tb.addMouseListener(this);
		
		tableResult= new JScrollPane(tb);
		pnKH_2.add(tableResult);
		
//		
	}
	public void reload() {
		try {
			//delete data in vectors
			vTitle.clear();
			vData.clear();
			
			Statement smKH=  connection.createStatement();
			ResultSet rsKH= smKH.executeQuery("SELECT * FROM KHACHHANG");// query information from table KhachHang
			ResultSetMetaData  rsmKH= rsKH.getMetaData(); //Get information of ResultSet
			col_num= rsmKH.getColumnCount(); // get num col of table KhahchHang
			
			vTitle= new Vector(col_num);
			for(int i=1; i<= col_num; i++) {
				vTitle.add(rsmKH.getColumnLabel(i));
			}
			vData= new Vector();
			while (rsKH.next()) {
				Vector row= new Vector(col_num);
				for(int i=1; i<=col_num; i++) {
					row.add(rsKH.getString(i));
				}
				vData.add(row);
			}
			rsKH.close();
			smKH.close();

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void delete() {
		try {
			Vector khVector = (Vector)vData.elementAt(selectedRow); //get information of the row selected
			String sql= "Delete from KHACHHANG where MAKH ='"+ khVector.elementAt(0)+"'";
			Statement statement= connection.createStatement();
			statement.executeUpdate(sql);
			
			vData.remove(selectedRow);
			model.fireTableDataChanged();
			statement.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void Insert() {
		if(tfMaKH.getText().equals("") || tfTenKH.getText().equals("") || tfCMND.getText().equals("") || tfAddress.getText().equals("")
				||tfNgDK.getText().equals("") || tfNumPhone.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Nhập thiếu dữ liệu");
		}
		else {	
			try {
				String maKH, tenKH ,CMND, DiaChi, sdt, ngaySinh;
				maKH= tfMaKH.getText();
				tenKH= tfTenKH.getText();
				CMND= tfCMND.getText();
				DiaChi= tfAddress.getText();
				sdt= tfNumPhone.getText();
				ngaySinh= tfNgDK.getText();
				
				Statement statement= connection.createStatement();
				statement.executeUpdate("Insert into Khachhang(makh, tenkh, cmnd, diachi, sdt, ngaysinh) values ('"+maKH+"','"
						+tenKH+"', '"+CMND+"','"+DiaChi+"','"+sdt+"','"+ngaySinh+"')");
				Vector row= new Vector(col_num);
				row.add(maKH);
				row.add(tenKH);
				row.add(CMND);
				row.add(DiaChi);			
				row.add(sdt);
				row.add(ngaySinh);
//				
				vData.add(row);
				model.fireTableDataChanged();
				statement.close();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	public void GetInfor() {
		try {
			String maKH;
			maKH= tfMaKH.getText();
//			
			Statement statement = connection.createStatement();
			String sql= "Select * from Khachhang where makh= '"+maKH+"'";		
			ResultSet findResultSet = statement.executeQuery(sql);

			int i=1;
			int check=1;// kiem tra du lieu cua findresultset
			while(findResultSet.next()) {
				check=0;
				tfMaKH.setText(findResultSet.getString(i)); 
				i++;
				tfTenKH.setText(findResultSet.getString(i));
				i++;
				tfCMND.setText(findResultSet.getString(i));	
				i++;
				tfAddress.setText(findResultSet.getString(i));
				i++;
				tfNumPhone.setText(findResultSet.getString(i));
				i++;
				tfNgDK.setText(findResultSet.getString(i));
			}
			if(check==1)
				JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
			statement.close();
			findResultSet.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	public void search() {
		try {
			String maKH;
			maKH= tfMaKH.getText();
			int check=0;
			Statement statement = connection.createStatement();
			String sql= "Select * from Khachhang where diachi= '"+maKH+"'";		
			ResultSet findResultSet = statement.executeQuery(sql);
			ResultSetMetaData resultSetMetaData= findResultSet.getMetaData();
			int column= resultSetMetaData.getColumnCount();
			vData.clear();
			while(findResultSet.next()) {
				check=1;
				Vector rowVector= new Vector(column);
				for(int i=1; i<= column; i++) {
					rowVector.add(findResultSet.getString(i));					
				}
				vData.add(rowVector);
				model.fireTableDataChanged();
			}
			if(check== 0) {
				JOptionPane.showMessageDialog(null,"Không tìm thấy kết quả");
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void update() {
		String maKH, tenKH ,CMND, DiaChi, sdt, ngaySinh;
		maKH= tfMaKH.getText();
		tenKH= tfTenKH.getText();
		CMND= tfCMND.getText();
		DiaChi= tfAddress.getText();
		sdt= tfNumPhone.getText();
		ngaySinh= tfNgDK.getText();
		try {
			Statement statement = connection.createStatement();		
			String sql="update khachhang set makh ='"+maKH+"',tenkh='"+tenKH+"', cmnd='"+CMND+"',diachi='"+DiaChi+"', sdt='"+sdt+
					"', ngaysinh='"+ngaySinh+"' where makh='"+maKH+"'";
			statement.executeUpdate(sql);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Delete")) {
			delete();
		}
		else if(e.getActionCommand().equals("Insert")) {
			Insert();
		}
		else if(e.getActionCommand().equals("Get Infor")) {
			GetInfor();
		}
		else if(e.getActionCommand().equals("Update")) {
			update();
			this.dispose();
			new KhachHangView();
		}
		else if(e.getActionCommand().equals("Search")) {
			search();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		selectedRow= tb.getSelectedRow();
//		Vector vRowSelected
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

	
	