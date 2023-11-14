package com.lanuza.form;


import javax.swing.*;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;
import javax.swing.border.LineBorder;

public class Receiving {

	private JFrame frame;
	private JTextField txtQty,txtSearch;
	private JTable table;
	private JComboBox supplierIDCombox,productIDCombox;
	private JDateChooser expDateChooser;
	private JLabel Datelbl,lbltotalprice;
	
	Receiving() {
		initialize();
		Connect();
		table_load();
		getDateToday();
		getSupplier();
		getProductCode();
	}
	
	Connection con = null;
	PreparedStatement pst;
	ResultSet rs= null;
	Statement st= null;
	java.util.Date EDate;
	java.sql.Date MyExpDate;
	
	//java.util.Date expDate;
	//java.sql.Date MyexpDATE;
	
	//int tot = 0,total,Uprice;	
	
	private void getDateToday() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		Datelbl.setText(dtf.format(now));
	}
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
			/*
			pst = con.prepareStatement("Select * from tblreceiving");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			*/
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			rs = st.executeQuery("Select * from phildrinksdb.tblreceiving");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
			
	private void getSupplier() {
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select * from phildrinksdb.tblsupplier";
			rs = st.executeQuery(Query);
			while(rs.next()) {
				String suppliers = rs.getString("Name");
				productIDCombox.addItem(suppliers);
				
			}
			
		}catch(Exception e) {

		}
				
	}
	
	private void getProductCode() {
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select * from phildrinksdb.tblproduct";
			rs = st.executeQuery(Query);
			while(rs.next()) {
					String codes = rs.getString("Code");
					supplierIDCombox.addItem(codes);	
			}
			
		}catch(Exception e) {

		}
				
	}
	/*
	private void getUpdatedTotal() {
		try {
			
		int label = Integer.parseInt(lbltotalprice.getText());
			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
		st = con.createStatement();
		String Query = "select Total from phildrinksdb.tblreceiving";
		rs = st.executeQuery(Query);
		while(rs.next()) {
			for(int i =0;i <= table.getRowCount();i++) {
				int getalltotal =  Integer.parseInt(table.getModel().getValueAt(i, 7).toString());//to get the total of selected row
				label =+ getalltotal;
			}
		}
		}catch(Exception e) {
			
		}
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			//int index = table.getSelectedRow();
			int index = table.getRowCount(Query);
			//int getalltotal =  Integer.parseInt(table.getModel().getValueAt(index,7).toString());//to get the total of selected row
			int getalltotal =  Integer.parseInt(table.getModel().getValueAt(index, index));//to get the total of selected row
			
			//String Query = "Select * from phildrinksdb.tblreceiving where Total ='" +getalltotal+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String Query = "select * from phildrinksdb.tblproduct";
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
				st = con.createStatement();
				rs = st.executeQuery(Query);
				
				if(rs.next()) {
					frame.dispose();
					Dashboard login = new Dashboard();
				}
			*/
			
			/*
			try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select * from phildrinksdb.tblproduct";
			rs = st.executeQuery(Query);
			while(rs.next()) {
				 int total = rs.getInt("Total");
				 lbltotalprice.setText(total);
			
		}catch(Exception e) {

		}
	}
	*/
		
	
	int temp = 0;
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1200,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Receiving Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 144, 284, 263);
		frame.getContentPane().add(panel);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  			
				
		  		String code,product,supplier;
		  		int price,qty,tot;
		  		
		  		code = supplierIDCombox.getSelectedItem().toString();
		  		//MODIFIED product = txtProduct.getText();
		  		//MODIFIEDprice = Integer.parseInt(pricetxt.getText());
		  		qty = Integer.parseInt(txtQty.getText());	  					
		  		supplier = productIDCombox.getSelectedItem().toString();
		  			  		
				//MODIFIED tot = qty * price;
		  		
		  		EDate = expDateChooser.getDate();
		  		MyExpDate = new java.sql.Date(EDate.getTime());		  		
		  		
		  		//MODIFIED temp = temp + tot;

				try {
					//to insert the value encoded by the user into the database
					pst = con.prepareStatement("insert into tblreceiving(Code,Product,Price,Qty,ExpDate,Supplier,Total)values(?,?,?,?,?,?,?)");
					
					/*
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
					PreparedStatement add = con.prepareStatement("insert into tblreceiving(Code,Product,Price,Qty,ExpDate,Supplier)values(?,?,?,?,?,?)");
					add.setString(1, codeCombox.getSelectedItem().toString());
					add.setString(2, producttxt.getText());
					//add.setInt(3, Integer.valueOf(pricetxt.getText()));
					//add.setInt(4, Integer.valueOf(qtytxt.getText()));
					add.setString(3, pricetxt.getText());
					add.setString(4, qtytxt.getText());
					add.setDate(5, MyExpDate);
					add.setString(6, supplierCombox.getSelectedItem().toString());
					int row = add.executeUpdate();
					*/
					
					pst.setString(1, code);
					//MODIFIED pst.setString(2, product);
					//MODIFIED pst.setInt(3, price);
					pst.setInt(4, qty);	
					pst.setDate(5, MyExpDate);
					pst.setString(6, supplier);
					//MODIFIED pst.setInt(7, tot);		
					pst.executeUpdate();
					
					String sumOfTotal = "select SUM(Total) FROM tblreceiving ";
					pst=con.prepareStatement(sumOfTotal);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						String sum = rs.getString("sum(Total)");
						lbltotalprice.setText(sum);					
					}	
					pst.close();
					
					JOptionPane.showMessageDialog(null, "Record added");
					table_load();								
					
					supplierIDCombox.setSelectedItem("");
					//MODIFIED txtProduct.setText("");
					//MODIFIED pricetxt.setText("");
					expDateChooser.setDate(null);
					txtQty.setText("");	
					supplierIDCombox.requestFocus();
					
					
				}catch(SQLException el) {
					el.printStackTrace();
				}											
		  	}
		  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(12, 217, 76, 29);
		panel.add(btnAdd);
		
		txtQty = new JTextField();
		txtQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQty.setColumns(10);
		txtQty.setBounds(96, 114, 165, 29);
		panel.add(txtQty);
		
		supplierIDCombox = new JComboBox();
		supplierIDCombox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						//String receiveId = txtSearch.getText();
						String code = supplierIDCombox.getSelectedItem().toString();				
						
						//pst = con.prepareStatement("Select Product,Qty,Price,Supplier from tblreceive where receivingID = ?");
						pst = con.prepareStatement("Select Product,Price from tblproduct where Code = ?");
						
						//pst.setString(1, receiveId);
						pst.setString(1, code);

						
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()==true) {
							//String product = rs.getString(1);
							//String qty = rs.getString(2);
							//String price = rs.getString(3);
							//String customer = rs.getString(4);
							String product = rs.getString(1);
							String price = rs.getString(2);
							
							
							
							//productCombox.setSelectedItem(product);
							//qtytxt.setText(qty);
							//pricetxt.setText(price);
							//customerCombox.setSelectedItem(customer);	
							
							//MODIFIED txtProduct.setText((product));
							//MODIFIED pricetxt.setText(price);
							
						}else {
							/*
							productCombox.setSelectedItem("");
							qtytxt.setText("");
							pricetxt.setText("");
							customerCombox.setSelectedItem("");
							*/
							
							//MODIFIED txtProduct.setText("");
							//MODIFIED pricetxt.setText("");
							
							
						}
							
					}catch(SQLException el) {
						
					}
				}
			});
		supplierIDCombox.setMaximumRowCount(2);
		supplierIDCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		supplierIDCombox.setEditable(true);
		supplierIDCombox.setBounds(96, 26, 165, 25);
		panel.add(supplierIDCombox);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQty.setBounds(17, 115, 46, 25);
		panel.add(lblQty);
		
		JLabel lblSupplierID = new JLabel("SupplierID");
		lblSupplierID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSupplierID.setBounds(7, 25, 81, 25);
		panel.add(lblSupplierID);
		
		JLabel lblExpDate = new JLabel("Exp Date");
		lblExpDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExpDate.setBounds(7, 165, 76, 25);
		panel.add(lblExpDate);
		 
		expDateChooser = new JDateChooser();	
		expDateChooser.setBounds(96, 165, 165, 29);
		panel.add(expDateChooser);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(185, 217, 89, 29);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(supplierIDCombox.getSelectedItem().toString().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information!");
		  		}else {
		  			try {
		  				
		  				DefaultTableModel model = (DefaultTableModel)table.getModel();
						int index = table.getSelectedRow();
		  				int getoldtotal =  Integer.parseInt(table.getModel().getValueAt(index,7).toString());//to get the total of selected row
		  				
		  				//to update the total in the tblreceiving based on the selected row
		  				int newtotal,oldqty,oldprice;
		  				oldqty = Integer.parseInt(txtQty.getText());//to get the current qty in the textfield
		  				//MODIFIED oldprice = Integer.parseInt(pricetxt.getText());//to get the current price in the textfield
		  				//MODIFIED newtotal = oldqty * oldprice; //to set the updated total by multiplying the current qty and price 
		  				
				  		EDate = expDateChooser.getDate();
				  		MyExpDate = new java.sql.Date(EDate.getTime());			  		
				  		
		  				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
		  				//MODIFIED String UpdateQuery = "Update  phildrinksdb.tblreceiving set Code= '" + supplierIDCombox.getSelectedItem().toString()+ "',Product = '" + txtProduct.getText()+"',Price = '" + pricetxt.getText()+"',Qty = '"+txtQty.getText()+"',ExpDate ='"+MyExpDate+"',Supplier='"+productIDCombox.getSelectedItem().toString()+"',Total = '"+newtotal+ "' where receivingID ='"+txtSearch.getText()+"'";
		  				Statement add = con.createStatement();
		  				//MODIFIED add.executeUpdate(UpdateQuery);
		  				JOptionPane.showMessageDialog(null,"Record Updated");
		  				String sumOfTotal = "select SUM(Total) FROM tblreceiving ";
						pst=con.prepareStatement(sumOfTotal);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							String sum = rs.getString("sum(Total)");
							lbltotalprice.setText(sum);					
						}	
						table_load();	
						
						
						//DefaultTableModel tablemodel = (DefaultTableModel)table.getModel();
						//int Myindex = table.getSelectedRow();
						int getnewtotal = Integer.parseInt(table.getModel().getValueAt(index,7).toString());//to get the total of selected row
						
						txtSearch.setText("");
						supplierIDCombox.setSelectedItem("");
						txtQty.setText("");
						expDateChooser.setDate(null);
						txtSearch.requestFocus();
		  				
		  				/*
						String code,product,price,qty,supplier,id;
						code = codeCombox.getSelectedItem().toString();
						product = producttxt.getText();
						price = pricetxt.getText();
						qty = qtytxt.getText();
						
						EDate = expDateChooser.getDate();
				  		MyExpDate = new java.sql.Date(EDate.getTime());
				  		
						supplier = supplierCombox.getSelectedItem().toString();	
						id = IDtxt.getText();
						
						pst = con.prepareStatement("update tblreceiving set Code=?,Product=?,Price=?,Qty=?,ExpDate=?,Supplier=? where receivingID = ?");
						pst.setString(1, code);
						pst.setString(2, product);
						pst.setString(3, price);
						pst.setString(4, qty);
						pst.setDate(5, MyExpDate);
						pst.setString(6, supplier);
						pst.setString(7,id);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Record Updated");
						table_load();	
						IDtxt.setText("");
						codeCombox.setSelectedItem("");
						qtytxt.setText("");
						supplierCombox.setSelectedItem("");
						IDtxt.requestFocus();
						*/
		  				
		  			}catch(SQLException ex) {
		  				ex.printStackTrace();
		  			}
		  		}
		  		/*
				String code,product,price,qty,supplier;
				code = codeCombox.getSelectedItem().toString();
				product = producttxt.getText();
				price = pricetxt.getText();
				qty = qtytxt.getText();
				
		  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(expDateChooser.getDate());
				supplier = supplierCombox.getSelectedItem().toString();			
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
					PreparedStatement add = con.prepareStatement("update tblreceiving set Code=?,Product=?,Price=?,Qty=?,ExpDate=?,Supplier=?");
					
					pst = con.prepareStatement("update tblreceiving set Code=?,Product=?,Price=?,Qty=?,ExpDate=?,Supplier=?");
					pst.setString(1, code);
					pst.setString(2, product);
					pst.setString(3, price);
					pst.setString(4, qty);
					pst.setString(5, date);
					pst.setString(6, supplier);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Updated");
					table_load();				
					codeCombox.setSelectedItem("");
					qtytxt.setText("");
					expDateChooser.setDate(null);
					supplierCombox.setSelectedItem("");
					codeCombox.requestFocus();
					
					
					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
				*/
		  	}
		  });
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(96, 217, 84, 29);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(txtSearch.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a product to be deleted");
		  		}else {

	
				try {
					/*
					pst = con.prepareStatement("delete from tblreceiving where receivingID=?");
					pst.setString(1, receiveid);
					pst.executeUpdate();
					
					table_load();
					
					codeCombox.setSelectedItem("");
					qtytxt.setText("");
					//expDateChooser.setDate("");
					supplierCombox.setSelectedItem("");
					codeCombox.requestFocus();
					*/
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
					int Myindex = table.getSelectedRow();
					String id = txtSearch.getText();
					String Query = "delete from phildrinksdb.tblreceiving where receivingID="+ id;
					Statement add = con.createStatement();
					add.executeUpdate(Query);
					
					String sumOfTotal = "select SUM(Total) FROM tblreceiving ";
					pst=con.prepareStatement(sumOfTotal);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						String sum = rs.getString("sum(Total)");
						lbltotalprice.setText(sum);					
					}	
					
					table_load();
					JOptionPane.showMessageDialog(null,"Record Deleted Successfully");			
					
					supplierIDCombox.setSelectedItem("");
					//MODIFIED txtProduct.setText("");
					//MODIFIED pricetxt.setText("");
					expDateChooser.setDate(null);
					txtQty.setText("");	
					supplierIDCombox.requestFocus();

					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
		  	  }
		  	}
		  });
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblProductID = new JLabel("ProductID");
		lblProductID.setBounds(7, 71, 81, 25);
		panel.add(lblProductID);
		lblProductID.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		productIDCombox = new JComboBox();
		productIDCombox.setBounds(96, 72, 165, 25);
		panel.add(productIDCombox);
		productIDCombox.setMaximumRowCount(2);
		productIDCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productIDCombox.setEditable(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 434, 284, 110);
		frame.getContentPane().add(panel_1);
		
		
		JLabel lblReceivingId = new JLabel("ReceivingID");
		lblReceivingId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceivingId.setBounds(9, 20, 99, 19);
		panel_1.add(lblReceivingId);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSearch.setColumns(10);
		txtSearch.setBounds(110, 16, 164, 29);
		panel_1.add(txtSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(102, 61, 81, 29);
		panel_1.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
			  		supplierIDCombox.setSelectedItem("");
			  		//MODIFIED txtProduct.setText("");
			  		//MODIFIED pricetxt.setText("");
					txtQty.setText("");
					expDateChooser.setDateFormatString("");
					productIDCombox.setSelectedItem("");
					supplierIDCombox.requestFocus();
			  	}
			  });
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(193, 61, 81, 29);
		panel_1.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.print();					
					pst = con.prepareStatement("truncate table phildrinksdb.tblreceiving");
					pst.executeUpdate();
					table_load();
					temp = 0;
					//lbltotalprice.setText(temp);
					String change  =String.valueOf(temp);
			  		lbltotalprice.setText(change);
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}
				
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(24, 574, 89, 29);
		frame.getContentPane().add(btnSave);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 186, 717, 358);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		//table.adjustColumn();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				String id = table.getModel().getValueAt(Myindex,0).toString();
				
				//IDtxt.setText(model.getValueAt(Myindex, 0).toString());
				txtSearch.setText(id);
				supplierIDCombox.setSelectedItem(model.getValueAt(Myindex, 1).toString());
				//MODIFIED txtProduct.setText(model.getValueAt(Myindex, 2).toString());
				//MODIFIED pricetxt.setText(model.getValueAt(Myindex, 3).toString());
				txtQty.setText(model.getValueAt(Myindex, 4).toString());	
				
				//expDateChooser.setDateFormatString(model.getValueAt(Myindex, 5).toString());
				productIDCombox.setSelectedItem(model.getValueAt(Myindex, 6).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductReceived = new JLabel("Received Products");
		lblProductReceived.setForeground(Color.BLACK);
		lblProductReceived.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductReceived.setBounds(599, 160, 178, 29);
		frame.getContentPane().add(lblProductReceived);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1184, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Receiving Section");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(457, 25, 263, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1184, 18);
		frame.getContentPane().add(bottomPanel);
		
		JLabel lblDateReceived = new JLabel("Date Received:");
		lblDateReceived.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateReceived.setBounds(891, 81, 113, 25);
		frame.getContentPane().add(lblDateReceived);
		
		Datelbl = new JLabel("");
		Datelbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		Datelbl.setForeground(new Color(0, 0, 0));
		Datelbl.setBackground(new Color(255, 255, 255));
		Datelbl.setBounds(915, 102, 89, 25);
		frame.getContentPane().add(Datelbl);
		
		lbltotalprice = new JLabel("0");
		lbltotalprice.setHorizontalAlignment(SwingConstants.LEFT);
		lbltotalprice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltotalprice.setBounds(885, 544, 136, 38);
		frame.getContentPane().add(lbltotalprice);
		
		JLabel lblNewLabel = new JLabel("Total Price:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(793, 544, 82, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(3, 65, 68));
		panel_2.setBounds(1031, 65, 153, 585);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_3.setOpaque(false);
		panel_3.setBounds(10, 11, 133, 543);
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_3.add(lblNewLabel_1);
		
		JPanel bottomPanel_1 = new JPanel();
		bottomPanel_1.setLayout(null);
		bottomPanel_1.setBackground(new Color(3, 65, 68));
		bottomPanel_1.setBounds(0, 65, 7, 585);
		frame.getContentPane().add(bottomPanel_1);
		
	}
}
