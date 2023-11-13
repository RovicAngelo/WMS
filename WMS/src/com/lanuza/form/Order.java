package com.lanuza.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.*;

import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class Order extends JFrame{

		private JFrame frame;
		private JButton btnBack;
		private JTable table;
		private JTextField txtName;
		private JTextField txtQty;
		private JTextField txtOrderId;
		
			Order() {
			initialize();
			Connect();
			table_load();
		}
		
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		Statement st;

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
				pst = con.prepareStatement("Select * from tblorder");
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
			  frame.getContentPane().setLayout(null);
			  
			  btnBack = new JButton("Back");
			  btnBack.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		frame.dispose();
			  		Dashboard back = new Dashboard();
			  	}
			  });
			  btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnBack.setBounds(31, 503, 89, 29);
			  frame.getContentPane().add(btnBack);
			  
			  JPanel topPanel = new JPanel();
			  topPanel.setBackground(new Color(3, 65, 68));
			  topPanel.setBounds(0, 0, 984, 66);
			  frame.getContentPane().add(topPanel);
			  topPanel.setLayout(null);
			  
			  JLabel topLabel = new JLabel("Manage Orders");
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
			  panel.setBorder(new TitledBorder(null, "Distribution Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel.setBounds(10, 112, 313, 262);
			  frame.getContentPane().add(panel);
			  panel.setLayout(null);
			  
			  JLabel lblName = new JLabel("Name");
			  lblName.setForeground(Color.BLACK);
			  lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblName.setBounds(23, 33, 51, 33);
			  panel.add(lblName);
			  
			  JLabel lblQty = new JLabel("Qty");
			  lblQty.setForeground(Color.BLACK);
			  lblQty.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblQty.setBounds(23, 80, 40, 33);
			  panel.add(lblQty);
			  
			  JLabel lblCustomerid = new JLabel("CustomerID");
			  lblCustomerid.setForeground(Color.BLACK);
			  lblCustomerid.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblCustomerid.setBounds(10, 132, 91, 33);
			  panel.add(lblCustomerid);
			  
			  JLabel lblDate = new JLabel("Date");
			  lblDate.setForeground(Color.BLACK);
			  lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblDate.setBounds(23, 176, 51, 33);
			  panel.add(lblDate);
			  
			  txtName = new JTextField();
			  txtName.setColumns(10);
			  txtName.setBounds(109, 37, 182, 28);
			  panel.add(txtName);
			  
			  txtQty = new JTextField();
			  txtQty.setColumns(10);
			  txtQty.setBounds(109, 84, 182, 28);
			  panel.add(txtQty);
			  
			  JButton btnAdd = new JButton("Add");
			  btnAdd.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		try {
			  			//pst = con.prepareStatement("insert into tblorder(Username,Password,Type)values(?,?,?)");
			  		}catch(Exception exc) {
			  			
			  		}
			  		
			  	}
			  });
			  btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnAdd.setBounds(121, 220, 80, 29);
			  panel.add(btnAdd);
			  
			  JButton btnClear = new JButton("Clear");
			  btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnClear.setBounds(211, 220, 80, 29);
			  panel.add(btnClear);
			  
			  JDateChooser dateChooser = new JDateChooser();
			  dateChooser.setBounds(109, 176, 182, 33);
			  panel.add(dateChooser);
			  
			  JComboBox customerIDCombox = new JComboBox();
			  customerIDCombox.setMaximumRowCount(2);
			  customerIDCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			  customerIDCombox.setEditable(true);
			  customerIDCombox.setBounds(111, 132, 180, 28);
			  panel.add(customerIDCombox);
			  
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setBounds(341, 158, 622, 353);
			  frame.getContentPane().add(scrollPane);
			  
			  table = new JTable();
			  scrollPane.setViewportView(table);
			  
			  JPanel panel_1 = new JPanel();
			  panel_1.setBorder(new TitledBorder(null, "Search OrderID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel_1.setBounds(10, 380, 313, 107);
			  frame.getContentPane().add(panel_1);
			  panel_1.setLayout(null);
			  
			  JLabel lblOrderId = new JLabel("OrderID");
			  lblOrderId.setForeground(Color.BLACK);
			  lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblOrderId.setBounds(10, 23, 74, 33);
			  panel_1.add(lblOrderId);
			  
			  txtOrderId = new JTextField();
			  txtOrderId.setColumns(10);
			  txtOrderId.setBounds(94, 27, 199, 28);
			  panel_1.add(txtOrderId);
			  
			  JButton btnUpdate = new JButton("Update");
			  btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnUpdate.setBounds(104, 66, 89, 29);
			  panel_1.add(btnUpdate);
			  
			  JButton btnDelete = new JButton("Delete");
			  btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnDelete.setBounds(204, 66, 89, 29);
			  panel_1.add(btnDelete);
			  
			  JLabel lblOrderList = new JLabel("Order List");
			  lblOrderList.setForeground(Color.BLACK);
			  lblOrderList.setFont(new Font("Tahoma", Font.BOLD, 18));
			  lblOrderList.setBounds(636, 117, 133, 30);
			  frame.getContentPane().add(lblOrderList);
			  frame.setLocationRelativeTo(null);
			  frame.setVisible(true); // this make Frame visible
		}

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnBack) {
				this.dispose();
				Dashboard back = new Dashboard();
			}
			
		}
	}

