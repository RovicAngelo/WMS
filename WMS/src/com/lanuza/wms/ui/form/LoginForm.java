package com.lanuza.wms.ui.form;

import java.awt.*;
import javax.swing.*;

import com.lanuza.wms.model.Account;
import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;

import java.awt.event.*;

public class LoginForm extends JFrame{

		private static final long serialVersionUID = 1L;
		private final AccountDAO accountDAO;
		private final AccountService accountService;
		private static JButton btnClear, btnLogin;
	    static JTextField txtUsername;
	    static JPasswordField txtPassword;
	    static JPanel authenticatePanel;
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    private JFrame frame;
		private JLabel lblcreateAccount;

		public LoginForm() {
			//this.profileForm = new ManageProfileForm();
			this.accountDAO = new AccountDAOImpl();
			this.accountService = new AccountServiceImpl(accountDAO);
			initialize();

		}	
		
		private void performLogin() {
	        String username = txtUsername.getText();
	        String password = new String(txtPassword.getPassword());

	        if (username.isEmpty() || password.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Missing Information");
	        } else {
	        	
	            // Use AccountService to validate credentials and get account details
	            Account account = accountService.getAccountByUsernameAndPassword(username, password);
	            

	            if (account != null) {
	                // Successfully logged in
	                // Close the login form
	                frame.dispose();
	                
	                int id =  account.getAccountId();

	                // Open the Dashboard
	                new ManageAllForm(id).setVisible(true);;
	            } else {
	                // Display an error message and reset fields
	                JOptionPane.showMessageDialog(null, "Wrong username or password");
	                txtUsername.setText("");
	                txtPassword.setText("");
	                txtUsername.requestFocus();
	            }
	        }
	    }
				
	    public void initialize() {
	        frame = new JFrame(); // JFrame - a GUI window to add component to
	        frame.setSize(710, 370); // this set the x and y dimension of Frame
	        frame.setTitle("wms"); // set the title if Frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
	        frame.setResizable(false);       
	        frame.getContentPane().setLayout(null);	   
	        //frame.setUndecorated(true);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true); // this make Frame visible
	       
	        authenticatePanel = new JPanel();
	        authenticatePanel.setToolTipText("");
	        authenticatePanel.setBounds(260, 1, 440, 330); // this set the x and y dimension of Frame
	        authenticatePanel.setVisible(true);
	        // authenticatePanel.setBackground(new Color(177, 202, 197));
	        // authenticatePanel.setBackground(new Color(20, 229, 235));
	        authenticatePanel.setBackground(Color.WHITE);

	        JPanel infoPanel = new JPanel();
	        infoPanel.setBackground(new Color(3, 65, 68));
	        infoPanel.setForeground(new Color(255, 255, 255));
	        infoPanel.setBounds(1, 1, 263, 330);
	        infoPanel.setLayout(null);

	        JLabel loginLabel = new JLabel("Login");
	        loginLabel.setBounds(216, 48, 72, 24);
	        loginLabel.setBackground(new Color(3, 65, 68));
	        loginLabel.setForeground(new Color(3, 65, 68));
	        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));	        

	        JLabel userLabel = new JLabel("username: ");
	        userLabel.setBounds(61, 118, 81, 32);
	        userLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	        userLabel.setForeground(Color.BLACK);
	        
	        JLabel passwordLabel = new JLabel("password: ");
	        passwordLabel.setBounds(61, 161, 81, 35);
	        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	        passwordLabel.setForeground(Color.BLACK);
	        
	        lblcreateAccount = new JLabel("Create Account?");
	        lblcreateAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        lblcreateAccount.setBounds(226, 249, 100, 24);
	        authenticatePanel.add(lblcreateAccount);
	        frame.getContentPane().add(infoPanel);	   

	        txtUsername = new JTextField(20);
	        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtUsername.setBounds(141, 118, 237, 32);
	        
	        txtPassword = new JPasswordField();
	        txtPassword.setBounds(141, 164, 237, 32);
	        
	        JTextArea txtrWarehouse = new JTextArea();
	        txtrWarehouse.setForeground(new Color(255, 255, 255));
	        txtrWarehouse.setBackground(new Color(3, 65, 68));
	        txtrWarehouse.setFont(new Font("Arial", Font.BOLD, 23));
	        txtrWarehouse.setText("Warehouse\r\nManagement\r\nSystem");
	        txtrWarehouse.setBounds(43, 106, 158, 85);
	        infoPanel.add(txtrWarehouse);

	        btnClear = new JButton();
	        btnClear.setForeground(new Color(255, 255, 255));
	        btnClear.setFocusPainted(false);
	        btnClear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		txtUsername.setText("");
		            txtPassword.setText("");        		
	        	}
	        });
	        btnClear.setBounds(141, 207, 65, 38);
	        btnClear.setFont(mainFont);
	        btnClear.setText("Clear");
	        btnClear.setBackground(new Color(3, 65, 68));
	        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());	           

	        btnLogin = new JButton();
	        btnLogin.setForeground(new Color(255, 255, 255));
	        btnLogin.setFocusPainted(false);
	        btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               performLogin();
	            }
	        });

	        btnLogin.setBounds(216, 207, 162, 38);
	        btnLogin.setFont(mainFont);
	        btnLogin.setText("Login");
	        btnLogin.setBackground(new Color(3, 65, 68));
	        btnLogin.setBorder(BorderFactory.createRaisedBevelBorder());
	        
	        JButton btnSignUp = new JButton();
	        btnSignUp.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		frame.dispose();
	        		new NewAccountForm(); 
	        	}
	        });	        
	        btnSignUp.setForeground(new Color(0, 0, 255));
	        btnSignUp.setText("Sign Up");
	        btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        btnSignUp.setBorder(null);
	        btnSignUp.setBackground(Color.WHITE);
	        btnSignUp.setBounds(324, 246, 54, 30);
	        authenticatePanel.add(btnSignUp);

	        ImageIcon img = new ImageIcon("logo.png"); // create an image
	        this.setIconImage(img.getImage());// change icon of Frame
	        this.getContentPane().setBackground(new Color(3, 65, 68));
	        
	        authenticatePanel.setLayout(null);
	        authenticatePanel.add(btnClear);
	        authenticatePanel.add(btnLogin);
	        authenticatePanel.add(loginLabel);
	        authenticatePanel.add(userLabel);
	        authenticatePanel.add(passwordLabel);
	        authenticatePanel.add(txtUsername);
	        authenticatePanel.add(txtPassword);
	        frame.getContentPane().add(authenticatePanel);
	    }
	}
