package com.lanuza.wms.dao;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Product;

//product crud
public interface ProductDAO {
	
	Product getProductById(int productId);
	
	List<Product> getAllProducts();
    
	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int productId);
	
	void tableLoad(JTable table);
	
	List<String> getAllSupplierName();
	
	void transferDataAndSetDefaults(Product product);
	
	int getTotalItems();
	
	List<Object[]> getSearchBy (String text);

}
