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

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.model.ReceivingEntry;
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
    

	@Override
	public int getTotalItems() {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
        int sum = 0;

        try {
        	 connection = DBConnection.getConnection();
             preparedStatement = connection.prepareStatement("SELECT COUNT(Name) FROM tblaccount");
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
	public Account getAccountByName(String name) {
		 Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;
        Account account = null;

        try {
        	connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblaccount WHERE Name = ?");

            preparedStatement.setString(1, name);

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

	@Override
	public List<Account> getAllAccountNameExcept(String currentName) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>();

        try {
        	connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT Name FROM tblaccount WHERE NOT Name = ?");

            preparedStatement.setString(1, currentName);

            try {
            	resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Account account = new Account(
                        resultSet.getString("Name")                       
                    );
                    accounts.add(account);
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
        return accounts;
	}
	
	public List<Object[]> getSearchBy(String text) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Object[]> searchResults = new ArrayList<>();

	    try {
	        connection = DBConnection.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM tblaccount WHERE LOWER(Name) LIKE LOWER(?)");
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
