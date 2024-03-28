package com.ecomm.model;

import com.ecomm.model.Category;
import java.util.Objects;

public class Product {
	
	private int productId;
	private Category category;    // has a relationship (Specific category has many products.)
	private String name;
	private String description;
	private double price;
	private int qty;
	private double discount;
	private String image;
	
	public Product() {
		super();
	}

	public Product(int productId, Category category, String name, String description, double price, int qty,
			double discount, String image) {
		super();
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.qty = qty;
		this.discount = discount;
		this.image = image;
	}

	public Product(Category category, String name, String description, double price, int qty, double discount,
			String image) {
		super();
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.qty = qty;
		this.discount = discount;
		this.image = image;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category + ", name=" + name + ", description="
				+ description + ", price=" + price + ", qty=" + qty + ", discount=" + discount + ", image="
				+ image + "]";
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
	
	
}
