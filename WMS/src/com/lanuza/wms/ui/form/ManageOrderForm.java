package com.lanuza.wms.ui.form;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.PurchasedOrderDAO;
import com.lanuza.wms.dao.impl.PurchasedOrderDAOImpl;
import com.lanuza.wms.model.PurchasedOrder;
import com.lanuza.wms.service.PurchasedOrderService;
import com.lanuza.wms.service.impl.PurchasedOrderServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;


public class ManageOrderForm extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private final PurchasedOrderService purchasedOrderService;
	private final PurchasedOrderDAO purchasedOrderDAO;
	
	private JTextField textField,txtOrderId,txtQuantity,txtAvailability,txtPrice;
	private Table table;
	private JComboBox<String> productNameCombox,CustomerNameCombox;
	private JLabel lblCurrentDate,txtGrossTotal;

	public ManageOrderForm(){	
		this.purchasedOrderDAO = new PurchasedOrderDAOImpl();
		this.purchasedOrderService = new PurchasedOrderServiceImpl(purchasedOrderDAO);
		this.productNameCombox = new JComboBox<String>();
		this.CustomerNameCombox = new JComboBox<String>();
		this.txtAvailability = new JTextField();
		this.txtPrice = new JTextField();
		this.txtQuantity = new JTextField();
		this.txtOrderId = new JTextField();
		initialize();
		loadData();
		getDateToday();
		displayGrossTotal();	
		populateProductCombox();
		populateCustomerName();	
	}
		private void initialize(){
		setLayout(null);	
		
		textField = new JTextField();
		textField.setToolTipText("Search by...");
		textField.setColumns(10);
		textField.setBounds(22, 27, 304, 33);
		add(textField);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageOrderForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(326, 27, 68, 33);
		add(btnSearchBy);
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setBackground(new Color(255, 255, 255));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setRound(10);
		roundPanel.setBounds(22, 71, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		
		table = new Table();
		
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	            	tableRowSelection();
	            }
	        });
		//table.getFocusTraversalKeysEnabled();
		//table.setFocusable(true);
		scrollPane.setViewportView(table);			
		
		JButton btnAddProduct = new JButton("New Order");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreateProductPopup();
			}
		});
		btnAddProduct.setToolTipText("Add");
		btnAddProduct.setFocusPainted(false);
		btnAddProduct.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAddProduct.setBackground(new Color(243, 243, 243));
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setForeground(Color.BLACK);
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtOrderId = new JTextField();
		txtOrderId.setToolTipText("Id");
		txtOrderId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtOrderId.setEditable(false);
		txtOrderId.setColumns(10);
		txtOrderId.setBackground(SystemColor.menu);
					
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtOrderId.getText().toString().equals("")) {
			 		JOptionPane.showMessageDialog(null, "Please select a row to enable editing");
			 	}else {
				showUpdateProductPopup();
			 	}
			}
		});
		btnEdit.setToolTipText("Edit Row");
		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEdit.setBackground(new Color(243, 243, 243));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		txtGrossTotal = new JLabel("0.0");
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setBackground(Color.WHITE);
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Update", this::deleteRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("Delete");
		btnDelete.setText("Delete");
		GroupLayout gl_roundPanel = new GroupLayout(roundPanel);
		gl_roundPanel.setHorizontalGroup(
			gl_roundPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblOrderId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtOrderId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 481, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap(567, Short.MAX_VALUE)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblOrderId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtOrderId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		roundPanel.setLayout(gl_roundPanel);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(926, 0, 170, 560);
		add(sidePanel2);
		
		JPanel panelShortcut1 = new JPanel();
		panelShortcut1.setLayout(null);
		panelShortcut1.setOpaque(false);
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setBounds(10, 39, 137, 491);
		sidePanel2.add(panelShortcut1);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(36, 6, 78, 16);
		panelShortcut1.add(lblNewLabel_1);
		
		JLabel lblCtrlS = new JLabel("Ctrl+S");
		lblCtrlS.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlS.setForeground(Color.WHITE);
		lblCtrlS.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		
		JTextArea txtrCtrlS = new JTextArea();
		txtrCtrlS.setText("Save as\r\n File");
		txtrCtrlS.setOpaque(false);
		txtrCtrlS.setForeground(Color.WHITE);
		txtrCtrlS.setFont(new Font("Monospaced", Font.PLAIN, 12));
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
		
		lblCurrentDate = new JLabel("2023-12-10");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(791, 0, 93, 40);
		add(lblCurrentDate);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(749, 4, 40, 33);
		add(lblDate);
   		
	}
	
	 private void showCreateProductPopup() {		 	
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Place New Order");
	        createProductDialog.setSize( 660, 335);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel mainPanel = new JPanel();
			//mainPanel.setBounds(190, 151, 631, 298);
			//add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel StockInfoPanel = new JPanel();
			StockInfoPanel.setBorder(new TitledBorder(null, "Product in Stock Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			StockInfoPanel.setBounds(10, 11, 199, 276);
			mainPanel.add(StockInfoPanel);
			StockInfoPanel.setLayout(null);
			
			JLabel lblAvailability = new JLabel("Availability");
			lblAvailability.setBounds(10, 83, 80, 29);
			lblAvailability.setForeground(Color.BLACK);
			lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
			StockInfoPanel.add(lblAvailability);
			
			txtAvailability = new JTextField();
			txtAvailability.setBounds(91, 82, 98, 31);
			txtAvailability.setToolTipText("Quantity in stock");
			txtAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtAvailability.setColumns(10);
			txtAvailability.setBackground(SystemColor.menu);
			StockInfoPanel.add(txtAvailability);
			
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setBounds(20, 125, 37, 29);
			lblPrice.setForeground(Color.BLACK);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			StockInfoPanel.add(lblPrice);
			
			txtPrice = new JTextField();
			txtPrice.setBounds(91, 124, 98, 31);
			txtPrice.setToolTipText("Product Price");
			txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtPrice.setEditable(true);
			txtPrice.setColumns(10);
			txtPrice.setBackground(SystemColor.menu);
			StockInfoPanel.add(txtPrice);
			
			JPanel orderManagerPanel = new JPanel();
			orderManagerPanel.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			orderManagerPanel.setBounds(219, 11, 402, 276);
			mainPanel.add(orderManagerPanel);
			orderManagerPanel.setLayout(null);
			
			JLabel lblProductName = new JLabel("Product Name");
			lblProductName.setForeground(Color.BLACK);
			lblProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblProductName.setBounds(23, 67, 90, 29);
			orderManagerPanel.add(lblProductName);
			
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
			productNameCombox.setToolTipText("Product Code");
			productNameCombox.setMaximumRowCount(2);
			productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			productNameCombox.setEditable(true);
			productNameCombox.setBounds(123, 66, 243, 31);
			orderManagerPanel.add(productNameCombox);
			
			JLabel lblCustomer = new JLabel("Customer");
			lblCustomer.setForeground(Color.BLACK);
			lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCustomer.setBounds(39, 120, 63, 29);
			orderManagerPanel.add(lblCustomer);
			
			CustomerNameCombox = new JComboBox<String>();
			CustomerNameCombox.setToolTipText("Customer Name");
			CustomerNameCombox.setMaximumRowCount(2);
			CustomerNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			CustomerNameCombox.setEditable(true);
			CustomerNameCombox.setBounds(123, 119, 243, 31);
			orderManagerPanel.add(CustomerNameCombox);
			
			JLabel lblQuantity = new JLabel("Quantity");
			lblQuantity.setForeground(Color.BLACK);
			lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblQuantity.setBounds(39, 168, 63, 29);
			orderManagerPanel.add(lblQuantity);
			
			txtQuantity = new JTextField();
			txtQuantity.setToolTipText("Qty");
			txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtQuantity.setColumns(10);
			txtQuantity.setBounds(123, 167, 126, 31);
			orderManagerPanel.add(txtQuantity);
			
			CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Add", this::addToStock, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnAdd.setText("Add");
			btnAdd.setBounds(157, 227, 63, 38);
			orderManagerPanel.add(btnAdd);
			
			CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Delete", (ActionListener) null, new Rectangle(369, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnCancel.setText("Cancel");
			btnCancel.setBounds(303, 227, 63, 38);
			orderManagerPanel.add(btnCancel);
			
			CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(442, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnClear.setText("Clear");
			btnClear.setBounds(230, 227, 63, 38);
			orderManagerPanel.add(btnClear);     

	        // Add action listener to the Save button
	        btnAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation (you can customize this part)
	                // For simplicity, just dispose the dialog in this example
	                createProductDialog.dispose();
	            }
	        });

	        // Add action listener to the Cancel button
	        btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Close the dialog when Cancel is clicked
	                createProductDialog.dispose();
	            }
	        });

	        // Add the popupPanel to the dialog's content pane
	        createProductDialog.getContentPane().add(mainPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        populateProductCombox();
			populateCustomerName();
	    }
	 
	 private void showUpdateProductPopup() {		 	
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Update Order");
	        createProductDialog.setSize( 660, 335);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel mainPanel = new JPanel();
			//mainPanel.setBounds(190, 151, 631, 298);
			//add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel StockInfoPanel = new JPanel();
			StockInfoPanel.setBorder(new TitledBorder(null, "Product in Stock Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			StockInfoPanel.setBounds(10, 11, 199, 276);
			mainPanel.add(StockInfoPanel);
			StockInfoPanel.setLayout(null);
			
			JLabel lblAvailability = new JLabel("Availability");
			lblAvailability.setBounds(10, 83, 80, 29);
			lblAvailability.setForeground(Color.BLACK);
			lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
			StockInfoPanel.add(lblAvailability);
			
			txtAvailability = new JTextField();
			txtAvailability.setBounds(91, 82, 98, 31);
			txtAvailability.setToolTipText("Quantity in stock");
			txtAvailability.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtAvailability.setColumns(10);
			txtAvailability.setBackground(SystemColor.menu);
			StockInfoPanel.add(txtAvailability);
			
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setBounds(20, 125, 37, 29);
			lblPrice.setForeground(Color.BLACK);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			StockInfoPanel.add(lblPrice);
			
			txtPrice = new JTextField();
			txtPrice.setBounds(91, 124, 98, 31);
			txtPrice.setToolTipText("Product Price");
			txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtPrice.setEditable(true);
			txtPrice.setColumns(10);
			txtPrice.setBackground(SystemColor.menu);
			StockInfoPanel.add(txtPrice);
			
			JPanel orderManagerPanel = new JPanel();
			orderManagerPanel.setBorder(new TitledBorder(null, "Order Manager", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			orderManagerPanel.setBounds(219, 11, 402, 276);
			mainPanel.add(orderManagerPanel);
			orderManagerPanel.setLayout(null);
			
			JLabel lblProductName = new JLabel("Product Name");
			lblProductName.setForeground(Color.BLACK);
			lblProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblProductName.setBounds(23, 67, 90, 29);
			orderManagerPanel.add(lblProductName);
			
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
			productNameCombox.setToolTipText("Product Code");
			productNameCombox.setMaximumRowCount(2);
			productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			productNameCombox.setEditable(true);
			productNameCombox.setBounds(123, 66, 243, 31);
			orderManagerPanel.add(productNameCombox);
			
			JLabel lblCustomer = new JLabel("Customer");
			lblCustomer.setForeground(Color.BLACK);
			lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCustomer.setBounds(39, 120, 63, 29);
			orderManagerPanel.add(lblCustomer);
			
			CustomerNameCombox = new JComboBox<String>();
			CustomerNameCombox.setToolTipText("Customer Name");
			CustomerNameCombox.setMaximumRowCount(2);
			CustomerNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			CustomerNameCombox.setEditable(true);
			CustomerNameCombox.setBounds(123, 119, 243, 31);
			orderManagerPanel.add(CustomerNameCombox);
			
			JLabel lblQuantity = new JLabel("Quantity");
			lblQuantity.setForeground(Color.BLACK);
			lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblQuantity.setBounds(39, 168, 63, 29);
			orderManagerPanel.add(lblQuantity);
			
			txtQuantity = new JTextField();
			txtQuantity.setToolTipText("Qty");
			txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtQuantity.setColumns(10);
			txtQuantity.setBounds(123, 167, 126, 31);
			orderManagerPanel.add(txtQuantity);
			
			CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Add", this::updateRow, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnUpdate.setText("Update");
			btnUpdate.setBounds(157, 227, 63, 38);
			orderManagerPanel.add(btnUpdate);
			
			CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Delete", (ActionListener) null, new Rectangle(369, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnCancel.setText("Cancel");
			btnCancel.setBounds(303, 227, 63, 38);
			orderManagerPanel.add(btnCancel);
			
			CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(442, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnClear.setText("Clear");
			btnClear.setBounds(230, 227, 63, 38);
			orderManagerPanel.add(btnClear);     

	        // Add action listener to the Save button
			btnUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation (you can customize this part)
	                // For simplicity, just dispose the dialog in this example
	                createProductDialog.dispose();
	            }
	        });

	        // Add action listener to the Cancel button
	        btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Close the dialog when Cancel is clicked
	                createProductDialog.dispose();
	            }
	        });

	        // Add the popupPanel to the dialog's content pane
	        createProductDialog.getContentPane().add(mainPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        //populateProductCombox();
			populateCustomerName();
			tableRowSelection();
	    }
	 
	 private void printTable(ActionEvent e) {
		 //print the order table
		 try {
				table.print();					
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}	
	 public void reflectOrdersToStock(ActionEvent e) {		
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
		    qty = Integer.parseInt(txtQuantity.getText());
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
		    if (selectedProduct.isEmpty() || customer.isEmpty() || txtQuantity.getText().isEmpty()) {
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
				txtQuantity.setText("");
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
			txtQuantity.setText("");
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
	                txtOrderId.setText("");
	                txtQuantity.setText("");
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
	        oldqty = Integer.parseInt(txtQuantity.getText());
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
	        if (selectedProduct.isEmpty() && txtOrderId.getText().isEmpty() && txtQuantity.getText().isEmpty()) {
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
	                productNameCombox.setSelectedItem("");
	                txtOrderId.setText("");
	                txtQuantity.setText("");
	                txtAvailability.setText("");
	                txtPrice.setText("");
	                CustomerNameCombox.setSelectedItem("");
	                productNameCombox.requestFocus();
	        }
	 	}
	 private void saveAsFile(ActionEvent e) {
		// Logic for saving as a file 
		}
	 private void ChangeMode(ActionEvent e) {
		// Logic for toggling between Light/Dark mode
		}
	 
	 private void getDateToday() { //method to get the date today
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();		
			lblCurrentDate.setText(dtf.format(now));
		}
		//to load the data in the table
	 public void loadData() {
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
		
		private void tableRowSelection() {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    int rowIndex = table.getSelectedRow();

		    // Check if a row is selected
		    if (rowIndex != -1) {
		        // for text field named txtId
		        String id = model.getValueAt(rowIndex, 0) != null ? model.getValueAt(rowIndex, 0).toString() : "";
		        txtOrderId.setText(id);

		        // for text field named txtProductName
		        String productName = model.getValueAt(rowIndex, 1) != null ? model.getValueAt(rowIndex, 1).toString() : "";
		        productNameCombox.setSelectedItem(productName);

		        // for text field named txtQuantity
		        String qtyString = model.getValueAt(rowIndex, 3) != null ? model.getValueAt(rowIndex, 3).toString() : "0";
		        int qty = Integer.parseInt(qtyString);
		        txtQuantity.setText(String.valueOf(qty));

		        // get the Availability And Price Based on ProductDescription selected
		        Map<String, Object> result = purchasedOrderService.getAvailabilityAndPriceByProductDescription(productName);

		        // check if result is not null before accessing values
		        if (result != null) {
		            // get the availability and price from product
		            double newAvailableQty = (double) result.get("newAvailableQty");
		            double productPrice = (double) result.get("productPrice");

		            // set the value of availability and price fields based on product name selected
		            txtAvailability.setText(String.valueOf(newAvailableQty));
		            txtPrice.setText(String.valueOf(productPrice));
		        } else {
		            // Handle the case where the result is null (e.g., clear fields).
		            txtAvailability.setText("");
		            txtPrice.setText("");
		        }

		        // for combo box named CustomerNameCombox
		        String customerName = model.getValueAt(rowIndex, 5) != null ? model.getValueAt(rowIndex, 5).toString() : "";
		        CustomerNameCombox.setSelectedItem(customerName);
		    } else {
		        // Handle the case where no row is selected (e.g., clear fields).
		        txtOrderId.setText("");
		        productNameCombox.setSelectedItem(null);
		        txtQuantity.setText("");
		        txtAvailability.setText("");
		        txtPrice.setText("");
		        CustomerNameCombox.setSelectedItem(null);
		    }
		}

		
}