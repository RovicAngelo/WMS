package com.lanuza.form;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

public class NewAccount  {

	private JFrame frame;
	private JTextField nametxt;
	private JTextField usernametxt;
	private JTextField passwordtxt;
	private JTextField repasswordtxt;

	public NewAccount() {
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
		
		JLabel lblNewAccount = new JLabel("New Account");
		lblNewAccount.setForeground(Color.WHITE);
		lblNewAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewAccount.setBounds(416, 25, 138, 30);
		topPanel.add(lblNewAccount);
		
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(130, 86, 54, 31);
		panel.add(lblName);
		
		nametxt = new JTextField();
		nametxt.setToolTipText("Juan Dela Cruz");
		nametxt.setColumns(10);
		nametxt.setBounds(222, 88, 222, 31);
		panel.add(nametxt);
	
		String[] list = {"Guest","Admin"};
		JComboBox typeCombox = new JComboBox(list);
		typeCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		//typeCombox.setModel(new DefaultComboBoxModel(new String[] {"Guest","Admin"}));		
		//JComboBox typeCombox = new JComboBox(list);
		//typeCombox.setEditable(true);
		//JOptionPane.showMessageDialog( null, typeCombox, "select or type a value", JOptionPane.QUESTION_MESSAGE);
		//String verify = typeCombox.getSelectedItem().toString();		
		typeCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		typeCombox.setBounds(222, 270, 222, 31);
		panel.add(typeCombox);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if (nametxt.getText().isEmpty() || usernametxt.getText().isEmpty() || passwordtxt.getText().isEmpty() || repasswordtxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
				}else {
					if(passwordtxt.getText().equals(repasswordtxt.getText())){
						String verify = String.valueOf(typeCombox.getSelectedItem());
						if (verify.equalsIgnoreCase("Admin")) {
							var masterpassword = javax.swing.JOptionPane.showInputDialog("Enter masterpassword for Admin type");
							System.out.println(masterpassword);
							if(masterpassword.equals("Hello")) {
								JOptionPane.showMessageDialog(null, "Successfully Login");
							}else if(masterpassword.isEmpty()) {
								//JOptionPane.showMessageDialog(null, "Sorry wrong password", "alert", JOptionPane.ERROR_MESSAGE);
								JOptionPane.showMessageDialog(null, "Missing Information");
							}else if(masterpassword != "Hello") {				
								//JOptionPane.showMessageDialog(null, "Congratulation", "information", JOptionPane.INFORMATION_MESSAGE);					
								JOptionPane.showMessageDialog(null, "Wrong Password");
							}
						}else if(verify.equalsIgnoreCase("Guest")) {
							JOptionPane.showMessageDialog(null, "Successfully Login");
						}	
					}else if(passwordtxt.getText() != repasswordtxt.getText()){
						JOptionPane.showMessageDialog(null, "Password and re-password are not the same");
						
					}
					
				}			
				
				
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignUp.setBounds(346, 335, 98, 31);
		panel.add(btnSignUp);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login newLogin = new Login();
			}
		});
		
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(238, 335, 98, 31);
		panel.add(btnCancel);
		
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
		
		usernametxt = new JTextField();
		usernametxt.setToolTipText("");
		usernametxt.setColumns(10);
		usernametxt.setBounds(222, 130, 222, 31);
		panel.add(usernametxt);
		
		passwordtxt = new JPasswordField();
		passwordtxt.setToolTipText("");
		passwordtxt.setColumns(10);
		passwordtxt.setBounds(222, 173, 222, 31);
		panel.add(passwordtxt);
		
		repasswordtxt = new JPasswordField();
		repasswordtxt.setToolTipText("");
		repasswordtxt.setColumns(10);
		repasswordtxt.setBounds(222, 216, 222, 31);
		panel.add(repasswordtxt);
		
		JLabel lblName_3_1 = new JLabel("Type");
		lblName_3_1.setForeground(Color.BLACK);
		lblName_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName_3_1.setBounds(130, 270, 48, 31);
		panel.add(lblName_3_1);
		
		JLabel lblRepassword = new JLabel("re-Password");
		lblRepassword.setForeground(Color.BLACK);
		lblRepassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRepassword.setBounds(100, 216, 98, 31);
		panel.add(lblRepassword);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
