package com.lanuza.wms.service;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Stock;

public interface StockService {
	List<Stock> getAllStock();
	
	void tableLoad(JTable table);
	
	double getSumOfTotal();
	
	List<Object[]> getSearchBy (String text);
}
