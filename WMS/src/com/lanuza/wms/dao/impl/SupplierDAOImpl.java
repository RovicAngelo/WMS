package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.SupplierDAO;
import com.lanuza.wms.model.Supplier;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public Supplier getSupplierById(int supplierId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Supplier supplier = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblsupplier WHERE SupplierId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, supplierId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a Supplier object from the result set
            	supplier = new Supplier(
                		resultSet.getInt("SupplierId"),
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

        return supplier;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO tblsupplier (Name, PhoneNo) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, supplier.getName());           
            preparedStatement.setString(2, supplier.getPhoneNo());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Supplier added successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "Failed to add supplier.");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteSupplier(int supplierId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM tblsupplier WHERE SupplierId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, supplierId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<Supplier> getAllSupplier() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Supplier> suppliers = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblsupplier";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Create Supplier objects from the result set and add to the list
            	Supplier supplier = new Supplier(
                		resultSet.getInt("SupplierId"),
                        resultSet.getString("Name"),           
                        resultSet.getString("PhoneNo")
                );
            	suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return suppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE tblsupplier SET Name = ?, PhoneNo = ? WHERE SupplierId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getPhoneNo());
            preparedStatement.setInt(3, supplier.getSupplierId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Supplier updated successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "No supplier found with the given ID.");
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
            String sql = "SELECT * FROM tblsupplier";
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
             preparedStatement = connection.prepareStatement("SELECT COUNT(Name) FROM tblsupplier");
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
}

