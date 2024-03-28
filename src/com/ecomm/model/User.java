package com.ecomm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    
    private int userId;
    private final LocalDate registeredDate;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String address;
    private String city;
    private String pincode;
    private String state;
    private static int counter; 

    // List to store the user's cart items
    private ArrayList<Cart> cartItems = new ArrayList<>();

    static {
        counter = 0;
    }

    {
        this.userId = ++counter;
        this.registeredDate = LocalDate.now();
    }

    public User(String name, String email, String password, String phone, String gender, String address, String city,
            String pincode, String state) {
        super();
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

    public LocalDate getRegisteredDate() {
        return registeredDate;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        // This method sets the userId
        this.userId = userId;
    }

    
    public ArrayList<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public void addToCart(Cart cart) {
        this.cartItems.add(cart);
    }

    public void removeFromCart(Cart cart) {
        this.cartItems.remove(cart);
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", gender="
                + gender + ", registeredDate=" + registeredDate + ", address=" + address + ", city=" + city
                + ", pincode=" + pincode + ", state=" + state + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, userId);
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
        return Objects.equals(email, other.email) && Objects.equals(phone, other.phone) && userId == other.userId;
    }
    
    public void clearCart() {
        this.cartItems.clear();
    }

}
