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

public class ProductDAO implements IDao<Product> {
    private Connection connection;
    private CategoryDAO categoryDAO;

    public ProductDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public void add(Product product) throws SQLException {
    	  String query = "INSERT INTO Product (CategoryID, Name, Description, Price, Qty, Discount) VALUES (?, ?, ?, ?, ?, ?)";
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, product.getCategory().getCategoryId());
          statement.setString(2, product.getName());
          statement.setString(3, product.getDescription());
          statement.setDouble(4, product.getPrice());
          statement.setInt(5, product.getQty());
          statement.setDouble(6, product.getDiscount());
          statement.executeUpdate();
    }

    @Override
    public void update(Product product) throws SQLException {
        updateProduct(product);
    }

    @Override
    public void delete(Product product) throws SQLException {
        deleteProduct(product.getProductId());
    }

    @Override
    public Product findOne(int productId) throws SQLException {
    	String query = "SELECT * FROM Product WHERE ProductID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int categoryId = resultSet.getInt("CategoryID");
            Category category = categoryDAO.findOne(categoryId);
            String name = resultSet.getString("Name");
            String description = resultSet.getString("Description");
            double price = resultSet.getDouble("Price");
            int qty = resultSet.getInt("Qty");
            double discount = resultSet.getDouble("Discount");
            return new Product(productId, category, name, description, price, qty, discount);
        }

        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
    	 List<Product> products = new ArrayList<>();
         String query = "SELECT * FROM Product";
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
             int productId = resultSet.getInt("ProductID");
             int categoryId = resultSet.getInt("CategoryID");
             Category category = categoryDAO.findOne(categoryId);
             String name = resultSet.getString("Name");
             String description = resultSet.getString("Description");
             double price = resultSet.getDouble("Price");
             int qty = resultSet.getInt("Qty");
             double discount = resultSet.getDouble("Discount");
             products.add(new Product(productId, category, name, description, price, qty, discount));
         }

         return products;
    }



    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE Product SET CategoryID = ?, Name = ?, Description = ?, Price = ?, Qty = ?, Discount = ? WHERE ProductID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, product.getCategory().getCategoryId());
        statement.setString(2, product.getName());
        statement.setString(3, product.getDescription());
        statement.setDouble(4, product.getPrice());
        statement.setInt(5, product.getQty());
        statement.setDouble(6, product.getDiscount());
        statement.setInt(7, product.getProductId());
        statement.executeUpdate();
    }

    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        statement.executeUpdate();
    }

    
    public int getProductQuantity(int productId) throws SQLException {
        String query = "SELECT Qty FROM Product WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("Qty");
                }
            }
        }
        return -1; // Return -1 if product quantity not found
    }

    public void updateProductQuantity(int productId, int newQuantity) throws SQLException {
        String query = "UPDATE Product SET Qty = ? WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }
}
