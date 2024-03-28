package com.ecomm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection dbConnection;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "group_11";
	private final String password ="root";
	private Connection connection;
	
	static {
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	public DBConnection() {          // Singleton class concept -> private constructor
		super();
	}

	public static DBConnection getDbConnection() {
		if (dbConnection == null) {
			synchronized (DBConnection.class) {              // public static method to access outside of the class
				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}
			}
			
		}
		return dbConnection;
	}

	public Connection getConnection() throws SQLException {
		if (connection == null) {                                   // if it is null then it will create new connection
			connection = DriverManager.getConnection(url,username,password);      
			if(!connection.isClosed()) {
//				System.out.println(connection.isClosed());
				return connection;
			}
		}
		return connection;
	}
	
	public boolean closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}                                                       // for closing connection
		return connection.isClosed();
	}
}
