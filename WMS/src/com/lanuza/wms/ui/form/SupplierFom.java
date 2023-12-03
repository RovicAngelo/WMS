package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.SupplierDAO;
import com.lanuza.wms.dao.impl.SupplierDAOImpl;
import com.lanuza.wms.model.Supplier;
import com.lanuza.wms.service.SupplierService;
import com.lanuza.wms.service.impl.SupplierServiceImpl;

public class SupplierFom {
	private JFrame frame;
	private JButton btnBack;
	private JTextField txtName;
	private JTable table;
	private JTextField txtSupplierId;
	private JTextField txtPhoneNo;
	private final SupplierService supplierService;
	private final SupplierDAO supplierDAO;
		
	public SupplierFom() {
	    this.supplierDAO = new SupplierDAOImpl();
	    this.supplierService = new SupplierServiceImpl(supplierDAO);
		initialize();
		loadData();
	}
	
	private void loadData() {
        // Call the tableLoad method from ProductService
        supplierService.tableLoad(table);
    }
	  		
	public void initialize() {	
		frame = new JFrame();
		frame.setTitle("PhilDrinks"); // set the title if Frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exit the application
		frame.setResizable(false);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // this make Frame visible
		frame.getContentPane().setLayout(null);
		  				  
		  JPanel topPanel = new JPanel();
		  topPanel.setBackground(new Color(3, 65, 68));
		  topPanel.setBounds(0, 0, 984, 66);
		  frame.getContentPane().add(topPanel);
		  topPanel.setLayout(null);
		  
		  JPanel bottomPanel = new JPanel();
		  bottomPanel.setBackground(new Color(3, 65, 68));
		  bottomPanel.setBounds(0, 543, 984, 18);
		  frame.getContentPane().add(bottomPanel);
		  bottomPanel.setLayout(null);
		  
		  JPanel panel = new JPanel();
		  panel.setLayout(null);
		  panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Supplier Form", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		  panel.setBounds(10, 102, 293, 261);
		  frame.getContentPane().add(panel);		  		 
		  
		  JPanel panel_1 = new JPanel();
		  panel_1.setLayout(null);
		  panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		  panel_1.setBounds(10, 374, 293, 114);
		  frame.getContentPane().add(panel_1);
		  	  			  
		  txtName = new JTextField();
		  txtName.setColumns(10);
		  txtName.setBounds(103, 63, 172, 28);
		  panel.add(txtName);
		  
		  txtSupplierId = new JTextField();		  		  
		  txtSupplierId.setColumns(10);
		  txtSupplierId.setBounds(109, 31, 161, 29);
		  panel_1.add(txtSupplierId);
		  
		  txtPhoneNo = new JTextField();
		  txtPhoneNo.setColumns(10);
		  txtPhoneNo.setBounds(103, 126, 172, 28);
		  panel.add(txtPhoneNo);
		  
		  JLabel topLabel = new JLabel("Manage Supplier ");
		  topLabel.setForeground(new Color(255, 255, 255));
		  topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		  topLabel.setBounds(399, 25, 263, 30);
		  topPanel.add(topLabel);
		  
		  JLabel lblName = new JLabel("Name");
		  lblName.setForeground(Color.BLACK);
		  lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		  lblName.setBounds(22, 59, 51, 33);
		  panel.add(lblName);
		  
		  JLabel lblPhoneNo = new JLabel("Phone No.");
		  lblPhoneNo.setForeground(Color.BLACK);
		  lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		  lblPhoneNo.setBounds(17, 126, 76, 28);
		  panel.add(lblPhoneNo);
		  
		  JLabel lblSupplierList = new JLabel("Supplier List");
		  lblSupplierList.setForeground(Color.BLACK);
		  lblSupplierList.setFont(new Font("Tahoma", Font.BOLD, 18));
		  lblSupplierList.setBounds(592, 102, 134, 30);
		  frame.getContentPane().add(lblSupplierList);
		  		 		  
		  JLabel lblId = new JLabel("SuppplierID");
		  lblId.setForeground(Color.BLACK);
		  lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		  lblId.setBounds(10, 27, 101, 33);
		  panel_1.add(lblId);
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(324, 138, 638, 361);
		  frame.getContentPane().add(scrollPane);
		  
		  table = new JTable();
		  table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int Myindex = table.getSelectedRow();
					String id = table.getModel().getValueAt(Myindex,0).toString();
					
					txtSupplierId.setText(id);	
					txtName.setText(model.getValueAt(Myindex, 1).toString());	
					txtPhoneNo.setText(model.getValueAt(Myindex, 2).toString());					
				}
			});
		  
		  scrollPane.setViewportView(table);
		  
		  btnBack = new JButton("Back");
		  btnBack.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		frame.dispose();
		  		new StockForm();
		  	}
		  });
		  btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnBack.setBounds(20, 499, 89, 29);
		  frame.getContentPane().add(btnBack);
		  
		  JButton btnAdd = new JButton("Add");
		  btnAdd.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			    	if(txtName.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
			  		}else {
			  			String name,phoneNumber;
				  		name = txtName.getText();
						phoneNumber = txtPhoneNo.getText();
		
				        // Create a Supplier object with the input data
				        Supplier supplier = new Supplier(name,phoneNumber);
	
				        // Call the addSupplier method from the SupplierService
				        supplierService.addSupplier(supplier);
				        				        			        
				        // Load the table
				        loadData();
				        //clear the text fields
				        txtName.setText("");
				        txtPhoneNo.setText("");
			        // ...
			  		}
			    }
			});				   		
		  btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnAdd.setBounds(80, 204, 89, 29);
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
		  btnClear.setBounds(179, 204, 89, 29);
		  panel.add(btnClear);
		  			  				  		  	  
		  JButton btnUpdate = new JButton("Update");
			  	btnUpdate.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	if(txtName.getText().isEmpty() ||txtPhoneNo.getText().isEmpty()) {
				  			JOptionPane.showMessageDialog(null,"Missing information(s)!");
				  		}else {
				        int selectedRow = table.getSelectedRow();
				        if (selectedRow != -1) {
				            int supplierId = (int) table.getValueAt(selectedRow, 0);

				            //text fields for editing
				            String name, phoneNo; 
			  				name = txtName.getText();
			  				phoneNo = txtPhoneNo.getText();

			  				Supplier updatedSupplier = new Supplier(supplierId,name,phoneNo);
				            supplierService.updateSupplier(updatedSupplier);		           
				            
				            //load table
				            loadData();
				            // Clear the fields 
				            txtName.setText("");
				            txtPhoneNo.setText("");
				            // ...
				        } else {
				            JOptionPane.showMessageDialog(null, "Please select a supplier to update");
				        }
				  	 }
				    }
			  });
		  btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnUpdate.setBounds(88, 71, 89, 29);
		  panel_1.add(btnUpdate);
		  
		  
		  
		  
		  JButton btnDelete = new JButton("Delete");		  
		  btnDelete.addActionListener(new ActionListener() {				  	
			    public void actionPerformed(ActionEvent e) {
			    	if(txtName.getText().isEmpty()||txtPhoneNo.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Select a supplier to be deleted");
			  		}else {
				        int selectedRow = table.getSelectedRow();
				        if (selectedRow != -1) {
				            int supplierId = (int) table.getValueAt(selectedRow, 0);
				            supplierService.deleteSupplier(supplierId);
				            //show a message
				            JOptionPane.showMessageDialog(null, "Supplier deleted successfully");
				        } else {
				            JOptionPane.showMessageDialog(null, "Please select a supplier to delete");
				        }
				        // Load the table
				        loadData();
				        //clear textfields
				        txtName.setText("");
						txtPhoneNo.setText("");
						txtSupplierId.setText("");
						txtName.requestFocus();	
			  			}
			    	}
			  });
		  btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnDelete.setBounds(181, 71, 89, 29);
		  panel_1.add(btnDelete);
	}
}
