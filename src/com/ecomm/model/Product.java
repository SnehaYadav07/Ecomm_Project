package com.ecomm.model;

import java.util.Objects;

public class Product {
    
    private int productId;
    private final Category category; // Reference to the associated Category
    private String name;
    private String description;
    private double price;
    private int qty;
    private double discount;
    private static int counter;

    static {
        counter = 0;
    }

    {
        this.productId = ++counter;
    }

    public Product(Category category, String name, String description, double price, int qty, double discount) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
    }

    public Product(int productId, Category category, String name, String description, double price, int qty,
            double discount) {
        super();
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0% and 100%");
        }
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return productId == other.productId;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", category=" + category + ", name=" + name + ", description="
                + description + ", price=" + price + ", qty=" + qty + ", discount=" + discount + "]";
    }
}
