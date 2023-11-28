package com.lanuza.wms.ui.form;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame{

		private static JButton clearButton, loginButton,AccountButton;
	    static JTextField usernameText;
	    static JPasswordField passwordText;
	    static JPanel authenticatePanel;
	    private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	    private JFrame frame;

		public Login() {
			initialize();

		}
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		

	    public void initialize() {


	        frame = new JFrame(); // JFrame - a GUI window to add component to
	        frame.setSize(710, 370); // this set the x and y dimension of Frame
	        frame.setTitle("PhilDrinks"); // set the title if Frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
	        frame.setResizable(false);       
	        frame.getContentPane().setLayout(null);	   
	        //frame.setUndecorated(true);
	       

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

	        JLabel infoLabel = new JLabel("PhilDrinks Warehouse");
	        infoLabel.setBounds(28, 63, 209, 24);
	        infoLabel.setForeground(new Color(0, 0, 0));
	        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));

	        JLabel loginLabel = new JLabel("Login");
	        loginLabel.setBounds(10, 36, 72, 24);
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
	        
	        AccountButton = new JButton();
	        AccountButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {	 
	        	}
	        });
	        AccountButton.setBounds(226, 242, 98, 30);
	        AccountButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        AccountButton.setText("Create Account?");
	        AccountButton.setBackground(new Color(255, 255, 255));
	        AccountButton.setBorder(null);    

	        usernameText = new JTextField(20);
	        usernameText.setBounds(141, 118, 237, 32);
	        // user = usernameText.getText();
	        passwordText = new JPasswordField();
	        passwordText.setBounds(141, 164, 237, 32);
	        // pass = usernameText.getText();

	        clearButton = new JButton();
	        clearButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		usernameText.setText("");
		            passwordText.setText("");        		
	        	}
	        });
	        clearButton.setBounds(141, 207, 65, 30);
	        clearButton.setFont(mainFont);
	        clearButton.setText("Clear");
	        clearButton.setBackground(new Color(33, 167, 213));
	        clearButton.setBorder(BorderFactory.createRaisedBevelBorder());	           

	        loginButton = new JButton();
	        loginButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
	        			JOptionPane.showMessageDialog(null, "Missing Information");
	        		
	        	}else {
	        		String Query = "Select * from phildrinksdb.tblaccount where Username ='" +usernameText.getText()+"'and Password ='"+passwordText.getText()+"'";
	        		try {
	        			Class.forName("com.mysql.cj.jdbc.Driver");
	        			
	    				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
	    				st = con.createStatement();
						rs = st.executeQuery(Query);
						
						if(rs.next()) {
							frame.dispose();
							Dashboard login = new Dashboard();
						}else {
							JOptionPane.showMessageDialog(null, "Wrong username or password");
						}
	        		}catch(Exception exc) {
	        			//exc.printStackTrace();
	        		}
	        	}
	        	}
	        });
	        loginButton.setBounds(216, 207, 162, 30);
	        loginButton.setFont(mainFont);
	        loginButton.setText("Login");
	        loginButton.setBackground(new Color(33, 167, 213));
	        loginButton.setBorder(BorderFactory.createRaisedBevelBorder());

	        ImageIcon img = new ImageIcon("logo.png"); // create an image
	        this.setIconImage(img.getImage());// change icon of Frame
	        this.getContentPane().setBackground(new Color(3, 65, 68));
	        authenticatePanel.setLayout(null);

	        authenticatePanel.add(clearButton);
	        authenticatePanel.add(loginButton);
	        authenticatePanel.add(loginLabel);
	        authenticatePanel.add(userLabel);
	        authenticatePanel.add(passwordLabel);
	        authenticatePanel.add(AccountButton);
	        authenticatePanel.add(usernameText);
	        authenticatePanel.add(passwordText);
	        infoPanel.add(infoLabel);
	        frame.getContentPane().add(authenticatePanel);
	        
	        JButton btnSignUp = new JButton();
	        btnSignUp.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		frame.dispose();
	        		NewAccount registerAccount = new NewAccount(); 
	        	}
	        });
	        
	        btnSignUp.setForeground(new Color(0, 0, 255));
	        btnSignUp.setText("Sign Up");
	        btnSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	        btnSignUp.setBorder(null);
	        btnSignUp.setBackground(Color.WHITE);
	        btnSignUp.setBounds(324, 242, 54, 30);
	        authenticatePanel.add(btnSignUp);
	        frame.getContentPane().add(infoPanel);
	        
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true); // this make Frame visible
	    }
	}
