package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.Category;
import com.ecomm.model.Product;
import com.ecomm.util.DBConnection;

public class ProductDao {

    private DBConnection dbConnection;

    public ProductDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean addCategory(Category category) {
        String sql = "INSERT INTO Category (CategoryName, CategoryDescription) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Get the connection
            preparedStatement = connection.prepareStatement(sql); // Prepare the statement

            preparedStatement.setString(1, category.getCategoryName()); // Set category name
            preparedStatement.setString(2, category.getCategoryDescription()); // Set category description

            int rowsAffected = preparedStatement.executeUpdate(); // Execute update
            return rowsAffected > 0; // Return true if row is added
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there's an exception
        } finally {
            // Clean up resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close(); // Close the statement
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Connection is not closed to keep it open
        }
    }


    public boolean addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        String sql = "INSERT INTO Product (CategoryID, Name, Description, Price, Qty, Discount, Image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Obtain the connection
            preparedStatement = connection.prepareStatement(sql); // Prepare the statement

            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, product.getCategory().getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getQty());
            preparedStatement.setDouble(6, product.getDiscount());
            preparedStatement.setString(7, product.getImage());

            // Execute the update and check the number of rows affected
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row is affected
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Cleanup resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close(); // Ensure the PreparedStatement is closed
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Note: The Connection is intentionally not closed here to keep it open after the method execution.
        }
    }
    
    
    public List<Product> findAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get the connection from your DBConnection class
            connection = dbConnection.getConnection();

            // SQL query to select all columns from the Product table
            String sql = "SELECT ProductID, CategoryID, Name, Description, Price, Qty, Discount, Image FROM Product";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add each product to the list
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                int categoryId = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                int qty = resultSet.getInt("Qty");
                double discount = resultSet.getDouble("Discount");
                String image = resultSet.getString("Image");

                		Category category = new Category();
                		Product product = new Product(productId, category, name, description, price, qty, discount, image);
                products.add(product);
            }
        } finally {
            // Close resources - consider using a utility method to make this cleaner
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) { /* Ignored */}
            }
            // Do NOT close the connection here if you want it to remain open
        }

        return products;
    }


    public boolean deleteProduct(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;
        
        try {
            connection = dbConnection.getConnection(); // Get connection
            String sql = "DELETE FROM Product WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, productId);
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            // Close the PreparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
        }
        return success;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET Name = ?, Description = ?, Price = ?, Qty = ?, Discount = ?, Image = ? WHERE ProductID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection(); // Get the connection from your DB connection manager
            preparedStatement = connection.prepareStatement(sql); // Prepare the statement
            
            // Set parameters for the PreparedStatement based on the product details
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQty());
            preparedStatement.setDouble(5, product.getDiscount());
            preparedStatement.setString(6, product.getImage()); // Assume image handling logic is elsewhere
            preparedStatement.setInt(7, product.getProductId());
            
            // Execute the update and check the number of rows affected
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row is affected
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Cleanup resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close(); // Ensure the PreparedStatement is closed
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Note: The Connection is intentionally not closed here to keep it open after the method execution
        }
    }


    public Product findProductById(int productId) {
        String sql = "SELECT p.ProductID, p.CategoryID, p.Name, p.Description, p.Price, p.Qty, p.Discount, p.Image, " +
                     "c.CategoryName, c.CategoryDescription FROM Product p " +
                     "JOIN Category c ON p.CategoryID = c.CategoryID WHERE p.ProductID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Assuming you have appropriate constructors or setter methods
                Category category = new Category(resultSet.getInt("CategoryID"),
                                                 resultSet.getString("CategoryName"),
                                                 resultSet.getString("CategoryDescription"));
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductID"));
                product.setCategory(category);
                product.setName(resultSet.getString("Name"));
                product.setDescription(resultSet.getString("Description"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setQty(resultSet.getInt("Qty"));
                product.setDiscount(resultSet.getDouble("Discount"));
                product.setImage(resultSet.getString("Image"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Connection is intentionally not closed to keep it open for further use
        }
        return null;
    }

    
    // Implement updateProduct and deleteProduct methods similarly

}
