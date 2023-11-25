package com.lanuza.form;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import javax.swing.border.BevelBorder;

public class Stock {

	private JFrame frame;
	private JTable table;
	private JLabel txtGrossTotal;
	String nameQuery, supplierQuery;
	int priceQuery;
	
	Stock() {
		initialize();
		Connect();
		table_load();
		getGrossTotal();
	}
	
	Connection con = null;
	PreparedStatement pst;
	ResultSet rs= null;
	Statement st= null;
	
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			rs = st.executeQuery("Select ProductCode, MAX(ProductDescription) AS ProductDescription, MAX(ProductPrice) AS ProductPrice,SUM(Qty) AS Quantity,SUM(Total) AS Total from phildrinksdb.tblstock GROUP BY ProductCode");		
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	void getGrossTotal() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phildrinksdb","root","114547");
			st = con.createStatement();
			String sumOfTotal = "select SUM(Total) FROM tblstock ";
			pst=con.prepareStatement(sumOfTotal);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				String sum = rs.getString("SUM(Total)");
				txtGrossTotal.setText(sum);					
			}	
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	int temp = 0;
	private JTextField txtSearchBy;
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setSize(1350,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTable3 = new JPanel();
		panelTable3.setBackground(new Color(226, 226, 226));
		panelTable3.setLayout(null);
		panelTable3.setBounds(76, 581, 1046, 57);
		frame.getContentPane().add(panelTable3);
		
		txtGrossTotal = new JLabel("0");
		txtGrossTotal.setBounds(877, 0, 159, 38);
		panelTable3.add(txtGrossTotal);
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setBackground(new Color(255, 255, 255));
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setBounds(761, 5, 116, 33);
		panelTable3.add(lblGrossTotal);
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(new Color(3, 65, 68));
		topPanel.setBounds(0, 0, 1370, 54);
		frame.getContentPane().add(topPanel);
		
		JLabel topLabel = new JLabel("Stock");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setBounds(583, 11, 167, 30);
		topPanel.add(topLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 641, 1370, 20);
		frame.getContentPane().add(bottomPanel);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(1164, 54, 170, 607);
		frame.getContentPane().add(sidePanel2);
		sidePanel2.setLayout(null);
		
		JPanel panelShortcut1 = new JPanel();
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setOpaque(false);
		panelShortcut1.setBounds(10, 39, 137, 491);
		sidePanel2.add(panelShortcut1);
		panelShortcut1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setBounds(36, 6, 78, 16);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panelShortcut1.add(lblNewLabel_1);
		
		JLabel lblCtrlS = new JLabel("Ctrl+S");
		lblCtrlS.setForeground(new Color(255, 255, 255));
		lblCtrlS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlS.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlS.setBounds(0, 39, 58, 28);
		panelShortcut1.add(lblCtrlS);
		
		JLabel lblCtrlP = new JLabel("Ctrl+P");
		lblCtrlP.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlP.setForeground(Color.WHITE);
		lblCtrlP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlP.setBounds(0, 78, 58, 28);
		panelShortcut1.add(lblCtrlP);
		
		JLabel lblS = new JLabel("S");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblS.setBounds(0, 127, 58, 28);
		panelShortcut1.add(lblS);
		
		JLabel lblM = new JLabel("P");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 176, 58, 28);
		panelShortcut1.add(lblM);
		
		JLabel lblM_1 = new JLabel("M");
		lblM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblM_1.setForeground(Color.WHITE);
		lblM_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM_1.setBounds(0, 229, 58, 28);
		panelShortcut1.add(lblM_1);
		
		JTextArea txtrCtrlS = new JTextArea();
		txtrCtrlS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlS.setForeground(new Color(255, 255, 255));
		txtrCtrlS.setOpaque(false);
		txtrCtrlS.setText("Save as\r\n File");
		txtrCtrlS.setBounds(65, 33, 82, 38);
		panelShortcut1.add(txtrCtrlS);
		
		JTextArea txtrCtrlP = new JTextArea();
		txtrCtrlP.setText("Print");
		txtrCtrlP.setOpaque(false);
		txtrCtrlP.setForeground(Color.WHITE);
		txtrCtrlP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlP.setBounds(65, 82, 82, 28);
		panelShortcut1.add(txtrCtrlP);
		
		JTextArea txtrS = new JTextArea();
		txtrS.setText("Go to \r\nSuppliers");
		txtrS.setOpaque(false);
		txtrS.setForeground(Color.WHITE);
		txtrS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrS.setBounds(68, 121, 82, 38);
		panelShortcut1.add(txtrS);
		
		JTextArea txtrP = new JTextArea();
		txtrP.setText("Go to \r\nProducts");
		txtrP.setOpaque(false);
		txtrP.setForeground(Color.WHITE);
		txtrP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrP.setBounds(65, 172, 82, 38);
		panelShortcut1.add(txtrP);
		
		JTextArea txtrM = new JTextArea();
		txtrM.setText("Change Mode\r\n(Light/Dark)");
		txtrM.setOpaque(false);
		txtrM.setForeground(Color.WHITE);
		txtrM.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrM.setBounds(46, 221, 82, 38);
		panelShortcut1.add(txtrM);
		
		JPanel sidePanel1 = new JPanel();
		sidePanel1.setLayout(null);
		sidePanel1.setBackground(new Color(3, 65, 68));
		sidePanel1.setBounds(0, 54, 35, 684);
		frame.getContentPane().add(sidePanel1);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBackground(new Color(64, 128, 128));
		panelButtons.setBounds(34, 54, 1129, 38);
		frame.getContentPane().add(panelButtons);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnBack.setToolTipText("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Dashboard goToDashboard = new Dashboard(); 
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBounds(0, 0, 63, 38);
		panelButtons.add(btnBack);
		
		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table.print();					
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnPrint.setToolTipText("Print");
		btnPrint.setFocusPainted(false);
		btnPrint.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setBackground(new Color(64, 128, 128));
		btnPrint.setBounds(126, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		JButton btnSaveFile = new JButton("");
		btnSaveFile.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnSaveFile.setToolTipText("Save as file");
		btnSaveFile.setFocusPainted(false);
		btnSaveFile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setBackground(new Color(64, 128, 128));
		btnSaveFile.setBounds(63, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		JButton btnMode = new JButton("");
		btnMode.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/stock.png")));
		btnMode.setToolTipText("Mode(Light/Dark)");
		btnMode.setFocusPainted(false);
		btnMode.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setBackground(new Color(64, 128, 128));
		btnMode.setBounds(189, 0, 63, 38);
		panelButtons.add(btnMode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 252, 1046, 332);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panelTable4 = new JPanel();
		panelTable4.setBackground(new Color(226, 226, 226));
		panelTable4.setBounds(34, 249, 42, 389);
		frame.getContentPane().add(panelTable4);
		
		JPanel panelTable2 = new JPanel();
		panelTable2.setBackground(new Color(226, 226, 226));
		panelTable2.setBounds(1121, 247, 42, 403);
		frame.getContentPane().add(panelTable2);
		
		JPanel panelTable1 = new JPanel();
		panelTable1.setBackground(new Color(226, 226, 226));
		panelTable1.setBounds(34, 93, 1129, 159);
		frame.getContentPane().add(panelTable1);
		panelTable1.setLayout(null);
		
		txtSearchBy = new JTextField();
		txtSearchBy.setToolTipText("Search by...");
		txtSearchBy.setColumns(10);
		txtSearchBy.setBounds(37, 115, 287, 33);
		panelTable1.add(txtSearchBy);
		
		JButton btnSearchBy = new JButton("");
		btnSearchBy.setIcon(new ImageIcon(ReceivingModern.class.getResource("/com/lanuza/icons/search.png")));
		btnSearchBy.setToolTipText("Add");
		btnSearchBy.setFocusPainted(false);
		btnSearchBy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setBackground(new Color(243, 243, 243));
		btnSearchBy.setBounds(325, 115, 63, 33);
		panelTable1.add(btnSearchBy);
		
	}
}
