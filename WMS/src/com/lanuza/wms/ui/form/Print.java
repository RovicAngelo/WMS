package com.lanuza.wms.ui.form;

//this frame doesn't have function yet
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lanuza.wms.ui.components.CustomButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class Print extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public Print() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel receivingAddPanel = new JPanel();
		receivingAddPanel.setLayout(null);
		receivingAddPanel.setBounds(68, 35, 397, 264);
		contentPane.add(receivingAddPanel);
		
		CustomButton btnClear = new CustomButton(new Color(243, 243, 243), "Update", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnClear.setToolTipText("Clear");
		btnClear.setText("Clear");
		btnClear.setBounds(208, 203, 63, 38);
		receivingAddPanel.add(btnClear);
		
		textField = new JTextField();
		textField.setToolTipText("Qty");
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setColumns(10);
		textField.setBounds(125, 42, 219, 31);
		receivingAddPanel.add(textField);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(51, 43, 63, 29);
		receivingAddPanel.add(lblQuantity);
		
		CustomButton btnCancel = new CustomButton(new Color(243, 243, 243), "Update", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCancel.setToolTipText("Cancel");
		btnCancel.setText("Cancel");
		btnCancel.setBounds(281, 203, 63, 38);
		receivingAddPanel.add(btnCancel);
		
		CustomButton btnAdd = new CustomButton(new Color(243, 243, 243), "Update", (ActionListener) null, new Rectangle(515, 132, 63, 38), false, new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setToolTipText("Add");
		btnAdd.setText("Add");
		btnAdd.setBounds(135, 203, 63, 38);
		receivingAddPanel.add(btnAdd);
		
		JComboBox<String> productNameCombox = new JComboBox<String>();
		productNameCombox.setToolTipText("Code");
		productNameCombox.setMaximumRowCount(2);
		productNameCombox.setFont(new Font("Tahoma", Font.BOLD, 13));
		productNameCombox.setEditable(true);
		productNameCombox.setBounds(124, 95, 220, 31);
		receivingAddPanel.add(productNameCombox);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setForeground(Color.BLACK);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduct.setBounds(60, 96, 51, 29);
		receivingAddPanel.add(lblProduct);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setForeground(Color.BLACK);
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExpiryDate.setBounds(41, 152, 74, 29);
		receivingAddPanel.add(lblExpiryDate);
		
		JDateChooser expDateChooser = new JDateChooser();
		expDateChooser.setToolTipText("Date");
		expDateChooser.setDateFormatString("d MM yyyy");
		expDateChooser.setBounds(125, 151, 173, 31);
		receivingAddPanel.add(expDateChooser);
	}

}
