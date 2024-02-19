package com.lanuza.wms.service.impl;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.ProductDAO;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

	@Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

	@Override
	public void tableLoad(JTable table) {
		productDAO.tableLoad(table);	
	}


	@Override
	public void transferDataAndSetDefaults(Product product) {
		productDAO.transferDataAndSetDefaults(product);
		
	}

	@Override
	public List<String> getAllSupplierName() {
		// TODO Auto-generated method stub
		return productDAO.getAllSupplierName();
	}

	@Override
	public int getTotalItems() {
		// TODO Auto-generated method stub
		return productDAO.getTotalItems();
	}

	@Override
	public List<Object[]> getSearchBy(String text) {
		// TODO Auto-generated method stub
		return productDAO.getSearchBy(text);
	}
}
