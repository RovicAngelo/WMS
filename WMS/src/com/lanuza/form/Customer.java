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
import javax.swing.border.EtchedBorder;

public class Customer {

	private JFrame frame;
	private JTable table;
	private JTextField txtName;

	Customer() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;
	private JTextField txtBRGY;
	private JTextField txtCity;
	private JTextField txtProvince;

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
		panel.setBounds(21, 129, 293, 240);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(21, 24, 51, 33);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(90, 28, 182, 28);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					  		
					  		String name;
					  		name = txtName.getText();
							
							try {
								pst = con.prepareStatement("insert into tblcustomer(Name)values(?)");
								pst.setString(1, name); 
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Record added");
								table_load();						
								txtName.setText("");
								txtName.requestFocus();
								
							}catch(SQLException el) {
								el.printStackTrace();
							}
					  	}
					  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(90, 200, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(183, 200, 89, 29);
		panel.add(btnClear);
		
		JLabel lblBrgy = new JLabel("Brgy");
		lblBrgy.setForeground(Color.BLACK);
		lblBrgy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBrgy.setBounds(21, 63, 51, 33);
		panel.add(lblBrgy);
		
		txtBRGY = new JTextField();
		txtBRGY.setColumns(10);
		txtBRGY.setBounds(90, 67, 182, 28);
		panel.add(txtBRGY);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(90, 106, 182, 28);
		panel.add(txtCity);
		
		txtProvince = new JTextField();
		txtProvince.setColumns(10);
		txtProvince.setBounds(90, 145, 182, 28);
		panel.add(txtProvince);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCity.setBounds(21, 102, 40, 33);
		panel.add(lblCity);
		
		JLabel lblProvince = new JLabel("Province");
		lblProvince.setForeground(Color.BLACK);
		lblProvince.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProvince.setBounds(10, 141, 66, 33);
		panel.add(lblProvince);
		
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
		btnBack.setBounds(31, 505, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCustomerList = new JLabel("Customer List");
		lblCustomerList.setForeground(new Color(0, 0, 0));
		lblCustomerList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerList.setBounds(596, 128, 134, 30);
		frame.getContentPane().add(lblCustomerList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 159, 601, 335);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				txtName.setText(model.getValueAt(Myindex, 1).toString());							
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 380, 293, 114);
		frame.getContentPane().add(panel_1);
		
		JLabel lblId = new JLabel("ProductID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(10, 31, 76, 33);
		panel_1.add(lblId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(102, 31, 168, 29);
		panel_1.add(textField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(88, 71, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(181, 71, 89, 29);
		panel_1.add(btnDelete);
		frame.setVisible(true);
	}
}
