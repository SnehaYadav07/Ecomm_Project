package com.ecomm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecomm.model.Admin;
import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

public class LoginDao {
	
	private DBConnection dbConnection;

	public LoginDao() {
		super();
	}

	public LoginDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}
	
//	@Override
	public User authenticateUser(String userEmail,String userPassword) throws SQLException {
	
		Connection connection = dbConnection.getConnection();
		String sqlQuery = " select * from users where email = ? and password = ? ";    // select query will return the all info from users for specific user and we are giving email and password.
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1,userEmail);
		preparedStatement.setString(2,userPassword);
		
		ResultSet rs = preparedStatement.executeQuery(); // with DML Statement like insert,update and delete we are using executeUpdate and with DDL statement like select we have to use executeQuery.
		User user = new User();
		if (rs.next()) {                                            // whenever you want to return an object then you have to add the parameter in getString method which is column name
			String name = rs.getString("name");            // everytime it will throw an error of constructor so fetch all data for future use 
			String email = rs.getString("email");
			String password = rs.getString("password");           // if this condition is true it will return all the values from the backend if not the null user will return.
			Date registeredDate = rs.getDate("registereddate");          // it will not return any int value so have use rs.next()
			String phone = rs.getString("phone");
			String gender = rs.getString("gender");
			String address = rs.getString("address");
			String city = rs.getString("city");                    
			String pincode = rs.getString("pincode");             // getter method get data from the backend
			String state = rs.getString("state");                  // setter method set data to frontend -> named it like model class in setstring
			
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			user.setGender(gender);
			user.setAddress(address);
			user.setCity(city);
			user.setPincode(pincode);
			user.setState(state);
		}
		return user;
	}
	
//	@Override
	public Admin authenticateAdmin (String userEmail,String userPassword) throws SQLException {
		
		Connection connection = dbConnection.getConnection();
		String sqlQuery = " select * from admin where email = ? and password = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, userEmail);
		preparedStatement.setString(2, userPassword);
		
		ResultSet rs = preparedStatement.executeQuery();
		Admin admin = new Admin();
		if (rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String phone = rs.getString("phone");
			int id=rs.getInt("adminid");
			
			admin.setName(name);
			admin.setEmail(email);
			admin.setPassword(password);
			admin.setPhone(phone);
			
//			admin=new Admin(id,name,email,password,phone);
		}
		return admin;
	}
}
