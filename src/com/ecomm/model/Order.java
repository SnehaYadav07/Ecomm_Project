package com.ecomm.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    
    private int orderId;
    private String status;
    private String paymentType;
    private final User user;
    private final LocalDate orderDate;
    private List<Cart> orderedProducts; // Changed to cart items
    
    private static int counter;
    
    static {
        counter = 0;
    }
    
    {
        this.orderId = ++counter;
    }

    public Order(String status, String paymentType, User user, LocalDate orderDate) {
        this.status = status;
        this.paymentType = paymentType;
        this.user = user;
        this.orderDate = orderDate;
    }
    
    

    public Order(int orderId, String status, String paymentType, User user, LocalDate orderDate,
			List<Cart> orderedProducts) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.paymentType = paymentType;
		this.user = user;
		this.orderDate = orderDate;
		this.orderedProducts = orderedProducts;
	}



	public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public User getUser() {
        return user;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Cart> getOrderedProducts() {
        return orderedProducts;
    }

    // Method to set ordered products
    public void setOrderedProducts(List<Cart> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", status=" + status + ", paymentType=" + paymentType + ", user=" + user
                + ", orderDate=" + orderDate + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return orderId == other.orderId;
    }

    // Method to validate the order status
    private boolean isValidStatus(String newStatus) {
        // You can define your logic for validating the status, e.g., checking against a list of valid status values
        return newStatus != null && !newStatus.isEmpty();
    }

    // Method to check if the order is eligible for payment
    public boolean isEligibleForPayment() {
        return status.equals("Pending") || status.equals("Incomplete");
    }

    public void processPayment(double amount) {
        if (isEligibleForPayment()) {
            // Process payment logic goes here
            System.out.println("Payment processed successfully for order ID: " + orderId + " Amount: " + amount);
        } else {
            System.out.println("Order is not eligible for payment. Status: " + status);
        }
    }
}
