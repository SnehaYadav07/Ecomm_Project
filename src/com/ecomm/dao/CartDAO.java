package com.ecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.Cart;
import com.ecomm.model.Product;
import com.ecomm.model.User;
import com.ecomm.util.DBConnection;

public class CartDAO {
	private Connection connection;
    private ProductDAO productDAO;

    public CartDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
        this.productDAO = new ProductDAO();
    }

    public void addCartItem(User user, int productId, int quantity) throws SQLException {
        String query = "INSERT INTO cart (UserID, ProductID, Qty) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getUserId());
        statement.setInt(2, productId);
        statement.setInt(3, quantity);
        statement.executeUpdate();
    }


    public void updateCartItem(User user, int cartId, int quantity) throws SQLException {
        String query = "UPDATE cart SET Qty = ? WHERE CartID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, quantity);
        statement.setInt(2, cartId);
        statement.executeUpdate();
    }

    public void deleteCartItem(int cartId) throws SQLException {
        String query = "DELETE FROM cart WHERE CartID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cartId);
        statement.executeUpdate();
    }
//
//    public List<Cart> getCartItemsByUserId(int userId) throws SQLException {
//        List<Cart> cartItems = new ArrayList<>();
//        String query = "SELECT * FROM cart WHERE UserID = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, userId);
//        ResultSet resultSet = statement.executeQuery();
//
//        while (resultSet.next()) {
//            int productId = resultSet.getInt("ProductID");
//            int qty = resultSet.getInt("Qty");
//            Product product = productDAO.getProductById(productId);
//            int cartId = resultSet.getInt("CartID");
//            Cart cart = new Cart(cartId, userId, product, qty);
//            cartItems.add(cart);
//        }
//
//        return cartItems;
//    }
}
