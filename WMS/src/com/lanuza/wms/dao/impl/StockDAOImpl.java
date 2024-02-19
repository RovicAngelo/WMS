package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	            			resultSet.getInt("StockId"), 	                  
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
	            String sql = "SELECT StockId,MAX(ProductDescription) as ProductDescription,MAX(ProductPrice) as ProductPrice,SUM(Quantity) as Quantity,SUM(Total) as Total FROM tblstock Group by ProductDescription;";
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

		@Override
		public double getSumOfTotal() {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
	        double sum = 0.0;

	        try {
	        	 connection = DBConnection.getConnection();
	             preparedStatement = connection.prepareStatement("SELECT SUM(Total) FROM tblstock");
	             resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	            	sum = resultSet.getDouble(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }
	        return sum;
		}
		
		@Override
		public List<Object[]> getSearchBy(String text) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    List<Object[]> searchResults = new ArrayList<>();

		    try {
		        connection = DBConnection.getConnection();
		        preparedStatement = connection.prepareStatement("SELECT * FROM tblstock WHERE LOWER(ProductDescription) LIKE LOWER(?)");
		        preparedStatement.setString(1, "%" + text + "%");
		        resultSet = preparedStatement.executeQuery();

		        ResultSetMetaData metaData = resultSet.getMetaData();
		        int columnCount = metaData.getColumnCount();

		        while (resultSet.next()) {
		            Object[] row = new Object[columnCount];
		            for (int i = 1; i <= columnCount; i++) {
		                // Populate the row with column values
		                row[i - 1] = resultSet.getObject(i);
		            }
		            // Add the row to the list of search results
		            searchResults.add(row);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBConnection.close(connection, preparedStatement, resultSet);
		    }

		    return searchResults;
		}
	    	    
}
