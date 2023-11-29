package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.ProductDAO;
import com.lanuza.wms.model.Product;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public Product getProductById(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblproduct WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a Product object from the result set
                product = new Product(
                		resultSet.getInt("ProductId"),
                        resultSet.getInt("ProductCode"),
                        resultSet.getString("ProductDescription"),
                        resultSet.getDouble("ProductPrice"),
                        resultSet.getString("Supplier")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return product;
    }

    @Override
    public void addProduct(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO tblproduct (ProductCode, ProductDescription, ProductPrice, Supplier) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setString(4, product.getSupplier());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Product added successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "Failed to add product.");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM tblproduct WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblproduct";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Create Product objects from the result set and add to the list
                Product product = new Product(
                		resultSet.getInt("ProductId"),
                        resultSet.getInt("ProductCode"),
                        resultSet.getString("ProductDescription"),
                        resultSet.getDouble("ProductPrice"),
                        resultSet.getString("Supplier")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        /*
        DefaultTableModel model = (DefaultTableModel)table.getModel();
		int Myindex = table.getSelectedRow();
		String id = table.getModel().getValueAt(Myindex,0).toString();	
		
		txtId.setText(id);					
		txtCode.setText(model.getValueAt(Myindex, 1).toString());
		txtDescription.setText(model.getValueAt(Myindex, 2).toString());
		txtPrice.setText(model.getValueAt(Myindex, 3).toString());	
		supplierCombox.setSelectedItem(model.getValueAt(Myindex, 4).toString());
		*/

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE tblproduct SET ProductCode = ?, ProductDescription = ?, ProductPrice = ?, Supplier = ? WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setString(4, product.getSupplier());
            preparedStatement.setInt(5, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Product updated successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "No product found with the given ID.");
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
            String sql = "SELECT * FROM tblproduct";
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
