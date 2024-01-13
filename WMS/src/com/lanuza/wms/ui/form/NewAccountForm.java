package com.lanuza.wms.ui.form;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;

public class NewAccountForm  {
	private final AccountDAO accountDAO;
	private final AccountService accountService;
	private JFrame frame;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtRepassword;

	public NewAccountForm() {
		this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Registration", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(202, 103, 559, 429);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewAccount = new JLabel("New Account");
		lblNewAccount.setForeground(Color.WHITE);
		lblNewAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewAccount.setBounds(416, 25, 138, 30);
		topPanel.add(lblNewAccount);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(113, 128, 85, 31);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(113, 171, 85, 31);
		panel.add(lblPassword);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(Color.BLACK);
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRole.setBounds(130, 270, 48, 31);
		panel.add(lblRole);
		
		JLabel lblRepassword = new JLabel("re-Password");
		lblRepassword.setForeground(Color.BLACK);
		lblRepassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRepassword.setBounds(100, 216, 98, 31);
		panel.add(lblRepassword);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);				
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(130, 86, 54, 31);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setToolTipText("Juan Dela Cruz");
		txtName.setColumns(10);
		txtName.setBounds(222, 88, 222, 31);
		panel.add(txtName);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setColumns(10);
		txtUsername.setBounds(222, 130, 222, 31);
		panel.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("");
		txtPassword.setColumns(10);
		txtPassword.setBounds(222, 173, 222, 31);
		panel.add(txtPassword);
		
		txtRepassword = new JPasswordField();
		txtRepassword.setToolTipText("");
		txtRepassword.setColumns(10);
		txtRepassword.setBounds(222, 216, 222, 31);
		panel.add(txtRepassword);
	 
		String[] list = {"Guest","Admin"};
		JComboBox<?> roleCombox = new JComboBox(list);
		roleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));		
		roleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleCombox.setBounds(222, 270, 222, 31);
		panel.add(roleCombox);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {	
				String name,username,password,repassword,role;
				
				name = txtName.getText();
				username=txtUsername.getText();
				password=txtPassword.getText();
				repassword = txtRepassword.getText();			
				role = roleCombox.getSelectedItem().toString();
				
				if (name.isEmpty() && username.isEmpty() && password.isEmpty() && repassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}else {
					if(password.equals(repassword)){
						
						if (role.equalsIgnoreCase("Admin")) {
							var masterpassword = javax.swing.JOptionPane.showInputDialog("Enter masterpassword for Admin type");							
							
							if(masterpassword.equals("password")) {
								Account adminAccount = new Account(name,username,password,role);
								accountService.addAccount(adminAccount);
								
								JOptionPane.showMessageDialog(null, "Successfully creates an admin account");
	
								frame.dispose();
								new LoginForm();
								
							}else if(masterpassword.isEmpty()) {
								//JOptionPane.showMessageDialog(null, "Sorry wrong password", "alert", JOptionPane.ERROR_MESSAGE);
								JOptionPane.showMessageDialog(null, "Missing Information");
								
							}else if(masterpassword != "password") {				
								//JOptionPane.showMessageDialog(null, "Congratulation", "information", JOptionPane.INFORMATION_MESSAGE);					
								JOptionPane.showMessageDialog(null, "Wrong Password");
							}
						}else if(role.equalsIgnoreCase("Guest")) {					
							
							Account guestAccount = new Account(name,username,password,role);
							accountService.addAccount(guestAccount);
							
							JOptionPane.showMessageDialog(null, "Successfully created a guest account");
							
							frame.dispose();
							new LoginForm();
						}	
					}else if(txtPassword.getText() != txtRepassword.getText()){
						JOptionPane.showMessageDialog(null, "Password and re-password are not the same");
						
					}					
				}											
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignIn.setBounds(346, 335, 98, 31);
		panel.add(btnSignIn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new LoginForm();
			}
		});
		
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(238, 335, 98, 31);
		panel.add(btnCancel);							
		
	}
}
