package com.lanuza.wms.service;

//business rules,validation here

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	
	Product getProductById(int productId);
	
	void addProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(int productId);
	
	void tableLoad(JTable table);
	
	List<String> getAllSupplierName();
	
	void transferDataAndSetDefaults(Product product);
	
	int getTotalItems();
	
	List<Object[]> getSearchBy (String text);
}