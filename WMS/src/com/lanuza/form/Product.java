package com.lanuza.form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class Product {

	private JFrame frame;
	private JTextField txtCode;
	private JTextField txtProduct;
	private JTextField txtPrice;
	private JTable table;

	Product() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtSearch;
	
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
			pst = con.prepareStatement("Select * from tblproduct");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1184, 66);
		frame.getContentPane().add(topPanel);
		
		JLabel lblProductSection = new JLabel("Manage Product");
		lblProductSection.setForeground(Color.WHITE);
		lblProductSection.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductSection.setBounds(399, 25, 148, 30);
		topPanel.add(lblProductSection);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 541, 984, 18);
		frame.getContentPane().add(bottomPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 112, 303, 242);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProduct.setBounds(10, 73, 65, 25);
		panel.add(lblProduct);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCode.setColumns(10);
		txtCode.setBounds(99, 32, 182, 29);
		panel.add(txtCode);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCode.setBounds(25, 33, 50, 25);
		panel.add(lblCode);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrice.setBounds(20, 113, 50, 25);
		panel.add(lblPrice);
		
		txtProduct = new JTextField();
		txtProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProduct.setColumns(10);
		txtProduct.setBounds(99, 72, 182, 29);
		panel.add(txtProduct);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(99, 112, 182, 29);
		panel.add(txtPrice);
		
		JButton btnAdd = new JButton("Add");
		 btnAdd.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		
			  		String code,product,price;
			  		code = txtCode.getText();
			  		product = txtProduct.getText();
			  		price = txtPrice.getText();;
					
					try {
						pst = con.prepareStatement("insert into tblproduct(Code,Product,Price)values(?,?,?)");
						pst.setString(1, code);
						pst.setString(2, product);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record added");
						table_load();
						txtCode.setText("");
						txtProduct.setText("");
						txtPrice.setText("");						
						txtCode.requestFocus();
						
					}catch(SQLException el) {
						el.printStackTrace();
					}
			  	}
			  });
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(85, 202, 89, 29);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(178, 202, 89, 29);
		panel.add(btnClear);
		
		JLabel lblCompanyId = new JLabel("CompanyID");
		lblCompanyId.setForeground(Color.BLACK);
		lblCompanyId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCompanyId.setBounds(10, 149, 95, 33);
		panel.add(lblCompanyId);
		
		JComboBox CompanyIdCombox = new JComboBox();
		CompanyIdCombox.setMaximumRowCount(2);
		CompanyIdCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		CompanyIdCombox.setEditable(true);
		CompanyIdCombox.setBounds(115, 152, 166, 28);
		panel.add(CompanyIdCombox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(331, 158, 626, 321);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				txtCode.setText(model.getValueAt(Myindex, 1).toString());
				txtProduct.setText(model.getValueAt(Myindex, 2).toString());
				txtPrice.setText(model.getValueAt(Myindex, 3).toString());			
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductList = new JLabel("Product List");
		lblProductList.setForeground(Color.BLACK);
		lblProductList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductList.setBounds(587, 123, 114, 30);
		frame.getContentPane().add(lblProductList);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard back = new Dashboard();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(20, 490, 89, 29);
		frame.getContentPane().add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(20, 365, 293, 114);
		frame.getContentPane().add(panel_1);
		
		JLabel lblId = new JLabel("ProductID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(10, 27, 76, 33);
		panel_1.add(lblId);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(102, 31, 168, 29);
		panel_1.add(txtSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(88, 71, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(181, 71, 89, 29);
		panel_1.add(btnDelete);
		frame.setVisible(true);
		
		
		int[] code = {};
	}
}
