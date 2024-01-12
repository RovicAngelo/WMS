package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.SupplierDAO;
import com.lanuza.wms.dao.impl.SupplierDAOImpl;
import com.lanuza.wms.model.Supplier;
import com.lanuza.wms.service.SupplierService;
import com.lanuza.wms.service.impl.SupplierServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;

public class ManageSupplierForm extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private SupplierDAO supplierDAO;
	private SupplierService supplierService;
	
	private JTextField txtSupplierId;
	private JTextField textField_1, txtSupplierName,txtPhoneNo;
	private Table table;
	private JLabel lblCurrentDate, txtTotalItem;

	/**
	 * Create the panel.
	 */
	public ManageSupplierForm() {
		this.supplierDAO = new SupplierDAOImpl();
		this.supplierService = new SupplierServiceImpl(supplierDAO);
		this.txtSupplierName = new JTextField();
		this.txtPhoneNo = new JTextField();
		initialize();
		loadData();
		getDateToday();
		displayTotalItems();
	}
	private void initialize() {
		setLayout(null);
		
		JLabel lblCustomerSection = new JLabel("Supplier Section");
		lblCustomerSection.setForeground(Color.BLACK);
		lblCustomerSection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustomerSection.setBounds(0, 0, 184, 29);
		add(lblCustomerSection);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setBackground(new Color(3, 65, 68));
		panelButtons.setBounds(0, 45, 1097, 38);
		add(panelButtons);
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setText("Print");
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPrint.setBackground(new Color(3, 65, 68));
		btnPrint.setBounds(0, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		CustomButton btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setText("Save");
		btnSaveFile.setForeground(Color.WHITE);
		btnSaveFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSaveFile.setBackground(new Color(3, 65, 68));
		btnSaveFile.setBounds(62, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", this::ChangeMode, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setText("Mode");
		btnMode.setForeground(Color.WHITE);
		btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMode.setBackground(new Color(3, 65, 68));
		btnMode.setBounds(124, 0, 63, 38);
		panelButtons.add(btnMode);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(927, 82, 170, 581);
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
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1097, 20);
		add(bottomPanel);
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setRound(10);
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setBackground(Color.WHITE);
		roundPanel.setBounds(20, 154, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		
		JLabel lblSupplierId = new JLabel("Supplier Id");
		lblSupplierId.setForeground(Color.BLACK);
		lblSupplierId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtSupplierId = new JTextField();
		txtSupplierId.setToolTipText("Id");
		txtSupplierId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSupplierId.setEditable(false);
		txtSupplierId.setColumns(10);
		txtSupplierId.setBackground(SystemColor.menu);
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Delete", this::deleteRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("Delete");
		btnDelete.setText("Delete");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSupplierId.getText().toString().equals("")) {
			 		JOptionPane.showMessageDialog(null, "Please select a row to enable editing");
			 	}else {
				showUpdateSupplierPopup();
			 	}
			}
		});
		btnEdit.setToolTipText("Edit Row");
		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEdit.setBackground(new Color(243, 243, 243));
		
		JButton btnNewSupplier = new JButton("New Supplier");
		btnNewSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddSupplierPopup();
			}
		});
		btnNewSupplier.setToolTipText("Add");
		btnNewSupplier.setFocusPainted(false);
		btnNewSupplier.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewSupplier.setBackground(new Color(243, 243, 243));
		
		JLabel lblTotalProduct = new JLabel("Total Item");
		lblTotalProduct.setForeground(Color.BLACK);
		lblTotalProduct.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		txtTotalItem = new JLabel("0");
		txtTotalItem.setOpaque(true);
		txtTotalItem.setHorizontalAlignment(SwingConstants.LEFT);
		txtTotalItem.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTotalItem.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTotalItem.setBackground(Color.WHITE);
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
							.addComponent(lblSupplierId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSupplierId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewSupplier, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotalProduct)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTotalItem, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSupplierId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSupplierId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewSupplier, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTotalItem, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalProduct, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new Table();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	           @Override
	           public void valueChanged(ListSelectionEvent e) {
	            tableSelectedRow();
	           }
	       });
		scrollPane.setViewportView(table);
		roundPanel.setLayout(gl_roundPanel);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Search by...");
		textField_1.setColumns(10);
		textField_1.setBounds(20, 110, 304, 33);
		add(textField_1);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageSupplierForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(324, 110, 68, 33);
		add(btnSearchBy);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(749, 94, 40, 33);
		add(lblDate);
		
		lblCurrentDate = new JLabel("2023-12-11");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(791, 90, 93, 40);
		add(lblCurrentDate);
	}
	
	
	 private void saveAsFile(ActionEvent e) {
			// Logic for saving as a file 
			}
	 private void ChangeMode(ActionEvent e) {
			// Logic for toggling between Light/Dark mode
			}
	 //method to get the date today
	 private void getDateToday() { 
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
	 	//display total items available
	 private void displayTotalItems() {		 
		 	int sumOfTotal = supplierService.getTotalItems();
	        // Update UI component (setText on a txtTotalItem)
		 	txtTotalItem.setText(String.valueOf(sumOfTotal));
		} 
	 private void tableSelectedRow() {
		 DefaultTableModel model = (DefaultTableModel)table.getModel();
			int Myindex = table.getSelectedRow();
			String id = table.getModel().getValueAt(Myindex,0).toString();	
			
			txtSupplierId.setText(id);					
			txtSupplierName.setText(model.getValueAt(Myindex, 1).toString());
			txtPhoneNo.setText(model.getValueAt(Myindex, 2).toString());	
	    }
	 private void loadData() {
	        // Call the tableLoad method from ProductService
		 supplierService.tableLoad(table);
	    }
	 	
	 private void clearFields(ActionEvent e) {
		 	txtSupplierName.setText("");
	  		txtPhoneNo.setText("");
	  		txtSupplierName.requestFocus();
	 }
	 private void addRow(ActionEvent e) {
		 if(txtSupplierName.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
	  		}else {
	  			String name,phoneNumber;
		  		name = txtSupplierName.getText();
				phoneNumber = txtPhoneNo.getText();

		        // Create a Supplier object with the input data
		        Supplier supplier = new Supplier(name,phoneNumber);

		        // Call the addSupplier method from the SupplierService
		        supplierService.addSupplier(supplier);
		        				        			        
		        // Load the table
		        loadData();
		        //clear the text fields
		        txtSupplierName.setText("");
				txtPhoneNo.setText("");
				txtSupplierId.setText("");
				txtSupplierName.requestFocus();	
	        // ...
	  		}
	 }
	 private void deleteRow(ActionEvent e) {
		 if(txtSupplierId.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Select a supplier to be deleted");
	  		}else {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int supplierId = Integer.parseInt(txtSupplierId.getText());
		            supplierService.deleteSupplier(supplierId);
		            //show a message
		            JOptionPane.showMessageDialog(null, "Supplier deleted successfully");
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a supplier to delete");
		        }
		        // Load the table
		        loadData();
		        //clear textfields
		        txtSupplierName.setText("");
				txtPhoneNo.setText("");
				txtSupplierId.setText("");
				txtSupplierName.requestFocus();	
	  			}
	 }
	 private void updateRow(ActionEvent e) {
		 if(txtSupplierName.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
	  		}else {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            int supplierId = Integer.parseInt(txtSupplierId.getText());

	            //text fields for editing
	            String name, phoneNo; 
				name = txtSupplierName.getText();
				phoneNo = txtPhoneNo.getText();

				Supplier updatedSupplier = new Supplier(supplierId,name,phoneNo);
	            supplierService.updateSupplier(updatedSupplier);		           
	            
	            //load table
	            loadData();
	            // Clear the fields 
	            txtSupplierName.setText("");
				txtPhoneNo.setText("");
				txtSupplierId.setText("");
				txtSupplierName.requestFocus();	
	            
	            // ...
	        } else {
	            JOptionPane.showMessageDialog(null, "Please select a supplier to update");
	        }
	  	 }
	 }
	 
	 private void showAddSupplierPopup() {
		    JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Create New Supplier");
	        createProductDialog.setSize( 410, 270);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
		 
	        JPanel supplierAddPanel = new JPanel();
	        //customerAddPanel.setBounds(343, 232, 397, 235);
	        supplierAddPanel.setLayout(null);       
	        
	        txtSupplierName = new JTextField();
	        txtSupplierName.setToolTipText("Qty");
	        txtSupplierName.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtSupplierName.setColumns(10);
	        txtSupplierName.setBounds(125, 58, 222, 31);
	        supplierAddPanel.add(txtSupplierName);
	        
	        JLabel lblSupplier = new JLabel("Supplier");
	        lblSupplier.setForeground(Color.BLACK);
	        lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblSupplier.setBounds(54, 59, 63, 29);
	        supplierAddPanel.add(lblSupplier);
	        
	        txtPhoneNo = new JTextField();
	        txtPhoneNo.setToolTipText("Qty");
	        txtPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtPhoneNo.setColumns(10);
	        txtPhoneNo.setBounds(125, 108, 222, 31);
	        supplierAddPanel.add(txtPhoneNo);
	        
	        JLabel lblPhoneNo = new JLabel("Phone No");
	        lblPhoneNo.setForeground(Color.BLACK);
	        lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblPhoneNo.setBounds(54, 109, 63, 29);
	        supplierAddPanel.add(lblPhoneNo);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(284, 175, 63, 38);
	        supplierAddPanel.add(btnCancel);
	        
	        CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Add", this::addRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnAdd.setToolTipText("Add");
	        btnAdd.setText("Add");
	        btnAdd.setBounds(138, 175, 63, 38);
	        supplierAddPanel.add(btnAdd);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(211, 175, 63, 38);
	        supplierAddPanel.add(btnClear);
	        
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
	        createProductDialog.getContentPane().add(supplierAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	 }
	 private void showUpdateSupplierPopup() {
		    JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Update Supplier");
	        createProductDialog.setSize( 410, 270);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
		 
	        JPanel supplierAddPanel = new JPanel();
	        //customerAddPanel.setBounds(343, 232, 397, 235);
	        supplierAddPanel.setLayout(null);       
	        
	        txtSupplierName = new JTextField();
	        txtSupplierName.setToolTipText("Qty");
	        txtSupplierName.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtSupplierName.setColumns(10);
	        txtSupplierName.setBounds(125, 58, 222, 31);
	        supplierAddPanel.add(txtSupplierName);
	        
	        JLabel lblSupplier = new JLabel("Supplier");
	        lblSupplier.setForeground(Color.BLACK);
	        lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblSupplier.setBounds(54, 59, 63, 29);
	        supplierAddPanel.add(lblSupplier);
	        
	        txtPhoneNo = new JTextField();
	        txtPhoneNo.setToolTipText("Qty");
	        txtPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtPhoneNo.setColumns(10);
	        txtPhoneNo.setBounds(125, 108, 222, 31);
	        supplierAddPanel.add(txtPhoneNo);
	        
	        JLabel lblPhoneNo = new JLabel("Phone No");
	        lblPhoneNo.setForeground(Color.BLACK);
	        lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblPhoneNo.setBounds(54, 109, 63, 29);
	        supplierAddPanel.add(lblPhoneNo);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(284, 175, 63, 38);
	        supplierAddPanel.add(btnCancel);
	        
	        CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnUpdate.setToolTipText("Update");
	        btnUpdate.setText("Update");
	        btnUpdate.setBounds(138, 175, 63, 38);
	        supplierAddPanel.add(btnUpdate);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(211, 175, 63, 38);
	        supplierAddPanel.add(btnClear);
	        
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
	        createProductDialog.getContentPane().add(supplierAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        tableSelectedRow();
	 }

}
