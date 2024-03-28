package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.Admin;
import com.ecomm.util.DBConnection;

public class AdminDAO implements IDao<Admin> {
    private Connection connection;

    public AdminDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
    }

    @Override
    public void add(Admin admin) throws SQLException {
        String query = "INSERT INTO Admin (Name, Email, Password, Phone) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getEmail());
        statement.setString(3, admin.getPassword());
        statement.setString(4, admin.getPhone());
        statement.executeUpdate();
    }

    @Override
    public void update(Admin admin) throws SQLException {
        String query = "UPDATE Admin SET Name = ?, Email = ?, Password = ?, Phone = ? WHERE AdminID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getEmail());
        statement.setString(3, admin.getPassword());
        statement.setString(4, admin.getPhone());
        statement.setInt(5, admin.getAdminId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Admin admin) throws SQLException {
        String query = "DELETE FROM Admin WHERE AdminID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, admin.getAdminId());
        statement.executeUpdate();
    }

    @Override
    public Admin findOne(int adminId) throws SQLException {
        String query = "SELECT * FROM Admin WHERE AdminID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, adminId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String phone = resultSet.getString("Phone");
            return new Admin(adminId, name, email, password, phone);
        }

        return null;
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int adminId = resultSet.getInt("AdminID");
            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String phone = resultSet.getString("Phone");
            admins.add(new Admin(adminId, name, email, password, phone));
        }

        return admins;
    }

    // Utility methods specific to AdminDAO
    public Admin getAdminByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM Admin WHERE Email = ? AND Password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int adminId = resultSet.getInt("AdminID");
            String name = resultSet.getString("Name");
            String phone = resultSet.getString("Phone");

            return new Admin(adminId, name, email, password, phone);
        }

        return null; // Admin not found
    }




}