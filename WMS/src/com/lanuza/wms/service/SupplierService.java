package com.lanuza.wms.service;

//business rules,validation here

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Supplier;


public interface SupplierService {
	List<Supplier> getAllSuppliers();
	
	Supplier getSupplierById(int supplierId);
	
	void addSupplier(Supplier supplier);
	
	void updateSupplier(Supplier supplier);
	
	void deleteSupplier(int supplierId);
	
	void tableLoad(JTable table);
	
	int getTotalItems();
	
	List<Object[]> getSearchBy (String text);
}
