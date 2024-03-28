package com.ecomm.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
	
	private int orderId;
	private String status;
	private String paymentType;         // user has order something. -> has a 
	private User user;
	private Date orderDate;
	
	public Order() {
		super();
	}

	public Order(int orderId, String status, String paymentType, User user, Date orderDate) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.paymentType = paymentType;
		this.user = user;
		this.orderDate = orderDate;
	}

	public Order(String status, String paymentType, User user, Date orderDate) {
		super();
		this.status = status;
		this.paymentType = paymentType;
		this.user = user;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	
}
