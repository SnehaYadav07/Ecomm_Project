package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecomm.model.Admin; // Assuming you have an Admin model class
import com.ecomm.util.DBConnection;

public class AdminDao {

    private DBConnection dbConnection;

    public AdminDao() {
        super();
    }

    public AdminDao(DBConnection dbConnection) {
        super();
        this.dbConnection = dbConnection;
    }

    public Admin login(String email, String password) throws Exception {
        Admin admin = null;
        try {
            Connection connection = dbConnection.getConnection();
            // Assuming there's an 'is_admin' column to distinguish admin users
            String sqlQuery = "SELECT * FROM users WHERE email = ? AND password = ? AND is_admin = TRUE";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                // Assuming you do not need to set the password in the admin object
                admin.setPhone(resultSet.getString("phone"));
                // Add other admin-specific fields as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while logging in", e);
        }
        return admin;
    }

}
