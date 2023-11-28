package com.lanuza.wms.ui.form;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;

public class Profile  {

	private JFrame frame;


	public Profile() {
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
		
		JLabel lblNewAccount = new JLabel("Profile");
		lblNewAccount.setForeground(Color.WHITE);
		lblNewAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewAccount.setBounds(416, 25, 138, 30);
		topPanel.add(lblNewAccount);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
	}
}
