package com.lanuza.wms.model;

public class Stock {
	private int stockId,quantity;
	private String productName,SupplierName;
	private double total,productPrice;
	
	public Stock() {
		super();
	}
	public Stock(int stockId, String productName,double productPrice, int quantity, double total, String supplierName) {
		super();
		this.stockId = stockId;
		this.quantity = quantity;
		this.productName = productName;
		SupplierName = supplierName;
		this.total = total;
		this.productPrice = productPrice;
	}
	//for update query
	public Stock(String productName,double productPrice, int quantity, double total, String supplierName,int stockId) {
		super();
		this.stockId = stockId;
		this.quantity = quantity;
		this.productName = productName;
		SupplierName = supplierName;
		this.total = total;
		this.productPrice = productPrice;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
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
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
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
	
	
}
