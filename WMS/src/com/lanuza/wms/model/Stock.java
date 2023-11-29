package com.lanuza.wms.model;

public class Stock {
	private int productCode, quantity;
	private String productDescription;
	private Double productPrice,total;				
		
	
	public Stock() {
		super();
	}
	public Stock(int productCode, String productDescription, Double productPrice, int quantity, Double total) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.total = total;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Stock [productCode=" + productCode + ", quantity=" + quantity + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", total=" + total + "]";
	}
	
}
