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

		@Override
		public double getSumOfTotal() {		
			return stockDAO.getSumOfTotal();
		}
		
		@Override
		public List<Object[]> getSearchBy(String text) {
			// TODO Auto-generated method stub
			return stockDAO.getSearchBy(text);
		}
}
