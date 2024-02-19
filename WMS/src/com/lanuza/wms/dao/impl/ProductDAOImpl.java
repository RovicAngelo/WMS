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
            String sql = "SELECT * FROM tblproduct WHERE ProductId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a Product object from the result set
                product = new Product(
                		resultSet.getInt("ProductId"), 
                        resultSet.getString("ProductDescription"),
                        resultSet.getDouble("ProductPrice"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Total"),
                        resultSet.getString("SupplierName")
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
            String sql = "INSERT INTO tblproduct (ProductDescription,ProductPrice,Quantity,Total, SupplierName) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getTotal());
            preparedStatement.setString(5, product.getSupplierName());

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
            String sql = "DELETE FROM tblproduct WHERE ProductId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

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
                        resultSet.getString("ProductDescription"),
                        resultSet.getDouble("ProductPrice"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("Total"),
                        resultSet.getString("SupplierName")
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

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE tblproduct SET ProductDescription = ?, ProductPrice = ?, Quantity=?, Totat=? ,SupplierName = ? WHERE ProductId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getTotal());
            preparedStatement.setString(5, product.getSupplierName());
            preparedStatement.setInt(6, product.getProductId());

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
            String sql = "SELECT ProductId, ProductDescription, ProductPrice,SupplierName FROM tblproduct";
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
	public List<String> getAllSupplierName() {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<String> names = new ArrayList<>();

	    try {
	        connection = DBConnection.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT Name FROM tblsupplier");
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
	public void transferDataAndSetDefaults(Product product) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	connection = DBConnection.getConnection();
	        preparedStatement = connection.prepareStatement("INSERT INTO tblstock(ProductDescription, ProductPrice, Quantity, Total, SupplierName) VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, product.getProductDescription());
			preparedStatement.setDouble(2, product.getProductPrice());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setDouble(4, product.getTotal());
			preparedStatement.setString(5, product.getSupplierName());
			preparedStatement.executeUpdate();
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
             preparedStatement = connection.prepareStatement("SELECT COUNT(ProductDescription) FROM tblproduct");
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

	@Override
	public List<Object[]> getSearchBy(String text) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Object[]> searchResults = new ArrayList<>();

	    try {
	        connection = DBConnection.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM tblproduct WHERE LOWER(ProductDescription) LIKE LOWER(?)");
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