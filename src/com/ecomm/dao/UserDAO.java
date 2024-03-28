package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
    }
    
    public User addUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, email, password, phone, gender, address, city, pincode, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getGender());
        statement.setString(6, user.getAddress());
        statement.setString(7, user.getCity());
        statement.setString(8, user.getPincode());
        statement.setString(9, user.getState());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                user.setUserId(userId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return user;
    }


    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name = ?, email = ?, password = ?, phone = ?, gender = ?, address = ?, city = ?, pincode = ?, state = ? WHERE UserId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getGender());
        statement.setString(6, user.getAddress());
        statement.setString(7, user.getCity());
        statement.setString(8, user.getPincode());
        statement.setString(9, user.getState());
        statement.setInt(10, user.getUserId());
        statement.executeUpdate();
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.executeUpdate();
    }

    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE UserId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String phone = resultSet.getString("phone");
            String gender = resultSet.getString("gender");
            LocalDate registeredDate = resultSet.getObject("RegisteredDate", LocalDate.class);
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String pincode = resultSet.getString("pincode");
            String state = resultSet.getString("state");
            return new User(name, email, password, phone, gender, address, city, pincode, state);
        }

        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String phone = resultSet.getString("phone");
            String gender = resultSet.getString("gender");
            LocalDate registeredDate = resultSet.getObject("RegisteredDate", LocalDate.class);
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String pincode = resultSet.getString("pincode");
            String state = resultSet.getString("state");
            users.add(new User(name, email, password, phone, gender, address, city, pincode, state));
        }

        return users;
    }
    
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
    	String query = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int userId = resultSet.getInt("UserId");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String gender = resultSet.getString("gender");
            LocalDate registeredDate = resultSet.getObject("RegisteredDate", LocalDate.class);
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String pincode = resultSet.getString("pincode");
            String state = resultSet.getString("state");

            return new User(name, email, password, phone, gender, address, city, pincode, state);
        }

        return null; // User not found
    }

}