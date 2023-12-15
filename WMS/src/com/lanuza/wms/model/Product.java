package com.lanuza.wms.model;

public class Product {

	private String productDescription,supplierName;
	private int productId,quantity;
	private double productPrice,total;	
	
	public Product() {
		super();
	}
	
	public Product( int productId,String productDescription, double productPrice, int quantity,
			double total,String supplierName) {
		super();
		this.productDescription = productDescription;
		this.supplierName = supplierName;
		this.productId = productId;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.total = total;
	}
	
	public Product(String productDescription, double productPrice, int quantity,
			double total,String supplierName, int productId) {
		super();
		this.productDescription = productDescription;
		this.supplierName = supplierName;
		this.productId = productId;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.total = total;
	}
	
	public Product(String productDescription, double productPrice, int quantity,
			double total,String supplierName) {
		super();
		this.productDescription = productDescription;
		this.supplierName = supplierName;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.total = total;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
 