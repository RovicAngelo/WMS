package com.lanuza.wms.service.impl;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.StockDAO;
import com.lanuza.wms.model.Stock;
import com.lanuza.wms.service.StockService;

public class StockServiceImpl implements StockService{
	
	 private final StockDAO stockDAO;
	 
	 public StockServiceImpl(StockDAO stockDAO) {
	        this.stockDAO = stockDAO;
	    }

		@Override
		public List<Stock> getAllStock() {
			 return stockDAO.getAllStock();
		}

		@Override
		public void tableLoad(JTable table) {
			stockDAO.tableLoad(table);		
		}
	 


}
