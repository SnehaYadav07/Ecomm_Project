/*

package com.ecomm.model;
// For Working without DB Connection
import java.sql.SQLException;
import java.util.List;

import com.ecomm.dao.CategoryDAO;
import com.ecomm.dao.ProductDAO;

public class ProductManager {
    private static ProductManager instance;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    private ProductManager() {
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
    }

    // Singleton getInstance method to get the single instance of ProductManager
    public synchronized static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    // Add a product
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a product
    public void removeProduct(Product product) {
        try {
            productDAO.deleteProduct(product.getProductId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all products
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find a product by ID
    public Product findProductById(int productId) {
        try {
            return productDAO.getProductById(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update product details
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit product details
    public void editProductDetails(int productId, String name, String description, double price, int qty, double discount) {
        try {
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQty(qty);
                product.setDiscount(discount);
                productDAO.updateProduct(product);
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a category
    public void addCategory(Category category) {
        try {
            categoryDAO.add(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a category
    public void removeCategory(Category category) {
        try {
            categoryDAO.deleteCategory(category.getCategoryId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a category by ID
    public Category findCategoryById(int categoryId) {
        try {
            return categoryDAO.getCategoryById(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        try {
            return categoryDAO.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

*/
