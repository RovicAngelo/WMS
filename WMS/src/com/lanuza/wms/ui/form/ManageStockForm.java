package com.lanuza.wms.ui.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.lanuza.wms.dao.StockDAO;
import com.lanuza.wms.dao.impl.StockDAOImpl;
import com.lanuza.wms.service.StockService;
import com.lanuza.wms.service.impl.StockServiceImpl;
import com.lanuza.wms.ui.components.CustomButton;
import com.lanuza.wms.ui.components.RoundPanel;
import com.lanuza.wms.ui.components.table.Table;

public class ManageStockForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private StockDAO stockDAO;
	private StockService stockService;
	private JTextField textField,txtStockId;
	private JLabel lblCurrentDate,txtGrossTotal;
	private Table table;


	public ManageStockForm() {
		this.stockDAO = new StockDAOImpl();
		this.stockService = new StockServiceImpl(stockDAO);
		initialize();
		loadData();
		displayGrossTotal();
		getDateToday();
	}
	private void initialize() {
		setLayout(null);
		
		JLabel lblStockView = new JLabel("Stock View");
		lblStockView.setForeground(Color.BLACK);
		lblStockView.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStockView.setBounds(0, 0, 184, 29);
		add(lblStockView);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBounds(new Rectangle(34, 54, 1129, 38));
		panelButtons.setBackground(new Color(3, 65, 68));
		panelButtons.setBounds(0, 45, 1097, 38);
		add(panelButtons);
		
		CustomButton btnPrint = new CustomButton(new Color(64, 128, 128), "Print", this::printTable, new Rectangle(63, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint.setText("Print");
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPrint.setBackground(new Color(3, 65, 68));
		btnPrint.setBounds(0, 0, 63, 38);
		panelButtons.add(btnPrint);
		
		CustomButton btnSaveFile = new CustomButton(new Color(64, 128, 128), "Save as file", this::saveAsFile, new Rectangle(126, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSaveFile.setText("Save");
		btnSaveFile.setForeground(Color.WHITE);
		btnSaveFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSaveFile.setBackground(new Color(3, 65, 68));
		btnSaveFile.setBounds(62, 0, 63, 38);
		panelButtons.add(btnSaveFile);
		
		CustomButton btnMode = new CustomButton(new Color(64, 128, 128), "Change Mode", this::ChangeMode, new Rectangle(378, 0, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnMode.setText("Mode");
		btnMode.setForeground(Color.WHITE);
		btnMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMode.setBackground(new Color(3, 65, 68));
		btnMode.setBounds(124, 0, 63, 38);
		panelButtons.add(btnMode);
		
		textField = new JTextField();
		textField.setToolTipText("Search by...");
		textField.setColumns(10);
		textField.setBounds(20, 110, 304, 33);
		add(textField);
		
		CustomButton btnSearchBy = new CustomButton(new Color(243, 243, 243), "Search", (ActionListener) null, new Rectangle(301, 52, 63, 33), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSearchBy.setIcon(new ImageIcon(ManageStockForm.class.getResource("/com/lanuza/wms/ui/resources/icons/search.png")));
		btnSearchBy.setText("");
		btnSearchBy.setBounds(324, 110, 68, 33);
		add(btnSearchBy);
		
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setRound(10);
		roundPanel.setPreferredSize(new Dimension(400, 400));
		roundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		roundPanel.setBackground(Color.WHITE);
		roundPanel.setBounds(20, 154, 864, 466);
		add(roundPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		
		JLabel lblStockId = new JLabel("Stock Id");
		lblStockId.setForeground(Color.BLACK);
		lblStockId.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtStockId = new JTextField();
		txtStockId.setToolTipText("Id");
		txtStockId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtStockId.setEditable(false);
		txtStockId.setColumns(10);
		txtStockId.setBackground(SystemColor.menu);
		
		txtGrossTotal = new JLabel("0.0");
		txtGrossTotal.setOpaque(true);
		txtGrossTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGrossTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGrossTotal.setBackground(Color.WHITE);
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setForeground(Color.BLACK);
		lblGrossTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		GroupLayout gl_roundPanel = new GroupLayout(roundPanel);
		gl_roundPanel.setHorizontalGroup(
			gl_roundPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStockId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStockId, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_roundPanel.createSequentialGroup()
							.addContainerGap(567, Short.MAX_VALUE)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_roundPanel.setVerticalGroup(
			gl_roundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStockId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStockId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_roundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(lblGrossTotal, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtGrossTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new Table();
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tableSelectedRow();
			}
		});
		scrollPane.setViewportView(table);
		roundPanel.setLayout(gl_roundPanel);
		
		JPanel sidePanel2 = new JPanel();
		sidePanel2.setLayout(null);
		sidePanel2.setBackground(new Color(3, 65, 68));
		sidePanel2.setBounds(927, 82, 170, 581);
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
		
		lblCurrentDate = new JLabel("2023-12-11");
		lblCurrentDate.setForeground(Color.BLACK);
		lblCurrentDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCurrentDate.setBackground(Color.WHITE);
		lblCurrentDate.setBounds(791, 90, 93, 40);
		add(lblCurrentDate);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(749, 94, 40, 33);
		add(lblDate);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(new Rectangle(0, 641, 1370, 20));
		bottomPanel.setBackground(new Color(3, 65, 68));
		bottomPanel.setBounds(0, 643, 1097, 20);
		add(bottomPanel);

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
	 private void printTable(ActionEvent e) {
		 //print the order table
		 try {
				table.print();					
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}
	 private void tableSelectedRow() {
		 DefaultTableModel model = (DefaultTableModel)table.getModel();
			int Myindex = table.getSelectedRow();
			String id = table.getModel().getValueAt(Myindex,0).toString();	
			
			txtStockId.setText(id);					
			
	    }
	 
		void loadData() {
			stockService.tableLoad(table);
		}
		private void displayGrossTotal() {
	        double sumOfTotal = stockService.getSumOfTotal();
	        // Update UI component (setText on a txtGrossTotal)
	        txtGrossTotal.setText(String.valueOf(sumOfTotal));
		} 

}
