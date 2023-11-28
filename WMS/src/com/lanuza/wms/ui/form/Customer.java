package com.lanuza.wms.ui.form;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

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
	private JTextField txtCustomerId;
	private JTextField txtBrgy;
	private JTextField txtMunicipality;
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
		frame.setSize(1200,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 139, 311, 252);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(29, 24, 51, 33);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(90, 28, 192, 28);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  		if(txtName.getText().isEmpty() || txtBrgy.getText().isEmpty() || txtMunicipality.getText().isEmpty() || txtProvince.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
			  		}else {
					  		String name, brgy, municipality, province;
					  		name = txtName.getText();
					  		brgy = txtBrgy.getText();
					  		municipality = txtMunicipality.getText();
					  		province = txtProvince.getText();
					  									
							try {
								pst = con.prepareStatement("insert into tblcustomer(Name, Brgy, Municipality, Province)values(?,?,?,?)");
								pst.setString(1, name); 
								pst.setString(2, brgy);
								pst.setString(3, municipality);
								pst.setString(4, province);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Record added");
								table_load();						
								txtName.setText("");
								txtBrgy.setText("");
								txtMunicipality.setText("");
								txtProvince.setText("");
								txtName.requestFocus();
								
							}catch(SQLException el) {
								el.printStackTrace();
							}
					  	 }
						}	
					  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(94, 200, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		  		txtName.setText("");
		  		txtBrgy.setText("");
		  		txtMunicipality.setText("");
		  		txtProvince.setText("");
		  		txtName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(193, 200, 89, 29);
		panel.add(btnClear);
		
		JLabel lblBrgy = new JLabel("Brgy");
		lblBrgy.setForeground(Color.BLACK);
		lblBrgy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBrgy.setBounds(31, 63, 51, 33);
		panel.add(lblBrgy);
		
		txtBrgy = new JTextField();
		txtBrgy.setColumns(10);
		txtBrgy.setBounds(90, 71, 192, 28);
		panel.add(txtBrgy);
		
		txtMunicipality = new JTextField();
		txtMunicipality.setColumns(10);
		txtMunicipality.setBounds(109, 110, 173, 28);
		panel.add(txtMunicipality);
		
		txtProvince = new JTextField();
		txtProvince.setColumns(10);
		txtProvince.setBounds(109, 149, 173, 28);
		panel.add(txtProvince);
		
		JLabel lblCity = new JLabel("Municipality");
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCity.setBounds(10, 107, 98, 33);
		panel.add(lblCity);
		
		JLabel lblProvince = new JLabel("Province");
		lblProvince.setForeground(Color.BLACK);
		lblProvince.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProvince.setBounds(20, 145, 66, 33);
		panel.add(lblProvince);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1184, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Manage Customer");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(399, 25, 263, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1184, 18);
		frame.getContentPane().add(bottomPanel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Stock back = new Stock();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(31, 582, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCustomerList = new JLabel("Customer List");
		lblCustomerList.setForeground(new Color(0, 0, 0));
		lblCustomerList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerList.setBounds(612, 156, 134, 30);
		frame.getContentPane().add(lblCustomerList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 187, 607, 361);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				String id = table.getModel().getValueAt(Myindex,0).toString();	
				
				txtCustomerId.setText(id);					
				txtName.setText(model.getValueAt(Myindex, 1).toString());
				txtBrgy.setText(model.getValueAt(Myindex, 2).toString());
				txtMunicipality.setText(model.getValueAt(Myindex, 3).toString());
				txtProvince.setText(model.getValueAt(Myindex, 4).toString());				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 423, 311, 125);
		frame.getContentPane().add(panel_1);
		
		JLabel lblId = new JLabel("ProductID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(10, 31, 76, 33);
		panel_1.add(lblId);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(102, 31, 188, 29);
		panel_1.add(txtCustomerId);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(txtName.getText().isEmpty() || txtBrgy.getText().isEmpty() || txtMunicipality.getText().isEmpty() || txtProvince.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
		  		}else {
		  			try {
		  				
		  				DefaultTableModel model = (DefaultTableModel)table.getModel();
		  				
		  				String name, brgy, municipality, province,id; 
		  				name = txtName.getText();
		  				brgy = txtBrgy.getText();
		  				municipality = txtMunicipality.getText();
		  				province = txtProvince.getText();
		  				id = txtCustomerId.getText();
						
						pst = con.prepareStatement("update tblcustomer set Name=?,Brgy=?,Municipality=?,Province=? where CustomerID = ?");
						pst.setString(1, name);
						pst.setString(2, brgy);
						pst.setString(3, municipality);
						pst.setString(4, province);
						pst.setString(5, id);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Record Updated");
						table_load();	
						txtName.setText("");
						txtBrgy.setText("");
						txtMunicipality.setText("");
						txtProvince.setText("");
						txtCustomerId.setText("");
						txtName.requestFocus();			  							  						  							  				
		  				
		  			}catch(SQLException ex) {
		  				ex.printStackTrace();
		  			}
		  		}				  		
		  	}
		  });
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(102, 71, 89, 29);
		panel_1.add(btnUpdate);		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(txtName.getText().isEmpty() || txtBrgy.getText().isEmpty() || txtMunicipality.getText().isEmpty() || txtProvince.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a customer to be deleted");
		  		}else {

	
				try {
					
					String id = txtCustomerId.getText();
					pst = con.prepareStatement("delete from tblcustomer where CustomerID=?");
					pst.setString(1, id);
					pst.executeUpdate();
					
					table_load();
					JOptionPane.showMessageDialog(null,"Record Successfully Deleted ");			
												
					txtName.setText("");
					txtBrgy.setText("");
					txtMunicipality.setText("");
					txtProvince.setText("");
					txtCustomerId.setText("");
					txtName.requestFocus();
									
					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
		  	  }
		  	}
		  });
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(201, 71, 89, 29);
		panel_1.add(btnDelete);
		frame.setVisible(true);
	}
}
