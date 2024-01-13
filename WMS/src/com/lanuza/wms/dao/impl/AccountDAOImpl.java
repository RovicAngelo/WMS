package com.lanuza.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.util.DBConnection;

import net.proteanit.sql.DbUtils;

public class AccountDAOImpl implements AccountDAO{

    @Override
    public Account getAccountById(int accountId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblaccount WHERE AccountId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a customer object from the result set
            	account = new Account(      
                        resultSet.getInt("AccountId"),
                        resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return account;
    }

    @Override
    public void addAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO tblaccount (Name,Username, Password, Role) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getRole());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Account added successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "Failed to add account.");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteAccount(int accountId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM tblaccount WHERE AccountId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<Account> getAllAccount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>(); 

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM tblaccount";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Create Customer objects from the result set and add to the list
            	Account account = new Account(  
                        resultSet.getInt("AccountId"),
            			resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role")
                );
            	accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or throw a custom exception)
        } finally {
            DBConnection.close(connection, preparedStatement, resultSet);
        }

        return accounts;
    }

    @Override
    public void updateAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE tblaccount SET Name = ?, Username = ?, Password = ?,  Role = ? WHERE AccountId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getRole());
            preparedStatement.setInt(5, account.getAccountId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(null, "Account updated successfully.");
            } else {
            	JOptionPane.showMessageDialog(null, "No account found with the given ID.");
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
            String sql = "SELECT * FROM tblaccount";
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
    public Account getAccountByUsernameAndPassword(String username, String password) {
    	 Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;
        Account account = null;

        try {
        	connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblaccount WHERE Username = ? AND Password = ?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try {
            	resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    account = new Account(
                        resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role"),
                        resultSet.getInt("AccountId")                       
                    );
                }    
            }
            finally {
    			// TODO: handle finally clause
    		}
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception
	        }
	    }
        return account;
    }
	
}
