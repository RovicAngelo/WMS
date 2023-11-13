package com.lanuza.form;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;

public class Inventory {

	private JFrame frame;

	Inventory() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
		}catch(ClassNotFoundException exc){
			
		}catch(SQLException exc) {
			
		}
	}
	
	void table_load() {
		try {
			pst = con.prepareStatement("Select * from tblinventory");
			rs = pst.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setForeground(Color.WHITE);
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInventory.setBounds(428, 25, 263, 30);
		topPanel.add(lblInventory);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard back = new Dashboard();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		backBtn.setBounds(847, 503, 89, 29);
		frame.getContentPane().add(backBtn);
		
		JButton btnSupplier = new JButton("Supplier");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Supplier launch = new Supplier();
			}
		});
		btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupplier.setBounds(691, 503, 145, 29);
		frame.getContentPane().add(btnSupplier);
		
		JLabel lblInventoryStatus = new JLabel("Inventory");
		lblInventoryStatus.setForeground(Color.BLACK);
		lblInventoryStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInventoryStatus.setBounds(10, 77, 105, 30);
		frame.getContentPane().add(lblInventoryStatus);
		
		JButton btnCustomer_1 = new JButton("Customer");
		btnCustomer_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCustomer_1.setBounds(536, 503, 145, 29);
		frame.getContentPane().add(btnCustomer_1);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.white));
		panel1.setBounds(38, 118, 240, 170);
		panel1.setBackground(new Color(141, 241, 171));
		frame.getContentPane().add(panel1);
		
		JPanel panel2 = new JPanel();
		panel1.setBorder(new LineBorder(Color.white));
		panel2.setBounds(308, 118, 240, 170);
		panel2.setBackground(new Color(32, 178, 170));
		frame.getContentPane().add(panel2);
		
		JPanel panel3 = new JPanel();
		panel1.setBorder(new LineBorder(Color.white));
		panel3.setBounds(583, 118, 240, 170);
		panel3.setBackground(new Color(239, 122, 16));
		frame.getContentPane().add(panel3);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
