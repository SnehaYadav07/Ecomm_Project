package com.ecomm.service;
import com.ecomm.model.Admin;
import com.ecomm.model.User;

public class AuthServiceFactory {
    public static AuthService<Admin> getAdminService() {
        return new AdminService();
    }

    public static AuthService<User> getUserService() {
        return new UserService();
    }
}