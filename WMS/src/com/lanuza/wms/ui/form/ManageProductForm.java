package com.lanuza.wms.ui.form;

import javax.swing.JPanel;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.lanuza.wms.dao.ProductDAO;
import com.lanuza.wms.dao.impl.ProductDAOImpl;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.service.ProductService;
import com.lanuza.wms.service.impl.ProductServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;

public class ManageProductForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ProductService productService;
	private final ProductDAO productDAO;
	private JTextField txtProductId;
	private JTextField textField_1;
	private JTextField txtDescription;
	private JTextField txtPrice;
	JLabel lblCurrentDate;
	private Table table;
	JComboBox<String> SupplierNameCombox;

	public ManageProductForm() {
		this.SupplierNameCombox = new JComboBox<String>();
        this.productDAO = new ProductDAOImpl();
        this.productService = new ProductServiceImpl(productDAO);
        this.txtDescription = new JTextField();
        this.txtPrice = new JTextField();
		initialize();
		loadData();
		populateSupplierCombox();
		
	}
	private void initialize() {
		setLayout(null);
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setRound(10);
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setBackground(Color.WHITE);
		roundPanel.setBounds(20, 155, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		
		JLabel lblProductId = new JLabel("Product Id");
		lblProductId.setForeground(Color.BLACK);
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtProductId = new JTextField();
		txtProductId.setToolTipText("Id");
		txtProductId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProductId.setEditable(false);
		txtProductId.setColumns(10);
		txtProductId.setBackground(SystemColor.menu);
		
		JButton btnAddProduct = new JButton("New Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreateProductPopup();
			}
		});
		btnAddProduct.setToolTipText("Add");
		btnAddProduct.setFocusPainted(false);
		btnAddProduct.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAddProduct.setBackground(new Color(243, 243, 243));
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtProductId.getText().toString().equals("")) {
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
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Delete", this::deleteRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("Delete");
		btnDelete.setText("Delete");
		
		JLabel lblTotalProduct = new JLabel("Total");
		lblTotalProduct.setForeground(Color.BLACK);
		lblTotalProduct.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		JLabel txtGrossTotal = new JLabel("0");
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setBackground(Color.WHITE);
		GroupLayout gl_roundPanel = new GroupLayout(roundPanel);
		gl_roundPanel.setHorizontalGroup(
			gl_roundPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblProductId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProductId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotalProduct, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblProductId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtProductId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalProduct, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new Table();
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tableSelectedRow();
			}
		});
		scrollPane.setViewportView(table);
		roundPanel.setLayout(gl_roundPanel);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Search by...");
		textField_1.setColumns(10);
		textField_1.setBounds(20, 111, 304, 33);
		add(textField_1);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageProductForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(324, 111, 68, 33);
		add(btnSearchBy);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 644, 1097, 20);
		add(bottomPanel);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(927, 83, 170, 581);
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
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setBackground(new Color(3, 65, 68));
		panelButtons.setBounds(0, 46, 1097, 38);
		add(panelButtons);
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setForeground(new Color(255, 255, 255));
		btnPrint.setBackground(new Color(3, 65, 68));
		btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPrint.setText("Print");
		btnPrint.setBounds(0, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		CustomButton btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setForeground(new Color(255, 255, 255));
		btnSaveFile.setBackground(new Color(3, 65, 68));
		btnSaveFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSaveFile.setText("Save");
		btnSaveFile.setBounds(62, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", this::ChangeMode, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setForeground(new Color(255, 255, 255));
		btnMode.setBackground(new Color(3, 65, 68));
		btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMode.setText("Mode");
		btnMode.setBounds(124, 0, 63, 38);
		panelButtons.add(btnMode);
		
		JLabel lblInfo = new JLabel("Product Section");
		lblInfo.setForeground(Color.BLACK);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfo.setBounds(0, 0, 170, 29);
		add(lblInfo);
		
		lblCurrentDate = new JLabel("2023-12-11");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(791, 91, 93, 40);
		add(lblCurrentDate);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(749, 95, 40, 33);
		add(lblDate);
				
	}
	
	 private void showCreateProductPopup() {
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Create New Product");
	        createProductDialog.setSize( 452, 297);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel createProductPanel = new JPanel();
			//createProductPanel.setBounds(282, 278, 452, 297);
			createProductPanel.setLayout(null);
			
			txtDescription = new JTextField();
			txtDescription.setToolTipText("Qty");
			txtDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDescription.setColumns(10);
			txtDescription.setBounds(123, 61, 253, 31);
			createProductPanel.add(txtDescription);
			
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setForeground(Color.BLACK);
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDescription.setBounds(45, 62, 79, 29);
			createProductPanel.add(lblDescription);
			
			CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Add", this::addRow, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnAdd.setText("Add");
			btnAdd.setBounds(166, 203, 63, 38);
			createProductPanel.add(btnAdd);
			
			CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Add", this::clearFields, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnClear.setText("Clear");
			btnClear.setBounds(239, 203, 63, 38);
			createProductPanel.add(btnClear);
			
			CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Add", (ActionListener) null, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnCancel.setText("Cancel");
			btnCancel.setBounds(313, 203, 63, 38);
			createProductPanel.add(btnCancel);
			
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setForeground(Color.BLACK);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPrice.setBounds(61, 104, 63, 29);
			createProductPanel.add(lblPrice);
			
			txtPrice = new JTextField();
			txtPrice.setToolTipText("Qty");
			txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtPrice.setColumns(10);
			txtPrice.setBounds(123, 103, 193, 31);
			createProductPanel.add(txtPrice);
			
			JLabel lblSupplier = new JLabel("Supplier");
			lblSupplier.setForeground(Color.BLACK);
			lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSupplier.setBounds(45, 145, 63, 29);
			createProductPanel.add(lblSupplier);
			
			SupplierNameCombox = new JComboBox<String>();
			SupplierNameCombox.setToolTipText("Customer Name");
			SupplierNameCombox.setMaximumRowCount(2);
			SupplierNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			SupplierNameCombox.setEditable(true);
			SupplierNameCombox.setBounds(124, 144, 251, 31);
			createProductPanel.add(SupplierNameCombox);   

	        // Add action listener to the Save button
	        btnAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation
	                // dispose the dialog
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
	        createProductDialog.getContentPane().add(createProductPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        populateSupplierCombox();
	    }
	 private void showUpdateProductPopup() {
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Update Product");
	        createProductDialog.setSize( 452, 297);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel createProductPanel = new JPanel();
			//createProductPanel.setBounds(282, 278, 452, 297);
			createProductPanel.setLayout(null);
			
			txtDescription = new JTextField();
			txtDescription.setToolTipText("Qty");
			txtDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDescription.setColumns(10);
			txtDescription.setBounds(123, 61, 253, 31);
			createProductPanel.add(txtDescription);
			
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setForeground(Color.BLACK);
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDescription.setBounds(45, 62, 79, 29);
			createProductPanel.add(lblDescription);
			
			CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnUpdate.setText("Add");
			btnUpdate.setBounds(166, 203, 63, 38);
			createProductPanel.add(btnUpdate);
			
			CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnClear.setText("Clear");
			btnClear.setBounds(239, 203, 63, 38);
			createProductPanel.add(btnClear);
			
			CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(296, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnCancel.setText("Cancel");
			btnCancel.setBounds(313, 203, 63, 38);
			createProductPanel.add(btnCancel);
			
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setForeground(Color.BLACK);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPrice.setBounds(61, 104, 63, 29);
			createProductPanel.add(lblPrice);
			
			txtPrice = new JTextField();
			txtPrice.setToolTipText("Qty");
			txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtPrice.setColumns(10);
			txtPrice.setBounds(123, 103, 193, 31);
			createProductPanel.add(txtPrice);
			
			JLabel lblSupplier = new JLabel("Supplier");
			lblSupplier.setForeground(Color.BLACK);
			lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSupplier.setBounds(45, 145, 63, 29);
			createProductPanel.add(lblSupplier);
			
			SupplierNameCombox = new JComboBox<String>();
			SupplierNameCombox.setToolTipText("Customer Name");
			SupplierNameCombox.setMaximumRowCount(2);
			SupplierNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			SupplierNameCombox.setEditable(true);
			SupplierNameCombox.setBounds(124, 144, 251, 31);
			createProductPanel.add(SupplierNameCombox);   

	        // Add action listener to the Save button
	        btnUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation
	                // dispose the dialog	   
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
	        createProductDialog.getContentPane().add(createProductPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        tableSelectedRow();
	        
	    }
	 private void loadData() {
	        // Call the tableLoad method from ProductService
	        productService.tableLoad(table);
	    }	
	 
	 private void populateSupplierCombox() {
		    List<String> supplierNames = productService.getAllSupplierName();

		    for (String supplierName : supplierNames) {
		    	SupplierNameCombox.addItem(supplierName);
		    }
		}
	 
	 private void tableSelectedRow() {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
	        int rowIndex = table.getSelectedRow();

	        // for text field named txtId
	        String id = model.getValueAt(rowIndex, 0).toString();
	        txtProductId.setText(id);

	        // for text fields named txtCode, txtDescription, and txtPrice
	        txtDescription.setText(model.getValueAt(rowIndex, 1).toString());
	        txtPrice.setText(model.getValueAt(rowIndex, 2).toString());

	        // for combo box named supplierCombox
	        SupplierNameCombox.setSelectedItem(model.getValueAt(rowIndex, 3).toString());
	    }
	 private void addRow(ActionEvent e) {
		 if(txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
	  		}else {
	  		String productDescription,supplierName;
	  		int qty= 0;	//this is an attribute of tblstock to be transfer as having 0 as initial 
	  		Double productPrice,total=0.0;
	        //text fields and combo box components
	  			productDescription = txtDescription.getText();
		  		productPrice =  Double.parseDouble(txtPrice.getText());
		  		supplierName = SupplierNameCombox.getSelectedItem().toString();	

		        // Create a Product object with the input data
		        Product product = new Product(productDescription, productPrice,qty,total, supplierName);

		        // Call the addProduct method from the ProductService
		        productService.addProduct(product);		
		        
		        productService.transferDataAndSetDefaults(product);
		        			        
		        // Load the table
		        loadData();
		        //clear the text fields
		        txtProductId.setText("");
		        txtDescription.setText("");
		        txtPrice.setText("");
		        SupplierNameCombox.setSelectedItem("");
	  		}		    	
		 
	 }
	 
	 private void updateRow(ActionEvent e) {
		 if(txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
	  		}else {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            int productId = (int) table.getValueAt(selectedRow, 0);

	            //text fields for editing
	            String productDescription = txtDescription.getText();
	            double productPrice = Double.parseDouble(txtPrice.getText());
	            int qty = 0;
	            double total = 0.0;
	            String supplier = SupplierNameCombox.getSelectedItem().toString();

	            Product updatedProduct = new Product(productDescription, productPrice, qty, total, supplier,productId);
	            productService.updateProduct(updatedProduct);		           
	            
	            //load table
	            loadData();
	            // Clear the fields 
	            txtProductId.setText("");
	            txtDescription.setText("");
	            txtPrice.setText("");	
	            SupplierNameCombox.setSelectedItem("");
	            // ...
	        } else {
	            JOptionPane.showMessageDialog(null, "Please select a product to update");
	        }
	  	 }
		 
	 }
	 
	 private void deleteRow(ActionEvent e) {
		 
		 if(txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Select a product to be deleted");
	  		}else {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int productId = (int) table.getValueAt(selectedRow, 0);
		            productService.deleteProduct(productId);
		            //show a message
		            JOptionPane.showMessageDialog(null, "Product deleted successfully");
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a product to delete");
		        }
		        // Load the table
		        loadData();
		        
		        // Clear the fields 
	            txtProductId.setText("");
	            txtDescription.setText("");
	            txtPrice.setText("");	
	            SupplierNameCombox.setSelectedItem("");
	  	 }
	 }
	 
	 private void clearFields(ActionEvent e) {
		  	txtProductId.setText("");
	        txtDescription.setText("");
	        txtPrice.setText("");
	        SupplierNameCombox.setSelectedItem("");		 
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
	 private void printTable(ActionEvent e) {
		 //print the order table
		 try {
				table.print();					
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}	 
}
