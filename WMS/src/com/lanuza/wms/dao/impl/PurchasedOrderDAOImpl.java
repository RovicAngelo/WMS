package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.PurchasedOrderDAO;
import com.lanuza.wms.model.PurchasedOrder;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class PurchasedOrderDAOImpl implements PurchasedOrderDAO {
	
	  @Override
	    public PurchasedOrder getPurchasedOrderById(int orderId) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        PurchasedOrder purchasedOrder = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblpurchasedorder WHERE OrderId = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, orderId);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                // Create a PurchasedOrder object from the result set
	                purchasedOrder = new PurchasedOrder(
	                		resultSet.getInt("OrderId"), 	                  
	                        resultSet.getString("ProductDescription"),
	                        resultSet.getDouble("ProductPrice"),
	                        resultSet.getInt("Quantity"),
	                        resultSet.getDouble("Total"),
	                        resultSet.getString("CustomerName"),
	                        resultSet.getDate("Order_Date")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        return purchasedOrder;
	    }

	    @Override
	    public void addPurchasedOrder(PurchasedOrder purchasedOrder) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "INSERT INTO tblpurchasedorder (ProductDescription,ProductPrice,Quantity,Total,CustomerName,Order_Date) VALUES (?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, purchasedOrder.getProductName());
	            preparedStatement.setDouble(2, purchasedOrder.getProductPrice());
	            preparedStatement.setInt(3, purchasedOrder.getQuantity());
	            preparedStatement.setDouble(4, purchasedOrder.getTotal());
	            preparedStatement.setString(5, purchasedOrder.getCustomerName());
	            preparedStatement.setDate(6, purchasedOrder.getOrder_Date());

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	            	JOptionPane.showMessageDialog(null, "Order added successfully.");
	            } else {
	            	JOptionPane.showMessageDialog(null, "Failed to add Order.");
	            }
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, null);
	        }
	    }

	    @Override
	    public void deletePurchasedOrder(int orderId) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "DELETE FROM tblpurchasedorder WHERE OrderId = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, orderId);
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, null);
	        }
	    }

	    @Override
	    public List<PurchasedOrder> getAllPurchasedOrders() {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<PurchasedOrder> purchasedOrders = new ArrayList<>();

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "SELECT * FROM tblpurchasedorder";
	            preparedStatement = connection.prepareStatement(sql);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                // Create PurchasedOrder objects from the result set and add to the list
	            	PurchasedOrder purchasedOrder = new PurchasedOrder(
	                  		resultSet.getInt("OrderId"), 	                  
	                        resultSet.getString("ProductDescription"),
	                        resultSet.getDouble("ProductPrice"),
	                        resultSet.getInt("Quantity"),
	                        resultSet.getDouble("Total"),
	                        resultSet.getString("CustomerName"),
	                        resultSet.getDate("Order_Date")
	                );
	            	purchasedOrders.add(purchasedOrder);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately (log or throw a custom exception)
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        return purchasedOrders;
	    }

	    @Override
	    public void updatePurchasedOrder(PurchasedOrder purchasedOrder) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DBConnection.getConnection();
	            String sql = "UPDATE tblpurchasedorder SET ProductDescription = ?, ProductPrice = ?,Quantity = ?,Total = ?,"
	            		+ " CustomerName = ?, Order_Date = ? WHERE OrderId = ?";
	            preparedStatement = connection.prepareStatement(sql);	         
	            preparedStatement.setString(1, purchasedOrder.getProductName());
	            preparedStatement.setDouble(2, purchasedOrder.getProductPrice());
	            preparedStatement.setInt(3, purchasedOrder.getQuantity());
	            preparedStatement.setDouble(4, purchasedOrder.getTotal());
	            preparedStatement.setString(5, purchasedOrder.getCustomerName());
	            preparedStatement.setDate(6, purchasedOrder.getOrder_Date());
	            preparedStatement.setInt(7, purchasedOrder.getOrderId());

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	            	JOptionPane.showMessageDialog(null, "Order updated successfully.");
	            } else {
	            	JOptionPane.showMessageDialog(null, "No Order found with the given ID.");
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
	            String sql = "SELECT * FROM tblpurchasedorder";
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
	             preparedStatement = connection.prepareStatement("SELECT SUM(Total) FROM tblpurchasedorder");
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
	    
	    	   	 
		@SuppressWarnings("resource")
		public Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        double currentQty = 0;
	        double availability = 0.0;
	        double price = 0;
	        double newAvailableQty = 0;

	        try {
	            connection = DBConnection.getConnection();
	            
	            // Query to get current quantity from tblpurchaseorder
	            String sqlPurchaseOrder = "SELECT SUM(Quantity) AS TotalQty FROM tblpurchasedorder WHERE ProductDescription = ?";
	            preparedStatement = connection.prepareStatement(sqlPurchaseOrder);
	            preparedStatement.setString(1, selectedProduct);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                currentQty = resultSet.getDouble("TotalQty");
	            }
	            
	            // Query to get availability and max price from tblstock
	            String sqlStock = "SELECT SUM(Quantity) AS TotalQty, MAX(ProductPrice) AS MaxPrice FROM tblstock WHERE ProductDescription = ?";
	            preparedStatement = connection.prepareStatement(sqlStock);
	            preparedStatement.setString(1, selectedProduct);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                availability = resultSet.getDouble("TotalQty");
	                price = resultSet.getDouble("MaxPrice");

	                newAvailableQty = availability - currentQty;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception according to your needs
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

	        // Create a Map to hold the results
	        Map<String, Object> result = new HashMap<>();
	        result.put("newAvailableQty", newAvailableQty);
	        result.put("productPrice", price);

	        return result;
	    }

		@Override
		public void reflectPurchaseOrderToStock() {
			 Connection connection = null;
		     PreparedStatement preparedStatement = null;
		     Statement statement = null;
		     ResultSet resultSet = null;
		
		  try {				 			  
			 connection = DBConnection.getConnection();          
	         String sqlJoins = "UPDATE tblstock s "
						+ "JOIN tblpurchasedorder o ON s.ProductDescription = o.ProductDescription "
						+ "SET "
						+ "s.Quantity = CASE WHEN (s.Quantity - o.Quantity) < 0 THEN 0 ELSE (s.Quantity - o.Quantity) END, "
						+ "s.Total = CASE WHEN (s.Total - o.Total) < 0 THEN 0 ELSE (s.Total - o.Total) END;";
	         statement = connection.createStatement();
			 statement.executeUpdate(sqlJoins);

	         JOptionPane.showMessageDialog(null, "Table data successfully modified stock");
	         preparedStatement = connection.prepareStatement("truncate table tblpurchasedorder");
	         preparedStatement.executeUpdate(); 
	         
		  } catch (Exception e) {
		  } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }

		}
		
		@Override
		public List<String> getAllCustomerName() {
				Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;
		        List<String> names = new ArrayList<>();
		        
			 try {
	        	 connection = DBConnection.getConnection();
	             preparedStatement = connection.prepareStatement("SELECT Name FROM tblcustomer");
	             resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	String name = resultSet.getString("Name");
	            	names.add(name);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }
			return names;
		}  	  
		
		@Override
		public List<String> getAllProductDescription() {
				Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;     
		        List<String> names = new ArrayList<>();  
		        
			 try {
	        	 connection = DBConnection.getConnection();
	             preparedStatement = connection.prepareStatement("SELECT ProductDescription FROM tblproduct");
	             resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	String name = resultSet.getString("ProductDescription");
	            	names.add(name);
	            }
	        } catch (SQLException e) { 
	            e.printStackTrace();
	        } finally {
	            DBConnection.close(connection, preparedStatement, resultSet);
	        }
			return names;
		}  	  
		
		@Override
		public List<Object[]> getSearchBy(String text) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    List<Object[]> searchResults = new ArrayList<>();

		    try {
		        connection = DBConnection.getConnection();
		        preparedStatement = connection.prepareStatement("SELECT * FROM tblpurchasedorder WHERE LOWER(ProductDescription) LIKE LOWER(?)");
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

		/*
		public void addPurchasedOrder(PurchasedOrder purchasedOrder) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = // obtain your database connection here

	            // Query to insert into the main purchased_orders table
	            String insertPurchasedOrderQuery = "INSERT INTO purchased_orders (product_name, price, quantity, total, customer, order_date) " +
	                    "VALUES (?, ?, ?, ?, ?, ?)";

	            preparedStatement = connection.prepareStatement(insertPurchasedOrderQuery);
	            preparedStatement.setString(1, purchasedOrder.getProductName());
	            preparedStatement.setDouble(2, purchasedOrder.getPrice());
	            preparedStatement.setInt(3, purchasedOrder.getQuantity());
	            preparedStatement.setDouble(4, purchasedOrder.getTotal());
	            preparedStatement.setString(5, purchasedOrder.getCustomer());
	            preparedStatement.setDate(6, purchasedOrder.getOrderDate());

	            preparedStatement.executeUpdate();

	            // Query to update the availability in the products table
	            String updateProductAvailabilityQuery = "UPDATE products SET availability = availability - ? WHERE product_name = ?";
	            preparedStatement = connection.prepareStatement(updateProductAvailabilityQuery);
	            preparedStatement.setInt(1, purchasedOrder.getQuantity());
	            preparedStatement.setString(2, purchasedOrder.getProductName());

	            preparedStatement.executeUpdate();

	        } finally {
	            // Close resources in a finally block
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }    
	  */
}
