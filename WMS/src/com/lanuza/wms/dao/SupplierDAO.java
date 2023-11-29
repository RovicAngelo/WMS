package com.lanuza.wms.dao;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Supplier;

//product crud
public interface SupplierDAO {
	
	Supplier getSupplierById(int supplierId);
	
	List<Supplier> getAllSupplier();
    
	void addSupplier(Supplier supplier);

	void updateSupplier(Supplier supplier);

	void deleteSupplier(int supplierId);
	
	void tableLoad(JTable table);

}
