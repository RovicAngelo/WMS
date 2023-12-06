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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.lanuza.wms.dao.PurchasedOrderDAO;
import com.lanuza.wms.dao.impl.PurchasedOrderDAOImpl;
import com.lanuza.wms.model.Customer;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.model.PurchasedOrder;
import com.lanuza.wms.service.PurchasedOrderService;
import com.lanuza.wms.service.impl.PurchasedOrderServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import javax.swing.ImageIcon;

public class PurchasedOrderForm {
	private final PurchasedOrderService purchasedOrderService;
	private final PurchasedOrderDAO purchasedOrderDAO;
	private JFrame frame;
	private JTextField txtQty,txtOrderId,txtSearchBy,txtAvailability,txtPrice;
	private JTable table;
	private JComboBox<String> productNameCombox;
	private JComboBox<String>CustomerNameCombox;
	private JLabel lblCurrentDate,txtGrossTotal;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int codeQuery;
	double temp = 0;
	java.util.Date EDate;
	java.sql.Date MyExpDate;
	
	PurchasedOrderForm() {
		this.purchasedOrderDAO = new PurchasedOrderDAOImpl();
		this.purchasedOrderService = new PurchasedOrderServiceImpl(purchasedOrderDAO);
		initialize();
		loadData();
		getDateToday();
		displayGrossTotal();	
		populateProductCombox();
		populateCustomerName();
	}	

	//to get the current date and display in lblCurrentDate
	private void getDateToday() { //method to get the date today
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();		
		lblCurrentDate.setText(dtf.format(now));
	}
	//to load the data in the table
	void loadData() {
		purchasedOrderService.tableLoad(table);
	}
	
	//to get the sum of product details 
	private void getProductDetail(String selectedProduct) {
		Map<String, Object> result = purchasedOrderService.getAvailabilityAndPriceByProductDescription(selectedProduct);
		//get the availability and price from method
        double newAvailableQty = (double) result.get("newAvailableQty");
        double productPrice = (double) result.get("productPrice");

        // UpdateUI components here,;
        txtAvailability.setText(String.valueOf(newAvailableQty));
        txtPrice.setText(String.valueOf(productPrice));            
      //to load the data in the table
	    loadData();
	}
	private void displayGrossTotal() {
        double sumOfTotal = purchasedOrderService.getSumOfTotal();
        // Update UI component (e.g., setText on a JTextField)
        txtGrossTotal.setText(String.valueOf(sumOfTotal));
	}  
	//to populate the productNameCombox with Desription attribute of tblproduct 
	private void populateProductCombox() {
	 	List<String> productDescriptions = purchasedOrderService.getAllProductDescription();
	 	
	 	for(String productDescription : productDescriptions) {
	 		productNameCombox.addItem(productDescription);
	 	} 	
	}
	//to populate the CustomerNameCombox with Name attribute of tblc 
	private void populateCustomerName() {
	 	List<String> customerNames = purchasedOrderService.getAllCustomerName();
	 	for(String customerName : customerNames) {
	 		CustomerNameCombox.addItem(customerName);
	 	}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		/*
		JPanel panelButtom = new JPanel();
		createPanelBorder(panelButtom,new Color(233, 233, 233),new Rectangle(76, 427, 1046, 215),null);
		frame.getContentPane().add(panelButtom);	
		*/
		JPanel panelButtom = new JPanel();
		createPanelBorder(panelButtom,new Color(233, 233, 233),new Rectangle(76, 427, 1046, 215),null);
		panelButtom.setBackground(new Color(233, 233, 233));
		panelButtom.setBounds(new Rectangle(76, 427, 1046, 215));
		panelButtom.setLayout(null);
		panelButtom.setOpaque(true);
		frame.getContentPane().add(panelButtom);

		/*
		JPanel panelLeft = new JPanel();
		createPanelBorder(panelLeft,new Color(233, 233, 233),new Rectangle(34, 187, 42, 455),null);
		frame.getContentPane().add(panelLeft);
		*/		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(new Color(233, 233, 233));
		panelLeft.setBounds(new Rectangle(34, 187, 42, 455));
		panelLeft.setLayout(null);
		panelLeft.setOpaque(true);
		frame.getContentPane().add(panelLeft);
		
		/*
		JPanel panelRight = new JPanel();
		createPanelBorder(panelRight,new Color(233, 233, 233),new Rectangle(1121, 187, 42, 455),null);
		frame.getContentPane().add(panelRight);
		*/
		JPanel panelRight = new JPanel();
		panelRight.setBackground(new Color(233, 233, 233));
		panelRight.setBounds(new Rectangle(1121, 187, 42, 455));
		panelRight.setLayout(null);
		panelRight.setOpaque(true);
		frame.getContentPane().add(panelRight);
		
		/*
		JPanel panelTop = new JPanel();
		createPanelBorder(panelTop,new Color(233, 233, 233),new Rectangle(34, 93, 1129, 95),null);
		frame.getContentPane().add(panelTop);
		*/
		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(233, 233, 233));
		panelTop.setBounds(new Rectangle(34, 93, 1129, 95));
		panelTop.setLayout(null);
		panelTop.setOpaque(true);
		frame.getContentPane().add(panelTop);
		
		/*
		JPanel panelProductStockDetails = new JPanel();
		createPanelBorder(panelProductStockDetails,new Color(240, 240, 240),new Rectangle(21, 71, 254, 109),null);
		panelProductStockDetails.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product in Stock Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelButtom.add(panelProductStockDetails);
		*/
		JPanel panelProductStockDetails = new JPanel();
		panelProductStockDetails.setBackground(new Color(240, 240, 240));
		panelProductStockDetails.setBounds(new Rectangle(21, 71, 254, 109));
		panelProductStockDetails.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product in Stock Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProductStockDetails.setLayout(null);	
		panelProductStockDetails.setOpaque(true);
		panelButtom.add(panelProductStockDetails);
				
		/*
		JPanel panelOrderManager = new JPanel();
		createPanelBorder(panelOrderManager,new Color(240, 240, 240),new Rectangle(387, 11, 600, 181),null);
		panelOrderManager.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelButtom.add(panelOrderManager);
		*/
		JPanel panelOrderManager = new JPanel();
		panelOrderManager.setBackground(new Color(240, 240, 240));
		panelOrderManager.setBounds(new Rectangle(387, 11, 600, 181));
		panelOrderManager.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelOrderManager.setLayout(null);	
		panelButtom.add(panelOrderManager);
		
		/*
		JPanel topPanel = new JPanel();
		createPanelBorder(topPanel,new Color(3, 65, 68),new Rectangle(0, 0, 1370, 54),null);
		frame.getContentPane().add(topPanel);	
		*/
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(new Rectangle(0, 0, 1370, 54));
		topPanel.setLayout(null);	
		frame.getContentPane().add(topPanel);
		
		/*
		JPanel bottomPanel = new JPanel();
		createPanelBorder(bottomPanel,new Color(3, 65, 68),new Rectangle(0, 641, 1370, 20),null);
		frame.getContentPane().add(bottomPanel);
		*/
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setLayout(null);	
		frame.getContentPane().add(bottomPanel);
		
		/*
		JPanel panelShortcut = new JPanel();
		createPanelBorder(panelShortcut,new Color(3, 65, 68),new Rectangle(1164, 54, 170, 607),null);
		frame.getContentPane().add(panelShortcut);
		*/
		JPanel panelShortcut = new JPanel();
		panelShortcut.setBackground(new Color(3, 65, 68));
		panelShortcut.setBounds(new Rectangle(1164, 54, 170, 607));
		panelShortcut.setLayout(null);	
		frame.getContentPane().add(panelShortcut);
		
		/*
		JPanel sidePanel1 = new JPanel();
		createPanelBorder(sidePanel1,new Color(3, 65, 68),new Rectangle(0, 54, 35, 684),null);
		frame.getContentPane().add(sidePanel1);
		*/
		JPanel sidePanel1 = new JPanel();
		sidePanel1.setBackground(new Color(3, 65, 68));
		sidePanel1.setBounds(new Rectangle(0, 54, 35, 684));
		sidePanel1.setLayout(null);	
		frame.getContentPane().add(sidePanel1);
		
		/*
		JPanel panelShortcut1 = new JPanel();
		createPanelBorder(panelShortcut1,new Color(3, 65, 68),new Rectangle(10, 39, 137, 491),null);
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setOpaque(false);
		panelShortcut.add(panelShortcut1);
		*/
		JPanel panelShortcut1 = new JPanel();
		panelShortcut1.setBackground(new Color(3, 65, 68));
		panelShortcut1.setBounds(new Rectangle(10, 39, 137, 491));
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setLayout(null);	
		panelShortcut1.setOpaque(false);
		panelShortcut.add(panelShortcut1);
		
		/*
		JPanel panelButtons = new JPanel();
		createPanelBorder(panelButtons,new Color(64, 128, 128),new Rectangle(34, 54, 1129, 38),null);
		frame.getContentPane().add(panelButtons);		
		*/
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(64, 128, 128));
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setLayout(null);	
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
		txtSearchBy.setBounds(43, 52, 304, 33);
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
			//get the selected product name
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedProduct =productNameCombox.getSelectedItem().toString();
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
		//using parameterized custom button to initialize new button	
		CustomButton btnProcess = new CustomButton(new Color(64, 128, 128), "Process Orders", this::reflectOrdersToStock, new Rectangle(0, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProcess.setText("Process");
		btnProcess.setLocation(64, 0);
		panelButtons.add(btnProcess);  
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setText("Print");
		btnPrint.setLocation(127, 0);
		panelButtons.add(btnPrint); 		
		
		CustomButton btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setText("Save");
		btnSaveFile.setLocation(190, 0);
		panelButtons.add(btnSaveFile);
							
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode",this::ChangeMode, new Rectangle(378, 0, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setText("Mode");
		btnMode.setLocation(253, 0);
		panelButtons.add(btnMode); 							
		
		CustomButton btnBack = new CustomButton(new Color(64, 128, 128), "Process Orders", (ActionListener) null, new Rectangle(0, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Dashboard();
			}
		});
		btnBack.setText("Back");
		btnBack.setBounds(0, 0, 63, 38);
		panelButtons.add(btnBack);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", null, new Rectangle(301, 52, 63, 33),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(PurchasedOrderForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(347, 52, 68, 33);
		panelTop.add(btnSearchBy); 						
		
		CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Add", this::addToStock, new Rectangle(296, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setText("Add");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnAdd);
					
		CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(442, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setText("Clear");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnClear);			
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Delete", this::deleteRow, new Rectangle(369, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setText("Delete");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnDelete);
		
		CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(515, 132, 63, 38),false,new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnUpdate.setText("Update");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOrderManager.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 187, 1046, 240);
		frame.getContentPane().add(scrollPane);
				
		table = new JTable();			
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	
		    	//to get the value(id) of selected row
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int Myindex = table.getSelectedRow();
		        
		        //get the values from fields
	            String id = model.getValueAt(Myindex, 0).toString();
		        String productName = model.getValueAt(Myindex, 2).toString();
		        int qty = Integer.parseInt(model.getValueAt(Myindex, 4).toString());
		        
		        //get the Availability And Price Based on ProductDescription selected
		        Map<String, Object> result = purchasedOrderService.getAvailabilityAndPriceByProductDescription(productName);
		        
		        //get the availablity and price from product
		        double newAvailableQty = (double) result.get("newAvailableQty");
		        double productPrice = (double) result.get("productPrice");
		        
		        //set the value of availability and price fields based on productname selected
		        txtAvailability.setText(String.valueOf(newAvailableQty));
                txtPrice.setText(String.valueOf(productPrice));
		        /*
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
		        */
                //clear fields
		        txtOrderId.setText(id);
		        productNameCombox.setSelectedItem(productName);
		        txtQty.setText(String.valueOf(qty));
		        CustomerNameCombox.setSelectedItem(model.getValueAt(Myindex, 6).toString());	 
		    }
		});
		scrollPane.setViewportView(table);						
								
	}	   
		 private void printTable(ActionEvent e) {
			 //print the order table
			 try {
					table.print();					
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}	
		 private void reflectOrdersToStock(ActionEvent e) {		
			 //subtract the table order values to table stock value based on productname
			 purchasedOrderService.reflectPurchaseOrderToStock();		
			 //load table data
			 loadData();
			}	
		 
		 private void addToStock(ActionEvent e) {
			    String customer, selectedProduct;
			    java.sql.Date currentDate;
			    int qty;
			    double price, tot,availability;
			    //get values from fields
			    selectedProduct = productNameCombox.getSelectedItem().toString();
			    customer = CustomerNameCombox.getSelectedItem().toString();
			    availability = Double.parseDouble(txtAvailability.getText());
			    qty = Integer.parseInt(txtQty.getText());
			    price = Double.parseDouble(txtPrice.getText());
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
			    //ensure fields are not empty before adding new order
			    if (selectedProduct.isEmpty() || customer.isEmpty() || txtQty.getText().isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Missing information!");
			    } else if (qty > availability) {
			        JOptionPane.showMessageDialog(null, "Please lower your selected quantity!");
			    } else {
			    	//get the total by multiplying quantity and price of table row
			        tot = qty * price;
			        //add order to table
					PurchasedOrder purchasedOrder = new PurchasedOrder(selectedProduct, price, qty, tot, customer, currentDate);
					purchasedOrderService.addPurchasedOrder(purchasedOrder);
					//display gross total in the label
					displayGrossTotal();
					//load table data
					loadData();
					//clear fields
					productNameCombox.setSelectedItem("");
					txtQty.setText("");
					txtAvailability.setText("");
					txtPrice.setText("");
					productNameCombox.requestFocus();
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
		        		//delete order row
		            	int id = Integer.parseInt(txtOrderId.getText());
		            	purchasedOrderService.deletePurchasedOrder(id);
		            	//show display the grossTotal in the label
		                displayGrossTotal();
		                //laod table data
		                loadData();
		                //clear fields
		                productNameCombox.setSelectedItem("");
		                txtQty.setText("");
		                txtAvailability.setText("");
		                txtPrice.setText("");
		                CustomerNameCombox.setSelectedItem("");
		                productNameCombox.requestFocus();
		        }
		 	}
		 private void updateRow(ActionEvent e) {
			 	String customer, selectedProduct;
		        int oldqty;
		        double newtotal,oldprice,price;
		        Date currentDate;
		        //get value from fields
		        customer = CustomerNameCombox.getSelectedItem().toString();
		        selectedProduct = productNameCombox.getSelectedItem().toString();
		        oldqty = Integer.parseInt(txtQty.getText());
		        price = Double.parseDouble(txtPrice.getText());
		        // Get the ID from the selected row
		        int id = Integer.parseInt(txtOrderId.getText());	
		        
		        String currentDateStr = lblCurrentDate.getText();
		        // Parsing the string date to java.sql.Date
			    try {
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date javaUtilDate = sdf.parse(currentDateStr);
			        currentDate = new java.sql.Date(javaUtilDate.getTime());
			    } catch (ParseException ex) {
			        ex.printStackTrace();
			        // Handle the ParseException
			        return; // exit the method if date parsing fails
			    }
		        // Ensure a row is selected
		        if (id == -1) {
		            JOptionPane.showMessageDialog(null, "Please select a row to update.");
		            return;
		        }
		        //Ensure fields is not empty before executing updating
		        if (selectedProduct.isEmpty() && txtOrderId.getText().isEmpty() && txtQty.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Missing information!");
		        } else {
		        		//get the new total by multiplying qty and price from table row
		                oldprice = price;
		                newtotal = oldqty * oldprice;
		                // Update Order
		                PurchasedOrder purchasedOrder = new PurchasedOrder(selectedProduct,price,oldqty,newtotal,customer,currentDate,id);
		                purchasedOrderService.updatePurchasedOrder(purchasedOrder);	                		                	                
		                // Update the gross total label		                
		               double sumOfTotal  = purchasedOrderService.getSumOfTotal();	
		               txtGrossTotal.setText(String.valueOf(sumOfTotal));
		               	//load table data
		                loadData();
		                //clear fields 
		                txtOrderId.setText("");
		                productNameCombox.setSelectedItem("");
		                txtQty.setText("");
		                txtOrderId.requestFocus();
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
			panel.setOpaque(true);
			panel.setPreferredSize(new Dimension(bounds.width, bounds.height)); // Set preferred size
		   }	
}
