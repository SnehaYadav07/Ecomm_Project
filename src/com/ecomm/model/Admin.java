package com.ecomm.model;

import java.util.Objects;

public class Admin {
	
	private int adminId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private static int counter; 
	
	static {
		counter=0;
	}
	//non static block
	{
		this.adminId=++counter;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String name, String email, String password, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	
	
	public Admin(int adminId, String name, String email, String password, String phone) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
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
	public int getAdminId() {
		return adminId;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(adminId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return adminId == other.adminId;
	}
	
	public void resetPassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password reset successfully.");
    }
}
