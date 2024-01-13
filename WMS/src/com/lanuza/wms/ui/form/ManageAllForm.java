package com.lanuza.wms.ui.form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

public class ManageAllForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblProfile;
    private JPanel bodyPanel;
    private JButton btnProduct, btnReceiving, btnDashboard, btnSupplier, btnOrder, btnInventory, btnLogout, btnCustomer;
    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
    private JLabel lblRole,lblUsername;

    public ManageAllForm(int id) {
        // Initialize the JFrame
        setTitle("wms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1347, 700);

        // Create the bodyPanel
        bodyPanel = new JPanel();
        bodyPanel.setSize(1097,664);
        bodyPanel.setLayout(new CardLayout());
        getContentPane().add(bodyPanel, BorderLayout.CENTER);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 250, 561);
        menuPanel.setBackground(new Color(3, 65, 68));
        menuPanel.setBorder(BorderFactory.createEtchedBorder());
        // Set the preferred size of menuPanel
        menuPanel.setPreferredSize(new Dimension(250, 664));
     	 
        btnProduct = new JButton();
        btnProduct.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageProductForm());
        	}
        });
        btnProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnProduct.setFocusPainted(false);
        btnProduct.setText("Product");
        btnProduct.setForeground(Color.WHITE);
        btnProduct.setFont(mainFont);
        btnProduct.setBackground(new Color(3, 65, 68));
        btnProduct.setBounds(10, 257, 230, 55);

        btnReceiving = new JButton();
        btnReceiving.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageReceivingForm());
        	}
        });
        btnReceiving.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnReceiving.setFocusPainted(false);
        btnReceiving.setFont(mainFont);
        btnReceiving.setText("Receiving");
        btnReceiving.setBounds(10, 473, 230, 55);
        // receivingButton.setBackground(new Color(29, 77, 122)); blue
        btnReceiving.setBackground(new Color(3, 65, 68)); // dark cyan
        btnReceiving.setForeground(new Color(255, 255, 255));
        // receivingButton.setBorder(BorderFactory.createRaisedBevelBorder());

        btnOrder = new JButton();
        btnOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageOrderForm()); 		
        	}
        });
        btnOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnOrder.setFocusPainted(false);
        btnOrder.setFont(mainFont);
        btnOrder.setText("Order");
        btnOrder.setBounds(10, 419, 230, 55);
        // distributionButton.setBackground(new Color(29, 77, 122));blue
        btnOrder.setBackground(new Color(3, 65, 68)); // dark cyan
        btnOrder.setForeground(new Color(255, 255, 255));
        // distributionButton.setBorder(BorderFactory.createRaisedBevelBorder());

        btnInventory = new JButton();
        btnInventory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageStockForm());
        	}
        });
        btnInventory.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnInventory.setFocusPainted(false);
        btnInventory.setFont(mainFont);
        btnInventory.setText("Stock");
        btnInventory.setBounds(10, 527, 230, 55);
        // inquiryButton.setBackground(new Color(29, 77, 122)); blue
        btnInventory.setBackground(new Color(3, 65, 68)); // dark cyan
        btnInventory.setForeground(new Color(255, 255, 255));
        // inquiryButton.setBorder(BorderFactory.createRaisedBevelBorder());

        btnLogout = new JButton();
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
        // logoutButton.setBackground(new Color(29, 77, 122)); blue
        btnLogout.setBackground(new Color(3, 65, 68)); // dark cyan
        btnLogout.setForeground(new Color(255, 255, 255));
        menuPanel.setLayout(null);
        // logoutButton.setBorder(BorderFactory.createRaisedBevelBorder());       	              	                       
       
        
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBounds(35, 29, 174, 120);
        menuPanel.add(panel);
        panel.setLayout(null);
        
        lblProfile = new JLabel("");
        lblProfile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new ManageProfileForm(id,true);
        		revalidate();
        		repaint();
        	}
        });
        lblProfile.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblProfile.setVerticalAlignment(SwingConstants.BOTTOM);
        lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfile.setHorizontalTextPosition(SwingConstants.CENTER);
        lblProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProfile.setIcon(new ImageIcon(ManageAllForm.class.getResource("/com/lanuza/wms/ui/resources/icons/user.png")));
        lblProfile.setBounds(0, 0, 174, 120);
        panel.add(lblProfile);
        
        btnCustomer = new JButton();
        btnCustomer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageCustomerForm());
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
        
        btnSupplier = new JButton();
        btnSupplier.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageSupplierForm());
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
        
        
        btnDashboard = new JButton();
        btnDashboard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showForm(new ManageDashboardForm());
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
        
        menuPanel.add(btnDashboard);
        menuPanel.add(btnProduct);
        menuPanel.add(btnSupplier);
        menuPanel.add(btnCustomer);
        menuPanel.add(btnOrder);
        menuPanel.add(btnLogout);
        menuPanel.add(btnReceiving);
        menuPanel.add(btnInventory);
        getContentPane().add(menuPanel, BorderLayout.WEST);
        
        lblUsername = new JLabel("Juan Dela Cruz");
        lblUsername.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new ManageProfileForm(id,true); 
        		revalidate();
        		repaint();
        	}
        });
        
 
        lblUsername.setVerticalAlignment(SwingConstants.TOP);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
        lblUsername.setBorder(null);
        lblUsername.setBounds(35, 150, 174, 17);
        menuPanel.add(lblUsername);
        
        lblRole = new JLabel("Admin");
        lblRole.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        		new ViewAllAccountForm();
        	}
        });
        lblRole.setVerticalAlignment(SwingConstants.TOP);
        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblRole.setForeground(Color.WHITE);
        lblRole.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole.setBounds(35, 171, 174, 17);
        menuPanel.add(lblRole);


        ManageDashboardForm dashboardView = new ManageDashboardForm();
        //showForm(dashboardView);
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
}

