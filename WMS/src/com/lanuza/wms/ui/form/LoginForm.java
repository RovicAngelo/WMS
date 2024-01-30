package com.lanuza.wms.ui.form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;
import com.lanuza.wms.ui.components.HoverEffect;
import com.lanuza.wms.ui.components.PlaceholderHandler;

public class LoginForm extends JFrame{

		private static final long serialVersionUID = 1L;
		private final AccountDAO accountDAO;
		private final AccountService accountService;	
		
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    
	    private final Color btnOriginalColor = new Color(3,65,68);
	    private final Color btnHoverColor = new Color(  4, 101, 106);
	    
	    private final Color lblHoverColor = new Color(250,250,250);
	    private final Color lblOriginalColor = new Color(240,240,240);
	  
	    private JFrame frame;
	    private JPanel signinPanel,signupPanel,bodyPanel;
	    
	    PlaceholderHandler placeholderHandler = new PlaceholderHandler(mainFont);
	    
	    private JLabel lblSignUp,lblHaveAccount,
	    lblLoginTitle,lblSignIn,lblIHaveAccount,
	    lblSignUpTitle;
	    
	    private JButton btnClear,btnLogin,btnClear1,btnSave;	   	    	   	    
	    private JTextField txtUsername,txtNewUsername,txtNewName;		    
	    private JPasswordField txtPassword,txtNewPassword;	   	  
	    private JComboBox<String> newRolecomboBox;

		public LoginForm() {
			//this.profileForm = new ManageProfileForm();
			this.accountDAO = new AccountDAOImpl();
			this.accountService = new AccountServiceImpl(accountDAO);
			initialize();
			//adding placeholder to the textFields and password fields
			placeholderHandler.addPlaceholder(txtUsername, "Enter username here");
			placeholderHandler.addPlaceholder(txtPassword, "Enter password here");
			
			placeholderHandler.addPlaceholder(txtNewName, "Enter name here");
			placeholderHandler.addPlaceholder(txtNewUsername, "Enter username here");
			placeholderHandler.addPlaceholder(txtNewPassword, "Enter password here");
		}	
				
	    public void initialize() {
	        frame = new JFrame(); // JFrame - a GUI window to add component to
	        frame.setSize(740, 420); // this set the x and y dimension of Frame
	        frame.setTitle("wms"); // set the title if Frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
	        frame.setResizable(false);       
	        frame.getContentPane().setLayout(null);	   
	        //frame.setUndecorated(true);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true); // this make Frame visible
	       
	        bodyPanel = new JPanel();
	        bodyPanel.setToolTipText("");
	        bodyPanel.setBounds(263, 1, 461, 380); // this set the x and y dimension of Frame
	        bodyPanel.setVisible(true);
	        // authenticatePanel.setBackground(new Color(177, 202, 197));
	        // authenticatePanel.setBackground(new Color(20, 229, 235));
	        bodyPanel.setBackground(Color.WHITE);

	        JPanel infoPanel = new JPanel();
	        infoPanel.setBackground(new Color(3, 65, 68));
	        infoPanel.setForeground(new Color(255, 255, 255));
	        infoPanel.setBounds(0, 1, 264, 380);
	        infoPanel.setLayout(null);
	        frame.getContentPane().add(infoPanel);
	        
	        JTextArea txtrWarehouse = new JTextArea();
	        txtrWarehouse.setEditable(false);
	        txtrWarehouse.setForeground(new Color(255, 255, 255));
	        txtrWarehouse.setBackground(new Color(3, 65, 68));
	        txtrWarehouse.setFont(new Font("Arial", Font.BOLD, 23));
	        txtrWarehouse.setText("Warehouse\r\nManagement\r\nSystem");
	        txtrWarehouse.setBounds(43, 132, 158, 85);
	        infoPanel.add(txtrWarehouse);

	        ImageIcon img = new ImageIcon("logo.png"); // create an image
	        this.setIconImage(img.getImage());// change icon of Frame
	        this.getContentPane().setBackground(new Color(3, 65, 68));
	        frame.getContentPane().add(bodyPanel);
	        bodyPanel.setLayout(new CardLayout(0, 0));
	        
	        signinPanel = new JPanel();
	        bodyPanel.add(signinPanel, "name_2438863606500");
	        signinPanel.setLayout(null);
	        
	        lblSignUp = new JLabel("Sign Up");
	        new HoverEffect(lblSignUp, lblHoverColor, lblOriginalColor);
	        new HoverEffect(lblSignUp,new MatteBorder(0, 0, 2, 0, (Color) new Color(200, 200, 200)),new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 255)));
	        lblSignUp.setOpaque(true);
	        lblSignUp.setBackground(lblOriginalColor);
	        lblSignUp.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		showForm(signupPanel);
	        	}
	        });
	        lblSignUp.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	        lblSignUp.setForeground(Color.BLUE);
	        lblSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	        lblSignUp.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(200, 200, 200)));
	        lblSignUp.setBounds(121, 345, 54, 24);
	        signinPanel.add(lblSignUp);
	        
	        lblHaveAccount = new JLabel("Don't have account");
	        lblHaveAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        lblHaveAccount.setBounds(10, 345, 116, 24);
	        signinPanel.add(lblHaveAccount);
	        
	        btnClear = new JButton();
	        btnClear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		txtUsername.setText("");
	        		txtPassword.setText("");
	        		txtUsername.requestFocus();
	        	}
	        });
	        new HoverEffect(btnClear,btnHoverColor,btnOriginalColor);
	        btnClear.setText("Clear");
	        btnClear.setForeground(Color.WHITE);
	        btnClear.setFont(mainFont);
	        btnClear.setFocusPainted(false);
	        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnClear.setBackground(new Color(3, 65, 68));
	        btnClear.setBounds(148, 220, 65, 38);
	        signinPanel.add(btnClear);
	        
	        btnLogin = new JButton();
	        btnLogin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 performLogin();
	        	}
	        });
	        btnLogin.setText("Login");
	        new HoverEffect(btnLogin,btnHoverColor,btnOriginalColor);
	        btnLogin.setForeground(Color.WHITE);
	        btnLogin.setFont(mainFont);
	        btnLogin.setFocusPainted(false);
	        btnLogin.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnLogin.setBackground(new Color(3, 65, 68));
	        btnLogin.setBounds(223, 220, 162, 38);
	        signinPanel.add(btnLogin);
	        
	        txtPassword = new JPasswordField();
	        txtPassword.setBounds(77, 166, 310, 32);
	        signinPanel.add(txtPassword);
	        
	        txtUsername = new JTextField(20);
	        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtUsername.setBounds(77, 120, 310, 32);
	        signinPanel.add(txtUsername);
	        
	        lblLoginTitle = new JLabel("Login");
	        lblLoginTitle.setForeground(new Color(3, 65, 68));
	        lblLoginTitle.setFont(new Font("Arial", Font.BOLD, 22));
	        lblLoginTitle.setBackground(new Color(3, 65, 68));
	        lblLoginTitle.setBounds(21, 14, 72, 24);
	        signinPanel.add(lblLoginTitle);
	        
	        signupPanel = new JPanel();
	        bodyPanel.add(signupPanel);
	        signupPanel.setLayout(null);
	        
	        lblSignIn = new JLabel("Sign In");
	        lblSignIn.setOpaque(true);
	        new HoverEffect(lblSignIn,new MatteBorder(0, 0, 2, 0, (Color) new Color(200, 200, 200)),new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 255)));
	        new HoverEffect(lblSignIn, lblHoverColor, lblOriginalColor);
	        lblSignIn.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		showForm(signinPanel);
	        	}
	        });
	        lblSignIn.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
	        lblSignIn.setForeground(Color.BLUE);
	        lblSignIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	        lblSignIn.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(200, 200, 200)));
	        lblSignIn.setBounds(102, 345, 54, 24);
	        signupPanel.add(lblSignIn);
	        
	        lblIHaveAccount = new JLabel("I have account");
	        lblIHaveAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        lblIHaveAccount.setBounds(10, 345, 90, 24);
	        signupPanel.add(lblIHaveAccount);
	        
	        btnClear1 = new JButton();
	        btnClear1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		txtNewName.setText("");
	        		txtNewUsername.setText("");
	        		txtNewPassword.setText("");
	        		txtNewName.requestFocus();
	        	}
	        });
	        new HoverEffect(btnClear1,btnHoverColor,btnOriginalColor);
	        btnClear1.setText("Clear");
	        btnClear1.setForeground(Color.WHITE);
	        btnClear1.setFont(mainFont);
	        btnClear1.setFocusPainted(false);
	        btnClear1.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnClear1.setBackground(new Color(3, 65, 68));
	        btnClear1.setBounds(153, 257, 65, 38);
	        signupPanel.add(btnClear1);
	        
	        btnSave = new JButton();
	        new HoverEffect(btnSave,btnHoverColor,btnOriginalColor);
	        btnSave.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	    				String name,username,password,role;
	    				
	    				name = txtNewName.getText();
	    				username=txtNewUsername.getText();	
	    				password = new String(txtNewPassword.getPassword());
	    				role = newRolecomboBox.getSelectedItem().toString();			
	    				
	    				// Use AccountService to validate credentials and get account details
	    	            Account account = accountService.getAccountByName(name);	            
	    	          //To validate if account already exist
	    	            if (account != null && account.getUsername().equals(username) && account.getPassword().equals(password)){
	    	            	//To validate if account name already exist
	    	            	JOptionPane.showMessageDialog(null, "Name already used by other account");            	              	
	    	            //if not continue account creation    
	    	            } else {
	    	            	//check if inputted field are/is empty
	    	            	if (name.isEmpty() && username.isEmpty() && password.isEmpty()) {
	    						JOptionPane.showMessageDialog(null, "Missing Information");
	    					}else {	    						
	    							//check if account created is an admin role
	    							if (role.equalsIgnoreCase("Admin")) {
	    								var masterpassword = javax.swing.JOptionPane.showInputDialog("Enter masterpassword for Admin type");							
	    								//to check if inputed masterpassword for admin creation is correct
	    								if(masterpassword.equals("password")) {
	    									
	    						            Account adminAccount = new Account(name,username,password,role);
	    									accountService.addAccount(adminAccount);									
	    		
	    									frame.dispose();
	    									new LoginForm();
	    								//check is masterpassword is empty									
	    								}else if(masterpassword.isEmpty()) {
	    									//JOptionPane.showMessageDialog(null, "Sorry wrong password", "alert", JOptionPane.ERROR_MESSAGE);
	    									JOptionPane.showMessageDialog(null, "Missing Information");
	    								//check is masterpassword is incorrect
	    								}else if(!masterpassword.equals("password")) {				
	    									//JOptionPane.showMessageDialog(null, "Congratulation", "information", JOptionPane.INFORMATION_MESSAGE);					
	    									JOptionPane.showMessageDialog(null, "Wrong Password");
	    								}
	    							//check if account created is a guest role
	    							}else if(role.equalsIgnoreCase("Guest")) {											         
	    										Account guestAccount = new Account(name,username,password,role);
	    										accountService.addAccount(guestAccount);										
	    										
	    										frame.dispose();
	    										new LoginForm();
	    							}else {
	    								JOptionPane.showMessageDialog(null, "Please select valid role type");
	    							}				
	    					}
	    	            }																			
	    			}
	        });
	        btnSave.setText("Save");
	        btnSave.setForeground(Color.WHITE);
	        btnSave.setFont(mainFont);
	        btnSave.setFocusPainted(false);
	        btnSave.setBorder(BorderFactory.createRaisedBevelBorder());
	        btnSave.setBackground(new Color(3, 65, 68));
	        btnSave.setBounds(228, 257, 162, 38);
	        signupPanel.add(btnSave);
	        
	        txtNewPassword = new JPasswordField();
	        txtNewPassword.setBounds(80, 172, 310, 32);
	        signupPanel.add(txtNewPassword);
	        
	        txtNewUsername = new JTextField(20);
	        txtNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtNewUsername.setBounds(80, 126, 310, 32);
	        signupPanel.add(txtNewUsername);
	        
	        lblSignUpTitle = new JLabel("Sign Up");
	        lblSignUpTitle.setForeground(new Color(3, 65, 68));
	        lblSignUpTitle.setFont(new Font("Arial", Font.BOLD, 22));
	        lblSignUpTitle.setBackground(new Color(3, 65, 68));
	        lblSignUpTitle.setBounds(21, 14, 90, 24);
	        signupPanel.add(lblSignUpTitle);
	        
	        txtNewName = new JTextField(20);
	        txtNewName.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtNewName.setBounds(80, 83, 310, 32);
	        signupPanel.add(txtNewName);
	        
	        String[] list = {"Choose role type","Guest","Admin"};
	        newRolecomboBox = new JComboBox(list);
	        newRolecomboBox.setFont(mainFont);		
	        newRolecomboBox.setBounds(80, 215, 310, 32);
	        signupPanel.add(newRolecomboBox);
	        
	        
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
	    private void performLogin() {
	        String username = txtUsername.getText();
	        String password = new String(txtPassword.getPassword());

	        if (username.isEmpty() || password.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Missing Information");
	        } else {
	        	
	            // Use AccountService to validate credentials and get account details
	            Account account = accountService.getAccountByUsernameAndPassword(username, password);
	            

	            if (account != null && password.equals(account.getPassword()) && username.equals(account.getUsername())) {
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
	  
	}
