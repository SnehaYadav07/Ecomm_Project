package com.ecomm.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.ecomm.dao.OrderDAO;
import com.ecomm.dao.ProductDAO;
import com.ecomm.dao.UserDAO;
import com.ecomm.dao.CartDAO;
import com.ecomm.model.*;
import com.ecomm.service.AuthService;
import com.ecomm.service.AuthServiceFactory;

public class UserMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductDAO productDAO = new ProductDAO();
    private static UserDAO userDAO = new UserDAO();
    private static OrderDAO orderDAO = new OrderDAO();
    private static CartDAO cartDAO = new CartDAO();
    private static User user;
    private static AuthService<User> userService = AuthServiceFactory.getUserService();

    public static void setUser(User user) {
        UserMenu.user = user;
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("User Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Buy Product");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    buyProduct();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void viewProducts() {
        System.out.println("Available Products:");
        try {
            List<Product> products = productDAO.findAll();
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void buyProduct() {
        System.out.println("Enter Product ID to Buy:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Product product = productDAO.findOne(productId);
            if (product != null) {
                // Add the product to the cart item
                Cart cartItem = new Cart(product, quantity);
                user.addToCart(cartItem);
                System.out.println("Product added to cart successfully.");

                // Update the cart table with the details from the cart item
                cartDAO.addCartItem(user, productId, quantity);
                System.out.println("Cart table updated successfully.");
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void viewCart() {
        System.out.println("Items in Cart:");
        List<Cart> cartItems = user.getCartItems();
        for (Cart cart : cartItems) {
            System.out.println(cart);
        }
    }

    private static void checkout() {
        try {
            System.out.println("Processing payment for the items in the cart...");
            double totalAmount = calculateTotalAmount();
            
            // Display the total amount to the user
            System.out.println("Total amount: $" + totalAmount);
            
            // Prompt the user to confirm the checkout process
            System.out.println("Confirm checkout? (Y/N)");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (!confirmation.equals("Y")) {
                System.out.println("Checkout canceled.");
                return;
            }

            // Create the order with status "Paid" instead of "Pending"
            Order order = new Order("Paid", "Credit Card", user, LocalDate.now());
            orderDAO.addOrder(order);

            // Add the ordered products to the OrderedProduct table
            orderDAO.addOrderedProducts(order.getOrderId(), user.getCartItems());
            
            // Update product quantities in the database
            updateProductQuantities(user.getCartItems());

            // Process payment only if the user confirms checkout
            boolean paymentSuccess = processPayment(order, totalAmount);
            if (paymentSuccess) {
                System.out.println("Payment processed successfully.");
                // Clear user's cart after successful payment
                user.clearCart();
            } else {
                System.out.println("Payment failed. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProductQuantities(List<Cart> cartItems) throws SQLException {
        for (Cart cart : cartItems) {
            int productId = cart.getProduct().getProductId();
            int currentQuantity = productDAO.getProductQuantity(productId);
            int newQuantity = currentQuantity - cart.getQuantity();
            productDAO.updateProductQuantity(productId, newQuantity);
        }
    }



    
    private static boolean processPayment(Order order, double totalAmount) {
       
        order.setStatus("Paid");
        try {
            orderDAO.update(order); // Update order status in the database
            return true; // Return true indicating payment success
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database update error
            return false; // Return false indicating payment failure
        }
    }


    private static double calculateTotalAmount() {
        double total = 0.0;
        List<Cart> cartItems = user.getCartItems();
        for (Cart cart : cartItems) {
            total += cart.getProduct().getPrice() * cart.getQuantity();
        }
        return total;
    }
}
