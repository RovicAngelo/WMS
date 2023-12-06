package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.ReceivingEntryDAO;
import com.lanuza.wms.model.ReceivingEntry;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class ReceivingEntryDAOImpl implements ReceivingEntryDAO{
	  @Override
	    public ReceivingEntry getReceivingEntryById(int receivingId) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        ReceivingEntry receivingEntry = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblreceivingentry WHERE ReceivingId = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, receivingId);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                // Create a receivingentry object from the result set
	            	receivingEntry = new ReceivingEntry(
	                		resultSet.getInt("ReceivingId"), 	                  
	                        resultSet.getString("ProductDescription"),
	                        resultSet.getDouble("ProductPrice"),
	                        resultSet.getInt("Quantity"),
	                        resultSet.getDouble("Total"),
	                        resultSet.getDate("ExpDate"),
	                        resultSet.getString("SupplierName"),
	                        resultSet.getDate("ReceivedDate")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        return receivingEntry;
	    }

	    @Override
	    public void addReceivingEntry(ReceivingEntry receivingEntry) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "INSERT INTO tblreceivingentry (ProductDescription,ProductPrice,Quantity,Total,ExpDate,SupplierName,ReceivedDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, receivingEntry.getProductName());
	            preparedStatement.setDouble(2, receivingEntry.getProductPrice());
	            preparedStatement.setInt(3, receivingEntry.getQuantity());
	            preparedStatement.setDouble(4, receivingEntry.getTotal());
	            preparedStatement.setDate(5, receivingEntry.getExpDate());
	            preparedStatement.setString(6, receivingEntry.getSupplierName());
	            preparedStatement.setDate(7, receivingEntry.getReceived_Date());

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	            	JOptionPane.showMessageDialog(null, "receivingentry added successfully.");
	            } else {
	            	JOptionPane.showMessageDialog(null, "Failed to add receivingentry.");
	            }
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, null);
	        }
	    }

	    @Override
	    public void deleteReceivingEntry(int receivingId) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "DELETE FROM tblreceivingentry WHERE ReceivingId = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, receivingId);
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, null);
	        }
	    }

	    @Override
	    public List<ReceivingEntry> getAllReceivingEntries() {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<ReceivingEntry> receivingentries = new ArrayList<>();

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblreceivingentry";
	            preparedStatement = connection.prepareStatement(sql);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                // Create receivingentry objects from the result set and add to the list
	            	ReceivingEntry receivingentry = new ReceivingEntry(
	                  		resultSet.getInt("ReceivingId"), 	                  
	                        resultSet.getString("ProductDescription"),
	                        resultSet.getDouble("ProductPrice"),
	                        resultSet.getInt("Quantity"),
	                        resultSet.getDouble("Total"),
	                        resultSet.getDate("ExpDate"),
	                        resultSet.getString("SupplierName"),
	                        resultSet.getDate("ReceivedDate")
	                );
	            	receivingentries.add(receivingentry);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        return receivingentries;
	    }

	    @Override
	    public void updateReceivingEntry(ReceivingEntry receivingentry) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "UPDATE tblreceivingentry SET ProductDescription = ?, ProductPrice = ?,Quantity = ?,Total = ?,ExpDate =?"
	            		+ " SupplierName = ?, ReceivedDate = ? WHERE ReceivingId = ?";
	            preparedStatement = connection.prepareStatement(sql);	         
	            preparedStatement.setString(1, receivingentry.getProductName());
	            preparedStatement.setDouble(2, receivingentry.getProductPrice());
	            preparedStatement.setInt(3, receivingentry.getQuantity());
	            preparedStatement.setDouble(4, receivingentry.getTotal());
	            preparedStatement.setDate(5, receivingentry.getExpDate());
	            preparedStatement.setString(6, receivingentry.getSupplierName());
	            preparedStatement.setDate(7, receivingentry.getReceived_Date());
	            preparedStatement.setInt(8, receivingentry.getReceivingId());

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	            	JOptionPane.showMessageDialog(null, "receivingentry updated successfully.");
	            } else {
	            	JOptionPane.showMessageDialog(null, "No receivingentry found with the given ID.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, null);
	        }
	    }
	    
	    @Override
	    public void tableLoad(JTable table) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblreceivingentry";
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
	             preparedStatement = connection.prepareStatement("SELECT SUM(Total) FROM tblreceivingentry");
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
		public void reflectReceivingEntryToStock() {
			 Connection connection = null;
		     PreparedStatement preparedStatement = null;
		     Statement statement = null;
		     ResultSet resultSet = null;
		
		  try {				 			  
			 connection = DBConnection.getConnection();          
	         String sqlJoins = "INSERT INTO tblstock(ProductDescription,ProductPrice,Quantity,Total,SupplierName) SELECT ProductDescription, MAX(ProductPrice),SUM(Quantity),SUM(Total),MAX(SupplierName) FROM tblreceivingentry GROUP BY ProductDescription";
	         statement = connection.createStatement();
			 statement.executeUpdate(sqlJoins);

	         JOptionPane.showMessageDialog(null, "Table data successfully modified stock");
	         
	         preparedStatement = connection.prepareStatement("truncate table tblreceivingentry");
	         preparedStatement.executeUpdate(); 
	         
		  } catch (Exception e) {
				// TODO: handle exception
		  } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

		}	    

		@Override
		public Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct) {
			 Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;
		        double price = 0.0;
		        String supplier = "";

		        try {
		            connection = DBConnection.getConnection();            
		            preparedStatement = connection.prepareStatement("Select ProductPrice, SupplierName from tblproduct where ProductDescription = ?");
		            preparedStatement.setString(1, selectedProduct);
		            resultSet = preparedStatement.executeQuery();					
					if(resultSet.next()) {		
						price = resultSet.getInt("ProductPrice");	
						supplier = resultSet.getString("SupplierName");										
					}

		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception according to your needs
		        } finally {
		            DBConnection.close(connection, preparedStatement, resultSet);
		        }

		        // Create a Map to hold the results
		        Map<String, Object> result = new HashMap<>();
		        result.put("ProductPrice", price);
		        result.put("Supplier", supplier);

		        return result;
		}
		
		@Override
		public List<String> getAllProductDescriptions() {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    List<String> descriptions = new ArrayList<>();

		    try {
		        connection = DBConnection.getConnection();
		        preparedStatement = connection.prepareStatement("SELECT ProductDescription FROM tblproduct");
		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            String description = resultSet.getString("ProductDescription");
		            descriptions.add(description);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBConnection.close(connection, preparedStatement, resultSet);
		    }
		    return descriptions;
		}
}
