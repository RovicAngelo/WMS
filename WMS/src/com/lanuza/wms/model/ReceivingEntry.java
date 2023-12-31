package com.lanuza.wms.model;

import java.sql.Date;

public class ReceivingEntry {
	private int receivingId,quantity;
	private String productName,supplierName;
	private double total,productPrice;
	private Date received_Date, expDate;
	
	public ReceivingEntry() {
		super();
	}
	//with all parameter
	public ReceivingEntry(int receivingId, String productName, double productPrice,int quantity, double total,Date expDate, String supplierName,
			 Date received_Date) {
		super();
		this.receivingId = receivingId;
		this.quantity = quantity;
		this.productName = productName;
		this.expDate = expDate;
		this.supplierName = supplierName;
		this.total = total;
		this.productPrice = productPrice;
		this.received_Date = received_Date;
	}
	//for update query
	public ReceivingEntry(String productName, double productPrice,int quantity, double total,Date expDate, String supplierName,
			 Date received_Date,int receivingId) {
		super();
		this.receivingId = receivingId;
		this.quantity = quantity;
		this.productName = productName;
		this.expDate = expDate;
		this.supplierName = supplierName;
		this.total = total;
		this.productPrice = productPrice;
		this.received_Date = received_Date;
	}
	//with no id
	public ReceivingEntry(String productName, double productPrice,int quantity, double total,Date expDate, String supplierName,
			 Date received_Date) {
		super();
		this.quantity = quantity;
		this.productName = productName;
		this.supplierName = supplierName;
		this.expDate = expDate;
		this.total = total;
		this.productPrice = productPrice;
		this.received_Date = received_Date;
	}
	public int getReceivingId() {
		return receivingId;
	}
	public void setReceivingId(int receivingId) {
		this.receivingId = receivingId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public Date getReceived_Date() {
		return received_Date;
	}
	public void setReceived_Date(Date received_Date) {
		this.received_Date = received_Date;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
}
