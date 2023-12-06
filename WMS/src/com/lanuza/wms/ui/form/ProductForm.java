package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

//ProductForm.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.ProductDAO;
import com.lanuza.wms.dao.impl.ProductDAOImpl;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.service.ProductService;
import com.lanuza.wms.service.impl.ProductServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import com.lanuza.wms.ui.components.CustomLabel;
import com.lanuza.wms.ui.components.CustomPanel;
import com.lanuza.wms.ui.components.CustomTextField;

public class ProductForm {

	private final ProductService productService;
	private final ProductDAO productDAO;
	
	private JComboBox<String>supplierCombox;
	private JTable table;
    private JFrame frame; //not sure wether to use panel or frame in sub forms
	private JTextField txtDescription,txtPrice,txtId;
	
	 public ProductForm() {
		 // Create instances of ProductDAOImpl and ProductServiceImpl internally
	        this.productDAO = new ProductDAOImpl();
	        this.productService = new ProductServiceImpl(productDAO);
	     initComponents();
	     loadData();
	     populateSupplierCombox();
	 }
	 
	 private void loadData() {
	        // Call the tableLoad method from ProductService
	        productService.tableLoad(table);
	    }	
	 
	 private void populateSupplierCombox() {
		    List<String> supplierNames = productService.getAllSupplierName();

		    for (String supplierName : supplierNames) {
		    	supplierCombox.addItem(supplierName);
		    }
		}
	
	 private void initComponents() {
		 	// UI components initialization...
		 	frame = new JFrame();
			frame.getContentPane().setForeground(new Color(0, 0, 0));
			frame.setSize(1000,600);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.getContentPane().setLayout(null);
			
			//Panels
			JPanel topPanel = new CustomPanel(new Color(3, 65, 68),new Rectangle(0, 0, 1184, 66),null,true, true,null);
			frame.getContentPane().add(topPanel);		
			
			JPanel bottomPanel = new CustomPanel(new Color(3, 65, 68),new Rectangle(0, 541, 984, 18),null,true,true,null);
			frame.getContentPane().add(bottomPanel);
			
			JPanel panel = new CustomPanel(new Color(240,240,240),new Rectangle(10, 112, 303, 242),null,true,true,new TitledBorder(null, "Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			frame.getContentPane().add(panel);
								
			 // Create label with text, fgcolor, font, bounds, horizontalAlignment parameters
			JLabel lblDescription = new CustomLabel("Description",new Color(0,0,0),new Font("Tahoma", Font.BOLD, 15),new Rectangle(8, 73, 88, 25),SwingConstants.CENTER);
			lblDescription.setLocation(10, 48);
			panel.add(lblDescription);
			
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPrice.setBounds(20, 98, 50, 25);
			panel.add(lblPrice);
								
			JLabel lblProductSection = new JLabel("Manage Product");
			lblProductSection.setForeground(Color.WHITE);
			lblProductSection.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblProductSection.setBounds(399, 25, 148, 30);
			topPanel.add(lblProductSection);
			
			txtPrice = new JTextField();
			txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtPrice.setColumns(10);
			txtPrice.setBounds(99, 97, 182, 29);
			panel.add(txtPrice);
			
			txtDescription = new JTextField();
			txtDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDescription.setColumns(10);
			txtDescription.setBounds(99, 47, 182, 29);
			panel.add(txtDescription);
			
			 // Create button with text, icon,bgColor,fgColor, tooltiptext, action, bounds, focus paint
			JButton btnAdd = new JButton();
			btnAdd.setText("Add");
			btnAdd.setBackground(new Color(240,240,240));
			btnAdd.setForeground(new Color(0,0,0));
			btnAdd.setBounds(new Rectangle(99, 202, 89, 29));
			btnAdd.setFocusPainted(false);
			btnAdd.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if(txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()) {
			  			JOptionPane.showMessageDialog(null,"Missing Information(s)");
			  		}else {
			  		String productDescription,supplierName;
			  		int qty= 0;	//this is an attribute of tblstock to be transfer as having 0 as initial 
			  		Double productPrice,total=0.0;
			        //text fields and combo box components
			  			productDescription = txtDescription.getText();
				  		productPrice =  Double.parseDouble(txtPrice.getText());
				  		supplierName = supplierCombox.getSelectedItem().toString();	
		
				        // Create a Product object with the input data
				        Product product = new Product(productDescription, productPrice,qty,total, supplierName);
	
				        // Call the addProduct method from the ProductService
				        productService.addProduct(product);		
				        
				        productService.transferDataAndSetDefaults(product);
				        			        
				        // Load the table
				        loadData();
				        //clear the text fields
				        txtId.setText("");
				        txtDescription.setText("");
				        txtPrice.setText("");
				        supplierCombox.setSelectedItem("");
			  		}
			    }
			});			
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel.add(btnAdd);
			
			JButton btnClear = new JButton();
			btnClear.setText("Clear");
			btnClear.setBackground(new Color(240,240,240));
			btnClear.setForeground(new Color(0,0,0));
			btnClear.setBounds(new Rectangle(192, 202, 89, 29));
			btnClear.setFocusPainted(false);
			btnClear.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
			  		txtDescription.setText("");
			  		txtPrice.setText("");
			  		supplierCombox.setSelectedItem("");
			  		txtDescription.requestFocus();
			  	}
			  });
			btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel.add(btnClear);
			
			JLabel lblSupplierName = new JLabel("Supplier");
			lblSupplierName.setForeground(Color.BLACK);
			lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblSupplierName.setBounds(10, 149, 88, 33);
			panel.add(lblSupplierName);
			
			supplierCombox = new JComboBox<String>();
			supplierCombox.setMaximumRowCount(2);
			supplierCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			supplierCombox.setEditable(true);
			supplierCombox.setBounds(99, 152, 182, 28);
			panel.add(supplierCombox);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(331, 158, 626, 321);
			frame.getContentPane().add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			        DefaultTableModel model = (DefaultTableModel) table.getModel();
			        int rowIndex = table.getSelectedRow();

			        // for text field named txtId
			        String id = model.getValueAt(rowIndex, 0).toString();
			        txtId.setText(id);

			        // for text fields named txtCode, txtDescription, and txtPrice
			        txtDescription.setText(model.getValueAt(rowIndex, 1).toString());
			        txtPrice.setText(model.getValueAt(rowIndex, 2).toString());

			        // for combo box named supplierCombox
			        supplierCombox.setSelectedItem(model.getValueAt(rowIndex, 3).toString());
			    }
			});

			scrollPane.setViewportView(table);
			
			JLabel lblProductList = new JLabel("Product List");
			lblProductList.setForeground(Color.BLACK);
			lblProductList.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblProductList.setBounds(587, 123, 114, 30);
			frame.getContentPane().add(lblProductList);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new Dashboard();
				}
			});
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnBack.setBounds(20, 490, 89, 29);
			frame.getContentPane().add(btnBack);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(20, 365, 293, 114);
			frame.getContentPane().add(panel_1);
			
			//Create textfields with font, bounds, bgcolor, fbcolor,tooltip,column,cursor,opaque,editable parameters
			txtId = new CustomTextField(new Font("Tahoma",Font.BOLD, 13), new Rectangle(102, 31, 168, 29) ,new Color(250,250,250),new Color(0,0,0),"Type Product Code",10,null,true,false);
			panel_1.add(txtId);
			
			// Update button action listener
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
		    	
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
			            String supplier = supplierCombox.getSelectedItem().toString();

			            Product updatedProduct = new Product(productDescription, productPrice, qty, total, supplier,productId);
			            productService.updateProduct(updatedProduct);		           
			            
			            //load table
			            loadData();
			            // Clear the fields 
			            txtId.setText("");
			            txtDescription.setText("");
			            txtPrice.setText("");	
			            supplierCombox.setSelectedItem("");
			            // ...
			        } else {
			            JOptionPane.showMessageDialog(null, "Please select a product to update");
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
			            txtId.setText("");
			            txtDescription.setText("");
			            txtPrice.setText("");	
			            supplierCombox.setSelectedItem("");
			  	 }
			    }
			});
			btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnDelete.setBounds(181, 71, 89, 29);
			panel_1.add(btnDelete);			
			
			JLabel lblProductId = new JLabel("ProductID");
			lblProductId.setForeground(Color.BLACK);
			lblProductId.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblProductId.setBounds(10, 27, 82, 33);
			panel_1.add(lblProductId);			  	
	 }
}

