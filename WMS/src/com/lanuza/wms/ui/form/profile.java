package com.lanuza.wms.ui.form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.lanuza.wms.ui.components.CustomButton;

public class profile extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel bodyPanel;
	private JTextField txtNewName;
	private JTextField txtNewUsername;
	private JTextField txtNewPassword;
	private JTextField txtVerifyPassword;
	
	public profile() {
		setLayout(null);						   	
        initialize();     

	}
	private void initialize(){
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setBackground(new Color(3, 65, 68));
		panelButtons.setBounds(0, 45, 1097, 38);
		add(panelButtons);
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", (ActionListener) null, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setText("Print");
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPrint.setBackground(new Color(3, 65, 68));
		btnPrint.setBounds(0, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", (ActionListener) null, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setText("Mode");
		btnMode.setForeground(Color.WHITE);
		btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMode.setBackground(new Color(3, 65, 68));
		btnMode.setBounds(62, 0, 63, 38);
		panelButtons.add(btnMode);
		
		JLabel lblProfileSection = new JLabel("Profile Section");
		lblProfileSection.setForeground(Color.BLACK);
		lblProfileSection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfileSection.setBounds(0, 0, 184, 29);
		add(lblProfileSection);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1097, 20);
		add(bottomPanel);
		
		bodyPanel = new JPanel();
		bodyPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		bodyPanel.setBackground(new Color(235, 235, 235));
		bodyPanel.setBounds(24, 272, 1050, 360);
		add(bodyPanel);
		bodyPanel.setLayout(new CardLayout(0, 0));
		
		JPanel modifyAccountPanel = new JPanel();
		modifyAccountPanel.setLayout(null);
		bodyPanel.add(modifyAccountPanel, "name_33608089124200");
		
		JPanel avatarPanel = new JPanel();
		avatarPanel.setLayout(null);
		avatarPanel.setBackground(Color.WHITE);
		avatarPanel.setBounds(114, 82, 153, 140);
		modifyAccountPanel.add(avatarPanel);
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setOpaque(true);
		lblAvatar.setBackground(Color.WHITE);
		lblAvatar.setBounds(18, 11, 117, 115);
		avatarPanel.add(lblAvatar);
		
		JLabel lblChangeProfile = new JLabel("Change Profile");
		lblChangeProfile.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChangeProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeProfile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeProfile.setBounds(143, 227, 86, 27);
		modifyAccountPanel.add(lblChangeProfile);
		
		JLabel lblModAccTitle = new JLabel("Modify Account");
		lblModAccTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblModAccTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblModAccTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModAccTitle.setBounds(0, 0, 117, 29);
		modifyAccountPanel.add(lblModAccTitle);
		
		JLabel lblNewUsername = new JLabel("Username");
		lblNewUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUsername.setForeground(Color.GRAY);
		lblNewUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewUsername.setBounds(380, 94, 59, 19);
		modifyAccountPanel.add(lblNewUsername);
		
		JLabel lblNewRole = new JLabel("Role");
		lblNewRole.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRole.setForeground(Color.GRAY);
		lblNewRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewRole.setBounds(380, 139, 59, 19);
		modifyAccountPanel.add(lblNewRole);
		
		txtNewName = new JTextField();
		txtNewName.setColumns(10);
		txtNewName.setBounds(472, 46, 286, 34);
		modifyAccountPanel.add(txtNewName);
		
		JLabel lblNewName = new JLabel("Name");
		lblNewName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewName.setForeground(Color.GRAY);
		lblNewName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewName.setBounds(380, 53, 59, 19);
		modifyAccountPanel.add(lblNewName);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setForeground(Color.GRAY);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewPassword.setBounds(363, 217, 94, 19);
		modifyAccountPanel.add(lblNewPassword);
		
		JLabel lblVerifyPassword = new JLabel("Verify Password");
		lblVerifyPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVerifyPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerifyPassword.setForeground(Color.GRAY);
		lblVerifyPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVerifyPassword.setBounds(351, 262, 106, 19);
		modifyAccountPanel.add(lblVerifyPassword);
		
		JComboBox newRoleCombox = new JComboBox();
		newRoleCombox.setBounds(472, 132, 286, 34);
		modifyAccountPanel.add(newRoleCombox);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(472, 87, 286, 34);
		modifyAccountPanel.add(txtNewUsername);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(472, 210, 286, 34);
		modifyAccountPanel.add(txtNewPassword);
		
		txtVerifyPassword = new JTextField();
		txtVerifyPassword.setColumns(10);
		txtVerifyPassword.setBounds(472, 255, 286, 34);
		modifyAccountPanel.add(txtVerifyPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(941, 298, 79, 29);
		modifyAccountPanel.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(852, 298, 79, 29);
		modifyAccountPanel.add(btnClear);
		
		JPanel accountInfoPanel = new JPanel();
		accountInfoPanel.setBounds(0, 0, 1048, 358);
		bodyPanel.add(accountInfoPanel);
		accountInfoPanel.setLayout(null);
		
		JLabel lblAccInfoTitle = new JLabel("Account Information");
		lblAccInfoTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAccInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccInfoTitle.setBounds(10, 11, 145, 29);
		accountInfoPanel.add(lblAccInfoTitle);
		
		JLabel lblCurrentName = new JLabel("Name");
		lblCurrentName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentName.setForeground(Color.GRAY);
		lblCurrentName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentName.setBounds(89, 88, 41, 19);
		accountInfoPanel.add(lblCurrentName);
		
		JLabel lblCurrentUsername = new JLabel("Username");
		lblCurrentUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setForeground(Color.GRAY);
		lblCurrentUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentUsername.setBounds(89, 162, 59, 19);
		accountInfoPanel.add(lblCurrentUsername);
		
		JLabel lblCurrentRole = new JLabel("Role");
		lblCurrentRole.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentRole.setForeground(Color.GRAY);
		lblCurrentRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentRole.setBounds(89, 237, 33, 19);
		accountInfoPanel.add(lblCurrentRole);
		
		JLabel lbltextCurrentName = new JLabel("New label");
		lbltextCurrentName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltextCurrentName.setBounds(89, 107, 286, 34);
		accountInfoPanel.add(lbltextCurrentName);
		
		JLabel lbltextCurrentUsernane = new JLabel("New label");
		lbltextCurrentUsernane.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltextCurrentUsernane.setBounds(89, 181, 286, 34);
		accountInfoPanel.add(lbltextCurrentUsernane);
		
		JLabel lbltextCurrentRole = new JLabel("New label");
		lbltextCurrentRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltextCurrentRole.setBounds(89, 258, 286, 34);
		accountInfoPanel.add(lbltextCurrentRole);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 96, 118, 114);
		add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 117, 115);
		panel_1.add(lblNewLabel);
		
		JLabel lblDisplayName = new JLabel("Name");
		lblDisplayName.setBounds(167, 94, 219, 46);
		add(lblDisplayName);
		lblDisplayName.setForeground(Color.BLACK);
		lblDisplayName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDisplayRole = new JLabel("Role");
		lblDisplayRole.setBounds(167, 139, 46, 29);
		add(lblDisplayRole);
		lblDisplayRole.setForeground(Color.BLACK);
		lblDisplayRole.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel accountInfoMenu = new JLabel("Account Information");
		accountInfoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		accountInfoMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		accountInfoMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		accountInfoMenu.setFont(new Font("Arial", Font.BOLD, 12));
		accountInfoMenu.setBounds(39, 238, 128, 29);
		add(accountInfoMenu);
		
		JLabel modifyAccountMenu = new JLabel("Modify Account");		
		modifyAccountMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		modifyAccountMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		modifyAccountMenu.setHorizontalAlignment(SwingConstants.CENTER);
		modifyAccountMenu.setFont(new Font("Arial", Font.BOLD, 12));
		modifyAccountMenu.setBounds(187, 238, 118, 29);
		add(modifyAccountMenu);
		
		MouseListener menuMouseListener = new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        JLabel selectedLabel = (JLabel) e.getSource();
		        selectedLabel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(3, 65, 68)));

		        // Update the other label's border
		        if (selectedLabel == modifyAccountMenu) {
		            accountInfoMenu.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		        } else if (selectedLabel == accountInfoMenu) {
		            modifyAccountMenu.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		        }
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	JLabel selectedLabel = (JLabel) e.getSource();
		    	if (selectedLabel == modifyAccountMenu) {
		            showForm(modifyAccountPanel );
		        } else if (selectedLabel == accountInfoMenu) {
		        	 showForm(accountInfoPanel );
		        }
		    }
		};
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
