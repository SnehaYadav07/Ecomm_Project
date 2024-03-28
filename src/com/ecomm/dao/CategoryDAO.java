package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.Category;
import com.ecomm.util.DBConnection;

public class CategoryDAO implements IDao<Category> {
    private Connection connection;

    public CategoryDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
    }

    @Override
    public void add(Category category) throws SQLException {
    	 String query = "INSERT INTO Category (CategoryName) VALUES (?)";
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setString(1, category.getCategoryName());
         statement.executeUpdate();
    }

    @Override
    public void update(Category category) throws SQLException {
    	 String query = "UPDATE Category SET CategoryName = ? WHERE CategoryID = ?";
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setString(1, category.getCategoryName());
         statement.setInt(2, category.getCategoryId());
         statement.executeUpdate();
    }

    @Override
    public void delete(Category category) throws SQLException {
        String query = "DELETE FROM Category WHERE CategoryID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, category.getCategoryId());
        statement.executeUpdate();
    }

    @Override
    public Category findOne(int categoryId) throws SQLException {
    	 String query = "SELECT CategoryName FROM Category WHERE CategoryID = ?";
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setInt(1, categoryId);
         ResultSet resultSet = statement.executeQuery();

         if (resultSet.next()) {
             String categoryName = resultSet.getString("CategoryName");
             return new Category(categoryId, categoryName, null); // Assuming null for image
         }

         return null;
    }

    @Override
    public List<Category> findAll() throws SQLException {
    	 List<Category> categories = new ArrayList<>();
         String query = "SELECT CategoryID, CategoryName FROM Category";
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
             int categoryId = resultSet.getInt("CategoryID");
             String categoryName = resultSet.getString("CategoryName");
             categories.add(new Category(categoryId, categoryName, null)); // Assuming null for image
         }

         return categories;
    }

}
