package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ManageDashboardForm extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public ManageDashboardForm() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to Warehouse Management System");
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(3, 65, 68));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(10, 84, 613, 108);
        add(lblNewLabel);
        
        JLabel lblDashboard = new JLabel("Dashboard");
        lblDashboard.setForeground(Color.BLACK);
        lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDashboard.setBounds(0, 0, 170, 29);
        add(lblDashboard);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(null);
        panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
        panelButtons.setBackground(new Color(3, 65, 68));
        panelButtons.setBounds(0, 33, 1097, 38);
        add(panelButtons);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
        bottomPanel.setBackground(new Color(3, 65, 68));
        bottomPanel.setBounds(0, 643, 1097, 20);
        add(bottomPanel);
        
        JPanel modifyAccountPanel = new JPanel();
        modifyAccountPanel.setLayout(null);
        modifyAccountPanel.setBounds(10, 222, 1030, 338);
        add(modifyAccountPanel);
        
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
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setForeground(Color.GRAY);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblUsername.setBounds(380, 94, 59, 19);
        modifyAccountPanel.add(lblUsername);
        
        JLabel lblRole = new JLabel("Role");
        lblRole.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole.setForeground(Color.GRAY);
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRole.setBounds(380, 139, 59, 19);
        modifyAccountPanel.add(lblRole);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(472, 46, 286, 34);
        modifyAccountPanel.add(textField);
        
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
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(472, 87, 286, 34);
        modifyAccountPanel.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(472, 210, 286, 34);
        modifyAccountPanel.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(472, 255, 286, 34);
        modifyAccountPanel.add(textField_3);
        
        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSave.setBounds(941, 298, 79, 29);
        modifyAccountPanel.add(btnSave);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnClear.setBounds(852, 298, 79, 29);
        modifyAccountPanel.add(btnClear);

    }     
}
