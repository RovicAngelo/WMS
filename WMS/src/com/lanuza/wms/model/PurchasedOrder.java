package com.lanuza.wms.model;

import java.sql.Date;

public class PurchasedOrder {
	private int OrderId,quantity;
	private String productName,customerName;
	private double total,productPrice;
	private java.sql.Date order_Date;
	
	public PurchasedOrder() {
		super();
	}

	public PurchasedOrder(int orderId, String productName,
			double productPrice, int quantity, double total, String customerName, Date order_Date) {
		super();
		OrderId = orderId;
		this.quantity = quantity;
		this.productName = productName;
		this.customerName = customerName;
		this.total = total;
		this.productPrice = productPrice;
		this.order_Date = order_Date;
	}
	
	public PurchasedOrder(String productName,
			double productPrice, int quantity, double total, String customerName, Date order_Date) {
		super();
		this.quantity = quantity;
		this.productName = productName;
		this.customerName = customerName;
		this.total = total;
		this.productPrice = productPrice;
		this.order_Date = order_Date;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public java.sql.Date getOrder_Date() {
		return order_Date;
	}

	public void setOrder_Date(Date order_Date) {
		this.order_Date = order_Date;
	}	
	
}
