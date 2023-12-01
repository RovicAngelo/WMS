package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	                        resultSet.getString("ProductName"),
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
	            String sql = "INSERT INTO tblpurchasedorder (ProductName,ProductPrice,Quantity,Total,CustomerName,Order_Date) VALUES (?, ?, ?, ?, ?, ?)";
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
	                        resultSet.getString("ProductName"),
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
	            String sql = "UPDATE tblpurchasedorder SET ProductName = ?, ProductPrice = ?,Quantity = ?,Total = ?,"
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
	            	JOptionPane.showMessageDialog(null, "purchasedOrder updated successfully.");
	            } else {
	            	JOptionPane.showMessageDialog(null, "No purchasedOrder found with the given ID.");
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
}
