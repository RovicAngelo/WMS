package com.lanuza.wms.ui.form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;

public class ManageAllForm extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final AccountDAO accountDAO;
	private final AccountService accountService;
	
	ManageReceivingForm receivingForm = new ManageReceivingForm();
	ManageOrderForm orderForm = new ManageOrderForm();
	

	private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	private JPanel bodyPanel;
	private JLabel lblFormSection;
	private CustomButton btnProcess, btnPrint,btnSaveFile,btnMode;
	private JButton btnDashboard,btnProduct,btnCustomer,btnSupplier,btnOrder,btnReceiving,btnStock,currentClickedButton;
	private JLabel lblRole,currentClickedLabel,lblUsername,lblAvatarIcon;
	String transactionType = "";

	public ManageAllForm(int id) {
		this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);
		
		Account account = accountService.getAccountById(id);
    	String name = account.getName();
    	String role = account.getRole();
		
		// Initialize the JFrame
        setTitle("wms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(1347, 700);
        setResizable(false);   
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(3, 65, 68));
		menuPanel.setPreferredSize(new Dimension(250, 700));
		menuPanel.setLayout(null);
		getContentPane().add(menuPanel,BorderLayout.WEST);
		
			JPanel avatarPanel = new JPanel();
			avatarPanel.setVisible(true);
			avatarPanel.setBounds(35, 19, 174, 117);     
	        avatarPanel.setLayout(null);
	        menuPanel.add(avatarPanel);
	        
				JLabel lblAvatar = new JLabel();
				lblAvatar.setVerticalTextPosition(SwingConstants.BOTTOM);
				lblAvatar.setVerticalAlignment(SwingConstants.BOTTOM);
				lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
				lblAvatar.setHorizontalTextPosition(SwingConstants.CENTER);
				lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblAvatar.setIcon(new ImageIcon(ManageAllForm.class.getResource("/com/lanuza/wms/ui/resources/icons/user.png")));
				lblAvatar.setBounds(36, 0, 97, 117);
				avatarPanel.add(lblAvatar);
				
				lblAvatarIcon = new JLabel("");
				lblAvatarIcon.setBounds(135, 78, 39, 39);
				avatarPanel.add(lblAvatarIcon);
				lblAvatarIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblAvatarIcon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						showForm(new ManageProfileForm(id,true));
						
						lblFormSection.setText("Profile Section");
						//disable process button
						btnProcess.setEnabled(false);
						btnProcess.setForeground(Color.GRAY);
						//enable print button
						btnPrint.setEnabled(true);
						btnPrint.setForeground(Color.WHITE);
						//enable save button
						btnSaveFile.setEnabled(true);
						btnSaveFile.setForeground(Color.WHITE);
						//enable mode button
						btnMode.setEnabled(true);
						btnMode.setForeground(Color.WHITE);
					}
				});
				lblAvatarIcon.setBackground(new Color(128, 128, 128));
				lblAvatarIcon.setHorizontalAlignment(SwingConstants.CENTER);			
				lblAvatarIcon.setIcon(new ImageIcon(ManageAllForm.class.getResource("/com/lanuza/wms/ui/resources/icons/9.png")));
	 
			
			lblUsername = new JLabel("no name");  
			lblUsername.setText(name);
			lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        lblUsername.setVerticalAlignment(SwingConstants.TOP);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblUsername.setForeground(Color.WHITE);
	        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
	        lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblUsername.setBounds(35, 143, 163, 17);
	        menuPanel.add(lblUsername);
	        
	        lblRole = new JLabel("no role");	 
	        lblRole.setIconTextGap(2);
	        lblRole.setText(role);
	        lblRole.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        lblRole.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {        		
	        		if(role.equals("Admin")){
	        			showForm(new ManageAllAccount());
		        		lblFormSection.setText("Accounts Section");
		        		//disable process button
		        		btnProcess.setEnabled(false);
		        		btnProcess.setForeground(Color.GRAY);
		        		//enable print button
		        		btnPrint.setEnabled(true);
		        		btnPrint.setForeground(Color.WHITE);
		        		//enable save button
		        		btnSaveFile.setEnabled(true);
		        		btnSaveFile.setForeground(Color.WHITE);
		        		//enable mode button
		        		btnMode.setEnabled(true);
		        		btnMode.setForeground(Color.WHITE);
	        		}else {
	        			JOptionPane.showMessageDialog(null, "Managing All Accounts are only accessible for admin users");
	        		}
	        		
	        	}
	        });
	        lblRole.setVerticalAlignment(SwingConstants.TOP);
	        lblRole.setFont(new Font("Tahoma", Font.BOLD, 10));
	        lblRole.setForeground(new Color(192, 192, 192));
	        lblRole.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
	        lblRole.setBounds(84, 166, 62, 19);
	        menuPanel.add(lblRole);
			
		        btnDashboard = new JButton();
		        btnDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnDashboard.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        		showForm(new ManageDashboardForm());
		        		lblFormSection.setText("Dashboard Section");
		        		//disable process button
		        		btnProcess.setEnabled(false);
		        		btnProcess.setForeground(Color.GRAY);
		        		//disable print button
		        		btnPrint.setEnabled(false);
		        		btnPrint.setForeground(Color.GRAY);
		        		//disable save button
		        		btnSaveFile.setEnabled(false);
		        		btnSaveFile.setForeground(Color.GRAY);
		        		//enable mode button
		        		btnMode.setEnabled(true);
		        		btnMode.setForeground(Color.WHITE);
		        	}
		        });
		        btnDashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnDashboard.setText("Dashboard");
		        btnDashboard.setForeground(Color.WHITE);
		        btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 12));
		        btnDashboard.setFocusPainted(false);
		        btnDashboard.setBackground(new Color(3, 65, 68));
		        btnDashboard.setBounds(10, 203, 230, 55);
		        menuPanel.add(btnDashboard);	
		        
		        btnProduct = new JButton();
		        btnProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnProduct.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		if(role.equals("Admin")){
		        			showForm(new ManageProductForm());
			        		lblFormSection.setText("Product Section");
			        		//disable process button
			        		btnProcess.setEnabled(false);
			        		btnProcess.setForeground(Color.GRAY);
			        		//enable print button
			        		btnPrint.setEnabled(true);
			        		btnPrint.setForeground(Color.WHITE);
			        		//enable save button
			        		btnSaveFile.setEnabled(true);
			        		btnSaveFile.setForeground(Color.WHITE);
			        		//enable mode button
			        		btnMode.setEnabled(true);
			        		btnMode.setForeground(Color.WHITE);
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Managing Products are only accessible for admin users");
		        		}  	        		
		        	}
		        });
		        btnProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnProduct.setFocusPainted(false);
		        btnProduct.setText("Product");
		        btnProduct.setForeground(Color.WHITE);
		        btnProduct.setFont(mainFont);
		        btnProduct.setBackground(new Color(3, 65, 68));
		        btnProduct.setBounds(10, 257, 230, 55);
		        menuPanel.add(btnProduct);	
		        
	        	btnSupplier = new JButton();
	        	btnSupplier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        	btnSupplier.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			if(role.equals("Admin")){
	        				showForm(new ManageSupplierForm());
		        			lblFormSection.setText("Supplier Section");
		        			//disable process button
		        			btnProcess.setEnabled(false);
			        		btnProcess.setForeground(Color.GRAY);
			        		//enable print button
			        		btnPrint.setEnabled(true);
			        		btnPrint.setForeground(Color.WHITE);
			        		//enable save button
			        		btnSaveFile.setEnabled(true);
			        		btnSaveFile.setForeground(Color.WHITE);
			        		//enable mode button
			        		btnMode.setEnabled(true);
			        		btnMode.setForeground(Color.WHITE);
	            		}else {
	            			JOptionPane.showMessageDialog(null, "Managing Suppliers are only accessible for admin users");
	            		}  
	        			
	        		}
	        	});
		        btnSupplier.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnSupplier.setText("Supplier");
		        btnSupplier.setForeground(Color.WHITE);
		        btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		        btnSupplier.setFocusPainted(false);
		        btnSupplier.setBackground(new Color(3, 65, 68));
		        btnSupplier.setBounds(10, 311, 230, 55);
		        menuPanel.add(btnSupplier);
		        
	        	btnCustomer = new JButton();
	        	btnCustomer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        	btnCustomer.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			if(role.equals("Admin")){
	        				showForm(new ManageCustomerForm());
		        			lblFormSection.setText("Customer Section");
		        			//disable process button
		        			btnProcess.setEnabled(false);
			        		btnProcess.setForeground(Color.GRAY);
			        		//enable print button
			        		btnPrint.setEnabled(true);
			        		btnPrint.setForeground(Color.WHITE);
			        		//enable save button
			        		btnSaveFile.setEnabled(true);
			        		btnSaveFile.setForeground(Color.WHITE);
			        		//enable mode button
			        		btnMode.setEnabled(true);
			        		btnMode.setForeground(Color.WHITE);
	            		}else {
	            			JOptionPane.showMessageDialog(null, "Managing Customers are only accessible for admin users");
	            		}      
	        			
	        		}
	        	});
		        btnCustomer.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnCustomer.setText("Customer");
		        btnCustomer.setForeground(Color.WHITE);
		        btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
		        btnCustomer.setFocusPainted(false);
		        btnCustomer.setBackground(new Color(3, 65, 68));
		        btnCustomer.setBounds(10, 365, 230, 55);
		        menuPanel.add(btnCustomer);
		        
		        btnOrder = new JButton();
		        btnOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnOrder.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		if(role.equals("Admin")){
		        			transactionType = "order";
		        			showForm(new ManageOrderForm());
			        		lblFormSection.setText("Order Section");
			        		//enable process button
			        		btnProcess.setEnabled(true);
			        		btnProcess.setForeground(Color.white);
			        		//enable print button
			        		btnPrint.setEnabled(true);
			        		btnPrint.setForeground(Color.WHITE);
			        		//enable save button
			        		btnSaveFile.setEnabled(true);
			        		btnSaveFile.setForeground(Color.WHITE);
			        		//enable mode button
			        		btnMode.setEnabled(true);
			        		btnMode.setForeground(Color.WHITE);		
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Managing Orders are only accessible for admin users");
		        		}   
		        		
		        	}
		        });
		        btnOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnOrder.setFocusPainted(false);
		        btnOrder.setFont(mainFont);
		        btnOrder.setText("Order");
		        btnOrder.setBounds(10, 419, 230, 55);
		        btnOrder.setBackground(new Color(3, 65, 68)); // dark cyan
		        btnOrder.setForeground(new Color(255, 255, 255));		
		        menuPanel.add(btnOrder);
		        
		        btnReceiving = new JButton();
		        btnReceiving.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnReceiving.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		if(role.equals("Admin")){
		        			transactionType = "receiving";		        			
		        			showForm(new ManageReceivingForm());
			        		lblFormSection.setText("Receiving Section");
			        		//enable process button
			        		btnProcess.setEnabled(true);
			        		btnProcess.setForeground(Color.WHITE);
			        		//enable print button
			        		btnPrint.setEnabled(true);
			        		btnPrint.setForeground(Color.WHITE);
			        		//enable save button
			        		btnSaveFile.setEnabled(true);
			        		btnSaveFile.setForeground(Color.WHITE);
			        		//enable mode button
			        		btnMode.setEnabled(true);
			        		btnMode.setForeground(Color.WHITE);
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Managing Receivings are only accessible for admin users");
		        		}  
		        		
		        	}
		        });
		        btnReceiving.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnReceiving.setFocusPainted(false);
		        btnReceiving.setFont(mainFont);
		        btnReceiving.setText("Receiving");
		        btnReceiving.setBounds(10, 473, 230, 55);
		        btnReceiving.setBackground(new Color(3, 65, 68)); // dark cyan
		        btnReceiving.setForeground(new Color(255, 255, 255));
		        menuPanel.add(btnReceiving);
	        
		        btnStock = new JButton();
		        btnStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnStock.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        		showForm(new ManageStockForm());
		        		lblFormSection.setText("Stock Section");
		        		//disable process button
		        		btnProcess.setEnabled(false);
		        		btnProcess.setForeground(Color.GRAY);
		        		//enable print button
		        		btnPrint.setEnabled(true);
		        		btnPrint.setForeground(Color.WHITE);
		        		//enable save button
		        		btnSaveFile.setEnabled(true);
		        		btnSaveFile.setForeground(Color.WHITE);
		        		//enable mode button
		        		btnMode.setEnabled(true);
		        		btnMode.setForeground(Color.WHITE);
		        	}
		        });
		        btnStock.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnStock.setFocusPainted(false);
		        btnStock.setFont(mainFont);
		        btnStock.setText("Stock");
		        btnStock.setBounds(10, 527, 230, 55);
		        // inquiryButton.setBackground(new Color(29, 77, 122)); blue
		        btnStock.setBackground(new Color(3, 65, 68)); // dark cyan
		        btnStock.setForeground(new Color(255, 255, 255));
		        menuPanel.add(btnStock);

		        JButton btnLogout = new JButton();
		        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnLogout.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		dispose();
		        		new LoginForm();
		        	}
		        });
		        btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
		        btnLogout.setFocusPainted(false);
		        btnLogout.setFont(mainFont);
		        btnLogout.setText("Logout");
		        btnLogout.setBounds(10, 581, 230, 55);
		        btnLogout.setBackground(new Color(3, 65, 68)); // dark cyan
		        btnLogout.setForeground(new Color(255, 255, 255));
		        menuPanel.add(btnLogout);
		        
		  JPanel formPanel = new JPanel();
		  formPanel.setSize(1097,664);
		  formPanel.setLayout(new BorderLayout());
	      getContentPane().add(formPanel, BorderLayout.CENTER);
		  
			JPanel headerPanel = new JPanel();
			headerPanel.setPreferredSize(new Dimension(1097,84));
			formPanel.add(headerPanel,BorderLayout.NORTH);
				headerPanel.setLayout(null);
			
				JPanel titlePanel = new JPanel();	
				titlePanel.setBounds(0, 0, 1097, 46);
				titlePanel.setPreferredSize(new Dimension(1097,46));
				headerPanel.add(titlePanel);
					titlePanel.setLayout(null);
				
					lblFormSection = new JLabel("Form Section");
					lblFormSection.setBounds(10, 0, 241, 25);
					lblFormSection.setForeground(Color.BLACK);
					lblFormSection.setFont(new Font("Tahoma", Font.BOLD, 20));
					titlePanel.add(lblFormSection);
				
				JPanel toolbarPanel = new JPanel();
				toolbarPanel.setLayout(null);
				toolbarPanel.setBounds(new Rectangle(0, 46, 1097, 38));
				toolbarPanel.setBackground(new Color(3, 65, 68));
				toolbarPanel.setPreferredSize(new Dimension(1097,38));
				headerPanel.add(toolbarPanel);
				
					btnProcess = new CustomButton(new Color(64, 128, 128), "Process Orders", this::reflectReceivingEntryToStock, new Rectangle(0, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					btnProcess.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(transactionType.equals("order")) {
								orderForm.reflectOrdersToStock(e);

							}else if(transactionType.equals("receiving")) {
								receivingForm.reflectReceivingToStock(e);
							}else {
								JOptionPane.showMessageDialog(null, "No selected transaction type");
							}
						}
					});
					btnProcess.setText("Process");
					btnProcess.setForeground(Color.WHITE);
					btnProcess.setBorder(new LineBorder(new Color(0, 0, 0)));
					btnProcess.setBackground(new Color(3, 65, 68));
					btnProcess.setBounds(0, 0, 63, 38);
					toolbarPanel.add(btnProcess);
					
					btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					btnPrint.setText("Print");
					btnPrint.setForeground(Color.WHITE);
					btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
					btnPrint.setBackground(new Color(3, 65, 68));
					btnPrint.setBounds(62, 0, 63, 38);
					toolbarPanel.add(btnPrint);
					
					btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					btnSaveFile.setText("Save");
					btnSaveFile.setForeground(Color.WHITE);
					btnSaveFile.setBorder(new LineBorder(new Color(0, 0, 0)));
					btnSaveFile.setBackground(new Color(3, 65, 68));
					btnSaveFile.setBounds(124, 0, 63, 38);
					toolbarPanel.add(btnSaveFile);
					
					btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", this::ChangeMode, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					btnMode.setText("Mode");
					btnMode.setForeground(Color.WHITE);
					btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
					btnMode.setBackground(new Color(3, 65, 68));
					btnMode.setBounds(186, 0, 63, 38);
					toolbarPanel.add(btnMode);
				
			bodyPanel = new JPanel();
			bodyPanel.setSize(1097,560);
		    bodyPanel.setLayout(new CardLayout());
		    formPanel.add(bodyPanel,BorderLayout.CENTER);
		        
			JPanel footerPanel = new JPanel();
			footerPanel.setLayout(null);	
			footerPanel.setBackground(new Color(3, 65, 68));
			footerPanel.setPreferredSize(new Dimension(1097, 20));
			formPanel.add(footerPanel,BorderLayout.SOUTH);
			
			//disable process button
    		btnProcess.setEnabled(false);
    		btnProcess.setForeground(Color.GRAY);
    		//disable print button
    		btnPrint.setEnabled(false);
    		btnPrint.setForeground(Color.GRAY);
    		//disable save button
    		btnSaveFile.setEnabled(false);
    		btnSaveFile.setForeground(Color.GRAY);
    		//disable mode button
    		btnMode.setEnabled(false);
    		btnMode.setForeground(Color.GRAY);
    		
    		// Create a common mouse listener for the buttons
    	    MouseListener buttonMouseListener = new MouseAdapter() {
    	        @Override
    	        public void mousePressed(MouseEvent e) {
    	            JButton clickedButton = (JButton) e.getSource();
    	            updateButtonBackground(clickedButton);
    	        }
    	    };
    		
    		// Add the mouse listener to each button
    	    btnDashboard.addMouseListener(buttonMouseListener);
    	    btnProduct.addMouseListener(buttonMouseListener);
    	    btnCustomer.addMouseListener(buttonMouseListener);
    	    btnOrder.addMouseListener(buttonMouseListener);
    	    btnReceiving.addMouseListener(buttonMouseListener);
    	    btnStock.addMouseListener(buttonMouseListener);
    	    btnSupplier.addMouseListener(buttonMouseListener);  	 
    	    
    	 // Create a common mouse listener for the labels
    	    MouseListener labelMouseListener = new MouseAdapter() {
    	        @Override
    	        public void mousePressed(MouseEvent e) {
    	            JLabel clickedLabel = (JLabel) e.getSource();
    	            updateLabelBackground(clickedLabel);
    	        }
    	    };
    		
    		// Add the mouse listener to each label
    	    lblRole.addMouseListener(labelMouseListener);
    	    lblAvatarIcon.addMouseListener(labelMouseListener);
    		
	}
	
	 private void showForm(JPanel panelToShow) {
	        // Remove any existing panels from the bodyPanel
	        bodyPanel.removeAll();

	        // Add the new panel to the bodyPanel
	        bodyPanel.add(panelToShow);

	        // Revalidate and repaint to ensure proper layout
	        bodyPanel.revalidate();
	        bodyPanel.repaint();
	    }
	
	 private void printTable(ActionEvent e) {
		 //print the order table
	
		}	
	 private void reflectReceivingEntryToStock(ActionEvent e) {		
		 
		}
	 private void saveAsFile(ActionEvent e) {
			// Logic for saving as a file 
		}
	 private void ChangeMode(ActionEvent e) {
			// Logic for toggling between Light/Dark mode
		}
	 private void updateButtonBackground(JButton clickedButton) {
		 
		 lblRole.setBorder(null);
		 lblUsername.setBorder(null);
		 lblAvatarIcon.setBorder(null);
		 
		    // Reset background color for the previously clicked button or label
		    if (currentClickedButton != null) {
		        currentClickedButton.setBackground(new Color(3, 65, 68));
		    }

		    // Set the background color for the newly clicked button
		    clickedButton.setBackground(new Color(4, 101, 106));

		    // Update the reference to the currently clicked button
		    currentClickedButton = clickedButton;
		    // Update the reference to the currently clicked label
		    currentClickedLabel = null;
		}
	// Helper method to reset background color for all buttons and labels
	 private void updateLabelBackground(JLabel clickedLabel) {
	     btnDashboard.setBackground(new Color(3, 65, 68));
	     btnProduct.setBackground(new Color(3, 65, 68));
	     btnCustomer.setBackground(new Color(3, 65, 68));
	     btnOrder.setBackground(new Color(3, 65, 68));
	     btnReceiving.setBackground(new Color(3, 65, 68));
	     btnStock.setBackground(new Color(3, 65, 68));
	     btnSupplier.setBackground(new Color(3, 65, 68));
	     
	  // Reset background color for the previously clicked button or label

		    if (currentClickedLabel != null) {
		    	currentClickedLabel.setBorder(null);
		    }

		    // Set the background color for the newly clicked button
		    clickedLabel.setBorder(new LineBorder(new Color(4, 101, 106), 1, true));

		    // Update the reference to the currently clicked button
		    currentClickedLabel = clickedLabel;
		    // Update the reference to the currently clicked label
		    currentClickedButton = null;
	 }	
	 
}
