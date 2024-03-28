package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.ecomm.model.Category;
import com.ecomm.util.DBConnection;

public class CategoryDao {

    private DBConnection dbConnection;

    public CategoryDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Method to add a new category to the database
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO Category (CategoryName, CategoryDescription) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryDescription());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Note: Connection is not closed here to keep it open after the method returns.
        }
    }

    
    
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM category WHERE categoryId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = this.dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("categoryId");
                String name = resultSet.getString("categoryName");
                String description = resultSet.getString("categoryDescription");
                return new Category(id, name, description);
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
            // Note: Connection is not closed here to keep it open after the method returns.
        }
        return null;
    }


    
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, CategoryName FROM Category"; // Adjust according to your schema
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("CategoryID")); // Ensure this matches your column name
                category.setCategoryName(resultSet.getString("CategoryName")); // And this too
                // Add other fields as necessary
                
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
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
            // Note: Do not close the connection here if you want it to remain open after the method returns.
        }
        return categories;
    }


    // Additional methods for fetching, updating, and deleting categories can be added here
}
