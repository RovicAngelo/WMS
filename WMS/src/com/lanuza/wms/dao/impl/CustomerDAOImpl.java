package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.CustomerDAO;
import com.lanuza.wms.model.Customer;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getCustomerById(int customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblcustomer WHERE CustomerId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a customer object from the result set
            	customer = new Customer(
                		resultSet.getInt("CustomerId"),
                        resultSet.getString("Name"),
                        resultSet.getString("PhoneNo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return customer;
    }

    @Override
    public void addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO tblcustomer (Name, PhoneNo) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhoneNo());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Customer added successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "Failed to add customer.");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM tblcustomer WHERE CustomerId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customers = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblcustomer";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Create Customer objects from the result set and add to the list
            	Customer customer = new Customer(
                		resultSet.getInt("CustomerId"),
                        resultSet.getString("Name"),
                        resultSet.getString("PhoneNo")
                );
            	customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE tblcustomer SET Name = ?,PhoneNo = ? WHERE CustomerId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhoneNo());
            preparedStatement.setInt(3, customer.getCustomerId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Customer updated successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "No customer found with the given ID.");
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
            String sql = "SELECT * FROM tblcustomer";
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
	public int getTotalItems() {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
        int sum = 0;

        try {
        	 connection = DBConnection.getConnection();
             preparedStatement = connection.prepareStatement("SELECT COUNT(Name) FROM tblcustomer");
             resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	sum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }
        return sum;
	}

	public List<Object[]> getSearchBy(String text) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Object[]> searchResults = new ArrayList<>();

	    try {
	        connection = DBConnection.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM tblcustomer WHERE LOWER(Name) LIKE LOWER(?)");
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


