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
		private JButton backBtn;
		private JTextField nametxt;
		private JTable table;
		
		Supplier() {
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
			  
			  backBtn = new JButton("Back");
			  backBtn.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		frame.dispose();
			  		Inventory back = new Inventory();
			  	}
			  });
			  backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			  backBtn.setBounds(860, 490, 89, 29);
			  frame.getContentPane().add(backBtn);
			  
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
			  panel.setBounds(38, 141, 384, 249);
			  frame.getContentPane().add(panel);
			  
			  JLabel lblName = new JLabel("Name");
			  lblName.setForeground(Color.BLACK);
			  lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
			  lblName.setBounds(32, 81, 51, 33);
			  panel.add(lblName);
			  
			  nametxt = new JTextField();
			  nametxt.setColumns(10);
			  nametxt.setBounds(93, 85, 203, 28);
			  panel.add(nametxt);
			  
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setBounds(488, 140, 461, 310);
			  frame.getContentPane().add(scrollPane);
			  
			  table = new JTable();
			  table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						int Myindex = table.getSelectedRow();
						nametxt.setText(model.getValueAt(Myindex, 1).toString());							
					}
				});
			  
			  scrollPane.setViewportView(table);
			  
			  JButton btnAdd = new JButton("Add");
			  btnAdd.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
				  		
				  		String name;
				  		name = nametxt.getText();
						
						try {
							pst = con.prepareStatement("insert into tblsupplier(Name)values(?)");
							pst.setString(1, name); 
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record added");
							table_load();						
							nametxt.setText("");
							nametxt.requestFocus();
							
						}catch(SQLException el) {
							el.printStackTrace();
						}
				  	}
				  });
			  btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnAdd.setBounds(32, 160, 89, 29);
			  panel.add(btnAdd);
			  
			  JButton btnClear = new JButton("Clear");
			  btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnClear.setBounds(236, 160, 89, 29);
			  panel.add(btnClear);
			  
			  JButton btnUpdate = new JButton("Update");
			  btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnUpdate.setBounds(32, 209, 89, 29);
			  panel.add(btnUpdate);
			  
			  JButton btnDelete = new JButton("Delete");
			  btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
			  btnDelete.setBounds(236, 209, 89, 29);
			  panel.add(btnDelete);
			  
			  JLabel lblSupplierList = new JLabel("Supplier List");
			  lblSupplierList.setForeground(Color.BLACK);
			  lblSupplierList.setFont(new Font("Tahoma", Font.BOLD, 18));
			  lblSupplierList.setBounds(649, 102, 134, 30);
			  frame.getContentPane().add(lblSupplierList);
		}
	}
