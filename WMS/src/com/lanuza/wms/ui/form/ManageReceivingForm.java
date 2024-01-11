package com.lanuza.wms.ui.form;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;

import com.lanuza.wms.dao.ReceivingEntryDAO;
import com.lanuza.wms.dao.impl.ReceivingEntryDAOImpl;
import com.lanuza.wms.model.PurchasedOrder;
import com.lanuza.wms.model.ReceivingEntry;
import com.lanuza.wms.service.ReceivingEntryService;
import com.lanuza.wms.service.impl.ReceivingEntryServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ManageReceivingForm extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ReceivingEntryDAO receivingEntryDAO;
	private ReceivingEntryService receivingEntryService;
	
	private JTextField textField,txtReceivingId,txtQuantity;
	JLabel lblCurrentDate,txtGrossTotal;
	private Table table;
	JComboBox<String> productNameCombox;
	JDateChooser expDateChooser;
	
	String supplierQuery;
	double priceQuery;
	java.util.Date EDate;
	java.sql.Date MyExpDate;


	public ManageReceivingForm() {
		this.receivingEntryDAO = new ReceivingEntryDAOImpl();
		this.receivingEntryService = new ReceivingEntryServiceImpl(receivingEntryDAO); 
		
		this.productNameCombox = new JComboBox<String>();
		this.txtQuantity = new JTextField();
		initialize();
		loadData();
		getDateToday();
		displayGrossTotal();
		populateProductCombox();
	}
	private void initialize() {
		setLayout(null);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setBackground(new Color(3, 65, 68));
		panelButtons.setBounds(0, 46, 1097, 38);
		add(panelButtons);
		
		CustomButton btnProcess = new CustomButton(new Color(64, 128, 128), "Process Orders", this::reflectReceivingEntryToStock, new Rectangle(0, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProcess.setText("Process");
		btnProcess.setForeground(Color.WHITE);
		btnProcess.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnProcess.setBackground(new Color(3, 65, 68));
		btnProcess.setBounds(0, 0, 63, 38);
		panelButtons.add(btnProcess);
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setText("Print");
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPrint.setBackground(new Color(3, 65, 68));
		btnPrint.setBounds(62, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		CustomButton btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setText("Save");
		btnSaveFile.setForeground(Color.WHITE);
		btnSaveFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSaveFile.setBackground(new Color(3, 65, 68));
		btnSaveFile.setBounds(124, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", this::ChangeMode, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setText("Mode");
		btnMode.setForeground(Color.WHITE);
		btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMode.setBackground(new Color(3, 65, 68));
		btnMode.setBounds(186, 0, 63, 38);
		panelButtons.add(btnMode);
		
		textField = new JTextField();
		textField.setToolTipText("Search by...");
		textField.setColumns(10);
		textField.setBounds(20, 112, 304, 33);
		add(textField);
		
		JLabel lblReceivingSection = new JLabel("Receiving Section");
		lblReceivingSection.setForeground(Color.BLACK);
		lblReceivingSection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReceivingSection.setBounds(0, 0, 210, 29);
		add(lblReceivingSection);
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setRound(10);
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setBackground(Color.WHITE);
		roundPanel.setBounds(20, 156, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		
		JLabel lblReceivingId = new JLabel("Receiving Id");
		lblReceivingId.setForeground(Color.BLACK);
		lblReceivingId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtReceivingId = new JTextField();
		txtReceivingId.setToolTipText("Id");
		txtReceivingId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtReceivingId.setEditable(false);
		txtReceivingId.setColumns(10);
		txtReceivingId.setBackground(SystemColor.menu);
		
		CustomButton btnDelete = new CustomButton(new Color(243, 243, 243), "Delete", this::deleteRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("Delete");
		btnDelete.setText("Delete");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtReceivingId.getText().toString().equals("")) {
			 		JOptionPane.showMessageDialog(null, "Please select a row to enable editing");
			 	}else {
				showUpdateReceivingEntryPopup();
			 	}
			}
		});
		btnEdit.setToolTipText("Edit Row");
		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEdit.setBackground(new Color(243, 243, 243));
		
		JButton btnNewReceivingEntry = new JButton("New Receiving");
		btnNewReceivingEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreateReceivingEntryPopup();
			}
		});
		btnNewReceivingEntry.setToolTipText("Add");
		btnNewReceivingEntry.setFocusPainted(false);
		btnNewReceivingEntry.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewReceivingEntry.setBackground(new Color(243, 243, 243));
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		txtGrossTotal = new JLabel("0.0");
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setBackground(Color.WHITE);
		GroupLayout gl_roundPanel = new GroupLayout(roundPanel);
		gl_roundPanel.setHorizontalGroup(
			gl_roundPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addContainerGap(567, Short.MAX_VALUE)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblReceivingId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtReceivingId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewReceivingEntry, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblReceivingId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtReceivingId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewReceivingEntry, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new Table();
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	            	tableRowSelection();
	            }
	        });
		scrollPane.setViewportView(table);
		roundPanel.setLayout(gl_roundPanel);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageReceivingForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(324, 112, 68, 33);
		add(btnSearchBy);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(743, 109, 40, 33);
		add(lblDate);
		
		lblCurrentDate = new JLabel("2023-12-11");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(785, 105, 93, 40);
		add(lblCurrentDate);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(927, 83, 170, 581);
		add(sidePanel2);
		
		JPanel panelShortcut1 = new JPanel();
		panelShortcut1.setLayout(null);
		panelShortcut1.setOpaque(false);
		panelShortcut1.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelShortcut1.setBounds(10, 39, 137, 491);
		sidePanel2.add(panelShortcut1);
		
		JLabel lblNewLabel_1 = new JLabel("SHORTCUTS");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(36, 6, 78, 16);
		panelShortcut1.add(lblNewLabel_1);
		
		JLabel lblCtrlS = new JLabel("Ctrl+S");
		lblCtrlS.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlS.setForeground(Color.WHITE);
		lblCtrlS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlS.setBounds(0, 39, 58, 28);
		panelShortcut1.add(lblCtrlS);
		
		JLabel lblCtrlP = new JLabel("Ctrl+P");
		lblCtrlP.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlP.setForeground(Color.WHITE);
		lblCtrlP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlP.setBounds(0, 78, 58, 28);
		panelShortcut1.add(lblCtrlP);
		
		JLabel lblCtrlD = new JLabel("Ctrl+D");
		lblCtrlD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlD.setForeground(Color.WHITE);
		lblCtrlD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCtrlD.setBounds(0, 117, 58, 28);
		panelShortcut1.add(lblCtrlD);
		
		JLabel lblS = new JLabel("M");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblS.setBounds(0, 168, 58, 28);
		panelShortcut1.add(lblS);
		
		JLabel lblM = new JLabel("B");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblM.setBounds(0, 213, 58, 28);
		panelShortcut1.add(lblM);
		
		JTextArea txtrCtrlS = new JTextArea();
		txtrCtrlS.setText("Save as\r\n File");
		txtrCtrlS.setOpaque(false);
		txtrCtrlS.setForeground(Color.WHITE);
		txtrCtrlS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlS.setBounds(65, 33, 82, 38);
		panelShortcut1.add(txtrCtrlS);
		
		JTextArea txtrCtrlP = new JTextArea();
		txtrCtrlP.setText("Print");
		txtrCtrlP.setOpaque(false);
		txtrCtrlP.setForeground(Color.WHITE);
		txtrCtrlP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlP.setBounds(65, 82, 82, 28);
		panelShortcut1.add(txtrCtrlP);
		
		JTextArea txtrCtrlD = new JTextArea();
		txtrCtrlD.setText("Save to \r\nDatabase");
		txtrCtrlD.setOpaque(false);
		txtrCtrlD.setForeground(Color.WHITE);
		txtrCtrlD.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrCtrlD.setBounds(65, 116, 82, 38);
		panelShortcut1.add(txtrCtrlD);
		
		JTextArea txtrS = new JTextArea();
		txtrS.setText("Change Mode\r\nLight/Dark");
		txtrS.setOpaque(false);
		txtrS.setForeground(Color.WHITE);
		txtrS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrS.setBounds(54, 161, 82, 38);
		panelShortcut1.add(txtrS);
		
		JTextArea txtrP = new JTextArea();
		txtrP.setText("Go to \r\nDashboard");
		txtrP.setOpaque(false);
		txtrP.setForeground(Color.WHITE);
		txtrP.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrP.setBounds(65, 206, 82, 38);
		panelShortcut1.add(txtrP);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 644, 1097, 20);
		add(bottomPanel);

	}
	 private void showCreateReceivingEntryPopup() {		 	
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Place New Receiving");
	        createProductDialog.setSize( 420, 305);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel receivingAddPanel = new JPanel();
	       // receivingAddPanel.setBounds(343, 232, 397, 264);
	        receivingAddPanel.setLayout(null);
	        	       	        
	        txtQuantity = new JTextField();
	        txtQuantity.setToolTipText("Qty");
	        txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtQuantity.setColumns(10);
	        txtQuantity.setBounds(125, 42, 219, 31);
	        receivingAddPanel.add(txtQuantity);
	           
	        JLabel lblQuantity = new JLabel("Quantity");
	        lblQuantity.setForeground(Color.BLACK);
	        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblQuantity.setBounds(51, 43, 63, 29);
	        receivingAddPanel.add(lblQuantity);       
	        
	        productNameCombox = new JComboBox<String>();
	        productNameCombox.setBounds(124, 95, 220, 31);
	        receivingAddPanel.add(productNameCombox);
	        productNameCombox.setToolTipText("Code");
	        productNameCombox.setMaximumRowCount(2);
	        productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
	        productNameCombox.setEditable(true);
	        
	        JLabel lblProduct = new JLabel("Product");
	        lblProduct.setBounds(60, 96, 51, 29);
	        receivingAddPanel.add(lblProduct);
	        lblProduct.setForeground(Color.BLACK);
	        lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
	        
	        JLabel lblExpiryDate = new JLabel("Expiry Date");
	        lblExpiryDate.setBounds(41, 152, 74, 29);
	        receivingAddPanel.add(lblExpiryDate);
	        lblExpiryDate.setForeground(Color.BLACK);
	        lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 13));
	        
	        expDateChooser = new JDateChooser();
	        expDateChooser.setBounds(125, 151, 173, 31);
	        receivingAddPanel.add(expDateChooser);
	        expDateChooser.setToolTipText("Date");
	        expDateChooser.setDateFormatString("d MM yyyy");
	        
	        JLabel lblDashboard = new JLabel("Dashboard");
	        lblDashboard.setForeground(Color.BLACK);
	        lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 20));
	        lblDashboard.setBounds(0, 0, 170, 29);
	        add(lblDashboard);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Update", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(208, 203, 63, 38);
	        receivingAddPanel.add(btnClear);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Update", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(281, 203, 63, 38);
	        receivingAddPanel.add(btnCancel);
	        
	        CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Update", this::addRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnAdd.setToolTipText("Add");
	        btnAdd.setText("Add");
	        btnAdd.setBounds(135, 203, 63, 38);
	        receivingAddPanel.add(btnAdd);

	        // Add action listener to the Save button
	        btnAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation (you can customize this part)
	                // For simplicity, just dispose the dialog in this example
	                createProductDialog.dispose();
	            }
	        });

	        // Add action listener to the Cancel button
	        btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Close the dialog when Cancel is clicked
	                createProductDialog.dispose();
	            }
	        });

	        // Add the popupPanel to the dialog's content pane
	        createProductDialog.getContentPane().add(receivingAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        populateProductCombox();
	    }
	 
	 private void showUpdateReceivingEntryPopup() {		 	
	        // Create a JDialog for the popup
	        JDialog createProductDialog = new JDialog();
	        createProductDialog.setTitle("Place New Receiving");
	        createProductDialog.setSize( 420, 305);
	        createProductDialog.setLocationRelativeTo(null); // Center the dialog on the screen
	        
	        JPanel receivingAddPanel = new JPanel();
	        //receivingAddPanel.setBounds(343, 232, 397, 264);
	        //add(receivingAddPanel);
	        receivingAddPanel.setLayout(null);
	        	       	        
	        txtQuantity = new JTextField();
	        txtQuantity.setToolTipText("Qty");
	        txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtQuantity.setColumns(10);
	        txtQuantity.setBounds(125, 42, 219, 31);
	        receivingAddPanel.add(txtQuantity);
	           
	        JLabel lblQuantity = new JLabel("Quantity");
	        lblQuantity.setForeground(Color.BLACK);
	        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
	        lblQuantity.setBounds(51, 43, 63, 29);
	        receivingAddPanel.add(lblQuantity);       
	        
	        productNameCombox = new JComboBox<String>();
	        productNameCombox.setBounds(124, 95, 220, 31);
	        receivingAddPanel.add(productNameCombox);
	        productNameCombox.setToolTipText("Code");
	        productNameCombox.setMaximumRowCount(2);
	        productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
	        productNameCombox.setEditable(true);
	        
	        JLabel lblProduct = new JLabel("Product");
	        lblProduct.setBounds(60, 96, 51, 29);
	        receivingAddPanel.add(lblProduct);
	        lblProduct.setForeground(Color.BLACK);
	        lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
	        
	        JLabel lblExpiryDate = new JLabel("Expiry Date");
	        lblExpiryDate.setBounds(41, 152, 74, 29);
	        receivingAddPanel.add(lblExpiryDate);
	        lblExpiryDate.setForeground(Color.BLACK);
	        lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 13));
	        
	        expDateChooser = new JDateChooser();
	        expDateChooser.setBounds(125, 151, 173, 31);
	        receivingAddPanel.add(expDateChooser);
	        expDateChooser.setToolTipText("Date");
	        expDateChooser.setDateFormatString("d MM yyyy");
	        
	        JLabel lblDashboard = new JLabel("Dashboard");
	        lblDashboard.setForeground(Color.BLACK);
	        lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 20));
	        lblDashboard.setBounds(0, 0, 170, 29);
	        add(lblDashboard);
	        
	        CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Clear", this::clearFields, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnClear.setToolTipText("Clear");
	        btnClear.setText("Clear");
	        btnClear.setBounds(208, 203, 63, 38);
	        receivingAddPanel.add(btnClear);
	        
	        CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Cancel", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnCancel.setToolTipText("Cancel");
	        btnCancel.setText("Cancel");
	        btnCancel.setBounds(281, 203, 63, 38);
	        receivingAddPanel.add(btnCancel);
	        
	        CustomButton btnUpdate = new CustomButton(new Color(243, 243, 243), "Update", this::updateRow, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	        btnUpdate.setToolTipText("Add");
	        btnUpdate.setText("Add");
	        btnUpdate.setBounds(135, 203, 63, 38);
	        receivingAddPanel.add(btnUpdate);

	        // Add action listener to the Save button
	        btnUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Perform save operation (you can customize this part)
	                // For simplicity, just dispose the dialog in this example
	                createProductDialog.dispose();
	            }
	        });

	        // Add action listener to the Cancel button
	        btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent el) {
	                // Close the dialog when Cancel is clicked
	                createProductDialog.dispose();
	            }
	        });

	        // Add the popupPanel to the dialog's content pane
	        createProductDialog.getContentPane().add(receivingAddPanel);

	        // Set the dialog to be visible
	        createProductDialog.setVisible(true);
	        populateProductCombox();
	    }
	 
	 private void printTable(ActionEvent e) {
		 //print the order table
		 try {
				table.print();					
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}	
	 private void reflectReceivingEntryToStock(ActionEvent e) {		
		 //subtract the table order values to table stock value based on productname
		 receivingEntryService.reflectReceivingEntryToStock();		
		 //load table data
		 loadData();
		}	
	 
	 private void addRow(ActionEvent e) {
			String selectedProduct;
	  		int qty;
	  		double tot;	
	  		Date currentDate;
	  		 // Parsing the string date to java.sql.Date
		    String currentDateStr = lblCurrentDate.getText();
		    try {
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date javaUtilDate = sdf.parse(currentDateStr);
		        currentDate = new java.sql.Date(javaUtilDate.getTime());
		    } catch (ParseException ex) {
		        ex.printStackTrace();
		        // Handle the ParseException
		        return; // exit the method if date parsing fails
		    }
	  		//select productname 
	  		selectedProduct = productNameCombox.getSelectedItem().toString();			
	  		qty = Integer.parseInt(txtQuantity.getText());	
	  		//for the exp date 
	  		EDate = expDateChooser.getDate(); 
	  		MyExpDate = new java.sql.Date(EDate.getTime());	
	  					
			if (selectedProduct.isEmpty() && txtReceivingId.getText().isEmpty() && txtQuantity.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Missing information!");
			}else {			  					  		  																	  			  						 		  		
				Map<String, Object> result = receivingEntryService.getAvailabilityAndPriceByProductDescription(selectedProduct);
		        //get the availablity and price from product
		        priceQuery = (double) result.get("ProductPrice");
		        supplierQuery = (String) result.get("Supplier");										
			
				tot = qty * priceQuery;	  		
			  									
				//to insert the value encoded by the user into the database
			  	ReceivingEntry receivingEntry = new ReceivingEntry(selectedProduct,priceQuery,qty,tot,MyExpDate,supplierQuery,currentDate);
			  	receivingEntryService.addReceivingEntry(receivingEntry);					
				//diplay gross total to label
				displayGrossTotal();
				//load table data
				loadData();	
				//clear fields
				productNameCombox.setSelectedItem("");
				expDateChooser.setDate(null);
				txtQuantity.setText("");	
				txtReceivingId.setText("");
				productNameCombox.requestFocus();	        						
						
			}
		
		}

	 private void clearFields(ActionEvent e) {
		 // Clearfields
		 	productNameCombox.setSelectedItem("");
			expDateChooser.setDate(null);
			txtQuantity.setText("");	
			txtReceivingId.setText("");
			productNameCombox.requestFocus();	      
	    }
	 private void deleteRow(ActionEvent e) {
		//ensure that fields are not empty				
			if(txtReceivingId.getText().isEmpty()) {
	  			JOptionPane.showMessageDialog(null,"Select a receive item to be deleted");
	  		}else {
	  			//get the id from fields
				int id = Integer.parseInt(txtReceivingId.getText());
				
				//execute delete method
				receivingEntryService.deleteReceivingEntry(id);
				
				//diplay gross total
				displayGrossTotal();
				
				//load table data
				loadData();	
				
				//clear fields
				productNameCombox.setSelectedItem("");
				expDateChooser.setDate(null);
				txtQuantity.setText("");	
				txtReceivingId.setText("");
				productNameCombox.requestFocus();					
	  	  }	
	 	}
	 private void updateRow(ActionEvent e) {
		 int oldqty,id;
			double oldprice,newtotal;
			String selectedProduct = productNameCombox.getSelectedItem().toString();	
			//to get the receiving id of selected row			
			id = Integer.parseInt(txtReceivingId.getText());						 	  				
			oldqty = Integer.parseInt(txtQuantity.getText());//to get the current qty in the textfield		
			
			Date currentDate;
	  		 // Parsing the string date to java.sql.Date
		    String currentDateStr = lblCurrentDate.getText();
		    try {
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date javaUtilDate = sdf.parse(currentDateStr);
		        currentDate = new java.sql.Date(javaUtilDate.getTime());
		    } catch (ParseException ex) {
		        ex.printStackTrace();
		        // Handle the ParseException
		        return; // exit the method if date parsing fails
		    }
		    
	  		EDate = expDateChooser.getDate();
	  		MyExpDate = new java.sql.Date(EDate.getTime());		
	  		
	        if (id == -1) {
	            JOptionPane.showMessageDialog(null, "Please select a row to update.");
	            return;
	        }else {
	        	if(selectedProduct.isEmpty() && txtReceivingId.getText().isEmpty() && txtQuantity.getText().isEmpty()) {
		  			JOptionPane.showMessageDialog(null,"Missing information!");
		  		}else {	 
		  			Map<String, Object> result = receivingEntryService.getAvailabilityAndPriceByProductDescription(selectedProduct);
			        //get the availablity and price from product
			        priceQuery = (double) result.get("ProductPrice");
			        supplierQuery = (String) result.get("Supplier");		  				
					
					oldprice = priceQuery;//to get the current price in the textfield
					newtotal = oldqty * oldprice; //to set the updated total by multiplying the current qty and price 
								
					ReceivingEntry receivingEntry = new ReceivingEntry(selectedProduct,priceQuery,oldqty,newtotal,MyExpDate,supplierQuery,currentDate,id);
					receivingEntryService.updateReceivingEntry(receivingEntry);
	  				displayGrossTotal();
					loadData();	
					
					txtReceivingId.setText("");
					productNameCombox.setSelectedItem("");
					txtQuantity.setText("");
					expDateChooser.setDate(null);
					txtReceivingId.requestFocus();	
		  		}
	        }
	 	}
	 private void saveAsFile(ActionEvent e) {
		// Logic for saving as a file 
		}
	 private void ChangeMode(ActionEvent e) {
		// Logic for toggling between Light/Dark mode
		}
	 
	 private void getDateToday() { //method to get the date today
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();		
			lblCurrentDate.setText(dtf.format(now));
		}
		//to load the data in the table
		void loadData() {
			receivingEntryService.tableLoad(table);
		}
		private void displayGrossTotal() {
			 double sumOfTotal = receivingEntryService.getSumOfTotal();
		        // Update UI component (e.g., setText on a JTextField)
		     txtGrossTotal.setText(String.valueOf(sumOfTotal));
		}  
		//to populate the productNameCombox with Desription attribute of tblproduct 
		private void populateProductCombox() {
			  List<String> productDescriptions = receivingEntryService.getAllProductDescriptions();

			    for (String description : productDescriptions) {
			        productNameCombox.addItem(description);
			    }	
		}
		
		private void tableRowSelection() {
			//to get the value(id) of selected row
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int Myindex = table.getSelectedRow();
			
			String id = table.getModel().getValueAt(Myindex,0).toString();
			txtReceivingId.setText(id);
			productNameCombox.setSelectedItem(model.getValueAt(Myindex,1).toString());
			txtQuantity.setText(model.getValueAt(Myindex, 3).toString());	
			expDateChooser.setDateFormatString(model.getValueAt(Myindex, 5).toString()); // this code is not functioning
		}
}
