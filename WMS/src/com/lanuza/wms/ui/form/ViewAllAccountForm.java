package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.dao.impl.AccountDAOImpl;
import com.lanuza.wms.service.AccountService;
import com.lanuza.wms.service.impl.AccountServiceImpl;

public class ViewAllAccountForm {
	
	private JFrame frame;
	private JTable table;
	private final AccountService accountService;
	private final AccountDAO accountDAO;

	public ViewAllAccountForm() {
		this.accountDAO = new AccountDAOImpl();
		this.accountService = new AccountServiceImpl(accountDAO);
		initialize();
		loadData();
	}
	
	private void loadData() {
        // Call the tableLoad method from ProductService
        accountService.tableLoad(table);
    }
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 1, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
				
		JLabel topLabel = new JLabel("View Account");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(434, 25, 132, 30);
		topPanel.add(topLabel);
		
		JLabel lblAccountList = new JLabel("Account List");
		lblAccountList.setForeground(new Color(0, 0, 0));
		lblAccountList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAccountList.setBounds(448, 110, 132, 30);
		frame.getContentPane().add(lblAccountList);		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				//new ManageAllForm();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(20, 485, 89, 29);
		frame.getContentPane().add(btnBack);	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 151, 867, 295);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();;
		scrollPane.setViewportView(table);	
	}
}
