package com.lanuza.wms.dao;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Stock;


//product crud
public interface StockDAO {
	
	List<Stock> getAllStock();
	
	void tableLoad(JTable table);
	
	double getSumOfTotal();

}