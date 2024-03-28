package com.ecomm.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class User {
	
	private int userId;
	private Date registeredDate;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String gender;
	private String address;
	private String city;
	private String pincode;
	private String state;
	
	public User() {
		super();
	}
	
	public User(int userId, Date registeredDate, String name, String email, String password, String phone,
			String gender, String address, String city, String pincode, String state) {
		super();
		this.userId = userId;
		this.registeredDate = registeredDate;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}

	public User(Date registeredDate, String name, String email, String password, String phone, String gender,
			String address, String city, String pincode, String state) {
		super();
		this.registeredDate = registeredDate;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", registeredDate=" + registeredDate + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", gender=" + gender + ", address=" + address
				+ ", city=" + city + ", pincode=" + pincode + ", state=" + state + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userId == other.userId;
	}
	
	
	
}
