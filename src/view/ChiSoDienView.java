package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.DAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class ChiSoDienView extends JFrame implements ActionListener, MouseListener{
	DAO dbDao = new DAO();
	Connection connection =  dbDao.DAOC();
	
	JPanel pnCS, pnCS_1, pnCS_2, pnTable;
	JTextField tfMaKH, tfMaHD, tfMaCS, tfMonth,tfFirstIndex, tfLastIndex, tfType;
	JLabel lbType;

	JScrollPane jcTable;
	DefaultTableModel defaultTableModel;
	JTable tbJTable;
	
	Vector vData= new Vector();
	Vector vTitle= new Vector();
	int column=0;
	int  selectedrow=0;
	
	public ChiSoDienView() {
		Container conCS= getContentPane();
		setSize(700,500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setVisible(true);
		
		pnCS= new JPanel();
		Border Border = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(Border, "Cập nhật chỉ số điện");
		pnCS.setBorder(titledBorder);
		pnCS.setLayout(new GridLayout(2,1));
		
		
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
		
		pnCS.add(pnCS_1);
		pnCS.add(pnCS_2);
		add(pnCS,BorderLayout.WEST);
		
		// pntabel chua  thong tin chi so dien
		
		pnTable= new JPanel();
		Border Border1 = BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(Border, "Thông tin chỉ số điện");
		pnTable.setBorder(titledBorder);
		pnTable.setLayout(new BorderLayout());;
		add(pnTable);
		
		reload();
		defaultTableModel= new DefaultTableModel(vData, vTitle);
		tbJTable=new JTable(defaultTableModel);
		jcTable= new JScrollPane(tbJTable);
		pnTable.add(jcTable);
		add(pnTable,BorderLayout.CENTER);
		
		
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
			String sql_2="insert into hoadon(mahd, makh, loaidien,status) values('"+maHD+"','"+maKH+"',"+type+",0)";
	
			if(statement.executeUpdate(sql_2)==0) {
				JOptionPane.showMessageDialog(null, "Insert into Hoadon failed");
				check=1;
			}
			if(check==0) {
				
				JOptionPane.showMessageDialog(null, "Insert thanh cong");
				Vector row= new Vector(column);
				row.add(maCS);
				row.add(month);
				row.add(firstIndex);
				row.add(lastIndex);
				row.add(maKH);
				vData.add(row);
				defaultTableModel.fireTableDataChanged();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void reload() {
		try {
			vData.clear();
			vTitle.clear();		
			Statement statement= connection.createStatement();
			ResultSet resultSet= statement.executeQuery("select * from chiso");
			ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
			column= resultSetMetaData.getColumnCount();
			vTitle=new Vector(column);
			
			for(int i=1; i<=column;i++) {
				vTitle.add(resultSetMetaData.getColumnLabel(i));
			}
			vData= new Vector();
			while(resultSet.next()) {
				Vector row= new Vector(column);
				for(int i=1; i<=column;i++) {
					row.add(resultSet.getString(i));					
				}
				vData.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void update() {
		
		try {
			// sua lai chuc nang update
			Vector csoVector= (Vector)vData.elementAt(selectedrow);
			System.out.println(csoVector.elementAt(3));
			String sql="update chiso set cs_dau='"+ csoVector.elementAt(2)+"',cs_cuoi='"+csoVector.elementAt(3)+"' where macs='"+csoVector.elementAt(0)
																		+"'and makh='"+csoVector.elementAt(4)+"'";
			Statement statement= connection.createStatement();
			if(statement.executeUpdate(sql)!=0) {
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
//				pnTable.def
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Insert")) {
			Insert();
		}
		else if(e.getActionCommand().equals("Update")) {
			update();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedrow= tbJTable.getSelectedRow();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

















