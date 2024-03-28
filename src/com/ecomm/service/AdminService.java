package com.ecomm.service;

import com.ecomm.dao.AdminDAO;
import com.ecomm.model.Admin;
import java.sql.SQLException;

public class AdminService implements AuthService<Admin> {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    @Override
    public Admin getUserByEmailAndPassword(String email, String password) throws Exception {
        // Use the AdminDAO to retrieve the admin by email and password
        return adminDAO.getAdminByEmailAndPassword(email, password);
    }

    @Override
    public Admin register(Admin admin) throws Exception{
        try {
            adminDAO.add(admin);
            return admin;
        } catch (SQLException e) {
            System.out.println("Error occurred while registering admin: " + e.getMessage());
            return null;
        }
    }
}
