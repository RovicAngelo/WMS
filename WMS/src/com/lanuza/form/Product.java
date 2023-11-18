package com.lanuza.form;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class Product {

	private JFrame frame;
	private JTextField txtCode,txtDescription,txtPrice;
	private JComboBox supplierCombox;
	private JTable table;

	Product() {
		initialize();
		Connect();
		table_load();
		getSupplier();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs = null;
	Statement st= null;
	private JTextField txtId;
	
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
			pst = con.prepareStatement("Select * from tblproduct");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getSupplier() {
		try {		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select Name from phildrinksdb.tblsupplier";
			rs = st.executeQuery(Query);
			while(rs.next()) {
					String supplierName = rs.getString("Name");
					supplierCombox.addItem(supplierName);	
			}
			
		}catch(Exception e) {

		}
				
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1184, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel lblProductSection = new JLabel("Manage Product");
		lblProductSection.setForeground(Color.WHITE);
		lblProductSection.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductSection.setBounds(399, 25, 148, 30);
		topPanel.add(lblProductSection);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 541, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 112, 303, 242);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProduct = new JLabel("Description");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProduct.setBounds(8, 73, 88, 25);
		panel.add(lblProduct);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCode.setColumns(10);
		txtCode.setBounds(99, 32, 182, 29);
		panel.add(txtCode);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCode.setBounds(25, 33, 50, 25);
		panel.add(lblCode);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrice.setBounds(20, 113, 50, 25);
		panel.add(lblPrice);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDescription.setColumns(10);
		txtDescription.setBounds(99, 72, 182, 29);
		panel.add(txtDescription);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(99, 112, 182, 29);
		panel.add(txtPrice);
		
		JButton btnAdd = new JButton("Add");
		 btnAdd.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		if(txtCode.getText().isEmpty() || txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
			  		}else {
			  		String description,supplier;
			  		int code,price;
			  		code = Integer.parseInt(txtCode.getText());
			  		description = txtDescription.getText();
			  		price =  Integer.parseInt(txtPrice.getText());
			  		supplier = supplierCombox.getSelectedItem().toString();
					
					try {
						pst = con.prepareStatement("insert into tblproduct(Code,Description,Price,Supplier)values(?,?,?,?)");
						pst.setInt(1,code);
						pst.setString(2, description);
						pst.setInt(3, price);
						pst.setString(4, supplier);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record added");
						table_load();
						txtCode.setText("");
						txtDescription.setText("");
						txtPrice.setText("");			
						txtCode.requestFocus();
						
					}catch(SQLException el) {
						el.printStackTrace();
					}
			  	  }
			  	}
			  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(99, 202, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		txtCode.setText("");
		  		txtDescription.setText("");
		  		txtPrice.setText("");
		  		txtCode.requestFocus();
		  	}
		  });
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(192, 202, 89, 29);
		panel.add(btnClear);
		
		JLabel lblSupplierId = new JLabel("Supplier");
		lblSupplierId.setForeground(Color.BLACK);
		lblSupplierId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSupplierId.setBounds(10, 149, 95, 33);
		panel.add(lblSupplierId);
		
		supplierCombox = new JComboBox();
		supplierCombox.setMaximumRowCount(2);
		supplierCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		supplierCombox.setEditable(true);
		supplierCombox.setBounds(99, 152, 182, 28);
		panel.add(supplierCombox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(331, 158, 626, 321);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				String id = table.getModel().getValueAt(Myindex,0).toString();	
				
				txtId.setText(id);					
				txtCode.setText(model.getValueAt(Myindex, 1).toString());
				txtDescription.setText(model.getValueAt(Myindex, 2).toString());
				txtPrice.setText(model.getValueAt(Myindex, 3).toString());	
				supplierCombox.setSelectedItem(model.getValueAt(Myindex, 4).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductList = new JLabel("Product List");
		lblProductList.setForeground(Color.BLACK);
		lblProductList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductList.setBounds(587, 123, 114, 30);
		frame.getContentPane().add(lblProductList);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard back = new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(20, 490, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(20, 365, 293, 114);
		frame.getContentPane().add(panel_1);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(102, 31, 168, 29);
		panel_1.add(txtId);
		
		JButton btnUpdate = new JButton("Update");
		 btnUpdate.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		if(txtCode.getText().isEmpty() || txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
			  		}else {
			  			try {
			  				
			  				DefaultTableModel model = (DefaultTableModel)table.getModel();
			  				
			  				String  description, supplier; 
			  				int code, price, id;
			  				code = Integer.parseInt(txtCode.getText());
			  				description = txtDescription.getText();
			  				price = Integer.parseInt(txtPrice.getText());
			  				supplier = supplierCombox.getSelectedItem().toString();
			  				id = Integer.parseInt(txtId.getText());
							
							pst = con.prepareStatement("update tblproduct set Code=?,Description=?,Price=?,Supplier=? where ProductID = ?");
							pst.setInt(1, code);
							pst.setString(2, description);
							pst.setInt(3, price);
							pst.setString(4, supplier);
							pst.setInt(5, id);
							pst.executeUpdate();
							
							JOptionPane.showMessageDialog(null,"Record Updated");
							table_load();	
							txtCode.setText("");
							txtDescription.setText("");
							txtPrice.setText("");
							txtId.setText("");
							txtCode.requestFocus();			  							  						  							  				
			  				
			  			}catch(SQLException ex) {
			  				ex.printStackTrace();
			  			}
			  		}				  		
			  	}
			  });
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(88, 71, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(txtCode.getText().isEmpty() || txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a product to be deleted");
		  		}else {

	
				try {
					
					String id = txtId.getText();
					pst = con.prepareStatement("delete from tblproduct where ProductID=?");
					pst.setString(1, id);
					pst.executeUpdate();
					
					table_load();
					JOptionPane.showMessageDialog(null,"Record Successfully Deleted ");			
												
					txtCode.setText("");
					txtDescription.setText("");
					txtPrice.setText("");
					txtId.setText("");
					txtCode.requestFocus();
									
					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
		  	  }
		  	}
		  });
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(181, 71, 89, 29);
		panel_1.add(btnDelete);			
		
		JLabel lblProductId = new JLabel("ProductID");
		lblProductId.setForeground(Color.BLACK);
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductId.setBounds(10, 27, 82, 33);
		panel_1.add(lblProductId);
		
		int[] code = {};
	}
}
