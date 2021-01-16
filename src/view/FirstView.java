package view;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
public class FirstView extends JFrame {


	ImageIcon imgageEVN = new ImageIcon("D:\\evn.png");
	JLabel lbTitleJLabel= new JLabel("Hệ thống thanh toán tiền điện");
	JPanel pnPage1= new JPanel();
	JButton btDKKH = new JButton("Khách hàng");
	JButton btTraCuu = new JButton("Tra cứu hoá đơn");
	JButton btCapnhat = new JButton("Nhập chỉ số điện ");
	
	
	public FirstView() {

		Border border = BorderFactory.createLineBorder(Color.black);
		lbTitleJLabel.setIcon(imgageEVN);
		lbTitleJLabel.setBounds(160,0,700,120);
		lbTitleJLabel.setFont(new Font(null, Font.BOLD, 24));//set kieu, co chu
		lbTitleJLabel.setBackground(Color.white);
		lbTitleJLabel.setOpaque(true);
		lbTitleJLabel.setBorder(border);
		Container con = getContentPane();
		
		setLayout(null);
		
		setSize(1000,700 );
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		con.add(lbTitleJLabel);
		con.add(btDKKH);
		btDKKH.setBounds(175,350, 150, 50);
		con.add(btTraCuu);
		btTraCuu.setBounds(415, 350, 150, 50);
		con.add(btCapnhat);
		btCapnhat.setBounds(650, 350, 150, 50);
		
		
		btDKKH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new KhachHangView();
				
				
			}
		});
		btTraCuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HoaDonView();
				
			}
		});
		btCapnhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChiSoDienView();
			}
		});
	}
}
