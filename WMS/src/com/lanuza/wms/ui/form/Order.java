package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.ui.components.ButtonManager;

import net.proteanit.sql.DbUtils;

public class Order {

	private JFrame frame;
	private JTextField txtQty,txtOrderId,txtSearchBy,txtAvailability,txtPrice;
	private JTable table;
	private JComboBox<String> productNameCombox,CustomerNameCombox;
	private JLabel lblCurrentDate,txtGrossTotal;
	int codeQuery;
	
	Order() {
		initialize();
		Connect();
		table_load();
		getDateToday();
		populateProductComboBox();
		populateCustomerCombox();
		getGrossTotal();
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
			rs = st.executeQuery("Select * from phildrinksdb.tblorder");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void populateProductComboBox() {
	    try {
	        pst = con.prepareStatement("select ProductDescription from phildrinksdb.tblproduct");
	        rs = pst.executeQuery();

	        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	        while (rs.next()) {
	            model.addElement(rs.getString("ProductDescription"));
	        }
	        productNameCombox.setModel(model);
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception appropriately
	    }
	}

	private void getProductDetail(String selectedProduct) {
	    int currentQty = 0; // Initialize currentQty outside try blocks
	    int availability, price, newAvailableQty;
	    
	    try {
	        // Query to get the total quantity ordered for the selected product
	        pst = con.prepareStatement("SELECT SUM(Qty) AS TotalQty FROM phildrinksdb.tblorder WHERE ProductDescription = ?");
	        pst.setString(1, selectedProduct);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            currentQty = rs.getInt("TotalQty");
	        }
	        pst.close();
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception appropriately
	    }

	    try {
	        // Query to get the total quantity and maximum price from the stock for the selected product
	        pst = con.prepareStatement("SELECT SUM(Qty) AS TotalQty, MAX(ProductPrice) AS MaxPrice FROM phildrinksdb.tblstock WHERE ProductDescription = ?");
	        pst.setString(1, selectedProduct);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            availability = rs.getInt("TotalQty");
	            price = rs.getInt("MaxPrice");

	            newAvailableQty = availability - currentQty;

	            txtAvailability.setText(String.valueOf(newAvailableQty));
	            txtPrice.setText(String.valueOf(price));
	        }
	        pst.close();
	       
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception appropriately
	    }
	    table_load();
	}

	
	private void populateCustomerCombox() {
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

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panelButtom = new JPanel();
		createPanelBorder(panelButtom,new Color(233, 233, 233),new Rectangle(76, 427, 1046, 215),null);
		frame.getContentPane().add(panelButtom);	
	
		JPanel panelLeft = new JPanel();
		createPanelBorder(panelLeft,new Color(233, 233, 233),new Rectangle(34, 187, 42, 455),null);
		frame.getContentPane().add(panelLeft);
		
		JPanel panelRight = new JPanel();
		createPanelBorder(panelRight,new Color(233, 233, 233),new Rectangle(1121, 187, 42, 455),null);
		frame.getContentPane().add(panelRight);
		
		JPanel panelTop = new JPanel();
		createPanelBorder(panelTop,new Color(233, 233, 233),new Rectangle(34, 93, 1129, 95),null);
		frame.getContentPane().add(panelTop);
		
		JPanel panelProductStockDetails = new JPanel();
		createPanelBorder(panelProductStockDetails,new Color(240, 240, 240),new Rectangle(21, 71, 254, 109),null);
		panelProductStockDetails.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product in Stock Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelButtom.add(panelProductStockDetails);
		
		JPanel panelOrderManager = new JPanel();
		createPanelBorder(panelOrderManager,new Color(240, 240, 240),new Rectangle(387, 11, 600, 181),null);
		panelOrderManager.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelButtom.add(panelOrderManager);
		
		JPanel topPanel = new JPanel();
		createPanelBorder(topPanel,new Color(3, 65, 68),new Rectangle(0, 0, 1370, 54),null);
		frame.getContentPane().add(topPanel);	
		
		JPanel bottomPanel = new JPanel();
		createPanelBorder(bottomPanel,new Color(3, 65, 68),new Rectangle(0, 641, 1370, 20),null);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panelShortcut = new JPanel();
		createPanelBorder(panelShortcut,new Color(3, 65, 68),new Rectangle(1164, 54, 170, 607),null);
		frame.getContentPane().add(panelShortcut);
		
		JPanel sidePanel1 = new JPanel();
		createPanelBorder(sidePanel1,new Color(3, 65, 68),new Rectangle(0, 54, 35, 684),null);
		frame.getContentPane().add(sidePanel1);
		
		JPanel panelShortcut1 = new JPanel();
		createPanelBorder(panelShortcut1,new Color(3, 65, 68),new Rectangle(10, 39, 137, 491),null);
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setOpaque(false);
		panelShortcut.add(panelShortcut1);
		
		JPanel panelButtons = new JPanel();
		createPanelBorder(panelButtons,new Color(64, 128, 128),new Rectangle(34, 54, 1129, 38),null);
		frame.getContentPane().add(panelButtons);		
		
		txtAvailability = new JTextField();
		txtAvailability.setBackground(new Color(240, 240, 240));
		txtAvailability.setBounds(91, 24, 136, 31);
		panelProductStockDetails.add(txtAvailability);
		txtAvailability.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtAvailability.setToolTipText("Quantity in stock");
		txtAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAvailability.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(true);
		txtPrice.setBackground(new Color(240, 240, 240));
		txtPrice.setBounds(91, 66, 136, 31);
		panelProductStockDetails.add(txtPrice);
		txtPrice.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtPrice.setToolTipText("Product Price");
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setBounds(453, 27, 126, 31);
		panelOrderManager.add(txtQty);
		txtQty.setToolTipText("Qty");
		txtQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQty.setColumns(10);
		
		txtOrderId = new JTextField();
		txtOrderId.setBounds(100, 11, 66, 31);
		panelButtom.add(txtOrderId);
		txtOrderId.setEditable(false);
		txtOrderId.setBackground(new Color(240, 240, 240));
		txtOrderId.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtOrderId.setToolTipText("Id");
		txtOrderId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtOrderId.setColumns(10);
		
		lblCurrentDate = new JLabel("");
		lblCurrentDate.setBounds(1026, 0, 93, 40);
		panelTop.add(lblCurrentDate);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setForeground(new Color(0, 0, 0));
		lblCurrentDate.setBackground(new Color(255, 255, 255));
		
		txtGrossTotal = new JLabel("0");
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setBackground(new Color(255, 255, 255));
		txtGrossTotal.setBounds(926, 48, 159, 41);
		panelTop.add(txtGrossTotal);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtSearchBy = new JTextField();
		txtSearchBy.setToolTipText("Search by...");
		txtSearchBy.setColumns(10);
		txtSearchBy.setBounds(43, 52, 248, 33);
		panelTop.add(txtSearchBy);
		
		JLabel lblOrderSection = new JLabel("Orders Section");
		lblOrderSection.setForeground(Color.WHITE);
		lblOrderSection.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrderSection.setBounds(588, 11, 167, 30);
		topPanel.add(lblOrderSection);	
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblGrossTotal.setBounds(808, 50, 116, 33);
		panelTop.add(lblGrossTotal);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(984, 4, 40, 33);
		panelTop.add(lblDate);
		
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setForeground(Color.BLACK);
		lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAvailability.setBounds(10, 25, 80, 29);
		panelProductStockDetails.add(lblAvailability);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrice.setBounds(20, 67, 37, 29);
		panelProductStockDetails.add(lblPrice);		
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(10, 28, 90, 29);
		panelOrderManager.add(lblProductName);
		lblProductName.setForeground(Color.BLACK);
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setForeground(Color.BLACK);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomer.setBounds(23, 81, 63, 29);
		panelOrderManager.add(lblCustomer);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(391, 28, 63, 29);
		panelOrderManager.add(lblQuantity);
		
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
		
		JLabel lblP = new JLabel("P");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setForeground(Color.WHITE);
		lblP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblP.setBounds(0, 213, 58, 28);
		panelShortcut1.add(lblP);
		
		JLabel lblM = new JLabel("M");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 259, 58, 28);
		panelShortcut1.add(lblM);
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setBounds(44, 12, 54, 29);
		panelButtom.add(lblOrderId);
		lblOrderId.setForeground(Color.BLACK);
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JLabel lblI = new JLabel("I");
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setForeground(Color.WHITE);
		lblI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblI.setBounds(0, 301, 58, 28);
		panelShortcut1.add(lblI);
								
		productNameCombox = new JComboBox<String>();						
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
		panelOrderManager.add(productNameCombox);
		productNameCombox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productNameCombox.setToolTipText("Product Code");
		productNameCombox.setMaximumRowCount(2);
		productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productNameCombox.setEditable(true);
			
		CustomerNameCombox = new JComboBox<String>();
		CustomerNameCombox.setBounds(102, 80, 251, 31);
		panelOrderManager.add(CustomerNameCombox);
		CustomerNameCombox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CustomerNameCombox.setToolTipText("Customer Name");
		CustomerNameCombox.setMaximumRowCount(2);
		CustomerNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		CustomerNameCombox.setEditable(true);																		
				
		JTextArea txtrCtrlS = new JTextArea();
		createTextArea(txtrCtrlS,"Save as\\n File",new Rectangle(65, 33, 82, 38));
		panelShortcut1.add(txtrCtrlS);
		
		JTextArea txtrCtrlP = new JTextArea();
		createTextArea(txtrCtrlP,"Print",new Rectangle(65, 82, 82, 28));
		panelShortcut1.add(txtrCtrlP);
		
		JTextArea txtrCtrlD = new JTextArea();
		createTextArea(txtrCtrlD,"Save to\\nDatabase",new Rectangle(65, 116, 82, 38));
		panelShortcut1.add(txtrCtrlD);
		
		JTextArea txtrS = new JTextArea();
		createTextArea(txtrS,"Go to\\nSuppliers",new Rectangle(65, 161, 82, 38));
		panelShortcut1.add(txtrS);
		
		JTextArea txtrP = new JTextArea();
		createTextArea(txtrP,"Go to\\nProducts",new Rectangle(65, 206, 82, 38));
		panelShortcut1.add(txtrP);
		
		JTextArea txtrM = new JTextArea();
		createTextArea(txtrM,"Change Mode\\n(Light/Dark)",new Rectangle(53, 252, 82, 38));
		panelShortcut1.add(txtrM);
								
		JTextArea txtrGoToInventory = new JTextArea();
		createTextArea(txtrGoToInventory,"Go to\\nInventory",new Rectangle(63, 298, 72, 38));
		panelShortcut1.add(txtrGoToInventory);		
			
		ButtonManager btnProcess = new ButtonManager("stock.png",new Color(64, 128, 128), "Process Orders", this::reflectOrdersToStock, new Rectangle(0, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnProcess);  
		
		ButtonManager btnPrint = new ButtonManager("stock.png",new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnPrint); 		
		
		ButtonManager btnSaveFile = new ButtonManager("stock.png",new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnSaveFile); 
		
		ButtonManager btnCustomer = new ButtonManager("stock.png",new Color(64, 128, 128), "Go to Customer", this::openCustomer, new Rectangle(189, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnCustomer); 
		
		ButtonManager btnProducts = new ButtonManager("stock.png",new Color(64, 128, 128), "Go to Product", this::openProduct, new Rectangle(252, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnProducts); 
				
		ButtonManager btnStock = new ButtonManager("stock.png",new Color(64, 128, 128), "Go to Stock", this::openStock, new Rectangle(315, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnStock); 
							
		ButtonManager btnMode = new ButtonManager("stock.png",new Color(64, 128, 128), "Change Mode",this::ChangeMode, new Rectangle(378, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelButtons.add(btnMode); 							
		
		ButtonManager btnSearchBy = new ButtonManager("search.png",new Color(243, 243, 243), "Search", null, new Rectangle(301, 52, 63, 33),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTop.add(btnSearchBy); 						
		
		ButtonManager btnAdd = new ButtonManager("2.png",new Color(243, 243, 243), "Add", this::addToStock, new Rectangle(296, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnAdd);
					
		ButtonManager btnClear = new ButtonManager("4.png",new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(442, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnClear);			
		
		ButtonManager btnDelete = new ButtonManager("6.png",new Color(243, 243, 243), "Delete", this::deleteRow, new Rectangle(369, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnDelete);
		
		ButtonManager btnUpdate = new ButtonManager("5.png",new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(515, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 187, 1046, 240);
		frame.getContentPane().add(scrollPane);
				
		table = new JTable();			
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int Myindex = table.getSelectedRow();

	            String id = model.getValueAt(Myindex, 0).toString();
		        String productName = model.getValueAt(Myindex, 2).toString();
		        int qty = Integer.parseInt(model.getValueAt(Myindex, 4).toString());

		        try {
		            // Query to get the total quantity ordered for the selected product
		            int currentQty = 0;
		            try (PreparedStatement pst = con.prepareStatement("SELECT SUM(Qty) AS TotalQty FROM phildrinksdb.tblorder WHERE ProductDescription = ?")) {
		                pst.setString(1, productName);
		                try (ResultSet rs = pst.executeQuery()) {
		                    while (rs.next()) {
		                        currentQty = rs.getInt("TotalQty");
		                    }
		                }
		            }

		            // Query to get the total quantity and maximum price from the stock for the selected product
		            try (PreparedStatement pst = con.prepareStatement("SELECT SUM(Qty) AS TotalQty, MAX(ProductPrice) AS MaxPrice FROM phildrinksdb.tblstock WHERE ProductDescription = ?")) {
		                pst.setString(1, productName);
		                try (ResultSet rs = pst.executeQuery()) {
		                    while (rs.next()) {
		                        int availability = rs.getInt("TotalQty");
		                        int price = rs.getInt("MaxPrice");

		                        int newAvailableQty = availability - currentQty;

		                        txtAvailability.setText(String.valueOf(newAvailableQty));
		                        txtPrice.setText(String.valueOf(price));
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace(); // Handle the exception appropriately (log or display an error message)
		        }

		        txtOrderId.setText(id);
		        productNameCombox.setSelectedItem(productName);
		        txtQty.setText(String.valueOf(qty));
		        CustomerNameCombox.setSelectedItem(model.getValueAt(Myindex, 6).toString());	 
		    }
		});
		scrollPane.setViewportView(table);						
								
	}
	   
		 private void openStock(ActionEvent e) {
		        new Stock();
		    } 
		 private void openProduct(ActionEvent e) {
		        new Product();
		    }	 
		 private void openCustomer(ActionEvent e) {
		        new Customer();
		    }
		 private void printTable(ActionEvent e) {
			 try {
					table.print();					
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}	
		 private void reflectOrdersToStock(ActionEvent e) {
			 String deleteQuery = "UPDATE tblstock s "
						+ "JOIN tblorder o ON s.ProductDescription = o.ProductDescription "
						+ "SET "
						+ "    s.Qty = CASE WHEN (s.Qty - o.Qty) < 0 THEN 0 ELSE (s.Qty - o.Qty) END, "
						+ "    s.Total = CASE WHEN (s.Total - o.Total) < 0 THEN 0 ELSE (s.Total - o.Total) END;"
						+ "";										
				try {			
					
					st = con.createStatement();
					st.executeUpdate(deleteQuery);
					st.close();
					JOptionPane.showMessageDialog(null, "Table data successfully modified stock");
					
					pst = con.prepareStatement("truncate table phildrinksdb.tblorder");
					pst.executeUpdate();
					pst.close();			
					
					table_load();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}	
		 private void addToStock(ActionEvent e) {
			 String customer;
		        int qty, price, tot, availability;
		        
		        String selectedProduct = (String) productNameCombox.getSelectedItem();
		        customer = CustomerNameCombox.getSelectedItem().toString();
		        availability = Integer.parseInt(txtAvailability.getText());
		        qty = Integer.parseInt(txtQty.getText());
		        price = Integer.parseInt(txtPrice.getText());
			  					  					
		        if (selectedProduct.isEmpty() || customer.isEmpty() || txtQty.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Missing information!");
		        } else if (qty > availability) {
		            JOptionPane.showMessageDialog(null, "Please lower your selected quantity!");					
		        } else {
		            try {
		                pst = con.prepareStatement("SELECT ProductCode FROM tblproduct WHERE ProductDescription = ?");
		                pst.setString(1, selectedProduct);
		                rs = pst.executeQuery();
		                
		                if (rs.next()) {
		                    codeQuery = rs.getInt("Code");
		                }
		                
		                pst.close();
		                tot = qty * price;
		                temp = temp + tot;
					  							  							 					  									
		                pst = con.prepareStatement("INSERT INTO tblorder(ProductCode, ProductDescription, ProductPrice, Qty, Total, Customer) VALUES (?, ?, ?, ?, ?, ?)");
		                pst.setInt(1, codeQuery);
		                pst.setString(2, selectedProduct);
		                pst.setInt(3, price);
		                pst.setInt(4, qty);
		                pst.setInt(5, tot);
		                pst.setString(6, customer);
		                pst.executeUpdate();
		                pst.close();
		                
		                getGrossTotal();
		                table_load();
		                productNameCombox.setSelectedItem("");
		                txtQty.setText("");
		                txtAvailability.setText("");
		                txtPrice.setText("");
		                productNameCombox.requestFocus();
																	
					}catch(SQLException el) {
							el.printStackTrace();
					}
				}
			}	
		 private void clearFields(ActionEvent e) {
			// Clearfields
				txtOrderId.setText("");
				txtAvailability.setText("");
				txtPrice.setText("");
				productNameCombox.setSelectedItem("");
				CustomerNameCombox.setSelectedItem("");
				txtQty.setText("");
		    }
		 private void deleteRow(ActionEvent e) {
			 if (txtOrderId.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Select an item to be deleted");
		        } else {
		            try {
		            	
		            	String id = txtOrderId.getText();
		            	
		                // Use PreparedStatement to prevent SQL injection
		                String query = "DELETE FROM phildrinksdb.tblorder WHERE OrderID = ?";
		                try (PreparedStatement pstDelete = con.prepareStatement(query)) {
		                    pstDelete.setInt(1, Integer.parseInt(id));
		                    pstDelete.executeUpdate();
		                }

		                getGrossTotal();
		                table_load();
		                JOptionPane.showMessageDialog(null, "Record Deleted Successfully");

		                productNameCombox.setSelectedItem("");
		                txtQty.setText("");
		                txtAvailability.setText("");
		                txtPrice.setText("");
		                CustomerNameCombox.setSelectedItem("");
		                productNameCombox.requestFocus();
		            } catch (SQLException ec) {
		                ec.printStackTrace();
		            }
		        }
		 	}
		 private void updateRow(ActionEvent e) {
			 String customer, selectedProduct;
		        int newtotal, oldqty, oldprice, price;

		        customer = (String) CustomerNameCombox.getSelectedItem();
		        selectedProduct = (String) productNameCombox.getSelectedItem();
		        oldqty = Integer.parseInt(txtQty.getText());
		        price = Integer.parseInt(txtPrice.getText());
		        // Get the ID from the selected row
		        int id = Integer.parseInt(txtOrderId.getText());			        	        		   
		        
		        // Ensure a row is selected
		        if (id == -1) {
		            JOptionPane.showMessageDialog(null, "Please select a row to update.");
		            return;
		        }

		        if (selectedProduct.isEmpty() && txtOrderId.getText().isEmpty() && txtQty.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Missing information!");
		        } else {
		            try {
		                // Query to get the product code based on the product description
		                pst = con.prepareStatement("SELECT ProductCode FROM tblproduct WHERE ProductDescription = ?");
		                pst.setString(1, selectedProduct);
		                rs = pst.executeQuery();

		                if (rs.next()) {
		                    codeQuery = rs.getInt("Code");
		                }

		                pst.close();

		                oldprice = price;
		                newtotal = oldqty * oldprice;

		                // Update query using a parameterized PreparedStatement
		                pst = con.prepareStatement("UPDATE phildrinksdb.tblorder SET ProductCode = ?, ProductDescription = ?, ProductPrice = ?, Qty = ?, Total = ?, Customer = ? WHERE OrderID = ?");
		                pst.setInt(1, codeQuery);
		                pst.setString(2, selectedProduct);
		                pst.setInt(3, price);
		                pst.setInt(4, oldqty);
		                pst.setInt(5, newtotal);
		                pst.setString(6, customer);
		                pst.setInt(7, id);
		                pst.executeUpdate();

		                JOptionPane.showMessageDialog(null, "Record Updated");

		                // Update the gross total
		                String sumOfTotal = "SELECT SUM(Total) FROM tblorder";
		                pst = con.prepareStatement(sumOfTotal);
		                rs = pst.executeQuery();

		                if (rs.next()) {
		                    String sum = rs.getString("sum(Total)");
		                    txtGrossTotal.setText(sum);
		                }

		                pst.close();
		                table_load();

		                txtOrderId.setText("");
		                productNameCombox.setSelectedItem("");
		                txtQty.setText("");
		                txtOrderId.requestFocus();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		 	}
		 private void saveAsFile(ActionEvent e) {
			// Logic for saving as a file 
			}
		 private void ChangeMode(ActionEvent e) {
			// Logic for toggling between Light/Dark mode
			}
		 
		private void createTextArea(JTextArea txtr,String text,Rectangle bounds) {		   
		    txtr.setLineWrap(true);
		    txtr.setWrapStyleWord(true);
		    txtr.setText(text.replace("\\n", System.lineSeparator())); // Use System.lineSeparator() for dynamic line breaks
			txtr.setOpaque(false);
			txtr.setForeground(Color.WHITE);
			txtr.setFont(new Font("Monospaced", Font.PLAIN, 12));
			txtr.setBounds(bounds);
		    txtr.setPreferredSize(new Dimension(bounds.width, bounds.height)); // Set preferred size
		   }	 	
		
		private void createPanelBorder(JPanel panel, Color color, Rectangle bounds, LayoutManager layout) {		   
			panel.setBackground(color);
			panel.setBounds(bounds);
			panel.setLayout(layout);
			panel.setVisible(true);
			panel.setOpaque(true);
			panel.setPreferredSize(new Dimension(bounds.width, bounds.height)); // Set preferred size
		   }	
}
