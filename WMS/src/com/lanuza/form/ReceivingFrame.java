package com.lanuza.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

public class ReceivingFrame extends JFrame{
	
		private JFrame frame;
		private JTextField qtytxt;
		private JTextField pricetxt;
		private JTable receivetable;
		private JTextField txtSearch;
		JComboBox productCombox;
		JComboBox customerCombox;
		
		
			ReceivingFrame() {
				Initialize();
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
					pst = con.prepareStatement("Select * from tblreceive");
					rs = pst.executeQuery();
					receivetable.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}

				  
	private void Initialize() {		  
			frame = new JFrame();
			frame.setSize(1000,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.getContentPane().setLayout(null);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			  
			  JPanel topPanel = new JPanel();
			  topPanel.setBackground(new Color(3, 65, 68));
			  topPanel.setBounds(0, 0, 984, 66);
			  frame.getContentPane().add(topPanel);
			  topPanel.setLayout(null);
			  
			  JLabel topLabel = new JLabel("Receiving Section");
			  topLabel.setForeground(new Color(255, 255, 255));
			  topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			  topLabel.setBounds(399, 25, 263, 30);
			  topPanel.add(topLabel);
			  
			  JPanel bottomPanel = new JPanel();
			  bottomPanel.setBackground(new Color(3, 65, 68));
			  bottomPanel.setBounds(-39, 543, 984, 18);
			  frame.getContentPane().add(bottomPanel);
			  bottomPanel.setLayout(null);
			  
			  JButton backBtn = new JButton("Back");
			  backBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		frame.dispose();
					Dashboard back = new Dashboard();
			  	}
			  });
			  backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  backBtn.setBounds(860, 490, 89, 29);
			  frame.getContentPane().add(backBtn);
			  
			  JButton btnPrint = new JButton("Print");
			  btnPrint.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnPrint.setBounds(705, 490, 145, 29);
			  frame.getContentPane().add(btnPrint);
			  
			  JPanel panel = new JPanel();
			  panel.setBorder(new TitledBorder(null, "Receiving Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel.setBounds(22, 80, 317, 316);
			  frame.getContentPane().add(panel);
			  panel.setLayout(null);
			  
			  JLabel lblSupplier = new JLabel("Supplier");
			  lblSupplier.setBounds(33, 219, 76, 25);
			  panel.add(lblSupplier);
			  lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  customerCombox = new JComboBox();
			  customerCombox.setBounds(119, 220, 153, 25);
			  panel.add(customerCombox);
			  customerCombox.setModel(new DefaultComboBoxModel(new String[] {"Ben Food & Bev.", "Pacific Minimart", "Sunrise Teashop"}));
			  customerCombox.setMaximumRowCount(2);
			  customerCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			  customerCombox.setEditable(true);
			  
			  JButton addBtn = new JButton("Add");
			  addBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
			  		String product,qty,price,customer;
			  		product = productCombox.getSelectedItem().toString();
			  		qty = qtytxt.getText();
			  		price = pricetxt.getText();
			  		customer = customerCombox.getSelectedItem().toString();
					
					try {
						pst = con.prepareStatement("insert into tblreceive(Product,Qty,Price,Supplier)values(?,?,?,?)");
						pst.setString(1, product);
						pst.setString(2, qty);
						pst.setString(3, price);
						pst.setString(4, customer); 
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record added");
						table_load();
						productCombox.setSelectedItem("");
						qtytxt.setText("");
						pricetxt.setText("");
						customerCombox.setSelectedItem("");
						productCombox.requestFocus();
						
					}catch(SQLException el) {
						el.printStackTrace();
					}
			  	}
			  });
			  addBtn.setBounds(101, 269, 89, 29);
			  panel.add(addBtn);
			  addBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  JButton clearBtn = new JButton("Clear");
			  clearBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
			  		productCombox.setSelectedItem("");
					qtytxt.setText("");
					pricetxt.setText("");
					customerCombox.setSelectedItem("");
					productCombox.requestFocus();
			  	}
			  });
			  clearBtn.setBounds(199, 269, 89, 29);
			  panel.add(clearBtn);
			  clearBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  pricetxt = new JTextField();
			  pricetxt.setBounds(119, 159, 153, 29);
			  panel.add(pricetxt);
			  pricetxt.setFont(new Font("Tahoma", Font.BOLD, 13));
			  pricetxt.setColumns(10);
			  
			  qtytxt = new JTextField();
			  qtytxt.setBounds(119, 101, 153, 29);
			  panel.add(qtytxt);
			  qtytxt.setFont(new Font("Tahoma", Font.BOLD, 13));
			  qtytxt.setColumns(10);
			  
			  productCombox = new JComboBox();
			  productCombox.setBounds(119, 54, 153, 25);
			  panel.add(productCombox);
			  productCombox.setModel(new DefaultComboBoxModel(new String[] {"Lipote Drinks", "Hagis Drinks", "Sapinit Drinks"}));
			  productCombox.setMaximumRowCount(2);
			  productCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			  productCombox.setEditable(true);
			  
			  JLabel lblPassword_1 = new JLabel("Price");
			  lblPassword_1.setBounds(52, 160, 57, 25);
			  panel.add(lblPassword_1);
			  lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  JLabel lblPassword = new JLabel("QTY");
			  lblPassword.setBounds(52, 102, 57, 25);
			  panel.add(lblPassword);
			  lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  JLabel lblUsername = new JLabel("Product");
			  lblUsername.setBounds(44, 53, 65, 25);
			  panel.add(lblUsername);
			  lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setBounds(433, 127, 517, 316);
			  scrollPane.setVisible(true);
			  frame.getContentPane().add(scrollPane);
			  
			  receivetable = new JTable();
			  scrollPane.setViewportView(receivetable);
			  
			  JLabel lblReceiveProductList = new JLabel("Receive Product List");
			  lblReceiveProductList.setForeground(new Color(0, 0, 0));
			  lblReceiveProductList.setFont(new Font("Tahoma", Font.BOLD, 18));
			  lblReceiveProductList.setBounds(600, 87, 194, 30);
			  frame.getContentPane().add(lblReceiveProductList);
			  
			  JPanel panel_1 = new JPanel();
			  panel_1.setBorder(new TitledBorder(null, "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel_1.setBounds(22, 419, 340, 113);
			  frame.getContentPane().add(panel_1);
			  panel_1.setLayout(null);
			  
			  JLabel lblReceivingid = new JLabel("ReceivingID");
			  lblReceivingid.setBounds(10, 37, 99, 19);
			  panel_1.add(lblReceivingid);
			  lblReceivingid.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  txtSearch = new JTextField();
			  txtSearch.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						
						try {
							String receiveId = txtSearch.getText();
							
							pst = con.prepareStatement("Select Product,Qty,Price,Supplier from tblreceive where receivingID = ?");
							pst.setString(1, receiveId);
							ResultSet rs = pst.executeQuery();
							
							if(rs.next()==true) {
								String product = rs.getString(1);
								String qty = rs.getString(2);
								String price = rs.getString(3);
								String customer = rs.getString(4);
								
								productCombox.setSelectedItem(product);
								qtytxt.setText(qty);
								pricetxt.setText(price);
								customerCombox.setSelectedItem(customer);
							}else {
								
								productCombox.setSelectedItem("");
								qtytxt.setText("");
								pricetxt.setText("");
								customerCombox.setSelectedItem("");
							}
								
						}catch(SQLException el) {
							
						}
					}
				});
			  
			  txtSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
			  txtSearch.setColumns(10);
			  txtSearch.setBounds(108, 33, 207, 29);
			  panel_1.add(txtSearch);
			  
			  JButton updateBtn = new JButton("Update");
			  updateBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
					String product,qty,price,customer,receiveId;
					
					product = productCombox.getSelectedItem().toString();					
					qty = qtytxt.getText();
					price = pricetxt.getText();
					customer = customerCombox.getSelectedItem().toString();
					receiveId = txtSearch.getText();
					try {
						
						pst = con.prepareStatement("update tblreceive set Product= ?,Qty=?,Price=?,Supplier=? where receivingID=?");
						pst.setString(1, product);
						pst.setString(2, qty);
						pst.setString(3, price);
						pst.setString(4, customer);
						pst.setString(5, receiveId);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null,"Record Updated");
						table_load();				
						productCombox.setSelectedItem("");					
						qtytxt.setText("");
						pricetxt.setText("");
						customerCombox.setSelectedItem("");
						productCombox.requestFocus();
						
						
						
					}catch(SQLException ec) {
						ec.printStackTrace();
					}
			  	}
			  });
			  updateBtn.setBounds(128, 73, 89, 29);
			  panel_1.add(updateBtn);
			  updateBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  
			  JButton delBtn = new JButton("Delete");
			  delBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {

			  		String receiveiD;
					
			  		receiveiD = txtSearch.getText();
					try {
						
						pst = con.prepareStatement("delete from tblreceive where receivingID=?");
						pst.setString(1, receiveiD);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null,"Record Deleted");
						table_load();
						
						productCombox.setSelectedItem("");
						qtytxt.setText("");
						pricetxt.setText("");
						customerCombox.setSelectedItem("");
						productCombox.requestFocus();
						
						
						
					}catch(SQLException ec) {
						ec.printStackTrace();
					}
			  	}
			  });
			  delBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  delBtn.setBounds(227, 73, 89, 29);
			  panel_1.add(delBtn);
			  

			}

		}