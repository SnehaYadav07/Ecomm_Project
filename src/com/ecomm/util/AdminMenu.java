package com.ecomm.util;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ecomm.dao.CategoryDAO;
import com.ecomm.dao.ProductDAO;
import com.ecomm.model.Category;
import com.ecomm.model.Product;

public class AdminMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductDAO productDAO = new ProductDAO();
    private static CategoryDAO categoryDAO = new CategoryDAO();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Menu:");
            System.out.println("1. Create Category");
            System.out.println("2. Create Product");
            System.out.println("3. View Available Products");
            System.out.println("4. View Categories");
            System.out.println("5. Modify Product");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createCategory();
                    break;
                case 2:
                    createProduct();
                    break;
                case 3:
                    viewAvailableProducts();
                    break;
                case 4:
                    viewCategories();
                    break;
                case 5:
                    modifyProduct();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        scanner.close();
    }

    private static void createCategory() {
        System.out.println("Enter Category Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Category Image:");
        String image = scanner.nextLine();

        Category category = new Category(name, image);
        try {
            categoryDAO.add(category);
            System.out.println("Category created successfully: " + category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createProduct() {
        System.out.println("Enter Category ID:");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Category category = categoryDAO.findOne(categoryId);
            if (category == null) {
                System.out.println("Category with ID " + categoryId + " not found.");
                return;
            }

            System.out.println("Enter Product Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Product Description:");
            String description = scanner.nextLine();
            System.out.println("Enter Product Price:");
            double price = scanner.nextDouble();
            System.out.println("Enter Product Quantity:");
            int qty = scanner.nextInt();
            System.out.println("Enter Product Discount:");
            int discount = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Product product = new Product(category, name, description, price, qty, discount);
            productDAO.add(product);
            System.out.println("Product created successfully: " + product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAvailableProducts() {
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

    private static void modifyProduct() {
        System.out.println("Enter Product ID to Modify:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Product product = productDAO.findOne(productId);
            if (product != null) {
                System.out.println("Enter New Product Name:");
                String name = scanner.nextLine();
                System.out.println("Enter New Product Description:");
                String description = scanner.nextLine();
                System.out.println("Enter New Product Price:");
                double price = scanner.nextDouble();
                System.out.println("Enter New Product Quantity:");
                int qty = scanner.nextInt();
                System.out.println("Enter New Product Discount:");
                int discount = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQty(qty);
                product.setDiscount(discount);

                productDAO.updateProduct(product);
                System.out.println("Product details updated successfully: " + product);
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewCategories() {
        System.out.println("Available Categories:");
        try {
            List<Category> categories = categoryDAO.findAll();
            for (Category category : categories) {
                System.out.println(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
