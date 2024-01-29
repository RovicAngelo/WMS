package com.lanuza.wms.ui.form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;
import com.lanuza.wms.ui.components.HoverEffect;
import com.lanuza.wms.ui.components.PlaceholderHandler;

import java.awt.Component;
import javax.swing.border.EmptyBorder;

public class ManageProfileForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO;
	private final AccountService accountService;
	
	private final Font mainFont = new Font("Tahoma", Font.BOLD, 12);
	
	PlaceholderHandler placeholderHandler = new PlaceholderHandler(mainFont);
	 
	private JPanel bodyPanel;
	private JTextField txtNewName,txtNewUsername;
	private JPasswordField txtNewPassword,txtVerifyPassword;
	private JComboBox<String> newRoleCombox;
	private JLabel lbltextCurrentRole,lbltextCurrentName,lbltextCurrentUsername,lbltextAccountId,lblDisplayName,lblDisplayRole;
	private JLabel accountInfoMenu,modifyAccountMenu;
	
	private Color btnOriginalColor = new Color(243, 243, 243);
	private Color btnHoverColor = new Color(220, 220, 220);

	public ManageProfileForm(int id, boolean initializeUI) {
		this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);			  
    	
		if (initializeUI) {				         
			//to get the ID in loginForm
	        Account account = accountService.getAccountById(id);
	        
	        String name = account.getName();
            String user = account.getUsername();
            //String pass = account.getPassword();
            String role = account.getRole();
            
            lblDisplayName = new JLabel();
            lblDisplayRole = new JLabel();            
            
            lbltextAccountId = new JLabel();
			lbltextCurrentName = new JLabel();
			lbltextCurrentUsername = new JLabel();
			lbltextCurrentRole = new JLabel();
			 
	    	
	    	txtNewName = new JTextField();    	    	
	    	txtNewUsername = new JTextField();	    		    	
	    	newRoleCombox = new JComboBox<String>();
	    	txtNewPassword = new JPasswordField();
	    	txtVerifyPassword = new JPasswordField();
	    	
	    	placeholderHandler.addPlaceholder(txtNewUsername, "Enter new username here");
	    	placeholderHandler.addPlaceholder(txtNewName, "Enter new name here");	    	
	    	placeholderHandler.addPlaceholder(txtNewPassword, "Enter new password here");
			placeholderHandler.addPlaceholder(txtVerifyPassword, "Re-enter new password here ");

			lblDisplayName.setText(name != null ? name : "null field");
			lblDisplayRole.setText(role != null ? role : "null field");

	    	
	    	lbltextAccountId.setText(String.valueOf(account.getAccountId()));
	    	lbltextCurrentName.setText(name != null ? name : "null field");  
	    	lbltextCurrentUsername.setText(user != null ? user : "null field");
	    	lbltextCurrentRole.setText(role != null ? role : "null field");

			/*
			 * txtNewName.setText(name != null ? name : "null field");
			 * txtNewUsername.setText(user != null ? user : "null field");
			 * newRoleCombox.setSelectedItem(role != null ? role : "null field");
			 */          
            initialize();    
            
		}    
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize(){				
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 11, 118, 114);	
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		
		lblDisplayName.setBounds(167, 11, 219, 29);	
		lblDisplayName.setForeground(Color.BLACK);
		lblDisplayName.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblDisplayName);
			
		lblDisplayRole.setBounds(164, 40, 46, 13);		
		lblDisplayRole.setForeground(Color.GRAY);
		lblDisplayRole.setFont(new Font("Arial", Font.BOLD, 11));
		add(lblDisplayRole);
		
		accountInfoMenu = new JLabel("Account Information");		
		accountInfoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		accountInfoMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		accountInfoMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		accountInfoMenu.setFont(new Font("Arial", Font.BOLD, 12));
		accountInfoMenu.setBounds(37, 155, 128, 29);
		add(accountInfoMenu);
		
		modifyAccountMenu = new JLabel("Modify Account");		
		modifyAccountMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		modifyAccountMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		modifyAccountMenu.setHorizontalAlignment(SwingConstants.CENTER);
		modifyAccountMenu.setFont(new Font("Arial", Font.BOLD, 12));
		modifyAccountMenu.setBounds(185, 155, 118, 29);
		add(modifyAccountMenu);							
		
		bodyPanel = new JPanel();
		bodyPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		bodyPanel.setBackground(new Color(235, 235, 235));
		bodyPanel.setBounds(22, 189, 1050, 360);
		add(bodyPanel);
		bodyPanel.setLayout(new CardLayout(0, 0));
		
		JPanel accountInfoPanel = new JPanel();
		accountInfoPanel.setBounds(0, 0, 1048, 358);
		bodyPanel.add(accountInfoPanel);
		accountInfoPanel.setLayout(null);
		
		JLabel lblAccInfoTitle = new JLabel("Account Information");
		lblAccInfoTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAccInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccInfoTitle.setBounds(8, 0, 145, 29);
		accountInfoPanel.add(lblAccInfoTitle);
		
		JLabel lblCurrentName = new JLabel("Name");
		lblCurrentName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentName.setForeground(Color.GRAY);
		lblCurrentName.setFont(mainFont);
		lblCurrentName.setBounds(94, 131, 41, 19);
		accountInfoPanel.add(lblCurrentName);
		
		JLabel lblCurrentUsername = new JLabel("Username");
		lblCurrentUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setForeground(Color.GRAY);
		lblCurrentUsername.setFont(mainFont);
		lblCurrentUsername.setBounds(94, 195, 59, 19);
		accountInfoPanel.add(lblCurrentUsername);
		
		JLabel lblCurrentRole = new JLabel("Role");
		lblCurrentRole.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentRole.setForeground(Color.GRAY);
		lblCurrentRole.setFont(mainFont);
		lblCurrentRole.setBounds(94, 259, 33, 19);
		accountInfoPanel.add(lblCurrentRole);
		
		//lbltextCurrentName = new JLabel();
		lbltextCurrentName.setBorder(new EmptyBorder(0, 10, 0, 0));
		lbltextCurrentName.setOpaque(true);
		lbltextCurrentName.setBackground(new Color(224, 224, 224));
		lbltextCurrentName.setFont(mainFont);
		lbltextCurrentName.setBounds(85, 150, 426, 34);
		accountInfoPanel.add(lbltextCurrentName);
		
		//lbltextCurrentUsername = new JLabel();
		lbltextCurrentUsername.setBorder(new EmptyBorder(0, 10, 0, 0));
		lbltextCurrentUsername.setOpaque(true);
		lbltextCurrentUsername.setBackground(new Color(224, 224, 224));
		lbltextCurrentUsername.setFont(mainFont);
		lbltextCurrentUsername.setBounds(85, 214, 426, 34);
		accountInfoPanel.add(lbltextCurrentUsername);
		
		//lbltextCurrentRole = new JLabel();
		lbltextCurrentRole.setBorder(new EmptyBorder(0, 10, 0, 0));
		lbltextCurrentRole.setOpaque(true);
		lbltextCurrentRole.setBackground(new Color(224, 224, 224));
		lbltextCurrentRole.setFont(mainFont);
		lbltextCurrentRole.setBounds(85, 280, 426, 34);
		accountInfoPanel.add(lbltextCurrentRole);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAccountId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountId.setForeground(Color.GRAY);
		lblAccountId.setFont(mainFont);
		lblAccountId.setBounds(94, 67, 69, 19);
		accountInfoPanel.add(lblAccountId);
		
		
		//lbltextAccountId = new JLabel();
		lbltextAccountId.setBorder(new EmptyBorder(0, 10, 0, 0));
		lbltextAccountId.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lbltextAccountId.setOpaque(true);
		lbltextAccountId.setBackground(new Color(224, 224, 224));
		lbltextAccountId.setFont(mainFont);
		lbltextAccountId.setBounds(85, 86, 426, 34);
		accountInfoPanel.add(lbltextAccountId);	
		
		JPanel modifyAccountPanel = new JPanel();
		modifyAccountPanel.setLayout(null);
		bodyPanel.add(modifyAccountPanel);
		
		JPanel avatarPanel = new JPanel();
		avatarPanel.setLayout(null);
		avatarPanel.setBackground(Color.WHITE);
		avatarPanel.setBounds(75, 82, 192, 155);
		modifyAccountPanel.add(avatarPanel);
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setOpaque(true);
		lblAvatar.setBackground(Color.WHITE);
		lblAvatar.setBounds(35, 29, 117, 115);
		avatarPanel.add(lblAvatar);
		
		JLabel lblChangeProfile = new JLabel("Change Profile");
		lblChangeProfile.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChangeProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeProfile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeProfile.setBounds(129, 238, 86, 27);
		modifyAccountPanel.add(lblChangeProfile);
		
		JLabel lblModAccTitle = new JLabel("Modify Account");
		lblModAccTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblModAccTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblModAccTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModAccTitle.setBounds(10, 0, 107, 29);
		modifyAccountPanel.add(lblModAccTitle);
		
		JLabel lblNewUsername = new JLabel("Username");
		lblNewUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUsername.setForeground(Color.GRAY);
		lblNewUsername.setFont(mainFont);
		lblNewUsername.setBounds(380, 94, 59, 19);
		modifyAccountPanel.add(lblNewUsername);
		
		JLabel lblNewRole = new JLabel("Role");
		lblNewRole.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRole.setForeground(Color.GRAY);
		lblNewRole.setFont(mainFont);
		lblNewRole.setBounds(380, 139, 59, 19);
		modifyAccountPanel.add(lblNewRole);
		
		txtNewName.setColumns(10);
		txtNewName.setBounds(472, 46, 286, 34);
		modifyAccountPanel.add(txtNewName);
		
		JLabel lblNewName = new JLabel("Name");
		lblNewName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewName.setForeground(Color.GRAY);
		lblNewName.setFont(mainFont);
		lblNewName.setBounds(380, 53, 59, 19);
		modifyAccountPanel.add(lblNewName);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setForeground(Color.GRAY);
		lblNewPassword.setFont(mainFont);
		lblNewPassword.setBounds(363, 217, 94, 19);
		modifyAccountPanel.add(lblNewPassword);
		
		JLabel lblVerifyPassword = new JLabel("Verify Password");
		lblVerifyPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVerifyPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerifyPassword.setForeground(Color.GRAY);
		lblVerifyPassword.setFont(mainFont);
		lblVerifyPassword.setBounds(351, 262, 106, 19);
		modifyAccountPanel.add(lblVerifyPassword);
		
		newRoleCombox.setBounds(472, 132, 286, 34);
		newRoleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		newRoleCombox.setModel(new DefaultComboBoxModel<String>(new String[] {"Choose role type","Admin", "Guest"}));
		modifyAccountPanel.add(newRoleCombox);
		
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(472, 87, 286, 34);
		modifyAccountPanel.add(txtNewUsername);
				
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(472, 210, 286, 34);
		modifyAccountPanel.add(txtNewPassword);
				
		txtVerifyPassword.setColumns(10);
		txtVerifyPassword.setBounds(472, 255, 286, 34);
		modifyAccountPanel.add(txtVerifyPassword);
		
		JButton btnClear = new JButton("Clear");
		new HoverEffect(btnClear,btnHoverColor,btnOriginalColor);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				txtNewName.setText("");
				txtNewUsername.setText("");				
				txtNewPassword.setText("");
				txtVerifyPassword.setText("");
				txtNewName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setToolTipText("Add");
		btnClear.setFocusPainted(false);
		btnClear.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setBackground(btnOriginalColor);
		btnClear.setBounds(851, 298, 83, 29);
		modifyAccountPanel.add(btnClear);
		
		JButton btnSave = new JButton("Save");
		new HoverEffect(btnSave,btnHoverColor,btnOriginalColor);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName,newUsername,newPassword,verifyPassword,newrole;
				int accid;
				
				newName = txtNewName.getText();
				newUsername = txtNewUsername.getText();
				newPassword = new String(txtNewPassword.getPassword());
				verifyPassword = new String(txtVerifyPassword.getPassword());
				newrole = newRoleCombox.getSelectedItem().toString();
				accid = Integer.parseInt( lbltextAccountId.getText());		
				
				//to get this current account by id
				Account account = accountService.getAccountById(accid);
				//get all account except current account
				List<Account> accounts = accountService.getAllAccountNameExcept(account.getName());
				
				 if (newName.equals("") || newUsername.equals("") || newrole.equals("") || newPassword.equals("") || verifyPassword.equals("")) {
					 JOptionPane.showMessageDialog(null, "Missing Information/s");
				 }else {
					//check if updated account is an admin role
						if (newrole.equalsIgnoreCase("Admin")) {
							
							var masterpassword = javax.swing.JOptionPane.showInputDialog("Enter masterpassword for Admin type");		
							
							//to check if inputed masterpassword for admin creation is correct
							if(masterpassword.equals("password")) {
								if(newPassword.equals(verifyPassword)) {
									 // Check if newName is equal to the current account name
						            // and continue with updateAccount if it's not in the accounts list
						            if (newName.equals(account.getName()) || !accounts.stream().anyMatch(acc -> newName.equals(acc.getName()))) {
										Account updateAccount = new Account(newName,newUsername,newPassword,newrole,accid);
										accountService.updateAccount(updateAccount);

										txtNewName.setText("");
										txtNewUsername.setText("");
										txtNewPassword.setText("");
										txtVerifyPassword.setText("");
										newRoleCombox.setSelectedItem("");
										txtNewName.requestFocus();
										
										JOptionPane.showMessageDialog(null, "please logout account to apply changes");
																								            				            		            						            
									}else {							
							            JOptionPane.showMessageDialog(null, "Name already used by other account");
							            }
								}else {
									JOptionPane.showMessageDialog(null, "New Password does not match with Verify Password");
								}
														
							}else if(masterpassword.isEmpty()) {
								JOptionPane.showMessageDialog(null, "Missing master password information");
								
							}else if(masterpassword != "password") {								
								JOptionPane.showMessageDialog(null, "Wrong Password");
							}
						}else if(newrole.equalsIgnoreCase("Guest")) {	
							if(newPassword.equals(verifyPassword)) {
								// Check if newName is equal to the current account name
						        // and continue with updateAccount if it's not in the accounts list
						        if (newName.equals(account.getName()) || !accounts.stream().anyMatch(acc -> newName.equals(acc.getName()))) {
									Account updateAccount = new Account(newName,newUsername,newPassword,newrole,accid);
									accountService.updateAccount(updateAccount);

									txtNewName.setText("");
									txtNewUsername.setText("");
									txtNewPassword.setText("");
									txtVerifyPassword.setText("");
									newRoleCombox.setSelectedItem("");
									txtNewName.requestFocus();
									
									JOptionPane.showMessageDialog(null, "please logout account to apply changes");
																														            				            		            						            
								}else{							
						            JOptionPane.showMessageDialog(null, "Name already used by other account");
						            }	
							}else {
								JOptionPane.showMessageDialog(null, "New Password does not match with Verify Password");
							}
						     													
						}else {
							JOptionPane.showMessageDialog(null, "Please select valid role type");
						}
				 }
					
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setToolTipText("Add");
		btnSave.setFocusPainted(false);
		btnSave.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSave.setBackground(btnOriginalColor);
		btnSave.setBounds(944, 298, 83, 29);
		modifyAccountPanel.add(btnSave);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(39, 11, 117, 115);
		add(lblNewLabel);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.WHITE);
					
		accountInfoMenu.addMouseListener(new MouseAdapter() {
			String newPasswordString = new String(txtNewPassword.getPassword());		    	
	    	String verifyPasswordString = new String(txtVerifyPassword.getPassword());	    

		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (!txtNewName.getText().equals("") || !txtNewUsername.getText().equals("") || !newPasswordString.equals("") || !verifyPasswordString.equals("")) {
		            int result = JOptionPane.showConfirmDialog(
		                 null,
		                 "Updating accounts are ongoing. Do you want to proceed?",
		                 "Confirmation",
		                 JOptionPane.YES_NO_OPTION
		            );

		            if (result == JOptionPane.YES_OPTION) {
		                accountInfoMenu.setBorder(new MatteBorder(0, 0, 3, 0, new Color(3, 65, 68)));
		 		        modifyAccountMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		 		        showForm(accountInfoPanel);
		            }
		        } 
		    }
		});
		
		modifyAccountMenu.addMouseListener(new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyAccountMenu.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(3, 65, 68)));
				accountInfoMenu.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));
				showForm(modifyAccountPanel);
			}
		});
//		showForm(accountInfoPanel);
		
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
