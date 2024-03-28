package com.ecomm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection dbConnection;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "group_11";
	private final String password = "root";
	private Connection connection;
	
	private DBConnection() {
		super();
	}

	public static DBConnection getDbConnection() {
		if(dbConnection==null) {
			synchronized(DBConnection.class) {
				if(dbConnection==null) {
					dbConnection=new DBConnection();
				}
			}
		}
		
		return dbConnection;
	}

	public Connection getConnection() {
		try {
			if(connection==null) {
				connection=DriverManager.getConnection(url,user,password);
				if(!connection.isClosed()) {
					return connection;
				} 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public boolean closeConnection() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		return connection.isClosed();
	}
	
}
