package com.lanuza.form;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

public class Order extends JFrame{

		private JFrame frame;
		private JButton btnBack;
		private JTable table;
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
		private JTextField txtQTY;

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
			  frame.setSize(1200, 700);
			  frame.getContentPane().setLayout(null);
			  
			  btnBack = new JButton("Back");
			  btnBack.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		frame.dispose();
			  		Dashboard back = new Dashboard();
			  	}
			  });
			  btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnBack.setBounds(23, 592, 89, 29);
			  frame.getContentPane().add(btnBack);
			  
			  JPanel topPanel = new JPanel();
			  topPanel.setBackground(new Color(3, 65, 68));
			  topPanel.setBounds(0, 0, 1184, 66);
			  frame.getContentPane().add(topPanel);
			  topPanel.setLayout(null);
			  
			  JLabel topLabel = new JLabel("Manage Orders");
			  topLabel.setForeground(new Color(255, 255, 255));
			  topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			  topLabel.setBounds(399, 25, 263, 30);
			  topPanel.add(topLabel);
			  
			  JPanel bottomPanel = new JPanel();
			  bottomPanel.setBackground(new Color(3, 65, 68));
			  bottomPanel.setBounds(0, 643, 1184, 18);
			  frame.getContentPane().add(bottomPanel);
			  bottomPanel.setLayout(null);
			  
			  JPanel panel = new JPanel();
			  panel.setBorder(new TitledBorder(null, "Distribution Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel.setBounds(19, 135, 313, 266);
			  frame.getContentPane().add(panel);
			  panel.setLayout(null);
			  
			  JLabel lblCustomerId = new JLabel("CustomerID");
			  lblCustomerId.setForeground(Color.BLACK);
			  lblCustomerId.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblCustomerId.setBounds(8, 44, 91, 33);
			  panel.add(lblCustomerId);
			  
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
			  
			  JComboBox customerIDCombox = new JComboBox();
			  customerIDCombox.setMaximumRowCount(2);
			  customerIDCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
			  customerIDCombox.setEditable(true);
			  customerIDCombox.setBounds(111, 47, 180, 28);
			  panel.add(customerIDCombox);
			  
			  JLabel lblProductId = new JLabel("ProductID");
			  lblProductId.setForeground(Color.BLACK);
			  lblProductId.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblProductId.setBounds(8, 99, 80, 33);
			  panel.add(lblProductId);
			  
			  JComboBox ProductIDCombox_1 = new JComboBox();
			  ProductIDCombox_1.setMaximumRowCount(2);
			  ProductIDCombox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			  ProductIDCombox_1.setEditable(true);
			  ProductIDCombox_1.setBounds(109, 102, 180, 28);
			  panel.add(ProductIDCombox_1);
			  
			  JLabel lblQty = new JLabel("QTY");
			  lblQty.setForeground(Color.BLACK);
			  lblQty.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblQty.setBounds(18, 147, 39, 33);
			  panel.add(lblQty);
			  
			  txtQTY = new JTextField();
			  txtQTY.setColumns(10);
			  txtQTY.setBounds(109, 151, 182, 28);
			  panel.add(txtQTY);
			  
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setBounds(342, 158, 679, 405);
			  frame.getContentPane().add(scrollPane);
			  
			  table = new JTable();
			  scrollPane.setViewportView(table);
			  
			  JPanel panel_1 = new JPanel();
			  panel_1.setBorder(new TitledBorder(null, "Search OrderID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			  panel_1.setBounds(19, 435, 313, 107);
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
			  lblOrderList.setBounds(617, 135, 133, 30);
			  frame.getContentPane().add(lblOrderList);
			  
			  JPanel shortcutPanel = new JPanel();
			  shortcutPanel.setLayout(null);
			  shortcutPanel.setBackground(new Color(3, 65, 68));
			  shortcutPanel.setBounds(1031, 66, 153, 584);
			  frame.getContentPane().add(shortcutPanel);
			  
			  JPanel panel_3 = new JPanel();
			  panel_3.setOpaque(false);
			  panel_3.setBorder(new LineBorder(new Color(255, 255, 255)));
			  panel_3.setBounds(10, 11, 133, 543);
			  shortcutPanel.add(panel_3);
			  
			  JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
			  lblNewLabel_1.setForeground(Color.WHITE);
			  lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
			  panel_3.add(lblNewLabel_1);
			  
			  JLabel lblTotalPrice = new JLabel("Total Price:");
			  lblTotalPrice.setFont(new Font("Arial Black", Font.BOLD, 12));
			  lblTotalPrice.setBounds(793, 570, 82, 30);
			  frame.getContentPane().add(lblTotalPrice);
			  
			  JLabel lblPriceIndicator = new JLabel("0");
			  lblPriceIndicator.setHorizontalAlignment(SwingConstants.LEFT);
			  lblPriceIndicator.setFont(new Font("Tahoma", Font.BOLD, 14));
			  lblPriceIndicator.setBounds(885, 570, 136, 29);
			  frame.getContentPane().add(lblPriceIndicator);
			  
			  JLabel lblDateReceived = new JLabel("Date Received:");
			  lblDateReceived.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblDateReceived.setBounds(901, 86, 113, 25);
			  frame.getContentPane().add(lblDateReceived);
			  
			  JLabel Datelbl = new JLabel("2023-11-14");
			  Datelbl.setForeground(Color.BLACK);
			  Datelbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			  Datelbl.setBackground(Color.WHITE);
			  Datelbl.setBounds(925, 107, 89, 25);
			  frame.getContentPane().add(Datelbl);
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

