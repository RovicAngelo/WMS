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

    }     
}
