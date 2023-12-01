package com.lanuza.wms.service;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.ReceivingEntry;

public interface ReceivingEntryService {
	List<ReceivingEntry> getAllReceivingEntries();
	
	ReceivingEntry getReceivingEntryById(int receivingId);
	
	void addReceivingEntry(ReceivingEntry receivingEntry);
	
	void updateReceivingEntry(ReceivingEntry receivingEntry);
	
	void deleteReceivingEntry(int receivingId);
	
	void tableLoad(JTable table);
}
