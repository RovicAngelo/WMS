package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

    }     
}
