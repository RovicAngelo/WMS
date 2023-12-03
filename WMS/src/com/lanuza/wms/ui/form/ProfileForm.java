package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.model.Account;
import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.ProductDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.ProductService;
import com.lanuza.wms.service.impl.AccountServiceImpl;

import net.proteanit.sql.DbUtils;

public class ProfileForm {
	private final AccountDAO accountDAO;
	private final AccountService accountService;
	private JFrame frame;
	private JTextField txtCurrentUsername,txtCurrentPassword,txtCurrentId;
	JComboBox currentRoleCombox, newRoleCombox;
	JPanel modifyPanel;
	int id;
	String name,username,password,role;
	
	

	public ProfileForm() {
		this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);
		initialize();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtNewName;
	private JTextField txtNewUsername;
	private JPasswordField txtNewPassword;
	private JPasswordField txtNewRepassword;
	private JTextField txtCurrentName;
	
	public void updateProfile(Account account) {
        // Update the UI components with the account details
        txtCurrentId.setText(String.valueOf(account.getAccountId()));
        txtCurrentName.setText(account.getName());
        txtCurrentUsername.setText(account.getUsername());
        txtCurrentPassword.setText(account.getPassword());
        currentRoleCombox.setSelectedItem(account.getRole());
        
        txtNewName.setText(account.getName());
        txtNewUsername.setText(account.getUsername());
        txtNewPassword.setText(account.getPassword());
        newRoleCombox.setSelectedItem(account.getRole());       
    }
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(31, 111, 293, 284);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentId = new JLabel("Account ID");
		lblCurrentId.setBounds(10, 33, 81, 33);
		panel.add(lblCurrentId);
		lblCurrentId.setForeground(Color.BLACK);
		lblCurrentId.setFont(new Font("Tahoma", Font.BOLD, 15));
				
		JLabel lblModifyAccount = new JLabel("Modify Account?");
		lblModifyAccount.setForeground(new Color(0, 0, 255));
		lblModifyAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModifyAccount.setBounds(170, 243, 104, 30);
		panel.add(lblModifyAccount);
		
		JLabel lblCurrentName = new JLabel("Name");
		lblCurrentName.setForeground(Color.BLACK);
		lblCurrentName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentName.setBounds(25, 70, 46, 33);
		panel.add(lblCurrentName);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 1, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Manage Account");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(525, 25, 166, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		modifyPanel = new JPanel();
		modifyPanel.setVisible(false);
		modifyPanel.setBackground(new Color(230, 230, 230));
		modifyPanel.setLayout(null);
		modifyPanel.setBounds(334, 90, 559, 429);
		frame.getContentPane().add(modifyPanel);
		
		JLabel lblCurrentUsername = new JLabel("Username");
		lblCurrentUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUsername.setForeground(new Color(0, 0, 0));
		lblCurrentUsername.setBounds(10, 110, 79, 33);
		panel.add(lblCurrentUsername);
		
		JLabel lblCurrentPassword = new JLabel("Password");
		lblCurrentPassword.setForeground(Color.BLACK);
		lblCurrentPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentPassword.setBounds(10, 154, 79, 33);
		panel.add(lblCurrentPassword);
		
		JLabel lblCurrentRole = new JLabel("Role");
		lblCurrentRole.setForeground(Color.BLACK);
		lblCurrentRole.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentRole.setBounds(25, 203, 46, 33);
		panel.add(lblCurrentRole);
		
		JLabel lblNewUsername = new JLabel("Username");
		lblNewUsername.setForeground(Color.BLACK);
		lblNewUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewUsername.setBounds(113, 128, 85, 31);
		modifyPanel.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("Password");
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewPassword.setBounds(113, 171, 85, 31);
		modifyPanel.add(lblNewPassword);
		
		JLabel lblNewRole = new JLabel("Role");
		lblNewRole.setForeground(Color.BLACK);
		lblNewRole.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewRole.setBounds(130, 270, 48, 31);
		modifyPanel.add(lblNewRole);
		
		JLabel lblNewRepassword = new JLabel("re-Password");
		lblNewRepassword.setForeground(Color.BLACK);
		lblNewRepassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewRepassword.setBounds(100, 216, 98, 31);
		modifyPanel.add(lblNewRepassword);
		
		JLabel lblNewName = new JLabel("Name");
		lblNewName.setForeground(Color.BLACK);
		lblNewName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewName.setBounds(130, 86, 54, 31);
		modifyPanel.add(lblNewName);
		
		JLabel lblInput = new JLabel("Input new account informations");
		lblInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInput.setForeground(new Color(0, 0, 0));
		lblInput.setBounds(170, 25, 232, 31);
		modifyPanel.add(lblInput);
				
		txtCurrentUsername = new JTextField();
		txtCurrentUsername.setEditable(false);
		txtCurrentUsername.setBounds(99, 114, 175, 29);
		panel.add(txtCurrentUsername);
		txtCurrentUsername.setColumns(10);
		
		txtCurrentPassword = new JTextField();
		txtCurrentPassword.setEditable(false);
		txtCurrentPassword.setColumns(10);
		txtCurrentPassword.setBounds(99, 154, 175, 29);
		panel.add(txtCurrentPassword);
		
		txtCurrentId = new JTextField();
		txtCurrentId.setEditable(false);
		txtCurrentId.setBounds(99, 37, 174, 29);
		panel.add(txtCurrentId);
		txtCurrentId.setColumns(10);
			
		txtCurrentName = new JTextField();
		txtCurrentName.setEditable(false);
		txtCurrentName.setColumns(10);
		txtCurrentName.setBounds(100, 74, 174, 29);
		panel.add(txtCurrentName);		
		
		txtNewName = new JTextField();
		txtNewName.setToolTipText("Juan Dela Cruz");
		txtNewName.setColumns(10);
		txtNewName.setBounds(222, 88, 222, 31);
		modifyPanel.add(txtNewName);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setToolTipText("");
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(222, 130, 222, 31);
		modifyPanel.add(txtNewUsername);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setToolTipText("");
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(222, 173, 222, 31);
		modifyPanel.add(txtNewPassword);
		
		txtNewRepassword = new JPasswordField();
		txtNewRepassword.setToolTipText("");
		txtNewRepassword.setColumns(10);
		txtNewRepassword.setBounds(222, 216, 222, 31);
		modifyPanel.add(txtNewRepassword);
		
		newRoleCombox = new JComboBox(new Object[]{});
		newRoleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		newRoleCombox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Guest"}));
		newRoleCombox.setBounds(222, 270, 222, 31);
		modifyPanel.add(newRoleCombox);
		
		currentRoleCombox = new JComboBox();
		currentRoleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentRoleCombox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Guest"}));
		currentRoleCombox.setBounds(99, 206, 175, 26);
		panel.add(currentRoleCombox);
		
		lblModifyAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyPanel.setVisible(true);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				frame.dispose();
				new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(21, 490, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			modifyPanel.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(20, 387, 98, 31);
		modifyPanel.add(btnCancel);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(355, 321, 89, 29);
		modifyPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNewName.setText("");
				txtNewUsername.setText("");
				txtNewPassword.setText("");
				txtNewRepassword.setText("");
				newRoleCombox.setSelectedItem("");
				txtNewName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(256, 321, 89, 29);
		modifyPanel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newName,newUsername,newPassword,newrole;
				int accid;
				
				newName = txtNewName.getText();
				newUsername = txtNewUsername.getText();
				newPassword = txtNewPassword.getText();
				newrole = newRoleCombox.getSelectedItem().toString();
				accid = Integer.parseInt( txtCurrentId.getText());
				
				Account updateAccount = new Account(accid,newName,newUsername,newPassword,newrole);
				accountService.updateAccount(updateAccount);			

				updateProfile(updateAccount);
				
				txtNewName.setText("");
				txtNewUsername.setText("");
				txtNewPassword.setText("");
				txtNewRepassword.setText("");
				newRoleCombox.setSelectedItem("");
				txtNewName.requestFocus();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));		
	}
}
