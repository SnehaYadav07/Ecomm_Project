package com.ecomm.model;

public class Cart {
    
    private int cartId;
    private Product product;
    private int quantity;
    
    public Cart(int cartId, Product product, int quantity) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }
    
    public Cart(int cartId, Product product) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = 0; // Initialize quantity to zero
    }
    
    

    public Cart(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", product=" + product + ", quantity=" + quantity + "]";
    }
    
    public void addItem(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        // Update the quantity
        this.quantity += quantity;
    }

    public void removeItem(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        if (this.quantity - quantity <= 0) {
            // If the quantity becomes zero or less, remove the item from the cart
            this.quantity = 0;
        } else {
            // Update the quantity
            this.quantity -= quantity;
        }
    }
}
