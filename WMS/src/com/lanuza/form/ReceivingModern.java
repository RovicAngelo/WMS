package com.lanuza.form;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class ReceivingModern {

	private JFrame frame;
	private JTextField txtQty,txtSearchId;
	private JTable table;
	private JComboBox productCodeCombox;
	private JDateChooser expDateChooser;
	private JLabel Datelbl,lbltotalprice;
	String nameQuery, supplierQuery;
	int priceQuery;
	
	ReceivingModern() {
		initialize();
		Connect();
		table_load();
		getDateToday();
		getProductCode();
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
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			rs = st.executeQuery("Select * from phildrinksdb.tblreceiving");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
				
	private void getProductCode() {
		try {			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String Query = "select * from phildrinksdb.tblproduct";
			rs = st.executeQuery(Query);
			
			while(rs.next()) {
				String productCodes = rs.getString("Code");		
				productCodeCombox.addItem(productCodes);
			}
		}catch(Exception e) {

		}
	}
	
	int temp = 0;
	private JTextField txtReceivingId;
	private JTextField txtReceiver;
	private JTextField textField;
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(226, 226, 226));
		panel.setLayout(null);
		panel.setBounds(76, 553, 1046, 77);
		frame.getContentPane().add(panel);
		
		txtQty = new JTextField();
		txtQty.setToolTipText("Qty");
		txtQty.setBounds(475, 3, 248, 38);
		panel.add(txtQty);
		txtQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQty.setColumns(10);
		
		productCodeCombox = new JComboBox();
		productCodeCombox.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		productCodeCombox.setToolTipText("Code");
		productCodeCombox.setBounds(113, 3, 360, 38);
		panel.add(productCodeCombox);
		productCodeCombox.setMaximumRowCount(2);
		productCodeCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productCodeCombox.setEditable(true);
		
		txtSearchId = new JTextField();
		txtSearchId.setToolTipText("Id");
		txtSearchId.setBounds(0, 3, 53, 38);
		panel.add(txtSearchId);
		txtSearchId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSearchId.setColumns(10);
		
		expDateChooser = new JDateChooser();
		expDateChooser.setDateFormatString("d MM yyyy");
		expDateChooser.setToolTipText("Date");
		expDateChooser.setBounds(725, 3, 120, 38);
		panel.add(expDateChooser);
		
		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/2.png")));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (productCodeCombox.getSelectedItem().toString().isEmpty() && txtSearchId.getText().isEmpty() && txtQty.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Missing information!");
				}else {
				  		String productCode;
				  		int qty,tot;		
				  			  		
				  		productCode = productCodeCombox.getSelectedItem().toString();
				  		//we need to get the price and name of product based on productidcombox selection
				  			  												
				  		qty = Integer.parseInt(txtQty.getText());	
				  		//for the exp date 
				  		EDate = expDateChooser.getDate(); 
				  		MyExpDate = new java.sql.Date(EDate.getTime());	
				  		  																	  			  						 		  		
						try {		
								String code = productCodeCombox.getSelectedItem().toString();				
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
							pst = con.prepareStatement("insert into tblreceiving(ProductCode,ProductDescription,ProductPrice,Qty,ExpDate,Total)values(?,?,?,?,?,?)");
							pst.setString(1, productCode);
							pst.setString(2, nameQuery); //this code sets the productname base on the code selected
							pst.setInt(3, priceQuery); //this code sets the productprice base on the code selected
							pst.setInt(4, qty);	
							pst.setDate(5, MyExpDate);
							pst.setInt(6, tot);		
							pst.executeUpdate();
							
							String sumOfTotal = "select SUM(Total) from tblreceiving ";
							pst=con.prepareStatement(sumOfTotal);
							rs = pst.executeQuery();
							
							if(rs.next()) {
								String sum = rs.getString("sum(Total)");
								lbltotalprice.setText(sum);					
							}	
							pst.close();
							
							JOptionPane.showMessageDialog(null, "Record added");
							table_load();								
							
							productCodeCombox.setSelectedItem("");
							expDateChooser.setDate(null);
							txtQty.setText("");	
							txtSearchId.setText("");
							productCodeCombox.requestFocus();
					
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
		btnAdd.setBounds(847, 3, 63, 38);
		panel.add(btnAdd);
		
		JButton btnAdd_1 = new JButton("");
		btnAdd_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(txtSearchId.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a receive item to be deleted");
		  		}else {

	
				try {
					String id = txtSearchId.getText();
					String Query = "delete from phildrinksdb.tblreceiving where ID ="+ id;
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
					
					productCodeCombox.setSelectedItem("");
					expDateChooser.setDate(null);
					txtQty.setText("");	
					txtSearchId.setText("");
					productCodeCombox.requestFocus();					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
		  	  }	
			}
		});
		btnAdd_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/6.png")));
		btnAdd_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd_1.setToolTipText("Add");
		btnAdd_1.setFocusPainted(false);
		btnAdd_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd_1.setBackground(new Color(243, 243, 243));
		btnAdd_1.setBounds(910, 3, 63, 38);
		panel.add(btnAdd_1);
		
		JButton btnAdd_1_1 = new JButton("");
		btnAdd_1_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/5.png")));
		btnAdd_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(productCodeCombox.getSelectedItem().toString().isEmpty() && txtSearchId.getText().isEmpty() && txtQty.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information!");
		  		}else {
		  			try {
		  				
		  				String code = productCodeCombox.getSelectedItem().toString();				
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
		  				
				  		EDate = expDateChooser.getDate();
				  		MyExpDate = new java.sql.Date(EDate.getTime());		
				  		
				  		
				  		//String UpdateQuery = "Update phildrinksdb.tblreceiving set SupplierName= '" + supplierNameCombox.getSelectedItem().toString()+ "',ProductCode = '"+productCodeCombox.getSelectedItem().toString()+"',ProductName = '"+nameQuery+"',ProductPrice = '"+priceQuery+ "',Qty = '"+txtQty.getText()+"',ExpDate ='"+MyExpDate+"',Total = '"+newtotal+ "' where ReceivingID ='"+txtSearch.getText()+"'";
				  		pst = con.prepareStatement("Update phildrinksdb.tblreceiving set ProductCode = ?, ProductDescription = ?, ProductPrice = ?, Qty = ?, ExpDate =?, Total = ? where ID =" + id);
						pst.setString(1, productCodeCombox.getSelectedItem().toString());
						pst.setString(2, nameQuery); //this code sets the productname base on the code selected
						pst.setInt(3, priceQuery); //this code sets the productprice base on the code selected
						pst.setInt(4, Integer.parseInt(txtQty.getText()));	
						pst.setDate(5, MyExpDate);
						pst.setInt(6, newtotal);		
						pst.executeUpdate();
				  		
				  		//Statement add = con.createStatement();
		  				//add.executeUpdate(UpdateQuery);
		  				JOptionPane.showMessageDialog(null,"Record Updated");
		  				String sumOfTotal = "select SUM(Total) FROM tblreceiving ";
						pst=con.prepareStatement(sumOfTotal);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							String sum = rs.getString("sum(Total)");
							lbltotalprice.setText(sum);					
						}	
						pst.close();
						table_load();	
						
						txtSearchId.setText("");
						productCodeCombox.setSelectedItem("");
						txtQty.setText("");
						expDateChooser.setDate(null);
						txtSearchId.requestFocus();	  				
		  			}catch(SQLException ex) {
		  				ex.printStackTrace();
		  			}
		  		}
			}
		});
		btnAdd_1_1.setToolTipText("Add");
		btnAdd_1_1.setFocusPainted(false);
		btnAdd_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd_1_1.setBackground(new Color(243, 243, 243));
		btnAdd_1_1.setBounds(973, 3, 63, 38);
		panel.add(btnAdd_1_1);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1370, 54);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Receiving Section");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(583, 11, 167, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 641, 1370, 20);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(3, 65, 68));
		panel_2.setBounds(1164, 54, 170, 607);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_3.setOpaque(false);
		panel_3.setBounds(10, 39, 150, 531);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setBounds(36, 6, 78, 16);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Ctrl+S");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 39, 58, 28);
		panel_3.add(lblNewLabel);
		
		JLabel lblCtrlp = new JLabel("Ctrl+P");
		lblCtrlp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlp.setForeground(Color.WHITE);
		lblCtrlp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlp.setBounds(0, 78, 58, 28);
		panel_3.add(lblCtrlp);
		
		JLabel lblCtrld = new JLabel("Ctrl+D");
		lblCtrld.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrld.setForeground(Color.WHITE);
		lblCtrld.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrld.setBounds(0, 117, 58, 28);
		panel_3.add(lblCtrld);
		
		JLabel lblS = new JLabel("S");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblS.setBounds(0, 168, 58, 28);
		panel_3.add(lblS);
		
		JLabel lblM = new JLabel("P");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 213, 58, 28);
		panel_3.add(lblM);
		
		JLabel lblM_1 = new JLabel("M");
		lblM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblM_1.setForeground(Color.WHITE);
		lblM_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM_1.setBounds(0, 259, 58, 28);
		panel_3.add(lblM_1);
		
		JTextArea txtrSaveToDatabase = new JTextArea();
		txtrSaveToDatabase.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrSaveToDatabase.setForeground(new Color(255, 255, 255));
		txtrSaveToDatabase.setOpaque(false);
		txtrSaveToDatabase.setText("Save as\r\n File");
		txtrSaveToDatabase.setBounds(68, 33, 82, 38);
		panel_3.add(txtrSaveToDatabase);
		
		JTextArea txtrPrint = new JTextArea();
		txtrPrint.setText("Print");
		txtrPrint.setOpaque(false);
		txtrPrint.setForeground(Color.WHITE);
		txtrPrint.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrPrint.setBounds(68, 82, 82, 28);
		panel_3.add(txtrPrint);
		
		JTextArea txtrSaveToDatabase_1_1 = new JTextArea();
		txtrSaveToDatabase_1_1.setText("Save to \r\nDatabase");
		txtrSaveToDatabase_1_1.setOpaque(false);
		txtrSaveToDatabase_1_1.setForeground(Color.WHITE);
		txtrSaveToDatabase_1_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrSaveToDatabase_1_1.setBounds(68, 116, 82, 38);
		panel_3.add(txtrSaveToDatabase_1_1);
		
		JTextArea txtrSaveToDatabase_1_1_1 = new JTextArea();
		txtrSaveToDatabase_1_1_1.setText("Go to \r\nSuppliers");
		txtrSaveToDatabase_1_1_1.setOpaque(false);
		txtrSaveToDatabase_1_1_1.setForeground(Color.WHITE);
		txtrSaveToDatabase_1_1_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrSaveToDatabase_1_1_1.setBounds(68, 161, 82, 38);
		panel_3.add(txtrSaveToDatabase_1_1_1);
		
		JTextArea txtrSaveToDatabase_1_1_2 = new JTextArea();
		txtrSaveToDatabase_1_1_2.setText("Go to \r\nProducts");
		txtrSaveToDatabase_1_1_2.setOpaque(false);
		txtrSaveToDatabase_1_1_2.setForeground(Color.WHITE);
		txtrSaveToDatabase_1_1_2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrSaveToDatabase_1_1_2.setBounds(68, 206, 82, 38);
		panel_3.add(txtrSaveToDatabase_1_1_2);
		
		JTextArea txtrSaveToDatabase_1_1_2_1 = new JTextArea();
		txtrSaveToDatabase_1_1_2_1.setText("Change Mode\r\n(Light/Dark)");
		txtrSaveToDatabase_1_1_2_1.setOpaque(false);
		txtrSaveToDatabase_1_1_2_1.setForeground(Color.WHITE);
		txtrSaveToDatabase_1_1_2_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrSaveToDatabase_1_1_2_1.setBounds(58, 252, 82, 38);
		panel_3.add(txtrSaveToDatabase_1_1_2_1);
		
		JPanel bottomPanel_1 = new JPanel();
		bottomPanel_1.setLayout(null);
		bottomPanel_1.setBackground(new Color(3, 65, 68));
		bottomPanel_1.setBounds(0, 54, 35, 684);
		frame.getContentPane().add(bottomPanel_1);
		
		JPanel bottomPanel_2 = new JPanel();
		bottomPanel_2.setLayout(null);
		bottomPanel_2.setBackground(new Color(64, 128, 128));
		bottomPanel_2.setBounds(34, 54, 1129, 38);
		frame.getContentPane().add(bottomPanel_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1.setToolTipText("Mode");
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setBounds(315, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1_1.setToolTipText("Product");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1.setBounds(252, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1_1_1.setToolTipText("Supplier");
		btnNewButton_1_1_1.setFocusPainted(false);
		btnNewButton_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1_1_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1_1.setBounds(189, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("");
		btnNewButton_1_1_2.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		btnNewButton_1_1_2.setToolTipText("Print");
		btnNewButton_1_1_2.setFocusPainted(false);
		btnNewButton_1_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1_1_2.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1_2.setBounds(63, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_3 = new JButton("");
		btnNewButton_1_1_3.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1_1_3.setToolTipText("Send to Database");
		btnNewButton_1_1_3.setFocusPainted(false);
		btnNewButton_1_1_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1_1_3.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1_3.setBounds(0, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1_1_3);
		
		JButton btnNewButton_1_1_2_1 = new JButton("");
		btnNewButton_1_1_2_1.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnNewButton_1_1_2_1.setToolTipText("Save as file");
		btnNewButton_1_1_2_1.setFocusPainted(false);
		btnNewButton_1_1_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_1_1_2_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1_1_2_1.setBounds(126, 0, 63, 38);
		bottomPanel_2.add(btnNewButton_1_1_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 222, 1046, 331);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		//table.adjustColumn();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(Myindex,0).toString();
				txtSearchId.setText(id);
				productCodeCombox.setSelectedItem(model.getValueAt(Myindex, 1).toString());
				txtQty.setText(model.getValueAt(Myindex, 4).toString());	
				//expDateChooser.setDateFormatString(model.getValueAt(Myindex, 6).toString()); // this code is not functioning
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(226, 226, 226));
		panel_1.setBounds(34, 218, 42, 412);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(226, 226, 226));
		panel_1_1.setBounds(1121, 218, 42, 416);
		frame.getContentPane().add(panel_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(226, 226, 226));
		panel_1_1_1.setBounds(34, 93, 1129, 131);
		frame.getContentPane().add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		
		Datelbl = new JLabel("");
		Datelbl.setBounds(1026, 0, 93, 40);
		panel_1_1_1.add(Datelbl);
		Datelbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		Datelbl.setForeground(new Color(0, 0, 0));
		Datelbl.setBackground(new Color(255, 255, 255));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblGrossTotal.setBounds(810, 87, 116, 33);
		panel_1_1_1.add(lblGrossTotal);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(976, 4, 40, 33);
		panel_1_1_1.add(lblDate);
		
		JLabel lblReceivingId = new JLabel("Receiving ID");
		lblReceivingId.setForeground(Color.BLACK);
		lblReceivingId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReceivingId.setBounds(41, 6, 85, 29);
		panel_1_1_1.add(lblReceivingId);
		
		JLabel lblReceiver = new JLabel("Receive by: ");
		lblReceiver.setForeground(Color.BLACK);
		lblReceiver.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReceiver.setBounds(41, 37, 80, 33);
		panel_1_1_1.add(lblReceiver);
		
		txtReceivingId = new JTextField();
		txtReceivingId.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtReceivingId.setColumns(10);
		txtReceivingId.setBounds(123, 7, 152, 28);
		panel_1_1_1.add(txtReceivingId);
		
		txtReceiver = new JTextField();
		txtReceiver.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtReceiver.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtReceiver.setBackground(new Color(226, 226, 226));
		txtReceiver.setColumns(10);
		txtReceiver.setBounds(123, 37, 187, 28);
		panel_1_1_1.add(txtReceiver);
		
		lbltotalprice = new JLabel("0");
		lbltotalprice.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbltotalprice.setOpaque(true);
		lbltotalprice.setBackground(new Color(255, 255, 255));
		lbltotalprice.setBounds(924, 86, 159, 38);
		panel_1_1_1.add(lbltotalprice);
		lbltotalprice.setHorizontalAlignment(SwingConstants.LEFT);
		lbltotalprice.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setToolTipText("Search by...");
		textField.setColumns(10);
		textField.setBounds(41, 92, 287, 33);
		panel_1_1_1.add(textField);
		
		JLabel lblSearchBy = new JLabel("");
		lblSearchBy.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/search.png")));
		lblSearchBy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblSearchBy.setToolTipText("Delete");
		lblSearchBy.setOpaque(true);
		lblSearchBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBy.setBackground(Color.WHITE);
		lblSearchBy.setBounds(329, 93, 60, 32);
		panel_1_1_1.add(lblSearchBy);
		
	}
}
