package com.lanuza.wms.model;

public class Product {
	private String productDescription, supplier;
	private int productId, productCode;
	private double productPrice;	
	
	

	public Product() {
		super();
	}
	public Product(int productId, int productCode, String productDescription, double productPrice,String supplier) {
		super();
		this.productId = productId;
		this.productDescription = productDescription;
		this.supplier = supplier;
		this.productCode = productCode;
		this.productPrice = productPrice;
	}
	
	public Product( int productCode, String productDescription, double productPrice,String supplier) {
		super();
		this.productDescription = productDescription;
		this.supplier = supplier;
		this.productCode = productCode;
		this.productPrice = productPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString() {
	    return "Product{" +
	    		 "id=" + productId +
	    		 ",code=" + productCode +
	            ", name='" + productDescription + '\'' +      
	            ", price=" + productPrice +
	            ",supplier='"+ supplier +'\'' +
	            '}';
	}
	
	
}
 