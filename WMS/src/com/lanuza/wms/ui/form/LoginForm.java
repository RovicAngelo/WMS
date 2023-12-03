package com.lanuza.wms.ui.form;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

import com.lanuza.wms.model.Account;
import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;

import java.awt.event.*;

public class LoginForm extends JFrame{
	private ProfileForm profileForm;
		private final AccountDAO accountDAO;
		private final AccountService accountService;
		private static JButton btnClear, btnLogin;
	    static JTextField txtUsername;
	    static JPasswordField txtPassword;
	    static JPanel authenticatePanel;
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    private JFrame frame;

		public LoginForm() {		
			this.accountDAO = new AccountDAOImpl();
			this.accountService = new AccountServiceImpl(accountDAO);
			initialize();

		}
		
		public void handleLogin() {
	        String username = txtUsername.getText();// Get the entered username from UI component
	        String password = txtPassword.getText();// Get the entered password from UI component

	        // Use AccountService to validate credentials and get account details
	        Account account = accountService.getAccountByUsernameAndPassword(username, password);

	        if (account != null) {
	            //Successfully logged in
	            //Open the profile form and update it with the account details
	            profileForm.updateProfile(account);
	            //hide or close the login form at this point
	        } else {
	            // Handle failed login attempt
	            // Display an error message, reset fields, or take appropriate action
	        }
	    }
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		private JLabel lblcreateAccount;
		

	    public void initialize() {
	        frame = new JFrame(); // JFrame - a GUI window to add component to
	        frame.setSize(710, 370); // this set the x and y dimension of Frame
	        frame.setTitle("PhilDrinks"); // set the title if Frame
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
	        infoPanel.setBounds(1, 1, 263, 330); // this set the x and y dimension of Frame
	        infoPanel.setVisible(true);
	        infoPanel.setLayout(null);

	        JLabel loginLabel = new JLabel("Login");
	        loginLabel.setBounds(216, 48, 72, 24);
	        loginLabel.setBackground(new Color(3, 65, 68));
	        loginLabel.setForeground(new Color(3, 65, 68));
	        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));	        

	        JLabel userLabel = new JLabel("username: ");
	        userLabel.setBounds(70, 126, 72, 14);
	        userLabel.setFont(mainFont);
	        userLabel.setForeground(Color.BLACK);
	        
	        JLabel passwordLabel = new JLabel("password: ");
	        passwordLabel.setBounds(70, 172, 72, 14);
	        passwordLabel.setFont(mainFont);
	        passwordLabel.setForeground(Color.BLACK);
	        
	        lblcreateAccount = new JLabel("Create Account?");
	        lblcreateAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        lblcreateAccount.setBounds(226, 245, 100, 24);
	        authenticatePanel.add(lblcreateAccount);
	        frame.getContentPane().add(infoPanel);	   

	        txtUsername = new JTextField(20);
	        txtUsername.setBounds(141, 118, 237, 32);
	        
	        txtPassword = new JPasswordField();
	        txtPassword.setBounds(141, 164, 237, 32);
	        
	        JTextArea txtrWarehouse = new JTextArea();
	        txtrWarehouse.setBackground(new Color(240, 240, 240));
	        txtrWarehouse.setFont(new Font("Arial", Font.BOLD, 23));
	        txtrWarehouse.setText("Warehouse\r\nManagement\r\nSystem");
	        txtrWarehouse.setBounds(43, 106, 158, 85);
	        infoPanel.add(txtrWarehouse);

	        btnClear = new JButton();
	        btnClear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		txtUsername.setText("");
		            txtPassword.setText("");        		
	        	}
	        });
	        btnClear.setBounds(141, 207, 65, 30);
	        btnClear.setFont(mainFont);
	        btnClear.setText("Clear");
	        btnClear.setBackground(new Color(33, 167, 213));
	        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());	           

	        btnLogin = new JButton();
	        btnLogin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		String username = txtUsername.getText();// Get the entered username from UI component
	    	        String password = txtPassword.getText();// Get the entered password from UI component
	    	        
	        		if(username.isEmpty() && password.isEmpty()) {
	        			JOptionPane.showMessageDialog(null, "Missing Information");
	        		
		        	}else {

		    	        // Use AccountService to validate credentials and get account details
		    	        Account account = accountService.getAccountByUsernameAndPassword(username, password);

		    	        if (account != null) {
		    	            //Successfully logged in
		    	            //Open the profile form and update it with the account details
		    	            profileForm.updateProfile(account);
		    	            //close the login form
		    	            frame.dispose();
							new Dashboard();
		    	        } else {
		    	            // Display an error message and reset fields
		    	        	JOptionPane.showMessageDialog(null, "Wrong username or password");
		    	        	txtUsername.setText("");
		    	        	txtPassword.setText("");
		    	        }
		        	}        		        		
	        	}
	        });
	        btnLogin.setBounds(216, 207, 162, 30);
	        btnLogin.setFont(mainFont);
	        btnLogin.setText("Login");
	        btnLogin.setBackground(new Color(33, 167, 213));
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
	        btnSignUp.setBounds(324, 242, 54, 30);
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
