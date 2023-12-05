package com.lanuza.wms.ui.form;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.lanuza.wms.dao.ReceivingEntryDAO;
import com.lanuza.wms.dao.impl.ReceivingEntryDAOImpl;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.model.ReceivingEntry;
import com.lanuza.wms.model.Supplier;
import com.lanuza.wms.service.ReceivingEntryService;
import com.lanuza.wms.service.impl.ReceivingEntryServiceImpl;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class ReceivingEntryForm {
	private final ReceivingEntryService receivingEntryService;
	private final ReceivingEntryDAO receivingEntryDAO;
	private JFrame frame;
	private JTextField txtQty,txtSearchId;
	private JTable table;
	private JComboBox<String> productNameCombox;
	private JDateChooser expDateChooser;
	private JLabel lblCurrentDate,txtGrossTotal;
	private JTextField txtSearchBy;
	String supplierQuery;
	double priceQuery;
	java.util.Date EDate;
	java.sql.Date MyExpDate;
	
	ReceivingEntryForm() {
		this.receivingEntryDAO = new ReceivingEntryDAOImpl();
		this.receivingEntryService = new ReceivingEntryServiceImpl(receivingEntryDAO);
		initialize();
		loadData();
		getDateToday();
		displayGrossTotal();
		populateProductCombox();
	}

	//to get the current date and display in lblCurrentDate
	private void getDateToday() { //method to get the date today
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();		
		lblCurrentDate.setText(dtf.format(now));;
	}
	//to load the data in the table
	void loadData() {
		receivingEntryService.tableLoad(table);
	}
	private void displayGrossTotal() {
        double sumOfTotal = receivingEntryService.getSumOfTotal();
        // Update UI component (e.g., setText on a JTextField)
        txtGrossTotal.setText(String.valueOf(sumOfTotal));
	}  
	
	//to populate the productNameCombox with Desription attribute of tblproduct 
	private void populateProductCombox() {
		 String productDescription = receivingEntryService.getProductDescription();
		 productNameCombox.addItem(productDescription);
	}
		
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTable3 = new JPanel();
		panelTable3.setBackground(new Color(226, 226, 226));
		panelTable3.setLayout(null);
		panelTable3.setBounds(76, 444, 1046, 197);
		frame.getContentPane().add(panelTable3);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1370, 54);
		frame.getContentPane().add(topPanel);
		
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
		
		JPanel panelTable4 = new JPanel();
		panelTable4.setBackground(new Color(226, 226, 226));
		panelTable4.setBounds(34, 198, 42, 443);
		frame.getContentPane().add(panelTable4);
		
		JPanel panelTable2 = new JPanel();
		panelTable2.setBackground(new Color(226, 226, 226));
		panelTable2.setBounds(1121, 198, 42, 443);
		frame.getContentPane().add(panelTable2);
		
		JPanel panelTable1 = new JPanel();
		panelTable1.setBackground(new Color(226, 226, 226));
		panelTable1.setBounds(34, 93, 1129, 107);
		frame.getContentPane().add(panelTable1);
		panelTable1.setLayout(null);
		
		JLabel topLabel = new JLabel("Receiving Section");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(583, 11, 167, 30);
		topPanel.add(topLabel);				
		
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
		
		JLabel lblS = new JLabel("M");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblS.setBounds(0, 168, 58, 28);
		panelShortcut1.add(lblS);
		
		JLabel lblM = new JLabel("B");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 213, 58, 28);
		panelShortcut1.add(lblM);
		
		lblCurrentDate = new JLabel("");
		lblCurrentDate.setBounds(1026, 0, 93, 40);
		panelTable1.add(lblCurrentDate);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setForeground(new Color(0, 0, 0));
		lblCurrentDate.setBackground(new Color(255, 255, 255));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblGrossTotal.setBounds(808, 59, 116, 33);
		panelTable1.add(lblGrossTotal);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(976, 4, 40, 33);
		panelTable1.add(lblDate);
		
		JLabel lblReceiver = new JLabel("Receive by: ");
		lblReceiver.setForeground(Color.BLACK);
		lblReceiver.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReceiver.setBounds(41, 11, 80, 33);
		panelTable1.add(lblReceiver);
		
		txtGrossTotal = new JLabel("0");
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setBackground(new Color(255, 255, 255));
		txtGrossTotal.setBounds(922, 58, 159, 38);
		panelTable1.add(txtGrossTotal);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtSearchBy = new JTextField();
		txtSearchBy.setToolTipText("Search by...");
		txtSearchBy.setColumns(10);
		txtSearchBy.setBounds(41, 55, 287, 33);
		panelTable1.add(txtSearchBy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 198, 1046, 248);
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
				productNameCombox.setSelectedItem(model.getValueAt(Myindex, 2).toString());
				txtQty.setText(model.getValueAt(Myindex, 4).toString());	
				//expDateChooser.setDateFormatString(model.getValueAt(Myindex, 6).toString()); // this code is not functioning
			}
		});
		scrollPane.setViewportView(table);
		
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
		txtrS.setText("Change Mode\r\nLight/Dark");
		txtrS.setOpaque(false);
		txtrS.setForeground(Color.WHITE);
		txtrS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrS.setBounds(54, 161, 82, 38);
		panelShortcut1.add(txtrS);
		
		JTextArea txtrP = new JTextArea();
		txtrP.setText("Go to \r\nDashboard");
		txtrP.setOpaque(false);
		txtrP.setForeground(Color.WHITE);
		txtrP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrP.setBounds(65, 206, 82, 38);
		panelShortcut1.add(txtrP);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Receiving Manager", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(142, 11, 723, 169);
		panelTable3.add(panel);
		panel.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(637, 120, 63, 38);
		panel.add(btnUpdate);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int oldqty,id;
				double oldprice,newtotal;
				String selectedProduct = productNameCombox.getSelectedItem().toString();	
				//to get the receiving id of selected row			
				id = Integer.parseInt(txtSearchId.getText());						 	  				
  				oldqty = Integer.parseInt(txtQty.getText());//to get the current qty in the textfield		
  				
  				Date currentDate;
		  		 // Parsing the string date to java.sql.Date
			    String currentDateStr = lblCurrentDate.getText();
			    try {
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date javaUtilDate = sdf.parse(currentDateStr);
			        currentDate = new java.sql.Date(javaUtilDate.getTime());
			    } catch (ParseException ex) {
			        ex.printStackTrace();
			        // Handle the ParseException
			        return; // exit the method if date parsing fails
			    }
			    
		  		EDate = expDateChooser.getDate();
		  		MyExpDate = new java.sql.Date(EDate.getTime());		
		  		
		        if (id == -1) {
		            JOptionPane.showMessageDialog(null, "Please select a row to update.");
		            return;
		        }else {
		        	if(selectedProduct.isEmpty() && txtSearchId.getText().isEmpty() && txtQty.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing information!");
			  		}else {	 
			  			Map<String, Object> result = receivingEntryService.getAvailabilityAndPriceByProductDescription(selectedProduct);
				        //get the availablity and price from product
				        priceQuery = (double) result.get("ProductPrice");
				        supplierQuery = (String) result.get("Supplier");		  				
						
						oldprice = priceQuery;//to get the current price in the textfield
						newtotal = oldqty * oldprice; //to set the updated total by multiplying the current qty and price 
									
						ReceivingEntry receivingEntry = new ReceivingEntry(selectedProduct,priceQuery,oldqty,newtotal,MyExpDate,supplierQuery,currentDate,id);
						receivingEntryService.updateReceivingEntry(receivingEntry);
		  				displayGrossTotal();
						loadData();	
						
						txtSearchId.setText("");
						productNameCombox.setSelectedItem("");
						txtQty.setText("");
						expDateChooser.setDate(null);
						txtSearchId.requestFocus();	
			  		}
		        }	  						
			}
		});
		btnUpdate.setToolTipText("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnUpdate.setBackground(new Color(243, 243, 243));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(491, 120, 63, 38);
		panel.add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ensure that fields are not empty				
				if(txtSearchId.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Select a receive item to be deleted");
		  		}else {
		  			//get the id from fields
					int id = Integer.parseInt(txtSearchId.getText());
					
					//execute delete method
					receivingEntryService.deleteReceivingEntry(id);
					
					//diplay gross total
					displayGrossTotal();
					
					//load table data
					loadData();	
					
					//clear fields
					productNameCombox.setSelectedItem("");
					expDateChooser.setDate(null);
					txtQty.setText("");	
					txtSearchId.setText("");
					productNameCombox.requestFocus();					
		  	  }	
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Delete");
		btnDelete.setFocusPainted(false);
		btnDelete.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setBackground(new Color(243, 243, 243));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(418, 120, 63, 38);
		panel.add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedProduct;
		  		int qty;
		  		double tot;	
		  		Date currentDate;
		  		 // Parsing the string date to java.sql.Date
			    String currentDateStr = lblCurrentDate.getText();
			    try {
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date javaUtilDate = sdf.parse(currentDateStr);
			        currentDate = new java.sql.Date(javaUtilDate.getTime());
			    } catch (ParseException ex) {
			        ex.printStackTrace();
			        // Handle the ParseException
			        return; // exit the method if date parsing fails
			    }
		  		//select productname 
		  		selectedProduct = productNameCombox.getSelectedItem().toString();			
		  		qty = Integer.parseInt(txtQty.getText());	
		  		//for the exp date 
		  		EDate = expDateChooser.getDate(); 
		  		MyExpDate = new java.sql.Date(EDate.getTime());	
		  					
				if (selectedProduct.isEmpty() && txtSearchId.getText().isEmpty() && txtQty.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Missing information!");
				}else {			  					  		  																	  			  						 		  		
					Map<String, Object> result = receivingEntryService.getAvailabilityAndPriceByProductDescription(selectedProduct);
			        //get the availablity and price from product
			        priceQuery = (double) result.get("ProductPrice");
			        supplierQuery = (String) result.get("Supplier");										
				
					tot = qty * priceQuery;	  		
				  									
					//to insert the value encoded by the user into the database
				  	ReceivingEntry receivingEntry = new ReceivingEntry(selectedProduct,priceQuery,qty,tot,MyExpDate,supplierQuery,currentDate);
				  	receivingEntryService.addReceivingEntry(receivingEntry);					
					//diplay gross total to label
					displayGrossTotal();
					//load table data
					loadData();	
					//clear fields
					productNameCombox.setSelectedItem("");
					expDateChooser.setDate(null);
					txtQty.setText("");	
					txtSearchId.setText("");
					productNameCombox.requestFocus();	        						
							
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setBackground(new Color(243, 243, 243));
		
		txtSearchId = new JTextField();
		txtSearchId.setBounds(37, 34, 63, 31);
		panel.add(txtSearchId);
		txtSearchId.setToolTipText("Id");
		txtSearchId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSearchId.setColumns(10);
		
		productNameCombox = new JComboBox<String>();
		productNameCombox.setBounds(200, 34, 248, 31);
		panel.add(productNameCombox);
		productNameCombox.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		productNameCombox.setToolTipText("Code");
		productNameCombox.setMaximumRowCount(2);
		productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productNameCombox.setEditable(true);
		
		txtQty = new JTextField();
		txtQty.setBounds(200, 76, 212, 31);
		panel.add(txtQty);
		txtQty.setToolTipText("Qty");
		txtQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQty.setColumns(10);
		
		expDateChooser = new JDateChooser();
		expDateChooser.setBounds(586, 34, 114, 31);
		panel.add(expDateChooser);
		expDateChooser.setDateFormatString("d MM yyyy");
		expDateChooser.setToolTipText("Date");
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setForeground(Color.BLACK);
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExpiryDate.setBounds(502, 35, 74, 29);
		panel.add(lblExpiryDate);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setForeground(Color.BLACK);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduct.setBounds(134, 35, 51, 29);
		panel.add(lblProduct);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(134, 77, 56, 29);
		panel.add(lblQuantity);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(13, 35, 14, 29);
		panel.add(lblId);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				productNameCombox.setSelectedItem("");
				expDateChooser.setDate(null);
				txtQty.setText("");	
				txtSearchId.setText("");
				productNameCombox.requestFocus();	       
			}
		});
		btnClear.setToolTipText("Clear");
		btnClear.setFocusPainted(false);
		btnClear.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setBackground(new Color(243, 243, 243));
		btnClear.setBounds(564, 120, 63, 38);
		panel.add(btnClear);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JButton btnPrint = new JButton("Print");
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
		btnPrint.setBounds(127, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		JButton btnSendDb = new JButton("Process");
		btnSendDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//insert selected data into destination table(tblstock)
				receivingEntryService.reflectReceivingEntryToStock();
				loadData(); 					
			}
		});
		btnSendDb.setToolTipText("Send to Database");
		btnSendDb.setFocusPainted(false);
		btnSendDb.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSendDb.setBackground(new Color(64, 128, 128));
		btnSendDb.setBounds(63, 0, 63, 38);
		panelButtons.add(btnSendDb);
		
		JButton btnSaveFile = new JButton("Save");
		btnSaveFile.setToolTipText("Save as file");
		btnSaveFile.setFocusPainted(false);
		btnSaveFile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setBackground(new Color(64, 128, 128));
		btnSaveFile.setBounds(190, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		JButton btnMode = new JButton("Mode");
		btnMode.setToolTipText("Mode(Light/Dark)");
		btnMode.setFocusPainted(false);
		btnMode.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setBackground(new Color(64, 128, 128));
		btnMode.setBounds(253, 0, 63, 38);
		panelButtons.add(btnMode);		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Dashboard();
			}
		});
		btnBack.setToolTipText("Back to Dashboard");
		btnBack.setFocusPainted(false);
		btnBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBounds(0, 0, 63, 38);
		panelButtons.add(btnBack);
		
		JButton btnSearchBy = new JButton("");
		btnSearchBy.setIcon(new ImageIcon(ReceivingEntryForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setToolTipText("Add");
		btnSearchBy.setFocusPainted(false);
		btnSearchBy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setBackground(new Color(243, 243, 243));
		btnSearchBy.setBounds(327, 55, 63, 33);
		panelTable1.add(btnSearchBy);
		
	}
}
