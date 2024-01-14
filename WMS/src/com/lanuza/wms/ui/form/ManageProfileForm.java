package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import javax.swing.ImageIcon;

public class ManageProfileForm {
	private final AccountDAO accountDAO;
	private final AccountService accountService;
	private JFrame frame;
	private JTextField txtNewName,txtNewUsername;
	private JPasswordField txtNewPassword,txtNewRepassword;
	private JLabel lblCurrentId1,lblCurrentName1,lblCurrentUsername1,lblCurrentRole1;
	JComboBox newRoleCombox;
	JPanel modifyPanel;
	String name;
	
	//constructor that hold the username and password in loginForm
    public ManageProfileForm(int id, boolean initializeUI) {
    	this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);
		 if (initializeUI) {
			//to get the ID in loginForm
	        Account account = accountService.getAccountById(id);
	        
	        String name = account.getName();
            String user = account.getUsername();
            String pass = account.getPassword();
            String role = account.getRole();
            
	    	lblCurrentId1 = new JLabel();
	    	lblCurrentName1 = new JLabel();	    	
	    	lblCurrentUsername1 = new JLabel();
	    	lblCurrentRole1 = new JLabel();
	    	
	    	txtNewName = new JTextField();
	    	txtNewUsername = new JTextField();
	    	newRoleCombox = new JComboBox();
	    	
	    	lblCurrentId1.setText(String.valueOf(account.getAccountId()));
           	lblCurrentName1.setText(name != null ? name : "null field");  
            lblCurrentUsername1.setText(user != null ? user : "null field");
            lblCurrentRole1.setText(role != null ? role : "null field");

            txtNewName.setText(name != null ? name : "null field");     
            txtNewUsername.setText(user != null ? user : "null field");
            newRoleCombox.setSelectedItem(role != null ? role : "null field");
            initialize();            
        }
    }

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000,600);
		frame.setUndecorated(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(235, 235, 235));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(238, 105, 528, 363);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentId = new JLabel("Account ID");
		lblCurrentId.setBounds(204, 54, 81, 33);
		panel.add(lblCurrentId);
		lblCurrentId.setForeground(Color.BLACK);
		lblCurrentId.setFont(new Font("Tahoma", Font.BOLD, 15));
				
		JLabel lblModifyAccount = new JLabel("Modify Account?");
		lblModifyAccount.setForeground(new Color(0, 0, 255));
		lblModifyAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModifyAccount.setBounds(354, 219, 112, 33);
		panel.add(lblModifyAccount);
		
		JLabel lblCurrentName = new JLabel("Name");
		lblCurrentName.setForeground(Color.BLACK);
		lblCurrentName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentName.setBounds(219, 91, 46, 33);
		panel.add(lblCurrentName);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 1, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Manage Account");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(420, 25, 166, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
			
		JLabel lblCurrentUsername = new JLabel("Username");
		lblCurrentUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUsername.setForeground(new Color(0, 0, 0));
		lblCurrentUsername.setBounds(204, 131, 79, 33);
		panel.add(lblCurrentUsername);
		
		JLabel lblCurrentRole = new JLabel("Role");
		lblCurrentRole.setForeground(Color.BLACK);
		lblCurrentRole.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentRole.setBounds(219, 175, 46, 33);
		panel.add(lblCurrentRole);		
		
		lblCurrentId1.setOpaque(true);
		lblCurrentId1.setBackground(new Color(255, 255, 255));
		lblCurrentId1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCurrentId1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentId1.setHorizontalTextPosition(SwingConstants.CENTER);		
		lblCurrentId1.setForeground(Color.BLACK);
		lblCurrentId1.setFont(new Font("Tahoma", Font.BOLD, 15));	
		lblCurrentId1.setBounds(295, 58, 173, 29);
		panel.add(lblCurrentId1);
			
		lblCurrentName1.setOpaque(true);
		lblCurrentName1.setBackground(new Color(255, 255, 255));
		lblCurrentName1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCurrentName1.setForeground(Color.BLACK);
		lblCurrentName1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentName1.setBounds(295, 95, 173, 29);
		panel.add(lblCurrentName1);
		
		lblCurrentUsername1.setOpaque(true);
		lblCurrentUsername1.setBackground(new Color(255, 255, 255));
		lblCurrentUsername1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCurrentUsername1.setForeground(Color.BLACK);
		lblCurrentUsername1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUsername1.setBounds(295, 135, 175, 29);
		panel.add(lblCurrentUsername1);
		
		lblCurrentRole1.setOpaque(true);
		lblCurrentRole1.setBackground(new Color(255, 255, 255));
		lblCurrentRole1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCurrentRole1.setForeground(Color.BLACK);
		lblCurrentRole1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentRole1.setBounds(293, 179, 173, 29);
		panel.add(lblCurrentRole1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(31, 54, 137, 131);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setIcon(new ImageIcon(ManageProfileForm.class.getResource("/com/lanuza/wms/ui/resources/icons/user.png")));
		lblNewLabel.setBounds(10, 5, 117, 115);
		panel_1.add(lblNewLabel);	
		
		lblModifyAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCreateProductPopup();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				frame.dispose();
				//new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(21, 490, 89, 29);
		frame.getContentPane().add(btnBack);			
		
			
	}
	
	 private void showCreateProductPopup() {
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Place New Order");
	        createProductDialog.setSize( 559, 429);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        modifyPanel = new JPanel();
			modifyPanel.setVisible(true);
			modifyPanel.setBackground(new Color(230, 230, 230));
			modifyPanel.setLayout(null);
			//modifyPanel.setBounds(334, 90, 559, 429);
			//frame.getContentPane().add(modifyPanel);
			
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
			lblNewName.setLabelFor(lblNewName);
			lblNewName.setForeground(Color.BLACK);
			lblNewName.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewName.setBounds(130, 86, 54, 31);
			modifyPanel.add(lblNewName);
			
			JLabel lblInput = new JLabel("Input new account informations");
			lblInput.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblInput.setForeground(new Color(0, 0, 0));
			lblInput.setBounds(170, 25, 232, 31);
			modifyPanel.add(lblInput);
			
			
			txtNewName.setToolTipText("Juan Dela Cruz");
			txtNewName.setColumns(10);
			txtNewName.setBounds(222, 88, 222, 31);
			modifyPanel.add(txtNewName);
						
			txtNewUsername.setColumns(10);
			txtNewUsername.setBounds(222, 130, 222, 31);
			modifyPanel.add(txtNewUsername);
					
			txtNewPassword = new JPasswordField();
			txtNewPassword.setColumns(10);
			txtNewPassword.setBounds(222, 173, 222, 31);
			modifyPanel.add(txtNewPassword);
			
			txtNewRepassword = new JPasswordField();
			txtNewRepassword.setColumns(10);
			txtNewRepassword.setBounds(222, 216, 222, 31);
			modifyPanel.add(txtNewRepassword);
			
			//newRoleCombox = new JComboBox(new Object[]{});
			newRoleCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
			newRoleCombox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Guest"}));
			newRoleCombox.setBounds(222, 270, 222, 31);
			modifyPanel.add(newRoleCombox);
	          
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
					newPassword = txtNewPassword.getPassword().toString();
					newrole = newRoleCombox.getSelectedItem().toString();
					accid = Integer.parseInt( lblCurrentId1.getText());
					
					Account updateAccount = new Account(newName,newUsername,newPassword,newrole,accid);
					accountService.updateAccount(updateAccount);			

					//updateProfile(updateAccount);
					
					txtNewName.setText("");
					txtNewUsername.setText("");
					txtNewPassword.setText("");
					txtNewRepassword.setText("");
					newRoleCombox.setSelectedItem("");
					txtNewName.requestFocus();
				}
			});
			btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));	
	        // Add action listener to the Save button
	        btnUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation (you can customize this part)
	                // For simplicity, just dispose the dialog in this example
	                createProductDialog.dispose();
	            }
	        });
	        
	        btnClear.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	            	txtNewName.setText("");
	            	txtNewUsername.setText("");
	            	txtNewPassword.setText("");
	            	txtNewRepassword.setText("");	            
	            	newRoleCombox.setSelectedItem("");
	            }
	        });
	        
	        JButton btnCancel = new JButton("Cancel");
			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				//modifyPanel.setVisible(false);
					createProductDialog.dispose();
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnCancel.setBounds(20, 347, 98, 31);
			modifyPanel.add(btnCancel);

	        // Add the popupPanel to the dialog's content pane
	        createProductDialog.getContentPane().add(modifyPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	    }
}
