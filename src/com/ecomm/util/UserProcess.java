package com.ecomm.util;

import com.ecomm.dao.UserDAO;
import com.ecomm.model.User;
import com.ecomm.service.AuthService;
import com.ecomm.service.UserService;

import java.util.Scanner;

public class UserProcess {
    private Scanner scanner;
    private AuthService<User> userService;

    public UserProcess(Scanner scanner, AuthService<User> userService) {
        this.scanner = scanner;
        this.userService = userService;
    }
    public void run() throws Exception {
        boolean continueUserSession = true;

        while (continueUserSession) {
            System.out.println("User - Login (L) / Register (R) / Exit (E)");
            String action = scanner.nextLine();

            if ("L".equalsIgnoreCase(action)) {
                handleLogin();
            } else if ("R".equalsIgnoreCase(action)) {
                handleRegistration();
            } else if ("E".equalsIgnoreCase(action)) {
                System.out.println("Exiting user session.");
                continueUserSession = false;
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

        User user = userService.getUserByEmailAndPassword(email, password);
        if (user != null) {
            System.out.println("Welcome, " + user.getName());
            System.out.println("Access E-commerce shopping Menu? (Y/N)");
            String userChoice = scanner.nextLine();
            if ("Y".equalsIgnoreCase(userChoice)) {
                UserMenu.setUser(user);
                UserMenu.main(new String[]{});
                // Optionally, you might want to ask whether to continue or log out after returning from the user menu.
                System.out.println("Continue session? (Y/N):");
                String continueSession = scanner.nextLine();
                if ("N".equalsIgnoreCase(continueSession)) {
                    // Exit the user session
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
        System.out.println("Enter Gender (M/F):");
        String gender = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter Pincode:");
        String pincode = scanner.nextLine();
        System.out.println("Enter State:");
        String state = scanner.nextLine();

        User newUser = new User(name, email, password, phone, gender, address, city, pincode, state);
        User registeredUser = userService.register(newUser);
        if (registeredUser != null) {
            System.out.println("User registered successfully: " + registeredUser);
            // Optionally prompt for login or further actions
        } else {
            System.out.println("Registration failed.");
        }
    }
}