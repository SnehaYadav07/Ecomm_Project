package com.ecomm;

import com.ecomm.model.Admin;
import com.ecomm.model.User;
import com.ecomm.service.AdminService;
import com.ecomm.service.AuthService;
import com.ecomm.service.AuthServiceFactory;
import com.ecomm.service.UserService;
import com.ecomm.util.AdminProcess;
import com.ecomm.util.UserProcess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AuthService<Admin> adminService = AuthServiceFactory.getAdminService();
        AuthService<User> userService = AuthServiceFactory.getUserService();

        System.out.println("Welcome to E-commerce Application!");

        boolean exit = false;
        while (!exit) {
            System.out.println("Select Role: Admin (A) / User (U) / Exit (E)");
            String role = scanner.nextLine().trim().toUpperCase();

            switch (role) {
                case "A":
                    AdminProcess adminProcess = new AdminProcess(scanner, adminService);
                    adminProcess.run();
                    break;
                case "U":
                    UserProcess userProcess = new UserProcess(scanner, userService);
                    userProcess.run();
                    break;
                case "E":
                    System.out.println("Exiting application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}