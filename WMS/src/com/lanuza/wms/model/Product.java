package com.lanuza.wms.model;

public class Product {
	
	private String description,supplierName;
	private int productId;
	private double price;	
	
	public Product() {
		super();
	}
	
	public Product(String description,double price,String supplierName) {
		super();
		this.description = description;
		this.supplierName = supplierName;
		this.price = price;
	}
	
	public Product(int productId,String description,double price,String supplierName) {
		super();
		this.description = description;
		this.productId = productId;
		this.supplierName = supplierName;
		this.price = price;
	}

	//for update query
	public Product(String description,double price,String supplierName,int productId) {
		super();
		this.description = description;
		this.productId = productId;
		this.supplierName = supplierName;
		this.price = price;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
 