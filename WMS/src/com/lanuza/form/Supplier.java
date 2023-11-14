package com.lanuza.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;

	public class Supplier extends JFrame{
		private JFrame frame;
		private JButton btnBack;
		private JTextField txtName;
		private JTable table;
		
		Supplier() {
			initialize();
			Connect();
			table_load();
		}
		
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		private JTextField txtBRGY;
		private JTextField txtSuppierId;
		private JTextField txtCity;
		private JTextField txtProvince;
		private JTextField txtPhoneNo;

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
				pst = con.prepareStatement("Select * from tblsupplier");
				rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
								
		public void initialize() {	
			frame = new JFrame();
			frame.setTitle("PhilDrinks"); // set the title if Frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
			frame.setResizable(false);
			frame.setSize(1000, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true); // this make Frame visible
			frame.getContentPane().setLayout(null);
			  
			  btnBack = new JButton("Back");
			  btnBack.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		frame.dispose();
			  		Inventory back = new Inventory();
			  	}
			  });
			  btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnBack.setBounds(20, 499, 89, 29);
			  frame.getContentPane().add(btnBack);
			  
			  JPanel topPanel = new JPanel();
			  topPanel.setBackground(new Color(3, 65, 68));
			  topPanel.setBounds(0, 0, 984, 66);
			  frame.getContentPane().add(topPanel);
			  topPanel.setLayout(null);
			  
			  JLabel topLabel = new JLabel("Manage Supplier ");
			  topLabel.setForeground(new Color(255, 255, 255));
			  topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			  topLabel.setBounds(399, 25, 263, 30);
			  topPanel.add(topLabel);
			  
			  JPanel bottomPanel = new JPanel();
			  bottomPanel.setBackground(new Color(3, 65, 68));
			  bottomPanel.setBounds(0, 543, 984, 18);
			  frame.getContentPane().add(bottomPanel);
			  bottomPanel.setLayout(null);
			  
			  JPanel panel = new JPanel();
			  panel.setLayout(null);
			  panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Supplier Form", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			  panel.setBounds(10, 102, 293, 261);
			  frame.getContentPane().add(panel);
			  
			  JLabel lblName = new JLabel("Name");
			  lblName.setForeground(Color.BLACK);
			  lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblName.setBounds(32, 25, 51, 33);
			  panel.add(lblName);
			  
			  txtName = new JTextField();
			  txtName.setColumns(10);
			  txtName.setBounds(93, 29, 182, 28);
			  panel.add(txtName);
			  
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setBounds(324, 138, 638, 361);
			  frame.getContentPane().add(scrollPane);
			  
			  table = new JTable();
			  table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						int Myindex = table.getSelectedRow();
						txtName.setText(model.getValueAt(Myindex, 1).toString());							
					}
				});
			  
			  scrollPane.setViewportView(table);
			  
			  JButton btnAdd = new JButton("Add");
			  btnAdd.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
				  		
				  		String name;
				  		name = txtName.getText();
						
						try {
							pst = con.prepareStatement("insert into tblsupplier(Name)values(?)");
							pst.setString(1, name); 
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record added");
							table_load();						
							txtName.setText("");
							txtName.requestFocus();
							
						}catch(SQLException el) {
							el.printStackTrace();
						}
				  	}
				  });
			  btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnAdd.setBounds(87, 221, 89, 29);
			  panel.add(btnAdd);
			  
			  JButton btnClear = new JButton("Clear");
			  btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnClear.setBounds(186, 221, 89, 29);
			  panel.add(btnClear);
			  
			  txtBRGY = new JTextField();
			  txtBRGY.setColumns(10);
			  txtBRGY.setBounds(93, 68, 182, 28);
			  panel.add(txtBRGY);
			  
			  txtCity = new JTextField();
			  txtCity.setColumns(10);
			  txtCity.setBounds(93, 107, 182, 28);
			  panel.add(txtCity);
			  
			  txtProvince = new JTextField();
			  txtProvince.setColumns(10);
			  txtProvince.setBounds(103, 143, 172, 28);
			  panel.add(txtProvince);
			  
			  txtPhoneNo = new JTextField();
			  txtPhoneNo.setColumns(10);
			  txtPhoneNo.setBounds(103, 182, 172, 28);
			  panel.add(txtPhoneNo);
			  
			  JLabel lblBrgy = new JLabel("Brgy");
			  lblBrgy.setForeground(Color.BLACK);
			  lblBrgy.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblBrgy.setBounds(32, 64, 41, 33);
			  panel.add(lblBrgy);
			  
			  JLabel lblCity = new JLabel("City");
			  lblCity.setForeground(Color.BLACK);
			  lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblCity.setBounds(32, 103, 35, 33);
			  panel.add(lblCity);
			  
			  JLabel lblProvince = new JLabel("Province");
			  lblProvince.setForeground(Color.BLACK);
			  lblProvince.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblProvince.setBounds(12, 143, 71, 29);
			  panel.add(lblProvince);
			  
			  JLabel lblPhoneNo = new JLabel("Phone No.");
			  lblPhoneNo.setForeground(Color.BLACK);
			  lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblPhoneNo.setBounds(12, 187, 76, 28);
			  panel.add(lblPhoneNo);
			  
			  JLabel lblSupplierList = new JLabel("Supplier List");
			  lblSupplierList.setForeground(Color.BLACK);
			  lblSupplierList.setFont(new Font("Tahoma", Font.BOLD, 18));
			  lblSupplierList.setBounds(592, 102, 134, 30);
			  frame.getContentPane().add(lblSupplierList);
			  
			  JPanel panel_1 = new JPanel();
			  panel_1.setLayout(null);
			  panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			  panel_1.setBounds(10, 374, 293, 114);
			  frame.getContentPane().add(panel_1);
			  
			  JLabel lblId = new JLabel("SuppplierID");
			  lblId.setForeground(Color.BLACK);
			  lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblId.setBounds(10, 27, 101, 33);
			  panel_1.add(lblId);
			  
			  txtSuppierId = new JTextField();
			  txtSuppierId.setColumns(10);
			  txtSuppierId.setBounds(109, 31, 161, 29);
			  panel_1.add(txtSuppierId);
			  
			  JButton btnUpdate = new JButton("Update");
			  btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnUpdate.setBounds(88, 71, 89, 29);
			  panel_1.add(btnUpdate);
			  
			  JButton btnDelete = new JButton("Delete");
			  btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnDelete.setBounds(181, 71, 89, 29);
			  panel_1.add(btnDelete);
		}
	}
