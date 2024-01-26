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
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

public class ManageDashboardForm extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;

    public ManageDashboardForm() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to Warehouse Management System");
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(3, 65, 68));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(0, 11, 613, 108);
        add(lblNewLabel);
        
        textField = new JTextField(20);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setBounds(271, 266, 237, 32);
        add(textField);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(271, 312, 237, 32);
        add(passwordField);
        
        JButton btnClear = new JButton();
        btnClear.setText("Clear");
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnClear.setFocusPainted(false);
        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
        btnClear.setBackground(new Color(3, 65, 68));
        btnClear.setBounds(271, 366, 65, 38);
        add(btnClear);
        
        JButton btnLogin = new JButton();
        btnLogin.setText("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createRaisedBevelBorder());
        btnLogin.setBackground(new Color(3, 65, 68));
        btnLogin.setBounds(346, 366, 162, 38);
        add(btnLogin);
        
        JLabel passwordLabel = new JLabel("password: ");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        passwordLabel.setBounds(191, 309, 81, 35);
        add(passwordLabel);
        
        JLabel userLabel = new JLabel("username: ");
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        userLabel.setBounds(191, 266, 81, 32);
        add(userLabel);
        
        JLabel lblcreateAccount = new JLabel("Don't have account");
        lblcreateAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        lblcreateAccount.setBounds(140, 451, 116, 24);
        add(lblcreateAccount);
        
        JLabel lblNewLabel_1 = new JLabel("Sign Up");
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.BLUE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblNewLabel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 255)));
        lblNewLabel_1.setBounds(251, 451, 54, 24);
        add(lblNewLabel_1);
        
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(new Color(3, 65, 68));
        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));
        loginLabel.setBackground(new Color(3, 65, 68));
        loginLabel.setBounds(151, 170, 72, 24);
        add(loginLabel);

    }     
}
