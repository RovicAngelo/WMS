package com.lanuza.wms.service.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.lanuza.wms.dao.ReceivingEntryDAO;
import com.lanuza.wms.model.ReceivingEntry;
import com.lanuza.wms.service.ReceivingEntryService;

public class ReceivingEntryServiceImpl implements ReceivingEntryService{

    private final ReceivingEntryDAO receivingEntryDAO;

    public ReceivingEntryServiceImpl(ReceivingEntryDAO receivingEntryDAO) {
        this.receivingEntryDAO = receivingEntryDAO;
    }

	@Override
    public List<ReceivingEntry> getAllReceivingEntries() {
        return receivingEntryDAO.getAllReceivingEntries();
    }

    @Override
    public ReceivingEntry getReceivingEntryById(int receivingId) {
        return receivingEntryDAO.getReceivingEntryById(receivingId);
    }

    @Override
    public void addReceivingEntry(ReceivingEntry receivingEntry) {
    	receivingEntryDAO.addReceivingEntry(receivingEntry);
    }

    @Override
    public void updateReceivingEntry(ReceivingEntry receivingEntry) {
    	receivingEntryDAO.updateReceivingEntry(receivingEntry);
    }

    @Override
    public void deleteReceivingEntry(int receivingId) {
    	receivingEntryDAO.deleteReceivingEntry(receivingId);
    }

	@Override
	public void tableLoad(JTable table) {
		receivingEntryDAO.tableLoad(table);		
	}
	
	@Override
	public double getSumOfTotal() {
        return receivingEntryDAO.getSumOfTotal();
    }
	@Override
	public void reflectReceivingEntryToStock() {
		receivingEntryDAO.reflectReceivingEntryToStock();	
	}

	@Override
	public Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct) {
		return receivingEntryDAO.getAvailabilityAndPriceByProductDescription(selectedProduct);
	}
}
