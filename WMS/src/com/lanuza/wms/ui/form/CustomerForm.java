package com.lanuza.wms.ui.form;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.CustomerDAO;
import com.lanuza.wms.dao.impl.CustomerDAOImpl;
import com.lanuza.wms.service.CustomerService;
import com.lanuza.wms.service.impl.CustomerServiceImpl;
import com.lanuza.wms.model.Customer;

import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomerForm {
	private final CustomerDAO customerDAO;
	private final CustomerService customerService;
	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private  JTextField txtCustomerId;
	private JTextField txtPhoneNo;

	public CustomerForm() {
		this.customerDAO = new CustomerDAOImpl();
		this.customerService = new CustomerServiceImpl(customerDAO);
		initialize();
		loadData();
	}
		
	void loadData() {
		customerService.tableLoad(table);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1200,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 148, 311, 226);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1184, 66);
		frame.getContentPane().add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1184, 18);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 395, 311, 132);
		frame.getContentPane().add(panel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(29, 43, 51, 33);
		panel.add(lblName);
		
		JLabel lblPhoneNo = new JLabel("PhoneNo");
		lblPhoneNo.setForeground(Color.BLACK);
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhoneNo.setBounds(10, 107, 70, 33);
		panel.add(lblPhoneNo);
				
		JLabel topLabel = new JLabel("Manage Customer");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(399, 25, 263, 30);
		topPanel.add(topLabel);
		
		JLabel lblCustomerList = new JLabel("Customer List");
		lblCustomerList.setForeground(new Color(0, 0, 0));
		lblCustomerList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerList.setBounds(612, 156, 134, 30);
		frame.getContentPane().add(lblCustomerList);
		
		JLabel lblId = new JLabel("CustomerID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(10, 31, 89, 33);
		panel_1.add(lblId);
		
		txtName = new JTextField();
		txtName.setBounds(90, 47, 192, 28);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(102, 31, 188, 29);
		panel_1.add(txtCustomerId);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(90, 111, 192, 28);
		panel.add(txtPhoneNo);  
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  		if(txtName.getText().isEmpty() ||  txtPhoneNo.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
			  		}else {
					  		String name, phoneNo;
					  		name = txtName.getText();
					  		phoneNo = txtPhoneNo.getText();
					  		
					  		Customer customer = new Customer(name,phoneNo);
					  		customerService.addCustomer(customer);
					  		
					  		loadData();						
							txtName.setText("");
							txtPhoneNo.setText("");
							txtName.requestFocus();	
					  	 }
						}	
					  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(94, 172, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		  		txtName.setText("");
		  		txtPhoneNo.setText("");
		  		txtName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(193, 172, 89, 29);
		panel.add(btnClear);				
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(31, 582, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(txtName.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
		  		}else {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int customerId = (int) table.getValueAt(selectedRow, 0);			  				
		  				String name, phoneNo; 
		  				
		  				//text fields for editing
		  				name = txtName.getText();
		  				phoneNo = txtPhoneNo.getText();
						
						Customer customer = new Customer(name,phoneNo,customerId);
						customerService.updateCustomer(customer);
		  				
						loadData();	
						txtName.setText("");
						txtPhoneNo.setText("");
						txtCustomerId.setText("");
						txtName.requestFocus();			  							  						  							  				
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a customer to update");
		        }		
		  		}				  		
		  	}
		  });
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(102, 81, 89, 29);
		panel_1.add(btnUpdate);		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		    	if(txtName.getText().isEmpty() && txtPhoneNo.getText().isEmpty()) {
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
			        //clear textfields
			        txtName.setText("");
					txtPhoneNo.setText("");
					txtCustomerId.setText("");
					txtName.requestFocus();	
		  			}
		  	}	  	
		  });
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(201, 81, 89, 29);
		panel_1.add(btnDelete);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 187, 607, 361);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				String id = table.getModel().getValueAt(Myindex,0).toString();	
				
				txtCustomerId.setText(id);					
				txtName.setText(model.getValueAt(Myindex, 1).toString());
				txtPhoneNo.setText(model.getValueAt(Myindex, 2).toString());				
			}
		});
		scrollPane.setViewportView(table);
	}
}
