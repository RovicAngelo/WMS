package com.lanuza.form;

import javax.swing.*;
import java.sql.*;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Customer {

	private JFrame frame;
	private JTable table;
	private JTextField nametxt;

	Customer() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
		}catch(ClassNotFoundException exc){
			
		}catch(SQLException exc) {
			
		}
	}
	
	void table_load() {
		try {
			pst = con.prepareStatement("Select * from tblcustomer");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 149, 384, 209);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(43, 74, 51, 33);
		panel.add(lblName);
		
		nametxt = new JTextField();
		nametxt.setBounds(123, 78, 203, 28);
		panel.add(nametxt);
		nametxt.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					  		
					  		String name;
					  		name = nametxt.getText();
							
							try {
								pst = con.prepareStatement("insert into tblcustomer(Name)values(?)");
								pst.setString(1, name); 
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Record added");
								table_load();						
								nametxt.setText("");
								nametxt.requestFocus();
								
							}catch(SQLException el) {
								el.printStackTrace();
							}
					  	}
					  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(42, 129, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(237, 129, 89, 29);
		panel.add(btnClear);
		
		JButton delBtn = new JButton("Delete");
		delBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		delBtn.setBounds(237, 169, 89, 29);
		panel.add(delBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		updateBtn.setBounds(42, 169, 89, 29);
		panel.add(updateBtn);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Manage Customer");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(399, 25, 263, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Inventory back = new Inventory();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(854, 503, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCustomerList = new JLabel("Customer List");
		lblCustomerList.setForeground(new Color(0, 0, 0));
		lblCustomerList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerList.setBounds(656, 118, 134, 30);
		frame.getContentPane().add(lblCustomerList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(505, 159, 429, 307);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				nametxt.setText(model.getValueAt(Myindex, 1).toString());							
			}
		});
		scrollPane.setViewportView(table);
		frame.setVisible(true);
	}
}
