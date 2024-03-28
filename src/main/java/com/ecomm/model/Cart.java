package com.ecomm.model;

import java.util.List;
import java.util.Objects;

public class Cart {
	
	private int cartId;
	private User user;
    private Product product;
    private int qty;
	
    public Cart() {
		super();
	}

	public Cart(int cartId, User user, Product product, int qty) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.product = product;
		this.qty = qty;
	}

	public Cart(User user, Product product, int qty) {
		super();
		this.user = user;
		this.product = product;
		this.qty = qty;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", product=" + product + ", qty=" + qty + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return cartId == other.cartId;
	}
	
	
	
}
