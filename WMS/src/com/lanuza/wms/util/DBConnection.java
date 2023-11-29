package com.lanuza.wms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/wmsdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    Statement st;
    
    public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
		}catch(ClassNotFoundException exc){
			exc.printStackTrace();
            throw new RuntimeException("Error loading JDBC driver");
		}catch(SQLException exc) {
			exc.printStackTrace();
            throw new RuntimeException("Error loading JDBC driver");
		}
	}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
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
            e.printStackTrace();
        }
    }

}
