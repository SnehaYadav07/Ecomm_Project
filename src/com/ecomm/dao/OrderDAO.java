package com.ecomm.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.model.Cart;
import com.ecomm.model.Order;
import com.ecomm.model.User;
import com.ecomm.util.DBConnection;


public class OrderDAO implements IDao<Order> {
    private Connection connection;
    private UserDAO userDAO;

    public OrderDAO() {
        this.connection = DBConnection.getDbConnection().getConnection();
        this.userDAO = new UserDAO();
    }

    @Override
    public void add(Order order) throws SQLException {
    	 String query = "INSERT INTO Orders (Status, PaymentType, UserID) VALUES (?, ?, ?)";
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setString(1, order.getStatus());
         statement.setString(2, order.getPaymentType());
         statement.setInt(3, order.getUser().getUserId());

         statement.executeUpdate();
         
         // Now, retrieve the newly inserted order ID
         int orderId = getLastInsertedOrderId();
         if (orderId != -1) {
             order.setOrderId(orderId);
             
             // Now, insert ordered products directly into OrderedProduct table
             addOrderedProducts(orderId, order.getOrderedProducts());
         } else {
             throw new SQLException("Failed to retrieve auto-generated order ID.");
         }
    }

    @Override
    public void update(Order order) throws SQLException {
    	   String query = "UPDATE Orders SET Status = ?, PaymentType = ? WHERE OrderID = ?";
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1, order.getStatus());
           statement.setString(2, order.getPaymentType());
           statement.setInt(3, order.getOrderId());
           statement.executeUpdate();
    }

    @Override
    public void delete(Order order) throws SQLException {
        deleteOrder(order.getOrderId());
    }

    @Override
    public Order findOne(int orderId) throws SQLException {
    	 String query = "SELECT * FROM Orders WHERE OrderID = ?";
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setInt(1, orderId);
         ResultSet resultSet = statement.executeQuery();

         if (resultSet.next()) {
             String status = resultSet.getString("Status");
             String paymentType = resultSet.getString("PaymentType");
             int userId = resultSet.getInt("UserID");
             User user = userDAO.getUserById(userId);
             // Retrieve OrderDate as a TIMESTAMP from the database
             Timestamp orderTimestamp = resultSet.getTimestamp("OrderDate");
             // Convert TIMESTAMP to LocalDate
             LocalDate orderDate = orderTimestamp.toLocalDateTime().toLocalDate();
             Order order = new Order(status, paymentType, user, orderDate);
             order.setOrderId(orderId);
             order.setOrderedProducts(user.getCartItems()); // Utilize cart items as ordered products
             return order;
         }

         return null;
    }

    @Override
    public List<Order> findAll() throws SQLException {
    	 List<Order> orders = new ArrayList<>();
         String query = "SELECT * FROM Orders";
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
             int orderId = resultSet.getInt("OrderID");
             String status = resultSet.getString("Status");
             String paymentType = resultSet.getString("PaymentType");
             int userId = resultSet.getInt("UserID");
             User user = userDAO.getUserById(userId);
             LocalDate orderDate = resultSet.getObject("OrderDate", LocalDate.class);
             Order order = new Order(status, paymentType, user, orderDate);
             order.setOrderId(orderId);
             order.setOrderedProducts(user.getCartItems()); // Utilize cart items as ordered products
             orders.add(order);
         }

         return orders;
    }

    public void addOrder(Order order) throws SQLException {
       
    }


    private int getLastInsertedOrderId() throws SQLException {
        String query = "SELECT MAX(OrderID) AS LastOrderID FROM Orders";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("LastOrderID");
        } else {
            return -1; // If no order was inserted, return -1
        }
    }



    public void deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM Orders WHERE OrderID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, orderId);
        statement.executeUpdate();
    }


    public void addOrderedProducts(int orderId, List<Cart> cartItems) throws SQLException {
        // Check if cartItems is null or empty
        if (cartItems == null || cartItems.isEmpty()) {
            // No cart items to add, return without further processing
            return;
        }
        
        // Assuming the cart items are removed after checkout
        for (Cart cart : cartItems) {
            String query = "INSERT INTO OrderedProduct (OrderID, Name, Qty, Price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId); // Insert the OrderID
            statement.setString(2, cart.getProduct().getName());
            statement.setInt(3, cart.getQuantity());
            statement.setDouble(4, cart.getProduct().getPrice());
            statement.executeUpdate();
        }
    }

}
