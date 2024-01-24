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

import com.lanuza.wms.dao.CustomerDAO;
import com.lanuza.wms.dao.impl.CustomerDAOImpl;
import com.lanuza.wms.model.Customer;
import com.lanuza.wms.service.CustomerService;
import com.lanuza.wms.service.impl.CustomerServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;

public class ManageCustomerForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;
	private CustomerService customerService;
	private JTextField txtCustomerId;
	private JTextField textField_1,txtCustomer,txtPhoneNo;
	JLabel lblCurrentDate, txtTotalItem;
	private Table table;

	public ManageCustomerForm() {
		this.customerDAO = new CustomerDAOImpl();
		this.customerService = new CustomerServiceImpl(customerDAO);
		this.txtCustomer = new JTextField();
		this.txtPhoneNo = new JTextField();
		initialize();
		getDateToday();
		loadData();
		displayTotalItems();
	}
		
	private void initialize() {	
		setLayout(null);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(927, 0, 170, 560);
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
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setRound(10);
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setBackground(Color.WHITE);
		roundPanel.setBounds(22, 71, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		
		JLabel lblCustomerId = new JLabel("Customer Id");
		lblCustomerId.setForeground(Color.BLACK);
		lblCustomerId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtCustomerId = new JTextField();
		txtCustomerId.setToolTipText("Id");
		txtCustomerId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerId.setEditable(false);
		txtCustomerId.setColumns(10);
		txtCustomerId.setBackground(SystemColor.menu);
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Update", this::deleteRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("Delete");
		btnDelete.setText("Delete");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCustomerId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Select a table row to enable editing");
				}else {
				showUpdateCustomerPopup();
				}
			}
		});
		btnEdit.setToolTipText("Edit Row");
		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEdit.setBackground(new Color(243, 243, 243));
		
		JButton btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddCustomerPopup();
			}
		});
		btnNewCustomer.setToolTipText("Add");
		btnNewCustomer.setFocusPainted(false);
		btnNewCustomer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewCustomer.setBackground(new Color(243, 243, 243));
		
		JLabel lblGrossTotal = new JLabel("Total Item");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		
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
							.addComponent(lblGrossTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTotalItem, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCustomerId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCustomerId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 448, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewCustomer, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCustomerId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtCustomerId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewCustomer, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTotalItem, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
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
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(749, 4, 40, 33);
		add(lblDate);
		
		lblCurrentDate = new JLabel("2023-12-11");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(791, 0, 93, 40);
		add(lblCurrentDate);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Search by...");
		textField_1.setColumns(10);
		textField_1.setBounds(22, 27, 304, 33);
		add(textField_1);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageCustomerForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(326, 27, 68, 33);
		add(btnSearchBy);

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
	 //To display total items available
	 private void displayTotalItems() {		 
		 	int sumOfTotal = customerService.getTotalItems();
	        // Update UI component (setText on a txtTotalItem)
		 	txtTotalItem.setText(String.valueOf(sumOfTotal));
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
	 private void tableSelectedRow() {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    int rowIndex = table.getSelectedRow();

		    // Check if a row is selected
		    if (rowIndex != -1) {
		        // for text field named txtCustomerId
		        String id = model.getValueAt(rowIndex, 0) != null ? model.getValueAt(rowIndex, 0).toString() : "";
		        txtCustomerId.setText(id);

		        // for text field named txtCustomer
		        String customer = model.getValueAt(rowIndex, 1) != null ? model.getValueAt(rowIndex, 1).toString() : "";
		        txtCustomer.setText(customer);

		        // for text field named txtPhoneNo
		        String phoneNo = model.getValueAt(rowIndex, 2) != null ? model.getValueAt(rowIndex, 2).toString() : "";
		        txtPhoneNo.setText(phoneNo);
		    } else {
		        // Handle the case where no row is selected (e.g., clear text fields).
		        txtCustomerId.setText("");
		        txtCustomer.setText("");
		        txtPhoneNo.setText("");
		    }
		}

	 private void loadData() {
	        // Call the tableLoad method from ProductService
	        customerService.tableLoad(table);
	    }
	 
	 private void clearFields(ActionEvent e) {
		 	txtCustomer.setText("");
	  		txtPhoneNo.setText("");
	  		txtCustomer.requestFocus();
	 }
	 private void addRow(ActionEvent e) {
			if(txtCustomer.getText().isEmpty() ||  txtPhoneNo.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
	  		}else {
			  		String name, phoneNo;
			  		name = txtCustomer.getText();
			  		phoneNo = txtPhoneNo.getText();
			  		
			  		Customer customer = new Customer(name,phoneNo);
			  		customerService.addCustomer(customer);
			  		// Load the table
			  		loadData();	
			  		 //display new total items
			  		displayTotalItems();
			  		txtCustomer.setText("");
					txtPhoneNo.setText("");
					txtCustomer.requestFocus();	
			  	 }
	 }
	 private void deleteRow(ActionEvent e) {
			if(txtCustomer.getText().isEmpty() && txtPhoneNo.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Select a customer to be deleted");
	  		}else {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		        	
		            int customerId = (int) table.getValueAt(selectedRow, 0);
		            customerService.deleteCustomer(customerId);
		            //show a message
		            JOptionPane.showMessageDialog(null, "Customer deleted successfully");
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a customer to delete");
		        }
		        // Load the table
		        loadData();
		        //display new total items
		        displayTotalItems();
		        //clear textfields
		        txtCustomer.setText("");
				txtPhoneNo.setText("");
				txtCustomerId.setText("");
				txtCustomer.requestFocus();	
	  			}
	 }
	 private void updateRow(ActionEvent e) {
			if(txtCustomer.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
	  		}else {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            int customerId = (int) table.getValueAt(selectedRow, 0);			  				
	  				String name, phoneNo; 
	  				
	  				//text fields for editing
	  				name = txtCustomer.getText();
	  				phoneNo = txtPhoneNo.getText();
					
					Customer customer = new Customer(name,phoneNo,customerId);
					customerService.updateCustomer(customer);
	  				
					loadData();	
					txtCustomer.setText("");
					txtPhoneNo.setText("");
					txtCustomerId.setText("");
					txtCustomer.requestFocus();			  							  						  							  				
	        } else {
	            JOptionPane.showMessageDialog(null, "Please select a customer to update");
	        }		
	  		}	
	 }
	 
	 private void showAddCustomerPopup() {
		    JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Create New Customer");
	        createProductDialog.setSize( 410, 270);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
		 
	        JPanel customerAddPanel = new JPanel();
	        //customerAddPanel.setBounds(343, 232, 397, 235);
	        customerAddPanel.setLayout(null);       
	        
	        txtCustomer = new JTextField();
	        txtCustomer.setToolTipText("Qty");
	        txtCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtCustomer.setColumns(10);
	        txtCustomer.setBounds(125, 58, 222, 31);
	        customerAddPanel.add(txtCustomer);
	        
	        JLabel lblCustomer = new JLabel("Customer");
	        lblCustomer.setForeground(Color.BLACK);
	        lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblCustomer.setBounds(54, 59, 63, 29);
	        customerAddPanel.add(lblCustomer);
	        
	        txtPhoneNo = new JTextField();
	        txtPhoneNo.setToolTipText("Qty");
	        txtPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtPhoneNo.setColumns(10);
	        txtPhoneNo.setBounds(125, 108, 222, 31);
	        customerAddPanel.add(txtPhoneNo);
	        
	        JLabel lblPhoneNo = new JLabel("Phone No");
	        lblPhoneNo.setForeground(Color.BLACK);
	        lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblPhoneNo.setBounds(54, 109, 63, 29);
	        customerAddPanel.add(lblPhoneNo);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(284, 175, 63, 38);
	        customerAddPanel.add(btnCancel);
	        
	        CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Add", this::addRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnAdd.setToolTipText("Add");
	        btnAdd.setText("Add");
	        btnAdd.setBounds(138, 175, 63, 38);
	        customerAddPanel.add(btnAdd);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(211, 175, 63, 38);
	        customerAddPanel.add(btnClear);
	        
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
	        createProductDialog.getContentPane().add(customerAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	 }
	 private void showUpdateCustomerPopup() {
		    JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Update Customer");
	        createProductDialog.setSize( 410, 270);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
		 
	        JPanel customerAddPanel = new JPanel();
	        //customerAddPanel.setBounds(343, 232, 397, 235);
	        customerAddPanel.setLayout(null);       
	        
	        txtCustomer = new JTextField();
	        txtCustomer.setToolTipText("Qty");
	        txtCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtCustomer.setColumns(10);
	        txtCustomer.setBounds(125, 58, 222, 31);
	        customerAddPanel.add(txtCustomer);
	        
	        JLabel lblCustomer = new JLabel("Customer");
	        lblCustomer.setForeground(Color.BLACK);
	        lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblCustomer.setBounds(54, 59, 63, 29);
	        customerAddPanel.add(lblCustomer);
	        
	        txtPhoneNo = new JTextField();
	        txtPhoneNo.setToolTipText("Qty");
	        txtPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtPhoneNo.setColumns(10);
	        txtPhoneNo.setBounds(125, 108, 222, 31);
	        customerAddPanel.add(txtPhoneNo);
	        
	        JLabel lblPhoneNo = new JLabel("Phone No");
	        lblPhoneNo.setForeground(Color.BLACK);
	        lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblPhoneNo.setBounds(54, 109, 63, 29);
	        customerAddPanel.add(lblPhoneNo);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(284, 175, 63, 38);
	        customerAddPanel.add(btnCancel);
	        
	        CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnUpdate.setToolTipText("Update");
	        btnUpdate.setText("Update");
	        btnUpdate.setBounds(138, 175, 63, 38);
	        customerAddPanel.add(btnUpdate);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(211, 175, 63, 38);
	        customerAddPanel.add(btnClear);
	        
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
	        createProductDialog.getContentPane().add(customerAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        tableSelectedRow();
	 }
}
