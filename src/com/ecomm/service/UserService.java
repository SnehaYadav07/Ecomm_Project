package com.ecomm.service;

import com.ecomm.dao.UserDAO;
import com.ecomm.model.User;

import java.sql.SQLException;

public class UserService implements AuthService<User> {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        try {
            return userDAO.getUserByEmailAndPassword(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public User register(User user) throws Exception{
        try {
            return userDAO.addUser(user);
        } catch (SQLException e) {
            System.out.println("Error occurred while registering user: " + e.getMessage());
            return null;
        }
    }
}