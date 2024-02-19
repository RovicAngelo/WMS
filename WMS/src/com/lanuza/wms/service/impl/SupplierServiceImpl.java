package com.lanuza.wms.service.impl;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.SupplierDAO;
import com.lanuza.wms.model.Supplier;
import com.lanuza.wms.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

	@Override
    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSupplier();
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        return supplierDAO.getSupplierById(supplierId);
    }

    @Override
    public void addSupplier(Supplier supplier) {
    	supplierDAO.addSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
    	supplierDAO.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(int supplierId) {
    	supplierDAO.deleteSupplier(supplierId);
    }

	@Override
	public void tableLoad(JTable table) {
		supplierDAO.tableLoad(table);		
	}

	@Override
	public int getTotalItems() {
		// TODO Auto-generated method stub
		return supplierDAO.getTotalItems();
	}
	
	@Override
	public List<Object[]> getSearchBy(String text) {
		// TODO Auto-generated method stub
		return supplierDAO.getSearchBy(text);
	}
}
