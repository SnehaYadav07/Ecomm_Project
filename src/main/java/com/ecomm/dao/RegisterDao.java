package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

public class RegisterDao {
	
	private DBConnection dbConnection;

	public RegisterDao() {
		super();
	}

	public RegisterDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}
	
//	@Override												// return type of the class is User and we are passing one object user and from servlet getting createUser so method will be executed
	public User create (User user) throws Exception {         // creating new user and registration
		try { 
			Connection connection = dbConnection.getConnection();      // doing connection from DBConnection.java file
			String sqlQuery = "insert into users (name, email, password, phone, gender, address, city, pincode, state) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, user.getName());                    // we are setting string to map with question marks and then getting name from createUser object 
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setString(7, user.getCity());
			preparedStatement.setString(8, user.getPincode());
			preparedStatement.setString(9, user.getState());
			if(preparedStatement.executeUpdate()<0) {     // like we have to executeQuery in DML and executeUpdate in DDL.
				user = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
}
