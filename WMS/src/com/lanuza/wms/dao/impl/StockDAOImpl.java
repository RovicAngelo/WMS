package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.StockDAO;
import com.lanuza.wms.model.Stock;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class StockDAOImpl implements StockDAO{

	  @Override
	    public List<Stock> getAllStock() {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Stock> stocks = new ArrayList<>();

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblstock";
	            preparedStatement = connection.prepareStatement(sql);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                // Create Stock objects from the result set and add to the list
	            	Stock stock = new Stock(
	            			resultSet.getInt("stockId"), 	                  
	                        resultSet.getString("ProductName"),
	                        resultSet.getDouble("ProductPrice"),
	                        resultSet.getInt("Quantity"),
	                        resultSet.getDouble("Total"),
	                        resultSet.getString("SupplierName")	
	                );
	            	stocks.add(stock);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        return stocks;
	    }

	    @Override
	    public void tableLoad(JTable table) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblstock";
	            preparedStatement = connection.prepareStatement(sql);
	            resultSet = preparedStatement.executeQuery();

	            // Set the table model using the resultSet
	            table.setModel(DbUtils.resultSetToTableModel(resultSet));

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }
	    }
	    	    
}
