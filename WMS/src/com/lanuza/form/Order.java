package com.lanuza.form;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.proteanit.sql.DbUtils;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Order {

	private JFrame frame;
	private JTextField txtQty,txtProductCode;
	private JTable table;
	private JComboBox productNameCombox,CustomerNameCombox;
	private JLabel lblCurrentDate,txtGrossTotal;
	private JButton btnAdd, btnUpdate, btnDelete, btnSearchBy,btnPrint,
	btnProducts, btnSaveFile, btnProcess,btnStock, btnCustomer,btnMode;
	String nameQuery, supplierQuery;
	int priceQuery;
	
	Order() {
		initialize();
		Connect();
		table_load();
		getDateToday();
		//getProductName();
		populateProductComboBox();
		getCustomerName();
		getGrossTotal();
		//getProductDetail();
	}
	
	Connection con = null;
	PreparedStatement pst;
	ResultSet rs= null;
	Statement st= null;
	java.util.Date EDate;
	java.sql.Date MyExpDate;
	
	private void getDateToday() { //method to get the date today
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();		
		lblCurrentDate.setText(dtf.format(now));
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
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			rs = st.executeQuery("Select ProductCode, MAX(ProductDescription), MAX(ProductPrice),SUM(Qty),SUM(Total),MAX(Supplier) from phildrinksdb.tblstock GROUP BY ProductCode");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void populateProductComboBox() {
	    try {
	        pst = con.prepareStatement("select Description from phildrinksdb.tblproduct");
	        rs = pst.executeQuery();

	        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	        while (rs.next()) {
	            model.addElement(rs.getString("Description"));
	        }

	        productNameCombox.setModel(model);
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception appropriately
	    }
	}

			
	/*
	private void getProductName() {
		try {			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select Description from phildrinksdb.tblproduct";
			rs = st.executeQuery(Query);
			
			while(rs.next()) {
				String productCodes = rs.getString("Description");		
				productNameCombox.addItem(productCodes);
			}
		}catch(Exception e) {

		}
	}
	*/
	/*
	//get product in stock details based on selected name in productCombox
	private void getProductDetail() {
	    try {
	    	//getProductName();
	        String code = productNameCombox.getSelectedItem().toString();
	        System.out.println(code);
	        pst = con.prepareStatement("Select Sum(Qty), Max(ProductPrice) from phildrinksdb.tblstock where ProductDescription = "+"'"+code+"'");
	        //pst.setString(1, code);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            String availability = rs.getString("Sum(Qty)");
	            String price = rs.getString("Max(ProductPrice)");
	            txtAvailability.setText(availability);
	            txtPrice.setText(price);
	        }
	    } catch(Exception e) {
	        // Handle exceptions (e.g., logging or displaying an error message)
	        e.printStackTrace();
	    }
	}
	*/
	private void getProductDetail(String selectedProduct) {
	    try {
	        pst = con.prepareStatement("SELECT Sum(Qty), Max(ProductPrice) FROM phildrinksdb.tblstock WHERE ProductDescription = ?");
	        pst.setString(1, selectedProduct);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            String availability = rs.getString("Sum(Qty)");
	            String price = rs.getString("Max(ProductPrice)");
	            txtAvailability.setText(availability);
	            txtPrice.setText(price);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception appropriately
	    }
	}

	
	private void getCustomerName() {
		try {			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select Name from phildrinksdb.tblcustomer";
			rs = st.executeQuery(Query);
			
			while(rs.next()) {
				String customer = rs.getString("Name");		
				CustomerNameCombox.addItem(customer);
			}
		}catch(Exception e) {

		}
	}
	
	void getGrossTotal() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String sumOfTotal = "select SUM(Total) FROM tblorder";
			pst=con.prepareStatement(sumOfTotal);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				String sum = rs.getString("sum(Total)");
				txtGrossTotal.setText(sum);					
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	int temp = 0;
	private JTextField txtOrderId;
	private JTextField txtSearchBy;
	private JTextField txtAvailability;
	private JTextField txtPrice;
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTable3 = new JPanel();
		panelTable3.setBackground(new Color(233, 233, 233));
		panelTable3.setLayout(null);
		panelTable3.setBounds(76, 427, 1046, 203);
		frame.getContentPane().add(panelTable3);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product in Stock Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(21, 71, 254, 109);
		panelTable3.add(panel);
		panel.setLayout(null);
		
		txtAvailability = new JTextField();
		txtAvailability.setBackground(new Color(240, 240, 240));
		txtAvailability.setBounds(91, 24, 136, 31);
		panel.add(txtAvailability);
		txtAvailability.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtAvailability.setToolTipText("Quantity in stock");
		txtAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAvailability.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(true);
		txtPrice.setBackground(new Color(240, 240, 240));
		txtPrice.setBounds(91, 66, 136, 31);
		panel.add(txtPrice);
		txtPrice.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtPrice.setToolTipText("Product Price");
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		
		JLabel lblSupplier_1_3 = new JLabel("Availability");
		lblSupplier_1_3.setForeground(Color.BLACK);
		lblSupplier_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSupplier_1_3.setBounds(10, 25, 80, 29);
		panel.add(lblSupplier_1_3);
		
		JLabel lblSupplier_1_4 = new JLabel("Price");
		lblSupplier_1_4.setForeground(Color.BLACK);
		lblSupplier_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSupplier_1_4.setBounds(20, 67, 37, 29);
		panel.add(lblSupplier_1_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(387, 11, 600, 181);
		panelTable3.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSupplier_1 = new JLabel("Product Name");
		lblSupplier_1.setBounds(10, 28, 90, 29);
		panel_1.add(lblSupplier_1);
		lblSupplier_1.setForeground(Color.BLACK);
		lblSupplier_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnAdd = new JButton("");
		btnAdd.setBounds(296, 132, 63, 38);
		panel_1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/2.png")));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (productNameCombox.getSelectedItem().toString().isEmpty() && txtProductCode.getText().isEmpty() && txtQty.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Missing information!");
				}else {
				  		String productCode;
				  		int qty,tot;		
				  			  		
				  		productCode = productNameCombox.getSelectedItem().toString();
				  		//we need to get the price and name of product based on productidcombox selection
				  			  												
				  		qty = Integer.parseInt(txtQty.getText());	  		  																	  			  						 		  		
						try {		
								String code = productNameCombox.getSelectedItem().toString();				
								pst = con.prepareStatement("Select Description,Price, Supplier from tblproduct where Code =" + code);
								rs = pst.executeQuery();					
								if(rs.next()) {
									nameQuery = rs.getString("Description");
									priceQuery = rs.getInt("Price");	
									supplierQuery = rs.getString("Supplier");										
								}
								pst.close();
								
								tot = qty * priceQuery;	  		
						  		temp = temp + tot;							  		
						  									
							//to insert the value encoded by the user into the database
							pst = con.prepareStatement("insert into tblorder(ProductCode,ProductDescription,ProductPrice,Qty,Total,Supplier)values(?,?,?,?,?,?)");
							pst.setString(1, productCode);
							pst.setString(2, nameQuery); //this code sets the productname base on the code selected
							pst.setInt(3, priceQuery); //this code sets the productprice base on the code selected
							pst.setInt(4, qty);	
							pst.setInt(5, tot);		
							pst.setString(6,supplierQuery);
							pst.executeUpdate();
							pst.close();
							getGrossTotal();
							table_load();	
							productNameCombox.setSelectedItem("");
							txtQty.setText("");	
							txtProductCode.setText("");
							productNameCombox.requestFocus();
					
						}catch(SQLException el) {
							el.printStackTrace();
						}
					  }
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setBackground(new Color(243, 243, 243));
		
		productNameCombox = new JComboBox();
		productNameCombox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedProduct = (String) productNameCombox.getSelectedItem();
		        if (selectedProduct != null) {
		            getProductDetail(selectedProduct);
		        }
		    }
		});

		productNameCombox.setBounds(102, 27, 251, 31);
		panel_1.add(productNameCombox);
		productNameCombox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productNameCombox.setToolTipText("Product Code");
		productNameCombox.setMaximumRowCount(2);
		productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productNameCombox.setEditable(true);
		/*
		// Assuming productNameCombox is a JComboBox<String>
		productNameCombox.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (productNameCombox.getSelectedItem() != null) {
		            getProductDetail();
		        }
		    }
		});
		*/
		txtQty = new JTextField();
		txtQty.setBounds(453, 27, 126, 31);
		panel_1.add(txtQty);
		txtQty.setToolTipText("Qty");
		txtQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQty.setColumns(10);
		
		CustomerNameCombox = new JComboBox();
		CustomerNameCombox.setBounds(102, 80, 251, 31);
		panel_1.add(CustomerNameCombox);
		CustomerNameCombox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CustomerNameCombox.setToolTipText("Customer Name");
		CustomerNameCombox.setMaximumRowCount(2);
		CustomerNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		CustomerNameCombox.setEditable(true);
		
		btnDelete = new JButton("");
		btnDelete.setBounds(369, 132, 63, 38);
		panel_1.add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(txtProductCode.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a order item to be deleted");
		  		}else {

	
				try {
					String id = txtProductCode.getText();
					String Query = "delete from phildrinksdb.tblorder where ID ="+ id;
					Statement add = con.createStatement();
					add.executeUpdate(Query);			
					pst.close();
					
					getGrossTotal();
					table_load();
					JOptionPane.showMessageDialog(null,"Record Deleted Successfully");			
					
					productNameCombox.setSelectedItem("");
					txtQty.setText("");	
					txtProductCode.setText("");
					productNameCombox.requestFocus();					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
		  	  }	
			}
		});
		btnDelete.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/6.png")));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Delete");
		btnDelete.setFocusPainted(false);
		btnDelete.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setBackground(new Color(243, 243, 243));
		
		btnUpdate = new JButton("");
		btnUpdate.setBounds(515, 132, 63, 38);
		panel_1.add(btnUpdate);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/5.png")));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(productNameCombox.getSelectedItem().toString().isEmpty() && txtProductCode.getText().isEmpty() && txtQty.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information!");
		  		}else {
		  			try {
		  				
		  				String code = productNameCombox.getSelectedItem().toString();				
						pst = con.prepareStatement("Select Description,Price, Supplier from tblproduct where Code =" + code); //query for product
						rs = pst.executeQuery();					
						if(rs.next()) {
							nameQuery = rs.getString("Description");
							priceQuery = rs.getInt("Price");	
							supplierQuery = rs.getString("Supplier");
						}
						pst.close();
						
						//to get the receiving id of selected row
						int Myindex = table.getSelectedRow();			
						String id = table.getModel().getValueAt(Myindex,0).toString();
						
		  				//to update the total in the tblreceiving based on the selected row
		  				int newtotal,oldqty,oldprice;
		  				oldqty = Integer.parseInt(txtQty.getText());//to get the current qty in the textfield
		  				  				
		  				oldprice = priceQuery;//to get the current price in the textfield
		  				newtotal = oldqty * oldprice; //to set the updated total by multiplying the current qty and price 				  		
				  		
				  		//String UpdateQuery = "Update phildrinksdb.tblreceiving set SupplierName= '" + supplierNameCombox.getSelectedItem().toString()+ "',ProductCode = '"+productCodeCombox.getSelectedItem().toString()+"',ProductName = '"+nameQuery+"',ProductPrice = '"+priceQuery+ "',Qty = '"+txtQty.getText()+"',ExpDate ='"+MyExpDate+"',Total = '"+newtotal+ "' where ReceivingID ='"+txtSearch.getText()+"'";
				  		pst = con.prepareStatement("Update phildrinksdb.tblorder set ProductCode = ?, ProductDescription = ?, ProductPrice = ?, Qty = ?,  Total = ? where ID =" + id);
						pst.setString(1, productNameCombox.getSelectedItem().toString());
						pst.setString(2, nameQuery); //this code sets the productname base on the code selected
						pst.setInt(3, priceQuery); //this code sets the productprice base on the code selected
						pst.setInt(4, Integer.parseInt(txtQty.getText()));	
						pst.setInt(5, newtotal);		
						pst.executeUpdate();
				  		
				  		//Statement add = con.createStatement();
		  				//add.executeUpdate(UpdateQuery);
		  				JOptionPane.showMessageDialog(null,"Record Updated");
		  				String sumOfTotal = "select SUM(Total) FROM tblorder ";
						pst=con.prepareStatement(sumOfTotal);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							String sum = rs.getString("sum(Total)");
							txtGrossTotal.setText(sum);					
						}	
						pst.close();
						table_load();	
						
						txtProductCode.setText("");
						productNameCombox.setSelectedItem("");
						txtQty.setText("");
						txtProductCode.requestFocus();	  				
		  			}catch(SQLException ex) {
		  				ex.printStackTrace();
		  			}
		  		}
			}
		});
		btnUpdate.setToolTipText("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnUpdate.setBackground(new Color(243, 243, 243));
		
		JLabel lblSupplier_1_5 = new JLabel("Customer");
		lblSupplier_1_5.setForeground(Color.BLACK);
		lblSupplier_1_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSupplier_1_5.setBounds(23, 81, 63, 29);
		panel_1.add(lblSupplier_1_5);
		
		JLabel lblSupplier_1_6 = new JLabel("Quantity");
		lblSupplier_1_6.setForeground(Color.BLACK);
		lblSupplier_1_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSupplier_1_6.setBounds(391, 28, 63, 29);
		panel_1.add(lblSupplier_1_6);
		
		JButton btnClear = new JButton("");
		btnClear.setBounds(442, 132, 63, 38);
		panel_1.add(btnClear);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setToolTipText("Clear");
		btnClear.setFocusPainted(false);
		btnClear.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setBackground(new Color(243, 243, 243));
		
		txtProductCode = new JTextField();
		txtProductCode.setBounds(99, 11, 66, 31);
		panelTable3.add(txtProductCode);
		txtProductCode.setEditable(false);
		txtProductCode.setBackground(new Color(240, 240, 240));
		txtProductCode.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtProductCode.setToolTipText("Id");
		txtProductCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProductCode.setColumns(10);
		
		JLabel lblSupplier_1_1 = new JLabel("Order Id");
		lblSupplier_1_1.setBounds(38, 11, 60, 29);
		panelTable3.add(lblSupplier_1_1);
		lblSupplier_1_1.setForeground(Color.BLACK);
		lblSupplier_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1370, 54);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Orders Section");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(588, 11, 167, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 641, 1370, 20);
		frame.getContentPane().add(bottomPanel);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(1164, 54, 170, 607);
		frame.getContentPane().add(sidePanel2);
		sidePanel2.setLayout(null);
		
		JPanel panelShortcut1 = new JPanel();
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setOpaque(false);
		panelShortcut1.setBounds(10, 39, 137, 491);
		sidePanel2.add(panelShortcut1);
		panelShortcut1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setBounds(36, 6, 78, 16);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panelShortcut1.add(lblNewLabel_1);
		
		JLabel lblCtrlS = new JLabel("Ctrl+S");
		lblCtrlS.setForeground(new Color(255, 255, 255));
		lblCtrlS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlS.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlS.setBounds(0, 39, 58, 28);
		panelShortcut1.add(lblCtrlS);
		
		JLabel lblCtrlP = new JLabel("Ctrl+P");
		lblCtrlP.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlP.setForeground(Color.WHITE);
		lblCtrlP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlP.setBounds(0, 78, 58, 28);
		panelShortcut1.add(lblCtrlP);
		
		JLabel lblCtrlD = new JLabel("Ctrl+D");
		lblCtrlD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlD.setForeground(Color.WHITE);
		lblCtrlD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlD.setBounds(0, 117, 58, 28);
		panelShortcut1.add(lblCtrlD);
		
		JLabel lblS = new JLabel("S");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblS.setBounds(0, 168, 58, 28);
		panelShortcut1.add(lblS);
		
		JLabel lblM = new JLabel("P");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 213, 58, 28);
		panelShortcut1.add(lblM);
		
		JLabel lblM_1 = new JLabel("M");
		lblM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblM_1.setForeground(Color.WHITE);
		lblM_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM_1.setBounds(0, 259, 58, 28);
		panelShortcut1.add(lblM_1);
		
		JTextArea txtrCtrlS = new JTextArea();
		txtrCtrlS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlS.setForeground(new Color(255, 255, 255));
		txtrCtrlS.setOpaque(false);
		txtrCtrlS.setText("Save as\r\n File");
		txtrCtrlS.setBounds(65, 33, 82, 38);
		panelShortcut1.add(txtrCtrlS);
		
		JTextArea txtrCtrlP = new JTextArea();
		txtrCtrlP.setText("Print");
		txtrCtrlP.setOpaque(false);
		txtrCtrlP.setForeground(Color.WHITE);
		txtrCtrlP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlP.setBounds(65, 82, 82, 28);
		panelShortcut1.add(txtrCtrlP);
		
		JTextArea txtrCtrlD = new JTextArea();
		txtrCtrlD.setText("Save to \r\nDatabase");
		txtrCtrlD.setOpaque(false);
		txtrCtrlD.setForeground(Color.WHITE);
		txtrCtrlD.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlD.setBounds(65, 116, 82, 38);
		panelShortcut1.add(txtrCtrlD);
		
		JTextArea txtrS = new JTextArea();
		txtrS.setText("Go to \r\nSuppliers");
		txtrS.setOpaque(false);
		txtrS.setForeground(Color.WHITE);
		txtrS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrS.setBounds(65, 161, 82, 38);
		panelShortcut1.add(txtrS);
		
		JTextArea txtrP = new JTextArea();
		txtrP.setText("Go to \r\nProducts");
		txtrP.setOpaque(false);
		txtrP.setForeground(Color.WHITE);
		txtrP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrP.setBounds(65, 206, 82, 38);
		panelShortcut1.add(txtrP);
		
		JTextArea txtrM = new JTextArea();
		txtrM.setText("Change Mode\r\n(Light/Dark)");
		txtrM.setOpaque(false);
		txtrM.setForeground(Color.WHITE);
		txtrM.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrM.setBounds(53, 252, 82, 38);
		panelShortcut1.add(txtrM);
		
		JLabel lblM_1_1 = new JLabel("I");
		lblM_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblM_1_1.setForeground(Color.WHITE);
		lblM_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM_1_1.setBounds(0, 301, 58, 28);
		panelShortcut1.add(lblM_1_1);
		
		JTextArea txtrGoToInventory = new JTextArea();
		txtrGoToInventory.setText("Go to\r\nInventory");
		txtrGoToInventory.setOpaque(false);
		txtrGoToInventory.setForeground(Color.WHITE);
		txtrGoToInventory.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrGoToInventory.setBounds(63, 298, 72, 38);
		panelShortcut1.add(txtrGoToInventory);
		
		JPanel sidePanel1 = new JPanel();
		sidePanel1.setLayout(null);
		sidePanel1.setBackground(new Color(3, 65, 68));
		sidePanel1.setBounds(0, 54, 35, 684);
		frame.getContentPane().add(sidePanel1);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBackground(new Color(64, 128, 128));
		panelButtons.setBounds(34, 54, 1129, 38);
		frame.getContentPane().add(panelButtons);
		
		btnStock = new JButton("");
		btnStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Stock goToStock = new Stock();
			}
		});
		btnStock.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnStock.setToolTipText("Go to Stock");
		btnStock.setFocusPainted(false);
		btnStock.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnStock.setBackground(new Color(64, 128, 128));
		btnStock.setBounds(315, 0, 63, 38);
		panelButtons.add(btnStock);
		
		btnProducts = new JButton("");
		btnProducts.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnProducts.setToolTipText("Go to Product");
		btnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Product goToProduct = new Product();
			}
		});
		btnProducts.setFocusPainted(false);
		btnProducts.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProducts.setBackground(new Color(64, 128, 128));
		btnProducts.setBounds(252, 0, 63, 38);
		panelButtons.add(btnProducts);
		
		btnCustomer = new JButton("");
		btnCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Customer goToCustomer = new Customer(); 
			}
		});
		btnCustomer.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnCustomer.setToolTipText("Go to Customer");
		btnCustomer.setFocusPainted(false);
		btnCustomer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCustomer.setBackground(new Color(64, 128, 128));
		btnCustomer.setBounds(189, 0, 63, 38);
		panelButtons.add(btnCustomer);
		
		btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table.print();					
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnPrint.setToolTipText("Print");
		btnPrint.setFocusPainted(false);
		btnPrint.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setBackground(new Color(64, 128, 128));
		btnPrint.setBounds(63, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		btnProcess = new JButton("");
		btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String checkTotalString = "Select Total from tblorder where ProductCode = 114547";
				
				String queryMethod = "UPDATE tblstock s "
						+ "JOIN tblorder o ON s.ProductCode = o.ProductCode "
						+ "SET "
						+ "s.Qty = GREATEST(s.Qty - COALESCE(o.Qty, 0), 0), "
						+ "s.Total = GREATEST(s.Total - COALESCE(o.Total, 0), 0) "
						+ "WHERE o.Total <= s.Total; ";
				/*
				String queryTrial = "INSERT INTO tblstock (ProductCode, ProductDescription, ProductPrice, Qty, Total, Supplier)"
				+ "SELECT"
				+ "r.ProductCode,r.ProductDescription,r.ProductPrice,(o.Qty - r.Qty) AS Qty,"
				+ "(o.Total - r.Qty) AS Total,r.Supplier"
				+ "FROM tblorder r"
				+ "JOIN tblstock o ON r.ProductCode = o.ProductCode"
				+ "GROUP BY r.ProductCode, r.ProductDescription, r.ProductPrice, r.Qty, r.Total, r.Supplier";
				
				String insertQuery = "insert into tblstock o(ProductCode,ProductDescription,ProductPrice,Qty,Total,Supplier) "
						+ "select r.ProductCode, r.ProductDescription, r.ProductPrice,r.Qty = o.Qty - r.Qty,r.Total = o.Total - r.Qty,r.Supplier from tblreceiving r Group By ProductCode";
				
				String deleteQuery = "INSERT INTO tblstock(ProductCode, ProductDescription,ProductPrice,Qty, Total, Supplier) "
						+ "SELECT o.ProductCode,o.ProductDescription, o.ProductPrice, (s.Qty - o.Qty) AS Qty, (s.Total - o.Total) AS Total, o.Supplier "
						+ "FROM tblorder o "
						+ "JOIN tblstock s ON o.ProductCode = s.ProductCode ON DUPLICATE KEY UPDATE "
						+ "tblstock.Qty = tblstock.Qty-o.Qty, tblstock.Total = tblstock.Total - o.Total";
				*/
				//String deleteQuery = "insert into tblstock(ProductCode,ProductDescription,ProductPrice,Qty,Total,Supplier) select ProductCode, MAX(ProductDescription), MAX(ProductPrice),SUM(Qty),SUM(Total),MAX(Supplier) from tblreceiving Group By ProductCode";
				
				//String insertQuery = "insert into tblstock(ProductCode,ProductDescription,ProductPrice,Qty,Total,Supplier) select ProductCode, ProductDescription, ProductPrice,Qty,Total,Supplier from tblreceiving Group By ProductCode";
				//Sql query to select selected data from source table(tblreceiving)
				//String selectQuery = " select ProductCode, ProductDescription, ProductPrice,Qty,Total,Supplier) from tblreceiving";
				try {			
					
					st = con.createStatement();
					st.executeUpdate(queryMethod);
					st.close();
					JOptionPane.showMessageDialog(null, "Table data successfully modified stock");
					/*
					pst = con.prepareStatement("truncate table phildrinksdb.tblorder");
					pst.executeUpdate();
					pst.close();			
					*/
					table_load();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnProcess.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnProcess.setToolTipText("Process Order");
		btnProcess.setFocusPainted(false);
		btnProcess.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProcess.setBackground(new Color(64, 128, 128));
		btnProcess.setBounds(0, 0, 63, 38);
		panelButtons.add(btnProcess);
		
		btnSaveFile = new JButton("");
		btnSaveFile.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnSaveFile.setToolTipText("Save as file");
		btnSaveFile.setFocusPainted(false);
		btnSaveFile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setBackground(new Color(64, 128, 128));
		btnSaveFile.setBounds(126, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		btnMode = new JButton("");
		btnMode.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnMode.setToolTipText("Mode(Light/Dark)");
		btnMode.setFocusPainted(false);
		btnMode.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setBackground(new Color(64, 128, 128));
		btnMode.setBounds(378, 0, 63, 38);
		panelButtons.add(btnMode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 187, 1046, 240);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		//table.adjustColumn();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(Myindex,0).toString();
				txtProductCode.setText(id);	
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panelTable4 = new JPanel();
		panelTable4.setBackground(new Color(233, 233, 233));
		panelTable4.setBounds(34, 187, 42, 443);
		frame.getContentPane().add(panelTable4);
		
		JPanel panelTable2 = new JPanel();
		panelTable2.setBackground(new Color(233, 233, 233));
		panelTable2.setBounds(1121, 187, 42, 443);
		frame.getContentPane().add(panelTable2);
		
		JPanel panelTable1 = new JPanel();
		panelTable1.setBackground(new Color(233, 233, 233));
		panelTable1.setBounds(34, 93, 1129, 95);
		frame.getContentPane().add(panelTable1);
		panelTable1.setLayout(null);
		
		lblCurrentDate = new JLabel("");
		lblCurrentDate.setBounds(1026, 0, 93, 40);
		panelTable1.add(lblCurrentDate);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setForeground(new Color(0, 0, 0));
		lblCurrentDate.setBackground(new Color(255, 255, 255));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblGrossTotal.setBounds(808, 50, 116, 33);
		panelTable1.add(lblGrossTotal);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(984, 4, 40, 33);
		panelTable1.add(lblDate);
		
		txtGrossTotal = new JLabel("0");
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setBackground(new Color(255, 255, 255));
		txtGrossTotal.setBounds(926, 48, 159, 41);
		panelTable1.add(txtGrossTotal);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtSearchBy = new JTextField();
		txtSearchBy.setToolTipText("Search by...");
		txtSearchBy.setColumns(10);
		txtSearchBy.setBounds(43, 52, 248, 33);
		panelTable1.add(txtSearchBy);
		
		btnSearchBy = new JButton("");
		btnSearchBy.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/search.png")));
		btnSearchBy.setToolTipText("Add");
		btnSearchBy.setFocusPainted(false);
		btnSearchBy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setBackground(new Color(243, 243, 243));
		btnSearchBy.setBounds(301, 52, 63, 33);
		panelTable1.add(btnSearchBy);
		
	}
}
