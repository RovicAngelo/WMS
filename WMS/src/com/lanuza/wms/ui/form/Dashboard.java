package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Dashboard implements ActionListener {
		private static final long serialVersionUID = 1L;
	    private JButton btnProduct,btnLogout, btnReceiving,btnOrder,btnInventory,btnCustomer,btnSupplier;
	    private JPanel dashboardPanel,panel_1;
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    private JLabel lblUsername,lblRole,greetLabel;
	    private JFrame frame;
	    
	    public Dashboard() {	    	
	    	initialize();
	    }

	    private void initialize() {
	    	frame = new JFrame();
	    	frame.setTitle("PhilDrinks"); // set the title if Frame
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
	    	frame.setResizable(false);
	    	frame.setSize(1000, 600);  

	        JPanel menuPanel = new JPanel();
	        menuPanel.setBounds(0, 0, 250, 561);
	        menuPanel.setVisible(true);
	        menuPanel.setBackground(new Color(3, 65, 68));
	        menuPanel.setBorder(BorderFactory.createEtchedBorder());
	        
	        dashboardPanel = new JPanel();
	        dashboardPanel.setForeground(new Color(255, 255, 255));
	        dashboardPanel.setBounds(250, 0, 734, 561);
	        dashboardPanel.setVisible(true);
	        dashboardPanel.setLayout(null);
	        
	        	greetLabel = new JLabel();
	        	greetLabel.setBounds(23, 25, 438, 35);
	        	greetLabel.setText("Welcome to PhilDrinks Warehouse");
	        	greetLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
	        	greetLabel.setForeground(new Color(3, 65, 68));       
	        	greetLabel.setVisible(true);
	     	 
	        btnProduct = new JButton();
	        btnProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnProduct.setFocusPainted(false);
	        btnProduct.setText("Product");
	        btnProduct.setForeground(Color.WHITE);
	        btnProduct.setFont(mainFont);
	        btnProduct.setBackground(new Color(3, 65, 68));
	        btnProduct.setBounds(10, 249, 230, 42);
	        btnProduct.addActionListener(this);


	        btnReceiving = new JButton();
	        btnReceiving.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnReceiving.setFocusPainted(false);
	        btnReceiving.setFont(mainFont);
	        btnReceiving.setText("Receiving");
	        btnReceiving.setBounds(10, 378, 230, 42);
	        // receivingButton.setBackground(new Color(29, 77, 122)); blue
	        btnReceiving.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnReceiving.setForeground(new Color(255, 255, 255));
	        // receivingButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnReceiving.addActionListener(this);

	        btnOrder = new JButton();
	        btnOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnOrder.setFocusPainted(false);
	        btnOrder.setFont(mainFont);
	        btnOrder.setText("Order");
	        btnOrder.setBounds(10, 422, 230, 42);
	        // distributionButton.setBackground(new Color(29, 77, 122));blue
	        btnOrder.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnOrder.setForeground(new Color(255, 255, 255));
	        // distributionButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnOrder.addActionListener(this);

	        btnInventory = new JButton();
	        btnInventory.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnInventory.setFocusPainted(false);
	        btnInventory.setFont(mainFont);
	        btnInventory.setText("Stock");
	        btnInventory.setBounds(10, 465, 230, 42);
	        // inquiryButton.setBackground(new Color(29, 77, 122)); blue
	        btnInventory.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnInventory.setForeground(new Color(255, 255, 255));
	        // inquiryButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnInventory.addActionListener(this);

	        btnLogout = new JButton();
	        btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnLogout.setFocusPainted(false);
	        btnLogout.setFont(mainFont);
	        btnLogout.setText("Logout");
	        btnLogout.setBounds(10, 508, 230, 42);
	        // logoutButton.setBackground(new Color(29, 77, 122)); blue
	        btnLogout.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnLogout.setForeground(new Color(255, 255, 255));
	        // logoutButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnLogout.addActionListener(this);        	              	                       
	       
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(41, 24, 162, 128);
	        menuPanel.add(panel);
	        panel.setLayout(null);
	        
	        JLabel lblProfilel = new JLabel("");
	        lblProfilel.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		frame.dispose();
	        		new ManageProfileForm(true);
	        	}
	        });
	        lblProfilel.setVerticalTextPosition(SwingConstants.BOTTOM);
	        lblProfilel.setVerticalAlignment(SwingConstants.BOTTOM);
	        lblProfilel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblProfilel.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblProfilel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        lblProfilel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/lanuza/wms/ui/resources/icons/user.png")));
	        lblProfilel.setBounds(0, 0, 162, 128);
	        panel.add(lblProfilel);
	        
	        panel_1 = new JPanel();
	        panel_1.setBorder(null);
	        panel_1.setOpaque(false);
	        panel_1.setBounds(37, 152, 170, 49);
	        menuPanel.add(panel_1);
	        panel_1.setLayout(null);
	        
	        lblUsername = new JLabel("Juan Dela Cruz");
	        lblUsername.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		frame.dispose();
	        		new ManageProfileForm(true);
	        	}
	        });
	        lblUsername.setVerticalAlignment(SwingConstants.TOP);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblUsername.setForeground(Color.WHITE);
	        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
	        lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblUsername.setBorder(null);
	        lblUsername.setBounds(5, 1, 163, 25);
	        panel_1.add(lblUsername);
	        
	        lblRole = new JLabel("Guest");
	        lblRole.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		new ViewAllAccountForm();
	        	}
	        });
	        lblRole.setVerticalAlignment(SwingConstants.TOP);
	        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        lblRole.setForeground(Color.WHITE);
	        lblRole.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
	        lblRole.setBounds(5, 24, 155, 25);
	        panel_1.add(lblRole);
	        
	        btnCustomer = new JButton();
	        btnCustomer.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnCustomer.setText("Customer");
	        btnCustomer.setForeground(Color.WHITE);
	        btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
	        btnCustomer.setFocusPainted(false);
	        btnCustomer.setBackground(new Color(3, 65, 68));
	        btnCustomer.setBounds(10, 292, 230, 42);
	        menuPanel.add(btnCustomer);
	        btnCustomer.addActionListener(this);
	        
	        btnSupplier = new JButton();
	        btnSupplier.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnSupplier.setText("Supplier");
	        btnSupplier.setForeground(Color.WHITE);
	        btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
	        btnSupplier.setFocusPainted(false);
	        btnSupplier.setBackground(new Color(3, 65, 68));
	        btnSupplier.setBounds(10, 335, 230, 42);
	        btnSupplier.addActionListener(this);
	        menuPanel.add(btnSupplier);
	        
	        
	        JButton btnDashboard = new JButton();
	        btnDashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
	        btnDashboard.setText("Dashboard");
	        btnDashboard.setForeground(Color.WHITE);
	        btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 12));
	        btnDashboard.setFocusPainted(false);
	        btnDashboard.setBackground(new Color(3, 65, 68));
	        btnDashboard.setBounds(10, 206, 230, 42);
	        menuPanel.add(btnDashboard);
	        frame.getContentPane().add(dashboardPanel);
	        
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true); // this make Frame visible
	        
	        ImageIcon img = new ImageIcon("logo.png"); // create an image
	        frame.setIconImage(img.getImage());// change icon of Frame
	        // this.getContentPane().setBackground(new Color(3, 65, 68)); // dark cyan
	        frame.getContentPane().setBackground(new Color(220, 211, 211)); // light gray
	        menuPanel.setLayout(null);
	    
	        frame.getContentPane().setLayout(null);      
	        
	        menuPanel.add(btnProduct);
	        menuPanel.add(btnReceiving);
	        menuPanel.add(btnOrder);
	        menuPanel.add(btnInventory);
	        menuPanel.add(btnLogout);     
	        dashboardPanel.add(greetLabel);
	        
	        
	        frame.getContentPane().add(menuPanel);
	        
	    }

	    public void actionPerformed(ActionEvent e) {
	    	if(e.getSource() == btnProduct) {
	    		frame.dispose();	    		
	    		new ProductForm();
	    		    		
	    	}else if(e.getSource() == btnCustomer) {
		    	frame.dispose(); 
		    	new CustomerForm();
		    		
		    }else if(e.getSource() == btnSupplier) {
	    		frame.dispose(); 
	    		new SupplierForm();
	    		
	    	}else if(e.getSource() == btnReceiving) {
	    		frame.dispose(); 
	    		new ReceivingEntryForm();
	    		
	    	}else if(e.getSource() == btnOrder) {
	    		frame.dispose();
	    		new PurchasedOrderForm();
	    	
	    	}else if(e.getSource() == btnInventory) {
	    		frame.dispose();
		    		new StockForm();
		    		   			   		
	    	}else if (e.getSource() == btnLogout) {        	
	    		frame.dispose();
	        	new LoginForm();
	        }       
	        
	    }
	}
