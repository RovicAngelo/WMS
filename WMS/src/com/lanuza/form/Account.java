package com.lanuza.form;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Account {

	private JFrame frame;
	private JTextField txtUsername,txtPassword,txtSearch;
	private JTable table;
	JComboBox typeCombox;

	public Account() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
		}catch(ClassNotFoundException exc){
			
		}catch(SQLException exc) {
			
		}
	}
	
	void table_load() {
		try {
			pst = con.prepareStatement("Select * from tblaccount");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
		panel.setBorder(new TitledBorder(null, "Account Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 110, 293, 231);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setBounds(10, 44, 88, 33);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(10, 88, 79, 33);
		panel.add(lblPassword);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.BLACK);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(30, 140, 46, 33);
		panel.add(lblType);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(92, 48, 182, 29);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(92, 92, 182, 29);
		panel.add(txtPassword);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
						
						String username,password,type;
						username = txtUsername.getText();
						password = txtPassword.getText();
						type = typeCombox.getSelectedItem().toString();
						
						try {
							pst = con.prepareStatement("insert into tblaccount(Username,Password,Type)values(?,?,?)");
							pst.setString(1, username);
							pst.setString(2, password);
							pst.setString(3, type);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record added");
							table_load();
							txtUsername.setText("");
							txtPassword.setText("");
							typeCombox.setSelectedItem("");
							txtUsername.requestFocus();
							
						}catch(SQLException el) {
							el.printStackTrace();
						}
					}
				});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(92, 179, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
				typeCombox.setSelectedItem("");
				txtUsername.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(185, 179, 89, 29);
		panel.add(btnClear);
		
		typeCombox = new JComboBox();
		typeCombox.setFont(new Font("Tahoma", Font.BOLD, 14));
		typeCombox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Guest"}));
		typeCombox.setBounds(92, 143, 182, 26);
		panel.add(typeCombox);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 1, 984, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Manage Account");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(399, 25, 263, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 543, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 352, 293, 114);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(37, 27, 41, 33);
		panel_1.add(lblId);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				try {
					String accid = txtSearch.getText();
					
					pst = con.prepareStatement("Select Username,Password,Type from tblaccount where accID = ?");
					pst.setString(1, accid);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true) {
						String username = rs.getString(1);
						String password = rs.getString(2);
						String type = rs.getString(3);
						
						txtUsername.setText(username);
						txtPassword.setText(password);
						typeCombox.setSelectedItem(type);
					}else {
						
						txtUsername.setText("");
						txtPassword.setText("");
						typeCombox.setSelectedItem("");
					}
						
				}catch(SQLException el) {
					
				}
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(88, 31, 182, 29);
		panel_1.add(txtSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username,password,type,accid;
				
				username = txtUsername.getText();
				password = txtPassword.getText();
				type = typeCombox.getSelectedItem().toString();
				accid = txtSearch.getText();
				try {
					
					pst = con.prepareStatement("update tblaccount set Username= ?,Password=?,Type=? where accID=?");
					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, type);
					pst.setString(4, accid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated");
					table_load();
					
					txtUsername.setText("");
					txtPassword.setText("");
					typeCombox.setSelectedItem("");
					txtUsername.requestFocus();
					
					
					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(88, 71, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String accid;
				
				accid = txtSearch.getText();
				try {
					
					pst = con.prepareStatement("delete from tblaccount where accID=?");
					pst.setString(1, accid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted");
					table_load();
					
					txtUsername.setText("");
					txtPassword.setText("");
					typeCombox.setSelectedItem("");
					txtUsername.requestFocus();
					
					
					
				}catch(SQLException ec) {
					ec.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(181, 71, 89, 29);
		panel_1.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 151, 641, 359);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				txtUsername.setText(model.getValueAt(Myindex, 1).toString());
				txtPassword.setText(model.getValueAt(Myindex, 2).toString());
				typeCombox.setSelectedItem(model.getValueAt(Myindex, 3).toString());		
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblAccountList = new JLabel("Account List");
		lblAccountList.setForeground(new Color(0, 0, 0));
		lblAccountList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAccountList.setBounds(572, 110, 132, 30);
		frame.getContentPane().add(lblAccountList);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Dashboard back = new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(20, 503, 89, 29);
		frame.getContentPane().add(btnBack);
	}
}
