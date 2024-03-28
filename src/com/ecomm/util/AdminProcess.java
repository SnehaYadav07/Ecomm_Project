package com.ecomm.util;

import com.ecomm.model.Admin;
import com.ecomm.service.AuthService;

import java.util.Scanner;

public class AdminProcess {
	 private Scanner scanner;
	    private AuthService<Admin> adminService;

	    public AdminProcess(Scanner scanner, AuthService<Admin> adminService) {
	        this.scanner = scanner;
	        this.adminService = adminService;
	    }

    public void run() throws Exception {
        boolean continueAdminSession = true;

        while (continueAdminSession) {
            System.out.println("Admin - Login (L) / Register (R) / Exit (E)");
            String action = scanner.nextLine();

            if ("L".equalsIgnoreCase(action)) {
                handleLogin();
            } else if ("R".equalsIgnoreCase(action)) {
                handleRegistration();
            } else if ("E".equalsIgnoreCase(action)) {
                System.out.println("Exiting admin session.");
                continueAdminSession = false;
            } else {
                System.out.println("Invalid option selected.");
            }
        }
    }

    private void handleLogin() throws Exception {
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        Admin admin = adminService.getUserByEmailAndPassword(email, password);
        if (admin != null) {
            System.out.println("Welcome, " + admin.getName());
            System.out.println("Access Admin Menu? (Y/N):");
            String accessMenu = scanner.nextLine();
            if ("Y".equalsIgnoreCase(accessMenu)) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.main(new String[]{});
              
                System.out.println("Continue session? (Y/N):");
                String continueSession = scanner.nextLine();
                if ("N".equalsIgnoreCase(continueSession)) {
                    // Exit the admin session
                }
            }
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private void handleRegistration() throws Exception {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Phone:");
        String phone = scanner.nextLine();

        Admin newAdmin = new Admin(name, email, password, phone);
        Admin registeredAdmin = adminService.register(newAdmin);
        if (registeredAdmin != null) {
            System.out.println("Admin registered successfully. Please login now.");
        } else {
            System.out.println("Registration failed.");
        }
    }
}