package com.lanuza.form;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

import java.awt.Component;

public class Dashboard extends JFrame implements ActionListener {
	    // JPanel warehousePanel;
	    private JLabel greetLabel;
	    private JButton btnProduct,btnLogout, btnReceiving,btnOrder,btnInventory;
	    private JPanel dashboardPanel;
	    //private JPanel dpPanel;
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    private JPanel panel_1;
	    private JLabel lblUsername;
	    private JLabel lblRole;

	   public Dashboard() {
	    	
	        this.setTitle("PhilDrinks"); // set the title if Frame
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
	        this.setResizable(false);
	        this.setSize(1000, 600);        

	        // warehousePanel = new JPanel();
	        // warehousePanel.setSize(800, 350); // this set the x and y dimension of Frame
	        // warehousePanel.setBounds(90, 120, 800, 350);
	        // warehousePanel.setVisible(true);
	        // warehousePanel.setBackground(Color.WHITE);

	        JPanel menuPanel = new JPanel();
	        menuPanel.setBounds(0, 0, 250, 561);
	        //menuPanel.setOpaque(false);
	        menuPanel.setVisible(true);
	        // menuPanel.setBackground(new Color(29, 77, 122)); blue
	        menuPanel.setBackground(new Color(3, 65, 68));
	        menuPanel.setBorder(BorderFactory.createEtchedBorder());
	        // accountLabel.setBorder(BorderFactory.createEtchedBorder());
	     	 
	        btnProduct = new JButton();
	        btnProduct.setFocusPainted(false);
	        btnProduct.setText("Product");
	        btnProduct.setForeground(Color.WHITE);
	        btnProduct.setFont(mainFont);
	        btnProduct.setBackground(new Color(3, 65, 68));
	        btnProduct.setBounds(10, 234, 230, 54);
	        btnProduct.addActionListener(this);


	        btnReceiving = new JButton();
	        btnReceiving.setFocusPainted(false);
	        btnReceiving.setFont(mainFont);
	        btnReceiving.setText("Receiving");
	        btnReceiving.setBounds(10, 288, 230, 54);
	        // receivingButton.setBackground(new Color(29, 77, 122)); blue
	        btnReceiving.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnReceiving.setForeground(new Color(255, 255, 255));
	        // receivingButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnReceiving.addActionListener(this);

	        btnOrder = new JButton();
	        btnOrder.setFocusPainted(false);
	        btnOrder.setFont(mainFont);
	        btnOrder.setText("Order");
	        btnOrder.setBounds(10, 342, 230, 54);
	        // distributionButton.setBackground(new Color(29, 77, 122));blue
	        btnOrder.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnOrder.setForeground(new Color(255, 255, 255));
	        // distributionButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnOrder.addActionListener(this);

	        btnInventory = new JButton();
	        btnInventory.setFocusPainted(false);
	        btnInventory.setFont(mainFont);
	        btnInventory.setText("Inventory");
	        btnInventory.setBounds(10, 396, 230, 54);
	        // inquiryButton.setBackground(new Color(29, 77, 122)); blue
	        btnInventory.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnInventory.setForeground(new Color(255, 255, 255));
	        // inquiryButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnInventory.addActionListener(this);

	        btnLogout = new JButton();
	        btnLogout.setFocusPainted(false);
	        btnLogout.setFont(mainFont);
	        btnLogout.setText("Logout");
	        btnLogout.setBounds(10, 450, 230, 54);
	        // logoutButton.setBackground(new Color(29, 77, 122)); blue
	        btnLogout.setBackground(new Color(3, 65, 68)); // dark cyan
	        btnLogout.setForeground(new Color(255, 255, 255));
	        // logoutButton.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnLogout.addActionListener(this);

	        ImageIcon img = new ImageIcon("logo.png"); // create an image
	        this.setIconImage(img.getImage());// change icon of Frame
	        // this.getContentPane().setBackground(new Color(3, 65, 68)); // dark cyan
	        this.getContentPane().setBackground(new Color(220, 211, 211)); // light gray
	        menuPanel.setLayout(null);
	    
	        getContentPane().setLayout(null);       
	        
	        dashboardPanel = new JPanel();
	        dashboardPanel.setForeground(new Color(255, 255, 255));
	        dashboardPanel.setBounds(250, 0, 734, 561);
	        dashboardPanel.setVisible(true);
	        dashboardPanel.setLayout(null);
	        
	        	greetLabel = new JLabel();
	        	greetLabel.setBounds(24, 25, 438, 35);
	        	greetLabel.setText("Welcome to PhilDrinks Warehouse");
	        	greetLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
	        	greetLabel.setForeground(new Color(3, 65, 68));       
	        	greetLabel.setVisible(true);
	                       
	        menuPanel.add(btnProduct);
	        menuPanel.add(btnReceiving);
	        menuPanel.add(btnOrder);
	        menuPanel.add(btnInventory);
	        menuPanel.add(btnLogout);     
	        dashboardPanel.add(greetLabel);
	        
	        
	        this.getContentPane().add(menuPanel);
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(41, 24, 162, 128);
	        menuPanel.add(panel);
	        panel.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		dispose();
		    		Account execAccount = new Account();
	        	}
	        });
	        lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
	        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/com/lanuza/icons/user.png")));
	        lblNewLabel.setBounds(0, 0, 162, 128);
	        panel.add(lblNewLabel);
	        
	        panel_1 = new JPanel();
	        panel_1.setBorder(null);
	        panel_1.setOpaque(false);
	        panel_1.setBounds(37, 152, 170, 60);
	        menuPanel.add(panel_1);
	        panel_1.setLayout(null);
	        
	        lblUsername = new JLabel("Juan Dela Cruz");
	        lblUsername.setVerticalAlignment(SwingConstants.TOP);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblUsername.setForeground(Color.WHITE);
	        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
	        lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblUsername.setBorder(null);
	        lblUsername.setBounds(5, 1, 163, 27);
	        panel_1.add(lblUsername);
	        
	        lblRole = new JLabel("Guest");
	        lblRole.setVerticalAlignment(SwingConstants.TOP);
	        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        lblRole.setForeground(Color.WHITE);
	        lblRole.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
	        lblRole.setBounds(42, 28, 84, 21);
	        panel_1.add(lblRole);
	        this.getContentPane().add(dashboardPanel);
	        
	        this.setLocationRelativeTo(null);
	        this.setVisible(true); // this make Frame visible
	        
	    }

	    public void actionPerformed(ActionEvent e) {
	    	/*if(e.getSource() == lblUsername) {
	    		this.dispose();
	    		Account execAccount = new Account();
	    		
	    	}else */
	    		if(e.getSource() == btnProduct) {
	    		this.dispose();	    		
	    		Product execProduct = new Product();
	    		
	    		
	    	}else if(e.getSource() == btnReceiving) {
	    		//this.dispose(); disabled the dispose method to allow multi instantiation of the frame
	    		ReceivingModern execReceiving = new ReceivingModern();
	    		
	    	}else if(e.getSource() == btnOrder) {
	    		this.dispose();
	    		Order execOrder = new Order();
	    	
	    	}else if(e.getSource() == btnInventory) {
		    		this.dispose();
		    		Inventory execInventory = new Inventory();
		    		   			   		
	    	}else if (e.getSource() == btnLogout) {        	
	        	this.dispose();
	        	Login Logout = new Login();
	        }       
	        
	    }
	}
